<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>

<% 
String mail = Sessions.getSessions().verifySession(request.getCookies());
String evName = request.getParameter("event");

%>