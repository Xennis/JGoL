package logic;

import events.IAEvent;
import gameview.sView.ISPlayer;

import java.awt.image.BufferedImage;
import java.util.*;

import logic.figure.*;
import logic.player.*;

/**
 * 
 * @author Raphael A.,Jasper S.
 * @version 3.0
 * 
 */
public class ActorController implements IActorController {

    List<IAPlayer> playerList;
    List<IAFigure> figureList;
    List<String> startAt;

    private int nextFigureID;
    private int nextPlayerID;

    private int currentQueuePosition;

    public ActorController() {
	playerList = new LinkedList<IAPlayer>();
	figureList = new LinkedList<IAFigure>();
	startAt = new LinkedList<String>();

	nextFigureID = 0;
	nextPlayerID = 0;

	currentQueuePosition = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#addPlayerWithFigures(gameView.sView.ISPlayer,
     * int, java.util.Map, java.awt.image.BufferedImage)
     */
    @Override
    public void addPlayerWithFigures(ISPlayer playerDesc, int gameViewID,
	    Map<String, Integer> tokens, BufferedImage icon) {
	APlayer tempPlayer = null;
	if (playerDesc.getType() == Constants.THIEF_PLAYER_ID) {
	    tempPlayer = new PlayerThief(playerDesc.getName(),
		    playerDesc.getColor(), nextPlayerID++,
		    playerDesc.getType(), gameViewID);

	} else if (playerDesc.getType() == Constants.POLICE_PLAYER_ID) {
	    tempPlayer = new PlayerPolice(playerDesc.getName(),
		    playerDesc.getColor(), nextPlayerID++,
		    playerDesc.getType(), gameViewID);

	}
	playerList.add(tempPlayer);
	for (int i = 0; i < playerDesc.getFigureCount(); i++) {
	    addFigure(tempPlayer.getId(), tokens, icon);
	}
    }

    /**
     * Add a figure to a player
     * 
     * @param playerID
     *            the player id
     * @param tokens
     *            the tokens the figure should have
     * @param icon
     *            the icon of the figure
     */
    private void addFigure(int playerID, Map<String, Integer> tokens,
	    BufferedImage icon) {
	AFigure tempFigure = null;

	for (IAPlayer tempPlayer : playerList) {
	    if (tempPlayer.getId() == playerID) {
		int randomPosition = (int) (Math.random() * (startAt.size() - 1));
		if (tempPlayer.isThief()) {
		    tempFigure = new FigureThief(tempPlayer, nextFigureID++,
			    startAt.get(randomPosition), tokens, icon);
		    figureList.add(0, tempFigure);
		} else if (tempPlayer.isPolice()) {
		    tempFigure = new FigurePolice(tempPlayer, nextFigureID++,
			    startAt.get(randomPosition), tokens, icon);
		    figureList.add(tempFigure);
		}
		startAt.remove(randomPosition);
		break;
	    }
	}
	tempFigure.getOwner().addFigure(tempFigure);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#removeFigure(logic.figure.IAFigure)
     */
    @Override
    public void removeFigure(IAFigure figure) {
	figure.getOwner().destroyFigure(figure.getId());
	figureList.remove(figure);
	currentQueuePosition--;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#setStartAt(java.util.List)
     */
    @Override
    public void setStartAt(List<String> startAt) {
	this.startAt.clear();
	this.startAt.addAll(startAt);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#removePlayer(int)
     */
    @Override
    public void removePlayer(int playerId) {

	for (IAFigure tempFigure : figureList) {
	    if (tempFigure.getOwner().getId() == playerId) {
		figureList.remove(tempFigure);
	    }
	}
	playerList.remove(playerId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getNextFigure()
     */
    @Override
    public IAFigure getNextFigure() {
	if (currentQueuePosition >= figureList.size()
		|| currentQueuePosition < 0) {
	    currentQueuePosition = 0;
	}
	return figureList.get(currentQueuePosition++);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getCurrentFigure()
     */
    @Override
    public IAFigure getCurrentFigure() {
	return figureList.get(currentQueuePosition);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigureQueue()
     */
    @Override
    public List<Integer> getFigureQueue() {
	List<Integer> list = new LinkedList<Integer>();
	for (IAFigure figure : figureList) {
	    list.add(figure.getId());
	}
	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#destroyCurrentGameState()
     */
    @Override
    public void destroyCurrentGameState() {
	playerList.clear();
	figureList.clear();
	startAt.clear();

	nextFigureID = 0;
	nextPlayerID = 0;

	currentQueuePosition = 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#processEvent(events.AEvent)
     */
    @Override
    public void processEvent(IAEvent e) {
	for (IAFigure tempFig : figureList) {
	    tempFig.processEvent(e);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigureList()
     */
    @Override
    public List<IAFigure> getFigureList() {
	return figureList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getPlayerPoliceNameList()
     */
    @Override
    public List<String> getPlayerPoliceNameList() {
	List<String> playerPoliceList = new LinkedList<String>();
	for (IAPlayer player : playerList) {
	    if (player.isPolice()) {
		playerPoliceList.add(player.getName());
	    }
	}
	return playerPoliceList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getPlayerThiefNameList()
     */
    @Override
    public List<String> getPlayerThiefNameList() {
	List<String> playerThiefList = new LinkedList<String>();
	for (IAPlayer player : playerList) {
	    if (player.isThief()) {
		playerThiefList.add(player.getName());
	    }
	}
	return playerThiefList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigureThiefList()
     */
    @Override
    public List<IFigureThief> getFigureThiefList() {
	List<IFigureThief> list = new LinkedList<IFigureThief>();
	for (IAFigure figure : figureList) {
	    if (figure.isThief()) {
		list.add((IFigureThief) figure);
	    }
	}
	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigurePoliceList()
     */
    @Override
    public List<IFigurePolice> getFigurePoliceList() {
	List<IFigurePolice> list = new LinkedList<IFigurePolice>();
	for (IAFigure figure : figureList) {
	    if (figure.isPolice()) {
		list.add((IFigurePolice) figure);
	    }
	}
	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#setCurrentQueuePosition(int)
     */
    @Override
    public void setCurrentQueuePosition(int currentQueuePosition) {
	this.currentQueuePosition = currentQueuePosition;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getNumPlayer()
     */
    @Override
    public int getNumPlayer() {
	return this.playerList.size();
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getPlayerList()
     */
    @Override
    public List<IAPlayer> getPlayerList() {
	return playerList;
    }
}
