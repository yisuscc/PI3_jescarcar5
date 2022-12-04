package Ejercicios;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.tour.HeldKarpTSP;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.Pair;
import us.lsi.common.Trio;

public class Ejercicio2 {
	public static record Ciudad2(String nombre, Integer puntos) {
		public static Ciudad2 ofFormat(String[] s) {
			//seguro que hay una forma mejor de hacerlo
			return new Ciudad2(s[0], Integer.valueOf(s[1].replace('p', ' ').trim()));

		}

		public static Ciudad2 of(String x) {
			String[] s = x.split(",");
			return new Ciudad2(s[0], Integer.valueOf(s[1].replace('p', ' ').strip()));
		}
	}

	public static record Arista(Double precio, Double tiempo) {
		public static Arista ofFormat(String[] s) {
			return new Arista(Double.valueOf(s[0].replace("euros", "").strip()), Double.valueOf(s[1].replace("minutos", "").strip()));
		}

		public static Arista of(String x) {
			String[] s = x.split(",");
			return new Arista(Double.valueOf(s[0].replace("euros", "")), Double.valueOf(s[1].replace("minutos", "")));
		}
	}

	// apartado a
	public static List<Set<Ciudad2>> apartadoA(Graph<Ciudad2, Arista> g) {
		var alg = new ConnectivityInspector<>(g);
		List<Set<Ciudad2>> ls = alg.connectedSets();
		apartadoAGrafo(g, ls, alg);
		return ls;
	}

	private static void apartadoAGrafo(Graph<Ciudad2, Arista>g,
			List<Set<Ciudad2>> ls,ConnectivityInspector<Ciudad2, Arista> ci){
		GraphColors.toDot(g, "resultados/ejercicio2/apartadoA.gv", v->v.nombre(),
				e-> "", v->GraphColors.color(asignaColor(v,ls,ci)), 
				e-> GraphColors.color(asignaColor(g.getEdgeSource(e),ls,ci)));
	}
	private static Color asignaColor(Ciudad2 v,List<Set<Ciudad2>> ls,ConnectivityInspector<Ciudad2, Arista> ci) {
		//es el mismo que se dio como ejemplo en clase
		Color[] vc = Color.values();
		Set<Ciudad2> s = ci.connectedSetOf(v);
		return vc[ls.indexOf(s)];
		}
	//apartado b
	public static Set<Ciudad2> apartadoB(Graph<Ciudad2, Arista> g) {
		// TODO
		
		
		return null;
	}

	// apartado c
	// creo ques eseria mejor devolver una tupla con un set de edges y otro de
	// vertices
	public static List<Ciudad2> apartadoC(Graph<Ciudad2, Arista> g) {
		GraphPath<Ciudad2, Arista> gp = new HeldKarpTSP().getTour(g);
		
		Graph<Ciudad2, Arista> gAux = gp.getGraph();
		GraphColors.toDot(g, "resultados/ejercicio2/apartadoC.gv", v->v.nombre(), e-> "",
				v-> GraphColors.colorIf(Color.blue,gAux.containsVertex(v)),
				e-> GraphColors.colorIf(Color.blue, gAux.containsEdge(e)));
		
		
		return gp.getVertexList();
	}

	// apartado d
public static Trio<Ciudad2, Ciudad2, Double> apartadoD(Graph<Ciudad2, Arista> g, Set<Ciudad2>set){
	Set<Trio<Ciudad2, Ciudad2, Double>> ss= new HashSet<>(); //se puede usar un sorted set 
	DijkstraShortestPath<Ciudad2,Arista> dij= new DijkstraShortestPath<>(g);
	for(Ciudad2 c1 : set) {
		Set<Ciudad2>  setAux = Set.copyOf(set);
		setAux.remove(c1);
		for(Ciudad2 c2 : setAux) {
			if(!Graphs.neighborSetOf(g, c1).contains(c2)) {
				Double pesoCamino = dij.getPathWeight(c1, c2);
				Trio<Ciudad2, Ciudad2, Double> trio = Trio.of(c1, c2, pesoCamino);
				ss.add(trio);
			}
		}
	}
	
	return ss.stream().min((a,b)-> a.third().compareTo(b.third())).get();
}


	
	

}
