<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
	<%@include file="../Header.jsp" %>		
			<div class="row">
			<div class="col-sm-8 blog-main">
				<%
					FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
					String mail = Sessions.getSessions().verifySession(request.getCookies());
					String event = fye.MS+"event/"+request.getParameter("ev");
					Boolean isMine = fye.eventIsFromAPerson(event, fye.MS+"person/"+mail);
					String[] arr = fye.getInformationOfEvent(event);
					ArrayList<String> people = fye.getAllThePeopleFromAnEvent(event);
				%>
				<h1><%= arr[0] %></h1>
				<img src="<%= arr[1]%>">
				<br><hr>
				<a class="btn btn-default" href="<%= arr[2]%>">Go to web</a>
				<p>People going: <%= people.size() %></p>
			</div>
			<%@include file="../Footer.jsp" %>
</html>