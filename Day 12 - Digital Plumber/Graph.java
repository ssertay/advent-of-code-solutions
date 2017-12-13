import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Graph {
	private HashMap<Integer, GraphNode> nodes;
	
	public Graph() {
		nodes = new HashMap<Integer, GraphNode>();
	}
	
	public HashMap<Integer, GraphNode> getNodes() {
		return nodes;
	}
	
	private void processLine(String line) {
		String[] lineArray = line.split(" <-> ");
		int nodeID = Integer.parseInt(lineArray[0]);
		int[] children = new int[lineArray[1].split(", ").length];
		for(int i=0; i<children.length ;i++) { children[i] = Integer.parseInt(lineArray[1].split(", ")[i]); }
		
		nodes.put(nodeID, new GraphNode(nodeID, children));
	}
	
	public void buildGraphFromFile(String fileName) {
		String line = null;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			
			while(( line = bufferedReader.readLine() ) != null) {
				processLine(line);
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {                
	        e.printStackTrace();
		}
		
	}
	
	public int getNumberOfConnectedTo(int nodeID) {
		int result = 0;
		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		GraphNode current = nodes.get(nodeID);
		current.visit();
		queue.add(current);
		
		while(!queue.isEmpty()) {
			current = queue.remove();
			current.visit();
			result++;
			
			for(int neigborID:current.getNeighbors()) {
				GraphNode neighbor = nodes.get(neigborID);
				if(!neighbor.isVisited()) {
					neighbor.visit();
					queue.add(neighbor);
				}
			}
		}
		
		return result;
	}
	
	private void fillTheGroup(int nodeID) {
		GraphNode node = nodes.get(nodeID);
		node.visit();
		
		for(int neighborID : node.getNeighbors()) {
			GraphNode neighbor = nodes.get(neighborID);
			if(!neighbor.isVisited()) {
				fillTheGroup(neighborID);
			}
		}
	}
	
	public int getNumberOfGroups() {
		int groups = 0;
		
		for (int nodeID : nodes.keySet()) {
		    GraphNode node = nodes.get(nodeID);
		    if(!node.isVisited()) {
		    		fillTheGroup(nodeID);
		    		groups++;
		    }
		}
		
		return groups;
	}
	
	public void printAdjacencyList() {
		Iterator it = nodes.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        GraphNode currentNode = (GraphNode) pair.getValue();
	        System.out.println(currentNode.getID() + " => " + Arrays.toString( currentNode.getNeighbors() ));
	        it.remove(); // careful this empties the map.
	    }
	}
	
}
