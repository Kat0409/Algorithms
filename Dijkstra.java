import java.util.*;

public class Dijkstra {
	private int dist[];
	private Set<Integer> knotenwerte;
	private PriorityQueue<Node> pq;
	private int V; // Number of vertices
	List<List<Node>> adj;

	public Dijkstra(int V) {
		this.V = V;
		dist = new int[V];
		knotenwerte = new HashSet<Integer>();
		pq = new PriorityQueue<Node>(V, new Node());
	}

	// Function for Dijkstra's Algorithm
	public void dijkstraAl(List<List<Node>> adj, int src) {

		this.adj = adj;
		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		pq.add(new Node(src, 0));
		dist[src] = 0;

		while (knotenwerte.size() != V && !pq.isEmpty()) { // wichtig !pg.isEmpty() abgrage, sonst crasht es wenn es keine
			System.out.println(knotenwerte);
			System.out.println("pg " +pq.peek().node);// Wege gibt (z.B. nur senke)

			int u = pq.remove().node;
			knotenwerte.add(u);

			neighbours(u);

		}

	}

	private void neighbours(int u) {
		// nehm alle neighbours aus PQ
		// sind die kosten billiger als vorher?
		// dann abspeichern ind dist
		// nimm den billigsten und pack den in die pq und settled.add(billigsten)
		int edgeCost = -1;
		int newCost = -1;

		for (Node n : adj.get(u)) {
				edgeCost = n.cost;
				newCost = edgeCost + dist[u];
			
			if (newCost < dist[n.node]) {
				dist[n.node] = newCost;
			}

			pq.add(new Node(n.node, dist[n.node]));

		}

	}

	// Driver code
	public static void main(String arg[]) {
		int V = 5;
		int source = 1;

		// Adjazentliste
		List<List<Node>> adj = new ArrayList<List<Node>>();

		// adj muss für jeden Knoten initialisiert werden
		for (int i = 0; i < V; i++) {
			List<Node> item = new ArrayList<Node>();
			adj.add(item);
		}

		// generiere Graph
		adj.get(0).add(new Node(4, 2));
		adj.get(1).add(new Node(2, 5));
		adj.get(1).add(new Node(3, 9));
		adj.get(1).add(new Node(0, 9));
		adj.get(1).add(new Node(4, 12));
		adj.get(2).add(new Node(3, 1));
		adj.get(3).add(new Node(0, 8));
		adj.get(3).add(new Node(4, 4));
		

		
		//Berechnung
		Dijkstra dpq = new Dijkstra(V);
		dpq.dijkstraAl(adj, source);

		//Print kürzester werg
		System.out.println("Kürzester Weg :");
		for (int i = 0; i < dpq.dist.length; i++) {
			System.out.println("von " + source + " nach " + i);
			if (dpq.dist[i] == Integer.MAX_VALUE) {
				System.out.println(" gibt es keinen Pfad ");
			} else {
				System.out.println(" ist " + dpq.dist[i]);
			}
		}
	}
}

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
