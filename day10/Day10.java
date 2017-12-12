import java.util.*;

public class Day10 {
	
	public static void partiallyReverseList(int[] list, int begin, int length) {
		int tail = (begin+length-1) % list.length;
		for(int i=0; i<length/2 ;i++) {
			int dummy = list[begin];
			list[begin] = list[tail];
			list[tail] = dummy;
			tail = (tail-1) % list.length;
			if(tail < 0) tail += list.length; // note to self: -1 % 255 is -1, not 255.
			begin = (begin+1) % list.length;
		}
	}
	
	
	public static void sparseHashSingleRound(int[] numbers, int[] lengths, int[] positionAndSkipSize) {
		int currentPosition = positionAndSkipSize[0];
		int skipSize = positionAndSkipSize[1];
		
		for(int length : lengths) {
			partiallyReverseList(numbers, currentPosition, length);
			currentPosition = (currentPosition+skipSize+length) % numbers.length;
			skipSize++;
		}
		
		positionAndSkipSize[0] = currentPosition;
		positionAndSkipSize[1] = skipSize;
	}
	
	
	public static void sparseHash(int[] numbers, int[] byteArray) {
		int[] positionAndSkipSize = new int[] {0,0};
		
		for(int i=0; i<64 ;i++) {
			sparseHashSingleRound(numbers, byteArray, positionAndSkipSize);
		}
	}
	
	public static int[] denseHash(int[] sparseHash) {
		int[] denseHash = new int[16];
		
		for(int i=0; i<16 ;i++) {
			int block = sparseHash[i*16];
			for(int j=(i*16)+1; j<(i+1)*16 ;j++) {
				block = block ^ sparseHash[j];
			}
			denseHash[i] = block;
		}
		
		return denseHash;
	}
	
	public static String knotHash(int[] denseHash) {
		StringBuilder result = new StringBuilder();
		
		for(int block : denseHash) {
			result.append(String.format("%02X", block));
		}
		
		return result.toString().toLowerCase();
	}
	
	public static void solvePart1() {
		int[] lengths = {225,171,131,2,35,5,0,13,1,246,54,97,255,98,254,110};
		int[] numbers = new int[256];
		for(int i=0; i<256 ;i++) numbers[i] = i;
		
		sparseHashSingleRound(numbers, lengths, new int[] {0,0});
		System.out.println("Solution to Part 1 is: " + numbers[0] * numbers[1]);
	}
	
	public static void solvePart2() {
		int[] numbers = new int[256];
		for(int i=0; i<256 ;i++) numbers[i] = i;
		String part2Input = "225,171,131,2,35,5,0,13,1,246,54,97,255,98,254,110";
		int[] byteArray = new int[part2Input.length() + 5];
		for(int i=0; i<part2Input.length() ;i++) byteArray[i] = (int)part2Input.charAt(i);
		byteArray[part2Input.length()] = 17;
		byteArray[part2Input.length()+1] = 31;
		byteArray[part2Input.length()+2] = 73;
		byteArray[part2Input.length()+3] = 47;
		byteArray[part2Input.length()+4] = 23;
		
		sparseHash(numbers, byteArray);
		System.out.println(knotHash(denseHash(numbers)));
	}
	
	public static void main(String[] args) {
		solvePart2();
	}
}
