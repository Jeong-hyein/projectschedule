<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>projectschedule/boardList.jsp</title>
</head>
<body>
<jsp:include page ="/common/header.jsp" />
<div><a href="BoardInsert.do">게시판등록</a></div>
<h3>게시판 목록</h3>
<table border="1">
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
		<td>
		<fmt:parseDate value="${vo.regdt}" var="fmtDt" pattern="yyyy-MM-dd HH:mm:ss"/>
		<fmt:formatDate value="${fmtDt}" pattern="MM-dd일 HH시 u"/> <!-- type =" " date: 날짜, time: 시간, both: 둘다, u:요일-->
		</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>