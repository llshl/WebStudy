<%@page import="db.RentcarDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	String id = request.getParameter("id");
	String rday = request.getParameter("rday");
	
	RentcarDAO rdao = new RentcarDAO();
	//예약삭제 메서드 호출
	rdao.carRemoveReserve(id,rday);
	
	response.sendRedirect("RentcarMain.jsp");
%>

</body>
</html>