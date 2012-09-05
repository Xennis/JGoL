/**
 * 
 */
package core;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Fabian
 * 
 */
public class ConfigStub implements IConfig {

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getName()
     */
    @Override
    public String getName() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getCoordinates()
     */
    @Override
    public String getCoordinates() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setCoordinates(java.lang.String)
     */
    @Override
    public void setCoordinates(String coordinates) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getLinks()
     */
    @Override
    public String getLinks() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setLinks(java.lang.String)
     */
    @Override
    public void setLinks(String links) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getMap()
     */
    @Override
    public String getMap() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setMap(java.lang.String)
     */
    @Override
    public void setMap(String map) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getStartat()
     */
    @Override
    public List<String> getStartat() {
	List<String> startat = new LinkedList<String>();
	startat.add("22");
	startat.add("23");
	startat.add("70");
	return startat;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getTicketTypes()
     */
    @Override
    public String[] getTicketTypes() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setTicketTypes(java.lang.String[])
     */
    @Override
    public void setTicketTypes(String[] ticketTypes) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getShowat()
     */
    @Override
    public List<Integer> getShowat() {
	List<Integer> showat = new LinkedList<Integer>();
	showat.add(3);
	showat.add(8);
	return showat;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getPoliceTokens()
     */
    @Override
    public HashMap<String, Integer> getPoliceTokens() {
	HashMap<String, Integer> policeTokens = new HashMap<String, Integer>();
	policeTokens.put("walk", 8);
	policeTokens.put("car", 4);
	policeTokens.put("swim", 0);
	return policeTokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getThiefTokens()
     */
    @Override
    public HashMap<String, Integer> getThiefTokens() {
	HashMap<String, Integer> thiefTokens = new HashMap<String, Integer>();
	thiefTokens.put("walk", 3);
	thiefTokens.put("car", 3);
	thiefTokens.put("swim", 2);
	return thiefTokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#putThiefTokens(java.lang.String, java.lang.Integer)
     */
    @Override
    public void putThiefTokens(String identifier, Integer value) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#putPoliceTokens(java.lang.String, java.lang.Integer)
     */
    @Override
    public void putPoliceTokens(String identifier, Integer value) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setStartat(java.util.List)
     */
    @Override
    public void setStartat(List<String> startat) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setShowat(java.util.List)
     */
    @Override
    public void setShowat(List<Integer> showat) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setPoliceTokens(java.util.Map)
     */
    @Override
    public void setPoliceTokens(Map<String, Integer> policeTokens) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setThiefTokens(java.util.Map)
     */
    @Override
    public void setThiefTokens(Map<String, Integer> thiefTokens) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getTokens(int)
     */
    @Override
    public Map<String, Integer> getTokens(int playerType) {
	// Thief only
	HashMap<String, Integer> thiefTokens = new HashMap<String, Integer>();
	thiefTokens.put("walk", 3);
	thiefTokens.put("car", 3);
	thiefTokens.put("swim", 2);
	return thiefTokens;
    }

}
