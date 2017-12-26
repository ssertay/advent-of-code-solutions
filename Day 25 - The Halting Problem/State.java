public class State {
	private char[] nextState;
	private long[] step;
	private boolean[] nextValue;
	
	public State(boolean[] nextValue, long[] step, char[] nextState) {
		this.nextState = new char[] { nextState[0], nextState[1] };
		this.step = new long[] { step[0], step[1] };
		this.nextValue = new boolean[] { nextValue[0], nextValue[1] };
	}
	
	public char getNextState(boolean tapeValue) { return tapeValue ? nextState[1] : nextState[0]; }
	public long getStep(boolean tapeValue) { return tapeValue ? step[1] : step[0]; }
	public boolean getNextValue(boolean tapeValue) { return tapeValue ? nextValue[1] : nextValue[0]; }
}
