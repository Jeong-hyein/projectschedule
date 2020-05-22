package schedule.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
		PrintWriter out = response.getWriter();
		 String loginId = (String) request.getSession().getAttribute("loginId"); //이름같아야한다. 
		 String seq = request.getParameter("seq"); 
		 ScheduleDAO dao = new ScheduleDAO(); 
		 ScheduleVO vo = dao.getSchedule(seq);
		  
		 if(loginId == null || ! vo.getId().equals(loginId)) {
			 
			 out.append("<script>");
			 out.append("alert('본인이 아닙니다.');");
			 out.append("location.href='Schedule.do';");
			 out.append("</script>");
			 return; 
		 
		 }else if (vo.getId().equals(loginId) || loginId.equals("admin")) {
		 request.setAttribute("schedule", vo);
		 request.getRequestDispatcher("/schedule/scheduleUpdate.jsp").forward(request,
		 response); }
		 
		
//		String seq = request.getParameter("seq");
//		ScheduleDAO dao = new ScheduleDAO();
//		ScheduleVO vo = dao.getSchedule(seq);
//		request.setAttribute("schedule", vo);
//		request.getRequestDispatcher("/schedule/scheduleUpdate.jsp").forward(request, response);
		
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
