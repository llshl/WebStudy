package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RentcarDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	//커넥션풀을 이용한 데이터베이스 연결
	public void getCon() {
		try {
			//외부 서버로부터 데이터 읽어들일때 커넥션풀의 jdbc/pool이게 없을수도 있으므로 예외처리(NamingException)
			Context initctx = new InitialContext();	
			//lookup메서드를 사용해서 java:comp/env를 찾고 그게 Object형이니 Context로 캐스팅
			Context envctx = (Context) initctx.lookup("java:comp/env");
			//DataSource이름인 jdbc/pool을 찾고 캐스팅
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();	//con객체생성
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//최신순 3대의 자동차를 리턴하는 메서드
	public Vector<CarListBean> getSelectCar(){
		Vector<CarListBean> v = new Vector<>();
		try {
			getCon();
			String sql = "select * from rentcar order by no desc";	//no를 기준으로 내림차순해서 다 가져오기
			pstmt = con.prepareStatement(sql);
			//쿼리실행 후 결과를 Result타입으로 변환
			rs = pstmt.executeQuery();
			int count = 0;
			while(rs.next()) {	//끝까지 돌리기
				CarListBean bean = new CarListBean();
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
				//벡터에 빈클래스 저장
				v.add(bean);
				count++;
				if(count > 2) {	//3번반복시 반복문 탈출
					break;	//3개만 벡터에 저장
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	//카테고리별 자동차 리스트를 저장하는 메서드
	public Vector<CarListBean> getCategoryCar(int cate){
		Vector<CarListBean> v = new Vector<>();
		//데이터를 저장할 빈클래스 선언
		CarListBean bean = null;
		getCon();
		
		try {
			String sql = "select * from rentcar where category=?";
			pstmt = con.prepareStatement(sql);
			//?값 매핑
			pstmt.setInt(1, cate);
			//결과리턴
			rs = pstmt.executeQuery();
			//반복문을 통해 데이터를 추출(저장)
			while(rs.next()) {	//끝까지 돌리기
				//데이터를 저장할 bean클래스 생성
				bean = new CarListBean();
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
				//벡터에 빈클래스 저장
				v.add(bean);
				//차 한대마다 bean한개씩이므로 반복문돌며 where조건에 맞는 차들이 순서대로 bean으로 벡터에 저장됨
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//모든 차량을 검색하는 메서드
	public Vector<CarListBean> getAllCar(){
		
		Vector<CarListBean> v = new Vector<>();
		//데이터를 저장할 빈클래스 선언
		CarListBean bean = null;
		getCon();
		
		try {
			String sql = "select * from rentcar";
			pstmt = con.prepareStatement(sql);
			//결과리턴
			rs = pstmt.executeQuery();
			//반복문을 통해 데이터를 추출(저장)
			while(rs.next()) {	//끝까지 돌리기
				//데이터를 저장할 bean클래스 생성
				bean = new CarListBean();
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
				//벡터에 빈클래스 저장
				v.add(bean);
				//차 한대마다 bean한개씩이므로 반복문돌며 where조건에 맞는 차들이 순서대로 bean으로 벡터에 저장됨
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//선택한 번호(no)에 해당하는 자동차 한대의 정보를 리턴하는 메서드
	public CarListBean getOneCar(int no) {
		//리턴타입객체
		CarListBean bean = new CarListBean();
		getCon();
		
		try {
			String sql = "select * from rentcar where no=?";
			pstmt = con.prepareStatement(sql);
			//?매핑
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {	//쿼리가 잘 실행됐다면
				bean.setNo(rs.getInt(1));
				bean.setName(rs.getString(2));
				bean.setCategory(rs.getInt(3));
				bean.setPrice(rs.getInt(4));
				bean.setUserpeople(rs.getInt(5));
				bean.setCompany(rs.getString(6));
				bean.setImage(rs.getString(7));
				bean.setInfo(rs.getString(8));
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;	//빈 한개 반환
	}

	
	//회원정보가 존재하는 회원인지 확인
	public int getMember(String id, String pass) {
		int result = 0;	//0이면 회원등록안도있음
		getCon();
		try {
			String sql = "select count(*) from member where id=? and pass1=?";	
						//*(모든것)을 카운트하라. id가 ? 이고 패스워드가 ?인 애들중에
						//1이 나오면 회원이 존재, 0이면 존재x
						//1은 해당 id와 패스워드를 가진 회원이 1명이기에 1명 반환
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			//결과리턴
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);	// 1또는 0이 저장됨
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//하나의 예약정보를 저장하는 메서드
	public void setReserveCar(CarReserveBean bean) {
		getCon();
		try {
			String sql = "insert into carreserve values(reserve_seq.NEXTVAL,?,?,?,?,?,?,?,?,?)";
														//시퀀스이름이 reserve_seq이고 NEXTVAL은 정수 1씩 증가
			pstmt = con.prepareStatement(sql);
			//?매핑
			pstmt.setInt(1, bean.getNo());
			pstmt.setString(2, bean.getId());
			pstmt.setInt(3, bean.getQty());
			pstmt.setInt(4, bean.getDday());
			pstmt.setString(5, bean.getRday());
			pstmt.setInt(6, bean.getUsein());
			pstmt.setInt(7, bean.getUsewifi());
			pstmt.setInt(8, bean.getUseseat());
			pstmt.setInt(9, bean.getUsenavi());
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//회원의 예약정보를 리턴하늠 메서드
	public Vector<CarViewBean> getAllReserve(String id){
		Vector<CarViewBean> v = new Vector<>();
		CarViewBean bean = null;
		getCon();
		try {
			String sql = "select * from rentcar natural join carreserve where sysdate < to_date(rday,'YYYY-MM-DD') and id=?";
			pstmt = con.prepareStatement(sql);
			//?매핑
			pstmt.setString(1, id);
			//결과리턴
			rs = pstmt.executeQuery();	//리턴 안받는거는 executeUpdate()
			//한사람이 여러개의 예약을 할수있기에 
			while(rs.next()) {
				bean = new CarViewBean();
				bean.setName(rs.getString(2));
				bean.setPrice(rs.getInt(4));
				bean.setImg(rs.getString(7));
				bean.setQty(rs.getInt(11));
				bean.setDday(rs.getInt(12));
				bean.setRday(rs.getString(13));
				bean.setUsein(rs.getInt(14));
				bean.setUsewifi(rs.getInt(15));
				bean.setUseseat(rs.getInt(16));
				bean.setUsenavi(rs.getInt(17));
				//빈클래스를 벡터에 저장
				v.add(bean);
			}
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return v;
	}
	
	
	//하나의 예약 삭제
	public void carRemoveReserve(String id,  String rday) {
		getCon();
		try {
			String sql = "delete from carreserve where id=? and rday=?";
						//carreserve테이블에서 id가? 이고 rday도 ?인 애를 삭제
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, rday);
			pstmt.executeUpdate();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
