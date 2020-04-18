import java.util.Iterator;
import java.util.Scanner;

import algorithms.Christofides;
import algorithms.GreedyTSP;
import entities.Graph;

public class Main {

	private static final String GREEDY = "GREEDY";
	private static final String CHRISTOFIDES = "CHRISTOFIDES";

	private static final String NO_SUCH_ALG = "Please choose greedy or christofides algorithm.";

	private static final String AUTOMATIC_GENERATE_ANSWER = "A";
	

	private static final String EDGE_INFORMATION_FOMART = "Origin: %d,Destiny: %d,Cost: %.1f\n";
	
	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		// build graph
		System.out.printf("Enter number of nodes: ");
		int numNodes = in.nextInt();
		in.nextLine();

		Graph g = new Graph(numNodes);
		System.out.println("Created nodes 0 to " + (numNodes - 1));

		int numEdges = numNodes * (numNodes - 1) / 2;

		System.out.printf(
				"\nWould you like to manual input your graph edges information, or generate a random complete graph?\n");
		System.out.printf("Input %s for automatic generation\n", AUTOMATIC_GENERATE_ANSWER);
		System.out.printf("else for manual input\n");
		String answer = in.nextLine().trim().toUpperCase();
		if (answer.equals(AUTOMATIC_GENERATE_ANSWER)) {
	
		} else {

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
				System.out.println("ERROR: wrong format");
				e.printStackTrace();
				System.exit(1);
			}
		}

		System.out.println("\nAlgorithm to execute: greedy or christofides?");
		// execute wanted algorithm
		String alg = in.nextLine().toUpperCase().trim();
		showGraph(g);
		switch (alg) {
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
		for(int i=0; i < numNodes;i++) {
			double[] destinies = g.incidentEdges(i);
			for(int j= 0; j < numNodes; j++) {
				double cost = destinies[j];
				if(cost > 0)
				System.out.printf(EDGE_INFORMATION_FOMART,i,j,cost);
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
0 1 1
0 2 3
0 3 3
0 4 1
1 2 2
2 3 4
1 4 1
2 3 4
2 4 3
3 4 3
	 */
	
/*
1 2 1
1 3 1
1 4 2
2 3 2
2 4 1
3 4 1
0 1 1
0 2 1
0 3 1
0 4 1



*/

}
