<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
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
					FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
					ArrayList<String[]> evs = new ArrayList<String[]>();
					ArrayList<String> evs1 = fye.getActualEvents();
					if (mail != null) evs1.addAll(fye.recommendEvents(fye.MS+"person/"+mail));
					int index = -1;
					String s;
					while(evs1.size() != ++index){
						s = evs1.get(index);
						if (evs1.indexOf(s) != evs1.lastIndexOf(s)){
							evs1.remove(index);
							index--;
						}
					}
					for (String ev: evs1){
						String[] infoEv = fye.getInformationOfEvent(ev);
						String place = fye.getPlaceOfAnEvent(ev);
						String[] placeInfo = fye.getInformationOfPlace(place);
						boolean asist = fye.PersonAssist(fye.MS+"person/"+mail, ev);
						// name, image, url, day, month, hour, price
						if (infoEv.length == 7){
						%>	
						<div class="col-sm-6">
							<div class="thumbnail">
								<img src="<%= infoEv[1]%>" alt="" class="img-rounded">
								<div class="caption">
									<h3><%= infoEv[0]%></h3>
									<h5><%= placeInfo[0] %> (<%= placeInfo[3]%> capacity)</h5>
									<p><%= infoEv[3]%>/<%= infoEv[4]%> - <%= infoEv[5]%> - <%= infoEv[6]%>€</p>
									<p><a href="event?ev=<%= ev.split("/event/")[1]%>" class="btn btn-default" role="button">View</a>
									<a href="<%= (asist?"event/nogo.jsp?event=":"event/go.jsp?event=")+ev.split("/event/")[1] %>" class="btn <%= asist?"btn-danger":"btn-success" %>" role="button"><%= asist?"Don't go":"Go!" %></a></p>
								</div>
							</div>
						</div>
						<% }else{ %><h1><%= ev %></h1><%}
					}
				%>
			</div>
			<%@include file="Footer.jsp" %>
</html>
