package followyourevent;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.xml.validation.Schema;

import jena.schemagen;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.tdb.TDBFactory;
import org.jsoup.Jsoup;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.SKOS;
import org.apache.jena.vocabulary.VCARD;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class FollowyoureventTDB {

 	private static Model model=null;
 	private static InfModel rdfsmodel=null;
 	private static Dataset dataset=null;
 	private static QueryExecution qexec=null;
// 	private static HashMap<String,Integer> oficialnames;
 	private static String MS = "http://followyourevent.com/";
 	//private static String OPENSHIFT_DATA_DIR="/Library/Tomcat/webapps/followyourevent/MyDatabases";
 	private static String OPENSHIFT_DATA_DIR="MyDatabases";
 	private static FollowyoureventTDB myFollowyoureventTDB=null;

 	private FollowyoureventTDB() {
 		makeDataset();
 		createModel();
 	}
 	
 	public static void main(String[] args) {
 		boolean s = false;
 		ArrayList<String> arr;
 		try{
 			//s = FollowyoureventTDB.getFollowyoureventTDB().createPlace("Tidi", "calledetidi", "https://logotidi.com", "120");
 			//FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
 			//FollowyoureventTDB.getFollowyoureventTDB().getInformationOfPlace("http://followyourevent.com/place/Tidicalledetidi");
 			FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
 			//arr = fye.getInformationOfEvent(MS+"event/hulen0223");
 			//System.out.println(arr.toString());
 			fye.createPerson("develascomikel@gmail.com", "Mikel", "21", "Male", "develask");
 			fye.createEvent("EventNumber1", "http://definicion.mx/wp-content/uploads/2014/07/Evento.jpg", "https://social-kayak.rhcloud.com/", "21", "05", "10", "80");
 			s = fye.addEventToAPerson(MS+"person/develascomikel@gmail.com", MS+"event/EventNumber12105");
 			//s = FollowyoureventTDB.getFollowyoureventTDB().addEventToAPerson(MS+"person/maildecade@gmail.com", MS+"event/hulen0223");
 			//FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
 			System.out.println(s);
		}catch(Exception e){
 			System.out.println(s);
 			e.printStackTrace();
 		}
 	}
 	
 	public static FollowyoureventTDB getFollowyoureventTDB(){
 		if(FollowyoureventTDB.myFollowyoureventTDB==null){
 			FollowyoureventTDB.myFollowyoureventTDB = new FollowyoureventTDB();
 		}
 		return FollowyoureventTDB.myFollowyoureventTDB;
 	}
 	
	public Model getModel(){
		return FollowyoureventTDB.getFollowyoureventTDB().model;
	}
	
	private void makeDataset(){
		//this will be the directory that we will use to save the data
     	dataset = TDBFactory.createDataset(OPENSHIFT_DATA_DIR);
	}

	private void createModel(){
        //obtain the model from the dataset
        model = dataset.getDefaultModel();
		rdfsmodel = ModelFactory.createRDFSModel(model);
		rdfsmodel.commit();
   	}
	
	public void addNewModel(Model mod){
   		//allow to write
		dataset.begin(ReadWrite.WRITE);
		//add the model 
   		rdfsmodel.add(mod);
	}
   	
	public ResultSet selectQuery(String query){
		ResultSet results=null;
		if(!query.contains("Update")){
			//allow to read 
			//dataset.begin(ReadWrite.READ);
 			Query quer = QueryFactory.create(query);
 	        qexec = QueryExecutionFactory.create(quer, rdfsmodel.getRawModel());
 	        try {
 	        	results = qexec.execSelect() ;
           	} finally { 
         		//rdfsmodel.close();
           		//dataset.end(); 
           	}
 		}else{
 			System.out.println("The query is not a select query");
 		}
 		return results;
	}
	
	public void closeExec(){
		qexec.close();
	}
	
	public Resource getResource(String uri){
		return rdfsmodel.getResource(uri);
	}
	
	public Resource createResource(String uri){
		Resource res=null;
		try{
			res = rdfsmodel.createResource(uri);
		}catch(Exception e){
			res=null;
		}
		return res;
	}
	
	public Property getProperty(String uri){
		Property pro = rdfsmodel.getProperty(uri);
		return pro;
	}
	
	public Property createProperty(String uri){
		Property pro = rdfsmodel.createProperty(uri);
		return pro;
	}
	
	public Statement createStatement(Resource res, Property pro, Resource res2){
		Statement stmt = null;
		stmt = rdfsmodel.createStatement(res, pro, res2);
		return stmt;
	}
	
	public Statement getStatement(Resource res, Property pro, Resource res2){
		Statement stmt = rdfsmodel.createStatement(res, pro, res2);
		return stmt;
	}
	
	public boolean removeStatement(Statement stmt){
		try{
			rdfsmodel.remove(stmt);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void write(PrintStream out, String way) {
		rdfsmodel.write(out,way);
	}

	public void add(Statement stmt) {
		rdfsmodel.add(stmt);
	}

	public void commit() {
		rdfsmodel.commit();
	}
	
	public void closeTransaction(){
		dataset.end();
		//rdfsmodel.close();
	}
	
	public void closeModel(){
		rdfsmodel.close();
	}
	
	/**
	 * 
	 * @param mail
	 * @return Array => name, mail, age , sex
	 */
	public static String[] getInformationAboutAPerson(String mail){
		String[] arr = new String[4];
		String query = "PREFIX Foaf: <http://xmlns.com/foaf/0.1/> "
				+ "PREFIX own: <http://followyourevent.com/vocabulary/>"
				+ "SELECT ?name ?sex ?age WHERE { ?peo Foaf:mbox '"+mail+"' ."
						+ " ?peo Foaf:givenname ?name ."
						+ " ?peo Foaf:gender ?sex ."
						+ " ?peo own:age ?age }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.nextSolution();
	    	arr[0] = soln.getLiteral("name").toString();
	    	arr[1] = mail;
	    	arr[2] = soln.getLiteral("age").toString();
	    	arr[3] = soln.getLiteral("sex").toString();
	    	return arr;
	    }else{
	    	return null;
	    }    
	}
	
	/**
	 * 
	 * @param uri
	 * @return ArrayList -> name, street, logo, capacity
	 */
	public static ArrayList<String> getInformationOfPlace(String uri){
		Resource reso = FollowyoureventTDB.getFollowyoureventTDB().getResource(uri);
		ArrayList<String> arr = new ArrayList<String>();
		String query = " PREFIX Prov: <http://www.w3.org/TR/prov-dm/> PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?name ?street ?logo ?capacity "
				+ "WHERE { <"+reso+"> Prov:organization ?name ."
						+ " <"+reso+"> VCARD:street ?street ."
						+ " <"+reso+"> DBpedia:logo ?logo ."
						+ " <"+reso+"> DBpedia:capacity ?capacity }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.nextSolution();
	    	arr.add(soln.getLiteral("name").toString());
	    	arr.add(soln.getLiteral("street").toString());
	    	arr.add(soln.getLiteral("logo").toString());
	    	arr.add(soln.getLiteral("capacity").toString());
	    	return arr;
	    }else{
	    	return arr;
	    }
	}
	
	/**
	 * 
	 * @param uriPlace
	 * @return Array -> list of events
	 */
	public static ArrayList<String> getEventsOfAPlace(String uriPlace){
		ArrayList<String> arr = new ArrayList<String>();
		String query = "PREFIX Own: <http://followyourevent.com/>"
				+ "SELECT ?name WHERE { <"+uriPlace+"> Own:offers ?name }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	while (res.hasNext()) {
	    		QuerySolution soln = res.next();
	    		try{
	    			arr.add(soln.getResource("name").toString());
	    		}catch(Exception e){
	    			
	    		}
			}
	    	return arr;
	    }else{
	    	return null;
	    }    
	}
	
	/**
	 * 
	 * @param uri
	 * @return Arraylist -> name, image, url, day, month, hour, price
	 */
	public static ArrayList<String> getInformationOfEvent(String uri){
		Resource reso = FollowyoureventTDB.getFollowyoureventTDB().getResource(uri);
		ArrayList<String> arr = new ArrayList<String>();
		String query = "PREFIX DBpedia: <http://dbpedia.org/> PREFIX Purl: <http://purl.org/dc/dcmitype/> "
				+ " PREFIX Prov: <http://www.w3.org/TR/prov-dm/> PREFIX Schema:<http://schema.org/> "
				+ " PREFIX Own: <http://followyourevent.com/> "
				+ "SELECT ?name ?image ?url ?day ?month ?hour ?price "
				+ "WHERE { <"+reso+"> DBpedia:event ?name ."
						+ " <"+reso+"> Purl:image ?image ."
						+ " <"+reso+"> Prov:primarySource ?url ."
						+ " <"+reso+"> DBpedia:day ?day ."
						+ " <"+reso+"> DBpedia:month ?month ."
						+ " <"+reso+"> Prov:start ?hour ."
						+ " <"+reso+"> Schema:price ?price }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.nextSolution();
	    	arr.add(soln.getLiteral("name").toString());
	    	arr.add(soln.getLiteral("image").toString());
	    	arr.add(soln.getLiteral("url").toString());
	    	arr.add(soln.getLiteral("day").toString());
	    	arr.add(soln.getLiteral("month").toString());
	    	arr.add(soln.getLiteral("hour").toString());
	    	arr.add(soln.getLiteral("price").toString());
	    	return arr;
	    }else{
	    	return arr;
	    }
	} 
	
	
	/**
	 * 
	 * @param mail
	 * @return arraylist -> identificador de eventos; si no existe null
	 */
	public static ArrayList<String> getAllTheEventsOfAPerson(String mail){
		ArrayList<String> arr = new ArrayList<String>();
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"person/"+mail);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "PREFIX own: <"+MS+"> "
				+ "SELECT ?ev WHERE { <"+person+"> <"+goes+"> ?ev }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	while (res.hasNext()) {
	    		QuerySolution soln = res.next();
	    		try{
	    			String l = soln.getResource("ev").toString();
		    		arr.add(l);
	    		}catch(Exception e){
	    			
	    		}
			}
	    	return arr;
	    }else{
	    	return null;
	    }
	}
	
	public static ArrayList<String> getAllPastEventsOfAPerson(String mail){
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.setTime(now);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		ArrayList<String> arr = new ArrayList<String>();
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"person/"+mail);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?ev WHERE { <"+person+"> <"+goes+"> ?ev ."
				+ " ?ev DBpedia:month ?month ."
				+ " ?ev DBpedia:day ?day ."
				+ " FILTER (?month < "+month+" || (?day < "+day+" && ?month = "+month+")) }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	while (res.hasNext()) {
	    		QuerySolution soln = res.next();
	    		try{
	    			String l = soln.getResource("ev").toString();
		    		arr.add(l);
	    		}catch(Exception e){
	    			
	    		}
			}
	    	return arr;
	    }else{
	    	return null;
	    }
	}
	
	public static ArrayList<String> getAllFutureEventsOfAPerson(String mail){
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.setTime(now);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		ArrayList<String> arr = new ArrayList<String>();
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"person/"+mail);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?ev WHERE { <"+person+"> <"+goes+"> ?ev ."
				+ " ?ev DBpedia:month ?month ."
				+ " ?ev DBpedia:day ?day ."
				+ " FILTER (?month > "+month+" || (?day >= "+day+" && ?month = "+month+")) }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	while (res.hasNext()) {
	    		QuerySolution soln = res.next();
	    		try{
	    			String l = soln.getResource("ev").toString();
		    		arr.add(l);
	    		}catch(Exception e){
	    			
	    		}
			}
	    	return arr;
	    }else{
	    	return null;
	    }
	}
	
	/**
	 * 
	 * @param uriEvent
	 * @return Array -> list of resources; if not null
	 */
	public static String[] getAllThePeopleFromAnEvent(String uriEvent){
		String[] arr;
		Resource event = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "SELECT (COUNT(?per) AS ?count) WHERE { ?per <"+goes+"> <"+event+"> }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.next();
	    	int count = Integer.parseInt(soln.getLiteral("count").toString());
	    	arr = new String[count];
	    	query = "SELECT ?per WHERE { ?per <"+goes+"> <"+event+"> }";
			res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
			if(res.hasNext()){
				int i=0;
				while (res.hasNext()) {
		    		try{
		    			soln = res.next();
		    			arr[i] = soln.getResource("per").toString();
		    		}catch(Exception e){
		    			
		    		}
	    		}
				return arr;
			}else{
				return null;
			}
	    	
	    }else{
	    	return null;
	    }
	}
	
	/**
	 * 
	 * @param mail
	 * @param pass
	 * @return true if equals; false if not equals
	 */
	public static boolean confirmPass(String mail, String pass){
		Resource reso = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"person/"+mail);
		String query = "PREFIX Own:<http://followyourevent.com/vocabulary/> SELECT ?pass WHERE { <"+reso+"> Own:pass ?pass .}";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.nextSolution();
	    	String dpass = soln.getLiteral("pass").toString();
	    	if(pass.equals(dpass)){
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }else{
	    	return false;
	    }	
	}
	
	/**
	 * 
	 * @param placeName
	 * @param street
	 * @param logo
	 * @param capacity
	 * @return true if ok; false if not created
	 */
	public static boolean createPlace(String placeName, String street, String logo, String capacity){
		Property porganization = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/organization");
		Property plogo = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/logo");
		Property pcapacity = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/capacity");
		if(!existPlace(placeName, street)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"place/"+placeName+street);
			res.addLiteral(porganization, placeName);
			res.addLiteral(VCARD.Street, street);
			res.addLiteral(plogo, logo);
			res.addLiteral(pcapacity, capacity);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param description
	 * @param kind
	 * @return if created true; if not false
	 */
	public static boolean createStyle(String description, String kind){
		Property pKind = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/kind");
		if(!existStyle(kind)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"tyle/"+kind);
			res.addLiteral(SKOS.definition, description);
			res.addLiteral(pKind, kind);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param name
	 * @param image
	 * @param url
	 * @param day
	 * @param month
	 * @param hour
	 * @param price
	 * @return true if created; false if not created
	 */
	public static boolean createEvent(String name, String image, String url, String day, String month, String hour, String price/*, int minimumage*/){
		Property eventname = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/event");
		Property pimage = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://purl.org/dc/dcmitype/image");
		Property primarySource = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/primarySource");
		Property pday = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/day");
		Property pmonth = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/month");
		Property start = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/start");
		Property pprice = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://schema.org/price");
		if(!existEvent(name, month, day)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"event/"+name+month+day);
			res.addLiteral(eventname, name);
			res.addLiteral(pimage, image);
			res.addLiteral(primarySource, url);
			res.addLiteral(pday, day);
			res.addLiteral(pmonth, month);
			res.addLiteral(start, hour);
			res.addLiteral(pprice, price);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param mail
	 * @param name
	 * @param age
	 * @param sex
	 * @param pass
	 * @return true if created; false if not created
	 */
	public static boolean createPerson(String mail, String name, String age, String sex, String pass){
		Property Ppass = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://followyourevent.com/vocabulary/pass");
		Property Page = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://followyourevent.com/vocabulary/age");
		if(!existPerson(mail)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"person/"+mail);
			res.addLiteral(FOAF.mbox, mail);
			res.addLiteral(FOAF.givenname, name);
			res.addLiteral(Page, age);
			res.addLiteral(FOAF.gender, sex);
			res.addLiteral(Ppass, pass);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return true;
		}else{
			return false;
		}	
	}
	
	/**
	 * 
	 * @param kind
	 * @return true if exist; false if not exist
	 */
	public static boolean existStyle(String kind){
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?peo WHERE { ?peo DBpedia:kind '"+kind+"' }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param name
	 * @param month
	 * @param day
	 * @return true if exist; false if not exist
	 */
	public static boolean existEvent(String name,String month, String day){
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?peo WHERE { ?peo DBpedia:event '"+name+"' ."
						+ " ?peo  DBpedia:day '"+day+"' ."
						+ " ?peo DBpedia:month '"+month+"'}";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param name
	 * @param street
	 * @return true if exist; false if not exist
	 */
	public static boolean existPlace(String name, String street){
		String query = "PREFIX Vcard: <http://www.w3.org/TR/vcard-rdf/> "
				+ " PREFIX Prov: <http://www.w3.org/TR/prov-dm/> "
				+ "SELECT ?peo WHERE { ?peo <"+VCARD.Street+"> '"+street+"' . "
				+ " ?peo Prov:organization '"+name+"' }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
		if(res.hasNext()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param mail
	 * @return true if exist; false if not exist
	 */
	public static boolean existPerson(String mail){
		String query = "PREFIX Foaf: <http://xmlns.com/foaf/0.1/> "
				+ "SELECT ?peo WHERE { ?peo Foaf:mbox '"+mail+"' }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param res1
	 * @param prop
	 * @param res2
	 * @return true if exist; false if not exist
	 */
	public static boolean existStatement(Resource res1, Property prop, Resource res2){
		String query = " SELECT ?peo WHERE { <"+res1+"> <"+prop+"> <"+res2+"> }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param uriEvent
	 * @param uriStyle
	 * @return if added true; if it was already added false
	 */
	public static boolean addStyleToAnEvent(String uriEvent, String uriStyle){
		try{
			Resource resEvent = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
			Resource resStyle = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriStyle);
			Property is = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"is");
			if(!existStatement(resEvent, is, resStyle)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(resEvent, is, resStyle);
				FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 
	 * @param uriEvent
	 * @param uriStyle
	 * @return true if removed; false if not
	 */
	public static boolean removeStyleFromAnEvent(String uriEvent, String uriStyle){
		try{
			Resource resEven = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
			Resource resStyle = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriStyle);
			Property is = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"is");
			if(existStatement(resEven, is, resStyle)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().getStatement(resEven, is, resStyle);
				FollowyoureventTDB.getFollowyoureventTDB().removeStatement(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 
	 * @param uriPerson
	 * @param uriPlace
	 * @return if added true; if it was already added false
	 */
	public static boolean addlikeablePlaceToAPerson(String uriPerson, String uriPlace){
		try{
			Resource resPer = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
			Resource resPlace = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPlace);
			Property prefers = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"prefers");
			if(!existStatement(resPer, prefers, resPlace)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(resPer, prefers, resPlace);
				FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 
	 * @param uriPerson
	 * @param uriPlace
	 * @return true if removed: false if not
	 */
	public static boolean removeLikeablePlaceToAPerson(String uriPerson, String uriPlace){
		try{
			Resource resPer = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
			Resource resPlace = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPlace);
			Property prefers = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"prefers");
			if(existStatement(resPer, prefers, resPlace)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().getStatement(resPer, prefers, resPlace);
				FollowyoureventTDB.getFollowyoureventTDB().removeStatement(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean addOwnerToAPlace(String uriPlace,String uriPerson){
		try{
			Resource resPer = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
			Resource resPlace = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPlace);
			Property hasOwner = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"hasOwner");
			if(!existStatement(resPer, hasOwner, resPlace)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(resPer, hasOwner, resPlace);
				FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 
	 * @param uriPlace
	 * @param uriEvent
	 * @return if added true; if it was already added false
	 */
	public static boolean addEventToAPlace(String uriPlace, String uriEvent){
		try{
			Resource resPlace = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPlace);
			Resource resEvent = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
			Property offers = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"offers");
			if(!existStatement(resPlace, offers, resEvent)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(resPlace, offers, resEvent);
				FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	/**
	 * 
	 * @param uriPlace
	 * @param uriEvent
	 * @return true if removed, false if not
	 */
	public static boolean removeEventFromAPlace(String uriPlace, String uriEvent){
		Resource place = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPlace);
		Resource even = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
		Property prop = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"offers");
		try{
			if(existStatement(place, prop, even)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().getStatement(place, prop, even);
				FollowyoureventTDB.getFollowyoureventTDB().removeStatement(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	
	/**
	 * 
	 * @param uriPerson
	 * @param uriEvent
	 * @return if added true; if it was already added false
	 */
	public static boolean addEventToAPerson(String uriPerson, String uriEvent){
		try{
			Resource resPer = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
			Resource resEvent = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
			Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
			if(!existStatement(resPer, goes, resEvent)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(resPer, goes, resEvent);
				FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	/**
	 * 
	 * @param uriPerson
	 * @param uriEvent
	 * @return true if removed, false if not
	 */
	public static boolean removeEventFromAPerson(String uriPerson, String uriEvent){
		Resource pers = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
		Resource even = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
		Property prop = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		try{
			if(existStatement(pers, prop, even)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().getStatement(pers, prop, even);
				FollowyoureventTDB.getFollowyoureventTDB().removeStatement(stmt);
				FollowyoureventTDB.getFollowyoureventTDB().commit();
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}
	
	public static void createRulesForRecommendations(){
		//TODO
	}
	
	public static void recommendEvents(){
		//TODO
	}

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
/*
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
/*if(FollowyoureventTDB.getFollowyoureventTDB().getModel().contains(MS+"Place", null)){
	event = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event");
	place = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Place");
}else{
	place = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"Place");
	event = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"Event");
}*/
/*
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
//				System.out.println("KAOS:");
//				doc = Jsoup.connect("http://kaos-bergen.no/").get();
//				newsHeadlines = doc.select(".avia-gallery-thumb img");
//				for (Element element : newsHeadlines) {
//					System.out.println("\t"+element.attr("src"));
//				}
	break;
case 3:
//				System.out.println("\nTIDI:");
//				doc = Jsoup.connect("http://tidi-bergen.no/").get();
//				newsHeadlines = doc.select(".avia-gallery-thumb img");
//				for (Element element : newsHeadlines) {
//					System.out.println("\t"+element.attr("src"));
//				}
//	
	break;
case 4:
//				System.out.println("\nLILLE:");
//				doc = Jsoup.connect("http://lille-bergen.no/").get();
//				newsHeadlines = doc.select(".avia-gallery-thumb img");
//				for (Element element : newsHeadlines) {
//					System.out.println("\t"+element.attr("src"));
//				}
//	
	break;
case 5:
//				System.out.println("\nTHESCOTSMAN:");
//				doc = Jsoup.connect("http://thescotsman.no/").get();
//				newsHeadlines = doc.select(".avia-gallery-thumb img");
//				for (Element element : newsHeadlines) {
//					System.out.println("\t"+element.attr("src"));
//				}
//	
	break;

//				System.out.println("\nKOK:");
//				doc = Jsoup.connect("http://klubbkok.no/").get();
//				newsHeadlines = doc.select(".hours p");
//				for (Element element : newsHeadlines) {
//					System.out.println("\t"+element.text());
//				}
default:
	break;
}

return names;
}*/