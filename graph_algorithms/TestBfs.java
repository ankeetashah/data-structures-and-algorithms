/**
 * Ankeeta Shah (abs2218) TestBfs.java
 *
 * Tests Problem 7 from Graph.java Reads in a map file and runs
 * getUnweightedShortestPath on the graph to compute the shortest path between
 * two cities
 * 
 */
public class TestBfs {

	public static void main(String[] args) {

		Graph g = new Graph();
		try {

			g = MapReader.readGraph(args[0], args[1]);
		} catch (Exception e) {
			System.out.println("Check command line arguments.");
			System.exit(0);
		}

		Graph g2 = g.getUnweightedShortestPath((String) args[2],
				(String) args[3]);
		g2.printAdjacencyList();

		DisplayGraph display2 = new DisplayGraph(g2);
		display2.setVisible(true);

	}

}
