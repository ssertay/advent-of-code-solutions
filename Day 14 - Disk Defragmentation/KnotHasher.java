public class KnotHasher {
	
	private int[] numbers;
	
	private static void partiallyReverseList(int[] list, int begin, int length) {
		int tail = (begin+length-1) % list.length;
		for(int i=0; i<length/2 ;i++) {
			int dummy = list[begin];
			list[begin] = list[tail];
			list[tail] = dummy;
			tail = (tail-1) % list.length;
			if(tail < 0) tail += list.length;
			begin = (begin+1) % list.length;
		}
	}
	
	
	private static void sparseHashSingleRound(int[] numbers, int[] lengths, int[] positionAndSkipSize) {
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
	
	
	private static void sparseHash(int[] numbers, int[] byteArray) {
		int[] positionAndSkipSize = new int[] {0,0};
		
		for(int i=0; i<64 ;i++) {
			sparseHashSingleRound(numbers, byteArray, positionAndSkipSize);
		}
	}
	
	private int[] denseHash(int[] sparseHash) {
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
	
	private int[] generateByteArray(String key) {
		int[] byteArray = new int[key.length() + 5];
		for(int j=0; j<key.length() ;j++) byteArray[j] = (int)key.charAt(j);
		byteArray[key.length()] = 17;
		byteArray[key.length()+1] = 31;
		byteArray[key.length()+2] = 73;
		byteArray[key.length()+3] = 47;
		byteArray[key.length()+4] = 23;
		
		return byteArray;
	}
	
	private void resetNumbers() {
		for(int i=0; i<256 ;i++) numbers[i] = i;
	}
	
	public KnotHasher() {
		numbers = new int[256];
		resetNumbers();
	}
	
	public String knotHash(String input) {		
		resetNumbers();
		sparseHash(numbers, generateByteArray(input));
		int[] denseHash = denseHash(numbers);
		
		StringBuilder result = new StringBuilder();
		
		for(int block : denseHash) {
			result.append(String.format("%02X", block));
		}
		
		return result.toString().toLowerCase();
	}
}
