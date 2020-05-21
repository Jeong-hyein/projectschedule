<%@page import="schedule.model.ScheduleVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  <title>calendar</title><style type="text/css">
body
{
background: -webkit-linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);
  background: linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);
	line-height: 1.6em;
}

#hor-minimalist-a
{
	font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif;
	font-size: 12px;
	background: #fff;
	margin: 45px;
	width: 480px;
	border-collapse: collapse;
	text-align: left;
}
#hor-minimalist-a th
{
	font-size: 14px;
	font-weight: normal;
	color: #039;
	padding: 10px 8px;
	border-bottom: 2px solid #6678b1;
}
#hor-minimalist-a td
{
	color: #669;
	padding: 9px 8px 0px 8px;
}
#hor-minimalist-a tbody tr:hover td
{
	color: #009;
}
div .title .insert .frm {
text_align: center;
}
</style>

<jsp:include page ="/common/header.jsp" />
<div style="margin-left:45%; margin-top:30px;">일정 목록</div>

<div style="margin-left:40.5%; margin-top:30px;">
<button ><a href="ScheduleInsert.do" style="text-decoration: none; color:black;">일정 등록</a></button>
<button><a href="ScheduleList.do" style="text-decoration: none; color:black;">리스트 보기</a></button> 
<button><a href="CalendarInsert.do" style="text-decoration: none; color:black;">달력 보기</a></button>
</div>
  <%
  java.util.Calendar cal=java.util.Calendar.getInstance(); //Calendar객체 cal생성
  int currentYear=cal.get(java.util.Calendar.YEAR); //현재 날짜 기억
  int currentMonth=cal.get(java.util.Calendar.MONTH);
  int currentDate=cal.get(java.util.Calendar.DATE);
  String Year=request.getParameter("year"); //나타내고자 하는 날짜
  String Month=request.getParameter("month");
  int year, month;
  if(Year == null && Month == null){ //처음 호출했을 때
  year=currentYear;
  month=currentMonth;
  }
  else { //나타내고자 하는 날짜를 숫자로 변환
   year=Integer.parseInt(Year);
   month=Integer.parseInt(Month);
   if(month<0) { month=11; year=year-1; } //1월부터 12월까지 범위 지정.
   if(month>11) { month=0; year=year+1; }
  }
  %>
  
 
  <table border=0 style="margin-left: 24%; margin-top: 30px; color: black;  white-space: nowrap; width: 49%; "> <!-- 달력 상단 부분, 더 좋은 방법이 없을까? -->
   <tr>
    <td align=left width=200> <!-- 년 도-->
    <a href="CalendarInsert.do?year=<%out.print(year-1);%>&month=<%out.print(month);%>" style="text-decoration: none;">◀</a>
    <% out.print(year); %>년
    <a href="CalendarInsert.do?year=<%out.print(year+1);%>&month=<%out.print(month);%>" style="text-decoration: none;">▶</a>
    </td>
    <td align=center width=300> <!-- 월 -->
    <a href="CalendarInsert.do?year=<%out.print(year);%>&month=<%out.print(month-1);%>" style="text-decoration: none;">◀</a>
    <% out.print(month+1); %>월
    <a href="CalendarInsert.do?year=<%out.print(year);%>&month=<%out.print(month+1);%>" style="text-decoration: none; ">▶</a>
    </td>
    <td align=right width=200"><% out.print(currentYear + "-" + (currentMonth+1) + "-" + currentDate); %></td>
   </tr>
  </table>
  <table border=1 cellspacing=0 style="margin-left: 24%; background: #fff; border-collapse: collapse; width: 50%; "> <!-- 달력 부분 -->
   <tr style="text-align: center; padding: 10px 8px; color:#669;">
    <td width=100>일</td> <!-- 일=1 -->
    <td width=100>월</td> <!-- 월=2 -->
    <td width=100>화</td> <!-- 화=3 -->
    <td width=100>수</td> <!-- 수=4 -->
    <td width=100>목</td> <!-- 목=5 -->
    <td width=100>금</td> <!-- 금=6 -->
    <td width=100>토</td> <!-- 토=7 -->
   </tr>
   <tr height=30>
   <%
   cal.set(year, month, 1); //현재 날짜를 현재 월의 1일로 설정
   int startDay=cal.get(java.util.Calendar.DAY_OF_WEEK); //현재날짜(1일)의 요일
   int end=cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); //이 달의 끝나는 날
   int br=0; //7일마다 줄 바꾸기
   ArrayList<ScheduleVO> list = (ArrayList<ScheduleVO>) request.getAttribute("list");
   for(int i=0; i<(startDay-1); i++) { //빈칸출력
    out.println("<td>&nbsp;</td>");
    br++;
    if((br%7)==0) {
     out.println("<br>");
    }
   }
   for(int i=1; i<=end; i++) { //날짜출력
    out.println("<td style='color:#669;'>");
    out.print(i);
   //일정출력
    String smonth;
    String sday;
    if(Integer.toString(month+1).length()<2) {
    	smonth = "0" + (month+1);
    } else {
    	smonth = Integer.toString((month+1));
    }
    if(Integer.toString(i).length()<2) {
    	sday = "0" + i;
    } else {
    	sday = Integer.toString(i);
    }
    String date = year+smonth+sday;
	//out.print("<br>");
    if(list != null){
	    for(int j=0; j<list.size(); j++){
			if(date.equals(list.get(j).getSdate())) {
				out.print("<br>"+ "<a href='ScheduleUpdate.do?seq="+list.get(j).getSeq()+"'" + "style='text-decoration: none; color:black;'>"+(j+1)+". " +list.get(j).getSchedule()+"</a>");			
			}
	    }
    }
   out.print("</td>");
    br++;
    if((br%7)==0 && i!=end) {
     out.println("</tr><tr height=30>");
    }
   }
   while((br++)%7!=0) //말일 이후 빈칸출력
    out.println("<td>&nbsp;</td>");
   %>
   </tr>
  </table>
 </body>
</html>