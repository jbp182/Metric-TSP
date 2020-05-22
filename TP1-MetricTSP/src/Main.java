import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import algorithms.Christofides;
import algorithms.GreedyTSP;
import entities.Graph;

public class Main {
	
	public static void main(String[] args) throws IOException {

		String dir = new File("").getAbsolutePath() + "/tests/";
		File folder = new File(dir);
		File[] files = folder.listFiles();
		
		List<String> names = new LinkedList<String>();
		List<Double> greedies = new LinkedList<Double>();
		List<Double> christofs = new LinkedList<Double>();
		
		for (File file : files) {
			if (file.isFile()) {
				String filename = file.getName();
				String[] params = filename.split("_");
				
				int numNodes = Integer.parseInt(params[2]);
				
				// solve problem from file
				Graph g = createGraph(file, numNodes);
				double gr = greedy(g);
				double ch = christofides(g);
				
				// save to lists
				String testName = filename.split("\\.")[0];
				names.add(testName);
				greedies.add(gr);
				christofs.add(ch);
			}			
		}
		
		// write to file
		Iterator<String> itNames = names.iterator();
		Iterator<Double> itGr = greedies.iterator();
		Iterator<Double> itCh = christofs.iterator();
		
		FileWriter out = new FileWriter(new File("results.csv"));
		out.write("Tests,");
		while(itNames.hasNext())
			out.write(itNames.next() + ",");
		out.write("\nGreedy,");
		while(itGr.hasNext()) 
			out.write(itGr.next() + ",");
		out.write("\nChristofides,");
		while(itCh.hasNext())
			out.write(itCh.next() + ",");
		out.close();
		
	}
	
	
	private static Graph createGraph(File file, int numNodes) throws FileNotFoundException {
		Graph g = new Graph(numNodes);
		Scanner in = new Scanner(file);
		
		for (int row = 0; row < numNodes; row++) {
			for (int col = 0; col < row; col++) {
				int cost = in.nextInt();
				g.addEdge(row, col, cost);
			}
			in.nextLine();
		}
		in.close();
		return g;		
	}

	private static double christofides(Graph g) {
		Christofides chris = new Christofides(g);
		chris.solve();
		return chris.getTotalCost();
	}


	private static double greedy(Graph g) {
		GreedyTSP greedy = new GreedyTSP(g);
		greedy.solve();
		return greedy.getTotalCost();
	}


}
