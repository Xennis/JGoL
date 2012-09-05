package gameview.kIView;

/**
 * A move of a figure.
 *
 */
public class Move {

    private final String ticket, station;
    private boolean useDouble;

	/**
	 * Create a move object.
	 * 
	 * @param ticket
	 *            Ticket type
	 * @param station
	 *            Station id
	 * @param useDouble
	 *            True, if used double ticket
	 */
    public Move(String ticket, String station, boolean useDouble) {
	this.ticket = ticket;
	this.station = station;
	this.useDouble = useDouble;
    }

	/**
	 * Get ticket type.
	 * 
	 * @return Ticket type
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * Get station id.
	 * 
	 * @return station id
	 */
	public String getStation() {
		return station;
	}

	/**
	 * Check used double ticket.
	 * 
	 * @return True, if used double ticket
	 */
	public boolean isUseDouble() {
		return useDouble;
	}

	/**
	 * Set used double ticket
	 * 
	 * @param useDouble
	 *            True, if used double ticket
	 */
	public void setUseDouble(boolean useDouble) {
		this.useDouble = useDouble;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Move [ticket=" + ticket + ", station=" + station
				+ ", useDouble=" + useDouble + "]";
	}

}
