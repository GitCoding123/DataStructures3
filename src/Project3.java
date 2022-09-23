
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;





/**
 * COP 3530: Project - Array Searches and Sorts
 * 
 * Project3 class includes methods to read in a csv file,
 * creating state objects using the data in the file to set and get attributes
 * for an object, and creating a stack and a priority queue of State objects
 * in which we can push/pop state objects from the stack and insert/remove state
 * objects from the priority  through singly and doubly linked lists.
 * 
 * @author Brian Gerkens
 * @version 10/26/21
 */

public class Project3 {
	
	public Project3() {
		
	}

	/**
	 * The main method includes while loops to prompt user for the file name
	 * input until the correct file name is entered. It also prints a number
	 * menu that will keep reprinting until option '3' is entered, which
	 * exits the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		String line = "";
		boolean running = true;
		Scanner scan = new Scanner(System.in);
		
		Link popLink;
				
		State state; 			//states read from file
				
		Stack stack = new Stack();				//stack object to call methods from stack class.
		PriorityQ pq = new PriorityQ();			//queue object to call methods from queue class.
		
		
		printHeader();
		System.out.println("Enter the file name: ");
		
		while(running) {
			String fileName = scan.next();
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
					
				 
				String headLine = br.readLine();
				while((line = br.readLine()) != null) {
							
					String[] data = line.split(",");
					//parse data from file
					String name = data[0];
					String capitol = data[1];
					String region = data[2];
					int usHouseSeats = Integer.parseInt(data[3]);
					int population = Integer.parseInt(data[4]);
					double covidCases = Double.parseDouble(data[5]);
					double covidDeaths = Double.parseDouble(data[6]);
					int income = Integer.parseInt(data[7]);
					double crimeRate = Double.parseDouble(data[8]);
					double CFR = covidCases / covidDeaths;
					double caseRate = covidCases / population * 100000;
					double deathRate = covidDeaths / population * 100000;
					
					state = new State(name, capitol, region, usHouseSeats, population,					//Create state objects from file, line by line.
							covidCases, covidDeaths, income, crimeRate, CFR, caseRate, deathRate);
					
					if (70 <= state.getDeathRate() && state.getDeathRate() < 100) {						//push state onto stack if 
						stack.push(state);
						
					}
					if (100 <= state.getDeathRate() && state.getDeathRate() < 150) {
						stack.push(state);
					}
					if (150 <= state.getDeathRate() && state.getDeathRate() < 250) {
						stack.push(state);
					}
					
					
				}
				running = false;			//end main while loop		
			}
			catch (FileNotFoundException e) {
				System.out.println("YIKES!!! Incorrect file name. Please try again.\nEnter the file name: ");
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	
		//Print stack contents
		
		System.out.println("\nStack Contents: \n");
		System.out.printf("%-15s%-15s%-18s%-17s%-15s%-15s%-15s%-15s%-19s%-17s%-17s%-17s%n",
				"Name", "Capitol", "Region", "US House Seats", "Population", "Covid Cases",
				"Covid Deaths", "Income", "Crime Rate", "CFR", "Case Rate", "Death Rate");
			for (int j = 0; j < 190; j++) {
				System.out.print("-");
			}
			System.out.println();
		stack.printStack(stack.getFirst());
		
		// Code to pop items off stack and insert them into priority queue.
		
		int n = 40;
		while (n > 0) {
			popLink = stack.pop();
			pq.insert(popLink);
			n--;
		}
		
		//Print queue contents
	
		System.out.println("\nPriority Queue contents: \n");
		System.out.printf("%-15s%-15s%-18s%-17s%-15s%-15s%-15s%-15s%-19s%-17s%-17s%-17s%n",
				"Name", "Capitol", "Region", "US House Seats", "Population", "Covid Cases",
				"Covid Deaths", "Income", "Crime Rate", "CFR", "Case Rate", "Death Rate");
		for(int j = 0; j < 190; j++) {
			System.out.print("-");
		}
		System.out.println();
		pq.printPriorityQ(pq.getFirst());
		System.out.println();
		
		
		//Menu
		running = true;
		int menuNumber = 0;
		int xInterval;
		int yInterval;
		
		while(running) {
			System.out.println("\nPlease choose options 1-3 from menu:\n");
			printMenu();
			try {
				menuNumber = scan.nextInt();
			}
			catch(InputMismatchException e) {
				scan.next();
			}
			if(menuNumber == 1) {
				System.out.println("Please enter a DR interval [x,y] for deletions.\nX: ");
				try {
					xInterval = scan.nextInt();
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid Entry!!!\n Try again.\n");
					continue;
				}
				
				System.out.println("\nY: ");
				try {
					yInterval = scan.nextInt();
				}
				catch (InputMismatchException e) {
					System.out.println("Invalid Entry!!!\n Try again.\n");
					continue;
				}
				if (xInterval > yInterval) {
					System.out.println("Error!!!\nX interval must be smaller than Y interval!!\nTry again.\n");
					continue;
				}
				pq.intervalDelete(xInterval, yInterval);
				System.out.println("States of priority queue with DRs in [" + xInterval + "," + yInterval + "] are deleted\n");
				
				
			}
			else if(menuNumber == 2) {
				
				System.out.println("\nPriority Queue contents: \n");
				System.out.printf("%-15s%-15s%-18s%-17s%-15s%-15s%-15s%-15s%-19s%-17s%-17s%-17s%n",
						"Name", "Capitol", "Region", "US House Seats", "Population", "Covid Cases",
						"Covid Deaths", "Income", "Crime Rate", "CFR", "Case Rate", "Death Rate");
				for(int j = 0; j < 190; j++) {
					System.out.print("-");
				}
				System.out.println();
				pq.printPriorityQ(pq.getFirst());
			}
			else if(menuNumber == 3) {
				System.out.println("File closed.\nProgram terminated.\nHave a nice day!");
				scan.close();
				running = false;
			}
			
			else {
				System.out.println("\nYIKES!!!\nInvalid entry. Please try again: \n");
			}	
		}
	}
	
	/**
	 * The printHeader() method prints the proper header consisting of the class,
	 * instructor name, and title of project 3.
	 * 
	 */
	public static void printHeader() {
		System.out.println("COP3530 Project 3\nInstructor: Xudong Liu\n");
		System.out.println("Linked Lists");
	}
	/**
	 * The printMenu() method prints the menu to the user with options 1-3.
	 * 
	 */
	public static void printMenu() {
		System.out.println("1.) Enter a DR interval for deletions.\n2.) Print the priority queue.\n3.) Exit\n");
	}
	
}