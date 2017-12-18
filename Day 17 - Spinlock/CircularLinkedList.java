public class CircularLinkedList {
	private Node head;
	private Node current;
	
	public CircularLinkedList() {
		head = null;
		current = null;
	}
	
	public Node getCurrent() { return current; }
	public Node getHead() { return head; }
	
	public void takeSteps(int n) {
		for(int steps=0; steps<n ;steps++) 
			current = current.next;
	}
	
	public void addToCurrent(int value) {
		Node node = new Node(value);
		
		if(head == null) {
			head = node;
			current = node;
			node.next = head;
		} else {
			node.next = current.next;
			current.next = node;
			current = current.next;
		}
	}
	
	public void printList() {
		Node runner = head;
		do {
			System.out.print(runner.getData() + " ");
			runner = runner.next;
		} while(runner != head);
	}
}
