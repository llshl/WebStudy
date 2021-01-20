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
	//센터는 계속 바뀌니까 request로 받는다
	String center = request.getParameter("center");

	//최초접속시 초기 센터설정
	if(center == null){
		center = "Center.jsp";	//Default센터값 부여
	}
%>



<table width="1000">
	<!-- Top부분 -->
	<tr height="140" align="center">
		<td align="center"> <jsp:include page="Top.jsp" /> </td>
	</tr>
	
	<!-- Center부분 -->
	<tr height="500" align="center">
		<td align="center"> <jsp:include page="<%= center %>" /> </td>
	</tr>
	
	<!-- Bottom부분 -->
	<tr height="100" align="center">
		<td align="center"> <jsp:include page="Bottom.jsp" /> </td>
	</tr>
</table>

</body>
</html>