package parse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ParseLSSolutions {

	public static void main(String[] args) throws IOException {
		
		Scanner in = new Scanner(System.in);
		System.out.println("test name?");
		String testName = in.nextLine().trim();
		System.out.println("Now enter the solution vector:");
		int numTests = 100;
		
		int bestSol = Integer.MAX_VALUE;
		int sumAll = 0;
		int[] vet = new int[numTests];
		int current;
		for(int i = 0; i < numTests; i++) {
			current = in.nextInt();
			if (current < bestSol)
				bestSol = current;
			sumAll += current;
			vet[i] = current;
		}

		in.close();
		
		int avg = sumAll / numTests;

		int stdevNumerator = 0;
		for(int i = 0; i < numTests; i++) {
			stdevNumerator += Math.pow(vet[i] - avg, 2);
		}
		
		double stdev = Math.sqrt((double)stdevNumerator / numTests);
		
		FileWriter writer = new FileWriter(new File("LS_sol/"  + testName + ".txt"));
		writer.write("Best solution found: " + bestSol + "\n");
		writer.write("Average: " + avg + "\n");
		writer.write("Standard deviation: " + stdev + "\n");
		
		writer.close();
	}

}
