<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	//Center페이지는 계속 변해야한다.
	//Center값을 변경해주기 위해서 request객체를 이용하여 center값을 받아옴
	String center = request.getParameter("center");

	//처음 SessionMain.jsp를 실행하면 request에서 null값이 넘어오기에 null처리 필요
	if(center== null){
		center = "Center.jsp";
	}
	

%>


<center>
	<table border="1" width="800">
	<!-- Top -->
	<tr height="150">
		<td align="center" colspan="2">
		<jsp:include page="Top.jsp"/>
		</td>
	</tr>
	
	<!-- Left -->
	<tr height="400">
		<td align="center">
		<jsp:include page="Left.jsp"/> 
		</td> 
	
	<!-- Center -->

	<td align="center" width="600">
		<jsp:include page="<%=center%>"/>
		</td>
	</tr>
	
	<!-- Bottom -->
	<tr height="100">
		<td align="center"colspan="2">
		<jsp:include page="Bottom.jsp"/>
		</td>
	</tr>
	
	</table>
</center>

</body>
</html>