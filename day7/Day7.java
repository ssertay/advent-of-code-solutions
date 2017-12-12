import java.io.*;
import java.util.*;

public class Day7 {
	
	public static void printMap(HashMap<String, TreeNode> map) {
		
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry)it.next();
			TreeNode node = (TreeNode) pair.getValue();
			node.printNodeInformation();
			it.remove();
		}
		
	}
	
	public static void processLine(String line, HashMap<String, TreeNode> map) {
		String[] lineArray = line.split(" ");
		
		// Process the first program.
		String firstProgramName = lineArray[0];
		int firstProgramWeight = Integer.parseInt(lineArray[1].substring(1, lineArray[1].length() - 1));
		
		if(!map.containsKey(firstProgramName)) {
			TreeNode newNode = new TreeNode(firstProgramName, firstProgramWeight);
			map.put(firstProgramName, newNode);
		}
		
		// If exists without a weight value, update the weight.
		else if(map.containsKey(firstProgramName)) {
			TreeNode node = (TreeNode) map.get(firstProgramName);
			node.setWeight(firstProgramWeight);
			map.put(firstProgramName, node);
		}
		
		// Process the children on the rhs of the line.
		if(lineArray.length > 3 ) {
			for(int i=3; i < lineArray.length ;i++) {
				String programName = lineArray[i];
				
				// Trim the last comma from the program name. 
				if(programName.charAt(programName.length() - 1) == ',') {
					programName = programName.substring(0, programName.length() - 1);
				}
				
				if(!map.containsKey(programName)) {
					TreeNode newNode = new TreeNode(programName);
					map.put(programName, newNode);
				}
				
				// TODO add to the children of the first program.
				TreeNode parent = map.get(firstProgramName);
				TreeNode child = map.get(programName);
				parent.addChild(child);
				map.put(firstProgramName, parent);
			}
		}
		
	}
	
	// This goes through the list to create a set of all nodes.
	public static HashMap<String, TreeNode> buildNodeSet() {
		
		HashMap<String,TreeNode> programMap = new HashMap<String, TreeNode>();
		String fileName = "/Users/sertaysener/eclipse-workspace/Day7/src/input.txt";
		String line = null;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			
			while(((line = bufferedReader.readLine()) != null)) {
				processLine(line, programMap);
			}
			
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {                
	            e.printStackTrace();
		}
		
		return programMap;
	}
	
	public static void main(String[] args) {
		HashMap<String, TreeNode> map = buildNodeSet();
		
		TreeNode root = map.get("gynfwly");
		root.printWholeTree(" ", false);
	}
}
