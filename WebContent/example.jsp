<%@ page import="java.util.*"%><%@ page import="org.jsoup.Jsoup"%><%@ page import="org.jsoup.nodes.*"%><%@ page import="org.jsoup.select.Elements"%><%@ page language="java" contentType="application/json"
    pageEncoding="UTF-8"%>{<%
String user=null;
String pass=null;
Document doc;
Elements newsHeadlines;
try {
	user = request.getParameter("user");
	pass = request.getParameter("password");
	doc = Jsoup.connect("http://hulen.no/").get();
	newsHeadlines = doc.select(".no-gutter .event-list");
	String name, img, fecha, hora, precio, link;
	Element el;
	Elements els;

	for (Element element : newsHeadlines) { 
		//System.out.println("\t"+element);
		img = element.select("img.img-responsive").first().attr("src");
		el = element.select(".list-info").first();
		link = el.select("a").first().attr("href").trim();
		name = el.select("a").first().text().replaceAll("[^a-zA-Z:]","");
		els = el.select("li");
		fecha = els.first().text().trim();
		hora = els.get(1).text().replaceAll("\\D+ || :+","");
		precio = els.get(2).text().replaceAll("\\D+","");
%>"<%=name%>": {
	"link": "<%=link%>",
	"fecha": "<%=fecha%>",
	"hora": "<%=hora%>",
	"precio": "<%=precio%>",
	"img": "<%=img%>"
},<%
		//System.out.println("\tEvento: "+name+" - ("+link+")");
		//System.out.println("\t\t- Fecha: "+fecha+" - "+hora);
		//System.out.println("\t\t- Precio: "+precio);
		//System.out.println("\t\t- Imagen: "+ img);
	}

}catch (Exception e) {
	user = "no user";
	pass = "no pass";
}%>"user": "<%= user %>","pass": "<%= pass %>"}