package parse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ParseLSSolutions {

	public static void main(String[] args) throws IOException {
		
		String inDir = "../TP2-LocalSearch/out/";
		int numTests = 10;
		
		String dir = new File(inDir).getAbsolutePath();
		File folder = new File(dir);
		File[] files = folder.listFiles();
		
		for(File file : files) {
			if(file.isFile()) {
				
				String filename = file.getName();
				if (filename.split("\\.")[0].split("_")[5].equals("prev")
						&& filename.split("\\.")[0].split("_")[4].equals("tabu")) 
					continue;
				
				Scanner in = new Scanner(file);

				String testName = filename.split("\\.")[0];

				
				int maxj = 1;
				if (filename.split("\\.")[0].split("_")[5].equals("prev")) {
					maxj = 2;
				}

				FileWriter writer = new FileWriter(new File(inDir + "sol/" + testName + "_sol.txt"));
				for (int j = 0; j < maxj; j++) {
					
					int bestSol = Integer.MAX_VALUE;
					int sumAll = 0;
					int[] vet = new int[numTests];
					int current;
					
					if (maxj == 2)
						in.next();
					for(int i = 0; i < numTests; i++) {
						current = in.nextInt();
						if (current < bestSol)
							bestSol = current;
						sumAll += current;
						vet[i] = current;
					}
					
					int avg = sumAll / numTests;
	
					int stdevNumerator = 0;
					for(int i = 0; i < numTests; i++) {
						stdevNumerator += Math.pow(vet[i] - avg, 2);
					}
					
					double stdev = Math.sqrt((double)stdevNumerator / numTests);
					
					writer.write("Best solution found: " + bestSol + "\n");
					writer.write("Average: " + avg + "\n");
					writer.write("Standard deviation: " + stdev + "\n\n");
					
				}

				writer.close();
				in.close();
				
			}
		}
	}

}
