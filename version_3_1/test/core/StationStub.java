/**
 * 
 */
package core;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Stub for {@link core.Station} with id 22.
 * 
 * @author Fabian
 * 
 */
public class StationStub implements IStation {

    /*
     * (non-Javadoc)
     * 
     * @see logic.ILink#addLink(java.lang.String, int)
     */
    @Override
    public void addLink(String type, String to) {
	// TODO Auto-generated method stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.ILink#getReachableLinks(java.util.List)
     */
    @Override
    public Map<String, Set<String>> getReachableLinks(List<String> types) {
	/*
	 * 22 walk 23 22 walk 21 22 swim 21
	 */
	Map<String, Set<String>> reachableLinks = new HashMap<String, Set<String>>();

	Set<String> setPoint23 = new HashSet<String>();
	setPoint23.add("walk");
	reachableLinks.put("23", setPoint23);

	Set<String> setPoint21 = new HashSet<String>();
	setPoint21.add("walk");
	setPoint21.add("swim");
	reachableLinks.put("21", setPoint21);

	return reachableLinks;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#getReachablePoints(java.lang.String)
     */
    @Override
    public Set<String> getReachableStations(String type) {
	// return all walk point
	Set<String> reachableStations = new HashSet<String>();
	reachableStations.add("21");
	reachableStations.add("23");
	return reachableStations;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#getLinksMap()
     */
    @Override
    public Map<String, Set<String>> getLinkMap() {
	Map<String, Set<String>> links = new HashMap<String, Set<String>>();

	Set<String> setWalk = new HashSet<String>();
	setWalk.add("21");
	setWalk.add("23");
	links.put("walk", setWalk);

	Set<String> setCar = new HashSet<String>();
	setCar.add("23");
	links.put("car", setCar);

	return links;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
	return "Link [ID=22, links={car=[23], walk=[21, 23]}]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#getId()
     */
    @Override
    public String getId() {
	return "22";
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IStation#getReachableStations(java.util.List)
     */
    @Override
    public Set<String> getReachableStations(List<String> types) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IStation#checkLink(java.lang.String, java.lang.String)
     */
    @Override
    public boolean checkLink(String type, String station) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IStation#getTicketTypes(java.lang.String)
     */
    @Override
    public Set<String> getTicketTypes(String endStation) {
	// For endStation "23"
	Set<String> ticketTypes = new HashSet<String>();
	ticketTypes.add("walk");
	ticketTypes.add("car");
	return ticketTypes;
    }

}
