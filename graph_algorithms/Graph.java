/** Ankeeta Shah (abs2218)
 * MapReader.java
 * Problems 4, 6, 7, 8, 9
 * 
 * Problem 4: addUndirectedEdge method to Graph that adds an undirected edge between the vertices 
 * 
 * Problem 6: computeEuclidianCost method that computes euclidean distance between two coordinates
 * 			  computeeEuclidianCosts() computes and sets the cost for each edge using the euclidean distance 
 * 
 * Problem 7: doBfs uses Breadth First Search to find the unweighted shortest path from the start vertex 
 * 			  Has tester class TestBfs 
 * 
 * Problem 8: doDijkstra uses Dijkstra's algorithm to find the shortest path
 * 			  uses getWeightedShortestPath 
 * 			  Utilizes Path class (used for the PriorityQueue)
 * 			  Has tester class TestDijkstra
 * 
 * Problem 9: doPrim uses Prim's algorithm to find a minimum spanning tree rooted in the input s
 *            Has tester class TestPrim 
 * 
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class Graph {

	// Keep a fast index to nodes in the map
	protected Map<String, Vertex> vertices;

	/**
	 * Construct an empty Graph.
	 */
	public Graph() {
		vertices = new HashMap<String, Vertex>();
	}

	public void addVertex(String name) {
		Vertex v = new Vertex(name);
		addVertex(v);
	}

	public void addVertex(Vertex v) {
		if (vertices.containsKey(v.name))
			throw new IllegalArgumentException(
					"Cannot create new vertex with existing name.");
		vertices.put(v.name, v);
	}

	public Collection<Vertex> getVertices() {
		return vertices.values();
	}

	public Vertex getVertex(String s) {
		return vertices.get(s);
	}

	/**
	 * Add a new edge from u to v. Create new nodes if these nodes don't exist
	 * yet. This method permits adding multiple edges between the same nodes.
	 * 
	 * @param u
	 *            the source vertex.
	 * @param w
	 *            the target vertex.
	 */
	public void addEdge(String nameU, String nameV, Double cost) {
		if (!vertices.containsKey(nameU))
			addVertex(nameU);
		if (!vertices.containsKey(nameV))
			addVertex(nameV);
		Vertex sourceVertex = vertices.get(nameU);
		Vertex targetVertex = vertices.get(nameV);
		Edge newEdge = new Edge(sourceVertex, targetVertex, cost);
		sourceVertex.addEdge(newEdge);
	}

	/**
	 * Add a new edge from u to v. Create new nodes if these nodes don't exist
	 * yet. This method permits adding multiple edges between the same nodes.
	 * 
	 * @param u
	 *            unique name of the first vertex.
	 * @param w
	 *            unique name of the second vertex.
	 */
	public void addEdge(String nameU, String nameV) {
		addEdge(nameU, nameV, 1.0);
	}

	/****************************
	 * Your code follow here. *
	 ****************************/

	/*
	 * Problem 4 In order to get an undirected edge, the relationship between
	 * the two verticies should be mutual s --> t as well as t --> s to get s
	 * <--> t
	 */
	public void addUndirectedEdge(String s, String t, double cost) {
		addEdge(s, t, cost);
		addEdge(t, s, cost);
	}

	/*
	 * Problem 6 computes the euclidean cost between two coordinates
	 */
	public double computeEuclideanCost(double ux, double uy, double vx,
			double vy) {
		double cost;
		cost = Math.sqrt(Math.pow(ux - vx, 2) + Math.pow(uy - vy, 2));
		return cost;
	}

	public void computeAllEuclideanCosts() {

		for (String v : vertices.keySet()) {
			for (Edge e : vertices.get(v).getEdges()) { // edges

				Vertex s = e.sourceVertex;
				Vertex t = e.targetVertex;

				double ux = s.posX;
				double uy = s.posY;
				double vx = t.posX;
				double vy = t.posY;
				double cost = computeEuclideanCost(ux, uy, vx, vy);
				e.cost = cost;

			}
		}
	}

	/*
	 * Problem 7: BFS
	 */
	/** BFS */
	public void doBfs(String s) {
		for (Vertex v : vertices.values()) {
			double cost = Double.POSITIVE_INFINITY; // cost starts out as
			// infinity
			v.cost = cost;
			v.backpointer = null; // start out as null
			v.visited = false; // set visited flag to false originally
		}

		Vertex start = vertices.get(s);
		// want the cost of the start vertex to be zero
		start.cost = 0.0;
		LinkedList<Vertex> queue = new LinkedList<Vertex>();
		queue.add(start); //  add to visited after you add to queue
		start.visited = true;

		while (!queue.isEmpty()) {
			Vertex v = queue.poll(); // return and remove vertex
			for (Edge e : v.getEdges()) {
				Vertex adjV = e.targetVertex;
				if (!adjV.visited) {
					adjV.cost = (v.cost + 1);
					adjV.backpointer = v;
					adjV.visited = true;
					queue.add(adjV);

				}
			}
		}
	}

	public Graph getUnweightedShortestPath(String s, String t) {
		Graph g = new Graph();
		doBfs(s);
		Vertex target = vertices.get(t);
		Vertex source = vertices.get(s);

		for (Vertex v : vertices.values()) {
			g.addVertex(new Vertex(v.toString(), v.posX, v.posY));
		}

		while (target != source) {
			g.addEdge(target.toString(), target.backpointer.toString());
			target = target.backpointer;
		}
		return g;
	}

	/*
	 * Problem 8: Dijkstra's
	 * Provide an example of a city pair (add a comment to doDijkstra), 
	 * for which the unweighted shortest path found by BFS is different 
	 * than the weighted shortest path found by Dijkstra's algorithm:
	 * 
	 * NewYork to Portland is different for BFS and Dijkstra
	 * BFS: NewYork, Montreal, SaultSaintMarie, Winnipeg, Calgary, Seattle, Portland
	 * Dijkstra: NewYork, Pittsburgh, Chicago, Duluth, Helena, Seattle, Portland
	 * 
	 * /** Dijkstra's
	 */
	public void doDijkstra(String s) {
		for (Vertex v : vertices.values()) {
			v.cost = Double.POSITIVE_INFINITY;
			v.backpointer = null;
			v.visited = false;
		}

		PriorityQueue<Path> pq = new PriorityQueue<Path>(); // utilizes PATH
		// class
		// do not want to keep a queue of vertices, but rather a queue of
		// (vertex, vertex.cost)

		Vertex start = vertices.get(s);
		if (start == null)
			System.out.println("Start vertex does not exist");

		start.cost = 0.0;
		Path path = new Path(start, start.cost);
		pq.add(path);

		while (!pq.isEmpty()) {
			Path vpath = pq.remove();
			Vertex v = vpath.vertex;
			if (v.visited) // already processed v
				continue;

			v.visited = true;

			for (Edge e : v.getEdges()) {
				Vertex w = e.targetVertex;
				double cost = e.cost;

				if (cost < 0)
					System.out.println("Graph has negative edges");

				if (w.cost > v.cost + cost) {
					w.cost = v.cost + cost;
					w.backpointer = v;
					path = new Path(w, w.cost);
					pq.add(path);
				}
			}
		}
	}

	public Graph getWeightedShortestPath(String s, String t) {
		doDijkstra(s);
		Vertex target = vertices.get(t);
		Vertex source = vertices.get(s);
		Graph g = new Graph();

		for (Vertex v : vertices.values()) {
			g.addVertex(new Vertex(v.toString(), v.posX, v.posY));
		}
		while (target != source) {
			g.addEdge(target.toString(), target.backpointer.toString());
			target = target.backpointer;
		}

		g.computeAllEuclideanCosts();
		return g;
	}

	/*
	 * Problem 9: Prim's Prim's algorithm uses the weight of the edge to
	 * determine the best path. // Uses Path class as well
	 */
	/** Prim's */
	public void doPrim(String s) {
		for (Vertex v : vertices.values()) {
			v.cost = Double.MAX_VALUE;
			v.backpointer = null;
			v.visited = false;

		}
		
		PriorityQueue<Path> pq = new PriorityQueue<Path>();
		Vertex start = vertices.get(s);
		if (start == null)
			System.out.println("Start vertex does not exist");

		start.cost = 0.0;
		Path path = new Path(start, start.cost);
		pq.add(path);

		while (!pq.isEmpty()) {
			Path vpath = pq.remove();
			Vertex v = vpath.vertex; //edge is (v,w)
			if (v.visited) // already processed v
				continue;

			v.visited = true;
			for (Edge e : v.getEdges()) {
				Vertex w = e.targetVertex;
				double cost = e.cost;
				
				if (!w.visited) {
				if (cost < 0)
					System.out.println("Graph has negative edges");

				if (w.cost > cost) {
					w.cost = cost;
					w.backpointer = v;
					path = new Path(w, w.cost);
					pq.add(path);
					
					
					
					
					
				}
			}
				
			}
		}
		
		
		
		

	}

	public Graph getMinimumSpanningTree(String s) {
		doPrim(s);
		Graph g = new Graph();

		for (Vertex v : vertices.values()) {
			g.addVertex(new Vertex(v.toString(), v.posX, v.posY));
		}
		for (Vertex v : vertices.values()) {
			if (v.backpointer != null) {
				g.addEdge(v.toString(), v.backpointer.toString());
			}
		}

		g.computeAllEuclideanCosts();//is this incorrect?
		return g;
	}

	/*************************/

	public void printAdjacencyList() {
		for (String u : vertices.keySet()) {
			StringBuilder sb = new StringBuilder();
			sb.append(u);
			sb.append(" -> [ ");
			for (Edge e : vertices.get(u).getEdges()) {
				sb.append(e.targetVertex.name);
				sb.append("(");
				sb.append(e.cost);
				sb.append(") ");
			}
			sb.append("]");
			System.out.println(sb.toString());
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		g.addVertex(new Vertex("v0", 0, 0));
		g.addVertex(new Vertex("v1", 0, 1));
		g.addVertex(new Vertex("v2", 1, 0));
		g.addVertex(new Vertex("v3", 1, 1));

		g.addEdge("v0", "v1");
		g.addEdge("v1", "v2");
		g.addEdge("v2", "v3");
		g.addEdge("v3", "v0");
		g.addEdge("v0", "v2");
		g.addEdge("v1", "v3");

		g.printAdjacencyList();

		DisplayGraph display = new DisplayGraph(g);
		display.setVisible(true);
	}

}
