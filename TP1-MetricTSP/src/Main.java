import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import algorithms.Christofides;
import algorithms.GreedyTSP;
import entities.Graph;

public class Main {

	private static final String GREEDY = "GREEDY";
	private static final String CHRISTOFIDES = "CHRISTOFIDES";

	private static final String NO_SUCH_ALG = "Please choose greedy or christofides algorithm.";


	public static void main(String[] args) throws IOException {
		
		
		String currentDir =new File("").getAbsolutePath();
		currentDir += "/tests";
		 
				 
		
		String[] list = new File(currentDir).list();
		BufferedReader in;
		System.out.println("NomeDoFicheiro Greedy Christofides");
		
		for (String text: list) {
			String[] names = text.split("N");
			int numNode = Integer.parseInt(names[0]);
			Graph g = new Graph(numNode);
			
			in = new BufferedReader(new InputStreamReader(new FileInputStream(currentDir+"/"+text)));
			System.out.print(text+" ");
			int numEdges = numNode * (numNode - 1) / 2;
			getMatriz(in, numEdges, g);
			execute(GREEDY, g);
			execute(CHRISTOFIDES, g);
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

		System.out.println(chris.getTotalCost()+" ");
		
		

	}


	private static void greedy(Graph g) {
		GreedyTSP greedy = new GreedyTSP(g);
		greedy.solve();
		System.out.print(" "+greedy.getTotalCost()+" ");
		
		
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
