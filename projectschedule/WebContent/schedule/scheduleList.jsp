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
body
{
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

<div class="title"><h3>일정 목록</h3></div>

<div class="insert"><a href="ScheduleInsert.do">일정 등록</a></div>
<div class="frm"><form name="searchfrm"> 
	<input name="p" value="1" type="hidden">
	ID: <input name="name">
	<button>검색</button>
	<input name="p" value="1" type="hidden">
	날짜: <input name="sdate">
	<button>검색</button>
	<input name="p" value="1" type="hidden">
	일정: <input name="schedule">
	<button>검색</button>
</form>
</div><br>
<div>
<table border="1" id="hor-minimalist-a" summary="Employee Pay Sheet">
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
				<fmt:formatDate pattern="yyyy-MM-dd" value="${sdate}"/></td>
				<td> <a href="ScheduleUpdate.do?seq=${vo.seq}">${vo.schedule}</a></td>
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
<div>
<my:paging paging="${paging}" jsfunc="gopage"/> <!-- 페이지 번호 나오게 -->
</div>
</body>
</html>