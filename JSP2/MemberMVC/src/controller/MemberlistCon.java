package controller;

import java.io.IOException;
import java.util.Vector;

import javax.print.attribute.standard.PresentationDirection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberBean;
import model.MemberDAO;


@WebServlet("/MemberlistCon.do")
public class MemberlistCon extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//데이터베이스에 연결하여 회원의 모든 정보를 리턴
		MemberDAO mdao = new MemberDAO();
		Vector<MemberBean> v = mdao.getAllMember();
		
		request.setAttribute("v", v);//bean이 담긴 벡터를 jsp로 넘겨주면 jsp에서 모든 회원 리스트 출력 가능
		RequestDispatcher dis = request.getRequestDispatcher("MemberList.jsp");
		dis.forward(request, response);
	}
}
