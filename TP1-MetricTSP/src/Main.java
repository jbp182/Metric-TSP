import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
		
		final int NUM_REPETITION = 10; 
		
		
		for (File file : files) {
			if (file.isFile()) {
				
				String filename = file.getName();
				String testName = filename.split("\\.")[0];
				String[] params = filename.split("_");
				int numNodes = Integer.parseInt(params[2]);
				
				// solve problem from file
				Graph g = createGraph(file, numNodes);
				
					
				double bestCost_greedy = Integer.MAX_VALUE;
				String best_pGr = null;
				double average_Gr = 0;
				double sv_Gr = 0;
				
				
				double bestCost_ch = Integer.MAX_VALUE;
				String best_pCh = null;
				double average_Ch = 0;
				double sv_Ch = 0;
				List<Double> costs_Gr = new ArrayList<Double>(NUM_REPETITION);
				List<Double> costs_Ch = new ArrayList<Double>(NUM_REPETITION);
				for (int i = 0; i < NUM_REPETITION; i++) {
					GreedyTSP greedy = new GreedyTSP(g);
					Christofides chris = new Christofides(g);
					
				
					//greedy
					greedy.solve();
					double gr = greedy.getTotalCost();
					average_Gr += gr;
					costs_Gr.add(gr);
						if(gr < bestCost_greedy) {
							bestCost_greedy = gr;
							best_pGr = greedy.getPermString();
						}
					// christofides
					chris.solve();
					double ch = chris.getTotalCost();
					if(ch < bestCost_ch) {
						bestCost_ch = ch;
						best_pCh= chris.getWayString();
					}
					average_Ch += ch;
					costs_Ch.add(ch);

				}
				average_Ch /= NUM_REPETITION;
				average_Gr /= NUM_REPETITION;
				sv_Gr = standardDesviation(costs_Gr, average_Gr);
				sv_Ch = standardDesviation(costs_Ch, average_Ch);
				
				
				
				// save to lists
				FileWriter permOut = new FileWriter(new File("./out/" + testName + ".txt"));
				writeContent(permOut, "greedy", bestCost_greedy, best_pGr, average_Gr, sv_Gr);
				writeContent(permOut, "christofides", bestCost_ch, best_pCh, average_Ch, sv_Ch);

				// write permutations
				permOut.close();

				
				names.add(testName);
				greedies.add(bestCost_greedy);
				christofs.add(bestCost_ch);



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
	
	private static double standardDesviation(List<Double> array, double average) {
		double sum = 0;
		int divisor = array.size() -1;
		double clause = 0;
		for (Double x : array) {
			clause = x - average;
			sum += clause*clause;
		}
		return Math.sqrt(sum/divisor);
	}
	
	private static void writeContent(FileWriter permOut,String algorithm,double bestCost,String bestRoute,double average,double sv) throws IOException {
		permOut.write(algorithm+"\n");
		permOut.write("Custo minimo: "+ bestCost+"\n");
		permOut.write("Media: " + average+"\n");
		permOut.write("Desvio padrao: " + sv+"\n");
		permOut.write(bestRoute +"\n");

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
	
	
}
