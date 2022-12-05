package test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;


import Ejercicios.Ejercicio2;
import Ejercicios.Ejercicio2.Arista;
import Ejercicios.Ejercicio2.Ciudad2;
import us.lsi.common.Pair;
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
		System.out.println("Grupo de ciudades a visitar que dan lugar al camino cerrado de menor precio: " + lsAux+ "-->" +peso);
		
	}
	public static void main(String[] args) {
		testApartadoA();
		testApartadoB();
		testApartadoC();
		

	}

}
