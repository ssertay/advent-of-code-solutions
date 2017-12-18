public class Day17 {
	
	public static void solvePart2(int steps) {
		CircularLinkedList list = new CircularLinkedList();
		list.addToCurrent(0);
		
		int size = 1;
		int nextToZero = 0;
		int position = 0;
		for(int value=1; value<=50000000 ;value++) {
			position = (position + steps) % size;
			if(position == 0) { nextToZero = value; }
			position++;
			size++;
		}
		
		System.out.println("The solution for part 2 is: "+Integer.toString(nextToZero));
	}
	
	public static void solvePart1(int steps, int insertions) {
		CircularLinkedList list = new CircularLinkedList();
		list.addToCurrent(0);
		
		for(int value=1; value<insertions+1 ;value++) {
			list.takeSteps(steps);
			list.addToCurrent(value);
		}
		
		System.out.println("The solution for part 1 is: "+Integer.toString(list.getCurrent().next.getData()));
	}
	
	public static void main(String[] args) {
		int steps = 316;
		int sampleSteps = 3;
		int insertions = 2017;
		int sampleInsertions = 10;
		
		
		solvePart1(steps, insertions);
		solvePart2(steps);
	}
}
