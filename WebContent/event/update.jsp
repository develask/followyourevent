<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%
String ev = request.getParameter("ev");
String logo = request.getParameter("logo");
String url = request.getParameter("url");
String date = request.getParameter("date");
String time = request.getParameter("time");
String price = request.getParameter("price");

FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
fye.modifyEvent(fye.MS+"event/"+ev, logo, url, date, time, price);

%>
OK