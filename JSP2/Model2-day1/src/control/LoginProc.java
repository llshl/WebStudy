package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginProc.do")	//jsp의 action="여기"와 같아야한다. 서블릿파일명은 달라도됨
public class LoginProc extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//doGet방식이든 doPost방식이든 reqPro가 받아주겠다 이말.
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//doGet방식이든 doPost방식이든 reqPro가 받아주겠다 이말.
	}

	
	public void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");	//doGet이든 doPost든 여기로 떠넘겨 줬다
		String pass = request.getParameter("password");
		
		request.setAttribute("id", id); 	//request객체에 데이터를 저장, 세션에 저장하면 모든 페이지에서 사용가능하고 request에 저장하면 그 다음페이지까지만 사용 가능
		request.setAttribute("pass", pass);

		//jsp쪽으로 requset객체를 떠넘겨준다
		RequestDispatcher dis = request.getRequestDispatcher("LoginProc.jsp");	//떠넘겨줄 객체는 "LoginProc.jsp"
		dis.forward(request, response);	//"LoginProc.jsp"쪽으로 포워드시킴, 이때 request와 response를 떠넘겨줌
		//그럼 이제 "LoginProc.jsp"가 실행됨
		
	}
}
