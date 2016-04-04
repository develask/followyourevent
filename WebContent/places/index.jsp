<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="followyourevent.*"%>
<!DOCTYPE html>
<html>
	<%@include file="../Header.jsp" %>		
			<div class="row">
			<div class="col-sm-8 blog-main">
				<%
					FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
					String mail = Sessions.getSessions().verifySession(request.getCookies());
					if (mail != null){
					}
				%>
			</div>
			<%@include file="../Footer.jsp" %>
</html>