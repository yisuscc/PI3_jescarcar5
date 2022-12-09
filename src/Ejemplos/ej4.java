package Ejemplos;

import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.spanning.KruskalMinimumSpanningTree;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;

import us.lsi.colors.GraphColors;
import us.lsi.colors.GraphColors.Color;
import us.lsi.graphs.views.SubGraphView;

public class ej4 {
public record Pasillo(String c1, String c2, Double mts){
	public static Pasillo ofFormat(String [] v) {
		return new Pasillo(v[0], v[1],Double.valueOf(v[2]));
	}
}
public static Set<String> apartadoA(Graph<String, Pasillo> gf){
	var algA = new GreedyVCImpl<>(gf);
	Set<String> cruces = algA.getVertexCover();
	return cruces;
	
}
public static void ApartadoB(Graph<String, Pasillo> gf,String file) {
	Set<String> crucesConCamara = apartadoA(gf);
	Predicate<String> pV = c-> crucesConCamara.contains(c);
	Predicate<Pasillo>pe = p->crucesConCamara.contains(gf.getEdgeSource(p))&&
			crucesConCamara.contains(gf.getEdgeTarget(p));
	Graph<String, Pasillo> sgf = SubGraphView.of(gf, pV,pe);
	var algB1 = new ConnectivityInspector<>(sgf);
	System.out.println("Numero de equipos nEcesarios"+ algB1.connectedSets().size());
	var algB2 = new KruskalMinimumSpanningTree<>(sgf);
	var tree = algB2.getSpanningTree();
	System.out.println(String.format("Metros de cable necesarios: "+ "%.1f",tree.getWeight() ));
	GraphColors.toDot(gf, "resultados/ejemplo4/"+file+".gv", c->"", v->"",
			v-> GraphColors.colorIf(Color.blue,Color.blank, crucesConCamara.contains(v)),
			e-> GraphColors.colorIf(Color.blue, Color.blank,tree.getEdges().contains(e)));
	
	
}

}
