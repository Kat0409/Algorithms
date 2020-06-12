import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

//Class to represent a node in the graph 
class Node implements Comparator<Node> {
	public int node;
	public int cost;

	public Node() {
	}

	public Node(int node, int cost) {
		this.node = node;
		this.cost = cost;
	}

	@Override
	public int compare(Node node1, Node node2) {
		if (node1.cost < node2.cost)
			return -1;
		if (node1.cost > node2.cost)
			return 1;
		return 0;
	}
}

public class Prim {
	private PriorityQueue<Node> pq;
	private int V; // Number of vertices
	static List<Integer> erg;

	public Prim(int V) {
		this.V = V;
		pq = new PriorityQueue<Node>(V, new Node());

	}

	private int primalg(List<List<Node>> adj, int source, List<Integer> erg) {
		int sum=0; 
		for (int i = 0; i < V; i++) {
			if (i == source) {
				pq.add(new Node(i, 0));
			}
			pq.add(new Node(i, Integer.MAX_VALUE)); // fuelle am Anfang alle Werte mit "unendlich, nicht erreichbar"
		}

		while (!pq.isEmpty()) {

			Node u = getMin(pq,erg); // ziehe kleinsten Wert aus der PriorityQueue
					
			erg.add(u.node);
			sum+= u.cost;
			if (erg.size() == V) { // hoere auf wenn erg genauso groß wie anzahl der Knoten
				break;
			}
			
			 else {

				aktualisiereNachbarn(pq, u, adj); // veraendern sich die Nachbarn? Schnellerer/neuerer Weg? 
				pq.remove(u);
			}
		}
		
		return sum; 

	}

	private void aktualisiereNachbarn(PriorityQueue<Node> pq, Node u, List<List<Node>> adj) {
		//System.out.println("s " + adj.get(u.node).size());
		System.out.println(u.node);

		for (Node n : pq) {
			for (Node e : adj.get(u.node)) {
				if (n.node == e.node && e.cost < n.cost) {
					n.cost = e.cost;
				}
			}
		}
	}

	public Node getMin(PriorityQueue<Node> pq,List<Integer> erg) {
		Node min = new Node(V + 1, Integer.MAX_VALUE);
		for (Node n : pq) {
		
			if (n.cost < min.cost) {
				if(!erg.contains(n.node)) {
					min = n;
				}
				
			}
		}
		return min;
	}

	// Driver code
	public static void main(String arg[]) {
		int V = 9;
		int source = 0;

		// Adjacency list representation of the
		// connected edges
		List<List<Node>> adj = new ArrayList<List<Node>>();
		List<Integer> erg = new LinkedList<Integer>();
		// Initialize list for every node
		for (int i = 0; i < V; i++) {
			List<Node> item = new ArrayList<Node>();
			adj.add(item);
		} // Calculate the single source shortest path
		Prim ppq = new Prim(V);

		// Inputs for the DPQ graph
		adj.get(0).add(new Node(1, 4));
		adj.get(0).add(new Node(7, 8));
		adj.get(1).add(new Node(0, 4));
		adj.get(7).add(new Node(0, 8));
		adj.get(1).add(new Node(2, 8));
		adj.get(1).add(new Node(7, 11));
		adj.get(2).add(new Node(1, 8));
		adj.get(7).add(new Node(1, 11));
		adj.get(2).add(new Node(3, 7));
		adj.get(2).add(new Node(8, 2));
		adj.get(2).add(new Node(5, 4));
		adj.get(3).add(new Node(2, 7));
		adj.get(8).add(new Node(2, 2));
		adj.get(5).add(new Node(2, 4));
		adj.get(3).add(new Node(4, 9));
		adj.get(3).add(new Node(5, 14));
		adj.get(4).add(new Node(3, 9));
		adj.get(5).add(new Node(3, 14));
		adj.get(4).add(new Node(5, 10));
		adj.get(5).add(new Node(4, 10));
		adj.get(5).add(new Node(6, 2));
		adj.get(6).add(new Node(5, 2));
		adj.get(6).add(new Node(7, 1));
		adj.get(6).add(new Node(8, 6));
		adj.get(5).add(new Node(6, 2));
		adj.get(7).add(new Node(6, 1));
		adj.get(8).add(new Node(6, 6));
		adj.get(7).add(new Node(8, 7));
		adj.get(8).add(new Node(7, 7));

		System.out.println("die Kosten des minimalen Spannbaums sind "+ppq.primalg(adj, source, erg));

		// Print the shortest path to all the nodes
		// from the source node
		System.out.println(erg);
	}
}
