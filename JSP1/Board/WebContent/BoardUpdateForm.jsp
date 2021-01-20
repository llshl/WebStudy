<%@page import="model.BoardDAO"%>
<%@page import="model.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<center>
<h2>게시글 수정</h2>
<%
	//어떤 게시글을 수정할것인지 알아야하기에 num(글넘버)를 받아온다.
	int num = Integer.parseInt(request.getParameter("num").trim());

	//하나의 게시글에 대한 정보를 리턴
	BoardDAO bdao= new BoardDAO();
	BoardBean bean = bdao.getOneUpdateBoard(num);
%>
<form action="BoardUpdateProc.jsp" method="post">
<table width="600" border="1" bgcolor="skyblue">
	<tr height="40">
		<td width="120" align="center">작성자</td>
		<td width="180" align="center"><%= bean.getWriter() %></td>
		<td width="120" align="center">작성일</td>
		<td width="180" align="center"><%= bean.getReg_date() %></td>
	</tr>
	<tr height="40">
		<td width="120" align="center">제목</td>
		<td width="480" align="left" colspan="3">&nbsp; <input type="text" name="subject" value="<%= bean.getSubject() %>" size="60"></td>	
	</tr>
	<tr height="40">
		<td width="120" align="center">패스워드</td>
		<td width="480" align="left" colspan="3">&nbsp; <input type="password" name="password" size="60"></td>	
	</tr>
	<tr height="40">
		<td width="120" align="center">내용</td>
		<td width="480" align="left" colspan="3">&nbsp; <textarea rows="10" cols="60" name="content" align="left"><%= bean.getContent() %></textarea>	
	</tr>
	
	<tr height="40">
		<td colspan="3" align="center">
		<input type="hidden" name="num" value="<%= bean.getNum() %>">
		<input type="submit" value="글수정">&nbsp;
		<input type="button" onclick="location.href='BoardList.jsp'" value="글목록보기">&nbsp;
		</td>
	</tr>

</table>
</form>
</center>
</body>
</html>