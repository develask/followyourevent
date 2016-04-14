<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>

<% 
String mail = Sessions.getSessions().verifySession(request.getCookies());
String evName = request.getParameter("event");
String from = request.getParameter("from");
if (from == null) from = "/followyourevent";
if (mail == null){
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", "/followyourevent/login");
}else{
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	fye.addEventToAPerson(fye.MS+"person/"+mail, fye.MS+"event/"+evName);
	response.setStatus(response.SC_MOVED_TEMPORARILY);
	response.setHeader("Location", from); 
}
%>