<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>project/header.jsp</title>
<style>
ul {
  list-style-type: none;
  margin: 0;
  padding: 0;
  overflow: hidden;
  background-color: #333;
}

li {
  float: left;
}
li.login {  
display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a {
  display: block;
  color: white;
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}

li a:hover:not(.active) {
  background-color: #111;
}

.active {
  background-color: #4CAF50;
}
</style>
</head>
<body>
<ul>
  <li class="login">${sessionScope.loginId} (${sessionScope.loginMember.name})님</li>
  <li><a href="#home">로그인</a></li>
  <li><a href="#news">로그아웃</a></li>
  <li><a href="#contact">게시판</a></li>
  <li><a href="#contact">정보수정</a></li>
  <li><a href="#contact">일정</a></li>
</ul>
</body>
</html>