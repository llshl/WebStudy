<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- 게시글 작성한 데이터를 한번에 읽어드림 -->
<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*"/>
</jsp:useBean>

<%
	//데이터베이스로 bean클래스를 넘겨주면 된다
	BoardDAO bdao = new BoardDAO();

	//데이터 저장 메소드 호출
	bdao.insertBoard(boardbean);
	
	//저장 후 전체 게시글 보기
	response.sendRedirect("BoardList.jsp");
	
%>
</body>
</html>