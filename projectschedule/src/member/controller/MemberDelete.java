package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.MemberDAO;
import member.model.MemberVO;

//project/MemberDelete.do
@WebServlet("/MemberDelete.do")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //String id = (String) request.getSession().getAttribute("loginId");
		  String id = request.getParameter("id");
	      MemberDAO dao = new MemberDAO();
	      MemberVO vo = new MemberVO();
	      vo.setId(id);
	      dao.memberDelete(vo);

	      response.sendRedirect( request.getContextPath() +"/MemberList.do");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
