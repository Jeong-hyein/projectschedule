package schedule.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.model.ScheduleDAO;
import schedule.model.ScheduleVO;

//project/ScheduleUpdate.do
@WebServlet("/ScheduleUpdate.do")
public class ScheduleUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seq = request.getParameter("seq");
		ScheduleDAO dao = new ScheduleDAO();
		ScheduleVO vo = dao.getSchedule(seq);	
		request.setAttribute("schedule", vo);
		request.getRequestDispatcher("/schedule/scheduleUpdate.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
