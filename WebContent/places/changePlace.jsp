<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%
response.setStatus(response.SC_MOVED_TEMPORARILY);
String email = Sessions.getSessions().verifySession(request.getCookies());
if (email == null){
	response.setHeader("Location", "/followyourevent/"+email); 
}else{
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	
	String  pl = request.getParameter("pl");
	String uriPL =  fye.MS+"place/" + pl;
	
	String name = request.getParameter("name");
	String street = request.getParameter("street");
	String lat = request.getParameter("lat");
	String lng = request.getParameter("lon");
	String logo = request.getParameter("logo");
	String capacity = request.getParameter("capacity");
	String url = request.getParameter("url");
	String check = request.getParameter("auto")!=null?"true":"false";
	Boolean isMine = fye.placeOwnerOfAPerson(fye.MS+ "person/" + email, uriPL);
	if (isMine){
		fye.modifyPlace(uriPL, name, street, logo, capacity, url, check.equals("true")?"Want":"No", Double.parseDouble(lat), Double.parseDouble(lng));
	}
	response.setHeader("Location", "/followyourevent/places/place.jsp?pl="+pl);
}
%>