public class GraphNode {
	private int id;
	private int[] neighbors;
	private boolean visited;
	
	public GraphNode(int id, int[] newNeighbors) {
		this.id = id;
		visited = false;
		neighbors = new int[newNeighbors.length];
		for(int i=0; i<newNeighbors.length; i++) { neighbors[i] = newNeighbors[i]; }
	}
	
	public void visit() {
		visited = true;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public int getID() { 
		return id; 
	}
	
	public int[] getNeighbors() {
		return neighbors;
	}
}
