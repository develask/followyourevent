<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>

<%
	FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
	String mail = Sessions.getSessions().verifySession(request.getCookies());
	String mail2 = request.getParameter("mail");
	if (fye.isAdmin(fye.MS+"person/"+mail)){
		if (fye.isAdmin(fye.MS+"person/"+mail2)){
			fye.removeAdminRoleToAPerson(fye.MS+"person/"+mail2);
		}else{
			fye.addAdminRoleToAPerson(fye.MS+"person/"+mail2);
		}
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "/followyourevent/user/admin.jsp");
	}else{
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", "/followyourevent/");
	}
%>