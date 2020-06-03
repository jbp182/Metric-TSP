package generate;

public class TestGenerator {

	public static final int RANDOM = 0;
	public static final int LINEAR = 1;
	public static final int SQUARE = 2;

	public static void main(String[] args) {

		int NUM_TESTS = 2;
		int MAX_SCALE = 50;
		String FILENAME = "tests/test";
		int[] numNodesArray =  //{50};
				{ 10, 50, 100, 500, 1000 };
		int NUM_NODES = 0;
		for (int i = 0; i < NUM_TESTS; i++) {
			for (int j = 0; j < numNodesArray.length; j++) {
				NUM_NODES = numNodesArray[j];
				String name = String.format("%s_%d_%d_%d.txt", FILENAME, MAX_SCALE, NUM_NODES, i);
				generateGraph(RANDOM, name, NUM_NODES, MAX_SCALE);
			}
		}
		System.out.println("done");

	}

	public static void generateGraph(int mode, String filename, int numNodes, int maxScale) {
		switch (mode) {
		case RANDOM:
			GraphGenerator.generateGraph(filename, numNodes, maxScale);

			break;
		case LINEAR:
			GraphGenerator.generateGraphLinear(filename, numNodes, maxScale);

			break;
		case SQUARE:
			GraphGenerator.generateGraphSquare(filename, numNodes, maxScale);

			break;
		default:
			System.out.println("This mode does not exist");
		}
	}

}
