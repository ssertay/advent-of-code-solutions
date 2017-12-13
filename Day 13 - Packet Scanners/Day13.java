public class Day13 {

	public static void part1(Firewall firewall) {
		System.out.println("Severity is: "+firewall.calculateSeverity());
	}

	public static void part2(Firewall firewall) {
		System.out.println("Minimum delay needed is: "+firewall.calculateDelayBruteForce());
	}

	public static void main(String[] args) {
		String fileName = "/Users/sertaysener/eclipse-workspace/Day13/src/input.txt";
		Firewall firewall = new Firewall();
		firewall.initializeFirewallWithFile(fileName);

		part1(firewall);
		part2(firewall);
	}
}
