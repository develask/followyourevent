<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		String name = Sessions.getSessions().verifySession(request.getCookies());
		if (name != null){
			response.setHeader("Location", "/followyourevent"); 
		}else{
		
			String mail = request.getParameter("email");
			String pass = request.getParameter("pass");
			if (Data.confirmPass(mail, pass)){
				response.addCookie( new Cookie("oauth","") );
				response.setHeader("Location", "/followyourevent"); 
			}else{
				response.setHeader("Location", "/followyourevent/login"); 
			}
		}
	%>
</body>
</html>