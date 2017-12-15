public class Day15 {

	public static long generateNumber(char generator, long prev, int part) {
		long factor = (generator == 'A') ? 16807 : 48271;
		long modulo = (generator == 'A') ? 4 : 8;

		prev = (prev * factor) % 2147483647;
		if(part == 2)
			while((prev % modulo) != 0)
				prev =  (prev * factor) % 2147483647;

		return prev;
	}

	public static int solve(int part, long A, long B, long mask) {
		int matched = 0;
		int loopRange = (part == 1) ? 40000000 : 5000000;

		for(int i=0; i<loopRange ;i++) {
			A =  generateNumber('A', A, part);
			B =  generateNumber('B', B, part);

			if((A % mask) == (B % mask)) {
				matched++;
			}
		}

		return matched;
	}

	public static void main(String[] args) {
		long A = 722;
		long B = 354;
		long mask = 1 << 16;

		System.out.println(solve(1,A, B, mask));
		System.out.println(solve(2,A, B, mask));
	}
}
