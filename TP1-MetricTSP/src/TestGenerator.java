import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TestGenerator {

	public static final int MAX_DISTANCE = 1000;

	public static void main(String[] args) throws IOException {
		int numNodes = 2;
		int numTest = 5;
		String fileName;
		int num = 0;

		for (; num < numTest; num++) {
			double[][] mat = generateRandomCompleteGraph(numNodes);
			makeThisTriangleDesiguality(mat);
			fileName = String.format("%dNodesGraph%d.txt", numNodes, num);
			writeToFile(fileName, mat);
		}
	}

	public static void writeToFile(String fileName, double[][] mat) throws IOException {
		File file = new File(fileName);
		FileOutputStream f = new FileOutputStream(file);
		int numNodes = mat.length;
		System.out.println(numNodes);
		for (int i = 0; i < numNodes; i++) {
			for (int j = i + 1; j < numNodes; j++) {
				String line = String.format("%d %d %.1f\n", i, j, mat[i][j]);
				f.write(line.getBytes());
			}
		}
		f.flush();
		f.close();
	}

	public static void makeThisTriangleDesiguality(double[][] mat) {
		int numNodes = mat.length;

		for (int k = 0; k < numNodes; k++) {
			for (int i = 0; i < numNodes; i++) {
				for (int j = 0; j < numNodes; j++) {
					double cost = mat[i][k] + mat[k][j];
					if (mat[i][j] > cost) {
						mat[i][j] = cost;
					}
				}
			}
		}
	}

	public static double[][] generateRandomCompleteGraph(int numNodes) {
		Random random = new Random();
		double[][] mat = new double[numNodes][numNodes];
		int cost;
		for (int i = 0; i < numNodes; i++) {
			for (int j = i + 1; j < numNodes; j++) {
				cost = random.nextInt(MAX_DISTANCE);
				mat[i][j] = cost;
				mat[j][i] = cost;
			}
		}
		return mat;
	}

	public static void showMatriz(double[][] mat) {
		int numNodes = mat.length;
		for (int i = 0; i < numNodes; i++) {
			System.out.print(i + ":");
			for (int j = 0; j < numNodes; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println();
		}
	}
}
