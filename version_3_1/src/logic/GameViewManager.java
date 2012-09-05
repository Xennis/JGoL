package logic;

import events.IAEvent;
import gameview.IGameView;
import gameview.IKiGameView;

import java.util.*;

import core.msgpump.IMsgPump;

/**
 * 
 * @author Jasper S.
 * @version 3.0
 */
public class GameViewManager implements IGameViewManager {

    private List<IGameView> gVList;
    private List<IAEvent> eventList;
    private IMsgPump msgPump;

    public GameViewManager(IMsgPump msgPump) {
	gVList = new LinkedList<IGameView>();
	eventList = new LinkedList<IAEvent>();
	this.msgPump = msgPump;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#queueEvent(events.AEvent)
     */
    @Override
    public synchronized void queueEvent(IAEvent e) {
	eventList.add(e);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#removeGameView(int)
     */
    @Override
    public void removeGameView(int gameViewID) {
	eventList.remove(gameViewID);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#addGameView(gameView.AGameView)
     */
    @Override
    public void addGameView(IGameView newGameView) {
	gVList.add(newGameView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#onRender()
     */
    @Override
    public void onRender() {
	for (IGameView i : gVList) {
	    i.onRender();
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#processEvents()
     */
    @Override
    public synchronized void processEvents() {
	for (IGameView tempGV : gVList) {
	    try {
		for (IAEvent e : eventList) {
		    if (!e.isProcessed()) {
			tempGV.processEvent(e);
			msgPump.logInfo("Verarbeite Event " + e.getEventType()
				+ " schicke es an " + tempGV.toString());
		    }
		}
	    } catch (ConcurrentModificationException e) {
		msgPump.logError(this.getClass().getName() + ": "
			+ e.getMessage());
	    }
	}
	eventList.clear();

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#getEventList()
     */
    @Override
    public synchronized List<IAEvent> getEventList() {
	return eventList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#getGameViewList()
     */
    @Override
    public List<IGameView> getGameViewList() {
	return gVList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IGameViewManager#removeAllKI()
     */
    @Override
    public void removeAllKI() {
	List<IGameView> gVListClone = new LinkedList<IGameView>(gVList);
	for (IGameView tempGV : gVListClone) {
	    // msgPump.logDebug("Gucke ob " + tempGV.toString() +
	    // " gelöscht werden kann " + tempGV.getViewType());
	    if (tempGV.getViewType() == Constants.TYPE_KI_EASY
		    || tempGV.getViewType() == Constants.TYPE_KI_HARD) {
		msgPump.logDebug("Versuche zu löschen: " + tempGV.toString());
		((IKiGameView) tempGV).destroy();
		gVList.remove(tempGV);
	    }
	}
	msgPump.logDebug("GVLIST: " + gVList);
    }

}
