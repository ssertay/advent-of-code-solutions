public class Day14 {
	
	public static void part1(Grid grid) {
		System.out.println(grid.getUsed());
	}
	
	public static void part2(Grid grid) {
		System.out.println(grid.getGroups());
	}
	
	public static void main(String[] args) {
		String puzzleInput = "oundnydw";
		Grid grid = new Grid(puzzleInput);
		
		part1(grid);
		part2(grid);
	}
}
