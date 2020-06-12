import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class GraphDFS {

	private int V;
	private static ArrayList<LinkedList<Integer>> adj;

	GraphDFS(int v) {
		V = v;
		adj = new ArrayList<LinkedList<Integer>>();
		for (int i = 0; i < v; i++) {
			adj.add(i, new LinkedList<Integer>());
		}

	}

	private void addEdge(int v, int w) {
		adj.get(v).add(w);

	}

	private LinkedList<Integer> DFS(int knoten) {
		int fürQuellenAlleine = 0;
		Stack<Integer> stack = new Stack<Integer>();
		LinkedList<Integer> erg = new LinkedList<Integer>();

		stack.add(knoten);

		while (stack.size() > 0) {
			int aktKnoten = stack.pop();
			if (!erg.contains(aktKnoten)) {
				erg.add(aktKnoten);

				for (int i : adj.get(aktKnoten)) {
					if (!erg.contains(i)) {
						stack.push(i);
					}

				}
			}
			if (stack.isEmpty() && erg.size() < V) {
				stack.add(fürQuellenAlleine);

			}

		}
		System.out.println(erg);
		return erg;

	}

	public static void main(String args[]) {
		GraphDFS g = new GraphDFS(4);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 2);
		g.addEdge(2, 0);
		g.addEdge(2, 3);
		g.addEdge(3, 3);

		g.DFS(3);
	}

}
