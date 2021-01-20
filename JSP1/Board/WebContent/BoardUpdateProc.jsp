<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	//useBean이나 request로 데이터를 읽어야하는데 4개는 useBean으로 한번에 읽자
	//DB에서 패스워드 받아와서 옳바른 패스워드여야 수정가능하도록
	request.setCharacterEncoding("EUC-KR");
%>
<!-- 사용자 데이터 읽어들이는 Bean클래스 설정 -->
<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*"/>	<!-- *로 안들어오는 없는부분은 null -->
</jsp:useBean>

<%
	//데이터베이스에 연결
	BoardDAO bdao = new BoardDAO();
	//해당 게시글의 패스워드값을 얻어옴
	String pass = bdao.getPass(boardbean.getNum());	//디비에서 패스워드값 받아오기
	
	//기존 패스워드값과(디비에서 가져온거) 업데이트proc에서 넘어온 패스워드값이 같은지 비교
	if(pass.equals(boardbean.getPassword())){
		//같다면 데이터 수정메서드 호출
		bdao.updateBoard(boardbean);
		//수정이 완료되면 전체게시물 보기로 감
		response.sendRedirect("BoardList.jsp");
	}else{	//입력받은 패스워드가 틀리다면
%>
	<script type="text/javascript">
		alert("패스워드가 일치하지 않습니다. 다시 확인 후 시도하세요옷!!!!!!!!!!!!!");
		history.go(-1);
	</script>
<%
	}
%>

</body>
</html>