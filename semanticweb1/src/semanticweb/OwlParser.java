package semanticweb;

import java.io.File;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.FileManager;
//import java.io.FileOutputStream;


public class OwlParser {
	public static final String qstring=
			"PREFIX emoji:<http://www.semanticweb.org/manish/ontologies/2016/0/emoji_ontology#>"+
			"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+
            "SELECT ?an WHERE {"+
     "?em emoji:hasAnnotation ?an. "+
         "?em emoji:unicode ?unicode."+
      "FILTER( ?unicode = \"";
	public static final String qstring_p="\"^^xsd:hexBinary)}";
	private Model model;
	public void init(){
		FileManager.get().addLocatorClassLoader(OwlParser.class.getClassLoader());
		model=FileManager.get().loadModel("emoji_1.owl");
		
		
	}
	
	public ResultSet findAnnotations(int unicode){
		String ustring=String.format("%x", unicode);
		Query query= QueryFactory.create(qstring+ustring+qstring_p);
		QueryExecution qexec=QueryExecutionFactory.create(query, model);
		return qexec.execSelect();
	}
	
	public Model getModel(){
		return model;
	}
	
	
}
