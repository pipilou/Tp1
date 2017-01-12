import java.util.ArrayList;
import java.util.Vector;

import utilensemjava.Lecture;

public class Graphe {
	private Liste adj[];

	Graphe(int nb_sommets, int nb_arcs) {
		adj = new Liste[nb_sommets];
		for (int i = 0; i < nb_sommets; i++)
			adj[i] = null;

		for (int k = 0; k < nb_arcs; k++) {
			int i = Lecture.lireEntier("sommet sortant du " + (k + 1)
					+ "ème arc (0-" + (nb_sommets - 1) + ")=? ");
			int j = Lecture.lireEntier("sommet d'arrivée du " + (k + 1)
					+ "ème arc (0-" + (nb_sommets - 1) + ")=? ");
			int val = Lecture
					.lireEntier("valeur du " + (k + 1) + "ème arc =? ");
			adj[i] = new Liste(j, adj[i], val);
		}

	}

	public void afficher() {
		for (int i = 0; i < adj.length; i++) {
			System.out.print("sommet " + i + " :");
			if (adj[i] != null) {
				Liste a = adj[i];
				while (a != null) {
					System.out.print(a.num_noeud + " " + a.valeur + " ");
					if (a.suivant != null)
						System.out.print(a.suivant.num_noeud + " -> ");
					a = a.suivant;
				}
			}
			System.out.println(" null");
		}
	}

	public boolean arc(int source, int dest) {
		boolean arcExiste = false;
		if (adj[source] != null) {
			Liste a = adj[source];
			while (a != null) {
				if (a.num_noeud == dest)
					arcExiste = true;
				if (arcExiste)
					a = null;
				else
					a = a.suivant;
			}
		}
		return arcExiste;
	}

	public int valeurArc(int source, int dest) {
		int val = 9999;
		boolean arcExiste = false;
		Liste a = adj[source];
		while (a != null) {
			if (a.num_noeud == dest) {
				val = a.valeur;
				arcExiste = true;
			}
			if (arcExiste)
				a = null;
			else
				a = a.suivant;
		}
		return val;
	}

	public Vector plusCourtChemin(int num_sommet) {
		final int INFINI = Integer.MAX_VALUE;
		Vector S = new Vector();
		Vector D = new Vector();

		for (int i = 0; i < adj.length; i++) {
			if (i != num_sommet)
				D.addElement(new Element(i, INFINI));
			else
				D.addElement(new Element(i, 0));
		}
		System.out.println("S:");
		for (int i = 0; i < S.size(); i++) {
			System.out.println(((Element) S.elementAt(i)).sommet + ","
					+ ((Element) S.elementAt(i)).distance);
		}
		System.out.println("D:");
		for (int i = 0; i < D.size(); i++) {
			System.out.println(((Element) D.elementAt(i)).sommet + ","
					+ ((Element) D.elementAt(i)).distance);
		}

		while (D.size() != 0) {

			int indice_min = 0;
			int dm = INFINI;
			int sm = ((Element) D.elementAt(0)).sommet;
			for (int i = 0; i < D.size(); i++) {
				if (dm > ((Element) D.elementAt(i)).distance) {
					dm = ((Element) D.elementAt(i)).distance;
					sm = ((Element) D.elementAt(i)).sommet;
					indice_min = i;
				}
			}
			Element m = new Element(sm, dm);

			S.addElement(m);
			System.out.println("S après avec taille= " + S.size());
			for (int i = 0; i < S.size(); i++) {
				System.out.println(((Element) S.elementAt(i)).sommet + ","
						+ ((Element) S.elementAt(i)).distance);
			}
			D.removeElementAt(indice_min);
			System.out.println("D après avec taille= " + D.size());
			for (int i = 0; i < D.size(); i++) {
				System.out.println(((Element) D.elementAt(i)).sommet + ","
						+ ((Element) D.elementAt(i)).distance);
			}

			System.out.println("élément à supprimer n'est pas trouvé");

			for (int i = 0; i < D.size(); i++) {
				Element x = (Element) D.elementAt(i);
				if (arc(m.sommet, x.sommet)) {
					int d = m.distance + valeurArc(m.sommet, x.sommet);
					if (d < x.distance) {
						x.distance = d;
						D.setElementAt(x, i);
					}
				}
			}
		}
		return S;
	}

	public Vector court(int num_sommet, int arr) {
		final int INFINI = Integer.MAX_VALUE;
		Vector S = new Vector();
		Vector D = new Vector();
		int C = 0;
		for (int i = 0; i < adj.length; i++) {
			if (i != num_sommet)
				D.addElement(new Element(i, INFINI));
			else
				D.addElement(new Element(i, 0));
		}
		System.out.println("S:");
		for (int i = 0; i < S.size(); i++) {
			System.out.println(((Element) S.elementAt(i)).sommet + ","
					+ ((Element) S.elementAt(i)).distance);
		}
		System.out.println("D:");
		for (int i = 0; i < D.size(); i++) {
			System.out.println(((Element) D.elementAt(i)).sommet + ","
					+ ((Element) D.elementAt(i)).distance);
		}

		while (C != -1) {

			int indice_min = 0;
			int dm = INFINI;
			int sm = ((Element) D.elementAt(0)).sommet;
			for (int i = 0; i < D.size(); i++) {
				if (dm > ((Element) D.elementAt(i)).distance) {
					dm = ((Element) D.elementAt(i)).distance;
					sm = ((Element) D.elementAt(i)).sommet;
					indice_min = i;
				}
			}
			Element m = new Element(sm, dm);

			S.addElement(m);
			System.out.println("S après avec taille= " + S.size());
			for (int i = 0; i < S.size(); i++) {
				System.out.println(((Element) S.elementAt(i)).sommet + ","
						+ ((Element) S.elementAt(i)).distance);
			}
			D.removeElementAt(indice_min);
			System.out.println("D après avec taille= " + D.size());
			for (int i = 0; i < D.size(); i++) {
				System.out.println(((Element) D.elementAt(i)).sommet + ","
						+ ((Element) D.elementAt(i)).distance);
			}

			System.out.println("élément à supprimer n'est pas trouvé");

			for (int i = 0; i < D.size(); i++) {
				Element x = (Element) D.elementAt(i);
				if (arc(m.sommet, x.sommet)) {
					int d = m.distance + valeurArc(m.sommet, x.sommet);
					if (d < x.distance) {
						x.distance = d;
						D.setElementAt(x, i);
					}
				}
			}
			for (int i = 0; i < S.size(); i++) {

				if (((Element) S.elementAt(i)).sommet == arr) {
					C = -1;
				}
			}
		}
		return S;
	}
}