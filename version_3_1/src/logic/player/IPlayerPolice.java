package logic.player;

import events.IAEvent;

/**
 * Interface to PlayerPolice
 * 
 * @author Jasper S.
 * @version 3.0
 */
public interface IPlayerPolice extends IAPlayer {

    /**
     * Process a {@link events.IAEvent}.
     * 
     * @param e A event
     */
    public abstract void processEvent(IAEvent e);

}