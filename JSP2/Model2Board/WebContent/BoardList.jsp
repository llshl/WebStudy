<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<body>

<c:if test="${msg==2}">
	<script type="text/javascript">
		alert("비밀번호가 틀렸습니다.")
	</script>
</c:if>
<c:if test="${msg==1}">
	<script type="text/javascript">
		alert("수정이 완료되었습니다.")
	</script>
</c:if>


<h2>전체글보기</h2>
<table width="700" border="1" bordercolor="skyblue">
	<tr height="40" align="right">
		<td colspan="5" align="right">
		<button onclick="location.href='BoardWriteForm.jsp'">글쓰기</button>
		</td>
	</tr>
	<tr height="40">
		<td width="50" align="center">번호</td>
		<td width="320" align="center">제목</td>
		<td width="100" align="center">작성자</td>
		<td width="150" align="center">작성일</td>
		<td width="80" align="center">조회수</td>
	</tr>
	
	<c:set var="number" value="${number }"/>	<!-- request객체로 넘어온 number를 "number"라고 이름 붙여서 받는다 -->
	<c:forEach var="bean" items="${v}" >	<!-- v를 EL로 받아서 bean을 하나씩 빼겟다-->
	
	<tr height="40">
		<td width="50" align="center">${number}</td>	<!-- 글번호 -->
		<td width="320" align="center">
			<c:if test="${bean.re_step>1}">
				<c:forEach var="j" begin="1" end="${(bean.re_step-1)*5 }">	<!-- 답글의 경우 들여쓰기를 해주는것 -->
				&nbsp;
				</c:forEach>
			</c:if>
			<a href="BoardInfoControl.do?num=${bean.num}">${bean.subject}</a>	<!-- 제목 -->
		</td>
		<td width="100" align="center">${bean.writer}</td>	<!-- 작성자 -->
		<td width="150" align="center">${bean.reg_date}</td>	<!-- 작성일 -->
		<td width="80" align="center">${bean.readcount}</td>	<!-- 조회수 -->
	</tr>
												<!-- EL안에서는 연산이 안되므로  -->
	<c:set var="number" value="${number-1 }"/>	<!-- for문 한번 끝나고 나면(게시글 한개가 출력되면) number를 -1씩 하면서 다시 넣어줌(어자피 게시판은 1씩 내림차순이니까 -->
	</c:forEach>
</table>
<p>
<!-- jstl로 페이지 카운터링 소스 작성 -->

	<c:if test="${count>0 }">
		<c:set var="pageCount" value="${count/ pageSize+(count%pageSize == 0 ? 0 :1) }"/>
		<c:set var="startPage" value="${1 }"/>
		
		<c:if test="${currentPage % 10 != 0}">
			<!-- 결과를 정수형으로 리턴받아야하기에 fmt -->
			<fmt:parseNumber var="result" value="${currentPage/10 }" integerOnly="true" />
			<c:set var="startPage" value="${result*10+1 }"/>
		</c:if>
		
		<c:if test="${currentPage % 10 == 0}">
			<!-- 결과를 정수형으로 리턴받아야하기에 fmt -->
			<c:set var="startPage" value="${(result-1)*10+1 }"/>
		</c:if>
		
		<!-- 화면에 보여질 페이징 처리 숫자를 표현 -->
		<c:set var="pageBlock" value="${10 }"/>
		<c:set var="endPage" value="${startPage+pageBlock-1 }"/>
			
		<c:if test="${endPage > pageCount}">
			<c:set var="endPage" value="${pageCount }"/>
		</c:if>
		
		<!-- 이전 링크를 걸어야 하는지 파악. 11페이지부터는 이전 버튼이 있어야한다. -->
		<c:if test="${startPage > 10}">
			<a href='BoradListCon.do?pageNum=${startPage-10 }'>[이전] </a>
		</c:if>
	
		<!-- 페이징처리 -->
		<c:forEach var="i" begin="${ startPage}" end="${endPage}">
			<a href='BoradListCon.do?pageNum=${i }' style="text-decoration:none">[${i}] </a>
		</c:forEach>
		
		<!-- 다음 링크를 걸어야 하는지 파악. -->
		<c:if test="${endPage < pageCount}">
			<a href='BoradListCon.do?pageNum=${startPage+10 }'>[다음] </a>
		</c:if>
	</c:if>

</body>
</html>