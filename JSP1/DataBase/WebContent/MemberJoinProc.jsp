<%@page import="model.MemberDAO"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("EUC-KR");
	//취미 부분은 별도로 읽어들여서 다시 빈클래스에 저장
	String[] hobby = request.getParameterValues("hobby");
	//배열에 있는 내용을 하나의 스트링으로 저장
	String texthobby = "";
	for(int i=0;i<hobby.length;i++){
		texthobby += hobby[i]+" ";
	}
%>
	<!-- useBean 이용해서 한꺼번에 데이터를 받아오기 -->
	<jsp:useBean id="mbean" class="model.MemberBean">
		<jsp:setProperty name="mbean" property="*" />	<!-- 매핑시키기 -->
	</jsp:useBean> 
	
<%
	mbean.setHobby(texthobby);	//취미에는 주소가 저장되기에 MemberBean클래스의 멤버메소드인 setHobby로 다시 지정
	
	//데이터베이스 클래스 객체 생성 
	MemberDAO mdao = new MemberDAO();
	mdao.insertMember(mbean);
	
	//회원가입이 되었다면 회원정보를 보여주는 페이지로 이동시김
	response.sendRedirect("MemberList.jsp");
%>
	오라클 제발 완료~
</body>
</html>