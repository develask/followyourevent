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
					String mail2 = Sessions.getSessions().verifySession(request.getCookies());
					String mail1 = request.getParameter("mail");
					mail = mail1;
					if (mail == null) mail = mail2;
					String me = fye.MS+"person/"+mail2;
					String person = fye.MS+"person/"+mail;
					if (mail == null){
						response.setStatus(response.SC_MOVED_TEMPORARILY);
						response.setHeader("Location", "/followyourevent/login");
					}else{
						String[] infoP = fye.getInformationAboutAPerson(mail);
						%>
							<h1><%= infoP[0] %></h1>
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
										Boolean go = fye.PersonAssist(me, ev);
									%>
									<li class="list-group-item"><a href="/followyourevent/event?ev=<%= ev.split("/event/")[1] %>"><%= evInfo[0] %></a> <span style="color: <%= go?"green":"red" %>;" class="glyphicon glyphicon-<%= go?"ok":"remove" %>-circle pull-right"></span></li>
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
										Boolean go = fye.PersonAssist(me, ev);
									%>
									<li class="list-group-item"><a href="/followyourevent/event?ev=<%= ev.split("/event/")[1] %>"><%= evInfo[0] %></a> <a href="/followyourevent/event/<%= go?"nogo":"go" %>.jsp?event=<%= ev.split("/event/")[1] %>&from=/followyourevent/user<%= (mail1!=null)?"?mail="+mail:"" %>" class="btn btn-<%= go?"danger":"success" %> btn-xs pull-right"><%= go?"Don't Go":"Go" %></a></li>
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