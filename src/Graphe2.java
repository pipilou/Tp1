import java.io.IOException;
import java.util.Vector;


public class Graphe2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
Chemin f= new Chemin();
f.affi();
Vector S = f.plusCourtChemin(0,4);
for (int i = 0; i < S.size(); i++) {
	System.out.println(((Element) S.elementAt(i)).sommet + ", "
			+ ((Element) S.elementAt(i)).distance);
}


}}
