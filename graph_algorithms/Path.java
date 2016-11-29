/**
 * Ankeeta Shah (abs2218) Path.java
 * 
 * Wrapper class (like pair) for priority queue to be used in Dijkstra and Prim
 * (vertex, vertex.cost) stored in queue instead of just vertex (which is not
 * comparable)
 * 
 */
public class Path implements Comparable<Path> {
	public Vertex vertex;
	public double cost;

	public Path(Vertex v, double c) {
		vertex = v;
		cost = c;
	}

	public int compareTo(Path path) {
		double otherCost = path.cost;
		return cost < otherCost ? -1 : cost > otherCost ? 1 : 0;
	}
}
