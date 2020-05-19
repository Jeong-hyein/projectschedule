package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberVO;


//project/MemberUpdate.do
@WebServlet("/MemberUpdate.do")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = (String) request.getSession().getAttribute("loginId"); //이름 같아야한다.
		if(id == null) {    	
			response.sendRedirect(request.getContextPath()+"/MemberLogin.do");
			return;
		} 
		
		//서비스 로직 처리(회원정보 1건 조회)
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.getMember(id);
		
		//결과 저장
		request.setAttribute("member", vo);
		
		//뷰페이지로 이동, request객체를 넘겨줘야하기 때문에 forward
		request.getRequestDispatcher("/member/memberUpdate.jsp")
				.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		//1. 파라미터 받기
		String id = request.getParameter("id"); //name속성이 파라미터
		String pwd = request.getParameter("pwd"); 
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String introduction = request.getParameter("introduction");		
		
		//2. 서비스 로직 처리 (DAO)
		MemberDAO memberDAO = new MemberDAO();
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setName(name);
		member.setGender(gender);
		member.setIntroduction(introduction);
		int r = memberDAO.memberUpdate(member);
		
		request.setAttribute("member", member);
		response.sendRedirect( request.getContextPath() +"/MemberList.do");
		}
	
	}


