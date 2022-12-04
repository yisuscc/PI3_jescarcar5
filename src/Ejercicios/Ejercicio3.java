package Ejercicios;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;

import us.lsi.colors.GraphColors;
import us.lsi.common.Pair;

public class Ejercicio3 {
	// he decidido utilizar String para simplificar 
//	public static record String(String String) {
//		// en su lugar se puede utilizar simplemente un string
//		public static  String ofFormat(String[]s) {
//			return new String(s[0]);
//		}
//		public static String of (String s) {
//			return new String(s);
//		}
//	}
//	
//	
//Apartado A
	public static Pair<Integer, List<Set<String>>> apartadoA(Graph<String, DefaultEdge> g, String nomFich){
		 VertexColoringAlgorithm<String> vcaa = new GreedyColoring<>(g);
		 Integer nCromatico = vcaa.getColoring().getNumberColors(); 
		 Map<String, Integer> mColor = vcaa.getColoring().getColors();
		 List<Set<String>> ls = vcaa.getColoring().getColorClasses();
		 return Pair.of(nCromatico,ls);
	}
	public static void apartadoAGrafo(Graph<String, DefaultEdge> g,Map<String, Integer> mColor, String nomFich) {
		GraphColors.toDot(g,"resultados/ejercicio3/apartadoA"+nomFich+".gv",v-> v, a-> "",
				v->GraphColors.color(mColor.get(v)), e-> GraphColors.color(8));
	}
}
