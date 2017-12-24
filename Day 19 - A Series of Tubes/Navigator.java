import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

enum Directions{ UP, DOWN, LEFT, RIGHT };

public class Navigator {
		private char[][] map;
		private String fileName = "/Users/sertaysener/eclipse-workspace/Day19/src/input.txt";
		private int mapHeight;
		private int mapWidth;
		private int currentCol;
		private int currentRow;
		private Directions facing = Directions.DOWN;
		private StringBuilder letters;
		private int stepsTaken;
		
		public Navigator() {
			currentRow = 0;
			currentCol = -1;
			mapHeight = getMapHeight();
			mapWidth = -1;
			stepsTaken = 0;
			letters = new StringBuilder();
			loadMapFromFile();
			System.out.println("Navigator ready.");
		}
		
		public void printExpeditionResult() { 
			System.out.println("Letters collected: "+letters.toString());
			System.out.println("Steps taken: "+stepsTaken);
		}
		
		private boolean validCell(int row, int col) {
			return row > 0 && row < mapHeight && col > 0 && col < mapWidth;
		}
		
		private boolean atCrossover() {
			return map[currentRow][currentCol] == '+';
		}
		
		private boolean ended() {
			return map[currentRow][currentCol] == ' ';
		}
		
		// TODO - this is repetitive clean it up.
		private void changeDirection() {
			switch(facing) {
			case UP:
				if(validCell(currentRow, currentCol-1)) // check left.
					if(map[currentRow][currentCol-1] == '-' || Character.isLetter(map[currentRow][currentCol-1]))
						facing = Directions.LEFT;
				if(validCell(currentRow, currentCol+1)) // check right.
					if(map[currentRow][currentCol+1] == '-' || Character.isLetter(map[currentRow][currentCol+1]))
						facing = Directions.RIGHT;
				break;
			case DOWN:
				if(validCell(currentRow, currentCol-1)) // check left.
					if(map[currentRow][currentCol-1] == '-' || Character.isLetter(map[currentRow][currentCol-1]))
						facing = Directions.LEFT;
				if(validCell(currentRow, currentCol+1)) // check right.
					if(map[currentRow][currentCol+1] == '-' || Character.isLetter(map[currentRow][currentCol+1]))
						facing = Directions.RIGHT;
				break;
			case LEFT:
				if(validCell(currentRow-1, currentCol)) // check up.
					if(map[currentRow-1][currentCol] == '|' || Character.isLetter(map[currentRow-1][currentCol]))
						facing = Directions.UP;
				if(validCell(currentRow+1, currentCol)) // check down.
					if(map[currentRow+1][currentCol] == '|' || Character.isLetter(map[currentRow+1][currentCol]))
						facing = Directions.DOWN;
				break;
			case RIGHT:
				if(validCell(currentRow-1, currentCol)) // check up.
					if(map[currentRow-1][currentCol] == '|' || Character.isLetter(map[currentRow-1][currentCol]))
						facing = Directions.UP;
				if(validCell(currentRow+1, currentCol)) // check down.
					if(map[currentRow+1][currentCol] == '|' || Character.isLetter(map[currentRow+1][currentCol]))
						facing = Directions.DOWN;
				break;
			default:
				break;
			}
		}
		
		private void markCell() {
			char current = map[currentRow][currentCol];
			
			if(current == ' ')
				throw new IllegalStateException("Why am i on an empty cell?!");
			
			if(atCrossover()) 
				changeDirection();
			
			if(Character.isLetter(current))
				letters.append(current);
				
			map[currentRow][currentCol] = '.'; // this marks the cell as "stepped on".
			stepsTaken++;
		}
		
		// Returns false if can't take a next step.
		private boolean takeAStep() {
			System.out.println("currently at : "+currentRow+","+currentCol+" facing "+facing);
			
			int rowStep = 0;
			int colStep = 0;
			
			switch(facing) {
			case UP:
				rowStep--;
				break;
			case DOWN:
				rowStep++;
				break;
			case LEFT:
				colStep--;
				break;
			case RIGHT:
				colStep++;
				break;
			default:
				break;
			}
			
			currentRow += rowStep;
			currentCol += colStep;
			
			if(!validCell(currentRow, currentCol)) return false; // check if we are off the map.
			if(ended()) return false;
			
			return true;
		}
		
		public void searchLetters() {
			System.out.println("Starting the expedition...");
			
			do {
				markCell();
			} while(takeAStep());
			
			System.out.println("Expedition complete !");
		}
		
		public void printMap() {
			for(int row=0; row<mapHeight ;row++) {
				for(int col=0; col<mapWidth; col++) {
					char current = (row == currentRow && col == currentCol) ? '@' : map[row][col];
					System.out.print(current);
				}
				System.out.println();
			}
		}
		
		public int getMapHeight() {
			String line = null;
			int height = 0;
			
			try {
				BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
				int i=0;
				while((line = bufReader.readLine()) != null)
					height++;
				bufReader.close();
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {                
		        e.printStackTrace();
			}
			
			return height;
		}
		
		private void loadMapFromFile() {
			System.out.println("Loading map from file...");
			String line = null;
			
			try {
				BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
				int i=0;
				while((line = bufReader.readLine()) != null) {
					if(mapWidth == -1) {
						mapWidth = line.length();
						map = new char[mapHeight][mapWidth];
					}
					for(int j=0; j<mapWidth ;j++) {
						if(line.charAt(j) == '|' && currentCol == -1) {
							currentCol = j;
						}
						map[i][j] = line.charAt(j);
					}
					i++;
				}
				System.out.println("Map loaded.");
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
