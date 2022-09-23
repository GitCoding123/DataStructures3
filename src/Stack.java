

/**
 * This class Stack initializes a stack using a double-ended 
 * singly-linked list. 
 *  
 * 
 * @author bgerk
 */
public class Stack {
	
	
	// create first and last links
	private Link first;
	private Link last;
	
	
	/**
	 * The Stack constructor constructs a new stack based on an empty singly-linked list.
	 */
	public Stack() {								//constructor
		first = null;
		
	}
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		

	}
	/**
	 * The printStack() method prints the stack in terms of 'first in last out' based
	 * on the linked list class created within the stack class.
	 *
	 *@param current is the Link that is passed through the function 
	 */
	public void printStack(Link current) {
			
		if (current == null) {
			return;
		}
		else {
			if (current != null) {
				current.displayLink();
				current = current.next;	
			}
			printStack(current);
			return;
		}
	}
	
	/**
	 * The pop() method pops a state object out of the top of the stack.
	 * 
	 * @return temp if stack is not empty.
	 * @return null if stack is empty.
	 */
	public Link pop() {
		if (isEmpty()) {
			return null;
		}
		Link temp = first;
		first = first.next;
		
		return temp;
	}
	
	
	/**
	 * The push() method pushes a state object onto the top of the stack.
	 * 
	 * @param State state
	 */
	public void push(State state) {	

		Link newLink = new Link(state);
		newLink.next = first;
		first = newLink;
	}
	
	/**
	 * pushLast() method allows us to push items into the beginning of 'stack' (shows that we have double-ended list)
	 * 
	 * @param state  represents a double-ended singly-linked list by allowing insertion at the end of list.
	 */
	public void pushLast(State state) {
		Link newLink = new Link(state);
		if (isEmpty()) {
			first = newLink;
		}
		else {
			last.next = newLink;
		}
		last = newLink;
	}
	
	
	/**
	 * getFirst() method allows us to retrieve the first globalized variable private link from the stack class
	 * 
	 * 
	 * @return first   this is the first link
	 */
	public Link getFirst() {
		return first;
	}
	/**
	 * The isEmpty() method determines whether the stack is empty.
	 * 
	 * @return (first == null) will return true if stack is empty.
	 */
	public boolean isEmpty() {
		return first == null;
	}
}
