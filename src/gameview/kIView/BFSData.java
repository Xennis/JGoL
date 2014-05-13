package gameview.kIView;

import java.util.Map;
/**
 * Data for BFS.
 * 
 * @author Jasper S.
 *
 */
public class BFSData {

	private String from;
	private Map<String, Integer> remainingTickets;

	/**
	 * The constructor which gets the from String and the remaining ticket map
	 * 
	 * @param from
	 *            Station
	 * @param remainingTickets
	 *            Reamaining tickets
	 */
	public BFSData(String from, Map<String, Integer> remainingTickets) {
		this.from = from;
		this.remainingTickets = (remainingTickets);
	}

	/**
	 * 
	 * @return the from String
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * Set from string
	 * 
	 * @param from Station
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 
	 * @return remaining tickets map
	 */
	public Map<String, Integer> getRemainingTickets() {
		return remainingTickets;
	}

	/**
	 * Set remaining tickets
	 * 
	 * @param remainingTickets Remaining ticket types
	 */
	public void setRemainingTickets(Map<String, Integer> remainingTickets) {
		this.remainingTickets = remainingTickets;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BFSData [from=" + from + ", remainingTickets="
				+ remainingTickets + "]";
	}

}
