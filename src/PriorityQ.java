/**
 * The PriorityQ class initializes a priority queue with a set size and number of items
 * in which we insert from our sorted array in terms of highest priority. 
 * 
 * @author bgerk
 */
public class PriorityQ {


	
	
	private Link first;
	private Link last;
	
	
	/**
	 * Constructor PriorityQ constructs a priority queue based on the
	 * first link = null.
	 * 
	 */
	public PriorityQ() {			//Constructor
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
	 * The printQueue() method prints out the priority queue in terms 
	 * of the highest priority.
	 * 
	 */
	public void printPriorityQ(Link current) {
		
		if(current == null) {
			return;
		}
		else {
			if (current != null) {
				current.displayLink();
				current = current.next;	
			}
			printPriorityQ(current);
			return;
		}
	}
	/**
	 * The insert() method inserts a state through the parameter, in which we define
	 * in the Project3 class, by the highest priority
	 * 
	 * @param insertLink inserts the link that we pass through
	 */
	public void insert(Link insertLink) {		
		Link newLink = new Link(insertLink.data);		//INSERT at beginning
		Link previous = null;
		Link current = first;
		
		while(current != null && insertLink.data.getDeathRate() > current.data.getDeathRate()) {
			previous = current;
			current = current.next;
		}
		if (previous == null) {
			first = newLink;
		}
		else {
			previous.next = newLink;
		}
		newLink.next = current;
	}
	
	 /**
	  * method insertLast() allows us to show a double-ended doubly-linked list
	  * by allowing us to insert state objects into the end of the list.
	  * 
	  * @param newLink
	  */
	public void insertLast(Link newLink) {
		
		if (isEmpty()) {
			first = newLink;
		}
		else {
			last.next = newLink;
		}
		last = newLink;
	}
	/**
	 * The remove() method removes a state object with the highest priority.
	 *
	 * @return stateQueue[--items]
	 */
	public Link remove(Link current) {
		
		//DELETE WITH GIVEN LINK
	
//		Link newLink = first;
//		while (newLink.data.getDeathRate() != current.data.getDeathRate()) {
//			current = current.next;
//			if(current == null) {
//				return null;
//			}
//		}
//		if (current == first) {
//			first = current.next;
//		}
//		else {
//			current.previous.next = current.next;
//		}
//		if (current == last) {
//			last = current.previous;
//		}
//		else {
//			current.next.previous = current.previous;
//		}
//		return current;
		
		//ARBITRARY LINK DELETION
		if(current == first) {
			first = current.next;
		}
		else {
			current.previous.next = current.next;
		}
		if (current == last) {
			last = current.previous;
		}
		else {
			current.next.previous = current.previous;
		}
		return current;
	}
	/**
	 * intervalDelete() method takes user input [x,y] and deletes items from priority queue within that interval.
	 * 
	 * @param x
	 * @param y
	 * @return True if item is deleted, False if otherwise.
	 */
	public boolean intervalDelete(double x, double y) {
		
		Link newLink = first;
		while (newLink != null) {
			if (x <= newLink.data.getDeathRate() && newLink.data.getDeathRate() <= y) {
				remove(newLink);
				newLink = newLink.next;
				continue;
			}
			else if (x >= newLink.data.getDeathRate() && newLink.data.getDeathRate() <= y) {
				remove(newLink);
				newLink = newLink.next;
				continue;
			}
			else {
				return false;
			}	
		}
		return true;	
	}	

	
	/**
	 * getFirst() method allows us to create a path in order to access the private global variable 'first' 
	 * from the PriorityQ class.
	 * 
	 * @return
	 */
	public Link getFirst() {
		return first;
	}
	
	/**
	 * The isEmpty() method determines whether or not the priority queue is empty.
	 * 
	 * @return (first == null) returns true if queue is empty.
	 */
	public boolean isEmpty() {
		return first == null;
	}
}
