import java.util.HashSet;

public class TuringMachine {
	private char machineState;
	private long steps;
	private HashSet<Long> setSlots;
	private long currentPosition;
	private State[] states;
	
	public TuringMachine(long steps, char initialState) {
		machineState = initialState;
		this.steps = steps;
		currentPosition = 0;
		setSlots = new HashSet<Long>();
		
		machineState = 'A';
		states = new State[6];
		states['A' - 'A'] = new State(new boolean[]{true, false}, new long[]{1, -1}, new char[] {'B', 'D'});
		states['B' - 'A'] = new State(new boolean[]{true, false}, new long[]{1, 1}, new char[] {'C', 'F'});
		states['C' - 'A'] = new State(new boolean[]{true, true}, new long[]{-1, -1}, new char[] {'C', 'A'});
		states['D' - 'A'] = new State(new boolean[]{false, true}, new long[]{-1, 1}, new char[] {'E', 'A'});
		states['E' - 'A'] = new State(new boolean[]{true, false}, new long[]{-1, 1}, new char[] {'A', 'B'});
		states['F' - 'A'] = new State(new boolean[]{false, false}, new long[]{1, 1}, new char[] {'C', 'E'});
	}
	
	private boolean getCurrentValue() {
		return setSlots.contains(currentPosition);
	}
	
	private void takeAStep(long nextStep, char nextState, boolean nextValue) {
		if(nextValue && !getCurrentValue()) {
			setSlots.add(currentPosition);
		} else if(!nextValue && getCurrentValue()) {
			setSlots.remove(currentPosition);
		}
		machineState = nextState;
		currentPosition += nextStep;
		
	}
	
	public void run() {
		for(int t=0; t<steps ;t++) {
			takeAStep(states[machineState - 'A'].getStep(getCurrentValue()), 
					states[machineState - 'A'].getNextState(getCurrentValue()), 
					states[machineState - 'A'].getNextValue(getCurrentValue()));
		}
		
		System.out.println("Machine stopped.");
	}
	
	public void printDiagnosticChecksum() {
		System.out.println(setSlots.size()+" many bits are set.");
	}
}
