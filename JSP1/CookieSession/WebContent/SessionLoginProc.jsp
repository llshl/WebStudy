<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<center>
	<h2>세션 로그인 처리1</h2>
<%
	request.setCharacterEncoding("EUC-KR");

	//사용자 데이터 받기
	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	//세션으로 아이디와 패스워드 저장
	session.setAttribute("id",id);
	session.setAttribute("pass",pass);
	
	//세션 유지시간 설정
	session.setMaxInactiveInterval(60*2);	//60초동안 세션 유지
	
	response.sendRedirect("SessionMain.jsp");
%>
	
	<!-- 이러헤 id를 넘겨줄 수도 있지만 주소창에 다 노출되므로 좋지 않다 --> 
	<!--<a href="SessionLoginProc2.jsp?id=<%=id%>">다음페이지로 이동</a>                        -->
	
	<!-- 이렇게 세션을 쓰는것이 좋다 -->
	<!--<a href="SessionLoginProc2.jsp">다음페이지로 이동</a>		--> 
	
	
	
</center>
</body>
</html>