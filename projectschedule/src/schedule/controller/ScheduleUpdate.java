package schedule.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;
import board.model.BoardVO;
import schedule.model.ScheduleDAO;
import schedule.model.ScheduleVO;

//project/ScheduleUpdate.do
@WebServlet("/ScheduleUpdate.do")
public class ScheduleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); 
		/*
		 * String loginId = (String) request.getSession().getAttribute("loginId"); //이름
		 * 같아야한다. String seq = request.getParameter("seq"); ScheduleDAO dao = new
		 * ScheduleDAO(); ScheduleVO vo = dao.getSchedule(seq);
		 * 
		 * if(loginId == null || ! vo.getId().equals(loginId)) {
		 * request.setAttribute("errorMsg", "본인이 아닙니다.");
		 * request.getRequestDispatcher("/Schedule.do").forward(request, response);
		 * }else if (vo.getId().equals(loginId) || loginId.equals("admin")) {
		 * request.setAttribute("schedule", vo);
		 * request.getRequestDispatcher("/schedule/scheduleUpdate.jsp").forward(request,
		 * response); }
		 */
		
		String seq = request.getParameter("seq");
		ScheduleDAO dao = new ScheduleDAO();
		ScheduleVO vo = dao.getSchedule(seq);
		request.setAttribute("schedule", vo);
		request.getRequestDispatcher("/schedule/scheduleUpdate.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		
		String seq = request.getParameter("seq");
		String id = request.getParameter("id");
		String sdate = request.getParameter("sdate");
		String schedule = request.getParameter("schedule");
		String memo = request.getParameter("memo");
		
		ScheduleDAO dao = new ScheduleDAO();
		ScheduleVO vo = new ScheduleVO();
		
		vo.setSeq(Integer.parseInt(seq));
		vo.setId(id);
		vo.setSdate(sdate);
		vo.setSchedule(schedule);
		vo.setMemo(memo);
		dao.scheduleUpdate(vo);
		
		request.setAttribute("schedule", vo);
		
		response.sendRedirect( request.getContextPath() +"/ScheduleList.do");
	}

}
