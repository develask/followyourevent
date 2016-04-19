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
import org.apache.jena.update.GraphStore;
import org.apache.jena.update.GraphStoreFactory;
import org.apache.jena.update.UpdateAction;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
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
 	public static String MS = "http://followyourevent.com/";
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
 		try{/*
 			//s = FollowyoureventTDB.getFollowyoureventTDB().createPlace("Tidi", "calledetidi", "https://logotidi.com", "120");
 			//FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
 			//FollowyoureventTDB.getFollowyoureventTDB().getInformationOfPlace("http://followyourevent.com/place/Tidicalledetidi");
 			*/FollowyoureventTDB fye = FollowyoureventTDB.getFollowyoureventTDB();
 			System.out.println(fye.getDoubleToString(-1.1));
 			//fye.modifyPlace(MS+"place/hulencallehulen", "hulen", "callehulenmodi", "logomodi", "masqyeabtes", "paginaoficialmod", "yes", 60.51, 05.28);
 			//System.out.println(fye.getWebUrlOfAPlace(MS+"place/hulencallehulen"));
 			//arr = fye.getActualEventsNearToYou(5.05, 60.45);
 			//System.out.println(fye.getEventsBetweenDates("04", "15", "05", "30").toString());
 			//fye.createRecommendations(MS+"person/maildecade@gmail.com");
// 			arr = fye.recommendEvents(MS+"person/maildecade@gmail.com");
// 			System.out.println(arr.size());
 			//fye.createPerson("develascomikel@gmail.com", "Mikel", "21", "Male", "develask");
 			//fye.createPlace("Matxitxako", "Street 4 60", "http://static.panoramio.com/photos/original/10930791.jpg", "700", "http://www.matxitxako.com/", "No");
 			//fye.addOwnerToAPlace(MS+"place/"+("Matxitxako"+"Street 4 60").replaceAll(" ", ""), MS+"person/"+"develascomikel@gmail.com");
 			//fye.createEvent("EventNumber1", "http://definicion.mx/wp-content/uploads/2014/07/Evento.jpg", "https://social-kayak.rhcloud.com/", "21", "05", "10:00", "80");
 			//fye.write(System.out, "JSON-LD");
 			/*fye.createEvent("EventNumber2", "http://www.espaciomadrid.es/wp-content/uploads/2015/12/patinaje-navidad.jpg", "https://social-kayak.rhcloud.com/", "24", "05", "12:30", "100");
 			fye.addEventToAPerson(MS+"person/develascomikel@gmail.com", MS+"event/EventNumber10521");
 			fye.addEventToAPlace(MS+"place/"+("Matxitxako"+"Street 4 60").replaceAll(" ", ""), MS+"event/EventNumber10521");
 			fye.addEventToAPerson(MS+"person/develascomikel@gmail.com", MS+"event/EventNumber20524");
 			*/
 			//arr = fye.getInformationOfEvent(MS+"event/hulen0223");
// 			ArrayList<String> mios = fye.getAllThePlacesOfAPerson("develascomikel@gmail.com");
// 			System.out.println(mios);
 			//s = FollowyoureventTDB.getFollowyoureventTDB().addEventToAPerson(MS+"person/maildecade@gmail.com", MS+"event/hulen0223");
 			//FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
 			//System.out.println(s);
 			//fye.write(System.out, "JSON-LD");
 			//System.out.println("--------------------------------------------------------------");
 			//FollowyoureventTDB.getFollowyoureventTDB().modifyEvent(MS+"event/tidi528", "tidimod", "HTtps://urldeimagentidi.com/mod", "HTtps://urldeleventotid.com/", "28mod", "05mod", "22:00", "220krmod");
 			//fye.write(System.out, "JSON-LD");
 			//arr = FollowyoureventTDB.getFollowyoureventTDB().getActualEvents();
 			/*for (int i = 0; i < arr.size(); i++) {
 				System.out.println(arr.get(i).toString());
			}*/
 			//System.out.println(fye.eventIsFromAPerson(MS+"event/"+"EventNumber10521", MS+"person/"+"develascomikel@gmail.com"));
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
	public String[] getInformationAboutAPerson(String mail){
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
	 * @return ArrayList -> name, street, logo, capacity, url, auto, lat, long
	 */
	public String[] getInformationOfPlace(String uri){
		Resource reso = FollowyoureventTDB.getFollowyoureventTDB().getResource(uri);
		String[] arr = new String[8];
		String query = " PREFIX Prov: <http://www.w3.org/TR/prov-dm/> PREFIX DBpedia: <http://dbpedia.org/> PREFIX Geo: <http://www.w3.org/2003/01/geo/wgs84_pos#/>"
				+ "SELECT ?name ?street ?logo ?capacity ?auto ?url ?lat ?long "
				+ "WHERE { <"+reso+"> Prov:organization ?name ."
						+ " <"+reso+"> <"+VCARD.Street+"> ?street ."
						+ " <"+reso+"> DBpedia:logo ?logo ."
						+ " <"+reso+"> DBpedia:capacity ?capacity ."
						+ " <"+reso+"> Prov:primarySource ?url ."
						+ " <"+reso+"> DBpedia:auto ?auto ."
						+ " <"+reso+"> Geo:lat ?lat ."
						+ " <"+reso+"> Geo:long ?long }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.nextSolution();
	    	arr[0] = soln.getLiteral("name").toString();
	    	arr[1] = soln.getLiteral("street").toString();
	    	arr[2] = soln.getLiteral("logo").toString();
	    	arr[3] = soln.getLiteral("capacity").toString();
	    	arr[4] = soln.getLiteral("url").toString();
	    	arr[5] = soln.getLiteral("auto").toString();
	    	arr[6] = soln.getLiteral("lat").toString();
	    	arr[7] = soln.getLiteral("long").toString();
	    	return arr;
	    }else{
	    	return arr;
	    }
	}
	
	public String getWebUrlOfAPlace(String uriPlace){
		Resource reso = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPlace);
		String query = " PREFIX Prov: <http://www.w3.org/TR/prov-dm/> "
				+ "SELECT ?oficial "
				+ "WHERE { <"+reso+"> Prov:primarySource ?oficial }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.nextSolution();
	    	return soln.getLiteral("oficial").toString();
	    }else{
	    	return null;
	    }
	}
	
	/**
	 * 
	 * @param uriPlace
	 * @return Array -> list of events
	 */
	public ArrayList<String> getEventsOfAPlace(String uriPlace){
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
	    	return arr;
	    }    
	}
	
	/**
	 * 
	 * @param uri
	 * @return Arraylist -> name, image, url, day, month, hour, price
	 */
	public String[] getInformationOfEvent(String uri){
		String[] arr = new String[7];
		try {
			Resource reso = FollowyoureventTDB.getFollowyoureventTDB().getResource(uri);
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
		    	arr[0] = soln.getLiteral("name").toString();
		    	arr[1] = soln.getLiteral("image").toString();
		    	arr[2] = soln.getLiteral("url").toString();
		    	arr[3] = soln.getLiteral("day").toString();
		    	arr[4] = soln.getLiteral("month").toString();
		    	arr[5] = soln.getLiteral("hour").toString();
		    	arr[6] = soln.getLiteral("price").toString();
		    	return arr;
		    }else{
		    	return arr;
		    }
		}catch(Exception e){
			return arr;
		}
	} 
	
	
	/**
	 * 
	 * @param mail
	 * @return arraylist -> identificador de eventos; si no existe null
	 */
	public ArrayList<String> getAllTheEventsOfAPerson(String mail){
		ArrayList<String> arr = new ArrayList<String>();
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"person/"+mail);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "PREFIX own: <"+MS+"> "
				+ "SELECT ?ev WHERE { <"+person+"> <"+goes+"> ?ev }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
    	while (res.hasNext()) {
    		QuerySolution soln = res.next();
    		try{
    			String l = soln.getResource("ev").toString();
	    		arr.add(l);
    		}catch(Exception e){
    			
    		}
		}
    	return arr;
	}
	
	/**
	 * 
	 * @return ArrayList of all automatic places
	 */
	public ArrayList<String> getAllAutomaticPlaces(){
		ArrayList<String> arr = new ArrayList<String>();
		String query = "SELECT ?place WHERE { ?place <"+RDF.type+"> <http://followyourevent.com/place> }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
    	try{
    		if(res.hasNext()){
        		while (res.hasNext()) {
            		QuerySolution soln = res.next();
            		String place = soln.getResource("place").toString();
            		if(FollowyoureventTDB.getFollowyoureventTDB().isautomatic(place)){
            			arr.add(place);
            		}
        		}
            	return arr;
        	}else{
        		return arr;
        	}
    	}catch(Exception e){
    		
    	}
		return arr;
		
	}
	
	/**
	 * 
	 * @param mail
	 * @return if ok : Arraylist -> String (resources); if not null
	 */
	public ArrayList<String> getAllThePlacesOfAPerson(String mail){
		ArrayList<String> arr = new ArrayList<String>();
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource(MS+"person/"+mail);
		Property hasOwner = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"hasOwner");
		String query = "SELECT ?place WHERE { ?place <"+hasOwner+"> <"+person+"> }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
    	if(res.hasNext()){
    		while (res.hasNext()) {
        		QuerySolution soln = res.next();
        		try{
        			String l = soln.getResource("place").toString();
    	    		arr.add(l);
        		}catch(Exception e){
        			
        		}
    		}
        	return arr;
    	}else{
    		return arr;
    	}
	}
	
	/**
	 * 
	 * @param mail
	 * @return if ok : Arraylist -> String (resources); if not null 
	 */
	public ArrayList<String> getAllPastEventsOfAPerson(String uriPerson){
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.setTime(now);
		String day = ""+cal.get(Calendar.DAY_OF_MONTH);
		String month = ""+(cal.get(Calendar.MONTH) + 1);
		if (day.length()==1) day = "0"+day;
		if (month.length()==1) month = "0"+month;
		ArrayList<String> arr = new ArrayList<String>();
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?ev WHERE { <"+person+"> <"+goes+"> ?ev ."
				+ " ?ev DBpedia:month ?month ."
				+ " ?ev DBpedia:day ?day ."
				+ " FILTER (?month < '"+month+"' || (?day < '"+day+"' && ?month = '"+month+"')) }";
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
	    	return arr;
	    }
	}
	
	/**
	 * 
	 * @param mail
	 * @return if ok : Arraylist -> String (resources); if not null
	 */
	public ArrayList<String> getAllFutureEventsOfAPerson(String uriPerson){
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.setTime(now);
		String day = "" + cal.get(Calendar.DAY_OF_MONTH);
		String month = "" + (cal.get(Calendar.MONTH) + 1);
		if (day.length()==1) day = "0"+day;
		if (month.length() == 1) month = "0"+month;
		ArrayList<String> arr = new ArrayList<String>();
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?ev WHERE { <"+person+"> <"+goes+"> ?ev ."
				+ " ?ev DBpedia:month ?month ."
				+ " ?ev DBpedia:day ?day ."
				+ " FILTER (?month > '"+month+"' || (?day >= '"+day+"' && ?month = '"+month+"')) }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
    	while (res.hasNext()) {
    		QuerySolution soln = res.next();
    		try{
    			String l = soln.getResource("ev").toString();
	    		arr.add(l);
    		}catch(Exception e){
    			
    		}
		}
    	return arr;
	}
	
	/**
	 * 
	 * @param n
	 * @return String of the num with 0 if it is necessary 
	 */
	private String getNumToString(int n){
		return (n+"").length()==1?"0"+n:""+n;
	}
	
	/**
	 * 
	 * @param d
	 * @return String of the double if it is necessary
	 */
	public static String getDoubleToString(double d){
		String ds[] = (""+d).split("[.]");
		String[] minus = ds[0].split("-");
		String first = minus.length==1?minus[0]:minus[1];
		while(first.length()<3){
			first = "0"+first;
		}
		return (minus.length==1?"":"-")+first+"."+ds[1];
	}
	
	/**
	 * 
	 * @param lat
	 * @param longi
	 * @return
	 */
	public ArrayList<String> getActualEventsNearToYou(double lat, double longi){
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.setTime(now);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		ArrayList<String> arr = new ArrayList<String>();
		String query;
		if((day+7)>30){
			query = "PREFIX DBpedia: <http://dbpedia.org/> "
					+ " PREFIX Own: <http://followyourevent.com/> "
					+ " PREFIX Geo: <http://www.w3.org/2003/01/geo/wgs84_pos#/> "
					+ "SELECT ?ev ?month ?day ?lat ?long"
					+ "WHERE { ?ev DBpedia:month ?month ."
					+ " ?place Own:offers ?ev ."
					+ " ?ev DBpedia:day ?day ."
					+ " ?place Geo:lat ?lat ."
					+ " ?place Geo:long ?long ."
					+ " FILTER ( ?lat >= '"+(lat-0.3)+"' && '"+(lat+0.3)+"' <= ?lat && ?long >= '"+(longi-0.3)+"' && '"+(longi+0.3)+"' <= ?long && "
					+ "(('"+getNumToString((day+7)%30)+"' >= ?day && ?month = '"+getNumToString((month+1))+"') || ('"+getNumToString(day)+"' =< ?day && ?month = '"+getNumToString(month)+"'))) ";
		}else{
			System.out.println(getDoubleToString(lat-1.3));
			query = "PREFIX DBpedia: <http://dbpedia.org/> "
					+ " PREFIX Own: <http://followyourevent.com/> "
					+ " PREFIX Geo: <http://www.w3.org/2003/01/geo/wgs84_pos#/> "
					+ "SELECT ?ev ?month ?day ?lat ?long "
					+ "WHERE { ?ev DBpedia:month ?month ."
					+ " ?ev DBpedia:day ?day ."
					+ " ?place Own:offers ?ev ."
					+ " ?place Geo:lat ?lat ."
					+ " ?place Geo:long ?long ."
					+ " FILTER ( ?lat >= '"+getDoubleToString(lat-1.3)+"' && '"+getDoubleToString(lat+1.3)+"' >= ?lat && ?long >= '"+getDoubleToString(longi-1.3)+"' && '"+getDoubleToString(longi+1.3)+"' >= ?long )"
					+ " FILTER (('"+getNumToString(day)+"' <=  ?day && ?day <= '"+getNumToString((day+7))+"') && (?month = '"+getNumToString(month)+"')) }";
		}
		
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
	    	return arr;
	    }
	}
	
	/**
	 * 
	 * @return Arraylist Events 
	 */
	public ArrayList<String> getActualEvents(){
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.setTime(now);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH) + 1;
		ArrayList<String> arr = new ArrayList<String>();
		String query;
		if((day+7)>30){
			query = "PREFIX DBpedia: <http://dbpedia.org/> "
					+ "SELECT ?ev ?month ?day WHERE { ?ev DBpedia:month ?month ."
					+ " ?ev DBpedia:day ?day ."
					+ " FILTER (('"+getNumToString((day+7)%30)+"' >= ?day && ?month = '"+getNumToString((month+1))+"') || ('"+getNumToString(day)+"' =< ?day && ?month = '"+getNumToString(month)+"')) }";
		}else{
			query = "PREFIX DBpedia: <http://dbpedia.org/> "
					+ "SELECT ?ev ?month ?day WHERE { ?ev DBpedia:month ?month ."
					+ " ?ev DBpedia:day ?day ."
					+ " FILTER (('"+getNumToString(day)+"' <=  ?day && ?day <= '"+getNumToString((day+7))+"') && (?month = '"+getNumToString(month)+"')) }";
		}
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
	    	return arr;
	    }
	} 
	
	/**
	 * 
	 * @return Arraylist Events 
	 */
	public ArrayList<String> getEventsBetweenDates(String startMonth, String startDay, String endMonth, String endDay){
		ArrayList<String> arr = new ArrayList<String>();
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
					+ "SELECT ?ev ?month ?day WHERE { ?ev DBpedia:month ?month ."
					+ " ?ev DBpedia:day ?day ."
					+ " FILTER ((( '"+startDay+"' <= ?day && '"+startMonth+"' = ?month ) || '"+startMonth+"' < ?month ) && (( '"+endDay+"' >= ?day && '"+endMonth+"' = ?month ) || '"+endMonth+"' > ?month ))}";//&& '"+endDay+"' <=  ?day && '"+endMonth+"' <= ?month ) }";
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
	    	return arr;
	    }
	} 
	
	/**
	 * 
	 * @param uriEvent
	 * @return Array -> list of resources; if not null
	 */
	public ArrayList<String> getAllThePeopleFromAnEvent(String uriEvent){
		ArrayList<String> arr = new ArrayList<String>();
		Resource event = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriEvent);
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "SELECT (COUNT(?per) AS ?count) WHERE { ?per <"+goes+"> <"+event+"> }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	QuerySolution soln = res.next();
	    	//int count = Integer.parseInt(soln.getLiteral("count").toString());
	    	query = "SELECT ?per WHERE { ?per <"+goes+"> <"+event+"> }";
			res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
			if(res.hasNext()){
				int i=0;
				while (res.hasNext()) {
		    		try{
		    			soln = res.next();
		    			arr.add(soln.getResource("per").toString());
		    		}catch(Exception e){
		    			
		    		}
	    		}
				return arr;
			}else{
				return arr;
			}
	    	
	    }else{
	    	return arr;
	    }
	}
	
	/**
	 * 
	 * @param mail
	 * @param pass
	 * @return true if equals; false if not equals
	 */
	public boolean confirmPass(String mail, String pass){
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
	 * @param oficialweb
	 * @param auto
	 * @param lat
	 * @param longi  
	 * @return true if ok; false if not created
	 */
	public String createPlace(String placeName, String street, String logo, String capacity, String oficialweb, String auto, double lat, double longi){
		Property porganization = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/organization");
		Property plogo = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/logo");
		Property pcapacity = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/capacity");
		Property pauto = FollowyoureventTDB.getFollowyoureventTDB().createProperty("http://dbpedia.org/auto");
		Property primarySource = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/primarySource");
		Property plat = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/2003/01/geo/wgs84_pos#/lat");
		Property plong = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/2003/01/geo/wgs84_pos#/long");
		Resource place = FollowyoureventTDB.getFollowyoureventTDB().getResource("http://followyourevent.com/place");
		String slat = this.getDoubleToString(lat);
		String slong = this.getDoubleToString(longi);
		if(!existPlace(placeName, street)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"place/"+(placeName+street).replaceAll(" ", ""));
			res.addLiteral(porganization, placeName);
			res.addLiteral(VCARD.Street, street);
			res.addLiteral(plogo, logo);
			res.addLiteral(pcapacity, capacity);
			res.addLiteral(pauto,auto);
			res.addLiteral(primarySource, oficialweb);
			res.addProperty(RDF.type, place);
			res.addLiteral(plat, slat);
			res.addLiteral(plong, slong);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return res.toString();
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @param description
	 * @param kind
	 * @return if created true; if not false
	 */
	public String createStyle(String description, String kind){
		Property pKind = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/kind");
		Resource style = FollowyoureventTDB.getFollowyoureventTDB().getResource("http://followyourevent.com/style");
		if(!existStyle(kind)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"tyle/"+kind);
			res.addLiteral(SKOS.definition, description);
			res.addLiteral(pKind, kind);
			res.addProperty(RDF.type, style);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return res.toString();
		}else{
			return null;
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
	public String createEvent(String name, String image, String url, String day, String month, String hour, String price){
		Property eventname = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/event");
		Property pimage = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://purl.org/dc/dcmitype/image");
		Property primarySource = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/primarySource");
		Property pday = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/day");
		Property pmonth = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/month");
		Property start = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/start");
		Property pprice = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://schema.org/price");
		Resource event = FollowyoureventTDB.getFollowyoureventTDB().getResource("http://followyourevent.com/event");
		if(!existEvent(name, month, day)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"event/"+(name+month+day).replaceAll(" ", ""));
			res.addLiteral(eventname, name);
			res.addLiteral(pimage, image);
			res.addLiteral(primarySource, url);
			res.addLiteral(pday, day);
			res.addLiteral(pmonth, month);
			res.addLiteral(start, hour);
			res.addLiteral(pprice, price);
			res.addProperty(RDF.type, event);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return res.toString();
		}else{
			return null;
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
	public String createPerson(String mail, String name, String age, String sex, String pass){
		Property Ppass = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://followyourevent.com/vocabulary/pass");
		Property Page = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://followyourevent.com/vocabulary/age");
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().getResource("http://followyourevent.com/person");
		if(!existPerson(mail)){
			Resource res = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"person/"+mail);
			res.addLiteral(FOAF.mbox, mail);
			res.addLiteral(FOAF.givenname, name);
			res.addLiteral(Page, age);
			res.addLiteral(FOAF.gender, sex);
			res.addLiteral(Ppass, pass);
			res.addProperty(RDF.type,person);
			FollowyoureventTDB.getFollowyoureventTDB().commit();
			return res.toString();
		}else{
			return null;
		}	
	}
	
	/**
	 * 
	 * @param uriPlace
	 * @return if is automatic true; if not false
	 */
	public boolean isautomatic(String uriPlace){
		String query = "PREFIX DBpedia: <http://dbpedia.org/> "
				+ "SELECT ?auto WHERE { <"+uriPlace+"> DBpedia:auto ?auto }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	String auto = res.next().getLiteral("auto").toString();
	    	if(auto.equals("Yes")){
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
	 * @param kind
	 * @return true if exist; false if not exist
	 */
	public boolean existStyle(String kind){
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
	public boolean existEvent(String name,String month, String day){
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
	public boolean existPlace(String name, String street){
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
	public boolean existPerson(String mail){
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
	public boolean existStatement(Resource res1, Property prop, Resource res2){
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
	public boolean addStyleToAnEvent(String uriEvent, String uriStyle){
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
	public boolean removeStyleFromAnEvent(String uriEvent, String uriStyle){
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
	public boolean addlikeablePlaceToAPerson(String uriPerson, String uriPlace){
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
	public boolean removeLikeablePlaceToAPerson(String uriPerson, String uriPlace){
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
	
	/**
	 * 
	 * @param uriPlace
	 * @param uriPerson
	 * @return if added true; if not false;
	 */
	public boolean addOwnerToAPlace(String uriPlace,String uriPerson){
		try{
			Resource resPer = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
			Resource resPlace = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPlace);
			Property hasOwner = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"hasOwner");
			if(!existStatement(resPer, hasOwner, resPlace)){
				Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(resPlace, hasOwner, resPer);
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
	public boolean addEventToAPlace(String uriPlace, String uriEvent){

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
	public boolean removeEventFromAPlace(String uriPlace, String uriEvent){
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
	public boolean addEventToAPerson(String uriPerson, String uriEvent){
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
	public boolean removeEventFromAPerson(String uriPerson, String uriEvent){
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
	
	/**
	 * 
	 * @param uriPerson
	 */
	public void createRecommendations(String uriPerson){
		Resource resPers = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
		String query = "PREFIX Own:<http://followyourevent.com/> "
				+ "CONSTRUCT {"
				+ " <"+uriPerson+"> Own:mayInterested ?fev ."
				+ "} WHERE {"
				+ " ?res Own:goes ?fev ."
					+ "{"
						+ " SELECT ?fev (COUNT(?fev) AS ?count)"
						+ " WHERE {"
							+ " ?per Own:goes ?fev"
							+ "{"
								+ " SELECT ?per "
								+ " WHERE {"
									+ "?per Own:goes ?ev ."
									+ "{"
										+ "SELECT ?ev "
										+ "WHERE { "
							   				+"<"+resPers+"> Own:goes ?ev "
						   				+ "}"
					   				+ "}"
				   				+ "}"
			   				+ "}"
		   				+ "} GROUP BY ?fev ORDER BY DESC(?count) LIMIT 10"
	   				+ "}"
   				+ "}";
		QueryExecution queryExecution = QueryExecutionFactory.create(query, dataset);
		Model resultModel = queryExecution.execConstruct();
		FollowyoureventTDB.getFollowyoureventTDB().addNewModel(resultModel);
	}
	
	/**
	 * 
	 * @param uriPerson
	 * @return if Ok: ArrayList -> String (Resource); if not null
	 */
	public ArrayList<String> recommendEvents(String uriPerson){
		ArrayList<String> arr = new ArrayList<String>();
		Resource resPers = FollowyoureventTDB.getFollowyoureventTDB().getResource(uriPerson);
		String query = "PREFIX Own:<http://followyourevent.com/> "
					+ "SELECT ?ev WHERE {"
					+ "<"+resPers+"> Own:mayInterested ?ev }";
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
    		return arr;
    	}	
	}
	
	/**
	 *  
	 * @param uriEvent
	 * @param uriPerson
	 * @return true if that event was created by this person
	 */
	public boolean eventIsFromAPerson(String uriEvent, String uriPerson){
		String uriPlace = getPlaceOfAnEvent(uriEvent);
		return placeOwnerOfAPerson(uriPerson, uriPlace);
	}
	
	/**
	 * update the events of the places
	 */
	public void updateAutomaticPlaces(){
		ArrayList<String> automatics = getAllAutomaticPlaces();
		for (int i = 0; i < automatics.size(); i++) {
			String uri = FollowyoureventTDB.getFollowyoureventTDB().getWebUrlOfAPlace(automatics.get(i));
			String[][] events = FollowyoureventTDB.getFollowyoureventTDB().getSpecificInfo(uri);
			FollowyoureventTDB.getFollowyoureventTDB().updateEvents(events);
		}
	}
	
	/**
	 * Update the events of one of the places, it is
	 * @param events
	 */
	public void updateEvents(String[][] events){
		for (int i = 0; i < events.length; i++) {
			if(existEvent(name, month, day)){
				modifyEvent(uriEvent, name, image, url, day, month, hour, price);
			}else{
				createEvent(name, image, url, day, month, hour, price);
			}
		}
	}
	
	/**
	 * 
	 * @param uri
	 * @return Doble Array of information of the events in a place
	 */
	public String[][] getSpecificInfo(String uri){
		Document doc = null;
		Elements newsHeadlines;
		Element el;
		Elements els;
		String[][] names = null;
		int ind = 0;
		switch (uri) {
		case "http://hulen.no/":
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
		default:
			break;
		}
		
		return names;
	}
	
	/**
	 * 
	 * @param uriPlace
	 * @param placeName
	 * @param street
	 * @param logo
	 * @param capacity
	 * @param oficialweb
	 * @param auto
	 * @param lat
	 * @param longi
	 */
	public void modifyPlace(String uriPlace, String placeName, String street, String logo, String capacity, String oficialweb, String auto, double lat, double longi){
		Resource respla = FollowyoureventTDB.getFollowyoureventTDB().createResource(uriPlace);
		Property pplaceName = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/organization");
		Property pstreet = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/vcard-rdf/street");
		Property plogo = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/logo");
		Property pcapacity = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/capacity");
		Property pprimarySource = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/primarySource");
		Property pauto = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/auto");
		Property plat = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/2003/01/geo/wgs84_pos#/lat");
		Property plong = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/2003/01/geo/wgs84_pos#/long");
		String slat = this.getDoubleToString(lat);
		String slong = this.getDoubleToString(longi);
		UpdateRequest update = UpdateFactory.create("DELETE { <"+respla+"> <"+pplaceName+"> ?placename ."
						+ " <"+respla+"> <"+pstreet+"> ?street ."
						+ " <"+respla+"> <"+plogo+"> ?logo ."
						+ "	<"+respla+"> <"+pcapacity+"> ?capacity ."
						+ " <"+respla+"> <"+pprimarySource+"> ?oficialweb ."
						+ " <"+respla+"> <"+pauto+"> ?auto ."
						+ " <"+respla+"> <"+plat+"> ?lat ."
						+ " <"+respla+"> <"+plong+"> ?long }"
				+ "INSERT { <"+respla+"> <"+pplaceName+"> '"+placeName+"' ."
						+ " <"+respla+"> <"+pstreet+"> '"+street+"' ."
						+ " <"+respla+"> <"+plogo+"> '"+logo+"' ."
						+ " <"+respla+"> <"+pcapacity+"> '"+capacity+"' ."
						+ " <"+respla+"> <"+pprimarySource+"> '"+oficialweb+"' ."
						+ "	<"+respla+"> <"+pauto+"> '"+auto+"' ."
						+ " <"+respla+"> <"+plat+"> '"+slat+"' ."
						+ " <"+respla+"> <"+plong+"> '"+slong+"' }"
				+ "WHERE { <"+respla+"> <"+pplaceName+"> ?placename ."
					+ " <"+respla+"> <"+pstreet+"> ?street ."
					+ " <"+respla+"> <"+plogo+"> ?logo ."
					+ " <"+respla+"> <"+pcapacity+"> ?capacity ."
					+ " <"+respla+"> <"+pprimarySource+"> ?oficialweb ."
					+ "	<"+respla+"> <"+pauto+"> ?auto ."
					+ " <"+respla+"> <"+plat+"> ?lat ."
					+ " <"+respla+"> <"+plong+"> ?long }");
		
		UpdateAction.execute(update, dataset);
		FollowyoureventTDB.getFollowyoureventTDB().commit();
	}
	
	/**
	 * 
	 * @param uriEvent
	 * @param name
	 * @param image
	 * @param url
	 * @param day
	 * @param month
	 * @param hour
	 * @param price
	 */
	public void modifyEvent(String uriEvent,String name, String image, String url, String day, String month, String hour, String price){
		
		Resource resev = FollowyoureventTDB.getFollowyoureventTDB().createResource(uriEvent);
		Property eventname = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/event");
		Property pimage = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://purl.org/dc/dcmitype/image");
		Property primarySource = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/primarySource");
		Property pday = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/day");
		Property pmonth = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://dbpedia.org/month");
		Property start = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://www.w3.org/TR/prov-dm/start");
		Property pprice = FollowyoureventTDB.getFollowyoureventTDB().getProperty("http://schema.org/price");
		UpdateRequest update = UpdateFactory.create("DELETE { <"+resev+"> <"+eventname+"> ?name ."
						+ " <"+resev+"> <"+pimage+"> ?image ."
						+ " <"+resev+"> <"+primarySource+"> ?primary ."
						+ " <"+resev+"> <"+pday+"> ?day ."
						+ " <"+resev+"> <"+pmonth+"> ?month ."
						+ "	<"+resev+"> <"+start+"> ?start ."
						+ " <"+resev+"> <"+pprice+"> ?price }"
				+ "	INSERT { <"+resev+"> <"+eventname+"> '"+name+"' ."
						+ " <"+resev+"> <"+pimage+"> '"+image+"' ."
						+ " <"+resev+"> <"+primarySource+"> '"+url+"' ."
						+ " <"+resev+"> <"+pday+"> '"+day+"' ."
						+ " <"+resev+"> <"+pmonth+"> '"+month+"' ."
						+ "	<"+resev+"> <"+start+"> '"+hour+"' ."
						+ " <"+resev+"> <"+pprice+"> '"+price+"' }"
				+ "WHERE { <"+resev+"> <"+eventname+"> ?name ."
					+ " <"+resev+"> <"+pimage+"> ?image ."
					+ " <"+resev+"> <"+primarySource+"> ?primary ."
					+ " <"+resev+"> <"+pday+"> ?day ."
					+ " <"+resev+"> <"+pmonth+"> ?month ."
					+ "	<"+resev+"> <"+start+"> ?start ."
					+ " <"+resev+"> <"+pprice+"> ?price }");
		
		UpdateAction.execute(update, dataset);	  
		FollowyoureventTDB.getFollowyoureventTDB().commit();
	}
	
	public boolean placeOwnerOfAPerson(String uriPerson, String uriPlace){
		String query = "SELECT ?peo WHERE { <"+uriPlace+"> <"+MS+"hasOwner> <"+uriPerson+"> }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
	    	return true;
	    }else{
	    	return false;
	    }
	}
	
	/**
	 * 
	 * @param uriPerson
	 * @param uriEvent
	 * @return true if assist; false if not
	 */
	public boolean PersonAssist(String uriPerson, String uriEvent){
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"goes");
		String query = "SELECT * WHERE { <"+uriPerson+"> <"+goes+"> <"+uriEvent+"> }";
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
	 * @return String uriPlace
	 */
	public String getPlaceOfAnEvent(String uriEvent){
		String query = "PREFIX Own: <http://followyourevent.com/>"
				+ "SELECT ?place WHERE { ?place Own:offers <"+uriEvent+"> }";
		ResultSet res = FollowyoureventTDB.getFollowyoureventTDB().selectQuery(query);
	    if(res.hasNext()){
    		QuerySolution soln = res.next();
    		try{
    			return soln.getResource("place").toString();
    		}catch(Exception e){
    			return null;
    		}
	    }else{
	    	return null;
	    } 
	}
}


