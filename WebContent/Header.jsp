<%@ page import="followyourevent.*"%><head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Follow Your Event</title>

		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

		<!-- Bootstrap 3.3.5 -->
		<link rel="stylesheet" href="/followyourevent/css/bootstrap.min.css">

		<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
		<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
		<!--[if lt IE 9]>
		    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
		
		<!-- jQuery 2.1.4 -->
		<script src="/followyourevent/js/jQuery-2.1.4.min.js"></script>

		<!-- Bootstrap 3.3.5 -->
		<script src="/followyourevent/js/bootstrap.min.js"></script>

		<!-- Angular -->
		<!-- script type="text/javascript" src="js/angular.min.js"></script>
		<script src="js/angular-route.min.js"></script -->

		<!-- Own js -->
		<!-- script src="js/index.js"></script -->
		<%
			Cookie[] cookies = request.getCookies();
			String mail = Sessions.getSessions().verifySession(cookies);
			FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
			String lat = Sessions.getCookie(cookies, "lat");
			String lon = Sessions.getCookie(cookies, "lon");
		%>
		
		<script>
			var latitude = -25.363, longitude = 131.044;
			var needPosition = [];
			function geoFindMe() {
	
				  if (!navigator.geolocation){
				    console.log("Geolocation is not supported by your browser.");
				    return;
				  }
	
				  function success(position) {
					  function format(num){
					    	return (""+num).substr(0, (""+num).indexOf(".")+4);
					    }
					  
				    latitude  = format(position.coords.latitude);
				    longitude = format(position.coords.longitude);
				    
				    var c = document.cookie.split(":");
				    var change = false;
				    var existen = false;
				    for (var el in c){
				    	var p = c[el].split("=");
				    	if (p[0].trim() == "lat" || p[0].trim() == "lon"){
				    		existen = true;
				    	}
				    	if ((p[0].trim() == "lat" && p[1]==latitude) || (p[0].trim() == "lon" && p[1]==longitude)){
				    		change = true;
				    	}
				    	
				    }
				    
				    if (change || !existen){
				    	document.cookie = "lat="+latitude;
					    document.cookie = "lon="+longitude;
					    location.reload();
				    }
	
				    console.log('Coordenates:\n\tLatitude: ' + latitude + '\n\tLongitude: ' + longitude + '');
				    
				    while (needPosition.length > 0){
				    	needPosition.pop()(latitude, longitude);
				    }

				  };
	
				  function error() {
				  	console.log("Unable to retrieve your location");
				  };
	
				  navigator.geolocation.getCurrentPosition(success, error);
			}
			geoFindMe();
		</script>
	</head>
	<body>
		<div class="container" style="margin-top: 30px;">