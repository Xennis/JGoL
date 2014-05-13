/**
 * 
 */
package events.logic.gameState;

import events.EventConstants;

/**
 * @author Fabian
 * 
 */
public class EPlayerMovedStub implements IEPlayerMoved {

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.IEPlayerMoved#getNewPosition()
     */
    @Override
    public String getNewPosition() {
	return "22";
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.logic.gameState.IEPlayerMoved#getTicketType()
     */
    @Override
    public String getTicketType() {
	return "walk";
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.IAEvent#getSource()
     */
    @Override
    public Object getSource() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.IAEvent#getEventType()
     */
    @Override
    public int getEventType() {
	return EventConstants.PLAYER_MOVED_EVENT;
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.IAEvent#processed()
     */
    @Override
    public void processed() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see events.IAEvent#isProcessed()
     */
    @Override
    public boolean isProcessed() {
	return false;
    }

}
