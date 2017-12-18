public class Day16 {
	
	public static void part2() {
		DancingPrograms programs = new DancingPrograms();
		programs.readDanceMovesFromFile("/Users/sertaysener/eclipse-workspace/Day16/src/input.txt");
		programs.dancePart2();
		System.out.println("Part 2 solution: " + programs.toString());
	}
	
	public static void part1() {
		DancingPrograms programs = new DancingPrograms();
		programs.readDanceMovesFromFile("/Users/sertaysener/eclipse-workspace/Day16/src/input.txt");
		programs.dance();
		System.out.println("Part 1 solution: " + programs.toString());
	}
	
	public static void main(String[] args) {
		part1();
		part2();
	}
}
