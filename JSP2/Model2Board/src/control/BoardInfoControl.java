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
 * Servlet implementation class BoardInfoControl
 */
@WebServlet("/BoardInfoControl.do")
public class BoardInfoControl extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//BoardList.jsp에서 bean.num가 넘어옴. 이거를 받아야한다
		int num = Integer.parseInt(request.getParameter("num"));
		
		//데이터베이스에 접근
		BoardDAO bdao = new BoardDAO();
		//하나의 게시글에 대한 정보를 리턴. 어떤 게시글인지는 num로 식별
		BoardBean bean = bdao.getOneBoard(num);	//num을 기준으로 디비에서 게시글가져오기
		
		//디비에서 게시물 한개의 정보를 bean으로 받아온것을 jsp로 넘겨준다
		request.setAttribute("bean", bean);	//req객체에 올리기
		RequestDispatcher dis = request.getRequestDispatcher("BoardInfo.jsp");
		dis.forward(request, response);
		
		
		

	}
}
