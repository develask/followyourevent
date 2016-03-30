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
		String mail = request.getParameter("email");
		String pass = request.getParameter("pass");
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		if (Data.confirmPass(mail, pass)){
			response.setHeader("Location", "/followyourevent"); 
		}else{
			response.setHeader("Location", "/followyourevent/login"); 
		}
	%>
</body>
</html>