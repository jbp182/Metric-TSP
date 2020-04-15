import java.util.Iterator;
import java.util.Scanner;

import algorithms.GreedyTSP;
import entities.Graph;

public class Main {
	
	private static final String GREEDY = "GREEDY";
	private static final String CHRISTOFIDES = "CHRISTOFIDES";
	
	private static final String NO_SUCH_ALG = "Please choose greedy or christofides algorithm.";

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		// build graph
		System.out.printf("Enter number of nodes: ");
		int numNodes = in.nextInt(); in.nextLine();

		Graph g = new Graph(numNodes);
		System.out.println("Created nodes 0 to " + (numNodes-1));

		int numEdges = numNodes * (numNodes - 1) / 2;
		System.out.printf("\nEnter information for %d edges. Format: x y cost\n", numEdges);
		// add edges
		try {
			for (int i = 0; i < numEdges; i++) {
				int origin = in.nextInt();
				int destiny = in.nextInt();
				int cost = in.nextInt();
				in.nextLine();
				
				g.addEdge(origin, destiny, cost);
			}
		} catch (Exception e) {
			System.out.println("ERROR: wrong format");;
			System.exit(1);
		}
		
		
		System.out.println("\nAlgorithm to execute: greedy or christofides?");
		// execute wanted algorithm
		String alg = in.nextLine().toUpperCase().trim();
		switch(alg) {
		case GREEDY:
			greedy(g);
			break;
		case CHRISTOFIDES:
			christofides(g);
			break;
		default:
			System.out.println(NO_SUCH_ALG);
		}
		
		in.close();
	}
	
	private static void greedy(Graph g) {
		GreedyTSP greedy = new GreedyTSP(g);
		greedy.solve();
		int cost = greedy.getTotalCost();
		Iterator<Integer> pre = greedy.preorderTraversal();
		
		System.out.println("\n-------------------------");
		System.out.println(GREEDY);
		System.out.println("Computed cost: " + cost);
		System.out.printf("Computed solution: ");
		while (pre.hasNext())
			System.out.printf("%d ", pre.next());
		System.out.println();
	}
	
	private static void christofides(Graph g) {
		
	}

}
