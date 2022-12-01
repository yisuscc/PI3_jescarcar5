package Ejercicios;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.tour.HeldKarpTSP;

public class Ejercicio2 {
	public static record Ciudad2(String nombre, Integer puntos) {
		public static Ciudad2 ofFormat(String[] s) {
			return new Ciudad2(s[0], Integer.valueOf(s[1].replace('p', ' ')));
			
		}
		public static Ciudad2 of(String x) {
			String[] s = x.split(",");
			return new Ciudad2(s[0], Integer.valueOf(s[1].replace('p', ' ')));
		}
	}
	public static record Arista(Double precio, Double tiempo) {
		public static Arista ofFormat(String[] s) {
			return new Arista(Double.valueOf(s[0].replace("euros", "")), 
					Double.valueOf(s[1].replace("minutos", "")));
		}
		public static Arista of(String x) {
			String [] s = x.split(",");
			return new Arista(Double.valueOf(s[0].replace("euros", "")), 
					Double.valueOf(s[1].replace("minutos", "")));
		}
	}
	
	
	//apartado a
	public static List<Set<Ciudad2>> apartadoA(Graph<Ciudad2, Arista>g){
		return  new ConnectivityInspector(g).connectedSets();
	}
	
	//apartado b
	public static Set<Ciudad2> apartadoB(Graph<Ciudad2, Arista>g){
		//TODO
		return null;
	}
	
	//apartado c
	// creo ques eseria mejor devolver una tupla con un set de edges y otro de vertices
	public static List<Ciudad2> apartadoC(Graph<Ciudad2, Arista>g){
		GraphPath<Ciudad2, Arista> gp =  new HeldKarpTSP().getTour(g);
		return gp.getVertexList();
	}
	
	//apartado d
	
	//TODO

}
