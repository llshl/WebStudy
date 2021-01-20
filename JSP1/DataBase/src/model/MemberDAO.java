package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

//오라클 데이터베이스에 연결하고 select 작업, insert작업 update작업 delete작업을 실행해 주는 클래스입니다.
//꼭 오라클이 아니어도 가능. 다른 데이터베이스도 이런 형식을 따른다
public class MemberDAO {
	//오라클에 접속하는 소스 작성
	/*
	 * String id = "system"; //접속 아이디 String pass = "1882"; String url =
	 * "jdbc:oracle:thin:@localhost:1521:XE"; //접속 url
	 */
	Connection con;	//데이터베이스에 접근할 수 있도록 설정
	PreparedStatement pstmt;	//데이터베이스에서 쿼리를 실행시켜주는 객체
	ResultSet rs;	//데이터베이스의 테이블의 선택 결과를 리턴받아 자바에 저장해주는 객체 
		
	//데이터베이스에 접근할 수 있도록 도와주는 메소드. 이거는 반복적으로 사용할것이기에 메소드로 생성
	public void getCon() {
		
		//커넥션풀을 이용하여 데이터베이스에 접근(server.xml에 미리 명시해둠)
		try {
			Context initctx = new InitialContext(); 
			//톰캣서버에 정보를 담아놓은 곳으로 이동
			Context envctx = (Context) initctx.lookup("java:comp/env");
			//데이터소스 객체를 선언
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			//데이터 소스를 기준으로 커넥션을 연결해주시오
			con = ds.getConnection();
		}catch(Exception e) {
			
		}
		
		
//		
//		try {
//			//1. 해당 데이터베이스를 사용한다고 선언(클래스를 등록=오라클용을 사용)
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			//2. 해당 데이터베이스에 접속(오라클에 접속)
//			con = DriverManager.getConnection(url,id,pass);
//		}catch(Exception e) {
//			
//		}
	}
	
