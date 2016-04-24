<%@ page import="followyourevent.*"%><%
			String[] info = FollowyoureventTDB.getFollowyoureventTDB().getInformationAboutAPerson(Sessions.getSessions().verifySession(request.getCookies()));
			boolean imadmin = FollowyoureventTDB.getFollowyoureventTDB().isAdmin(FollowyoureventTDB.MS+"person/"+Sessions.getSessions().verifySession(request.getCookies()));
			%>
			<div class="col-sm-3 col-sm-offset-1">
				<div class="panel panel-default" style="margin-bottom: 0;">
					<div class="panel-heading">
						<h3 class="panel-title">
							<a href="/followyourevent"><%= info!=null?"Hello,  "+info[0]:"Follow Your Event"%></a>
							<a class="pull-right" href="/followyourevent/user"><span class="glyphicon glyphicon-user"></span></a>
							<%
							if (imadmin){
								%><a class="pull-right" href="/followyourevent/user/admin.jsp"><span class="glyphicon glyphicon-star"></span></a><%
							}
							%>
						</h3>
					</div>
					<div class="panel-body">
						<% if (info != null){ %>
						<p>Mail:  <%= info[1]%></p>
						<p>Age:  <%= info[2]%></p>
						<p>Sex:  <%= info[3]%></p>
						<% }else{ %>
							<p>Here you can find those events you want to go!</p>
							<p><a class="btn btn-primary" href="/followyourevent/login" role="button">Sign In</a> <a class="btn btn-default" href="/followyourevent/signup" role="button">Sign Up</a></p>
						<% } %>
					</div>
					<%
						if (info != null){
							%><div class="panel-footer">
								<p><a class="btn btn-primary" href="/followyourevent/places" role="button">Admin place</a>  <a class="btn btn-danger text-right" href="/followyourevent/logout.jsp" role="button">Log Out</a></p>
							</div><%
						}
					%>
				</div>
			</div>
		</div>
  </body>