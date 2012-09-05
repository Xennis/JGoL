/**
 * 
 */
package logic;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import logic.figure.FigurePoliceStub;
import logic.figure.FigureThiefStub;
import logic.figure.IAFigure;
import logic.figure.IFigurePolice;
import logic.figure.IFigureThief;
import logic.player.IAPlayer;
import logic.player.PlayerPoliceStub;
import logic.player.PlayerThiefStub;
import events.IAEvent;
import gameview.sView.ISPlayer;
import gameview.sView.SPlayerStubPolice;
import gameview.sView.SPlayerStubThief;

/**
 * @author Fabian
 * 
 */
public class ActorControllerStub implements IActorController {

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#addPlayerWithFigures(gameView.sView.ISPlayer,
     * int, java.util.Map, java.awt.image.BufferedImage)
     */
    @Override
    public void addPlayerWithFigures(ISPlayer playerDesc, int gameViewID,
	    Map<String, Integer> tokens, BufferedImage icon) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#removeFigure(logic.figure.IAFigure)
     */
    @Override
    public void removeFigure(IAFigure figure) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#setStartAt(java.util.List)
     */
    @Override
    public void setStartAt(List<String> startAt) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#removePlayer(int)
     */
    @Override
    public void removePlayer(int playerID) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getNextFigure()
     */
    @Override
    public IAFigure getNextFigure() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getCurrentFigure()
     */
    @Override
    public IAFigure getCurrentFigure() {
	return new FigureThiefStub();
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigureQueue()
     */
    @Override
    public List<Integer> getFigureQueue() {
	List<Integer> figureQueue = new LinkedList<Integer>();
	figureQueue.add(0);
	figureQueue.add(1);
	return figureQueue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#destroyCurrentGameState()
     */
    @Override
    public void destroyCurrentGameState() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigureList()
     */
    @Override
    public List<IAFigure> getFigureList() {
	List<IAFigure> figureList = new LinkedList<IAFigure>();
	figureList.add(new FigureThiefStub());
	figureList.add(new FigurePoliceStub());
	return figureList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getPlayerPoliceNameList()
     */
    @Override
    public List<String> getPlayerPoliceNameList() {
	List<String> playerNames = new LinkedList<String>();
	playerNames.add(new SPlayerStubPolice().getName());
	return playerNames;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getPlayerThiefNameList()
     */
    @Override
    public List<String> getPlayerThiefNameList() {
	List<String> playerNames = new LinkedList<String>();
	playerNames.add(new SPlayerStubThief().getName());
	return playerNames;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#setCurrentQueuePosition(int)
     */
    @Override
    public void setCurrentQueuePosition(int currentQueuePosition) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getNumPlayer()
     */
    @Override
    public int getNumPlayer() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public List<IAPlayer> getPlayerList() {
	List<IAPlayer> playerList = new LinkedList<IAPlayer>();
	playerList.add(new PlayerPoliceStub());
	playerList.add(new PlayerThiefStub());
	return playerList;
    }

    @Override
    public void processEvent(IAEvent e) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigureThiefList()
     */
    @Override
    public List<IFigureThief> getFigureThiefList() {
	List<IFigureThief> figureThiefList = new LinkedList<IFigureThief>();
	figureThiefList.add(new FigureThiefStub());
	return figureThiefList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IActorController#getFigurePoliceList()
     */
    @Override
    public List<IFigurePolice> getFigurePoliceList() {
	// TODO Auto-generated method stub
	return null;
    }

}
