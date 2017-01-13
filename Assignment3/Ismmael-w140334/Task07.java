package ontologyapi;

import java.io.InputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 * Task 07: Querying ontologies (RDFs)
 * @author Ismael Ayat Ortiz - w140334 - Ismmael
 *
 */
public class Task07
{
	public static String ns = "http://somewhere#";
	
	public static void main(String args[])
	{
		String filename = "example6.rdf";
		
		// Create an empty model
		OntModel model = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM);
		
		// Use the FileManager to find the input file
		InputStream in = FileManager.get().open(filename);
	
		if (in == null)
			throw new IllegalArgumentException("File: "+filename+" not found");
	
		// Read the RDF/XML file
		model.read(in, null);
		
		
		// ** TASK 7.1: List all individuals of "Person" **
		System.out.println("Task 7.1");
		System.out.println("");

		Resource x = model.getResource(ns+"Person");
		ExtendedIterator<Individual> iterador = model.listIndividuals(x);
		int size = 1;
		while(iterador.hasNext())
		{
			System.out.println("Person " + size + " : " + iterador.next().getURI());
			size++;
		}

		System.out.println("");

		// ** TASK 7.2: List all subclasses of "Person" **

		System.out.println("Task 7.2");
		System.out.println("");

		ExtendedIterator<OntClass> iterador2 = x.listSubClasses();
		int size2 = 1;
		while(iterador2.hasNext()){
			System.out.println("Subclasses: "+ size2 + " : " + iterador2.next().getURI());
			size2++;
		}
		System.out.println("");
		
		// ** TASK 7.3: Make the necessary changes to get as well indirect instances and subclasses. TIP: you need some inference... **
	
		System.out.println("Task 7.3");	
		System.out.println("");
		System.out.println("Individuals:")
		OntClass personas = ModelFactory.createOntologyModel(OntModelSpec.RDFS_MEM_RDFS_INF, model).getOntClass(ns+"Person");
		for (ExtendedIterator<Individual> i = (ExtendedIterator<Individual>) personas.listInstances(); i.hasNext()){
		 System.out.println(i.next().getURI());
	}
		
		System.out.println("");
		System.out.println("Subclasses:");
		for (ExtendedIterator<OntClass> i = persons.listSubClasses(); i.hasNext()){
		   System.out.println(i.next().getURI()); 
		}; 
	}
}
