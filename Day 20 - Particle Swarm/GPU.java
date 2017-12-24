import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class GPU {
	private String fileName = "/Users/sertaysener/eclipse-workspace/Day20/src/input.txt";
	private ArrayList<Particle> particles;
	
	public GPU() {
		particles = new ArrayList<Particle>();
		loadParticlesFromFile();
	}
	
	public void removeCollided() {
		HashMap<String, ArrayList<Particle>> positions = new HashMap<String, ArrayList<Particle>>();
		
		for(Particle p : particles) {
			ArrayList<Particle> list;
			if(!positions.containsKey(p.positionString())) {
				list = new ArrayList<Particle>();
			} else {
				list = positions.get(p.positionString());
			}
			
			list.add(p);
			positions.put(p.positionString(), list);
		}
		
		// iterate over map, remove all particles if there is more than 1 underneath a key.
		for(String key : positions.keySet()) {
			ArrayList<Particle> list = positions.get(key);
			if(list.size() > 1) {
				for(Particle p : list) {
					particles.remove(p);
				}
			}
		}
	}
	
	public void simulate(int ticks) {
		System.out.println("Starting simulation..");
		for(int t=0; t<ticks ;t++) {
			for(Particle p : particles) {
				p.update();
			}
			removeCollided();
		}
		System.out.println("Simulation ended.");
	}
	
	public void printParticlesLeft() {
		System.out.println("Particles left: "+particles.size());
	}
	
	public void printParticleWithClosestDistance() {
		long minDistance = Long.MAX_VALUE;
		int winnerID = 0;
		for(Particle p : particles) {
			if(p.getManhattanDistance() < minDistance) {
				minDistance = p.getManhattanDistance();
				winnerID = p.id;
			}
		}
		
		System.out.println("And the winner is: "+winnerID);
	}
	
	private void loadParticlesFromFile() {
		System.out.println("Loading particles from file...");
		String line = null;
		
		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
			int newParticleID = 0;
			while((line = bufReader.readLine()) != null) {
				String[] lineArray = line.split("=");
				String[] positionArray = lineArray[1].substring(1, lineArray[1].length()-4).split(",");
				String[] velocityArray = lineArray[2].substring(1, lineArray[2].length()-4).split(",");
				String[] accelerationArray = lineArray[3].substring(1, lineArray[3].length()-1).split(",");
				
				Particle newParticle = new Particle(newParticleID, 
						Long.parseLong(positionArray[0]),
						Long.parseLong(positionArray[1]),
						Long.parseLong(positionArray[2]),
						Long.parseLong(velocityArray[0]),
						Long.parseLong(velocityArray[1]),
						Long.parseLong(velocityArray[2]),
						Long.parseLong(accelerationArray[0]),
						Long.parseLong(accelerationArray[1]),
						Long.parseLong(accelerationArray[2]));
				
				particles.add(newParticle);
				
				newParticleID++;
			}
			System.out.println("Particles loaded. There are "+particles.size()+" particles.");
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
