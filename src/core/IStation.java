package core;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for a {@link core.Station} on the map.
 * 
 * @author Fabian R.
 */
public interface IStation {

    /**
     * Add a link to another station.
     * 
     * @param type
     *            Ticket type for this link
     * @param station
     *            Destination station
     */
    public abstract void addLink(String type, String station);

    /**
     * Check link to another points exists.
     * 
     * @param type
     *            Ticket type for this link
     * @param station
     *            Destination station id
     * @return True if the link exists
     */
    public abstract boolean checkLink(String type, String station);

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public abstract boolean equals(Object obj);

    /**
     * Get the identification String of the station.
     * 
     * @return Identification String of the station.
     */
    public abstract String getId();

    /**
     * Get the links from this station to other stations. {@link java.util.Map}
     * with ticket types as key and stations id as value.
     * 
     * @return {@link java.util.Map} with all links from this stations to other
     *         stations
     */
    public abstract Map<String, Set<String>> getLinkMap();

    /**
     * Get reachable links from this stations to other stations for specific
     * ticket types. Returns a {@link java.util.Map} with stations id as key and
     * ticket types as value.
     * 
     * @param types
     *            List with ticket types of a {@link logic.figure.AFigure} (
     *            {@link logic.figure.AFigure#getTicketTypesRemaining()})
     * @return {@link java.util.Map} with stations id as key and ticket types as
     *         value.
     */
    public abstract Map<String, Set<String>> getReachableLinks(
	    List<String> types);

    /**
     * Get all reachable stations from this stations for specific ticket types.
     * 
     * @param types
     *            {@link java.util.List} with ticket types
     * @return {@link java.util.Set} with stations id's
     */
    public abstract Set<String> getReachableStations(List<String> types);

    /**
     * Get a {@link java.util.List} with all reachable stations for the specific
     * ticket type.
     * 
     * @param type
     *            Ticket type
     * @return {@link java.util.List} with all reachable stations for the
     *         specific ticket type
     */
    public abstract Set<String> getReachableStations(String type);

    /**
     * Get {@link java.util.Set} with all ticket types from this station to
     * another station.
     * 
     * @param endStation
     *            Map station
     * @return {@link java.util.Set} with all ticket types from this station to
     *         another station
     */
    public abstract Set<String> getTicketTypes(String endStation);

    /*
     * (non-Javadoc)
     * 
     * @see logic.ILink#getReachablePoints(java.lang.String)
     */
    public abstract String toString();
}