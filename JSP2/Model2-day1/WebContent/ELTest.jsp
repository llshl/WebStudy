<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<%
	int i=3;
	out.println("i= "+i);
	request.setAttribute("i",4);
%>
<p>
i= <%= i %>
<p>
i = ${i < 2 }

</body>
</html>