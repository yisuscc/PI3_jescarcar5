//package Ejemplos;
//
//import org.jgrapht.Graph;
//
//import Ejemplos.ej4.Pasillo;
//import us.lsi.graphs.Graphs2;
//import us.lsi.graphs.GraphsReader;
//
//public class TestEj4 {
//	public static void testEjemplo$(String file) {
//		Graph<String,Pasillo> grafo = 
//				GraphsReader.newGraph("ficheros/"+file+".txt", v->v[0], Pasillo::ofFormat, Graphs2::simpleWeightedGraph,Pasillo::mts);
//	}
//	public static void main() {
//		System.out.println("Apartado A:");
//		System.out.println("las Camaras deben colocarse en los siguientes"+ Ejemplo4.apartadoA(Grafo)+"cruces");
//		Ejemplo4.apartadoA(Grafo).forEach(c-> syso("\\t- Cruce)"+c);
//		System.out.println("ApartadoB");
//		Ejemplo4.apartadoB(grafo,file);
//		
//	}
//}
