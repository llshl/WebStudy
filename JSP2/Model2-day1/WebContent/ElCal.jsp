<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>

<h2>계산기</h2>
<form action="ElCal.jsp" method="post">
	<table width="450">
		<tr height="40">
			<td align="center" width="100"><input type="text" name="exp1" value="${param.exp1}"></td>
			<td align="center" width="50"><select name="exp2">
										<option value="+"> + </option>
										<option value="-"> - </option>
										<option value="*"> * </option>
										<option value="/"> / </option>
										</select></td>
			<td align="center" width="100"><input type="text" name="exp3" value="${param.exp3}"></td>
			<td align="center" width="20">=</td>
			<td align="center" width="100">
			
<%
			String exp2 = request.getParameter("exp2");
			//null처리
			if(exp2==null){
				exp2="+";
			}
			if(exp2.equals("+")){
%>
				<input type="text" name="exp4" value="${param.exp1 + param.exp3}">
<%		
			}
			else if(exp2.equals("-")){
%>
				<input type="text" name="exp4" value="${param.exp1 - param.exp3}">
<%		
			}
			else if(exp2.equals("*")){
%>
				<input type="text" name="exp4" value="${param.exp1 * param.exp3}">
<%		
			}
			else if(exp2.equals("/")){
%>
				<input type="text" name="exp4" value="${param.exp1 / param.exp3}">
<%		
			}
%>
			
			<td align="center" width="100"><input type="submit" value="결과보기"></td>
		</tr>
	</table>
</form>

</body>
</html>