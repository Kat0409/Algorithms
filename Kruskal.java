import java.util.ArrayList;
import java.util.LinkedList;

public class Kruskal {

	private int V;
	private LinkedList<tuple> adj;

	kruskal(int v) {
		V = v;
		adj = new LinkedList<tuple>();
	}

	private void addEdge(int von, int bis, int kosten) {
		adj.add(new tuple(von, bis, kosten));

	}

	public static void main(String args[]) {

		kruskal g = new kruskal(4);

		g.addEdge(0, 1, 10);
		g.addEdge(0, 2, 6);
		g.addEdge(0, 3, 5);
		g.addEdge(1, 3, 15);
		g.addEdge(2, 3, 4);

		g.findeKruskal(g);

	}

	private void findeKruskal(kruskal g) {

		adj = sort(adj);

		ArrayList<tuple> erg = new ArrayList<tuple>();

		erg.add(adj.poll());

		int i = 0;
		while (i < adj.size() && erg.size() < V) {

			int v = adj.get(i).v;
			int w = adj.get(i).w;
			if (!hasCircle(erg, v, w)) {
				erg.add(adj.poll());
			} else {
				adj.poll();
			}

		}
		for (int j = 0; j < erg.size(); j++) {
			System.out.println("von " + erg.get(j).v + "nach " + erg.get(j).w);
		}

	}

	private boolean hasCircle(ArrayList<tuple> erg, int v, int w) {
		for (int ergi = 0; ergi < erg.size(); ergi++) {
			// erst v abprüfen, wenn v in erg ist dann w abprüfen
			if (erg.get(ergi).v == v || erg.get(ergi).v == w) {
				for (int ergi2 = 0; ergi2 < erg.size(); ergi2++) {
					if (erg.get(ergi2).w == w || erg.get(ergi2).v == v) {
						return true;
					}
				}
			}
		}
		return false;

	}

	//kanten der Größe nach Sortieren
  private LinkedList<tuple> sort(LinkedList<tuple> adj) {
		LinkedList<tuple> adjSortiert = new LinkedList<tuple>();
		adjSortiert.add(adj.removeFirst());
		int j = 0;
		while (!adj.isEmpty()) {
			int kostenAktuell = adj.peek().getKosten();

			while (j < adjSortiert.size()) {
				if (kostenAktuell < (adjSortiert.get(j).getKosten())) {
					adjSortiert.add(j, adj.poll());
					break;
				}
				if (kostenAktuell > adjSortiert.getLast().getKosten()) {
					adjSortiert.add(adj.poll());
					break;
				}
				j++;
			}
			j = 0;
		}
		return adjSortiert;

	}

	public class tuple {
		private int w; //Kannte von
		private int kosten;
		private int v; // kannte nach

		tuple(int v, int w, int kosten) {
			this.v = v;
			this.w = w;
			this.setKosten(kosten);
		}

		public int getKosten() {
			return kosten;
		}

		public void setKosten(int kosten) {
			this.kosten = kosten;
		}
	}
}
