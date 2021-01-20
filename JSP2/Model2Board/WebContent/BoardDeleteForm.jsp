<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>


<center>
<h2>게시글 삭제</h2>
<form action="BoardUDeleteProcCon.do" method="post">
<table width="600" border="1" bgcolor="skyblue">
	
	<tr height="40">
		<td width="120" align="center">패스워드</td>
		<td width="480" align="left" colspan="3">&nbsp; <input type="password" name="password" size="60"></td>	
	</tr>
	
	<tr height="40">
		<td colspan="3" align="center">
		<input type="hidden" name="num" value="${bean.num}">
		<input type="hidden" name="pass" value="${bean.password}">
		
		<input type="submit" value="글삭제">&nbsp;
		<input type="button" onclick="location.href='BoardListCon.do'" value="글목록보기">&nbsp;
		</td>
	</tr>

</table>
</form>
</center>



</body>
</html>