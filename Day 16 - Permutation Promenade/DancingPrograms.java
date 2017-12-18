import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class DancingPrograms {
	private final static int SIZE = 16;
	private char[] programs;
	private int currentHead;
	private ArrayList<String> moves;
	
	public DancingPrograms() {
		moves = new ArrayList<String>();
		programs = new char[SIZE];
		resetPrograms();
		currentHead = 0;
	}
	
	private void resetPrograms() {
		for(int i=0; i<SIZE ;i++)
			programs[i] = (char)((int)'a' + i);
		currentHead = 0;
	}
	
	private void spin(int amount) {
		if(amount > 16)
			throw new IllegalArgumentException("Can't spin more than 16 dancers.");
		currentHead = (((currentHead - amount) % SIZE) + SIZE) % 16;
	}
	
	private void exchange(int pos1, int pos2) {
		if(pos1 < 0 || pos2 < 0 || pos1 > 16 || pos2 > 16)
			throw new IllegalArgumentException("No program at "+pos1+" and "+pos2);
		
		pos1 = (pos1 + currentHead) % SIZE;
		pos2 = (pos2 + currentHead) % SIZE;
		
		char dummy = programs[pos1];
		programs[pos1] = programs[pos2];
		programs[pos2] = dummy;
	}
	
	private int getCharPosition(char c) {
		for(int i=0; i<SIZE ;i++)
			if(c == programs[i]) return i;
		return -1;
	}
	
	private void partner(char first, char second) {
		int firstIndex  = getCharPosition(first);
		int secondIndex = getCharPosition(second);
		
		if(firstIndex == -1 || secondIndex == -1)
			throw new IllegalArgumentException();
		
		programs[firstIndex] = second;
		programs[secondIndex] = first;
	}
	
	private void danceMove(String danceMove) {
		switch(danceMove.charAt(0)) {
		case 's':
			spin(Integer.parseInt(danceMove.substring(1, danceMove.length())));
			break;
		case 'x':
			String[] positions = danceMove.substring(1, danceMove.length()).split("/");
			exchange(Integer.parseInt(positions[0]), Integer.parseInt(positions[1]));
			break;
		case 'p':
			partner(danceMove.charAt(1), danceMove.charAt(3));
			break;
		default:
			break;
		}
	}
	
	public int cyclesAt() {
		dance();
		for(int i=1; i<1000000000 ;i++) {
			if(toString().equals("abcdefghijklmnop")) {
				return i;
			}
			dance();
		}
		
		return -1;
	}
	
	public void dancePart2() {
		if(moves.isEmpty())
			throw new IllegalStateException("No dance moves to execute!");
		
		int loopCount = 1000000000 % cyclesAt();
		resetPrograms();
		for(int i=0; i<loopCount ;i++) {
			dance();
		}
	} 
	
	public void dance() {
		if(moves.isEmpty())
			throw new IllegalStateException("No dance moves to execute!!");
		for(String move : moves)
			danceMove(move);
	}
	
	public void readDanceMovesFromFile(String fileName) {
		try {
			Scanner scan = new Scanner(new File(fileName));
			scan.useDelimiter(",");
			while(scan.hasNext()) {
				moves.add(scan.next());
			}
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {                
	        e.printStackTrace();
		}
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for(int i=currentHead, j=0; j<SIZE ;i = (i+1) % SIZE, j++)
			builder.append(programs[i]);
		return builder.toString();
	}
	
	public void testRun() {
		System.out.println(toString());
		System.out.println();
		
		danceMove("s1");
		danceMove("x3/4");
		danceMove("pe/b");
		
		System.out.println(toString());
	}
}
