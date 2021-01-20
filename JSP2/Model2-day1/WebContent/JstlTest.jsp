<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<!-- 변수선언 -->
<c:set var="sum" value="0"/>

<c:if test="${i>3}">
	안녕하세요 
</c:if>


<!-- 반복문 -->
<c:forEach var="i" begin="1" end="10" >
	<c:set var="sum" value="${sum = sum+i }"/>
	${sum }
</c:forEach>

</body>
</html>