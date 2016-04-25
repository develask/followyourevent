<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	String mail = Sessions.getSessions().verifySession(request.getCookies());
	String pl = request.getParameter("pl");
	String newValue = request.getParameter("val");
	if (fye.isAdmin(fye.MS+"person/"+mail)){
		fye.changeAutomaticStatus(FollowyoureventTDB.MS+"place/"+pl, newValue);
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "/followyourevent/user/admin.jsp");
	}else{
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "/followyourevent/");
	}
%>