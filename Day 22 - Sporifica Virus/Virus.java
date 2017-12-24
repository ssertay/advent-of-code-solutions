import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class Virus {
	private String fileName = "/Users/sertaysener/eclipse-workspace/Day22/src/input.txt";
	private HashMap<String, Integer> visited;
	private int[] position;
	private int[][] directions = { {1,0},{0,1},{-1,0},{0,-1} }; // down, right, up, left
	private int d;
	private int burstsCausedInfection;
	// cell states: 0: clean, 1: weakened, 2: infected, 3: flagged
	
	
	public Virus() {
		visited = new HashMap<String, Integer>();
		position = new int[2];
		d = 2;
		burstsCausedInfection = 0;
		loadMapFromFile();
	}
	
	public void printInfectedBursts() {
		System.out.println(burstsCausedInfection+" bursts caused infection.");
	}
	
	private int currentNodeState() {
		if(!visited.containsKey(Arrays.toString(position)))
			visited.put(Arrays.toString(position), 0);
		return visited.get(Arrays.toString(position));
	}
	
	private void burst() {
		
		switch(currentNodeState()) {
		case 0: // clean
			d = (d+1) % 4; // turn left
			break;
		case 1: // weakened
			burstsCausedInfection++;
			break;
		case 2: // infected
			d = (d+3) % 4; // turn right
			break;
		case 3: // flagged
			d = (d+2) % 4; // reverse direction
			break;
		default:
			break;
		}
		
		visited.put(Arrays.toString(position), (visited.get(Arrays.toString(position)) + 1) % 4);
		
		position[0] += directions[d][0];
		position[1] += directions[d][1];
	}
	
	public void spread(int ticks) {
		for(int t=0; t<ticks ;t++) {
			burst();
		}
	}
	
	private void loadMapFromFile() {
		String line = null;
		
		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
			int row=0; 
			int lineLength = Integer.MIN_VALUE;
			while((line = bufReader.readLine()) != null) {
				if(lineLength == Integer.MIN_VALUE) lineLength = line.length();
				
				for(int col=0; col<line.length() ;col++) {
					char current = line.charAt(col);
					if(current == '#') {
						int[] infectedPos = new int[] {row, col};
						visited.put(Arrays.toString(infectedPos), 2);
					}
				}
				row++;
			}
			
			position[0] = row/2;
			position[1] = lineLength/2;
			
			System.out.println("Infected cells loaded from file..");
			System.out.println("Virus starts at: "+ position[0] + " " + position[1]);
			bufReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {                
	        e.printStackTrace();
		}
	}
}
