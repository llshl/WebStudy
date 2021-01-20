package control;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;


@WebServlet("/BoardListCon.do")
public class BoardListCon extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//화면에 보여질 게시글의 개수 지정
		int pageSize = 10;
		//현재 보여지고 있는 페이지의 넘버값을 읽어드림
		String pageNum = request.getParameter("pageNum");
		//초기 페이지 진입시 null처리
		if(pageNum == null) {
			pageNum = "1";
		}
		//전체 게시글의 개수
		int count = 0;
		//jsp페이지 내에서 보여질 넘버링 숫자값 저장하는 변수
		int number = 0;
		
		//현재 보여지고 있는 페이지 문자를 숫자로 변환
		int currentPage = Integer.parseInt(pageNum);
		//전체 게시글의 개수를 가져와야하기에 데이터베이스 객체생성
		BoardDAO bdao = new BoardDAO();
		count = bdao.getAllCount();
		
		//현재 보여질 페이지 시작번호 설정
		int startRow = (currentPage-1)*pageSize+1;
		int endRow = currentPage*pageSize;
		
		//최신글 10개를 기준으로 게시글 리턴받아주는 메서드 호출
		Vector<BoardBean> v = bdao.getAllBoard(startRow, endRow);	//startRow값과 endRow값을 넣어줘야 이 범위에 맞는 게시물만 가져온다
		number = count - (currentPage-1) * pageSize;
		
		
		///////////////////////////////수정, 삭제 시 비밀번호가 틀렸다면
		String msg = (String) request.getAttribute("msg");
		
		
		///////////////////////////////위에서 데이터베이스에서 가져온 정보들을 BoardList.jsp쪽으로 request객체에 담아서 넘겨주면 된다.
		//일단 request객체에 싣기(담기)
		request.setAttribute("v",v);	//최신글 10개를 빈으로 담은 벡터를 "v"라고 이름붙혀서 request객체에 실어서 jsp로 보냄
		request.setAttribute("number", number);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("count", count);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("msg", msg);
		
		//request객체를 넘겨줌
		RequestDispatcher dis = request.getRequestDispatcher("BoardList.jsp");	//여기로 보내겟다
		dis.forward(request, response);
	}
}






