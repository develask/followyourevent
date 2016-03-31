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
		//Create person
		Resource person = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Person");
		
		Property pass =  FollowyoureventTDB.getFollowyoureventTDB().createProperty(own+"pass");
		
		Property age = FollowyoureventTDB.getFollowyoureventTDB().createProperty(own+"age");
		age.addProperty(RDFS.subPropertyOf, FOAF.Agent);
		
		//Create Cade tracy
		Resource CadeTracy = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"CadeTracy");
		CadeTracy.addLiteral(FOAF.mbox, "maildecade@gmail.com");
		CadeTracy.addLiteral(FOAF.givenname, "CadeTracy");
		CadeTracy.addLiteral(pass, "mentira");
		//Create Ines Dominguez
		//Resource InesDominguez = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"InesDominguez");
		
		//Cade Tracy is a person
		CadeTracy.addProperty(RDF.type,person);
		//Ines Dominguez is a person
		//InesDominguez.addProperty(RDF.type,person);
		
		Resource event = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event");
		//Resource place = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Place");
		//Create PostalAddress of Cade Tracy
		Resource ev = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event/Hulen");
		
		ev.addProperty(RDF.type, event);
		ev.addProperty(VCARD.NAME, "Hulen");
		ev.addProperty(VCARD.PHOTO, "direccion");
		ev.addProperty(DCTerms.date, "20150226");
		ev.addProperty(DCTerms.date, "12:05");
		ev.addProperty(RDFS.label, "100kr");
		ev.addProperty(RDFS.label, "link");
		
		Resource ev2 = FollowyoureventTDB.getFollowyoureventTDB().createResource(MS+"Event/Tidi");
		
		ev2.addProperty(RDF.type, event);
		ev2.addProperty(VCARD.NAME, "Tidi");
		ev2.addProperty(VCARD.PHOTO, "direccionTIDI");
		ev2.addProperty(DCTerms.date, "20150227");
		ev2.addProperty(DCTerms.date, "12:15");
		ev2.addProperty(RDFS.label, "90kr");
		ev2.addProperty(RDFS.label, "linkTidi");
		
		/*//Create PostalAddress of Ines Dominguez
		Resource PostalAddressInesDominguez = FollowyoureventTDB.createResource("");
		
		//Add information about her address
		PostalAddressInesDominguez.addLiteral(VCARD.Country, "SPAIN");
		PostalAddressInesDominguez.addLiteral(VCARD.Locality, "Valencia");
		PostalAddressInesDominguez.addLiteral(VCARD.Pobox, "46020");
		PostalAddressInesDominguez.addLiteral(VCARD.Street, "Carrer de la Guardia Civil 20");
		*/
		
		//Create the property goes
		Property goes = FollowyoureventTDB.getFollowyoureventTDB().createProperty(MS+"goes");
		
		//create stmt
		//Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(InesDominguez, goes, ev);
		//Adding the statement
		//FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		
		//create stmt
		Statement stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(CadeTracy, goes, ev);
		//Adding the statement
		FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		
		//create stmt
		stmt = FollowyoureventTDB.getFollowyoureventTDB().createStatement(CadeTracy, goes, ev2);
		//Adding the statement
		FollowyoureventTDB.getFollowyoureventTDB().add(stmt);
		
		FollowyoureventTDB.getFollowyoureventTDB().write(System.out, "JSON-LD");
		
		FollowyoureventTDB.getFollowyoureventTDB().commit();
	}

}
