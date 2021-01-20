<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- 데이터를 한번에 받아오는 bean클래스 사용 -->
<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*"/>	<!-- 답글달기위해 부모글의 정보들인 num, ref,re_step,re_level 받음 -->
</jsp:useBean>

<%
	//데이터베이스 객체 생성
	BoardDAO bdao = new BoardDAO();
	bdao.reWriteBoard(boardbean);	//DAO에 메서드 생성
	
	//답변글 데이터를 모두 저장 후 전체 게시글 보기
	response.sendRedirect("BoardList.jsp");
%>
</body>
</html>