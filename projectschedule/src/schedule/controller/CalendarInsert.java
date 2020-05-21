package schedule.controller;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.model.ScheduleDAO;
import schedule.model.ScheduleVO;

//project/CalendarInsert.do
@WebServlet("/CalendarInsert.do")
public class CalendarInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		ScheduleDAO dao = new ScheduleDAO();
		ArrayList<ScheduleVO> list =dao.getCal();
		
		request.setAttribute("list", list);
		request.getRequestDispatcher("/schedule/calendar.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		//등록처리
		
		response.sendRedirect("/CalendarInsert.do");
		//등록으로 고치기
		
	}

}
