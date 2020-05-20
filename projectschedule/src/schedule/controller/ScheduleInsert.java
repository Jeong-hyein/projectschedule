package schedule.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.model.ScheduleDAO;
import schedule.model.ScheduleVO;

//project/ScheduleInsert.do
@WebServlet("/ScheduleInsert.do")
public class ScheduleInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/scheduleInsert.jsp")
		.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		//파라미터 받기
		String id = request.getParameter("id");
		String sdate = request.getParameter("sdate");
		String schedule = request.getParameter("schedule");
		String memo = request.getParameter("memo");
		
		//2. 서비스 로직 처리 (DAO)
		ScheduleDAO scheduleDAO = new ScheduleDAO();
		ScheduleVO scheduleVO = new ScheduleVO();
		scheduleVO.setId(id);
		scheduleVO.setSdate(sdate);
		scheduleVO.setSchedule(schedule);
		scheduleVO.setMemo(memo);
		int r = scheduleDAO.scheduleInsert(scheduleVO);
		
		String contextPath = getServletContext().getContextPath();
		response.sendRedirect(contextPath + "/ScheduleList.do");
	}

}
