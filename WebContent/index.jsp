<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
	<%@include file="Header.jsp" %>
				<%
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
					ArrayList<String[]> evs = new ArrayList<String[]>();
					ArrayList<String> evs1 = null;
					if (lat != null && lon != null){
						
						Calendar cal = Calendar.getInstance();
						Date now = cal.getTime();
						cal.setTime(now);
						String day = ""+cal.get(Calendar.DAY_OF_MONTH);
						String month = ""+(cal.get(Calendar.MONTH) + 1);
						if (day.length()==1) day = "0"+day;
						if (month.length()==1) month = "0"+month;
						String fechaInicio = cal.get(Calendar.YEAR)+"-"+month+"-"+day;
						//año-mes-dia
						cal = Calendar.getInstance();
						String[] s = fechaInicio.split("-");
						cal.set(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
						cal.add(Calendar.DAY_OF_YEAR, 7);
						day = ""+cal.get(Calendar.DAY_OF_MONTH);
						month = ""+(cal.get(Calendar.MONTH) + 1);
						if (day.length()==1) day = "0"+day;
						if (month.length()==1) month = "0"+month;
						String fechaFin = cal.get(Calendar.YEAR)+"-"+month+"-"+day;

						
						evs1 = fye.getActualEventsNearToYou(Double.parseDouble(lat), Double.parseDouble(lon), 0.3, fechaInicio.split("-")[1], fechaInicio.split("-")[2], fechaFin.split("-")[1], fechaFin.split("-")[2]);
					}else{
						evs1 = fye.getActualEvents();
					}
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
