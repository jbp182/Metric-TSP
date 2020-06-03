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
				String testName = filename.split("\\.")[0];
				String[] params = filename.split("_");
				int numNodes = Integer.parseInt(params[2]);
				
				// solve problem from file
				Graph g = createGraph(file, numNodes);
				
				// greedy
				GreedyTSP greedy = new GreedyTSP(g);
				greedy.solve();
				double gr = greedy.getTotalCost();
				String pGr = greedy.getPermString();
				
				// christofides
				Christofides chris = new Christofides(g);
				chris.solve();
				double ch = chris.getTotalCost();
				String pCh = chris.getWayString();
				
				// save to lists
				names.add(testName);
				greedies.add(gr);
				christofs.add(ch);

				// write permutations
				FileWriter permOut = new FileWriter(new File("./out/" + testName + ".txt"));
				permOut.write("greedy,");
				permOut.write(pGr);
				permOut.write("\nchristofides,");
				permOut.write(pCh);
				permOut.close();
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
		in.nextLine();
		System.out.println(file.getName());
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
	
	
}