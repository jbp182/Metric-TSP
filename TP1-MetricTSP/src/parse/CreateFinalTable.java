package parse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class CreateFinalTable {
	
	public static void main(String[] args) throws IOException {
		
		// gets tests names and tp1 results
		File results_tp1 = new File("results.csv");
		Scanner in = new Scanner(results_tp1);

		String line = in.nextLine();
		line = line.substring(line.indexOf(",") + 1);
		String[] tests = line.split(",");
		
		line = in.nextLine();
		line = line.substring(line.indexOf(",") + 1);
		String[] grCosts = line.split(",");

		line = in.nextLine();
		line = line.substring(line.indexOf(",") + 1);
		String[] chCosts = line.split(",");
		
		in.close();
		
		
		// for each test
		String tp2dir = "../TP2-LocalSearch/out/";
		FileWriter out = new FileWriter(new File( "Statistic_results.csv"));
		out.write(",greedy,christ,greedy+tabu,christ+tabu,greedy+sa,christ+sa,tabu,sa,\n");
		
	
		for (int i = 0; i < tests.length; i++) {
			String testname = tests[i];
			File tabuRand = new File(tp2dir + "sol/" + testname + "_tabu_rand_sol.txt");
			File tabuPrev = new File(tp2dir + testname + "_tabu_prev.txt");
			File saRand = new File(tp2dir + "sol/" + testname + "_sa_rand_sol.txt");
			File saPrev = new File(tp2dir + "sol/" + testname + "_sa_prev_sol.txt");
			
		//	Scanner intr = new Scanner(tabuRand);
		//	Scanner intp = new Scanner(tabuPrev);
			Scanner insr = new Scanner(saRand);
			Scanner insp = new Scanner(saPrev);
			
			List<Integer> bests = new LinkedList<Integer>();
			List<Double> means = new LinkedList<Double>();
			List<Double> stdev = new LinkedList<Double>();
			
			// greedy
			bests.add((int)Double.parseDouble(grCosts[i]));
			
			// christofides
			bests.add((int)Double.parseDouble(chCosts[i]));
			
			// greedy+tabu
		//	intp.next();
			//bests.add(intp.nextInt());
			
			// christ+tabu
		//	intp.next();
		//	bests.add(intp.nextInt());
			
			// greedy+sa
			insp.next(); insp.next(); insp.next(); 
			bests.add(insp.nextInt());
			insp.next();
			means.add(insp.nextDouble());
			insp.next(); insp.next();
			stdev.add(Double.parseDouble(insp.next()));
			
			// christ+sa
			insp.next(); insp.next(); insp.next(); 
			bests.add(insp.nextInt());
			insp.next();
			means.add(insp.nextDouble());
			insp.next(); insp.next();
			stdev.add(Double.parseDouble(insp.next()));

			System.out.println(testname);
			// tabu random seeds
		//	intr.next(); intr.next(); intr.next();
		//	bests.add(intr.nextInt());
		//	intr.next();
		//	means.add(intr.nextDouble());
		//	intr.next(); intr.next();
		//	stdev.add(Double.parseDouble(intr.next()));
			
			
			// sa random seeds
			insr.next(); insr.next(); insr.next();
			bests.add(insr.nextInt());
			insr.next();
			means.add(insr.nextDouble());
			insr.next(); insr.next();
			stdev.add(Double.parseDouble(insr.next()));
			
		//	intr.close();
		//	intp.close();
			insr.close();
			insp.close();
			
			
			out.write(testname);
			out.write("\n");
			
			// best
			out.write("best,");
			for(int b : bests)
				out.write(b + ",");
			out.write("\n");
			
			// mean
			out.write("mean,");
			for(int j = 0; j < 4; j++)
				out.write(",");
			for(double m : means)
				out.write(m + ",");
			out.write("\n");
			
			// stdev
			out.write("stdev,");
			for(int j = 0; j < 4; j++)
				out.write(",");
			for(double d : stdev)
				out.write(d + ",");
			out.write("\n");
			out.write("\n");
				
			
		}
		out.close();
		
		
		
		
		
		
		
	}

}
