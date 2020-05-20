<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>projectschedule/boardList.jsp</title>
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
</style>


<jsp:include page ="/common/header.jsp" />
<span><a href="BoardInsert.do">게시판등록</a></span>
<h3>게시판 목록</h3>
<form name="searchfrm">
	<input name="p" value="1" type="hidden">
	작성자: <input name="id">
	<button>검색</button>
	<input name="p" value="1" type="hidden">
	제목: <input name="title">
	<button>검색</button>
</form><br>
<table border="1" id="hor-minimalist-a" summary="Employee Pay Sheet">
	<tr>
	<th>번호</th>
	<th>아이디</th>
	<th>제목</th>
	<th>내용</th>
	<th>쓴 날짜</th>
	</tr>
	<c:forEach var="vo" items="${list}" >
		<tr>
		<td>${vo.seq}</td>
		<td>${vo.id}</td>
		<td><a href="BoardUpdate.do?seq=${vo.seq}">${vo.title}</a></td>
		<td>${vo.contents}</td>
		<td>${vo.regdt}</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>