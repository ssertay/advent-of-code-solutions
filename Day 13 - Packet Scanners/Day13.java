import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Day13 {
	
	public static void main(String[] args) {
		String fileName = "/Users/sertaysener/eclipse-workspace/Day13/src/input.txt";
		Firewall firewall = new Firewall();
		firewall.initializeFirewallWithFile(fileName);
		
		System.out.println(firewall.calculateDelayBruteForce());
	}
}
