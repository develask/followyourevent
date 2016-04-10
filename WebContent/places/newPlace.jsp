<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%
response.setStatus(response.SC_MOVED_TEMPORARILY);
String email = Sessions.getSessions().verifySession(request.getCookies());
if (email == null){
	response.setHeader("Location", "/followyourevent/"+email); 
}else{

	String name = request.getParameter("name");
	String street = request.getParameter("street");
	String logo = request.getParameter("logo");
	String capacity = request.getParameter("capacity");
	String url = request.getParameter("url");
	String check = request.getParameter("auto");
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	if (fye.createPlace(name, street, logo, capacity, url, check.equals("true")?"Want":"No")){
		if (fye.addOwnerToAPlace(fye.MS+"place/"+(name+street).replaceAll(" ", ""), fye.MS+"person/"+email)){
			response.setHeader("Location", "/followyourevent/places/place.jsp?pl="+(name+street).replaceAll(" ", ""));
		}else{
			response.setHeader("Location", "/followyourevent/places");
		}
	}else{
		response.setHeader("Location", "/followyourevent/places"); 
	}
	
}
%>