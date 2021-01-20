<%@page import="model.MemberBean"%>
<%@page import="java.util.Vector"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<!-- 1. 데이터베이스에서 모든 회원의 정보를 가져오기 -->
<!-- 2. table태ㅡ를 이용하여 화면에 회원들의 정보를 출력 -->

<%
	MemberDAO mdao = new MemberDAO();
	//회원들의 정보가 얼마나 들어올지 모르기때문에 가변길이인 Vecrot를 이용하여 데이터를 저장해줌
	//MemberDAO에서 데이터베이스에서 데이터 가지고 오면 이곳에서 그 결과물을 벡터로 받아줌
	Vector<MemberBean> vec = mdao.allSelectMember();
%>
<center>
<h2>모든 회원보기</h2>

<table width="800" border="1">
	<tr height="50">
		<td align=center width="150">아이디</td>
		<td align=center width="250">이메일</td>
		<td align=center width="200">전화번호</td>
		<td align=center width="150">취미</td>
	</tr>
	<%
		for(int i=0;i<vec.size();i++){
			MemberBean bean = vec.get(i);	//빈클래스에 담긴 회원 정보를 여기서 하나씩 빼기
	%>
	<tr height="50">
		<td align=center width="150">
		<a href="MemberInfo.jsp?id=<%= bean.getId() %>">
		<%= bean.getId() %></a></td>
		<td align=center width="250"><%= bean.getEmail() %></td>
		<td align=center width="200"><%= bean.getTel() %></td>
		<td align=center width="150"><%= bean.getHobby() %></td>
	</tr>
	<%
		}
	%>
</table>
</center>
</body>
</html>