<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@ page import="java.util.Date"%>
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
			System.out.println("pass not equal");
		}else{
			if (FollowyoureventTDB.getFollowyoureventTDB().createPerson(mail, name, age, sex, pass)){
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				Cookie c = new Cookie("oauth", Sessions.sha1(mail+(new Date()).getTime()));
				response.addCookie( c );
				Sessions.getSessions().setNewSession(c.getValue(), mail);
				response.setHeader("Location", "/followyourevent");
			}else{
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", "/followyourevent/signup");
				System.out.println("Error creating person");
			}
		}
		
		
	%>
</body>
</html>