package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//데이터베이스의 커넥션풀을 사용하도록 설정하는 메소드
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			//데이터소스에 의해서 
			con = ds.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//하나의 새로운 게시글이 넘어와서 저장되는 메소드
	public void insertBoard(BoardBean bean) {
		//빈클래스에 넘어오지 않았던 데이터들을 초기화 해줘야한다.
		int ref = 0;	//글 그룹의미(쿼리를 실행시켜서 가장 큰 ref를 가져온 후 +1하면됨)
		int re_step = 1;	//새로운 글이기에 1(최상위 부모)
		int re_level = 1;	//새로운 글이기에 1(최상위 부모)
		try {
			//데이터베이스에 연결
			getCon();
			
			//가장 큰 ref값을 읽어오는 쿼리 준비
			String refsql = "select max(ref) from board";	//ref중에서 가장 큰것을 리턴하라 라는뜻
			//쿼리실행 객체
			pstmt = con.prepareStatement(refsql);
			//쿼리 실행 후 결과를 리턴
			rs = pstmt.executeQuery();	//rs에 결과리턴
			
			if(rs.next()) {		//쿼리실행 결과값이 있다면 추출
				ref = rs.getInt(1)+1;	//최대값에 +1을 더해서 글 그룹을 설정
			}
			//실제로 게시글 전체값을 테이블에 저장하는 소스
			//board_seq.NEXTVAL은 게시물 번호가 자동적으로 1씩 추가되게 만드는 오라클측 시퀀스이다 board_seq가 토드에서 설정한 시퀀스이름
			//sysdate는 현재 시스템의 날짜
			//values안에 순서대로 (num, writer, email, subject, password, reg_date, ref, re_step, re_level, readcount(어자피 첫글이니 조회수 0부터), content)으로 각각 매핑예정
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			//?에 값을 pstmt로 매핑, board_seq.NEXTVAL은 토드(오라클)에서 설정한 1씩증가 시퀀스 실행
			//setString(index, value)에서 인덱스는 ?의 순서를 의미, sql에서 중간중간 상수들은 포함안됨.
			pstmt.setString(1, bean.getWriter());	//1번짹 ?부터 writer부터
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			//쿼리실행
			pstmt.executeUpdate();
			//자원반납
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//데이터베이스에있는 모든 게시글을 가져오기, 카운터링할거기때문에 10개씩 가져온다 start부터 end까지
	public Vector<BoardBean> getAllBoard(int start, int end) {
		//리턴할 객체 선언
		Vector<BoardBean> v = new Vector<>();
		//디비접속
		getCon();
		
		try {
			//쿼리작성 이거는 그냥 전체 다 가져오기 쿼리
			//String sql = "select * from board order by ref desc, re_step asc";	//ref는 내림차순정렬,r re_step을 오름차순정렬해서 board에 있는거 전체 가져오기
																				//이렇게 하면 최신글이 가장 위로온다
			
			String sql = "select * from (select A.* ,Rownum Rnum from (select *from board order by ref desc, re_step asc)A)" + "where Rnum >= ? and Rnum <= ?";
			//(select *from board order by ref desc, re_step asc)이렇게 정렬 후 이거를 A라고 부르기로한다. 
			//그 다음 A.* 즉 A에대한 모든것과 Rnum을 함께 select(검색)한 후
			//(where Rnum>= ? and Rnum <= ?), 즉 Rnum을 기준으로 조건검색을 하여 그것을 select한다라는뜻
			
			//쿼리실행할 객체 생성
			pstmt = con.prepareStatement(sql);
			//?에 값 매핑
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			//쿼리실행 후 결과저장
			rs = pstmt.executeQuery();
			//데이터개수를 모르기에 반복문을 통해 데이터 추출
			while(rs.next()) {
				//데이터를 BoardBean클래스로 패키징
				BoardBean bean = new BoardBean();
				//rs의 첫번째 요소를 setNum에 넣어주기 이런식
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				
				//패키징한 데이터를 벡터에 저장
				v.add(bean);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//데이터베이스에서 하나의 게시글 리턴하는 메소드(BoardInfo에서 사용, 수정하기할때가 아닌 그냥 조회할때 사용하는애)
	public BoardBean getOneBoard(int num) {
		//리턴타입선언
		BoardBean bean = new BoardBean();
		//디비연결
		getCon();

		try {
			//조회수 증가 쿼리
								//업데이트해라, board의 readcount를 = readcount+1로, num이 ?인 게시글을
								//즉,  num이 ?인 게시글의 readcount를 readcount+1로 업데이트하라
			String readsql = "update board set readcount = readcount+1 where num=?";
			//쿼리실행할 객체 생성
			pstmt = con.prepareCall(readsql);
			pstmt.setInt(1, num);	//1번째?에는 num(BoardList에서 겟방식으로 넘겨준)을 넣어주겠다
			pstmt.executeUpdate();	//반영

			//쿼리준비
			String sql = "select * from board where num=?";	//board에 있는 모든 게시글중에 num가 ?인 게시글만 가져오기 
			//쿼리실행객체선언
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,num);	//1번째?에는 num(BoardList에서 겟방식으로 넘겨준)을 넣어주겠다
			//쿼리실행 후 결과리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {	//어자피 데이터 1개밖에 없으므로 if문
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	//답글달기 메서드
	public void reWriteBoard(BoardBean bean) {
		//부모 글그룹,글스텝,글레벨을 읽어들임
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		
		//디비연결
		getCon();
		try {
			//////////////////핵심코드//////////////////
			//부모글보다 큰 re_level의 값을 전부 1씩 증가시키기
			String levelsql = "update board set re_level=re_level+1 where ref=? and re_level > ?";
						//갱신해라, 보드테이블에서, re_level을 re_level에 1더한값으로, ref가 ?이면서 re_level이 ?보다 큰애들만
			//쿼리실행객체 선언
			pstmt = con.prepareStatement(levelsql);
			pstmt.setInt(1, ref);		//첫번째 물음표에 ref넣고
			pstmt.setInt(2, re_level);	//두번째 물음표에 re_level넣기
			
			//쿼리실행
			pstmt.executeUpdate();
		
			//답변글 데이터를 디비에 저장
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			//board_seq.NEXTVAL은 토드(오라클)에서 설정한 1씩증가 시퀀스 실행
			pstmt = con.prepareStatement(sql);
			//?에 값을 대입
			pstmt.setString(1, bean.getWriter());	//1번짹 ?부터 writer부터
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);		//부모의 ref를 넣어줌
			pstmt.setInt(6, re_step+1);		//답글이기에 부모글의re_step에 1을 더해서 넣어줌
			pstmt.setInt(7, re_level+1);		//levelsql쿼리로 부모글보다 큰애들은 1씩 증가시켜줬고 새로달린 글(자신)은 부모보다 1커야하니까 +1해준다
			//0인부분은 readcount, 어자피 새로운 글이니 조회수 0부터 시작
			pstmt.setString(8, bean.getContent());
			
			//쿼리실행
			pstmt.executeUpdate();
			con.close();			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//BoardUpdate와 Delelte에서 사용하는 게시글 한개 정보 가져오기 메서드
	//위에있는 getOneBoard에서 조회수 +1쿼리 부분만 제거함
		public BoardBean getOneUpdateBoard(int num) {
			//리턴타입선언
			BoardBean bean = new BoardBean();
			//디비연결
			getCon();

			try {

				//쿼리준비
				String sql = "select * from board where num=?";	//board에 있는 모든 게시글중에 num가 ?인 게시글만 가져오기 
				//쿼리실행객체선언
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,num);	//1번째?에는 num(BoardList에서 겟방식으로 넘겨준)을 넣어주겠다
				//쿼리실행 후 결과리턴
				rs = pstmt.executeQuery();
				if(rs.next()) {	//어자피 데이터 1개밖에 없으므로 if문
					bean.setNum(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setEmail(rs.getString(3));
					bean.setSubject(rs.getString(4));
					bean.setPassword(rs.getString(5));
					bean.setReg_date(rs.getDate(6).toString());
					bean.setRef(rs.getInt(7));
					bean.setRe_step(rs.getInt(8));
					bean.setRe_level(rs.getInt(9));
					bean.setReadcount(rs.getInt(10));
					bean.setContent(rs.getString(11));
				}
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return bean;
		}
		
		//update와 delete시 필요한 패스워드를 받아오는 메서드
		public String getPass(int num) {
			//리턴할 변수 객체 생성
			String pass = "";
			getCon();
			
			try {
				String sql = "select password from board where num=?";	//board에서 num이 ?인 행의 패스워드값을 가져오기
				//쿼리실행객체 선언
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				//쿼리실행
				rs = pstmt.executeQuery();
				//패스워드 값을 저장
				if(rs.next()) {	//패스워드가 디비에서 잘 찾아졌다면
					pass = rs.getString(1);
				}
				//자원반납
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return pass;
		}
	
		//게시글 수정(update)하는 메서드
		public void updateBoard(BoardBean bean) {
			//디비연
			getCon();
			
			try {
				//쿼리준비
				String sql = "update board set subject=? , content=? where num=?";
							//subject를 ?로 바꾸고 content를 ?로 바꿔라. 조건은 num가 ?인 애중에
				pstmt = con.prepareStatement(sql);
				
				//?값 대입
				pstmt.setString(1, bean.getSubject());
				pstmt.setString(2, bean.getContent());
				pstmt.setInt(3, bean.getNum());
				
				pstmt.executeUpdate();
				
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		//하나의 게시물을 삭제하는 메서드
		public void deleteBoard(int num) {
			getCon();
			try {
				//쿼리준비
				String sql = "delete from board where num=?";	//board테이블에서 num가 ?인 애를 삭제하라
				pstmt = con.prepareStatement(sql);
				//?매핑
				pstmt.setInt(1, num);
				//쿼리실행
				//select문이 아닐때는(수정, 삭제일때는) executeUpdate하는듯
				pstmt.executeUpdate();
				//자원반납
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		//전체 글의 개수반환하는 메서드
		public int getAllCount() {
			getCon();
			//게시글 전체 수를 저장하는 변수선언
			int count = 0;
			
			try {
				//쿼리준비
				String sql = "select count(*) from board";	//board테이블에서 전체(*)를 count함
				//쿼리실행할 객체 생성
				pstmt = con.prepareStatement(sql);
				//쿼리실행 후 결과리턴
				rs = pstmt.executeQuery();
				if(rs.next()) {	//데이터가 있다면
					count = rs.getInt(1);	//첫번째요소를 넣어줌
				}
				con.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			return count;
		}
}
