<%@page import="model.BoardBean"%>
<%@page import="java.util.Vector"%>
<%@page import="model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<%
	//전체 게시글 내용을 jsp쪽으로 가져오기
	//BoardDAO bdao = new BoardDAO();

	//전체 게시글을 리턴 받아주는 소스작성
	//1페이지, 2페이지... 이런식으로 페이지 카운터링이 필요
	//Vector<BoardBean> vec = bdao.getAllBoard();
%>
<!-- 여기 센터 -->
<h2>전체 게시글 보기</h2>

<!-- 게시글보기에 카운터릴 성정하기 위한 변수들 선언(카운터링이란 게시글이 많아서 1페이지 2페이지 이렇게 넘거나느것 -->
<%

	//총 게시물을 184개라고 가정하고 설명

	//화면에 보여질 게시글의 게수 설정
	//게시글을 10개씩 끊어서 화면에 보여줄거다.
	int pageSize = 10;

	//현재 카운터를 클릭한 번호값을 읽어오기
	String pageNum = request.getParameter("pageNum");  
	
	//만약 처음 BoardList.jsp를 클랙햇거나 수정 삭제하다가 여기로 넘어오면 pageNum값이 없기에 null처리 필요
	//즉 pageNum값이 없다는것은 게시판에 최초접속이라는 뜻. 최초접속시에는 1페이부터 보여주니까 1
	if(pageNum==null){
		pageNum = "1";
	}
	int count = 0;	//전체 글의 개수를 저장하는 변수
	int number = 0;	//글번호
	
	//현재 페이지를 의미.(pageNum을 파싱)
	int currentPage = Integer.parseInt(pageNum);
	
	//데이터베이스에서 전체 게시글 내용을 jsp쪽으로 가져오기
	BoardDAO bdao = new BoardDAO();

	//전체 개시물의 개수를 읽어들이는 메소드, 우리는 전체 글의 개수를 알아야한다.
	count = bdao.getAllCount();	//주석의 설명은 이게 184라고 가정한다
	
	//현재 페이지에 보여줄 시작번호 설정 = 데이터베이스에서 불러올 시작번호
	//만약 글이 76개있으면 76번째 글이 최신글이니 76번째 글부터 보역주기
	int startRow = (currentPage-1)*pageSize+1;	//현재 페이지의 맨 윗글(그 페이지에서 가장최신글)		//(1-1)*10+1=1 즉 1페이지일 경우 1번글부터
	int endRow = currentPage*pageSize;			//현재 페이지의 맨 아랫글(그 페이지에서 가장늦은글)	//1*10=10  10번글까지 있다.
	
	//전체 게시글을 리턴 받아주는 소스작성
	//최신글 10개를 기준으로 게시물을 이턴받아주는 메서드 호출
	Vector<BoardBean> vec = bdao.getAllBoard(startRow, endRow);
	
	//테이블에 표시할 번호를 지정
	number = count-(currentPage-1)*pageSize;	//184-(1-1)*10=184 즉 1페이지에서는 184번글부터 하나씩 줄어드는 식으로 글번호 매겨짐
%>



<table width="700" border="1" bgcolor="skyblue">
	<tr height="40">
		<td align="right" colspan="5">
		<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.jsp'"></td>
	</tr>
	<tr height="40">
		<td width="50" align="center">번호</td>
		<td width="320" align="center">제목</td>
		<td width="100" align="center">작성자</td>
		<td width="150" align="center">작성일</td>
		<td width="80" align="center">조회수</td>
	</tr>
<%
	//이 윗부분은 게시판의 1행, 즉 번호 제목 작성자 작성일 조회수라고 적힌 행임
	//이 밑으로 게시글을 붙이는 소스코드 작성
	for(int i=0;i<vec.size();i++){
		BoardBean bean = vec.get(i);	//벡터에 저장돼있는 빈클래스를 하나씩 추출(벡터 한칸마다 빈클래스의 요소들이 쭉 한번에 붙는다. 벡터는 가변길이니까 쭉 붙일수있다.)
%>
	<tr height="40">
		<td width="50" align="center"> <%= number-- %></td>	<!-- 글번호(i+1)는 ref_step기준 정렬일때 막 섞이므로 그냥 고정으로 1번부터 증가하도록 -->
		<td width="320" align="left"> <a href="BoardInfo.jsp?num=<%= bean.getNum() %>" style="text-decoration:none"> 	<!-- style="text-decoration:none밑줄없애기 -->
	<%
		if(bean.getRe_step()>1){	//답변글의 제목은 들여쓰기해주기
			for(int j=0;j<(bean.getRe_step()-1)*5;j++){	//-1 해준이유는 부모글은 둘여쓰기 안함, 2부터 자식글이므로ㅇㅇ, *5는 5칸공백으로 들여쓰기 
	%>
			&nbsp;
	<%
			}
		}
	%>
		<%= bean.getSubject() %></a> </td>
		<td width="100" align="center"> <%= bean.getWriter() %></td>
		<td width="150" align="center"> <%= bean.getReg_date() %></td>
		<td width="80" align="center"> <%= bean.getReadcount() %></td>
	</tr>
<%
	}	
%>
</table>

<p>
<!-- 페이지 카운터링 소스 -->
<%
	if(count > 0){	//전체 게시글 수가 0부타 크면 실행, 글이 한개라도 있어야 밑에 1페이지라고 나오니까
		int pageCount = count / pageSize +(count%pageSize == 0 ? 0 : 1);	//184/10 + 1 = 19 즉, 184개의 글을 10개씩 나누면 18페이지가 꽉차고 나머지 글은 1페이지 추가해서 보여줌
		
		//시작페이지 숫자를 설정(1번게시글부터인지, 11번게시글부턴지, 21번글부터인지)
		 int startPage = 1;
		 if(currentPage % 10 != 0){	//현재 페이지가 10의 배수가 아니라면
			 startPage = (int)(currentPage/10)*10+1;	//(1/10)*10+1=1  즉, 1페이지에선 1부터 시작페이지로 설정 , 11부터할거냐 21부터할거냐 31부터할거냐 ....
		 }
		 else{	//10의 배수라면
			 startPage = ((int)(currentPage/10)-1)*10+1;	//현재 20페이지라면 => (20/10-1)*10+1 = 11, 즉 11~20을 보여줌 
		 }
		 int pageBlock = 10;	//카운터링 처리 숫자, 즉 일단 10으로 잡아놓기?
		 int endPage = startPage+pageBlock-1;	//1+10-1=10 즉, 화면에 보여질 페이지 마지막숫자
		 
		 if(endPage > pageCount) endPage = pageCount;	//10>19  실행x
		 
		 //이전이라는 링크를 만들건지 파악(1~10페이지 구간에서는 이전페이지 안나옴. 다음페이지버튼만 있다.  11~20구간부터 이전페이지 버튼이 나옴)
		 if(startPage > 10){	//11~20페이지 구간부터 [이전페이지] 버튼이 생성
%>
			<a href="BoardList.jsp?pageNum=<%= startPage-10 %>">[이전]</a>
<%
		 }
		 //startPage부터 endPage까지 쭉 실행
		 for(int i= startPage; i<=endPage; i++){	//i<=endPage에서 <=이기에 1~10페이지까지 보임
%>
			<a href="BoardList.jsp?pageNum=<%= i %>">[<%= i %>]</a>
<%
		 }

		//다음페이지 버튼을 만들건지 파악(남은 페이지가 10개보다 작으면 다음페이지버튼이 없다)
		if(endPage < pageCount){
%>
			<a href="BoardList.jsp?pageNum=<%= startPage+10 %>">[다음]</a>	
<%			//다음을 누르면 startPage에 10을 더해서 맨위에서부터 다시 시작(BoardList.jsp를 호출했으니까)
		}
	}
	
%>



<!-- 여기 센터 -->
</body>
</html>