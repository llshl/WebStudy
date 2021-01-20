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
	
	//데이터베이스에 연결
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();	//커넥션 연결해주는 메서드
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	//전체 글의 개수 반환하는 메서드
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
			//데이터 한개만 가져오는것이기에 while은 안써도 된다.
			if(rs.next()) {	//데이터가 있다면
				count = rs.getInt(1);	//첫번째요소를 넣어줌
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	//화면에 보여질 데이터를 10개씩 추출해서 리턴하는 메서드
	public Vector<BoardBean> getAllBoard(int start, int end) {
		//리턴할 객체 선언
		Vector<BoardBean> v = new Vector<>();
		//디비접속
		getCon();
		
		try {
			//쿼리작성 이거는 그냥 전체 다 가져오기 쿼리
			//String sql = "select * from board order by ref desc, re_step asc";	
			//ref는 내림차순정렬(최신글이 위로), re_step을 오름차순정렬(답글그굷에서 오름차순)해서 board에 있는거 전체 가져오기
			//이렇게 하면 최신글이 가장 위로온다
			
			//Rownum이라는 것은 오라클에서 자체적으로 만들어주는 그런것. 따로 알아보자 Roqnum을 Rnum으로 지칭
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

	public void insertBoard(BoardBean bean) {
		getCon();
		int ref = 0;
		int re_step = 1;	//새 글을 등록하는것이므로
		int re_level = 1;	//새 글을 등록하는것이므로 
		try {
			//쿼리작성
			String refsql = "select max(ref) from board";	//ref중에 가장 큰걸 가져온다
			pstmt = con.prepareStatement(refsql);
			//쿼ㅣ실행 후 결과를 리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt(1)+1;	//가장 큰 값에 1을 더해준것
			}
			//데이터를 삽입하는 쿼리
			//실제로 게시글 전체값을 테이블에 저장하는 소스
			//board_seq.NEXTVAL은 게시물 번호가 자동적으로 1씩 추가되게 만드는 오라클측 시퀀스이다 board_seq가 토드에서 설정한 시퀀스이름
			//sysdate는 현재 시스템의 날짜
			//values안에 순서대로 (num, writer, email, subject, password, reg_date, ref, re_step, re_level, readcount(어자피 첫글이니 조회수 0부터), content)으로 각각 매핑예정
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			//?대입
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			//쿼리실행
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	//하나의 게시글을 읽어들이는 메서드
	public BoardBean getOneBoard(int num) {
		getCon();
		BoardBean bean = null;
		
		try {
			//하나의 게시글을 읽었다는 조회수 증가
			String countsql = "update board set readcount = readcount+1 where num=?";	//num가 ?인 게시글의 조회수를 1증가시킴
			pstmt = con.prepareStatement(countsql);
			//?매핑
			pstmt.setInt(1, num);
			//쿼리실행
			pstmt.executeUpdate();
			
			//한 게시글에 대한 정보를 리턴해주는 쿼리 작성
			String sql = "select * from board where num=?";	//nunm가 ?인 게시글을 디비에서 가져온다
			pstmt = con.prepareStatement(sql);
			//?대입
			pstmt.setInt(1, num);
			//쿼리실행후 게시글 리턴받는다
			rs = pstmt.executeQuery();
			//게시글을 리턴받았다면
			while(rs.next()) {
				//데이터를 BoardBean클래스로 패키징
				bean = new BoardBean();
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
				
			}
			con.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	//답변글을 저장하는 메서드
	public void reInsertBoard(BoardBean bean) {
		getCon();
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		try {
			/////////////////////핵심코드
			//쿼리작성
			String levelsql = "update board set re_level = re_level+1 where ref=? and re_level > ?";	//ref중에 가장 큰걸 가져온다
								//re_level을 1씩 증가, 조건은 해당 글 그룹의 게시글만 그리고 현재 들어온 re_level 보다 큰 값들만
			pstmt = con.prepareStatement(levelsql);
			//?매핑
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_level);
			
			pstmt.executeUpdate();
		
			//데이터를 삽입하는 쿼리
			//실제로 게시글 전체값을 테이블에 저장하는 소스
			//board_seq. NEXTVAL은 게시물 번호가 자동적으로 1씩 추가되게 만드는 오라클측 시퀀스이다 board_seq가 토드에서 설정한 시퀀스이름
			//sysdate는 현재 시스템의 날짜
			//values안에 순서대로 (num, writer, email, subject, password, reg_date, ref, re_step, re_level, readcount(어자피 첫글이니 조회수 0부터), content)으로 각각 매핑예정
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?)";
			pstmt = con.prepareStatement(sql);
			//?대입
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step+1);	//기존 부모글의 step보다 1을 증가시켜야하기때문에
			pstmt.setInt(7, re_level+1);	//이하동문
			pstmt.setString(8, bean.getContent());
			//쿼리실행
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//조회수를 증가시키지 않는 하나의 메서드 리턴하는 메서드(글 수정)
		public BoardBean getOneUpdateBoard(int num) {
			getCon();
			BoardBean bean = null;
			
			try {
				//한 게시글에 대한 정보를 리턴해주는 쿼리 작성
				String sql = "select * from board where num=?";	//nunm가 ?인 게시글을 디비에서 가져온다
				pstmt = con.prepareStatement(sql);
				//?대입
				pstmt.setInt(1, num);
				//쿼리실행후 게시글 리턴받는다
				rs = pstmt.executeQuery();
				//게시글을 리턴받았다면
				while(rs.next()) {
					//데이터를 BoardBean클래스로 패키징
					bean = new BoardBean();
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
				}
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return bean;
		}

		
		//하나의 게시글을 수정하는 메서드
		public void updateBoard(int num, String subject, String content) {
			getCon();
			try {
				//쿼리준비
				String sql = "update board set subject=? , content=? where num=?";
							//업데이트해라 board를 set해라 subject를 ?로, content를?로, num가 ?인 게시글을
				//쿼리실행할 객체 선언
				pstmt = con.prepareStatement(sql);
				//?대입
				pstmt.setString(1, subject);
				pstmt.setString(2, content);
				pstmt.setInt(3, num);
				//쿼리실행
				pstmt.executeUpdate();
				
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
		//하나의 게시글을 삭제하는 메서드
		public void deleteBoard(int num) {
			getCon();
			try {
				//쿼리준비
				String sql = "delete from board where num=?";
				pstmt = con.prepareStatement(sql);
				//?대입
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
}