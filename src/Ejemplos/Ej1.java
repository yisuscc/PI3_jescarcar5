package Ejemplos;

import java.util.function.Predicate;

import org.jgrapht.Graph;

import us.lsi.colors.GraphColors;
import us.lsi.graphs.views.SubGraphView;

public class Ej1 {
	public static void crearVista(String file, Graph<Ciudad, Carretera>g, 
			Predicate<Ciudad> pv, Predicate<Carretera>pET, String nomvreVista) {
		
		Graph<Ciudad, Carretera> vista = SubGraphView.of(g,pv, pET);
		GraphColors.toDot(vista, "", x-> x.nombre(), y-> y.km().toString(),
				v-> GraphColors.colorIf(Color.red, vista.edgesOf(v)));
		
	}

}
