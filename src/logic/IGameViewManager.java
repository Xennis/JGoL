package logic;

import java.util.List;

import events.IAEvent;
import gameview.IGameView;

/**
 * Interface to the GameViewManager
 * 
 * @author Jasper S.
 * @version 3.0
 * 
 */
public interface IGameViewManager {

    /**
     * Queue all events
     * 
     * @param e A event
     */
    public abstract void queueEvent(IAEvent e);

    /**
     * Remove a gameView form manager.
     * 
     * @param gameViewID gameView to remove
     */
    public abstract void removeGameView(int gameViewID);

    /**
     * Add a gameView to manager.
     * 
     * @param newGameView gameView to add
     */
    public abstract void addGameView(IGameView newGameView);

    /**
	 * On render method.
	 */
    public abstract void onRender();

    /**
	 * Process the events.
	 */
    public abstract void processEvents();

    /**
     * @return {@link java.util.List} with all {@link events.IAEvent} of the
     *         game view
     */
    public abstract List<IAEvent> getEventList();
    
    /**
     * Get a list with all gameViews
     * 
     * @return list with all gameViews
     */
    public abstract List<IGameView> getGameViewList();

    /**
     * Remove all ki gameViews.
     */
    public abstract void removeAllKI();

}