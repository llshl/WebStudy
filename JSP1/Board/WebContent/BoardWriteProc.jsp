<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>

<%
	request.setCharacterEncoding("EUC-KR");
%>
<!-- �Խñ� �ۼ��� �����͸� �ѹ��� �о�帲 -->
<jsp:useBean id="boardbean" class="model.BoardBean">
	<jsp:setProperty name="boardbean" property="*"/>
</jsp:useBean>

<%
	//�����ͺ��̽��� beanŬ������ �Ѱ��ָ� �ȴ�
	BoardDAO bdao = new BoardDAO();

	//������ ���� �޼ҵ� ȣ��
	bdao.insertBoard(boardbean);
	
	//���� �� ��ü �Խñ� ����
	response.sendRedirect("BoardList.jsp");
	
%>
</body>
</html>