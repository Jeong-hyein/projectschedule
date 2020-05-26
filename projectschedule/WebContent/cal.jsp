<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>


<script type="text/javascript" src="<c:url value = "/fullcal/lib/moment.min.js" />"></script>
<script type="text/javascript" src="<c:url value = "/fullcal/lib/fullcalendar.js" />"></script>


<link rel="stylesheet" type="text/css" href="<c:url value="/fullcal/fullcalendar.css"/>">
<link rel="stylesheet" type="text/css" href="<c:url value="/fullcal/fullcalendar.min.css"/>">
<link rel="stylesheet" media="print" type="text/css" href="<c:url value="/fullcal/fullcalendar.pint.min.css"/>">

<title></title>
</head>
<body>
<div style="float:center; width:300px;" id="calendar"></div>

<script>
$(document).ready(function() {
	$("#calendar").fullCalendar({
		defaultDate : "2020-05-26"
		, editable : true
		, eventLimit : true
		, events : [
			{
				title : "Long Event"
				, start : "2020-05-22"
				, end : "2020-05-25"
			},
		]
	});
});

</script>
</body>
</html>