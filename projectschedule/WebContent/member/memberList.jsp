<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.pagination{width: 800px; margin: 30px auto;}
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

</style>
<title>project/memberList.jsp</title>
<jsp:include page ="/common/header.jsp" />
<div class="title" style="margin-left:45.5%; margin-top:50px;">
<h3>회원목록</h3>
</div>
<div style="margin-left: 40%; margin-top:30px;">
<form name="searchfrm"> <!-- action 없으면 조건을 가지고 현재페이지 다시 부르는거. -->
	<input name="p" value="1" type="hidden">
	이름: <input name="name">
	<button>검색</button>
</form><br>
</div>
<div class="table" style="margin-left: 28%;">
	<table border="1" id="hor-minimalist-a" summary="Employee Pay Sheet">
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
</div>
<script>
function gopage(p) { //document.searchfrm하면 searchfrm을 찾아간다.
		document.searchfrm.p.value = p; //폼에 p값을 담는다.
		document.searchfrm.submit(); //폼 전체를 전송
	}
</script>
<my:paging paging="${paging}" jsfunc="gopage"/>

</body>

</html>