	//데이터베이스에 한사람의 회원 정보를 저장해주는 메소드
	public void insertMember(MemberBean mbean) {	//mbean객체를 인자로 받아서 밑에잇는 getId와 같은 메소드로 데이터 추출
		try{
//			//1. 해당 데이터베이스를 사용한다고 선언(클래스를 등록=오라클용을 사용)
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			//2. 해당 데이터베이스에 접속(오라클에 접속)
//			Connection con = DriverManager.getConnection(url,id,pass);
			//1번 2번대신 getCon메소드 호출
			getCon();
			//3. 접속 후 쿼리 준비
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			//쿼리를 사용하도록 설정
			PreparedStatement pstmt = con.prepareStatement(sql);	//PreparedStatement객체란 jsp에서 쿼리를 사용하도록 설정하는것
			//?에 맞게 데이터를 매핑
			pstmt.setString(1, mbean.getId());		//이 8개들은 MemberBean의 getter메소드들 이것을 (?,?,?,?,?,?,?,?)여기에 하나씩 넣은것
			pstmt.setString(2, mbean.getPass1());
			pstmt.setString(3, mbean.getEmail());
			pstmt.setString(4, mbean.getTel());
			pstmt.setString(5, mbean.getHobby());
			pstmt.setString(6, mbean.getJob());
			pstmt.setString(7, mbean.getAge());
			pstmt.setString(8, mbean.getInfo());
			pstmt.executeUpdate();	//insert, update, delete 시 사용하는 메서드
			//5. 자원 반납
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//모든 회원의 정보를 리턴해 주는 메소드 호출
	public Vector<MemberBean> allSelectMember(){
		//데이터베이스의 정보를 가변길이 자료형인 벡터로 저장하기
		Vector<MemberBean> v = new Vector<>();
		
		//데이터베이스는 항상 예외처리
		try {
			//오라클과 연결하기 메소드 호출
			getCon();	
			//쿼리준비
			String sql = "select * from member";
			//쿼리를 실행시켜주는 객체 선언
			pstmt = con.prepareStatement(sql);	//이렇게하면 prepareStatement가 오라클에서 쿼리를 실행 가능
			//쿼리를 실행시킨 결과를 리턴해서 받아줌(오라클 테이블에 검색되 결과를 자바 객체에 저장)
			rs = pstmt.executeQuery();
			//반복문을 사용해서 rs(ResultSet)에 저장된 데이터를 추출해놓아야함
			
			while(rs.next()) {	//저장된 데이터만큼만 반복
				MemberBean bean = new MemberBean();	//컬럼으로 나뉘어진 데이터를 빈클래스에 저장
				bean.setId(rs.getString(1));		//컬럼인덱스가 1부터 시작한다
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
				
				//패키징된 MemberBean클래스를 벡터에 저장
				v.add(bean);
			}
			//자원 반남
			con.close();
		}catch(Exception e) {
			//e.printStackTrace();
		}
		//다 저장된 벡터를 리턴
		return v;
	}
	
	
	//한 사람에 대한 정보를 리턴하는 메소드 작성
	public MemberBean oneSelectMember(String id) {
		//한 사람에 대한 정보만 리턴하니까 bean객체 생성(벡터 쓸 필요 없다)
		MemberBean bean = new MemberBean();
		
		try {
			//오라클과 연결하기
			getCon();
			//한 사람만 가져오는 쿼리작성, 인자로 넘어온 id에 맞는 회원찾기
			String sql = "select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			//?에 값을 매핑
			pstmt.setString(1,id);	//첫번째 물음표에 대응, 즉 id=?의 물음표에 대응
			//쿼리실행
			rs = pstmt.executeQuery();	//검색결과가 rs에 저장됨
			
			if(rs.next()) {	//검색된 결과(레코드)가 있다면
				bean.setId(rs.getString(1));		//그 id에 해당하는 멤버의 정보를 보두 bean에 넣기
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
			}
			//자원 반납
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	
	//한 회원의 패스워드 값을 리턴하는 메소드
	public String getPass(String id) {
		//String으로 리턴을 해야하기에 String변수 선언
		String pass = "";
		try {
			getCon();
			String sql = "select pass1 from member where id=?";	//인자로 받은 id에 해당되는 퍄스워드만 받아오면 되니까
			pstmt = con.prepareStatement(sql);
			
			//?에 값을 매핑
			pstmt.setString(1,id);	//첫번째 물음표에 대응 즉 id=?의 물음표
			//쿼리실행
			rs = pstmt.executeQuery();	//검색결과가 rs에 저장됨
			
			if(rs.next()) {
				pass = rs.getString(1);	//1번이 패스워드값이 저장된 컬럼 인덱스
			}
			//자원반납
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return pass;
	}
	
	//한 회원의 정보를 수정하는 메소드
	public void updateMember(MemberBean bean) {
		try {
			getCon();
			String sql = "update member set email=?,tel=? where id=?";	//id가 ?일때 이메일과 전화번호를 수정
			//쿼리 실행 객체 선언
			pstmt = con.prepareStatement(sql);
			
			//?에 값을 매핑
			pstmt.setString(1,bean.getEmail());	//첫번째 물음표에 대응
			pstmt.setString(2,bean.getTel());	//두번째 물음표
			pstmt.setString(3,bean.getId());	//세번재 물음표
			
			//쿼리실행
			pstmt.executeUpdate();	//excuteQuery가 아닌 업데이트다, 반환값은 없음
		
			//자원반납
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//한 회원을 삭제하는 메소드
	public void deleteMember(String id) { 
		try {
			getCon();
			String sql = "delete from member where id=?";	//id가 ?인 회원의 정보 삭제
			//쿼리 실행 객체 선언
			pstmt = con.prepareStatement(sql);
			
			//?에 값을 매핑
			pstmt.setString(1,id);	//첫번째 물음표에 대응
			
			//쿼리실행
			pstmt.executeUpdate();	//excuteQuery가 아닌 업데이트다, 반환값은 없음
		
			//자원반납
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
