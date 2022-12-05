//package Ejemplos;
//
//import java.awt.Color;
//import java.util.function.Predicate;
//
//import org.jgrapht.Graph;
//
//import us.lsi.colors.GraphColors;
//import us.lsi.graphs.Graphs2;
//import us.lsi.graphs.GraphsReader;
//
//public class TestEj1 {
//	public static void testEj1(String file) {
//		Graph<Ciudad, Carretera> g = GraphsReader.newGraph("", Ciudad::ofFormat, Carretera::ofFormat, Graphs2::simpleGraph);
//		GraphColors.toDot(g,  "", v->v.nombre(), e-> e.nombre(), v -> GraphColors.color(us.lsi.colors.GraphColors.Color.black), e -> GraphColors.color(Color.black));
//	
//		Predicate<Ciudad> pv = c-> c.nombre().contains("e");
//		Predicate<Carretera>pA = ca -> ca.km()<200;
//		// TODO LLAMADAS A LA FUncion
//		Predicate<Ciudad> pV2 = c-> c.habitantes()<50000;
//		Predicate<Carretera> pa2= ca->ca.km() >100 && (g.getEdgeSource(ca).nombre().length()>5
//				|| g.getEdgeTarget(ca).nombre().length()>5);
//		
//				
//	}
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
