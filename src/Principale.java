import utilensemjava.Lecture;

import java.util.*;

public class Principale {

	public static void main(String[] args) {

		int V = Lecture.lireEntier("nombre de sommets =? ");
		int E = Lecture.lireEntier("nombre d'arcs =? ");
		Graphe g = new Graphe(V, E);
		g.afficher();

	  Vector S = g.plusCourtChemin(0);
		for (int i = 0; i < S.size(); i++) {
			System.out.println(((Element) S.elementAt(i)).sommet + ", "
					+ ((Element) S.elementAt(i)).distance);
	}
	
	
		
		
	
	}
}
