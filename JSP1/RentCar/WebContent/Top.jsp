<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<!-- ������ �̿��� �α��� ó�� -->
<%
	//�������� id��������, ObjectŸ���̹Ƿ� String������ ĳ����
	String id =(String)session.getAttribute("id");

	//�α����� �Ǿ����� �ʴٸ�
	if(id == null){
		id = "GUEST";
	}
%>
<table width="1000" bordercolor="white">
	<tr height="70">
		<td colspan="4">
			<a href="RentcarMain.jsp" style="text-decoration:none">
				<img alt="" src="img/rent2.jpg" height="65">
			</a>
		</td>
		<td align="center" width="200">
			<%= id %>��
			<%
				if(id.equals("GUEST")){%>
					<button onclick="location.href='RentcarMain.jsp?center=MemberLogin.jsp'">�α���</button>
				<%}else{%>
					<button onclick="location.href='RentcarMain.jsp?center=MemberLogout.jsp'">�α׾ƿ�</button>
				<%}%>
		</td>
	</tr>
	<tr height="50">
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="RentcarMain.jsp?center=CarReserveMain.jsp" style="text-decoration:none">�����ϱ�</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="RentcarMain.jsp?center=CarReserveView.jsp" style="text-decoration:none">����Ȯ��</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="#" style="text-decoration:none">�����Խ���</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="#" style="text-decoration:none">�̺�Ʈ</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="#" style="text-decoration:none">��������</a></font>
		</td>
	</tr>
</table>

</body>
</html>