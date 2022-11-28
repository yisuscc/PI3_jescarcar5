package Ejercicios;

import org.jgrapht.graph.SimpleDirectedGraph;

import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Ejercicio1 {
public static record Persona2 (Integer id , String nombre,Integer year,String Ciudad) {
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
		
		
		
		

	}

}
