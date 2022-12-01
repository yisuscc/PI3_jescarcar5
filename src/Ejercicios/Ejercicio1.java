package Ejercicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.DefaultAttribute;

import us.lsi.colors.GraphColors;
import us.lsi.common.List2;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.views.SubGraphView;


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

//TODO APARTADO A
public static Graph<Persona2,DefaultEdge>apartadoA(Graph<Persona2, DefaultEdge>g){
// averiguamos quien tiene los  padres 
Set<Persona2> s = new HashSet<>();
for (Persona2 p : g.vertexSet()) {
	//Boolean a = 
	//Graphs.predecessorListOf(g,p).stream().map(p-> Pair);
if(Graphs.vertexHasPredecessors(g, p)) {
	List<Persona2> lsAux = new ArrayList<>();
}
	
}
}
//TODO APARTADO B
public static Set<Persona2> apartadoB(Graph<Persona2, DefaultEdge>g,Persona2 p){
Set<Persona2> s = new HashSet<>();
apartadoBaux(g, p, s);
	return s;
}
private static void apartadoBaux(Graph<Persona2, DefaultEdge>g,Persona2 p, Set<Persona2> s) {
List<Persona2>ls = 	Graphs.predecessorListOf(g,p );
if(ls.size()!=0 ) {
	for(Persona2 paux : ls) {
		apartadoBaux(g, paux, s);
		s.add(paux);
	}
}
}

// APARTADO C
public enum Relacion{Hermanos,Primos,Otros};
private static Relacion apartadoC(Graph<Persona2, DefaultEdge>grafo,Persona2 p1, Persona2 p2) {
// si el camino minimo es 4 son primos
	// si el camino es 2 son hermanos
	// otro otros
ShortestPathAlgorithm<Persona2, DefaultEdge> a = new DijkstraShortestPath<>(Graphs.undirectedGraph(grafo));
Double  s  = a.getPathWeight(p1, p2);

Relacion res;
if(s == 2.0) {
	res = Relacion.Hermanos;
}else if (s==4.0) {
	res = Relacion.Primos;
}else {
	res = Relacion.Otros;
}
return null; 
}
//ApartadoD:
public static Set<Persona2>apartadoD(Graph<Persona2, DefaultEdge>grafo) {
Set<Persona2> res = new HashSet<>();
for(Persona2 padre:grafo.vertexSet() ) {
	Set<Persona2> setAux = new HashSet<>();
	for (Persona2 hijo:Graphs.successorListOf(grafo,padre)) {
		setAux.addAll(Graphs.predecessorListOf(grafo, hijo));
	}
	if(setAux.size()>2)
		res.add(padre);
}
return res;
}

//apartado e

public static Set<Persona2> apartadoE(Graph<Persona2, DefaultEdge>grafo){
	
	GreedyVCImpl<Persona2, DefaultEdge> ve = new GreedyVCImpl<>(grafo);
	return ve.getVertexCover();
}


	
	public static void main(String[] args) {
		
		// el grafo es dirigido simple sin pesos
		 
		// apartado a simplemente un predicado y estilizar 
		
		//lo he hcho segun el ejercico deleeRelaciones de Varios ejemplos.java
		
		Graph<Persona2, DefaultEdge> grafo1 = GraphsReader.newGraph("ficheros/Alumnos/PI3E1A_DatosEntrada.txt",
				Persona2::ofFormat, s-> new DefaultEdge(),Graphs2::simpleDirectedGraph);
		Graph<Persona2, DefaultEdge> grafo2 = GraphsReader.newGraph("ficheros/Alumnos/PI3E1B_DatosEntrada.txt",
				Persona2::ofFormat, s-> new DefaultEdge(),Graphs2::simpleDirectedGraph);
		
	Persona2 maria = Persona2.of("13,Maria,2008,Sevilla");
	System.out.println(apartadoB(grafo1,maria));
	
		
		
		

	}

}
