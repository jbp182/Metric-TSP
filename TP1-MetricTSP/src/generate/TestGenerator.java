package generate;

public class TestGenerator {


	public static void main(String[] args) {
		
		int NUM_TESTS = 2;
		int NUM_NODES = 100;
		int MAX_SCALE = 100;
		String FILENAME = "tests/test";
		
		
		for (int i = 0; i < NUM_TESTS; i++) {
			String name = String.format("%s_%d_%d_%d.txt", FILENAME, MAX_SCALE, NUM_NODES, i);
			GraphGenerator.generateGraph(name, NUM_NODES, MAX_SCALE);
		}
		
	}

}
