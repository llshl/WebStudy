<%@page import="model.MemberBean"%>
<%@page import="model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<!-- 0. memberlist���� �ѱ� id�� �޾��ش� -->
<!-- 1. �����ͺ��̽����� �Ѹ��� ȸ���� ������ �������� -->
<!-- 2. table�¤Ѹ� �̿��Ͽ� ȭ�鿡 ȸ���� ������ ��� -->
<%
	String id = request.getParameter("id");	//MemberList���� get������� ���� id�� �޾���
	MemberDAO mdao = new MemberDAO();
	MemberBean mbean = mdao.oneSelectMember(id);	//�޼��� ȣ�� �� ���ڷ� id ������
%>
	<h2>ȸ�� ���� ����</h2>
<center>
	<table width="400" border="1">
	<tr height="50">
		<td align="center" width="150">���̵�</td>
		<td width="250"><%= mbean.getId() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">�̸���</td>
		<td width="250"><%= mbean.getEmail() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">��ȭ��ȣ</td>
		<td width="250"><%= mbean.getTel() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">���</td>
		<td width="250"><%= mbean.getHobby() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">����</td>
		<td width="250"><%= mbean.getJob() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">����</td>
		<td width="250"><%= mbean.getAge() %></td>
	</tr>
	<tr height="50">
		<td align="center" width="150">����</td>
		<td width="250"><%= mbean.getInfo() %></td>
	</tr>
	
	<tr height="50">
		<td align="center" colspan="2">
		<button onclick="location.href='MemberUpdateForm.jsp?id=<%=mbean.getId()%>'"> ȸ������ </button>
		<button onclick="location.href='MemberDeleteForm.jsp?id=<%=mbean.getId()%>'"> ȸ������ </button>
		<button onclick="location.href='MemberList.jsp'"> ��Ϻ��� </button>
		<button onclick="location.href='MemberJoin.jsp'"> ȸ������ </button>
		</td>
	</tr>
	
	
	
	</table>
</center>
</body>
</html>