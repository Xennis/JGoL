package core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import logic.Constants;

import core.msgpump.IMsgPump;

/**
 * the configuration-file-object for the game. No further documentation needed,
 * since it consists only of setters and getters. Holds each property which is
 * in the .ini file. The set methods are only to be used by the loadConfig
 * method of the RessourceCache class
 * 
 * @author Jasper, Fabian
 * @version 1.0
 */
public class Config implements IConfig, Serializable {

    private static final long serialVersionUID = -2976455177743828600L;
    private String name;
    private String coordinates;
    private String links;
    private String map;
    private List<String> startat;
    private List<Integer> showat;
    private String[] ticketTypes;

    private Map<String, Integer> policeTokens;
    private Map<String, Integer> thiefTokens;

    private IMsgPump msgPump;

    public Config(IMsgPump msgPump) {
	this.msgPump = msgPump;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getName()
     */
    @Override
    public String getName() {
	return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setName(java.lang.String)
     */
    @Override
    public void setName(String name) {
	msgPump.logInfo("Just added the game name: " + name);
	this.name = name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getCoordinates()
     */
    @Override
    public String getCoordinates() {
	return coordinates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setCoordinates(java.lang.String)
     */
    @Override
    public void setCoordinates(String coordinates) {
	msgPump.logInfo("Just added the coordinates filename: " + coordinates);
	this.coordinates = coordinates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getLinks()
     */
    @Override
    public String getLinks() {
	return links;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setLinks(java.lang.String)
     */
    @Override
    public void setLinks(String links) {
	msgPump.logInfo("Just added the link filename: " + links);
	this.links = links;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getMap()
     */
    @Override
    public String getMap() {
	return map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setMap(java.lang.String)
     */
    @Override
    public void setMap(String map) {
	msgPump.logInfo("Just added the map filename: " + map);
	this.map = map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getStartat()
     */
    @Override
    public List<String> getStartat() {
	return startat;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setStartat(int[])
     */
    @Override
    public void setStartat(List<String> startat) {
	msgPump.logInfo("Just added the startat array");
	this.startat = startat;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getTicketTypes()
     */
    @Override
    public String[] getTicketTypes() {
	return ticketTypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setTicketTypes(java.lang.String[])
     */
    @Override
    public void setTicketTypes(String[] ticketTypes) {
	this.ticketTypes = ticketTypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getShowat()
     */
    @Override
    public List<Integer> getShowat() {
	return showat;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setShowat(int[])
     */
    @Override
    public void setShowat(List<Integer> showat) {
	msgPump.logInfo("Just added the showat array");
	this.showat = showat;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getPoliceTokens()
     */
    @Override
    public Map<String, Integer> getPoliceTokens() {
	return policeTokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getTokens(int)
     */
    @Override
    public Map<String, Integer> getTokens(int playerType) {
	if (playerType == Constants.POLICE_PLAYER_ID) {
	    return getPoliceTokens();
	} else if (playerType == Constants.THIEF_PLAYER_ID) {
	    return getThiefTokens();
	} else {
	    return null;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setPoliceTokens(java.util.Map)
     */
    @Override
    public void setPoliceTokens(Map<String, Integer> policeTokens) {
	this.policeTokens = policeTokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#getThiefTokens()
     */
    @Override
    public Map<String, Integer> getThiefTokens() {
	return thiefTokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#setThiefTokens(java.util.Map)
     */
    @Override
    public void setThiefTokens(Map<String, Integer> thiefTokens) {
	this.thiefTokens = thiefTokens;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#putThiefTokens(java.lang.String, java.lang.Integer)
     */
    @Override
    public void putThiefTokens(String identifier, Integer value) {
	thiefTokens.put(identifier, value);
	msgPump.logInfo("Just added to ThiefHashMap: " + identifier + "="
		+ value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IConfig#putPoliceTokens(java.lang.String, java.lang.Integer)
     */
    @Override
    public void putPoliceTokens(String identifier, Integer value) {
	policeTokens.put(identifier, value);
	msgPump.logInfo("Just added to PoliceHashMap: " + identifier + "="
		+ value);
    }

}
