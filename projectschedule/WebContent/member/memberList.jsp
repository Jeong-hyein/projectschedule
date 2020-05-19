<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<style>
.mailbg {
	background-color: yellowgreen
};
</style>
<title>project/memberList.jsp</title>
<jsp:include page ="/common/header.jsp" />

<h3>회원목록</h3>
<form name="searchfrm"> <!-- action 없으면 조건을 가지고 현재페이지 다시 부르는거. -->
	<input name="p" value="1" type="hidden">
	이름: <input name="name">
	<button>검색</button>
</form><br>
	<table border="1">
		<tr>
			<td>아이디</td>
			<td>이름</td>
			<td>성별</td>
			<td>자기소개</td>
			<td>등록일자</td>
		</tr>
		<c:forEach var="vo" items="${list}">
			<tr>
				<td>${vo.id}</td>
				<td>${vo.name}</td>
				<td>${vo.gender}</td>
				<td>${vo.introduction}</td>
				<td>${vo.regdt}</td>
			</tr>
		</c:forEach>
		</table>
<script>
function gopage(p) { //document.searchfrm하면 searchfrm을 찾아간다.
		document.searchfrm.p.value = p; //폼에 p값을 담는다.
		document.searchfrm.submit(); //폼 전체를 전송
	}
</script>
<my:paging paging="${paging}" jsfunc="gopage"/> <!-- 페이지 번호 나오게 -->
</body>

</html>