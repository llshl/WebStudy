package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.MemberBean;
import model.MemberDAO;


@WebServlet("/proc.do")
public class MemberJoinProc extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("EUC-KR");
		
		MemberBean bean = new MemberBean();
		bean.setId(request.getParameter("id"));
		String pass1 = request.getParameter("pass1");
		String pass2 = request.getParameter("pass2");
		bean.setPass1(pass1);
		bean.setPass2(pass2);
		bean.setEmail(request.getParameter("email"));
		bean.setTel(request.getParameter("tel"));
		String[] arr = request.getParameterValues("hobby");	//hobby는 여러개일수 있으니 배열로 받아옴
		String data = "";
		for(String string : arr) {
			data += string+" ";	//하나의 스트링으로 데이터를 연결
		}
		bean.setHobby(data); 	//하나의 스트링으로 만든 data을 넣음
		bean.setJob(request.getParameter("job"));
		bean.setAge(request.getParameter("age"));
		bean.setInfo(request.getParameter("info"));
		//여기까지 bean설정
		
		
		//패스워드가 같을 경우에만 데이터베이스에 저장
		if(pass1.equals(pass2)) {	//비밀번호가 같다면
			//데이터베이스 객체 생성
			MemberDAO mdao = new MemberDAO();
			mdao.insertMember(bean);
			//컨트롤러에서 또 다른 컨트롤러를 호출해야한다.
			RequestDispatcher dis = request.getRequestDispatcher("MemberlistCon.do"); 	//로그인 잘 됐으면 새로운 컨트롤러 호출 멤버 리스트 보여줘야하기때문
			dis.forward(request, response);
		}else {
			request.setAttribute("msg", "패스워드가 일치하지 않습니다."); 	//스크립트 태그를 쓰면 안된다. msg로 넘겨줘야함
			RequestDispatcher dis = request.getRequestDispatcher("LoginError.jsp"); 	//LoginError.jsp로 떠넘기기
			dis.forward(request, response);
		}
	}
}
