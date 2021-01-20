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
 * Servlet implementation class BoardReWriteProcCon
 */
@WebServlet("/BoardReWriteProcCon.do")
public class BoardReWriteProcCon extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-KR");
		
		//게시글에 답글달려면 답글달려는 게시물에 대한 정보들이 필요하므로 그 정보들을 bean에 넣은 뒤 DAO로 넘겨줌
		BoardBean bean = new BoardBean();
		
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		bean.setContent(request.getParameter("content"));
		
		bean.setRef(Integer.parseInt(request.getParameter("ref")));
		bean.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		bean.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		BoardDAO bdao = new BoardDAO();
		bdao.reInsertBoard(bean);	//수정한 게시글(빈)을 디비에 넣는 메서드
		
		RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");	//바로 jsp로가는것이 아닌 여기를 거치고간다
		dis.forward(request, response);
	}
}
