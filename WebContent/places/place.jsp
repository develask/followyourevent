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
					String pl = fye.MS+"place/"+request.getParameter("pl");
					ArrayList<String> events = fye.getEventsOfAPlace(pl);
					Boolean isMine = fye.placeOwnerOfAPerson(fye.MS+ "person/" + mail, pl);
					String[] infopl = fye.getInformationOfPlace(pl);
					%>
						<h1>Place: <small><%= infopl[0] %></small></h1>
						<img src="https://maps.googleapis.com/maps/api/staticmap?center=<%= infopl[6] %>,<%= infopl[7] %>&zoom=14&maptype=roadmap&size=900x200&markers=color:red%7C<%= infopl[6] %>,<%= infopl[7] %>">
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
								<tr onclick="document.location = '/followyourevent/event?ev=<%= event.split("/event/")[1]%>';">
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
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title"  role="button" data-toggle="collapse" aria-expanded="false" aria-controls="dropdown2" data-target="#dropdown2">Modify Place</h3>
							</div>
							<div class="panel-body collapse" id="dropdown2">
								<form action="changePlace.jsp?" class="form-horizontal">
									<div class="form-group">
										<label for="placeName" class="col-sm-2">Place Name:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="placeName" placeholder="Place Name" name="name" value="<%= infopl[0] %>">
										</div>
									</div>
									<div class="form-group">
										<label for="placeStreet" class="col-sm-2">Street address:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="placeStreet" placeholder="Street Nº 6" name="street" value="<%= infopl[1] %>">
										</div>
									</div>
									<div class="sr-only">
										<input type="number" id="lat" name="lat" step="any">
										<input type="number" id="lon" name="lon" step="any">
										<input type="text" value="<%= request.getParameter("pl") %>" name="pl">
									</div>
									<div id="map" style="height: 200px; width: 100%; margin-bottom: 20px;"></div>
									<script type="text/javascript">
										// api = AIzaSyAcXuoYJXKQ8P4h2l8zSIqr91UrhbTC83o
										function initMap(){
											$("#dropdown2").removeClass("collapse");
											var myLatlng = {lat: <%= infopl[6] %>, lng: <%= infopl[7] %>};
											
											var map = new google.maps.Map(document.getElementById('map'), {
											    zoom: 14,
											    center: myLatlng
											});
											var marker = new google.maps.Marker({
											    position: myLatlng,
											    map: map,
											    title: 'Position'
											});
											$("#lat").val(myLatlng.lat);
											$("#lon").val(myLatlng.lng);
											
											map.addListener("click", function(ev){
												marker.setPosition(ev.latLng);
												map.panTo(marker.getPosition());
												map.setCenter(marker.getPosition());
												$("#lat").val(ev.latLng.lat());
												$("#lon").val(ev.latLng.lng());
											});
											setTimeout(function(){
												$("#dropdown2").addClass("collapse");
											},500);
										}
									</script>
									<script src="https://maps.googleapis.com/maps/api/js?signed_in=false&callback=initMap" async defer></script>
									<div class="form-group">
										<label for="url" class="col-sm-2">Web URL:</label>
										<div class="col-sm-10">
											<input type="url" class="form-control" id="url" placeholder="http://host.domain/" name="url" value="<%= infopl[4] %>">
										</div>
									</div>
									<div class="form-group">
										<label for="logo" class="col-sm-2">Logo's URL:</label>
										<div class="col-sm-10">
											<input type="url" class="form-control" id="logo" placeholder="http://host.domain/path_to_image.ext" name="logo" value="<%= infopl[2] %>">
										</div>
									</div>
									<div class="form-group">
										<label for="capa" class="col-sm-2">Capacity:</label>
										<div class="col-sm-10">
											<input type="number" class="form-control" id="capa" placeholder="Number" name="capacity" value="<%= infopl[3] %>">
										</div>
									</div>
									<div class="form-group">
										<label for="auto" class="col-sm-2">Page auto:</label>
										<div class="col-sm-10">
											<input type="checkbox" class="" id="auto" placeholder="Number" name="auto" value="<%= infopl[5].equals("No")?"false":"true" %>">
										</div>
									</div>
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