<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<body>

<center>
<h2>게시글 수정</h2>
<form action="BoardUpdateProcCon.do" method="post">
<table width="600" border="1" bgcolor="skyblue">
	<tr height="40">
		<td width="120" align="center">작성자</td>
		<td width="180" align="center">${bean.writer}</td>
		<td width="120" align="center">작성일</td>
		<td width="180" align="center">${bean.reg_date}</td>
	</tr>
	<tr height="40">
		<td width="120" align="center">제목</td>
		<td width="480" align="left" colspan="3">&nbsp; <input type="text" name="subject" value="${bean.subject}" size="60"></td>	
	</tr>
	<tr height="40">
		<td width="120" align="center">패스워드</td>
		<td width="480" align="left" colspan="3">&nbsp; <input type="password" name="password" size="60"></td>	
	</tr>
	<tr height="40">
		<td width="120" align="center">내용</td>
		<td width="480" align="left" colspan="3">&nbsp; <textarea rows="10" cols="60" name="content" align="left">${bean.content}</textarea>	
	</tr>
	
	<tr height="40">
		<td colspan="3" align="center">
		<input type="hidden" name="num" value="${bean.num}">
		<input type="hidden" name="pass" value="${bean.password}">
		<input type="submit" value="글수정">&nbsp;
		<input type="button" onclick="location.href='BoardListCon.do'" value="글목록보기">&nbsp;
		</td>
	</tr>

</table>
</form>
</center>

</body>
</html>