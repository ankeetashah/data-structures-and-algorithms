/**
 * Ankeeta Shah (abs2218) TestDijkstra.java
 *
 * Tests Problem 8 from Graph.java Reads in a map file and runs
 * getWeightedShortestPath on the graph to compute the shortest path between two
 * cities
 * 
 * 
 */
public class TestDijkstra {

	public static void main(String[] args) {

		Graph g = new Graph();
		try {

			g = MapReader.readGraph(args[0], args[1]);
		} catch (Exception e) {
			System.out.println("Check command line arguments.");
			System.exit(0);
		}
		Graph g2 = g.getWeightedShortestPath(args[2], args[3]);

		DisplayGraph display2 = new DisplayGraph(g2);
		display2.setVisible(true);
	}

}
