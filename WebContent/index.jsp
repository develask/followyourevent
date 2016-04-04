<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<!DOCTYPE html>
<html>
	<%@include file="Header.jsp" %>
				<%
					String mail = Sessions.getSessions().verifySession(request.getCookies());
					if (mail == null){
						%>
						<div class="jumbotron">
							<h1>Follow Your Event</h1>
							<p>Here you can find those events you want to go!</p>
							<p><a class="btn btn-primary btn-lg" href="login" role="button">Sign In</a> <a class="btn btn-default btn-lg" href="signup" role="button">Sign Up</a></p>
						</div>
						<%
					}
				%>
			
			<div class="row">
			<div class="col-sm-8 blog-main">
				<%
					if (mail != null){
						ArrayList<String> evs = FollowyoureventTDB.getFollowyoureventTDB().getAllTheEventsOfAPerson(mail);
						for (String ev: evs){
							ArrayList<String> infoEv = FollowyoureventTDB.getInformationOfEvent(ev);
							// name, image, url, day, month, hour, price
							if (infoEv.size() == 7){
							%>
							<div class="col-sm-6">
								<div class="thumbnail">
									<img src="<%= infoEv.get(1)%>" alt="" class="img-rounded">
									<div class="caption">
										<h3><%= infoEv.get(0)%></h3>
										<p><%= infoEv.get(3)%>/<%= infoEv.get(4)%> - <%= infoEv.get(5)%> - <%= infoEv.get(6)%>€</p>
										<p><a href="event/nogo.jsp?event=<%= infoEv.get(0)+infoEv.get(4)+infoEv.get(3)%>" class="btn btn-danger" role="button">Don't go</a></p>
									</div>
								</div>
							</div>
							<% }else{ %><h1><%= ev %></h1><%}
						}
					}
				%>
			</div>
			<%@include file="Footer.jsp" %>
</html>
