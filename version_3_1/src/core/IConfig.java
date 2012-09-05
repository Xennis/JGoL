package core;

import java.util.List;
import java.util.Map;

/**
 * Interface for {@link core.Config}.
 * 
 * @author Fabian
 *
 */
public interface IConfig {

    /**
     * Returns the name of the currently loaded Map
     * 
     * @return the name of the currently loaded Map
     * @since 1.0
     */
    public abstract String getName();

    /**
     * Set the name of the map which is to be stored in the Config-Object
     * 
     * @param name
     *            the name of the map which is to be stored in the Config-Object
     * @since 1.0
     */
    public abstract void setName(String name);

    /**
     * Returns the name of the coordinate file in the directory of the map
     * 
     * @return the name of the coordinate file in the directory of the map as
     *         String
     * @since 1.0
     */
    public abstract String getCoordinates();

    /**
     * Set the name of the coordinate file in the directory of the map
     * 
     * @param coordinates
     *            the name of the coordinate file in the directory of the map
     * @since 1.0
     */
    public abstract void setCoordinates(String coordinates);

    /**
     * Returns the name of the link file in the directory of the map
     * 
     * @return the name of the link file in the directory of the map as String
     * @since 1.0
     */
    public abstract String getLinks();

    /**
     * Set the name of the links file in the directory of the map
     * 
     * @param links
     *            the name of the links file in the directory of the map
     * @since 1.0
     */
    public abstract void setLinks(String links);

    /**
     * get the name of the map image in the directory of the map
     * 
     * @return the name of the map image in the directory of the map as String
     * @since 1.0
     */
    public abstract String getMap();

    /**
     * set the name of the map image in the directory of the map
     * 
     * @param map
     *            the name of the map file in the directory of the map
     * @since 1.0
     */
    public abstract void setMap(String map);

    /**
     * Get the startAt list with all positions a figure can possibly start at.
     * 
     * @return the list with all possible start-positions as Integer
     * @since 1.0
     */
    public abstract List<String> getStartat();

    /**
     * Set the StartAt list to some predefined values.
     * 
     * @param startat
     *            the startAt positions of the map which is loaded.
     * @since 1.0
     */
    public abstract void setStartat(List<String> startat);

    /**
     * Get the names of the ticket types of the map as String-array
     * 
     * @return the names of the ticket types
     * @since 1.0
     */
    public abstract String[] getTicketTypes();

    /**
     * Set the ticketTypes to some predefined values
     * 
     * @param ticketTypes
     *            the different ticketTypes which are used by this map
     * @since 1.0
     */
    public abstract void setTicketTypes(String[] ticketTypes);

    /**
     * get the round numbers in which the thiefs are to be shown
     * 
     * @return the round numbers in which the thiefs are to be shown
     * @since 1.0
     */
    public abstract List<Integer> getShowat();

    /**
     * set the round numbers in which the thiefs are to be shown
     * 
     * @param showat
     *            the round numbers in which the thiefs are to be shown
     * @since 1.0
     */
    public abstract void setShowat(List<Integer> showat);

    /**
     * get the starting amount of tickets a police figure has.
     * 
     * @return the starting amount of tickets a police figure has as
     *         Map<TicketTypeName, TicketCount>
     * @since 1.0
     */
    public abstract Map<String, Integer> getPoliceTokens();

    /**
     * set the starting amount of tickets a police figure has.
     * 
     * @param policeTokens
     *            the starting amout of tickets a police figure should have
     * @since 1.0
     */
    public abstract void setPoliceTokens(Map<String, Integer> policeTokens);

    /**
     * get the starting amount of tickets a thief figure has.
     * 
     * @return the starting amount of tickets a thief figure has as
     *         Map<TicketTypeName, TicketCount>
     * @since 1.0
     */
    public abstract Map<String, Integer> getThiefTokens();

    /**
     * set the starting amount of tickets a thief figure has.
     * 
     * @param thiefTokens
     *            the starting amout of tickets a thief figure should have
     * @since 1.0
     */
    public abstract void setThiefTokens(Map<String, Integer> thiefTokens);

    /**
     * Add additional tokens to the thiefs starting repository
     * 
     * @param identifier
     *            the ticket type
     * @param value
     *            the number of tickets to be added
     * @since 1.0
     */
    public abstract void putThiefTokens(String identifier, Integer value);

    /**
     * Add additional tokens to the policemans starting repository
     * 
     * @param identifier
     *            the ticket type
     * @param value
     *            the number of tickets to be added
     * @since 1.0
     */
    public abstract void putPoliceTokens(String identifier, Integer value);

    /**
     * get the starting amount of tickets a figure has.
     * 
     * @param playerType
     *            player type integer
     * @return the starting amount of tickets a figure has as
     *         Map<TicketTypeName, TicketCount>
     * @since 1.0
     */
    public abstract Map<String, Integer> getTokens(int playerType);

}