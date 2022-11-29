package Ejercicios;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.DefaultAttribute;

import us.lsi.colors.GraphColors;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class Ejercicio1 {
public static record Persona2 (Integer id , String nombre,Integer year,String Ciudad) {
	//para que no se confunda con el record de miguel toro 
	public static Persona2  of (String s) {
		String[] x = s.split(",");
		return new Persona2(Integer.valueOf(x[0]), x[1],Integer.parseInt(x[2]),x[3]);
	}
	public static Persona2  ofFormat (String[]x) {
		return new Persona2(Integer.valueOf(x[0]), x[1], Integer.parseInt(x[2]),x[3]);
	}
}




	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// el grafo es dirigido simple sin pesos
		 
		// apartado a simplemente un predicado y estilizar 
		
		//lo he hcho segun el ejercico deleeRelaciones de Varios ejemplos.java
		
		Graph<Persona2, Object> grafo1 = GraphsReader.newGraph("ficheros/Alumnos/PI3E1A_DatosEntrada.txt",
				Persona2::ofFormat, s-> new DefaultEdge(),Graphs2::simpleDirectedGraph);
		Graph<Persona2, Object> grafo2 = GraphsReader.newGraph("ficheros/Alumnos/PI3E1B_DatosEntrada.txt",
				Persona2::ofFormat, s-> new DefaultEdge(),Graphs2::simpleDirectedGraph);
		
		try {
			GraphColors.toDot(grafo2, "ficheros/Alumnos/prueva.gv");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		

	}

}
