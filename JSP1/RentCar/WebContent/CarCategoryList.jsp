<%@page import="db.CarListBean"%>
<%@page import="java.util.Vector"%>
<%@page import="db.RentcarDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	//카테고리 분류 값을 받아와야한다.
	int category = Integer.parseInt(request.getParameter("category"));
	String temp = "";
	if(category==1){
		temp = "소형";
	}
	else if(category==2){
		temp = "중형";
	}
	else if(category==3){
		temp = "대형";
	}
%>
<!-- center -->
<table width="1000">
	<tr height="60">
		<td align="center"colspan="3">
		<font size="6" color="gray"> <%=temp %> 자동차</font>
		</td>
	</tr>
<%
	RentcarDAO rdao = new RentcarDAO();
	Vector<CarListBean> v = rdao.getCategoryCar(category);	//받아와질 데이터가 몇개인지 모르니까 벡터로받는다
	//tr을 3개씩 보여주고 다시 tr을 실행할 수 있도록하는 변수 선언
	int j=0;
	//화면에 차량이 한줄에 3개 나오고 다음줄에 또 3개나오고 이런식으로 보여야한다.
	for(int i=0;i<v.size();i++){
		//벡터에 저장돼있는 bean클래스를 추출
		CarListBean bean = v.get(i);	//bean형 벡터로 넘어온 것들을 다시 bean으로 꺼내기
		if(j%3==0){
%>
		<tr height="220">

		<%}%>
		<td width="333" align="center">
		<a href="RentcarMain.jsp?center=CarReserveInfo.jsp?no=<%= bean.getNo() %>">
			<img alt="" src="img/<%= bean.getImage() %>" width="300" height="200">
		</a>
		<p>
		<font size="3" color="gray"><b>차량명 : <%= bean.getName() %><br></b></font>
		<font size="3" color="gray"><b>금액 : <%= bean.getPrice() %></b></font></p></td>
<%		j=j+1;	//j값을 증가하여 하나의 행에 총 3개의 차량정보를 보여주기 위하여
	}
%>


</table>
<!-- center -->
</body>
</html>