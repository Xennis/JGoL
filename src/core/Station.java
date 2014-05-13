package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.Constants;

/**
 * The Class holds information about a point on the map.
 * 
 * @author Fabian
 * @since 0.3.6
 * @version 3.0
 */
public class Station implements IStation {

    /** The link identification integer. */
    private final String id;

    /**
     * The links from this point to other points. {@link java.util.Map} with
     * ticket types as key and point as value.
     */
    Map<String, Set<String>> linkMap;

    /** All station with are reachable from this station */
    private Set<String> stationList;

    /**
     * Construct a new link object.
     * 
     * @param id
     *            The identification integer
     */
    public Station(String id) {
	this.id = id;
	this.linkMap = new HashMap<String, Set<String>>();
	this.stationList = new HashSet<String>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.ILink#addLink(java.lang.String, java.lang.String)
     */
    @Override
    public void addLink(String type, String station) {
	if (!this.linkMap.containsKey(type)) {
	    this.linkMap.put(type, new HashSet<String>());
	}
	this.linkMap.get(type).add(station);
	this.stationList.add(station);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.ILink#checkLink(java.lang.String, java.lang.String)
     */
    @Override
    public boolean checkLink(String type, String point) {
	if (this.linkMap.containsKey(type)) {
	    return this.linkMap.get(type).contains(point);
	} else {
	    return false;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
	IStation link = (IStation) obj;
	if (id.equals(link.getId()) && linkMap.equals(link.getLinkMap())) {
	    return true;
	} else {
	    return false;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#getId()
     */
    @Override
    public String getId() {
	return id;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#getLinksMap()
     */
    @Override
    public Map<String, Set<String>> getLinkMap() {
	return linkMap;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.ILink#getReachableLinks(java.util.List)
     */
    @Override
    public Map<String, Set<String>> getReachableLinks(List<String> types) {
	Map<String, Set<String>> reachableLinks = new HashMap<String, Set<String>>();

	if (types.contains(Constants.TICKET_BLACK)) {
	    // Add all stations with ticket "black".
	    for (String station : stationList) {
		reachableLinks.put(station, new HashSet<String>());
		reachableLinks.get(station).add(Constants.TICKET_BLACK);
	    }
	}

	for (String type : types) {
	    if (this.linkMap.containsKey(type)) {
		for (String station : this.linkMap.get(type)) {
		    if (!reachableLinks.containsKey(station)) {
			reachableLinks.put(station, new HashSet<String>());
		    }
		    reachableLinks.get(station).add(type);
		}
	    }
	}

	return reachableLinks;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IStation#getReachableStations(java.util.List)
     */
    @Override
    public Set<String> getReachableStations(List<String> types) {
	Set<String> reachableStations = new HashSet<String>();
	for (String type : types) {
	    reachableStations.addAll(this.getReachableStations(type));
	}
	return reachableStations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.ILink#getReachablePoints(java.lang.String)
     */
    @Override
    public Set<String> getReachableStations(String type) {
	Set<String> reachableStations = new HashSet<String>();

	if (type.equals(Constants.TICKET_BLACK)) {
	    // Add all stations
	    reachableStations.addAll(stationList);
	} else {
	    if (linkMap.containsKey(type)) {
		reachableStations.addAll(linkMap.get(type));
	    }
	}
	return reachableStations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IStation#getTicketTypes(java.lang.String)
     */
    @Override
    public Set<String> getTicketTypes(String endStation) {
	Set<String> returnSet = new HashSet<>();
	for (Map.Entry<String, Set<String>> entr : linkMap.entrySet()) {
	    if (entr.getValue().contains(endStation)) {
		returnSet.add(entr.getKey());
	    }
	}
	returnSet.add(Constants.TICKET_BLACK);
	return returnSet;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#toString()
     */
    @Override
    public String toString() {
	return "Link [ID=" + id + ", links=" + linkMap + "]";
    }
}
