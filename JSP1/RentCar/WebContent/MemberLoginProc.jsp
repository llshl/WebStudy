<%@page import="db.RentcarDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("EUC-KR");

	String id = request.getParameter("id");
	String pass = request.getParameter("pass");
	
	//회원 id와 패스워드가 일치하는지 비교
	RentcarDAO rdao = new RentcarDAO();
	
	//해당 회원이 있는지 여부를 숫자로 표현
	int result = rdao.getMember(id,pass);	//회원이 디비에 저장된 회원인지
	if(result==0){
%>		<script> alert("회원정보가 일치하지 않습니다!");
			location.href='RentcarMain.jsp?center=MemberLogin.jsp';
		</script>
<%
	}else{
		//로그인처리가 됐다면
		session.setAttribute("id",id);	//id를 세션에 저장
		response.sendRedirect("RentcarMain.jsp");
	}
%>

</body>
</html>