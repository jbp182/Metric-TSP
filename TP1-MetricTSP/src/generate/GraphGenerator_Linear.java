package generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphGenerator_Linear {

	public static void generateGraph(String filename, int numNodes, int maxScale) {
		
		Point[] points = new Point[numNodes];

		int y0 = (int)(Math.random() * maxScale);
		for (int i = 0; i < numNodes; i++) {
			int x = (int)(Math.random() * maxScale);
			points[i] = new Point(x,y0);
		}
		
		int[][] dist = new int[numNodes][numNodes];
		
		for(int i = 0; i < numNodes - 1; i++) {
			for (int j = i+1; j < numNodes; j++) {
				int d = (int)points[i].getDistanceTo(points[j]);
				dist[i][j] = d;
				dist[j][i] = d;
			}
		}
		
		try {
			FileWriter writer = new FileWriter(new File(filename));
			
			for (int row = 0; row < numNodes; row++) {
				for (int col = 0; col < numNodes; col++) {
					writer.write(dist[row][col] + " ");
				}
				writer.write("\n");
			}
			
			writer.close();
		} catch (IOException e) {
			System.err.println("Error while creating file " + filename + ".");
		}
		
	}
	
	
	
}
