<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
 <html> 
 <head profile="http://www.w3.org/2005/10/profile"> 
 <meta charset='utf-8' /> 
 <link rel="icon" type="image/png" href="http://example.com/myicon.png"/> 
 <link href='fullcal/fullcalendar.min.css' rel='stylesheet' />
  <link href='fullcal/fullcalendar.print.min.css' rel='stylesheet' media='print' /> 
  <script src='fullcal/lib/moment.min.js'></script> 
  <script src='fullcal/lib/jquery.min.js'></script>
   <script src='fullcal/fullcalendar.min.js'></script>
    <script> $(document).ready(function() { $('#calendar').fullCalendar({ header: { left: 'prev,next today', center: 'title', right: 'month,basicWeek,basicDay' }, defaultDate: new Date(),
    	navLinks: true, // can click day/week names to navigate views editable: false,
    	eventLimit: true, // allow "more" link when too many events 
    	events: [ { title: 'All Day Event', start: '2020-05-26' },{ title: '혜인 캘린더', start: '2020-05-26' },
    		{ title: '캬캬캬캬캬캬캬', start: '2020-05-26' },{ title: 'test', start: '2020-05-26' },{ title: '현우바보', start: '2020-05-26' },
    		{ title: '하하하하ㅏㅎ', start: '2020-05-26' },
    		{ title: 'Long Event', start: '2020-05-01', end: '2020-05-10' }, 
    		{ id: 999, title: 'Repeating Event', start: '2017-04-09T16:00:00' }, 
    		] }); });
    </script> 
    <style> 
    body { margin: 40px 10px; padding: 0; font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif; font-size: 14px; } 
    #calendar { max-width: 900px; margin: 0 auto; } </style> 
    </head> 
    <body> 
    <div id='calendar'></div> 
    </body> 
    </html>
