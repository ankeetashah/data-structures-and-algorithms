/** Ankeeta Shah (abs2218)
 * MapReader.java
 * Problem 5
 * 
 * reads in a graph from vertex and edges txt files
 * and returns a new Graph object
 */
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MapReader {

	public static Graph readGraph(String vertexfile, String edgefile) {
		// create mapreader and graph
		MapReader mr = new MapReader();
		Graph g = new Graph();

		// scan files
		String vfile = mr.reader(vertexfile);
		String efile = mr.reader(edgefile);

		Scanner vScan = new Scanner(vfile);
		Scanner eScan = new Scanner(efile);

		// add vertices
		while (vScan.hasNextLine()) {
			String nameOfCity = vScan.next();
			int x = Integer.parseInt(vScan.next());
			int y = Integer.parseInt(vScan.next());

			Vertex v = new Vertex(nameOfCity, x, y);
			g.addVertex(v);
			// can also pass the x and y coordinates to euclidian distance
		}
		// add edges
		while (eScan.hasNextLine()) {

			String s = eScan.next();
			String t = eScan.next();

			Vertex u = g.getVertex(s);
			Vertex v = g.getVertex(t);

			double ux = u.posX;
			double uy = u.posY;
			double vx = v.posX;
			double vy = v.posY;

			double cost = Math
					.sqrt(Math.pow(ux - vx, 2) + Math.pow(uy - vy, 2));

			g.addUndirectedEdge(s, t, cost);
		}

		return g;
	}

	// scans the file
	public String reader(String s) {
		String str = "";
		try {
			Scanner scan = new Scanner(new FileReader(s));
			while (scan.hasNextLine()) {
				str += " " + scan.nextLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		return str.replaceAll(",", " ");
	}

	public static void main(String[] args) {
		Graph g = new Graph();
		try {
			g = MapReader.readGraph(args[0], args[1]);

		} catch (Exception e) {
			System.out.println("Check command line arguments.");
			System.exit(0);
		}
		g.computeAllEuclideanCosts();
		// g.printAdjacencyList();

		DisplayGraph display = new DisplayGraph(g);
		display.setVisible(true);

		// test from Jarvis
		/*
		 * try { g = MapReader.readGraph(args[0], args[1]); int notfound = 4;
		 * for (Vertex v : g.getVertices()) { if (v.name.equals("Denver")) { for
		 * (Edge e : v.getEdges()) { if (e.targetVertex.name.equals("Seattle")
		 * || e.targetVertex.name.equals("SanFrancisco") ||
		 * e.targetVertex.name.equals("Nashville") ||
		 * e.targetVertex.name.equals("Montreal")) notfound--; else {
		 * System.out.println("Found invalid edge in read graph"); return; } }
		 * break; } } if (notfound > 0) {
		 * System.out.println("missing edge in graph"); return; }
		 * 
		 * return; } catch (Exception e) { System.out.println("exception"); }
		 */

	}

}
