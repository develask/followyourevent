<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
	<%@include file="../Header.jsp" %>		
			<div class="row">
			<div class="col-sm-8 blog-main">
				<h1>Searching by place.</h1>
				<hr>
				<%
				if (request.getParameter("lat")!=null) lat = request.getParameter("lat");
				if (request.getParameter("lng")!=null) lon = request.getParameter("lng");
				Double dist = request.getParameter("dist")!=null?Double.parseDouble(request.getParameter("dist")):0.3;
				
				String fechaInicio = request.getParameter("datestart");
				if (fechaInicio==null || fechaInicio.equals("")){
					//año-mes-dia
					Calendar cal = Calendar.getInstance();
					Date now = cal.getTime();
					cal.setTime(now);
					String day = ""+cal.get(Calendar.DAY_OF_MONTH);
					String month = ""+(cal.get(Calendar.MONTH) + 1);
					if (day.length()==1) day = "0"+day;
					if (month.length()==1) month = "0"+month;
					fechaInicio = cal.get(Calendar.YEAR)+"-"+month+"-"+day;
				}
				
				String fechaFin = request.getParameter("dateend");
				if (fechaFin==null || fechaFin.equals("")){
					//año-mes-dia
					Calendar cal = Calendar.getInstance();
					String[] s = fechaInicio.split("-");
					cal.set(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
					cal.add(Calendar.DAY_OF_YEAR, 7);
					String day = ""+cal.get(Calendar.DAY_OF_MONTH);
					String month = ""+(cal.get(Calendar.MONTH) + 1);
					if (day.length()==1) day = "0"+day;
					if (month.length()==1) month = "0"+month;
					fechaFin = cal.get(Calendar.YEAR)+"-"+month+"-"+day;
				}
				
				%>
				<form action="search.jsp">
					<div class="form-group">
						<label for="date" class="col-sm-2">Start date:</label>
						<div class="col-sm-10 input-group" style="padding-left: 15px; padding-right: 15px;">
							<input type="date" class="form-control" id="date" name="datestart" value="<%= fechaInicio %>">
						</div>
					</div>
					<div class="form-group">
						<label for="date2" class="col-sm-2">End date:</label>
						<div class="col-sm-10 input-group" style="padding-left: 15px; padding-right: 15px;">
							<input type="date" class="form-control" id="date2" name="dateend" value="<%= fechaFin %>">
						</div>
					</div>
					<div class="form-group">
						<label for="dist" class="col-sm-2">Distance:</label>
						<div class="col-sm-10 input-group" style="padding-left: 15px; padding-right: 15px;">
							<input type="range" min="0.02" max="1" step="0.02" value="<%= dist %>" name="dist">
						</div>
					</div>
					<div class="sr-only">
						<input type="text" name="lat" id="lat">
						<input type="text" name="lng" id="lng">
					</div>
					<div class="form-group">
						<label class="col-sm-2">Center:</label>
					</div>
					<div id="map" style="height: 400px; width: 100%; margin-bottom: 20px;"></div>
					<script type="text/javascript">
						// api = AIzaSyAcXuoYJXKQ8P4h2l8zSIqr91UrhbTC83o
						function initMap(){
							$("#dropdown2").removeClass("collapse");
							var myLatlng = {lat: <%= lat %>, lng: <%= lon %>};
							
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
							$("#lng").val(myLatlng.lng);
							
							map.addListener("click", function(ev){
								marker.setPosition(ev.latLng);
								map.panTo(marker.getPosition());
								map.setCenter(marker.getPosition());
								$("#lat").val(ev.latLng.lat());
								$("#lng").val(ev.latLng.lng());
							});
							setTimeout(function(){
								$("#dropdown2").addClass("collapse");
							},500);
						}
					</script>
					<script src="https://maps.googleapis.com/maps/api/js?signed_in=false&callback=initMap" async defer></script>
					<input type="submit" value="Find">
				</form>
			</div>
			<%@include file="../Footer.jsp" %>
</html>