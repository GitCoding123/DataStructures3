


/**
	 * This class Link allows us to create links which we then can 
	 * use to form a stack, through a singly-linked list.
	 * 
	 * @author bgerk
	 */
public class Link {

	public State data;
	public Link next;
	public Link previous;
	
	/**
	 * Constructor Link() constructs the actual link given the state parameter.
	 * 
	 * @param state
	 */
	public Link(State state) {
		data = state;
		
	}
	/**
	 * The displayLink() method displays the link containing state attributes. 
	 * 
	 */
	public void displayLink() {
	
		
		System.out.printf("%-15s%-15s%-18s%-17s%-15s%-15s%-15s%-15s%-15s%-20s%-20s%-20s%n", data.getName(),
				data.getCapitol(), data.getRegion(), data.getUsHouseSeats(), 
				data.getPopulation(), data.getCovidCases(), data.getCovidDeaths(),
				data.getIncome(), data.getCrimeRate(), String.format("%.6f", data.getCFR()), String.format("%.2f", data.getCaseRate()),
				String.format("%.2f", data.getDeathRate()));
	}

}
