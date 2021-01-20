<%@page import="db.CarListBean"%>
<%@page import="db.RentcarDAO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("EUC-KR");
%>
<jsp:useBean id="rbean" class="db.CarReserveBean">
	<jsp:setProperty name="rbean" property="*"/>
</jsp:useBean>

<%
	String id = (String)session.getAttribute("id");
	if(id==null){
%>	<script> alert("로그인 후 사용하세요!");
		location.href='RentcarMain.jsp?center=MemberLogin.jsp';
	</script>
<%
	}
	
	//날짜비교
	Date d1 = new Date();
	Date d2 = new Date();
	//날짜를 2020-1-7 포멧해주는 클래스 선언
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	d1 = sdf.parse(rbean.getRday());//yyyy-MM-dd형태 그대로 받을수있음, rday는 String형식
	d2 = sdf.parse(sdf.format(d2));	//format함수로 인해 yyyy-MM-dd형태로 바뀜
	
	//날짜비교메서드 사용
	int compare = d1.compareTo(d2);
	//compare 함수는
	//예약하려는 날짜보다 현재날짜가 크다면 -1반환
	//예약하려는 날짜와 현재날짜가 같다면 0
	//예약하려는 날짜가 현재날짜보다 크다면 1	(크다는것은 더 뒷날)
	
	if(compare < 0){	//오늘보다 이전날짜라면
%>	<script> alert("오늘 날짜보다 이전 날짜는 선택할 수 없음."); history.go(-1);</script>
<%}

	//결과적으로 아무 문제 없다면 데이터 저장후 결과 페이지 보여주기
	//id값이 빈클래스에 없기에
	String id1 = (String)session.getAttribute("id");
	rbean.setId(id1);

	//데이터베이스에 빈클래스 저장
	RentcarDAO rdao = new RentcarDAO();
	rdao.setReserveCar(rbean);
	
	//차량정보 얻어오기
	CarListBean cbean = rdao.getOneCar(rbean.getNo());
	
	//차량 총 금액
	int totalcar = cbean.getPrice()*rbean.getQty()*rbean.getDday();
	//옵션금액
	int usein = 0;
	if(rbean.getUsein()==1){
		usein = 10000;
	}
	int usewifi = 0;
	if(rbean.getUsewifi()==1){
		usewifi = 10000;
	}
	int useseat = 0;
	if(rbean.getUseseat()==1){
		useseat = 10000;
	}
	int totaloption = (rbean.getQty()*rbean.getDday())*(usein+usewifi+useseat);
%>
<!-- center -->
<table width="1000">
		<tr height="100">
			<td align="center">
			<font size="6" color="gray">차량예약 완료화면</font>
			</td>
		</tr>
		<tr>
			<td align="center">
			<img alt="" src="img/<%=cbean.getImage()%>" width="470">
			</td>
		</tr>
		<tr height="50">
			<td align="center">
			<font size="5" color="red">차량 금액 <%=totalcar%>원</font>
			</td>
		</tr>
		<tr height="50">
			<td align="center">
			<font size="5" color="red">옵션 금액 <%=totaloption %>원 </font>
			</td>
		</tr>
		<tr height="50">
			<td align="center">
			<font size="5" color="red">총 금액 <%=totaloption+totalcar%>원 </font>
			</td>
		</tr>
</table>
		
<!-- center -->

</body>
</html>