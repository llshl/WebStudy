<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<!-- 세션을 이용한 로그인 처리 -->
<%
	//세션으로 id가져오기, Object타입이므로 String형으로 캐스팅
	String id =(String)session.getAttribute("id");

	//로그인이 되어있지 않다면
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
			<%= id %>님
			<%
				if(id.equals("GUEST")){%>
					<button onclick="location.href='RentcarMain.jsp?center=MemberLogin.jsp'">로그인</button>
				<%}else{%>
					<button onclick="location.href='RentcarMain.jsp?center=MemberLogout.jsp'">로그아웃</button>
				<%}%>
		</td>
	</tr>
	<tr height="50">
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="RentcarMain.jsp?center=CarReserveMain.jsp" style="text-decoration:none">예약하기</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="RentcarMain.jsp?center=CarReserveView.jsp" style="text-decoration:none">예약확인</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="#" style="text-decoration:none">자유게시판</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="#" style="text-decoration:none">이벤트</a></font>
		</td>
		<td align="center" width="200" bgcolor="pink">
			<font color="white" size="5"><a href="#" style="text-decoration:none">고객센터</a></font>
		</td>
	</tr>
</table>

</body>
</html>