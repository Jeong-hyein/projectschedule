package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.BoardDAO;
import board.model.BoardVO;


//project/BoardUpdate.do
@WebServlet("/BoardUpdate.do")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		PrintWriter out = response.getWriter();
		 String seq = request.getParameter("seq"); BoardDAO dao = new BoardDAO();
		 BoardVO vo = dao.getBorad(seq);
		 
		 String loginId = (String) request.getSession().getAttribute("loginId"); //이름 같아야한다. 
		 if(loginId == null || ! vo.getId().equals(loginId) ||!loginId.equals("admin")) 
		 { 
			 out.append("<script>");
			 out.append("alert('본인이 아닙니다.');");
			 out.append("location.href='BoardList.do';");
			 out.append("</script>");
			 return; 
		 }
		 else if (vo.getId().equals(loginId) || loginId.equals("admin")) 
		 {
			request.setAttribute("board", vo);
		    request.getRequestDispatcher("/board/boardUpdate.jsp").forward(request,
		    response); 
		 }
		 
		
//		String seq = request.getParameter("seq");
//		BoardDAO dao = new BoardDAO();
//		BoardVO vo = dao.getBorad(seq);	
//		request.setAttribute("board", vo);
//		request.getRequestDispatcher("/board/boardUpdate.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		

		
		String seq = request.getParameter("seq");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");

		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setSeq(Integer.parseInt(seq));
		vo.setId(id);
		vo.setTitle(title);
		vo.setContents(contents);
		dao.boardUpdate(vo);
		
		request.setAttribute("board", vo);
		
		response.sendRedirect( request.getContextPath() +"/BoardList.do");
	}

}
