<%@page import="model.MemberBean"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<!-- 0. memberlist에서 넘긴 id를 받아준다 -->
<!-- 1. 데이터베이스에서 한명의 회원의 정보를 가져오기 -->
<!-- 2. table태ㅡ를 이용하여 화면에 회원의 정보를 출력 -->
<%
	String id = request.getParameter("id");	//MemberList에서 get방식으로 보낸 id를 받아줌
	MemberDAO mdao = new MemberDAO();
	MemberBean mbean = mdao.oneSelectMember(id);	//메서드 호출 시 인자로 id 보내줌
%>
	<h2>회원 정보 보기</h2>
<center>
	<table width="400" border="1">
	<tr height="50">
		<td align="center" width="150">아이디</td>
		<td width="250"><%= mbean.getId() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">이메일</td>
		<td width="250"><%= mbean.getEmail() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">전화번호</td>
		<td width="250"><%= mbean.getTel() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">취미</td>
		<td width="250"><%= mbean.getHobby() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">직업</td>
		<td width="250"><%= mbean.getJob() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">나이</td>
		<td width="250"><%= mbean.getAge() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">정보</td>
		<td width="250"><%= mbean.getInfo() %></td>
	</tr>
	
	<tr height="50">
		<td align="center" colspan="2">
		<button onclick="location.href='MemberUpdateForm.jsp?id=<%=mbean.getId()%>'"> 회원수정 </button>
		<button onclick="location.href='MemberDeleteForm.jsp?id=<%=mbean.getId()%>'"> 회원삭제 </button>
		<button onclick="location.href='MemberList.jsp'"> 목록보기 </button>
		<button onclick="location.href='MemberJoin.jsp'"> 회원가입 </button>
		</td>
	</tr>
	
	
	
	</table>
</center>
</body>
</html>