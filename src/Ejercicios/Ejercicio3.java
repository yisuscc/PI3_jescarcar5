package Ejercicios;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;

import us.lsi.common.Pair;

public class Ejercicio3 {
	public static record Asignatura(String asignatura) {
		public static  Asignatura ofFormat(String[]s) {
			return new Asignatura(s[0]);
		}
		public static Asignatura of (String s) {
			return new Asignatura(s);
		}
	}
	
	
//Apartado A
	public static Pair<Integer, List<Set<Asignatura>>> apartadoA(Graph<Asignatura, DefaultEdge> g){
		 VertexColoringAlgorithm<Asignatura> vcaa = new GreedyColoring<>(g);
		 Integer nCromatico = vcaa.getColoring().getNumberColors(); 
		 List<Set<Asignatura>> ls = vcaa.getColoring().getColorClasses();
		 return Pair.of(nCromatico,ls);
	}
}
