package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

@WebServlet("/BoardUpdateProcCon.do")
public class BoardUpdateProcCon extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//jsp폼에서 넘어온 데이터를 받아줌
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");	//이건 사용자가 입력한 패스워드
		String pass = request.getParameter("pass");	//이건 히든으로 넘어온거. 실제 데이터베이스에 저장돼있는 패스워드
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		//password와 pass를 비교해야한다
		if(password.equals(pass)) {		//올바른 패스워드를 받았을경우 수정메서드 실행
			BoardDAO bdao = new BoardDAO();
			bdao.updateBoard(num,subject,content); 	//평소에는 num,subject,content과 같은 요소들을 Bean으로 묶어서 보냈겠지만 3개밖에 없으니 그냥 인자로 보냄
			request.setAttribute("msg", "1");	//1보내면 수정 완료
			//쿼리보낸 후 수정환료됏다면 전체게시글 보기로 이동
			RequestDispatcher dis = request.getRequestDispatcher("BoardListCon.do");
			dis.forward(request, response);
		}
		else {
			//입력받은 비밀번호가 틀렸다면 이전페이지로 이동
			request.setAttribute("msg", "2");	//2보내면 비번틀렷다는뜻
		}
	}
}
