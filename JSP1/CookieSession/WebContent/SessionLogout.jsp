<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	//로그아웃은 즉 세션의 유지시간을 종료시키는것
	session.setAttribute("id",null);	//id에 null넣은것
	session.setMaxInactiveInterval(0);	//세션 유지시간을 죽이는것
	
	response.sendRedirect("SessionMain.jsp");
%>

</body>
</html>