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
	String lat = request.getParameter("lat");
	String lng = request.getParameter("lon");
	String logo = request.getParameter("logo");
	String capacity = request.getParameter("capacity");
	String url = request.getParameter("url");
	String check = request.getParameter("auto");
	if (check==null) check = "Want";
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	String pla = fye.createPlace(name, street, logo, capacity, url, check.equals("true")?"Want":"No");
	if (pla!=null){
		if (fye.addOwnerToAPlace(pla, fye.MS+"person/"+email)){
			response.setHeader("Location", "/followyourevent/places/place.jsp?pl="+pla.split("/place/")[1]);
		}else{
			response.setHeader("Location", "/followyourevent/places");
		}
	}else{
		response.setHeader("Location", "/followyourevent/places"); 
	}
	
}
%>