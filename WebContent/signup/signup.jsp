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
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String sex = request.getParameter("sex");
		String pass = request.getParameter("pass");
		String pass2 = request.getParameter("pass2");
		if (!pass.equals(pass2)){
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", "/followyourevent/signup");
		}
		
		if (Data.createPerson(mail, name, Integer.parseInt(age), sex, pass)){
			%>
			<h2>Se ha creado:</h2>
			<p><b>Mail:</b><%= mail %></p>
			<%
		}else{
			%><h2>No se ha podido crear.</h2><%
		}
		
		
	%>
</body>
</html>