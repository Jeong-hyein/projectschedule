package board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;


//project/BoardInsert.do
@WebServlet("/BoardInsert.do")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/board/boardInsert.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String contents = request.getParameter("contents");
		
		//2. 서비스 로직 처리 (DAO)
		BoardDAO boardDAO = new BoardDAO();
		BoardVO board = new BoardVO();
		board.setId(id);
		board.setTitle(title);
		board.setContents(contents);
		int r = boardDAO.boardInsert(board);
		
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(contextPath + "/BoardList.do");
	}

}
