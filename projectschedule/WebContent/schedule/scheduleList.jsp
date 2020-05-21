<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>/projectschedule/scheduleList.jsp</title>
<style type="text/css">
.pagination{width: 800px; margin-left:21%}
.pagination ul{list-style-type: none; display:table; margin:0 auto;}
.pagination ul li{display:table-cell;vertical-align:middle;}
.pagination ul li.rdbtn a{width: 15px; height: 15px; background-color: #fff;
    display: inline-block;  margin: 5px;  border-radius: 50%; text-indent:-10000px}
.pagination ul li.active a{background-color:#3498DB;}
.pagination ul li.nbtn a{width: 27px;display: inline-block;background-color: #fffafa;
    padding: 3px;border-radius: 5px; color: #333; font-weight: bold;text-decoration: none; margin: 0 5px;
}
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
	text-align: center;
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

<div style="margin-left: 45%; margin-top:30px;">일정 목록</div>

<div style="margin-left: 40.5%; margin-top:30px; ">
<button ><a href="ScheduleInsert.do" style="text-decoration: none; color:black;">일정 등록</a></button>
<button><a href="ScheduleList.do" style="text-decoration: none; color:black;">리스트 보기</a></button> 
<button><a href="CalendarInsert.do" style="text-decoration: none; color:black;">달력 보기</a></button>
</div>

<div class="frm" style=" margin-left: 28%; margin-top:50px;">
<form name="searchfrm"> 
	<input name="p" value="1" type="hidden">
	ID: <input name="id">
	날짜: <input name="sdate">
	일정: <input name="schedule">
	<button>검색</button>
	<button ><a href="ScheduleInsert.do" style="text-decoration: none; color:black;">일정 등록</a></button>
</form>
</div><br>
<div>
<table border="1" id="hor-minimalist-a" summary="Employee Pay Sheet" style="margin-left: 35%;">
		<tr>
			<td>아이디</td>
			<td>날짜</td>
			<td>일정</td>
			<td>메모</td>

		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.id}</td>
				<td>
				<fmt:parseDate value="${vo.sdate}" var="sdate" pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
				<fmt:formatDate pattern="yyyy-MM-dd" value="${sdate}"/>
				</td>
				<td> <a href="ScheduleUpdate.do?seq=${vo.seq}" style="text-decoration: none; color:black;">${vo.schedule}</a></td>
				<td>${vo.memo}</td>
			</tr>
		</c:forEach>
		</table>
</div>
<script>
function gopage(p) { //document.searchfrm하면 searchfrm을 찾아간다.
		document.searchfrm.p.value = p; //폼에 p값을 담는다.
		document.searchfrm.submit(); //폼 전체를 전송
	}
</script>

<my:paging paging="${paging}" jsfunc="gopage"/> <!-- 페이지 번호 나오게 -->

</body>
</html>