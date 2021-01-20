<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	request.setCharacterEncoding("EUC-KR");

	
	//아이디 읽어오기
	String id = request.getParameter("id");
	//아이디저장 체크박스가 체크되었는지 확인
	String save = request.getParameter("save");
	
	//체크됐는지 판단
	if(save!=null){
		//쿠키를 사용하려면 쿠키클래스를 생성해야함
		//위에서 받은 id를 value로 넣음
		Cookie cookie = new Cookie("id",id);	//스트링형의 (Key, Value) 한쌍
		
		//쿠키 유효시간 설정
		cookie.setMaxAge(60*3);	//10분간 유효(초단위)
		
		//사용자측에 쿠키 넘겨주기
		response.addCookie(cookie);
		out.write("쿠키생성 완료");
	}

	
%>
</body>
</html>