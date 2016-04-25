<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.Year"%>
<!DOCTYPE html>
<html>
	<%@include file="../Header.jsp" %>		
			<div class="row">
			<%
						ArrayList<String> admin = fye.getAdminList();
			if (!fye.isAdmin(fye.MS+"person/"+mail)){
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "/followyourevent/");
			}
			%>
			<div class="col-sm-8 blog-main">
				<div class="col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Admin users:</h3>
						</div>
						<%
						if (admin.size()==0){
							%>
							<div class="panel-body">
								There is no user with admin privileges.
							</div>
							<%
						}else{
						%>
						<ul class="list-group">
							<%
							for (String us: admin){
								String[] userInfo = fye.getInformationAboutAPerson(us.split("/person/")[1]);
							%>
							<li class="list-group-item"><a target="_blank" href="/followyourevent/user?mail=<%= us.split("/person/")[1] %>"><%= userInfo[0] %></a> <a href="/followyourevent/user/toggleAdmin.jsp?mail=<%= us.split("/person/")[1] %>" class="btn btn-danger btn-xs pull-right"><span class="glyphicon glyphicon-remove"></span></a></li>
							<%} %>
						</ul>
						<%} %>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Find users:</h3>
						</div>
						<div class="panel-body">
							<input type="text" id="inp">
							<button id="search">Search</button>
						</div>
						<ul class="list-group" id="listUsers">
						</ul>
						<script>
							var b = document.getElementById("search");
							b.addEventListener("click", function(ev){
								var inp = document.getElementById("inp").value;
								var lista = document.getElementById("listUsers");
								lista.innerHTML = "";
								$.ajax({
							        url: '/followyourevent/user/findByName.jsp',
							        type: 'GET',
							        data: { name: inp} ,
							        contentType: 'application/json; charset=utf-8',
							        success: function (response) {
							        	response = JSON.parse(response);
							            for (var ind in response){
							            	if (!response[ind].isAdmin){
							            		var li = document.createElement("li");
							            		li.classList.add("list-group-item");
							            		var a = document.createElement("a");
							            		a.setAttribute("href", "/followyourevent/user/?mail="+response[ind].id.split("/person/")[1]);
							            		a.setAttribute("target", "_blank");
							            		a.innerHTML = response[ind].name;
							            		var a2 = document.createElement("a");
							            		a2.classList.add("btn");
							            		a2.classList.add("btn-success");
							            		a2.classList.add("btn-xs");
							            		a2.classList.add("pull-right");
							            		var span = document.createElement("span");
							            		span.classList.add("glyphicon");
							            		span.classList.add("glyphicon-plus");
							            		a2.appendChild(span);
							            		a2.setAttribute("href", "/followyourevent/user/toggleAdmin.jsp?mail="+response[ind].id.split("/person/")[1]);
							            		li.appendChild(a);
							            		li.appendChild(a2);
							            		lista.appendChild(li);
							            	}
							            }
							        },
							        error: function () {
							            alert("error");
							        }
							    }); 
							});
						</script>
					</div>
				</div>
				<%
					ArrayList<String> miLista = fye.getWantedAutomaticPlaces();
				%>
				<div class="col-sm-12">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">Places:</h3>
						</div>
						<ul class="list-group" id="listUsers">
							<%
								for(String pl: miLista){
									// name, street, logo, capacity, url, auto, lat, long
									String[] info = fye.getInformationOfPlace(pl);
									%><li class="list-group-item"><%= info[0] %> - <a target="_blank" href="<%= info[4] %>"><%= info[4] %></a>
										<div class="pull-right">
											<a href="/followyourevent/places/changeAuto.jsp?pl=<%= pl.split("/place/")[1] %>&val=Want">
												<span class="glyphicon glyphicon-question-sign" <%= info[5].equals("Want")?"style=\"color: blue;\"":"style=\"color: black;\"" %>></span>
											</a>
											<a href="/followyourevent/places/changeAuto.jsp?pl=<%= pl.split("/place/")[1] %>&val=Yes">
												<span class="glyphicon glyphicon-ok" <%= info[5].equals("Yes")?"style=\"color: green;\"":"style=\"color: black;\"" %>></span>
											</a>
											<a href="/followyourevent/places/changeAuto.jsp?pl=<%= pl.split("/place/")[1] %>&val=No">
												<span class="glyphicon glyphicon-remove" style="color: black;"></span>
											</a>
										</div>
									</li><%
								}
							%>
						</ul>
					</div>
				</div>
			</div>
			<%@include file="../Footer.jsp" %>
</html>