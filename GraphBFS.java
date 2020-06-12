import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

	private int V;
	private static ArrayList<LinkedList<Integer>> adj;

	GraphBFS(int v) {
		V = v;
		adj = new ArrayList<LinkedList<Integer>>();
		for (int i = 0; i < v; i++) {
			adj.add(i, new LinkedList<Integer>());
		}

	}

	private void addEdge(int v, int w) {
		adj.get(v).add(w);

	}

	private LinkedList<Integer> BFS(int knoten) {
		int fürQuellenAlleine = 0; 
		Queue<Integer> q = new LinkedList<>();
		LinkedList<Integer> erg = new LinkedList<Integer>();

		q.add(knoten);

		while (!q.isEmpty()) {
			int aktKnoten = q.poll();
			if(!erg.contains(aktKnoten)) {
			erg.add(aktKnoten);
			
			for (int i : adj.get(aktKnoten)) {
				if (!erg.contains(i)) {
					q.add(i);
				}

			}
			}
			if (q.isEmpty() && erg.size() < V) {
				q.add(fürQuellenAlleine++);
				
			}

		
		}
		System.out.println(erg);
		return erg;

	}

	public static void main(String args[]) {
		GraphBFS g = new GraphBFS(4);

		//g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);
		System.out.println(adj);
		g.BFS(2);
	}

}
