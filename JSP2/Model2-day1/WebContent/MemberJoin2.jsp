<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<h2>회원가입</h2>
<form action="MProc2" method="post">	<!--MProc2는 url매핑이름, 서블릿 클래스 파일명이 아님!! -->
<table width="400" border="1" bordercolor="1">
	<tr height="40">
		<td width="150" align="center">아이디</td>
		<td width="250" align="center"><input type="text" name="id"> </td>
	</tr>
	<tr height="40">
		<td width="150" align="center">패스워드</td>
		<td width="250" align="center"><input type="password" name="password"> </td>
	</tr>
	<tr height="40">
		<td width="150" align="center">이메일</td>
		<td width="250" align="center"><input type="Email" name="email"> </td>
	</tr>
	<tr height="40">
		<td width="150" align="center">전화번호</td>
		<td width="250" align="center"><input type="Tel" name="tel"> </td>
	</tr>
	<tr height="40">
		<td width="150" align="center">주소</td>
		<td width="250" align="center"><input type="text" name="address"> </td>
	</tr>
	<tr height="40">
		<td colspan="2" align="center"><input type="submit" value="회원가입"> </td>
	</tr>

</table>
</form>

</body>
</html>