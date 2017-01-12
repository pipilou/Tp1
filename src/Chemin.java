import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

public class Chemin {
	ArrayList<Station> a = new ArrayList<Station>();

	Liste adj[];

	public Chemin() throws IOException {
		Fichier f = new Fichier();
		f.ouvrir("kelkonk_bus-2.geo", "Lecture");
		String chaine = "";
		while (chaine != null) {
			chaine = f.lire();
			if (chaine != null) {
				if (!chaine.substring(0, 1).equals("#")) {
					String t[] = chaine.split("\t");

					int g = Integer.parseInt(t[2]);
					int h = Integer.parseInt(t[1]);
					a.add(new Station(h, g, t[0]));

				}
			}
		}

		f.fermer();
		adj = new Liste[a.size()];
		String ligne = "";
		String arret = "";
		String arretp = "";
		String chaine1 = "";
		int val = 0;
		int pos = 0;
		Fichier c = new Fichier();
		c.ouvrir("kelkonk_bus-2.graph", "Lecture");

		for (int i = 0; i < adj.length; i++) {

			adj[i] = null;

		}
		while (chaine1 != null) {
			chaine1 = c.lire();
			if (chaine1 != null) {
				if (chaine1 != null) {
					if (!chaine1.equals("")) {
						if (!chaine1.substring(0, 1).equals("#")) {
							String s[] = chaine1.split(":");
							if (ligne.equals(s[0])) {

								arret = s[1];

								val = (int) distance(arret, arretp);
								pos = position(arretp);
								arretp = arret;
								adj[pos] = new Liste(position(arret), adj[pos],
										val);

							} else {
								ligne = s[0];
								arretp = s[1];
							}

						}
					}
				}
			}
		}
		c.fermer();
	}

	public void affi() {
		for (int i = 0; i < adj.length; i++) {
			System.out.print("sommet " + (i) + " :");
			if (adj[i] != null) {
				Liste b = adj[i];
				while (b != null) {
					System.out.print(b.num_noeud + " " + b.valeur + " ");
					if (b.suivant != null)
						System.out.print(" -> ");
					b = b.suivant;
				}
			}
			System.out.println(" null");
		}

	}

	public int vitesse(String l) {
		int v = 0;
		if (l.equals("A-a") || l.equals("B-a") || l.equals("A-b")
				|| l.equals("B-b")) {

			v = 10;

		}

		if (l.equals("C-a") || l.equals("C-b")) {

			v = 20;

		}

		return v;

	}

	public int position(String arret) {
		int p = 0;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).nom.equals(arret)) {

				p = i;

			}

		}

		return p;
	}

	public double distance(String n, String f) {
		double d1 = 0;
		double d = 0;
		int x1 = 0;
		int x2 = 0;
		int y1 = 0;
		int y2 = 0;
		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).nom.equals(n)) {
				x1 = a.get(i).getx();
				y1 = a.get(i).gety();
			}

		}

		for (int i = 0; i < a.size(); i++) {
			if (a.get(i).nom.equals(f)) {
				x2 = a.get(i).getx();
				y2 = a.get(i).gety();
			}
		}

		int x = x1 - x2;
		int y = y1 - y2;
		d1 = Math.pow(x, 2) + Math.pow(y, 2);
		d = Math.sqrt(d1);

		return d;
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

	public Vector plusCourtChemin(int num_sommet, int arr) {
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