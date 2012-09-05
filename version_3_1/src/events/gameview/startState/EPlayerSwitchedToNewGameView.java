package events.gameview.startState;

import events.AEvent;

/**
 * If the game configuration is ready and all information is send the state will
 * be changed when this event is processed.
 * 
 * @author Raphael A.
 * 
 */
public class EPlayerSwitchedToNewGameView extends AEvent {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


    /**
     * Create Event to switch to new gameView.
     * 
     * @param source
     *            Event source
     * @param eventType
     *            Event id integer
     */
    public EPlayerSwitchedToNewGameView(Object source, int eventType) {
	super(source, eventType);
    }

}
