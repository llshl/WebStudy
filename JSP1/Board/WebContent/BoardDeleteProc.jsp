<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	String pass = request.getParameter("password");		//Delete폼에서 사용자가 입력한 비밀번호
	int num = Integer.parseInt(request.getParameter("num"));	//히든으로 넘어온건가?
			
	//데이터베이스 연결
	BoardDAO bdao = new BoardDAO();
	String password = bdao.getPass(num);	//디비에 저장돼있는 올바른 패스워드
	
	//입력받은 패스워드와 디비에서 가져온 패스워드를 비교
	if(pass.equals(password)){		//같다면
		bdao.deleteBoard(num);			//삭제진행
		response.sendRedirect("BoardList.jsp");	//목록으로 이동
	}
	else{	//다르다면
	
%>
	<script>
		alert("패스워드가 틀립니다. 다시 확인해주세요.");
		history.go(-1);
	</script>
<%
	}
%>

</body>
</html>