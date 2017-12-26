public class Day25 {
	public static void main(String[] args) {
		TuringMachine machine = new TuringMachine(12302209, 'A');
		machine.run();
		machine.printDiagnosticChecksum();
	}
}
