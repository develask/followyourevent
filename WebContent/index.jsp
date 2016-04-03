<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Follow Your Event</title>

		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

		<!-- Bootstrap 3.3.5 -->
		<link rel="stylesheet" href="css/bootstrap.min.css">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
	</head>
	<body>

		<div class="container">
			<div class="jumbotron">
				<%
					String mail = Sessions.getSessions().verifySession(request.getCookies());
					if (mail != null){
						String[] info = FollowyoureventTDB.getFollowyoureventTDB().getInformationAboutAPerson(mail);
						%><h1>Hello,  <%= info[0]%></h1>
						<p>Mail:  <%= info[1]%></p>
						<p>Age:  <%= info[2]%></p>
						<p>Sex:  <%= info[3]%></p>
						<p><a class="btn btn-danger btn-lg" href="logout.jsp" role="button">Log Out</a></p>
						<%
					}else{
						%>
						<h1>Follow Your Event</h1>
						<p>Here you can find those events you want to go!</p>
						<p><a class="btn btn-primary btn-lg" href="login" role="button">Sign In</a> <a class="btn btn-default btn-lg" href="signup" role="button">Sign Up</a></p>
						<%
					}
				%>
			</div>

			<div class="row">
				<%
					if (mail != null){
						ArrayList<String> evs = FollowyoureventTDB.getFollowyoureventTDB().getAllTheEventsOfAPerson(mail);
						for (String ev: evs){
							ArrayList<String> infoEv = FollowyoureventTDB.getInformationOfEvent(ev);
							// name, image, url, day, month, hour, price
							%>
							<div class="col-sm-6 col-md-4">
								<div class="thumbnail">
									<img src="<%= infoEv.get(1)%>" alt="">
									<div class="caption">
										<h3><%= infoEv.get(0)%></h3>
										<p><%= infoEv.get(3)%>/<%= infoEv.get(4)%> - <%= infoEv.get(5)%> - <%= infoEv.get(6)%>€</p>
										<p><a href="#" class="btn btn-primary" role="button">Interest In</a> <a href="#" class="btn btn-default" role="button">I'll go</a></p>
									</div>
								</div>
							</div>
							<%
						}
					}
				%>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="" alt="">
						<div class="caption">
							<h3>Lorem ipsum dolor.</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eum, quae!</p>
							<p><a href="#" class="btn btn-primary" role="button">Interest In</a> <a href="#" class="btn btn-default" role="button">I'll go</a></p>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="thumbnail">
						<img src="" alt="">
						<div class="caption">
							<h3>Lorem ipsum.</h3>
							<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem, harum.</p>
							<p><a href="#" class="btn btn-primary" role="button">Interest In</a> <a href="#" class="btn btn-default" role="button">I'll go</a></p>
						</div>
					</div>
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
</html>
