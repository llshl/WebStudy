package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;

@WebServlet("/MProc2")
public class MemberJoinProc2 extends HttpServlet {
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}
	
	//이 컨트롤러로 여러 jsp를 처리할거라서 get으로 들어올지 post로 들어올지 모른다
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	//request객체에 담겨서 매겨변수로 들어옴
		//request를 각각 일일히 할수없기때문에 bean으로 받는다
		MemberBean bean = new MemberBean();
		bean.setId(request.getParameter("id"));
		bean.setPassword(request.getParameter("password"));
		bean.setEmail(request.getParameter("email"));
		bean.setTel(request.getParameter("tel"));
		bean.setAddress(request.getParameter("address"));
		
		//request객체에 bean클래스를 추가
		request.setAttribute("bean", bean);
		
		RequestDispatcher dis = request.getRequestDispatcher("MemberView.jsp");
		dis.forward(request, response);
	}

}
