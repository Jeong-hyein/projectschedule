<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>project/schedule</title>
<jsp:include page ="/common/header.jsp" />
<style type="text/css">
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

<script>
if("${errorMsg}" != ""){
	alert("${errorMsg}")
	}
</script>

<div style="margin-left:45%; margin-top:30px;">일정 목록</div>

<div style="margin-left:40.5%; margin-top:30px;">
<button ><a href="ScheduleInsert.do" style="text-decoration: none; color:black;">일정 등록</a></button>
<button><a href="ScheduleList.do" style="text-decoration: none; color:black;">리스트 보기</a></button> 
<button><a href="CalendarInsert.do" style="text-decoration: none; color:black;">달력 보기</a></button>
</div>

</body>
</html>