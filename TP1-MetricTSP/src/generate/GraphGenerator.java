package generate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphGenerator {

	public static void generateGraph(String filename, int numNodes, int maxScale) {
		
		Point[] points = new Point[numNodes];
		
		for (int i = 0; i < numNodes; i++) {
			int x = (int)(Math.random() * maxScale);
			int y = (int)(Math.random() * maxScale);
			points[i] = new Point(x,y);
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
			writer.write(numNodes + " " + numNodes + "\n");
			
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
	
	public static void generateGraphLinear(String filename, int numNodes, int maxScale) {
		
		Point[] points = new Point[numNodes];

		int x = (int)(Math.random() * maxScale);

		int y = (int)(Math.random() * maxScale);
		for (int i = 0; i < numNodes; i++) {
			y +=  (int)(Math.random() * maxScale);
			points[i] = new Point(x,y);
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
			writer.write(numNodes + " " + numNodes + "\n");
			
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
	
	public static void generateGraphSquare(String filename, int numNodes, int maxScale) {
		
		
		Point[] points = new Point[numNodes];

		int numEdges = 4;
		int numPointInEdge = numNodes / numEdges;
		
		int k = 0;
		// pin x0
		//vertical edge 1
		int x0 = 0;
		int x1 = (int)(Math.random() * maxScale);
		int y = 0;
		
		
		for (; k < numPointInEdge; k++) {
			y +=  (int)(Math.random() * maxScale);
			points[k] = new Point(x0,y);
			points[k+2*numPointInEdge] = new Point(x1,y);
			
		}
		
		//pin y0
		//honrizontal edge 1
		
		int interval = (x1-x0)/numPointInEdge;
		int x = 0;
		for (k = numPointInEdge; k < numPointInEdge*2; k++) {
			x += interval; 
			points[k] = new Point(x,0);
		}
		
		for(x = 0,k = 3*numPointInEdge; k < numNodes;k++) {
			x+=interval;
			points[k] = new Point(x,y);
			
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
			writer.write(numNodes + " " + numNodes + "\n");
			
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
