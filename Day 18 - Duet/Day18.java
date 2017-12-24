import java.util.LinkedList;
import java.util.Queue;

public class Day18 {
	
	public static void main(String[] args) {
		Queue<Long> firstQueue = new LinkedList<Long>();
		Queue<Long> secondQueue = new LinkedList<Long>();
		
		SingingMachine firstMachine = new SingingMachine(0);
		SingingMachine secondMachine = new SingingMachine(1);
		
		while(!firstMachine.isWaiting() || !secondMachine.isWaiting()) {
			firstMachine.executeSingeOperation(firstQueue, secondQueue);
			secondMachine.executeSingeOperation(firstQueue, secondQueue);
		}
		
		System.out.println("Second machine sent "+secondMachine.valuesSent()+" times.");
	}
		
}
