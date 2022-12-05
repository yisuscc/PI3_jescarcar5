//package Ejemplos;
//
//
//import java.util.List;
//import java.util.Set;
//
//import org.jgrapht.Graph;
//import org.jgrapht.GraphPath;
//import org.jgrapht.alg.connectivity.ConnectivityInspector;
//import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
//import org.jgrapht.graph.SimpleWeightedGraph;
//
//import us.lsi.graphs.Graphs2;
//
//public class Ej2 { // Puedes ser graph normal  pero está asi para reforzar
//	public static void apartadoA(SimpleWeightedGraph<Ciudad, Carretera> gr, String file) {
//		
//		Graph<Ciudad, Carretera> g = Graphs2.explicitCompleteGraph(gr, 1000.0, Graphs2::simpleWeightedGraph, 
//				()-> Carretera.of(1000), Carretera::km);
//		// hacer el todot 
//	}
//	
//	public static void apartadoB(SimpleWeightedGraph<Ciudad, Carretera> gr, String file, String c1, String c2 ) {
//		// Hacer las llamadaws da ciudad
//		
//		var alg = new DijkstraShortestPath<>(gr);
//		Ciudad origen = ciudad(c1);
//		Ciudad destino = ciudad (c2);
//		GraphPath<Ciudad, Carretera> gp = alg.getPath(origen, destino);
//		//Crear el todot
//		
//	}
//	public static void apartadoC() {
//		
//	}
//	public static void apartadoD(SimpleWeightedGraph<Ciudad, Carretera> gr, String file) {
//	var alg = new ConnectivityInspector<>(gr);
//	List<Set<Ciudad>> ls = alg.connectedSets();
//	
//		
//	}
//	private static Ciudad ciudad(String c2) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
