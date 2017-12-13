import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;


public class Day8 {
	
	static int maxValueEverHeld = Integer.MIN_VALUE;

	public static boolean checkConditional(HashMap<String, Integer> registers, String firstParam, String conditional, int secondParam) {
		int registerValue = registers.get(firstParam);

		switch(conditional) {
			case "==":
				return registerValue == secondParam;
			case "!=":
				return registerValue != secondParam;
			case ">":
				return registerValue > secondParam;
			case "<":
				return registerValue < secondParam;
			case ">=":
				return registerValue >= secondParam;
			case "<=":
				return registerValue <= secondParam;
			default:
				throw new IllegalArgumentException("Can't evaluate the conditional: " + conditional);
		}

	}

	public static void checkRegister(HashMap<String, Integer> registers, String registerName) {
		if(!registers.containsKey(registerName)) {
			registers.put(registerName, 0);
		}
	}

	public static void modifyRegister(HashMap<String, Integer> registers, String registerName, String operation, int amount) {

		switch(operation) {
			case "inc":
				break;
			case "dec":
				amount *= -1;
				break;
			default:
				throw new IllegalArgumentException("Can't evaluate the operation: " + operation);
		}

		int newValue = registers.get(registerName) + amount;

		if(newValue > maxValueEverHeld) {
			maxValueEverHeld = newValue;
		}
		registers.put(registerName, newValue);
	}

	public static void processLine(HashMap<String, Integer> registers, String line) {
		String[] lineArray = line.split(" ");
		String registerName = lineArray[0];
		String operation = lineArray[1];
		int number = Integer.parseInt(lineArray[2]);
		String firstParameter = lineArray[4];
		String conditional = lineArray[5];
		int secondParameter = Integer.parseInt(lineArray[6]);

		checkRegister(registers, registerName);
		checkRegister(registers, firstParameter);

		if(checkConditional(registers, firstParameter, conditional, secondParameter)) {
			modifyRegister(registers, registerName, operation, number);
		}
	}

	public static int getMaxRegisterValue(HashMap<String, Integer> registers) {
		int max = Integer.MIN_VALUE;

	    Iterator it = registers.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        if((int)pair.getValue() > max) {
	        		max = (int)pair.getValue();
	        }
	        it.remove();
	    }

	    return max;
	}

	public static void readInstructions(HashMap<String, Integer> registers) {

		String fileName = "/Users/sertaysener/eclipse-workspace/Day8/src/input.txt";
		String line = null;

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

			while(((line = bufferedReader.readLine()) != null)) {
				processLine(registers, line);
			}

			bufferedReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
	        e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		HashMap<String, Integer> registers = new HashMap<String, Integer>();

		readInstructions(registers);
		System.out.println(maxValueEverHeld);
	}
}
