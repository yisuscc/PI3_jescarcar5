package Ejercicios;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Predicate;

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
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.views.SubGraphView;

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
			return new Arista(Double.valueOf(s[2].replace("euros", "").strip()), Double.valueOf(s[3].replace("min", "").strip()));
		}

		public static Arista of(String x) {
			String[] s = x.split(",");
			return new Arista(Double.valueOf(s[2].replace("euros", "")), Double.valueOf(s[3].replace("min", "")));
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
		List<Set<Ciudad2>> lss = apartadoA(g);
		Function<Set<Ciudad2>,Integer> sumPuntos= s-> s.stream().mapToInt(Ciudad2::puntos).sum();
		Set<Ciudad2> setRes = lss.stream().max((a,b)-> sumPuntos.apply(a).compareTo(sumPuntos.apply(b))).orElse(new HashSet<>());
		
		apartadoBGrafo(g,setRes);
		return setRes;
	}

	private static void apartadoBGrafo(Graph<Ciudad2, Arista> g, Set<Ciudad2> setRes) {
		Predicate<Arista> estaEnSet =  a -> setRes.containsAll(Graphs2.getVertices(g, a));
	 GraphColors.toDot(g, "resultados/ejercicio2/apartadoB.gv", v-> v.nombre() +" "+ v.puntos()+ "Puntos", a-> "", 
			 v-> GraphColors.colorIf(Color.blue, setRes.contains(v)), e->GraphColors.colorIf(Color.blue, estaEnSet.test(e)));
		
	}

	// apartado c
	
	
	public static Pair<Double, List<Ciudad2>> apartadoC(Graph<Ciudad2, Arista> g) {
		//TODO Ver si es posible simplificarlo
		// el lsSets se puede hacer mediante una llamada al apartado a
		// pero eso implicaria reconstruir el grafo del apartadoA
		//cosa que no quiero
		var alg = new ConnectivityInspector<>(g); 
		List<Set<Ciudad2>> lsSets = alg.connectedSets();
		SortedSet<GraphPath<Ciudad2, Arista> > ss = new TreeSet<>((a,b)-> 
		((Double)a.getWeight()).compareTo((Double)b.getWeight()));
		//puedes usar un stream pero me gustan los sortedSet
		
		for(Set<Ciudad2> c : lsSets) {
			Graph<Ciudad2, Arista> gAux = SubGraphView.of(g, c);
			ss.add(new HeldKarpTSP().getTour(gAux));
		}
	
		GraphPath<Ciudad2, Arista> gp = ss.first();

		Graph<Ciudad2, Arista> gAux = gp.getGraph();
		GraphColors.toDot(g, "resultados/ejercicio2/apartadoC.gv", v->v.nombre(), e-> e.precio.toString()+ " mins",
				v-> GraphColors.colorIf(Color.cyan,gp.getVertexList().contains(v)),
				e-> GraphColors.colorIf(Color.cyan, gp.getEdgeList().contains(e)));
		
		
		return Pair.of(gp.getWeight(), gp.getVertexList());
	}

	// apartado d
public static Trio<Ciudad2, Ciudad2, Double> apartadoD(Graph<Ciudad2, Arista> g, Set<Ciudad2>set,String label){
	SortedSet<Trio<Ciudad2, Ciudad2, Double>> ss= new TreeSet<>((c1,c2)-> c1.third().compareTo(c2.third())); 
	//se puede usar tambien un set y un stream 
	DijkstraShortestPath<Ciudad2,Arista> dij= new DijkstraShortestPath<>(g);
	
	for(Ciudad2 c1 : set) {
		
		Set<Ciudad2>  setAux = new HashSet<>(set);
		setAux.remove(c1);
		
		for(Ciudad2 c2 : setAux) {
			if(!Graphs.neighborSetOf(g, c1).contains(c2)) {
				Double pesoCamino = dij.getPathWeight(c1, c2);
				Trio<Ciudad2, Ciudad2, Double> trio = Trio.of(c1, c2, pesoCamino);
				ss.add(trio);
			}
		}
	}
apartadoDGrafo(g, dij, ss.first(), label);
	return ss.first()	;
}


public static void apartadoDGrafo(Graph<Ciudad2, Arista> g,
		DijkstraShortestPath<Ciudad2,Arista> dij,Trio<Ciudad2, Ciudad2, Double>t,String label) {
	Predicate<Ciudad2> pV =v -> t.first().equals(v) || t.second().equals(v);
	 String fichero  = "resultados/ejercicio2/apartadoD"+label+".gv";
	Predicate<Arista> pA = a-> dij.getPath(t.first(), t.second()).getEdgeList().contains(a);
	GraphColors.toDot(g, fichero,  v-> v.nombre() ,e-> e.tiempo().toString()+" Min", v-> GraphColors.colorIf(Color.red, pV.test(v)),
			a-> GraphColors.colorIf(Color.red, pA.test(a)));
	
	
}
	
	

}
