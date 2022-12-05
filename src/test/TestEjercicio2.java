package test;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.SimpleWeightedGraph;

import Ejercicios.Ejercicio2;
import Ejercicios.Ejercicio2.Arista;
import Ejercicios.Ejercicio2.Ciudad2;
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
			System.out.println("Conjunto "+i.toString() + ls.get(i));
		}
	}
	public static void testApartadoB() {
		System.out.println("Test apartado B ejercicio 2");
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio2");
		Set<Ciudad2> set = Ejercicio2.apartadoB(grafoPrecio);//cualquierade los 2 vale
	}
	public static void main(String[] args) {
		testApartadoA();

	}

}
