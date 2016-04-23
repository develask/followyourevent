<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<%@page import="java.util.ArrayList"%>
[
<%
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	ArrayList<String> lista = fye.getPeopleByName(request.getParameter("name"));
	int i = 0;
	for (String p: lista){
		String[] info = fye.getInformationAboutAPerson(p.split("/person/")[1]);
		boolean admin = fye.isAdmin(p);
		%>{
			"id": "<%= p %>",
			"name": "<%= info[0] %>",
			"isAdmin": <%= admin %>
		}<%= ++i<lista.size()?",":"" %><%
	}
%>
]