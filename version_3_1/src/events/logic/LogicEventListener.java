package events.logic;

import events.IAEvent;

/**
 * Listener for all logic events and calls the logicEventHappend method
 * 
 * @author Jasper S.
 * @version 1.0
 */
public interface LogicEventListener {

	/**
	 * Process logic events.
	 * 
	 * @param e A logic event
	 */
    public void logicEventHappend(IAEvent e);

}
