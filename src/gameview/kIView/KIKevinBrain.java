package gameview.kIView;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import logic.Constants;

import core.IStationMap;

import events.EventConstants;
import events.IAEvent;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.ENextFigure;

import gameview.IKiGameView;

/**
 * Ki easy brain.
 * 
 * @author Felix K.
 * 
 */
public class KIKevinBrain extends AKIBrain {

    private Map<Integer, EInitNewGame> figureList;
    private Map<String, Set<String>> reachableLinks;
    private boolean doubleAllowed = false;
    private int currentFigureID;
    private String currentStation;
    private Map<Integer, String> showThieves;
    Random rand;
    List<String> keyList;

    /**
     * The constructor which gets and sets the link map and the kigameview
     * 
     * @param links
     *            Map with all map links
     * @param kiGV
     *            The ki gameView
     */
    public KIKevinBrain(IStationMap links, IKiGameView kiGV) {
	super(links, kiGV);
	figureList = new HashMap<Integer, EInitNewGame>();
	showThieves = new HashMap<>();
	rand = new Random();
	keyList = new LinkedList<>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.kIView.AKIBrain#getMove()
     */
    @Override
    public synchronized Move getMove() {
	return move;

    }

    /**
     * Sets a move for the random KI, it auto chooses a random position. In case
     * a thieve is around (==> its position is in showthieves), it autochooses
     * this station
     * 
     * @param move
     *            Move of a figure
     */
    @Override
    protected synchronized void setMove(Move move) {
	// search for thief around
	String station = "";
	if (figureList.containsKey(currentFigureID)
		&& figureList.get(currentFigureID).getType() == Constants.POLICE_PLAYER_ID) {
	    station = checkPolice();

	    // search for cop around (check in figurelist for
	    // matches in the prev. generated keyList)
	} else if (figureList.containsKey(currentFigureID)
		&& figureList.get(currentFigureID).getType() == Constants.THIEF_PLAYER_ID) {
	    station = checkOther();
	}
	

	List<String> tickets = new LinkedList<>(reachableLinks.get(station));
	int randomTicket = rand.nextInt(tickets.size());
	String ticket = tickets.get(randomTicket);
	boolean usedouble = false;
	if (doubleAllowed) {
	    int randomDouble = rand.nextInt(1);
	    if (randomDouble == 1) {
		usedouble = true;
	    }
	}
	this.move = new Move(ticket, station, usedouble);
    }

    /**
     * Helper method for setMove, basically it checks for thieves. This can only
     * happen if showthieves isnt empty If it is empty, it chooses a random
     * value to move to
     * 
     * @return returns the Stationname
     */
    private String checkPolice() {
	if (!showThieves.isEmpty()) {
	    for (Map.Entry<Integer, String> entr : showThieves.entrySet()) {
		if (reachableLinks.containsKey(entr.getValue())) {
		    return entr.getValue();
		}
	    }
	}
	//no thieve in showthieves/no thief near to cop, so just return a random value
	int randomKey = rand.nextInt(keyList.size());
	return keyList.get(randomKey);
    }

    /**
     * Helper method for setMove, basically it deletes all stations which have
     * cops on it and then choses a random value from the rest. If the thief is
     * surrounded by cops, it just uses a random value for movement
     * 
     * @return returns the Stationname
     */
    private String checkOther() {
	int randomKey = rand.nextInt(keyList.size());
	for (Map.Entry<Integer, EInitNewGame> entr : figureList.entrySet()) {
	    if (entr.getValue().getType() != Constants.POLICE_PLAYER_ID) {
		continue;
		// if the cop is at the position, remove the station from the
		// keylist and choose a new randomKey
	    } else if (keyList.contains(entr.getValue().getFigurePosition())) {
		keyList.remove(entr.getValue().getFigurePosition());
		randomKey = rand.nextInt(keyList.size());
	    }
	}
	// if all keys have been deleted, reset the listand choose a random
	// value
	if (keyList.isEmpty()) {
	    keyList = new LinkedList<>(reachableLinks.keySet());
	    randomKey = rand.nextInt(keyList.size());
	}
	// return the station, either one without cops or random if only cops
	// are around
	return keyList.get(randomKey);
    }

    /**
     * Processes Event Data, so it can set moves at the right time and get all
     * needed data from the event object
     * 
     * @param e
     *            A event
     */
    @Override
    public void processEvent(IAEvent e) {
	if (e.getEventType() == EventConstants.INIT_NEW_GAME) {
	    EInitNewGame temp = (EInitNewGame) e;
	    figureList.put(temp.getFigureId(), temp);
	}
	if (e.getEventType() == EventConstants.NEXT_FIGURE_EVENT
		&& figureList.get(((ENextFigure) e).getFigureId()).getOwnerGV() == kiGV
			.getViewID()) {
	    kiGV.setActive(true);
	    ENextFigure temp = (ENextFigure) e;

	    showThieves = temp.getShowThieves();
	    // konstanten für GV?!
	    doubleAllowed = temp.useDoubleTicketIsAllowed();
	    currentStation = temp.getPosition();
	    currentFigureID = temp.getFigureId();
	    reachableLinks = temp.getReachableLinks();
	    if (reachableLinks.keySet().isEmpty()) {
		this.move = new Move("none", currentStation, false);
		return;
	    }
	    keyList.clear();
	    keyList.addAll(reachableLinks.keySet());
	    setMove(null);
	}

    }

    /**
     * Not needed in random KI
     * 
     */
    @Override
    public void stopComputing() {
    }

}
