package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;

/**
 * Servlet implementation class BoardUpdateCon
 */
@WebServlet("/BoardUpdateCon.do")
public class BoardUpdateCon extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//해당 글의 번호
		int num = Integer.parseInt(request.getParameter("num"));
		
		//데이터베이스에서 하나의 게시글에 대한 정보를 리턴하는 메서드
		BoardDAO bdao = new BoardDAO();
		//리턴타입은 빈
		BoardBean bean = bdao.getOneUpdateBoard(num);	//조회수를 증가시키지 않고 디비에서 하나 가져오기
		
		request.setAttribute("bean", bean);  //게시물 bean으로 가져온걸 req객체에 싣는다
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardUpdateForm.jsp");
		dis.forward(request, response);
 	}

}
