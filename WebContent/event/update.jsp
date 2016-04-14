<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%
String ev = request.getParameter("ev");
String name = request.getParameter("name");
String logo = request.getParameter("logo");
String url = request.getParameter("url");
String date = request.getParameter("date");
String time = request.getParameter("time");
String price = request.getParameter("price");

FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
//             uriEvent, name, image, url, day,  month, hour, price
fye.modifyEvent(fye.MS+"event/"+ev, name, logo, url, date.split("-")[2], date.split("-")[1], time, price);

%>
OK