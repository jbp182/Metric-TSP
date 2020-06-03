package generate;

public class TestGenerator {


	public static final int RANDOM = 0;
	public static final int LINEAR = 1;
	public static final int SQUARE = 2;
	
	
	public static void main(String[] args) {
		
		int NUM_TESTS = 2;
		int NUM_NODES = 50;
		int MAX_SCALE = 100;
		String FILENAME = "tests/test";
		
		
		for (int i = 2; i < 4; i++) {
			String name = String.format("%s_%d_%d_%d.txt", FILENAME, MAX_SCALE, NUM_NODES, i);
			generateGraph(SQUARE, name, NUM_NODES, MAX_SCALE);
		}
		System.out.println("done");
		
	}
	
	
	public static void generateGraph(int mode,String filename, int numNodes, int maxScale) {
		switch(mode) {
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
