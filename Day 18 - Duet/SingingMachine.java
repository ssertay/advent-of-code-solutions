import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Queue;


public class SingingMachine {
	private long[] registers;
	private ArrayList<String> operations;
	private int programCounter;
	private String fileName = "/Users/sertaysener/eclipse-workspace/Day18/src/input.txt";
	private int machineID;
	private int valuesSent;
	private int mulInvoked;
	private boolean waiting;
	
	// NOTE this code was altered to fit day 22, make a copy if necessary
	public SingingMachine(int id) {
		registers = new long[26];
		resetRegisters();
		operations = new ArrayList<String>();
		programCounter = 0;
		machineID = id;
		valuesSent = 0;
		registers['p'-'a'] = machineID;
		waiting = false;
		
		loadOperationsFromFile();
	}
	
	public boolean isWaiting() { return waiting; }
	
	public int valuesSent() { return valuesSent; }
	
	private void resetRegisters() {
		for(int i=0;i<26;i++)
			registers[i] = 0;
	}
	
	private void setRegister(char target, long value) {
		registers[target - 'a'] = value;
	}
	
	private void addition(char target, long value) {
		registers[target - 'a'] += value;
	}
	
	// TODO
	private void subtraction(char target, long value) {
		registers[target - 'a'] -= value;
	}
	
	private void multiplication(char target, long value) {
		registers[target - 'a'] *= value;
	}
	
	private void modular(char target, long value) {
		registers[target - 'a'] = registers[target - 'a'] % value;
	}
	
	private void jumpIfPositive(long val, long offset) {
		if(val > 0)
			programCounter += offset-1;
	}
	
	private void jumpIfNotZero(long val, long offset) {
		if(val != 0) {
			programCounter += offset-1;
		}
	}
	
	private void receive(char reg, Queue<Long> q) {
		if(q.isEmpty()) {
			waiting = true;
			programCounter--;
			System.out.println("Machine "+machineID+" is waiting.");
		} else {
			if(waiting)
				System.out.println("Machine "+machineID+" is now running.");
			waiting = false;
			long val = q.remove();
			registers[reg - 'a'] = val;
			System.out.println("Machine "+machineID+" received "+val);
		}
	}
	
	private void send(long value, Queue<Long> q) {
		q.add(value);
		System.out.println("Machine "+machineID+" sent "+value);
		valuesSent++;
	}
	
	private long getValue(String param) {
		if(param.charAt(0) >= 'a' && param.charAt(0) <= 'z') // parameter is a register reference, return its value;
			return registers[param.charAt(0) - 'a'];
		else
			return Long.parseLong(param);
	}
	
	public void executeSingeOperation(Queue<Long> firstQueue, Queue<Long> secondQueue) {
			String[] opArray = operations.get(programCounter).split(" ");
			System.out.println(programCounter+" => "+operations.get(programCounter));
			String opName = opArray[0];
			
			switch(opName) {
			case "snd":
				if(machineID == 0) {
					send(getValue(opArray[1]), secondQueue);
				} else {
					send(getValue(opArray[1]), firstQueue);
				}
				break;
			case "set":
				setRegister(opArray[1].charAt(0), getValue(opArray[2]));
				break;
			case "add":
				addition(opArray[1].charAt(0), getValue(opArray[2]));
				break;
			case "sub":
				subtraction(opArray[1].charAt(0), getValue(opArray[2]));
				break;
			case "mul":
				multiplication(opArray[1].charAt(0), getValue(opArray[2]));
				break;
			case "mod":
				modular(opArray[1].charAt(0), getValue(opArray[2]));
				break;
			case "rcv":
				if(machineID == 0) {
					receive(opArray[1].charAt(0), firstQueue);
				} else {
					receive(opArray[1].charAt(0), secondQueue);
				}
				break;
			case "jgz":
				jumpIfPositive(getValue(opArray[1]), getValue(opArray[2]));
				break;
			case "jnz":
				jumpIfNotZero(getValue(opArray[1]), getValue(opArray[2]));
				break;
			default:
				break;
			}
			programCounter++;
		
	}
	
	public void executeOperations(Queue<Long> firstQueue, Queue<Long> secondQueue) {
		int t = 0;
		while((programCounter >= 0) && (programCounter < operations.size()) && t<200) {
			executeSingeOperation(firstQueue, secondQueue);
			t++;
		}
		
		System.out.println("Stopped. Mul invkoked: " + mulInvoked);
	}
	
	public void printSent() {
		System.out.println("Machine "+machineID+" sent "+valuesSent+" values.");
	}
	
	private void loadOperationsFromFile() {
		System.out.println("Loading operations for machine: " + machineID);
		String line = null;
		
		try {
			BufferedReader bufReader = new BufferedReader(new FileReader(fileName));
			while((line = bufReader.readLine()) != null) {
				operations.add(line);
			}
			
			System.out.println("Operations for machine: " + machineID + " loaded.");
			bufReader.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {                
	        e.printStackTrace();
		}
	}
}
