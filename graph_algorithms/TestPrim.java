import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Ankeeta Shah (abs2218) TestPrim.java
 *
 * Tests Problem 9 from Graph.java Reads in a map file and runs
 * getMinimumSpanningTree on the graph to compute the shortest path between two
 * cities
 * 
 * 
 */
public class TestPrim {

	public static void main(String[] args) {

		// The problem is basically that the priority queue is not compatible
		// with Vertex because Vertex is not comparable
		Graph g = new Graph();
		try {

			g = MapReader.readGraph(args[0], (args[1]));
		} catch (Exception e) {
			System.out.println("Check command line arguments.");
			System.exit(0);
		}

		Graph g2 = g.getMinimumSpanningTree((String) args[2]);
		g2.printAdjacencyList();

		DisplayGraph display2 = new DisplayGraph(g2);
		display2.setVisible(true);
		
		
		
		
		
		
		
	}

}
