package logic.player;

import events.IAEvent;

/**
 * The interface to PlayerThief
 * 
 * @author Jasper S.
 * @version 3.0
 * 
 */
public interface IPlayerThief extends IAPlayer {

    /**
     * Process a {@link events.IAEvent}.
     * 
     * @param e A event
     */
    public abstract void processEvent(IAEvent e);

}