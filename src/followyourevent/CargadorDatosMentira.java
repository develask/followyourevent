package followyourevent;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.vocabulary.DCTerms;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

import followyourevent.FollowyoureventTDB;

public class CargadorDatosMentira {

	public static void main(String[] args) {
		
		//URI
		String MS = "http://followyourevent.com/";
		String own = "http://followyourevent.com/vocabulary/";
		String prov = "http://www.w3.org/TR/prov-dm/";
		String dbPedia = "http://dbpedia.org/";
		String dcterms = "http://purl.org/dc/dcmitype/";
		String schema = "http://schema.org/";
		//Create person
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"person");
		
		Property pass =  FollowyoureventTDB.getFollowyoureventTDB().createProperty(own+"pass");
		
		Property age = FollowyoureventTDB.getFollowyoureventTDB().createProperty(own+"age");
		age.addProperty(RDFS.subPropertyOf, FOAF.Agent);
		
		Property organization = FollowyoureventTDB.getFollowyoureventTDB().createProperty(prov+"organization");
		
		Property eventname = FollowyoureventTDB.getFollowyoureventTDB().createProperty(dbPedia+"event");
		
		Property image = FollowyoureventTDB.getFollowyoureventTDB().createProperty(dcterms+"image");
		
		Property primarySource = FollowyoureventTDB.getFollowyoureventTDB().createProperty(prov+"primarySource");
		
		Property day = FollowyoureventTDB.getFollowyoureventTDB().createProperty(dbPedia+"day");
		
		Property month = FollowyoureventTDB.getFollowyoureventTDB().createProperty(dbPedia+"month");
		
		Property start = FollowyoureventTDB.getFollowyoureventTDB().createProperty(prov+"start");

		Property price = FollowyoureventTDB.getFollowyoureventTDB().createProperty(schema+"price");
		
		Property logo = FollowyoureventTDB.getFollowyoureventTDB().createProperty(dbPedia+"logo");
		
		Property capacity = FollowyoureventTDB.getFollowyoureventTDB().createProperty(dbPedia+"capacity");
		
		Property kind = FollowyoureventTDB.getFollowyoureventTDB().createProperty(dbPedia+"kind");
		
		Property auto = FollowyoureventTDB.getFollowyoureventTDB().createProperty("http://dbpedia.org/auto");
		
		Property plat = FollowyoureventTDB.getFollowyoureventTDB().createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#/lat");
		
		Property plong = FollowyoureventTDB.getFollowyoureventTDB().createProperty("http://www.w3.org/2003/01/geo/wgs84_pos#/long");
		
		//Create Cade tracy
		Resource CadeTracy = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"person/maildecade@gmail.com");
		CadeTracy.addLiteral(FOAF.mbox, "maildecade@gmail.com");
		CadeTracy.addLiteral(FOAF.givenname, "CadeTracy");
		CadeTracy.addLiteral(FOAF.gender,"Female");
		CadeTracy.addLiteral(age, "21");
		CadeTracy.addLiteral(pass, "mentira");
		//Create Ines Dominguez
		Resource InesDominguez = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"person/maildeines@gmail.com");
		InesDominguez.addLiteral(FOAF.mbox, "maildeines@gmail.com");
		InesDominguez.addLiteral(FOAF.givenname, "InesDominguez");
		InesDominguez.addLiteral(FOAF.gender,"Female");
		InesDominguez.addLiteral(age, "25");
		InesDominguez.addLiteral(pass, "mentira2");
		//Cade Tracy is a person
		//CadeTracy.addProperty(RDF.type,person);
		//Ines Dominguez is a person
		//InesDominguez.addProperty(RDF.type,person);
		
		Resource place = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"place");
		
		Resource hulen = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"place/hulencallehulen");
		hulen.addLiteral(organization, "hulen");
		hulen.addLiteral(VCARD.Street, "callehulen");
		hulen.addLiteral(logo, "urllogo");
		hulen.addLiteral(capacity, "250");
		hulen.addLiteral(auto, "Yes");
		hulen.addLiteral(primarySource, "paginaoficial");
		hulen.addProperty(RDF.type, place);
		hulen.addLiteral(plat, "060.38");
		hulen.addLiteral(plong, "005.32");
		
		Resource tidi = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"place/tidicalletidi");
		tidi.addLiteral(organization, "tidi");
		tidi.addLiteral(VCARD.Street, "calletidi");
		tidi.addLiteral(logo, "urllogo");
		tidi.addLiteral(capacity, "250");
		tidi.addLiteral(auto, "Want");
		tidi.addLiteral(primarySource, "paginaoficial");
		tidi.addProperty(RDF.type, place);
		tidi.addLiteral(plat, "060.90");
		tidi.addLiteral(plong, "005.80");
		
		//Create PostalAddress of Cade Tracy
		Resource ev = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"event/hulen0411");
		
		ev.addLiteral(eventname, "hulen");
		ev.addLiteral(image, "HTtps://urldeimagen.com/");
		ev.addLiteral(primarySource, "HTtps://urldelevento.com/");
		ev.addLiteral(day, "22");
		ev.addLiteral(month, "04");
		ev.addLiteral(start, "21:00");
		ev.addLiteral(price, "200kr");
		
		Resource ev2 = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"event/tidi0510");
		
		//ev2.addProperty(RDF.type, event);
		ev2.addLiteral(eventname, "tidi");
		ev2.addLiteral(image, "HTtps://urldeimagentidi.com/");
		ev2.addLiteral(primarySource, "HTtps://urldeleventotid.com/");
		ev2.addLiteral(day, "25");
		ev2.addLiteral(month, "04");
		ev2.addLiteral(start, "21:00");
		ev2.addLiteral(price, "220kr");
		
		//hulen offers party hulen
		Property offers = FollowyoureventTDB.getFollowyoureventTDB().getProperty(MS+"offers");
		
		//create offers statement
		Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(hulen, offers, ev);
		
		//Adding the statement
		FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		
		//create offers statement
		stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(tidi, offers, ev2);
		
		//Adding the statement
		FollowyoureventTDB.getFollowyoureventTDB().add(stmt);	
		
		
		//Create the property goes
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().createProperty(MS+"goes");
		
		//create stmt
		stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(CadeTracy, goes, ev);
		//Adding the statement
		FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		
		//create stmt
		stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(InesDominguez, goes, ev);
		//Adding the statement
		FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		
		//create stmt
		stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(InesDominguez, goes, ev2);
		//Adding the statement
		FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		
		FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
		
		FollowyoureventTDB.getFollowyoureventTDB().commit();
	}

}
