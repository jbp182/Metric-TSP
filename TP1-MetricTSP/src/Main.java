import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import algorithms.Christofides;
import algorithms.GreedyTSP;
import entities.Graph;

public class Main {

	private static final String GREEDY = "GREEDY";
	private static final String CHRISTOFIDES = "CHRISTOFIDES";

	private static final String NO_SUCH_ALG = "Please choose greedy or christofides algorithm.";

	private static final String EDGE_INFORMATION_FOMART = "Origin: %d,Destiny: %d,Cost: %.1f\n";

	public static void main(String[] args) throws IOException {

		// build graph

		// int numNodes[] = {3,5,10,15,30,50,100};

		int numNode = 5;
		int testNum = 0;
		final int MAX_TEST_ORDER = 4;
		String fileName;
		BufferedReader in;
		for (; testNum <= MAX_TEST_ORDER; testNum++) {
			
			Graph g = new Graph(numNode);
			fileName = String.format("%dNodesGraph%d.txt", numNode, testNum);
		//	fileName = "t2.txt";
			in= new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			System.out.println("\nThis is "+ fileName);
			int numEdges = numNode * (numNode - 1) / 2;
			getMatriz(in, numEdges, g);
			execute(GREEDY, g);
			execute(CHRISTOFIDES, g);
			System.out.println();
			in.close();
		}
	}

	private static void execute(String algorithm, Graph g) {
		// execute wanted algorithm

		switch (algorithm) {
		case GREEDY:
			greedy(g);
			break;
		case CHRISTOFIDES:
			christofides(g);
			break;
		default:
			System.out.println(NO_SUCH_ALG);
		}

	}

	private static void getMatriz(BufferedReader in, int numEdges, Graph g) {
		try {
			for (int i = 0; i < numEdges; i++) {
				String[] line = in.readLine().split(" ");
				int origin = Integer.parseInt(line[0]);
				int destiny = Integer.parseInt(line[1]);
				double cost = Double.parseDouble(line[2]);
				g.addEdge(origin, destiny, cost);
			}
		} catch (Exception e) {
			System.out.println("ERROR: wrong format");
			e.printStackTrace();
			System.exit(1);

		}

	}

	private static void christofides(Graph g) {
		// TODO Auto-generated method stub
		Christofides chris = new Christofides(g);
		chris.solve();

		double cost = chris.getTotalCost();
		int[] result = chris.getWay();

		System.out.println("\n-------------------------");
		System.out.println(CHRISTOFIDES);
		System.out.println("Computed cost: " + cost);
		System.out.printf("Computed solution: ");
		for (int i = 0; i < result.length; i++) {
			System.out.printf("%d ", result[i]);
		}

	}

	private static void showGraph(Graph g) {
		int numNodes = g.numNodes();
		for (int i = 0; i < numNodes; i++) {
			double[] destinies = g.incidentEdges(i);
			for (int j = 0; j < numNodes; j++) {
				double cost = destinies[j];
				if (cost > 0)
					System.out.printf(EDGE_INFORMATION_FOMART, i, j, cost);
			}
		}
		System.out.println();
	}

	private static void greedy(Graph g) {
		GreedyTSP greedy = new GreedyTSP(g);
		greedy.solve();
		double cost = greedy.getTotalCost();
		Iterator<Integer> pre = greedy.preorderTraversal();

		System.out.println("\n-------------------------");
		System.out.println(GREEDY);
		System.out.println("Computed cost: " + cost);
		System.out.printf("Computed solution: ");
		while (pre.hasNext())
			System.out.printf("%d ", pre.next());
		System.out.println();
	}

	/*
	 * 0 1 1 0 2 3 0 3 3 0 4 1 1 2 2 2 3 4 1 4 1 2 3 4 2 4 3 3 4 3
	 */

	/*
	 * 1 2 1 1 3 1 1 4 2 2 3 2 2 4 1 3 4 1 0 1 1 0 2 1 0 3 1 0 4 1
	 * 
	 * 
	 * 
	 */

}
