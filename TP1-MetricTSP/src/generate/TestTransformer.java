package generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import entities.Graph;

public class TestTransformer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated constructor stub
		
		String dir = new File("").getAbsolutePath() + "/manuais/";
		File folder = new File(dir);
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isFile()) {
				
				String filename = file.getName();
				String[] params = filename.split("_");
				int numNodes = Integer.parseInt(params[2]);
				
				Graph g = createGraph(file, numNodes);
			
				// write permutations
				FileWriter permOut = new FileWriter(new File("./tests/" + filename));

				permOut.write(numNodes + " " + numNodes+'\n');
				for (int i = 0; i < numNodes; i++) {
					for ( double cost: g.incidentEdges(i)) {
						permOut.write((int)cost + " ");
					}
					permOut.write("\n");
				}
				
				permOut.close();
			}			
		}
		
	}
	
	private static Graph createGraph(File file, int numNodes) throws FileNotFoundException {
		Graph g = new Graph(numNodes);
		Scanner in = new Scanner(file);
		for (int i = 0; i < 2*(numNodes-1); i++) {
			int row = in.nextInt();
			int col = in.nextInt();
			int cost = in.nextInt();
			g.addEdge(row, col, cost);
			
		}
		in.close();
		return g;		
	}

}
