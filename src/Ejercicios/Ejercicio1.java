package Ejercicios;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.nio.Attribute;

import Ejercicios.Ejercicio1.Persona2;
import Ejercicios.Ejercicio2.Arista;
import Ejercicios.Ejercicio2.Ciudad2;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.common.List2;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

import us.lsi.graphs.views.SubGraphView;

public class Ejercicio1 {
	public static record Persona2(Integer id, String nombre, Integer year, String Ciudad) {
		// para que no se confunda con el record de miguel toro
		public static Persona2 of(String s) {
			String[] x = s.split(",");
			return new Persona2(Integer.valueOf(x[0]), x[1], Integer.parseInt(x[2]), x[3]);
		}

		public static Persona2 ofFormat(String[] x) {
			return new Persona2(Integer.valueOf(x[0]), x[1], Integer.parseInt(x[2]), x[3]);
		}
	}

//APARTADO A
	public static void apartadoA(Graph<Persona2, DefaultEdge> g, String nombreFichero) {
// averiguamos quien tiene los  padres 
		Predicate<Persona2> pV = p -> {// separo el pedicate del for
			//para mayorr comodidad al editarlo
			Boolean b =  false;
			
			if (Graphs.vertexHasPredecessors(g, p)) {
				List<Persona2> lsAux = Graphs.predecessorListOf(g, p);
				if (lsAux.get(0).Ciudad().equals(lsAux.get(1).Ciudad())
						&& lsAux.get(0).year().equals(lsAux.get(1).year())) {
					b = true;
				}
			}
			return b;
		};
		// se puede hace con un set o con un predicate
		// usando un set de vertices, te ahorras el predicate de aristas 
		// así seria el set:
		//Set<Persona2> s = g.vertexSet().stream().filter(pV).collect(Collectors.toSet());
		//hacemos la vista
	Graph<Persona2, DefaultEdge> vista = SubGraphView.of(g, pV, t ->true );
	System.out.println("Personas cuyos padres cumplen los requisitos:" + vista.vertexSet());
	GraphColors.toDot(g,"resultados/ejercicio1/apartadoA"+
	nombreFichero+".gv",
	v-> v.nombre(),
	a-> "",
	v-> GraphColors.colorIf(Color.blue, vista.containsVertex(v)),//probar el color if de yescolor no colorr
	a -> GraphColors.colorIf(Color.red, vista.containsEdge(a))  );
			
		
		
	}

// APARTADO B
	public static Set<Persona2> apartadoB(Graph<Persona2, DefaultEdge> g, String nombrePersona,String nombreFichero) {
		// ojo solo sirve si en el grafo solo hay una persona con el mismo nombre,
		// si no en lugar de recibir un string debeia recibir un Persona 2  (o un Integer id)
		Persona2 p = devuelvePersona2(g,nombrePersona);
		
		Set<Persona2> s = new HashSet<>();
		apartadoBaux(g, p, s);
		return s;
	}
//
	private static Persona2 devuelvePersona2(Graph<Persona2, DefaultEdge> g, String nombrePersona) {
	
	return g.vertexSet().stream().filter(v-> v.nombre().equalsIgnoreCase(nombrePersona.trim())).findFirst().get();
}

	private static void apartadoBaux(Graph<Persona2, DefaultEdge> g, Persona2 p, Set<Persona2> s) {
		List<Persona2> ls = Graphs.predecessorListOf(g, p);
		if (ls.size() != 0) {
			for (Persona2 paux : ls) {
				apartadoBaux(g, paux, s);
				s.add(paux);
			}
		}
		
	}
	private static void apartadoBGrafo(Graph<Persona2, DefaultEdge> g, Persona2 p, Set<Persona2> s,String nombreFichero) {
	
		GraphColors.toDot(g,"resultados/ejercicio1/apartadoD"+ nombreFichero+".gv", v-> v.nombre(), a -> "",v->GraphColors.color(asigna(v, p, s)),
				e-> GraphColors.color(Color.black));

	}

	
	private static Color asigna(Persona2 v, Persona2 p, Set<Persona2> s ) {
	//lo hago asi porqu con fucntion me da errores
		// Cosads de Map string atributte
		// y no entiendo el piorque la verdad 
	// si es casi igual que el de asignaColor del ejemplo 
		Integer  res ; 
		Color[] vc = Color.values();
		if (v.equals(p))
			res=  2;
		else if(s.contains(v))
			res = 4;
		else res = 5;
		return vc[5];
			
	
	}


// APARTADO C
	public enum Relacion {
		Hermanos, Primos, Otros
	};

	public static Relacion apartadoC(Graph<Persona2, DefaultEdge> grafo, String nombre1, String nombre2) {
// si el camino minimo es 4 son primos
		// si el camino es 2 son hermanos
		// otro otros
		Persona2 p1 = devuelvePersona2(grafo, nombre1);
		Persona2 p2 = devuelvePersona2(grafo, nombre2);
		
		ShortestPathAlgorithm<Persona2, DefaultEdge> a = new DijkstraShortestPath<>(Graphs.undirectedGraph(grafo));
		Double s = a.getPathWeight(p1, p2);

		Relacion res;
		if (s == 2.0) {
			res = Relacion.Hermanos;
		} else if (s == 4.0) {
			res = Relacion.Primos;
		} else {
			res = Relacion.Otros;
		}
		return res;
	}

//ApartadoD:
	public static Set<Persona2> apartadoD(Graph<Persona2, DefaultEdge> grafo, String nombreFichero) {
		Set<Persona2> res = new HashSet<>();
		for (Persona2 padre : grafo.vertexSet()) {
			Set<Persona2> setAux = new HashSet<>();
			for (Persona2 hijo : Graphs.successorListOf(grafo, padre)) {
				setAux.addAll(Graphs.predecessorListOf(grafo, hijo));
			}
			if (setAux.size() > 2)
				res.add(padre);
		}
		apartadoDGrafo(grafo, res, nombreFichero);
		return res;
	}
	private static void apartadoDGrafo(Graph<Persona2, DefaultEdge> grafo,Set<Persona2> set, String nombreFichero) {
		GraphColors.toDot(grafo,"resultados/ejercicio1/apartadoD"+ nombreFichero+".gv", v-> v.nombre(), a -> "", v-> GraphColors.colorIf(Color.cyan, set.contains(v)),
				a-> GraphColors.colorIf(Color.black, grafo.containsEdge(a)));
		
	}

//apartado e

	public static Set<Persona2> apartadoE(Graph<Persona2, DefaultEdge> grafo, String nombreFichero) {

		GreedyVCImpl<Persona2, DefaultEdge> ve = new GreedyVCImpl<>(Graphs.undirectedGraph(grafo));
		apartadoEGrafo(grafo, ve.getVertexCover(), nombreFichero);
		return ve.getVertexCover();
	}
	private static void apartadoEGrafo(Graph<Persona2, DefaultEdge> grafo,Set<Persona2> set, String nombreFichero) {
		GraphColors.toDot(grafo,"resultados/ejercicio1/apartadoE"+ nombreFichero+".gv", v-> v.nombre(), a -> "", v-> GraphColors.colorIf(Color.cyan, set.contains(v)),
				a-> GraphColors.colorIf(Color.black, grafo.containsEdge(a)));
		}

	
	}


