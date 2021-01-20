<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%

	//사용자pc의 쿠키저장소로부터 쿠키값을 읽어들임
	//쿠키가 몇개있을지 모르기에 배열을 이용하여 저장
	Cookie[] cookies = request.getCookies();	//해당하는 사용자의 쿠키값을 읽어들여서 배열에 저장
	
	String id = "";
	
	//첫 로그인일때는 쿠키값이 없기에 없을 경우 if문
	if(cookies != null){
		for(int i=0;i<cookies.length;i++){
			if(cookies[i].getName().equals("id")){
				id = cookies[i].getValue();
				break;	//우리가 원하는 데이터를 찾았기에 반복문 탈출
			}
		}
	}

%>

<center>
<h2>쿠키 로그인</h2>
<form action="CookieLoginProc.jsp" method="post">
<table width="400" border="1">
	
	<tr height="50">
		<td width="150">아이디</td>
		<td width="250"><input type="text" name="id" value="<%=id%>" %></td>
	</tr>
	<tr height="50">
		<td width="150">패스워드</td>
		<td width="250"><input type="password" name="pass"></td>
	</tr>
	<tr height="50">
		<td colspan="2" align="center"><input type="checkbox" name="save" value="1">아이디 저장</td>
	</tr>
	<tr height="50">
		<td colspan="2" align="center"><input type="submit" value="로그인"></td>
	</tr>
</table>
</form>
</center>

</body>
</html>