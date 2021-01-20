package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	
@WebServlet("/HelloWorld")		//					/HelloWorld라고 주소 url에 표시해주어야 이 서블릿 클래스가 실행된다.
public class HelloWorld extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//reqPro로 떠넘김
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);	//reqPro로 떠넘김
	}
	
	//post나 get방식으로 들어온거 모두 이 메서드에 처리
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//화면에 HellloWorld라고 출력하고싶다... jsp쪽으로 넘겨질 데이터를 설정
		String msg = "Hello World 안녕하세요~";
		Integer data = 12;
		
		//jsp쪽으로 데이터를 request에 부착하여 넘겨줘야한다. forward에 객체만 담을수있는듯?
		request.setAttribute("msg", msg);	//"msg"값으로 msg(Hello World 안녕하세요~)넘기겠다
		request.setAttribute("data", data);
		
		//서블릿에서 jsp를 호출하면서 데이터를 같이 넘겨주는 객체를 선언
		RequestDispatcher rd = request.getRequestDispatcher("HelloWorld.jsp");	//jsp파일명을 명시
		rd.forward(request, response);
	}

}
