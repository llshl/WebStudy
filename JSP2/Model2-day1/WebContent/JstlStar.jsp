<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>

<!-- 5개의 반복적인 일을 하는 outerfor문 -->
<c:forEach var="i" begin="0" end="4">

	<!-- 공백출력하는 반복문 -->
	<c:forEach var="j" begin="${i+1}" end="4">
		&nbsp;
	</c:forEach>
	
	<!-- *을 출력하는 반복문 -->
	<c:forEach var="j" begin="1" end="${1+(i*2)}">
	*
	</c:forEach>
<br>
</c:forEach>

</body>
</html>