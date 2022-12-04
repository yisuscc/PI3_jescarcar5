package test;

import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import Ejercicios.Ejercicio3;
//import Ejercicios.Ejercicio3.Asignatura;
import us.lsi.common.Files2;
import us.lsi.common.Pair;
import us.lsi.graphs.Graphs2;


public class TestEjercicio3 {

	private static Graph<String, DefaultEdge> leeGrafo(String file) {
		Graph<String, DefaultEdge> res = Graphs2.simpleGraph(String::new,DefaultEdge::new,false);
		Files2.streamFromFile(file).forEach(linea -> {
			String[] vAux = linea.split(":");
			String[] v = vAux[1].split(",");
			for (int i = 0; i < v.length; i++) {// añadimos los vertices
				res.addVertex(v[i]);
			}
			// añadimos las aristas
			if (v.length > 1) {
				for (int i = 0; i < v.length; i++) {
					for (int j = i + 1; j < v.length; j++) {
						res.addEdge(v[i], v[j]);
					}
				}
			}

		}
				);

		return res;
	}

	static Graph<String, DefaultEdge> grafoA = leeGrafo("ficheros/Alumnos/PI3E3A_DatosEntrada.txt");
	static Graph<String, DefaultEdge> grafoB = leeGrafo("ficheros/Alumnos/PI3E3B_DatosEntrada.txt");
	public static void testApartadoA() {
		System.out.println("Test Ejercicio3 apartado A");
		System.out.println("Los grafos resultantes se encuentran en la carpeta resultados/ejercicio3");
		System.out.println("Fichero A");
		Pair<Integer, List<Set<String>>> p1 = Ejercicios.Ejercicio3.apartadoA(grafoA, "FicheroA");
		System.out.println("Numero de franjas horarias necesarias: "+ p1.first());
		for(int i =0; i<p1.second().size();i++ ) {
			int n = i+1;
			System.out.println("Franja"+n + p1.second().get(i));
		}
		System.out.println("Fichero B");
		Pair<Integer, List<Set<String>>> p2 = Ejercicios.Ejercicio3.apartadoA(grafoB, "FicheroB");
		System.out.println("Numero de franjas horarias necesarias: "+ p2.first());
		for(int i =0; i<p2.second().size();i++ ) {
			int n = i+1;
			System.out.println("Franja"+n + p2.second().get(i));
		}
		
	}

	public static void main(String[] args) {
		testApartadoA();
	}

}
