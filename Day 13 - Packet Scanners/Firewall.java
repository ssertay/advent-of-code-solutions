import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Firewall {
	
	private ArrayList<Integer> layers;
	
	public Firewall() {
		layers = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getLayers() {
		return layers;
	}
	
	public int calculateDelayBruteForce() {
		int delay = 0;
		
		while(!runThroughWithDelay(delay)) {
			delay++;
		}
		
		return delay;
	}
	
	public boolean runThroughWithDelay(int delay) {
		int position = 0;
		
		for(int picoseconds=delay ; position<layers.size(); picoseconds++) {
			int interval = (layers.get(position)-1) *2;
			if(layers.get(position) != 0 && (picoseconds % interval == 0)) {
				return false;
			}
			position++;
		}
		
		return true;
	}
	
	public int calculateSeverity() {
		int severity = 0;
		
		for(int picoseconds=0 ; picoseconds<layers.size(); picoseconds++) {
			int interval = (layers.get(picoseconds)-1) *2;
			if(layers.get(picoseconds) != 0 && (picoseconds % interval == 0)) {
				System.out.println("Caught at layer: "+picoseconds);
				severity += picoseconds * layers.get(picoseconds);
			}
		}
		
		return severity;
	}
	
	public void printLayers() {
		for(int i=0; i<layers.size() ;i++) System.out.println(i+": "+layers.get(i)); 
	}
	
	public void initializeFirewallWithFile(String fileName) {
		String line = null;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			int index = 0;
			
			while(( line = bufferedReader.readLine() ) != null) {
				String[] lineArray = line.split(": ");
				int layer = Integer.parseInt(lineArray[0]);
				int range = Integer.parseInt(lineArray[1]);
				
				while(index < layer) {
					layers.add(0);
					index++;
				}
				layers.add(range);
				index++;
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
	
	
}
