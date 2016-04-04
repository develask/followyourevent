<%@ page import="followyourevent.*"%><%
			String[] info = FollowyoureventTDB.getFollowyoureventTDB().getInformationAboutAPerson(Sessions.getSessions().verifySession(request.getCookies()));
			%>
			<div class="col-sm-3 col-sm-offset-1">
				<div class="panel panel-default" style="margin-bottom: 0;">
					<div class="panel-heading">
						<h3 class="panel-title"><%= info!=null?"Hello,  "+info[0]:"Follow Your Event"%></h3>
					</div>
					<div class="panel-body">
						<% if (info != null){ %>
						<p>Mail:  <%= info[1]%></p>
						<p>Age:  <%= info[2]%></p>
						<p>Sex:  <%= info[3]%></p>
						<% }else{ %>
							<p>Here you can find those events you want to go!</p>
							<p><a class="btn btn-primary" href="login" role="button">Sign In</a> <a class="btn btn-default" href="signup" role="button">Sign Up</a></p>
						<% } %>
					</div>
					<%
						if (info != null){
							%><div class="panel-footer">
								<p><a class="btn btn-primary" href="places" role="button">Admin place</a>  <a class="btn btn-danger text-right" href="logout.jsp" role="button">Log Out</a></p>
							</div><%
						}
					%>
				</div>
			</div>
		</div>

		<!-- jQuery 2.1.4 -->
		<script src="js/jQuery-2.1.4.min.js"></script>

		<!-- Bootstrap 3.3.5 -->
		<script src="js/bootstrap.min.js"></script>

		<!-- Angular -->
		<!-- script type="text/javascript" src="js/angular.min.js"></script>
		<script src="js/angular-route.min.js"></script -->

		<!-- Own js -->
		<!-- script src="js/index.js"></script -->
  </body>