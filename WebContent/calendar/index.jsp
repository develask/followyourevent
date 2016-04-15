<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<!DOCTYPE html>
<html>
	<%@include file="../Header.jsp" %>
	
			<link href='../css/fullcalendar.css' rel='stylesheet' />
			<link href='../css/fullcalendar.print.css' rel='stylesheet' media='print' />
			<script src='../js/moment.min.js'></script>
			<script src='../js/fullcalendar.min.js'></script>
			
			<div class="col-sm-8 blog-main">
				<div id='calendar'></div>
				<script>
				<%
				String mail = Sessions.getSessions().verifySession(request.getCookies());
				FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();

				String startMonth = request.getParameter("startMonth");
				String startDay = request.getParameter("startDay");
				String endMonth = request.getParameter("endMonth");
				String endDay = request.getParameter("endDay");

				Calendar cal = Calendar.getInstance();
				Date now = cal.getTime();
				cal.setTime(now);
				int year = cal.get(Calendar.YEAR);
				if (startMonth == null) startMonth = ""+(cal.get(Calendar.MONTH) + 1);
				if (startDay == null) startDay = "01";
				if (endMonth == null) endMonth = startMonth;
				if (endDay == null) endDay = "31";
%>
				$(document).ready(function() {

					$('#calendar').fullCalendar({
						defaultDate: '<%= year %>-<%= Integer.parseInt(startMonth)+1 %>-<%= startDay %>',
						editable: true,
						eventLimit: true, // allow "more" link when too many events
						events: [
<%

ArrayList<String> evs = fye.getEventsBetweenDates(startMonth, startDay, endMonth, endDay);

for (String ev: evs){
	// name, image, url, day, month, hour, price
	String[] evI = fye.getInformationOfEvent(ev);
	boolean as = fye.PersonAssist(fye.MS+"person/"+mail, ev);
	System.out.println(year+"-"+evI[4]+"-"+evI[3]);
	%>{
			title: '<%= evI[0] %>',
			url: '/followyourevent/event?ev=<%= ev.split("/event/")[1] %>',
			start: '<%= year %>-<%= evI[4] %>-<%= evI[3] %>',
			color: '<%= as?"green":"red" %>'
		}<%
}
%>
						]
					});
					
				});
				</script>
			</div>
			<%@include file="../Footer.jsp" %>
</html>
