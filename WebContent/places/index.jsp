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
					if (mail != null){
						ArrayList<String> places = fye.getAllThePlacesOfAPerson(mail);
						%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Street</th>
									<th>Capacity</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (String place: places){
									String[] arr = fye.getInformationOfPlace(place);
								%>
								<tr>
									<th scope="row"><a href="/followyourevent/places/place.jsp?pl=<%= fye.MS+"place/"+(arr[0]+arr[1]).replaceAll(" ", "")%>"><%= places.indexOf(place)%></a></th>
									<td><%= arr[0]%></td>
									<td><%= arr[1]%></td>
									<td><%= arr[3]%></td>
								</tr>
								<%}%>
							</tbody>
						</table>
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Create New Place</h3>
							</div>
							<div class="panel-body">
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