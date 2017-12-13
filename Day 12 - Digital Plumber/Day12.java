public class Day12 {
	
	public static void solvePart1(Graph graph) {
		System.out.println( graph.getNumberOfConnectedTo(0) );
	}
	
	public static void solvePart2(Graph graph) {
		System.out.println( graph.getNumberOfGroups() );
	}
	
	public static void main(String[] args) {
		String fileName = "/Users/sertaysener/eclipse-workspace/Day12/src/input.txt";
		Graph graph = new Graph();
		graph.buildGraphFromFile(fileName);
		
		//solvePart1(graph);
		solvePart2(graph);
	}
}
