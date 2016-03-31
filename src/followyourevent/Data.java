package followyourevent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class Data {
	
	private static HashMap<String,Integer> oficialnames;
	private static String MS = "http://followyourevent-upv.rhcloud.com/";
	
	public static void main(String[] args) {
		//boolean s= false;
		try{
			Data.createPerson("maildeInesDominguez", "InesDominguez", 20, "Female", "mentira2");
			//s = Data.exist("maildde@gmail.com", "mentira");
			//System.out.println(s);
			FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
		}catch(Exception e){
			e.printStackTrace();
		}
		/*try{
			FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
			insertNewSite("http://www.hulen.no/");
			System.out.println("------------------------------------------------");
			FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
		}catch(Exception e){
			e.printStackTrace();
			FollowyoureventTDB.getFollowyoureventTDB().closeTransaction();
		}finally{
			FollowyoureventTDB.getFollowyoureventTDB().closeModel();
		}*/
		/*//TODO cambiamos a get
		//declare variables
		Document doc;
		Elements newsHeadlines;
		String MS = "pre:http://followyourevent-upv.rhcloud.com/";
		Resource event=null;
		Resource place=null;
		if(FollowyoureventTDB.getResource(MS+"Place")==null){
			event = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event");
			place = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Place");
		}else{
			place = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"Place");
			event = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"Event");
		}
		
		try {
			//create local variable for the names
			ArrayList<String> names = new ArrayList<String>();
			//make the sentence for select the names
			String query = "SELECT ?res WHERE { ?res <rdf:type> '"+MS+place+"' .}";//"+MS+"Place .}";
			//get the sites
			names = getSitesNames(query);
			String name, img, fecha, hora, precio, link;
			Element el;
			Elements els; 
			for (int i = 0; i < names.size(); i++) {
				System.out.println("\n"+names.get(i)+":");
				//create site resource for each real site
				Resource site = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+names.get(i));
				//add the type of place to each one
				site.addProperty(RDF.type, place);
				
				//Start making connections
				//TODO conseguir la url independientemente de cada garito
				doc = Jsoup.connect("http://hulen.no/").get();
				//This will be the same for all the sites
				newsHeadlines = doc.select(".no-gutter .event-list");
				for (Element element : newsHeadlines) {
					//System.out.println("\t"+element);
					img = element.select("img.img-responsive").first().attr("src");
					el = element.select(".list-info").first();
					link = el.select("a").first().attr("href").trim();
					name = el.select("a").first().text();
					els = el.select("li");
					fecha = els.first().text();
					hora = els.get(1).text().replaceAll("\\D+ || :+","");
					precio = els.get(2).text().replaceAll("\\D+","");
					
					System.out.println("\tEvento: "+name+" - ("+link+")");
					System.out.println("\t\t- Fecha: "+fecha+" - "+hora);
					System.out.println("\t\t- Precio: "+precio);
					System.out.println("\t\t- Imagen: "+ img);
					
					// TODO - to url 
					Resource ev = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event/"+name.replaceAll(" ", "%20"));
					ev.addProperty(RDF.type, event);
					ev.addProperty(VCARD.NAME, name);
					ev.addProperty(VCARD.PHOTO, img);
					//ev.addProperty(VCARD., arg1)
				}
				
			}
			System.out.println("---------------------------------------------");
			FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "TURTLE");
		} catch (IOException e) {
			e.printStackTrace();
		}
*/
	}
	public static ArrayList<String> getSitesNames(){
		String query =null; //TODO falta consulta
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
		ArrayList<String> names = new ArrayList<String>();
		String name=null;
		for ( ; res.hasNext() ; ){
            QuerySolution soln = res.nextSolution();
            name = soln.getLiteral("name").getString();
            names.add(name);
        }
		FollowyoureventTDB.getFollowyoureventTDB().closeExec();
		return names;
	}
	
	public static void insertNewSite(String uri){
		//declare variables
		String MS = "pre:http://followyourevent-upv.rhcloud.com/";
		Resource event=null;
		Resource place=null;
		Statement stmt=null;
		if(FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"Place")==null){
			event = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event");
			place = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Place");
		}else{
			place = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"Place");
			event = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"Event");
		}
		
		String name, img, fecha, hora, precio, link;
		
		//take the name of the site
		//TODO coger el nombre del garito
		String str = "Hulen";
		//create site resource for each real site
		Resource site = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Place");
		Property offers = FollowyoureventTDB.getFollowyoureventTDB().createProperty(MS+"offers");
		//add the type of place to each one
		site.addProperty(RDF.type, place);
		site.addProperty(RDF.value, str);
		
		String[][] info = getSpecificInfo(uri,str);
		for (int i = 0; i < info.length; i++) {
			name = info[i][0];
			img = info[i][1];
			fecha = info[i][2];
			hora = info[i][3];
			precio = info[i][4];
			link = info[i][5];
			
			//addProperties
			Resource ev = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event/"+name.replaceAll(" ", "%20"));
			ev.addProperty(RDF.type, event);
			ev.addProperty(VCARD.NAME, name);
			ev.addProperty(VCARD.PHOTO, img);
			ev.addProperty(DCTerms.date, fecha);
			ev.addProperty(DCTerms.date, hora);
			ev.addProperty(RDFS.label, precio);
			ev.addProperty(RDFS.label, link);
			
			//create conexion between the place and the event
			stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(site, offers, ev);
			
			//add the conexion to the rdfsmodel
			FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		}
		FollowyoureventTDB.getFollowyoureventTDB().commit();
		FollowyoureventTDB.getFollowyoureventTDB().closeTransaction();
	}
	
	public static String[][] getSpecificInfo(String uri,String str){
		Document doc = null;
		Elements newsHeadlines;
		//String name, img, fecha, hora, precio, link;
		Element el;
		Elements els;
		String[][] names = null;
		int ind = 0;
		oficialnames = new HashMap<String, Integer>();
		oficialnames.put("Hulen", 1);
		//TODO we need to inicialize the structure
		switch (oficialnames.get(str)) {
		case 1:
			//Start making connections
			try {
				doc = Jsoup.connect(uri).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//This will be the same for all the sites
			newsHeadlines = doc.select(".no-gutter .event-list");
			names = new String[newsHeadlines.size()][6];
			//get all the events
			for (Element element : newsHeadlines){
				names[ind][1] = element.select("img.img-responsive").first().attr("src");
				el = element.select(".list-info").first();
				names[ind][5] = el.select("a").first().attr("href").trim();
				names[ind][0] = el.select("a").first().text();
				els = el.select("li");
				names[ind][2] = els.first().text();
				names[ind][3] = els.get(1).text().replaceAll("\\D+ || :+","");
				names[ind][4] = els.get(2).text().replaceAll("\\D+","");
				ind++;
			}
			break;
		case 2:
//			System.out.println("KAOS:");
//			doc = Jsoup.connect("http://kaos-bergen.no/").get();
//			newsHeadlines = doc.select(".avia-gallery-thumb img");
//			for (Element element : newsHeadlines) {
//				System.out.println("\t"+element.attr("src"));
//			}
			break;
		case 3:
//			System.out.println("\nTIDI:");
//			doc = Jsoup.connect("http://tidi-bergen.no/").get();
//			newsHeadlines = doc.select(".avia-gallery-thumb img");
//			for (Element element : newsHeadlines) {
//				System.out.println("\t"+element.attr("src"));
//			}
		//	
			break;
		case 4:
//			System.out.println("\nLILLE:");
//			doc = Jsoup.connect("http://lille-bergen.no/").get();
//			newsHeadlines = doc.select(".avia-gallery-thumb img");
//			for (Element element : newsHeadlines) {
//				System.out.println("\t"+element.attr("src"));
//			}
		//	
			break;
		case 5:
//			System.out.println("\nTHESCOTSMAN:");
//			doc = Jsoup.connect("http://thescotsman.no/").get();
//			newsHeadlines = doc.select(".avia-gallery-thumb img");
//			for (Element element : newsHeadlines) {
//				System.out.println("\t"+element.attr("src"));
//			}
		//	
			break;

//			System.out.println("\nKOK:");
//			doc = Jsoup.connect("http://klubbkok.no/").get();
//			newsHeadlines = doc.select(".hours p");
//			for (Element element : newsHeadlines) {
//				System.out.println("\t"+element.text());
//			}
		default:
			break;
		}
		
		return names;
	}

	public static ArrayList<Resource> getAllTheEventsFromAPerson(String name){
		//the name parameter will be the complete url 
		Resource res = FollowyoureventTDB.getFollowyoureventTDB().getResource(name);
		ArrayList<Resource> arr = getAllTheEventsFromAPerson(res);
		return arr;
	}
	
	public static ArrayList<Resource> getAllTheEventsFromAPerson(Resource name){
		String query = "SELECT ?ev WHERE { '"+name+"' '"+MS+"goes' ?ev .}";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
		Resource usename;
		ArrayList<Resource> reses = new ArrayList<Resource>();
		for ( ; res.hasNext() ; ){
            QuerySolution soln = res.nextSolution();
            usename = soln.getResource(soln.toString());//TODO falta saber como coger
            reses.add(usename);
        }
		FollowyoureventTDB.getFollowyoureventTDB().closeExec();
		return reses;
	}
	
	public static ArrayList<Resource> getAllThePeopleFromAnEvent(String event){
		//the name parameter will be the complete url 
		Resource res = FollowyoureventTDB.getFollowyoureventTDB().getResource(event);
		ArrayList<Resource> arr = getAllTheEventsFromAPerson(res);
		return null;
	}
	
	public static ArrayList<Resource> getAllThePeopleFromAnEvent(Resource event){
		String query = "SELECT ?peo WHERE { ?peo '"+MS+"goes' '"+event+"' .}";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
		Resource useevent;
		ArrayList<Resource> reses = new ArrayList<Resource>();
		for ( ; res.hasNext() ; ){
            QuerySolution soln = res.nextSolution();
            useevent = soln.getResource(MS+"Person");//TODO falta saber como coger
            reses.add(useevent);
        }
		FollowyoureventTDB.getFollowyoureventTDB().closeExec();
		return null;
	}
	
	public static boolean confirmPass(String user, String pass){
		//TODO crear las SELECT 
		String query = "SELECT ?peo WHERE { ?peo '"+MS+"goes' '' .}";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
        QuerySolution soln = res.nextSolution();
        Literal getuser, getpass; 
        getuser = soln.getLiteral(user);
        if(getuser!=null){
        	query = "SELECT ?peo WHERE { ?peo '"+MS+"goes' '' .}";
        	res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
        	soln = res.nextSolution();
        	getpass = soln.getLiteral(pass);
        	if (getpass!=null) {
				return true;
			}else{
				System.out.println("The user is not in the database");
				return false;
			}
        }else{
        	System.out.println("The user is not in the database");
        	return false;
        }
		
	}
	
	public static boolean createPerson(String mail, String name, int age, String sex, String pass){
		Property Ppass = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://followyourevent.com/vocabulary/pass");
		Property Page = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://followyourevent.com/vocabulary/age");;
		if(!exist(mail,pass)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Person");
			res.addLiteral(FOAF.mbox, mail);
			res.addLiteral(FOAF.givenname, name);
			res.addLiteral(Page, age);
			res.addLiteral(FOAF.gender, sex);
			res.addLiteral(Ppass, pass);
			return true;
		}else{
			return false;
		}	
	}
	
	public static boolean exist(String mail, String pass){
		String query = "PREFIX Foaf: <http://xmlns.com/foaf/0.1/> "
				+ "PREFIX own: <http://followyourevent-upv.rhcloud.com/>	"//TODO habra que cambiar el nombre del pass cuando tengamos claro el vocabulario
				+ "SELECT ?peo WHERE { ?peo Foaf:mbox '"+mail+"' . "
								+ " ?peo own:pass '"+pass+"'}";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
        if(res.hasNext()){
        	QuerySolution soln = res.nextSolution();
        	return true;
        }else{
        	return false;
        }
	}
	
	public static void addEventToAPerson(String person, String event){
		Resource personres = FollowyoureventTDB.getFollowyoureventTDB().getResource(person);
		Resource eventres = FollowyoureventTDB.getFollowyoureventTDB().getResource(event);
		addEventToAPerson(personres,eventres);
	}
	
	public static void addEventToAPerson(Resource person, Resource event){
		Property progoes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		FollowyoureventTDB.getFollowyoureventTDB().add(FollowyoureventTDB.getFollowyoureventTDB().createStatement(person, progoes, event));
	}
	
	public static void recommendEvents(){
		
	}

}