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
					String mail = Sessions.getSessions().verifySession(request.getCookies());
					String ev = request.getParameter("ev");
					String event = fye.MS+"event/"+ev;
					Boolean isMine = fye.eventIsFromAPerson(event, fye.MS+"person/"+mail);
					String[] arr = fye.getInformationOfEvent(event);
					ArrayList<String> people = fye.getAllThePeopleFromAnEvent(event);
					
					if (isMine){
						%>
						<h1 id="name" contenteditable="true"><%= arr[0] %></h1>
						<img id="img" src="<%= arr[1]%>"><br><br>
						<div class="form-group">
							<label for="imgInp" class="col-sm-2">Logo URL:</label>
							<div class="col-sm-10">
								<input type="url" class="form-control" id="imgInp" placeholder="http://host.domain/page" value="<%= arr[1]%>">
							</div>
						</div>
						<br><hr>
						<div class="form-group">
							<a id="goWeb" class="btn btn-default col-sm-2" href="<%= arr[2]%>">Go to web</a>
							
							<div class="col-sm-10">
								<input type="url" class="form-control" id="url" placeholder="http://host.domain/page" value="<%= arr[2]%>">
							</div>
						</div>
						<br>
						<br>
						<div class="form-group">
							<label for="date" class="col-sm-2">Date:</label>
							<div class="col-sm-10 input-group" style="padding-left: 15px; padding-right: 15px;">
								<input type="date" class="form-control" id="date" name="date". value="<%= Year.now().getValue()+"-"+arr[4]+"-"+ arr[3] %>">
								<span class="input-group-addon" id="sizing-addon1"> - </span>
								<input type="time" class="form-control"  name="time" id="time" value="<%= arr[5]%>">
							</div>
						</div>
						<div class="form-group">
							<label for="price" class="col-sm-2">Price:</label>
							<div class="col-sm-10 input-group" style="padding-left: 15px; padding-right: 15px;">
								<input type="number" class="form-control" id="price" placeholder="Number" name="price" value="<%= arr[6]%>">
								<span class="input-group-addon" id="sizing-addon1" >Nok</span>
							</div>
						</div>
						<button id="guardar">Guardar</button>
						<script>
							function getId(id){
								return document.getElementById(id);
							}
							var imgInp = getId("imgInp");
							imgInp.addEventListener("change", function(ev){
								getId("img").setAttribute("src", imgInp.value);
							});
							var url = getId("url");
							url.addEventListener("change", function(ev){
								getId("goWeb").setAttribute("href", url.value);
							});
							var guardar = getId("guardar");
							guardar.addEventListener("click", function(ev){
								var logo = getId("imgInp").value;
								var url = getId("url").value;
								var date = getId("date").value;
								var time = getId("time").value;
								var price = getId("price").value;
								var xhttp = new XMLHttpRequest();
								xhttp.onreadystatechange = function() {
								    if (xhttp.readyState == 4 && xhttp.status == 200) {
								      alert("Saved!");
								    }
								 };
								 xhttp.open("GET", "/followyourevent/event/update?ev="+ev
										 +"&logo="+logo
										 +"&url="+url
										 +"&date="+date
										 +"&time="+time
										 +"&price="+price, true);
								 xhttp.send();
							});
						</script>
						<%
					}else{
						%>
						<h1><%= arr[0] %></h1>
						<img src="<%= arr[1]%>">
						<br><hr>
						<a class="btn btn-default" href="<%= arr[2]%>">Go to web</a>
						<p class="text-right"><%= arr[4]%>/<%= arr[3]%> - <%= arr[5]%></p>
						<p>Just for <%= arr[6]%> Nok</p>
						<%
					}
				%>
				<p>People going: <%= people.size() %></p>
			</div>
			<%@include file="../Footer.jsp" %>
</html>