package board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import common.Paging;
import member.model.MemberDAO;
import member.model.MemberVO;


//project/BoardList.do
@WebServlet("/BoardList.do")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		BoardDAO dao = new BoardDAO();
		
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		//1. 파라미터
		
		//페이징 처리(추가로 들어간거)
		//현재 페이지 파라미터 받기
		String strPage = request.getParameter("p"); //페이지 번호 파라미터로 받음
		int p = 1;
		if(strPage != null && !strPage.isEmpty()) { //페이지 파라미터를 안받아오면 1로 받아왓으면 받아온값으로
			p = Integer.parseInt(strPage);
		}
		
		//페이징 객체를 생성
		Paging paging = new Paging();
		paging.setPageUnit(5);	 	//한페이지에 출력할 레코드 건수, 생략시 10(디폴트가 10),(옵션: 생략가능)
		paging.setPageSize(3);		//한페이지 출력할 페이지 번호 수(옵션: 생략가능) 
		paging.setPage(p);			//현재페이지(필수)
//		paging.setTotalRecord(50); //전체 레코드 건수 조회(필수)
		paging.setTotalRecord(dao.getCount(id, title));
		request.setAttribute("paging", paging);
		
		
		//서비스(DAO 목록조회)
		//범위에 있는것만 출력하기 위해
		int start = paging.getFirst();
		int end = paging.getLast();
		
		ArrayList<BoardVO> list = dao.getBoardList(start, end, id, title);
		
		//3.결과 출력(out.print) or 결과저장해서 view forward
		request.setAttribute("list", list);
		request.getRequestDispatcher("/board/boardList.jsp")
				.forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
