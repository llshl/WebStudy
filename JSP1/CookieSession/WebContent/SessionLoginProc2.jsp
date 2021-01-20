<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>


<center>
	<h2>세션 로그인 처리2</h2>
<%
	request.setCharacterEncoding("EUC-KR");
//앞 페이지에서 세션처리를 했으므로 request로 받을 필요 없다
//	String id = request.getParameter("id");
//	String pass = request.getParameter("pass");

	String id = (String)session.getAttribute("id");	//Object형으로 받기때문에 String형으로 캐스팅필요
	String pass = (String)session.getAttribute("pass");
%>
	<!-- 이건 세션으로 유지된 id임 -->
	<h2>당신의 아이디는 <%= id %>입니다</h2>
	
</center>

</body>
</html>