/**
 * 
 */
package events.gameview.gameState;

import events.EventConstants;

/**
 * @author Fabian
 * 
 */
public class EPlayerRequestedMoveStub implements IEPlayerRequestedMove {

    /*
     * (non-Javadoc)
     * 
     * @see events.gameView.gameState.IEPlayerRequestedMove#getdestination()
     */
    @Override
    public String getDestination() {
	return "22";
    }

    /*
     * (non-Javadoc)
     * 
     * @see events.gameView.gameState.IEPlayerRequestedMove#getTicketType()
     */
    @Override
    public String getTicketType() {
	return "car";
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
	return EventConstants.PLAYER_REQUESTED_MOVE;
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

    /*
     * (non-Javadoc)
     * 
     * @see events.gameView.gameState.IEPlayerRequestedMove#usedDoubleTicket()
     */
    @Override
    public boolean usedDoubleTicket() {
	return false;
    }

}
