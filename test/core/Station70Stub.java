/**
 * 
 */
package core;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Stub for {@link core.Station} with id 70 and no reachable links to other
 * points.
 * 
 * @author Fabian
 * 
 */
public class Station70Stub implements IStation {

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#addLink(java.lang.String, java.lang.String)
     */
    @Override
    public void addLink(String type, String point) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#getReachableLinks(java.util.List)
     */
    @Override
    public Map<String, Set<String>> getReachableLinks(List<String> types) {
	Map<String, Set<String>> reachableLinks = new HashMap<String, Set<String>>();
	return reachableLinks;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.ILink#getId()
     */
    @Override
    public String getId() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IStation#getLinkMap()
     */
    @Override
    public Map<String, Set<String>> getLinkMap() {
	// TODO Auto-generated method stub
	return null;
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
     * @see core.IStation#getReachableStations(java.lang.String)
     */
    @Override
    public Set<String> getReachableStations(String type) {
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
	// TODO Auto-generated method stub
	return null;
    }

}
