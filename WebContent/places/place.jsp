<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<!DOCTYPE html>
<html>
	<%@include file="../Header.jsp" %>		
			<div class="row">
			<div class="col-sm-8 blog-main">
				<%
					FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
					String mail = Sessions.getSessions().verifySession(request.getCookies());
					String pl = fye.MS+"place/"+request.getParameter("pl");
					ArrayList<String> events = fye.getEventsOfAPlace(pl);
					Boolean isMine = mail != null;
					%>
						<h1>Place: <small><%= fye.getInformationOfPlace(pl)[0] %></small></h1>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Date</th>
									<th>Price</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (String event: events){
									String[] arr = fye.getInformationOfEvent(event);
								%>
								<tr onclick="document.location = '/followyourevent/event?ev=<%= (arr[0]+arr[4]+arr[3]).replaceAll(" ", "")%>';">
									<th scope="row"><%= events.indexOf(event)%></th>
									<td><%= arr[0]%></td>
									<td><%= arr[4]+"/"+arr[3]+" - "+arr[5]%></td>
									<td><%= arr[6]%> nok</td>
								</tr>
								<%}%>
							</tbody>
						</table>
						<%
					if (isMine){
						%>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title" role="button" data-toggle="collapse" aria-expanded="false" aria-controls="dropdown" data-target="#dropdown">Create New Event</h3>
							</div>
							<div class="panel-body collapse" id="dropdown">
								<form action="/followyourevent/event/newEvent.jsp" class="form-horizontal">
									<div class="form-group">
										<label for="eventName" class="col-sm-2">Event Name:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="eventName" placeholder="Event Name" name="name">
										</div>
									</div>
									<div class="form-group">
										<label for="logo" class="col-sm-2">Logo's URL:</label>
										<div class="col-sm-10">
											<input type="url" class="form-control" id="logo" placeholder="http://host.domain/path_to_image.ext" name="logo">
										</div>
									</div>
									<div class="form-group">
										<label for="url" class="col-sm-2">Page URL:</label>
										<div class="col-sm-10">
											<input type="url" class="form-control" id="url" placeholder="http://host.domain/page" name="url">
										</div>
									</div>
									<div class="form-group">
										<label for="date" class="col-sm-2">Date:</label>
										<div class="col-sm-10 input-group" style="padding-left: 15px; padding-right: 15px;">
											<input type="date" class="form-control" id="date" name="date">
											<span class="input-group-addon" id="sizing-addon1"> - </span>
											<input type="time" class="form-control"  name="time">
										</div>
									</div>
									<div class="form-group">
										<label for="price" class="col-sm-2">Price:</label>
										<div class="col-sm-10 input-group" style="padding-left: 15px; padding-right: 15px;">
											<input type="number" class="form-control" id="price" placeholder="Number" name="price">
											<span class="input-group-addon" id="sizing-addon1">Nok</span>
										</div>
									</div>
									<input class="hide" value="<%= pl%>" name="place">
									<div class="form-group">
										<div class="col-sm-offset-2 col-sm-10">
											<button type="submit" class="btn btn-default">Create</button>
										</div>
									</div>
								</form>
							</div>
						</div>
						<%
					}
				%>
			</div>
			<%@include file="../Footer.jsp" %>
</html>