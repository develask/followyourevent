<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.Year"%>
<!DOCTYPE html>
<html>
	<%@include file="../Header.jsp" %>		
			<div class="row">
			<div class="col-sm-8 blog-main">
				<%
					FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
					String mail2 = Sessions.getSessions().verifySession(request.getCookies());
					String mail = request.getParameter("mail");
					if (mail == null) mail = mail2;
					String person = fye.MS+"person/"+mail;
					if (mail == null){
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", "/followyourevent/login");
					}else{
						String[] infoP = fye.getInformationAboutAPerson(mail);
						%>
							<h1>Hello, <%= infoP[0] %></h1>
							<p><b>Mail:</b> <%= infoP[1] %></p>
							<p><b>Age:</b> <%= infoP[2] %></p>
							<p><b>Sex:</b> <%= infoP[3] %></p>
						<%
						ArrayList<String> past = fye.getAllPastEventsOfAPerson(person);
						ArrayList<String> future = fye.getAllFutureEventsOfAPerson(person);
						%>
						<div class="col-sm-6">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Past Events:</h3>
								</div>
								<%
								if (past.size()==0){
									%>
									<div class="panel-body">
										There is no past events.
									</div>
									<%
								}else{
								%>
								<ul class="list-group">
									<%
									for (String ev: past){
										String[] evInfo = fye.getInformationOfEvent(ev);
										
									%>
									<li class="list-group-item"><%= evInfo[0] %> <a class="btn btn-primary btn-xs pull-right">Go</a></li>
									<%} %>
								</ul>
								<%} %>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="panel panel-default">
								<div class="panel-heading">
									<h3 class="panel-title">Future Events:</h3>
								</div>
								<%
								if (future.size()==0){
									%>
									<div class="panel-body">
										There is no future events.
									</div>
									<%
								}else{
								%>
								<ul class="list-group">
									<%
									for (String ev: future){
										String[] evInfo = fye.getInformationOfEvent(ev);
										
									%>
									<li class="list-group-item"><%= evInfo[0] %> <a class="btn btn-primary btn-xs pull-right">Go</a></li>
									<%} %>
								</ul>
								<%} %>
							</div>
						</div>
						<%
						if (mail == mail2){
							//ventana para cambiar los datos.	
						}
					}
				%>
			</div>
			<%@include file="../Footer.jsp" %>
</html>