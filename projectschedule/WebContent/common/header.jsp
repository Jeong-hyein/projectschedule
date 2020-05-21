<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>project/header.jsp</title>
<style type="text/css">
html {
height: 100%;
}
<!--

#demo-container{padding:25px 15px 0 15px;
  background: -webkit-linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);
  background: linear-gradient(45deg, rgba(66, 183, 245, 0.8) 0%, rgba(66, 245, 189, 0.4) 100%);}

ul#simple-menu{list-style-type:none;width:100%;position:relative;height:27px;font-family:"Trebuchet MS",Arial,sans-serif;font-size:13px;font-weight:bold;margin:0px 0px 0px 0px;padding:11px 0 0 0;}
ul#simple-menu li{display:block;float:left;margin:0 0 0 4px;height:27px;}
ul#simple-menu li.left{margin:0;}
ul#simple-menu li a{display:block;float:left;color:#fff;background:#4285F4;line-height:27px;text-decoration:none;padding:0 17px 0 18px;height:27px;}
ul#simple-menu li a.right{padding-right:19px;}
ul#simple-menu li a:hover{background:#2E4560;}
ul#simple-menu li a.current{color:#2E4560;background:#fff;}
ul#simple-menu li a.current:hover{color:#2E4560;background:#fff;}
ul#simple-menu li.userId {
	margin-top: 2px;
	margin-left: 15px;
	font-size: 15px;
}
-->
</style>
</head>
<body>
<div id="demo-container">

	<ul id="simple-menu">
		<li><a href="Main.do" title="Home">Home</a></li>
		<c:if test="${loginId == null}">
		<li><a href="MemberLogin.do" title="Home">로그인</a></li>
		</c:if>
		<c:if test="${loginId != null}">
		<li><a href="MemberLogout.do" title="Home">로그아웃</a></li>
		<li><a href="MemberUpdate.do" title="Home">정보수정</a></li>
		<li><a href="Schedule.do" title="Home">일정</a></li>
		</c:if>
		<li><a href="BoardList.do" title="Home">게시판</a></li>
		<c:if test="${loginId == 'admin'}">
		<li><a href="MemberList.do" title="Home">회원목록</a></li>
		</c:if>
		<li class = "userId">${sessionScope.loginId} (${sessionScope.loginMember.name})님</li>
	</ul>
</div>