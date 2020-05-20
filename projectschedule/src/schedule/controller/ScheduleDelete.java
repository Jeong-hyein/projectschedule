package schedule.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import schedule.model.ScheduleDAO;
import schedule.model.ScheduleVO;


//project/ScheduleDelete.do
@WebServlet("/ScheduleDelete.do")
public class ScheduleDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String seq = request.getParameter("seq");//이름 같아야한다.
		
		ScheduleDAO dao = new ScheduleDAO();
		ScheduleVO vo = new ScheduleVO();
		vo.setSeq(Integer.parseInt(seq));
		dao.scheduleDelete(vo);

		response.sendRedirect( request.getContextPath() +"/ScheduleList.do");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
