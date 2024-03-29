package test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import Ejercicios.Ejercicio1;
import Ejercicios.Ejercicio1.Persona2;
import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class TestEjercicio1 {
	 static Graph<Persona2, DefaultEdge> grafo1 = GraphsReader.newGraph("ficheros/Alumnos/PI3E1A_DatosEntrada.txt",
			Persona2::ofFormat, s -> new DefaultEdge(), Graphs2::simpleDirectedGraph);
	static Graph<Persona2, DefaultEdge> grafo2 = GraphsReader.newGraph("ficheros/Alumnos/PI3E1B_DatosEntrada.txt",
			Persona2::ofFormat, s -> new DefaultEdge(), Graphs2::simpleDirectedGraph);
	public static void testApartadoA() {
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio1");
		System.out.println("Testa apartado A fichero A");
		List<String> ls1 =  Ejercicio1.apartadoA(grafo1, "FicheroA").stream().map(c-> c.nombre()).toList();
		System.out.println("Personas cuyos padres cumplen los requisitos:" + ls1);
		System.out.println("Test apartado a fichero b");
		List<String> ls2 =  Ejercicio1.apartadoA(grafo2, "FicheroB").stream().map(c-> c.nombre()).toList();
		System.out.println("Personas cuyos padres cumplen los requisitos:" + ls2);
		
	}
	public static void testApartadoB() {
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio1");
		System.out.println("Test apartado B fichero A");
		Set<String> s1 = Ejercicio1.apartadoB(grafo1, "Maria", "FicheroA").stream().map(c->c.nombre()).collect(Collectors.toSet());
		System.out.println("Los ancestros de maría son : "+s1 );
		System.out.println("Test apartado B fichero B");
		Set<String> s2 = Ejercicio1.apartadoB(grafo2, "Raquel", "FicheroB").stream().map(c->c.nombre()).collect(Collectors.toSet());
		System.out.println("Los ancestros  de raquel son : "+ s2);
		
		
	}
	public static void testApartadoC() {
		System.out.println(" Test apartado C Fichero A");
		System.out.println("Rafael y Sara son: " +Ejercicio1.apartadoC(grafo1, "Rafael", "Sara"));
		System.out.println("Maria y patricia son: " +Ejercicio1.apartadoC(grafo1, "Maria", "Patricia"));
		System.out.println("Carmen y rafael son: "+Ejercicio1.apartadoC(grafo1, "Carmen","Rafael"));
		System.out.println(" Test apartado C Fichero B");
		System.out.println("Julia y angela son: " +Ejercicio1.apartadoC(grafo2, "Julia", "Angela"));
		System.out.println("Alvaro y raquel son: "+ Ejercicio1.apartadoC(grafo2, "Alvaro","Raquel"));
		System.out.println("Laura y raquel soN: " +Ejercicio1.apartadoC(grafo2, "Laura", "Raquel"));
	}
	public static void testApartadoD() {
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio1");
		System.out.println("Test apartado D fichero A");
		Set<String> s1 = Ejercicio1.apartadoD(grafo1, "FicheroA").stream().map(c-> c.nombre()).collect(Collectors.toSet());
		Set<String> s2 = Ejercicio1.apartadoD(grafo2, "FicheroB").stream().map(c-> c.nombre()).collect(Collectors.toSet());
		System.out.println("Los Personas que tienen hijos con distintas personas: " + s1);
		System.out.println("Test apartado D fichero B");
		System.out.println("Los Personas que tienen hijos con distintas personas: " +s2);
	}
	public static void testApartadoE() {
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio1");
		 System.out.println("Test apartado E FicheroA");
		Ejercicio1.apartadoE(grafo1,"FicheroA");
		 System.out.println("Test apartado E FicheroB");
		 Ejercicio1.apartadoE(grafo2,"FicheroB");
		 
	}
	public static void main(String[] args) {
	
	testApartadoA();
	testApartadoB();
		testApartadoC();
		testApartadoD();
		testApartadoE();
	}

}
