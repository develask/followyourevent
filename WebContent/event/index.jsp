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
					boolean isMine = fye.eventIsFromAPerson(event, fye.MS+"person/"+mail);
					boolean go = fye.PersonAssist(fye.MS+"person/"+mail, event);
					String[] arr = fye.getInformationOfEvent(event);
					ArrayList<String> people = fye.getAllThePeopleFromAnEvent(event);
					String place = fye.getPlaceOfAnEvent(event);
					if (isMine){
						%>
						<h1>
							<span id="name" contenteditable="true"><%= arr[0] %></span>
							<a href="/followyourevent/event/<%= go?"nogo":"go" %>.jsp?event=<%= ev %>&from=/followyourevent/event?ev=<%= ev %>" class="btn btn-<%= go?"danger":"success" %> pull-right"><%= go?"Don't Go":"Go" %></a>
						</h1>
						<img id="img" src="<%= arr[1]%>" style="max-width: 100%;"><br><br>
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
								<input type="date" class="form-control" id="date" name="date". value="<%= Year.now().getValue()+"-"+(arr[4].length()<2?"0":"")+arr[4]+"-"+ (arr[3].length()<2?"0":"")+arr[3] %>">
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
								var name = getId("name").innerHTML;
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
								 xhttp.open("GET", "/followyourevent/event/update.jsp?ev=<%= ev %>"
										 +"&name="+name
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
						<h1>
							<span><%= arr[0] %></span>
							<a href="/followyourevent/event/<%= go?"nogo":"go" %>.jsp?event=<%= ev %>&from=/followyourevent/event?ev=<%= ev %>" class="btn btn-<%= go?"danger":"success" %> pull-right"><%= go?"Don't Go":"Go" %></a>
						</h1>
						<img src="<%= arr[1]%>">
						<br><hr>
						<a class="btn btn-default" href="<%= arr[2]%>">Go to web</a>
						<p class="text-right"><%= arr[4]%>/<%= arr[3]%> - <%= arr[5]%></p>
						<p>Just for <%= arr[6]%> Nok</p>
						<%
					}
				%>
				<hr>
				<a class="btn btn-default" href="/followyourevent/places/place.jsp?pl=<%= place.split("/place/")[1]%>">Place: <%= fye.getInformationOfPlace(place)[0] %></a>
				<hr>
				<h3>People going: <%= people.size() %></h3>
				<ul class="list-group">
					<% for(String p: people){ String[] i = fye.getInformationAboutAPerson(p.split("/person/")[1]); %>
					<a href="/followyourevent/user?mail=<%= i[1] %>" class="list-group-item"><%= i[0] %></a>
					<% } %>
				</ul>
			</div>
			<%@include file="../Footer.jsp" %>
</html>