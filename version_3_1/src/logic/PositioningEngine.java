package logic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import core.IStationMap;

import logic.figure.IFigurePolice;
import logic.figure.IFigureThief;

import events.logic.gameState.IEPlayerMoved;

/**
 * The positioning engine is used to determine all possible thief positions.
 * 
 * @author Jasper Schwinghamer
 * @version 3.0
 * 
 */
public class PositioningEngine implements IPositioningEngine {

    private IActorController actorCont;
    private Map<Integer, Set<String>> possiblePositions;
    private IStationMap links;

    /**
     * The positioning engine is used to determine all possible thief positions.
     * 
     * @since 2.9
     * @param actorCont
     *            the {@link logic.ActorController}, used to obtain positions
     *            and figureLists
     * @param links
     *            the link map {@link core.StationMap}
     */
    public PositioningEngine(IActorController actorCont, IStationMap links) {
	this.actorCont = actorCont;
	possiblePositions = new HashMap<Integer, Set<String>>();
	this.links = links;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPositioningEngine#setLinks(core.IStationMap)
     */
    @Override
    public void setLinks(IStationMap links) {
	this.links = links;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPositioningEngine#init()
     */
    @Override
    public void init() {
	if (!possiblePositions.isEmpty()) {
	    destoryData();
	}
	for (IFigureThief fig : actorCont.getFigureThiefList()) {
	    possiblePositions.put(fig.getId(), new HashSet<String>());
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPositioningEngine#destoryData()
     */
    @Override
    public void destoryData() {
	possiblePositions.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * logic.IPositioningEngine#moveHappend(events.logic.gameState.IEPlayerMoved
     * , int)
     */
    @Override
    public synchronized void moveHappend(IEPlayerMoved e, int figureID) {
	Set<String> oldPositions = new HashSet<String>(
		possiblePositions.get(figureID));
	Set<String> newPositions = new HashSet<String>();

	if (oldPositions.isEmpty()) {
	    return;
	}

	for (String temp : oldPositions) {
	    Set<String> tempLinks = links.get(temp).getReachableStations(
		    e.getTicketType());
	    newPositions.addAll(tempLinks);
	}
	for (IFigurePolice fig : actorCont.getFigurePoliceList()) {
	    if (newPositions.contains(fig.getPosition())) {
		newPositions.remove(fig.getPosition());
	    }
	}
	possiblePositions.put(figureID, newPositions);

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPositioningEngine#updatePosition(java.lang.String, int)
     */
    @Override
    public synchronized void updatePosition(String position, int figureID) {
	Set<String> temp = possiblePositions.get(figureID);
	temp.clear();
	temp.add(position);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPositioningEngine#getPossiblePositions()
     */
    @Override
    public synchronized Map<Integer, Set<String>> getPossiblePositions() {
	return possiblePositions;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IPositioningEngine#getAllPossiblePositions()
     */
    @Override
    public synchronized Set<String> getAllPossiblePositions() {
	Set<String> temp = new HashSet<String>();
	for (Map.Entry<Integer, Set<String>> entry : possiblePositions
		.entrySet()) {
	    temp.addAll(entry.getValue());
	}
	return temp;
    }
}
