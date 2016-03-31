package followyourevent;

import java.io.PrintStream;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
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
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;

public class FollowyoureventTDB {

 	private static Model model=null;
 	private static InfModel rdfsmodel=null;
 	private static Dataset dataset=null;
 	private static QueryExecution qexec=null;
 	//TODO cuidado
 	private static String OPENSHIFT_DATA_DIR="MyDatabases";//"OPENSHIFT_DATA_DIR";
 	private static FollowyoureventTDB myFollowyoureventTDB=null;

 	private FollowyoureventTDB() {
 		makeDataset();
 		createModel();
 	}
 	
 	public static FollowyoureventTDB getFollowyoureventTDB(){
 		if(FollowyoureventTDB.myFollowyoureventTDB==null){
 			FollowyoureventTDB.myFollowyoureventTDB = new FollowyoureventTDB();
 			/*Resource place = createResource("pre:http://followyourevent-upv.rhcloud.com/Place");
 			rdfsmodel.createResource("pre:http://followyourevent-upv.rhcloud.com/Hulen")
 				.addProperty(RDF.type, place);
 			rdfsmodel.write(System.out, "JSON-LD");*/
 		}
 		return FollowyoureventTDB.myFollowyoureventTDB;
 	}
	
	private void makeDataset(){
		//this will be the directory that we will use to save the data
		String envVar = System.getenv(OPENSHIFT_DATA_DIR);
		//create the dataset
		//TODO cambiar variable
     	dataset = TDBFactory.createDataset(OPENSHIFT_DATA_DIR);
	}

	private void createModel(){
        //obtain the model from the dataset
        model = dataset.getDefaultModel();
		rdfsmodel = ModelFactory.createRDFSModel(model);
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
			dataset.begin(ReadWrite.READ);
 			Query quer = QueryFactory.create(query);
 	        qexec = QueryExecutionFactory.create(quer, rdfsmodel.getRawModel());
 	        try {
 	        	results = qexec.execSelect() ;
           	} finally { 
         		//rdfsmodel.close();
           		dataset.end(); 
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
		Resource res=null;
		try{
			res = rdfsmodel.getResource(uri);
		}catch(Exception e){
			res=null;
		}
		return res;
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
	
	public Statement getStatement(){
		return null;
	}
	
	public void updateQuery(String query){
		if(!query.contains("Select")){
			try{
				GraphStore graphStore = GraphStoreFactory.create(dataset);
				//allow to Write 
				dataset.begin(ReadWrite.WRITE);
				//Create request	
				UpdateRequest request = UpdateFactory.create(query);
	        	//Create processor 
	        	UpdateProcessor proc = UpdateExecutionFactory.create(request, graphStore);
	        	//execute
	        	proc.execute();
			} finally{
				rdfsmodel.commit();
        		dataset.end();
			}
		}else{
			System.out.println("The query is not a Update query");
		}
	}
	
	public void write(PrintStream out, String way) {
	//	dataset.begin(ReadWrite.READ);
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

}	
