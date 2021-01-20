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
 * Servlet implementation class BoardDeleteCon
 */
@WebServlet("/BoardDeleteCon.do")
public class BoardDeleteCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//글 삭제이기에 글 번호를 입력
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO bdao = new BoardDAO();
		//빈클래스타입으로 리턴
		BoardBean bean = bdao.getOneUpdateBoard(num);//조회수를 증가시키지 않는 메서드이기 때문에 이것 사용
		
		request.setAttribute("bean", bean);
		RequestDispatcher dis = request.getRequestDispatcher("BoardDeleteForm.jsp");
		dis.forward(request, response);
	}

}
