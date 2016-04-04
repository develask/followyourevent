<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%
response.setStatus(response.SC_MOVED_TEMPORARILY);
String email = Sessions.getSessions().verifySession(request.getCookies());
if (email == null){
	//response.setHeader("Location", "/followyourevent"); 
}else{
	String name = request.getParameter("name");
	String logo = request.getParameter("logo");
	String url = request.getParameter("url");
	String date = request.getParameter("date");
	String time = request.getParameter("time");
	String price = request.getParameter("price");
	String placeUri = request.getParameter("place");
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	fye.createEvent(name, logo, url, date.split("-")[2], date.split("-")[1], time, price);
	fye.addEventToAPlace(placeUri, fye.MS+"event/"+(name+date.split("-")[1]+date.split("-")[2]).replaceAll(" ", ""));
	response.setHeader("Location", "/followyourevent/places/place.jsp?pl="+placeUri.split("/place/")[1]);
	
}
%>