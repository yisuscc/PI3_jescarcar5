package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.SimpleWeightedGraph;


import Ejercicios.Ejercicio2;
import Ejercicios.Ejercicio2.Arista;
import Ejercicios.Ejercicio2.Ciudad2;
import us.lsi.common.Pair;
import us.lsi.common.Trio;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;


public class TestEjercicio2 {

	static Graph<Ciudad2,Arista> grafoPrecio = GraphsReader.newGraph("ficheros/Alumnos/PI3E2_DatosEntrada.txt", Ciudad2::ofFormat, Arista::ofFormat,
			Graphs2::simpleWeightedGraph, Arista::precio);
	static Graph<Ciudad2,Arista> grafoTiempo =  GraphsReader.newGraph("ficheros/Alumnos/PI3E2_DatosEntrada.txt", Ciudad2::ofFormat, Arista::ofFormat,
			Graphs2::simpleWeightedGraph, Arista::tiempo);
	public static void testApartadoA() {
		System.out.println("Test apartado A ejercicio 2");
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio2");
		List<Set<Ciudad2>> ls = Ejercicio2.apartadoA(grafoPrecio); //cualquierade los 2 vale
		for (Integer i = 0; i<ls.size(); i++) {
			Set<String>setLimpio =  ls.get(i).stream().map(c->c.nombre()).collect(Collectors.toSet());
			System.out.println("Conjunto "+i.toString() + setLimpio);
		}
	}
	public static void testApartadoB() {
		System.out.println("Test apartado B ejercicio 2");
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio2");
		Set<String> set = Ejercicio2.apartadoB(grafoPrecio).stream().map(c->c.nombre() ).collect(Collectors.toSet());//cualquierade los 2 vale
		System.out.println("Grupo de ciudades que maximiza la suma de las puntuaciones"+ set);
	}
	public static void testApartadoC() {
		System.out.println("Test apartado C ejercicio 2");
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio2");
		Pair<Double, List<Ciudad2>>par = Ejercicio2.apartadoC(grafoPrecio);
		List<Ciudad2> lsAux = par.second();
		List<String> ls = lsAux.stream().map(c-> c.nombre()).toList();
		Double peso = par.first();
		System.out.println("Grupo de ciudades a visitar que dan lugar al camino cerrado de menor precio: " + ls+ "-->" +peso);
		
	}
	public static void testApartadoD() {
	
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio2");
		System.out.println("Test apartado D ejercicio 2");
//		 el ls1 se puede hacer con una llamada al apartado a pero
//		no es conveniente ya que se reescribiria el grafo
		var alg = new ConnectivityInspector<>(grafoTiempo);
		List<Set<Ciudad2>> ls1 = alg.connectedSets();;
		Trio<Ciudad2,Ciudad2, Double> t1 = Ejercicio2.apartadoD(grafoTiempo, ls1.get(0),"Grupo1");
		Trio<Ciudad2,Ciudad2, Double> t2 = Ejercicio2.apartadoD(grafoTiempo, ls1.get(1),"Grupo2");
		List<String> lsAux1 = ls1.get(0).stream().map(c->c.nombre()).toList();
		List<String> lsAux2 = ls1.get(1).stream().map(c->c.nombre()).toList();
	
		System.out.println("Para el grupo"+ lsAux1+", las ciudades no conectadas directamente"
				+ " entre las que se puede viajar en menor tiempo son: ");
		System.out.println("Origen: "+t1.first().nombre()+" Destino: "+t1.second().nombre() +"--> "+ t1.third()+"Minutos");
		System.out.println("---------------------");
		System.out.println("Para el grupo"+ lsAux2+", las ciudades no conectadas directamente"
				+ " entre las que se puede viajar en menor tiempo son: ");
		System.out.println("Origen: "+t2.first().nombre()+" Destino: "+t2.second().nombre() +"--> "+ t2.third()+"Minutos");
		
	}
	public static void main(String[] args) {
		testApartadoA();
		testApartadoB();
		testApartadoC(); //TODO Corregir problema Con el HK
		testApartadoD();
		

	}

}
