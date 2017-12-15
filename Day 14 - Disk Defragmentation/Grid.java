public class Grid {
	
	private final static int ROWS = 128;
	private final static int COLS = 128;
	private final static int USED = -1;
	private final static int UNUSED = 0;
	private String feed;
	private int[][] grid;
	private int usedCells;
	
	
	private void fillGrid() {
		KnotHasher hasher = new KnotHasher();
		
		/* This fills the row based on the value of the knot hash. */
		for(int row=0; row<ROWS ;row++) {
			String key = feed + "-" + Integer.toString(row);
			String hash = hasher.knotHash(key);
			
			for(int charIndex=0; charIndex<hash.length() ;charIndex++) {
				int characterValue = Character.digit(hash.charAt(charIndex), 16); // Integer representation of hex char.
				
				for(int k=charIndex*4, l=k+3; l >= k ;l--) {
					int mask = 1;
					int currentCellValue = UNUSED;
					if(((characterValue & mask) == 1)) {
						currentCellValue = USED;
						usedCells++;
					}
					grid[row][l] = currentCellValue;
					characterValue = characterValue >> 1;
				}
			}
		}
	}
	
	public Grid(String input) {
		grid = new int[ROWS][COLS];
		feed = input;
		usedCells = 0;
		
		fillGrid();
	}
	
	public int getUsed() { return usedCells; }
	
	public void printGrid() {
		for(int r=0; r<ROWS ;r++) {
			for(int c=0; c<COLS ;c++) {
				char currentCell = (grid[r][c] == USED) ? '@' : '.';
				System.out.print(currentCell);
			}
			System.out.println();
		}
	}
	
	private boolean isValidCell(int row, int col) {
		return row < ROWS && row > -1 && col < COLS && col > -1;
	}
	
	private void fillGroup(int groupNumber, int row, int col) {
		grid[row][col] = groupNumber;
		
		// down
		if(isValidCell(row-1,col)) {
			if(grid[row-1][col] == USED) {
				fillGroup(groupNumber, row-1, col);
			}
		}
		// up
		if(isValidCell(row+1,col)) {
			if(grid[row+1][col] == USED) {
				fillGroup(groupNumber, row+1, col);
			}
		}
		// left
		if(isValidCell(row,col-1)) {
			if(grid[row][col-1] == USED) {
				fillGroup(groupNumber, row, col-1);
			}
		}
		//down
		if(isValidCell(row,col+1)) {
			if(grid[row][col+1] == USED) {
				fillGroup(groupNumber, row, col+1);
			}
		}
	}
	
	/* Floodfill algorithm that operates in-place while updating the binary grid values with group numbers. */
	public int getGroups() {
		int groups = 0;
		
		for(int row=0; row<ROWS ;row++) {
			for(int col=0; col<COLS ;col++) {
				if(grid[row][col] == USED) {
					groups++;
					fillGroup(groups, row, col);
				}
			}
		}
		
		return groups;
	}
}
