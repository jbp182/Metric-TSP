import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import algorithms.Christofides;
import algorithms.GreedyTSP;
import entities.Graph;

public class Main {



	public static void main(String[] args) throws IOException {
		
		
		String currentDir =new File("").getAbsolutePath();
		currentDir += "/tests";
		String csvName = "result.csv";
				 
		
		String[] list = new File(currentDir).list();
		BufferedReader in;
		StringBuilder sb;
		PrintWriter writer = new PrintWriter(new File(csvName));
		writer.write("nameOfFile,greedyCost,christofidesCost\n");
		for (String text: list) {
			String[] names = text.split("N");
			int numNode = Integer.parseInt(names[0]);
			Graph g = new Graph(numNode);
			
			in = new BufferedReader(new InputStreamReader(new FileInputStream(currentDir+"/"+text)));
			int numEdges = numNode * (numNode - 1) / 2;
			getMatriz(in, numEdges, g);
			sb = new StringBuilder();
			sb.append(text);
			sb.append(",");
			sb.append(greedy(g));
			sb.append(",");
			sb.append(christofides(g));
			sb.append("\n");
			writer.write(sb.toString());
			
			in.close();
			
		}
		writer.close();
		System.out.println("Done!");
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

	private static double christofides(Graph g) {
		// TODO Auto-generated method stub
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
