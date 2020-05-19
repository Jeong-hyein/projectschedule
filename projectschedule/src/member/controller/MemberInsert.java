package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberVO;

//project/MemberInsert
@WebServlet("/MemberInsert.do")
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/memberInsert.jsp").forward(request, response);
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
				int r = memberDAO.memberInsert(member);
	
				//3. 회원목록으로 이동 //include, forwaed, sendRedirect
				//경로 다름 주의!, 페이지만 바뀌면 된다, request객체 필요없음.: send
				String contextPath = getServletContext().getContextPath();
				response.sendRedirect(contextPath + "/MemberList.do");
	}

}
