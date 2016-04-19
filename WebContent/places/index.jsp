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
					if (mail != null){
						ArrayList<String> places = fye.getAllThePlacesOfAPerson(mail);
						%>
						<h1>Your places:</h1>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Street</th>
									<th>Url</th>
									<th>Capacity</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (String place: places){
									String[] arr = fye.getInformationOfPlace(place);
								%>
								<tr onclick="document.location = '/followyourevent/places/place.jsp?pl=<%= place.split("/place/")[1]%>';">
									<th scope="row"><%= places.indexOf(place)%></th>
									<td><%= arr[0]%></td>
									<td><%= arr[1]%></td>
									<td><%= arr[4]%></td>
									<td><%= arr[3]%></td>
								</tr>
								<%}%>
							</tbody>
						</table>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title"  role="button" data-toggle="collapse" aria-expanded="false" aria-controls="dropdown" data-target="#dropdown">Create New Place</h3>
							</div>
							<div class="panel-body collapse" id="dropdown">
								<form action="newPlace.jsp" class="form-horizontal">
									<div class="form-group">
										<label for="placeName" class="col-sm-2">Place Name:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="placeName" placeholder="Place Name" name="name">
										</div>
									</div>
									<div class="form-group">
										<label for="placeStreet" class="col-sm-2">Street address:</label>
										<div class="col-sm-10">
											<input type="text" class="form-control" id="placeStreet" placeholder="Street Nº 6" name="street">
										</div>
									</div>
									<div class="sr-only">
										<input type="number" id="lat" name="lat" step="any">
										<input type="number" id="lon" name="lon" step="any">
									</div>
									<div id="map" style="height: 200px; width: 100%; margin-bottom: 20px;"></div>
									<script type="text/javascript">
										// api = AIzaSyAcXuoYJXKQ8P4h2l8zSIqr91UrhbTC83o
										function initMap(){
											$("#dropdown").removeClass("collapse");
											var myLatlng = {lat: parseFloat(latitude), lng: (longitude)};
											
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
											needPosition.push(function(lat, lon){
												var myLatlng = {lat: parseFloat(lat), lng: parseFloat(lon)};
												marker.setPosition(myLatlng);
												map.panTo(marker.getPosition());
												map.setCenter(marker.getPosition());
												$("#lat").val(myLatlng.lat);
												$("#lon").val(myLatlng.lng);
												$("#dropdown").addClass("collapse");
											});
											
											map.addListener("click", function(ev){
												marker.setPosition(ev.latLng);
												map.panTo(marker.getPosition());
												map.setCenter(marker.getPosition());
												$("#lat").val(ev.latLng.lat());
												$("#lon").val(ev.latLng.lng());
											});
										}
									</script>
									<script src="https://maps.googleapis.com/maps/api/js?signed_in=false&callback=initMap" async defer></script>
									<div class="form-group">
										<label for="url" class="col-sm-2">Web URL:</label>
										<div class="col-sm-10">
											<input type="url" class="form-control" id="url" placeholder="http://host.domain/" name="url">
										</div>
									</div>
									<div class="form-group">
										<label for="logo" class="col-sm-2">Logo's URL:</label>
										<div class="col-sm-10">
											<input type="url" class="form-control" id="logo" placeholder="http://host.domain/path_to_image.ext" name="logo">
										</div>
									</div>
									<div class="form-group">
										<label for="capa" class="col-sm-2">Capacity:</label>
										<div class="col-sm-10">
											<input type="number" class="form-control" id="capa" placeholder="Number" name="capacity">
										</div>
									</div>
									<div class="form-group">
										<label for="auto" class="col-sm-2">Page auto:</label>
										<div class="col-sm-10">
											<input type="checkbox" class="" id="auto" placeholder="Number" name="auto">
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