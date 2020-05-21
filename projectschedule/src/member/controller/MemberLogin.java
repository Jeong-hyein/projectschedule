package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.MemberDAO;
import member.model.MemberVO;



//project/MemberLogin
@WebServlet("/MemberLogin.do")
public class MemberLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/memberLogin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//2. 서비스 로직
		MemberDAO dao = new MemberDAO();
		dao.getMember(id); //단건조회
		MemberVO vo = dao.getMember(id);
		
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
				if(vo.getId() == null) { 
			request.setAttribute("errorMsg", "id 오류");
			request.getRequestDispatcher("/member/memberLogin.jsp").forward(request, response);
		}else if(! vo.getPwd().equals(pwd)) { 
			request.setAttribute("errorMsg", "pwd 오류"); 
			request.getRequestDispatcher("/member/memberLogin.jsp").forward(request, response);
		} else { 
			HttpSession session = request.getSession();
			session.setAttribute("loginId", id); 
			session.setAttribute("loginMember", vo); 
			response.sendRedirect(request.getContextPath() + "/Main.do");
		}
	}

}
