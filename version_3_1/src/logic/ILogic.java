package logic;

import java.util.List;
import java.util.Map;

import events.IAEvent;

/**
 * The Interface to the logic
 * 
 * @author Raphael A., Jasper S., Fabian R., Felix
 *         K.
 * @version 3.0
 */
public interface ILogic {

    public abstract void checkCollisions();

    /**
     * Manage events fired by GameView.
     * 
     * @param e
     *            {@link events.IAEvent} event
     */
    public abstract void gVEventHappend(IAEvent e);

    /**
     * Initialize game logic.
     * 
     * @param language
     *            {@link java.util.Map} with text translation
     * @param languageNames
     *            {@link java.util.List} with all language names
     */
    public abstract void init(Map<String, String> language,
	    List<String> languageNames);

    /**
     * Process all logic events.
     * 
     * @param e The logic event
     */
    public abstract void logicEventHappend(IAEvent e);

    /**
     * onRender is called every mainloop and all subclasses are called, can be
     * used to do some stuff every mainloop (every 100ms in normal
     * configuration).
     */
    public abstract void onRender();

    /**
     * processEvents is called every mainloop, all events which are queued where
     * ever will be processed
     */
    public abstract void processEvents();

    // -----------------GAMEVIEW GLOBAL
    // EVENTs-------------------------------------------------------

    /**
     * Manage all global events.
     * 
     * @param e
     *            {@link events.IAEvent} event
     */
    public abstract void processGameViewGlobalEvent(IAEvent e);

    // ----------------------------GAME VIEW
    // EVENTS----------------------------------------

    /**
     * Manage all GameView events.
     * 
     * @param e
     *            {@link events.IAEvent} event
     */
    public abstract void processGVEvent(IAEvent e);

    /**
     * Manage all GameView events.
     * 
     * @param e
     *            {@link events.IAEvent} event
     */
    public abstract void queueGVEvent(IAEvent e);

}