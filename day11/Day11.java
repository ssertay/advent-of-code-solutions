import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Day11 {
	
	// TODO find the distance.
	public static int[] getDistances(String[] directions) {
		int x = 0;
		int y = 0;
		int z = 0;
		int distance = 0;
		int maxDistance = Integer.MIN_VALUE;
		
		for(String d : directions) {
			switch(d) {
			case "n":
				y++;
				z--;
				break;
			case "s":
				y--;
				z++;
				break;
			case "ne":
				z--;
				x++;
				break;
			case "sw":
				z++;
				x--;
				break;
			case "nw":
				y++;
				x--;
				break;
			case "se":
				y--;
				x++;
				break;
			}
			distance = (Math.abs(x) + Math.abs(y) + Math.abs(z)) / 2;
			if(distance > maxDistance) maxDistance = distance;
			
		}
		
		return new int[] {distance, maxDistance};
	}
	
	public static String[] getDirections() {
		String fileName = "/Users/sertaysener/eclipse-workspace/Day11/src/input.txt";
		String line = null;
		String[] directions = null;
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
			
			line = bufferedReader.readLine();
			directions = line.split(",");
			
			
			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {                
            e.printStackTrace();
		}
		
		return directions;
	}
	
	public static void solve() {
		String[] directions = getDirections();
		System.out.println("Latest distance is: " + getDistances(directions)[0]);
		System.out.println("Maximum distance is: " + getDistances(directions)[1]);
	}
	
	public static void main(String[] args) {
		solve();
	}
}
