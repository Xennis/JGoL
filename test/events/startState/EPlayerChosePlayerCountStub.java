/**
 * 
 */
package events.startState;

import events.gameview.startState.IEPlayerChosePlayerCount;

/**
 * @author Fabian
 * 
 */
public class EPlayerChosePlayerCountStub implements IEPlayerChosePlayerCount {

    /*
     * (non-Javadoc)
     * 
     * @see events.gameView.startState.IEPlayerChosePlayerCount#getPlayerCount()
     */
    @Override
    public int getPlayerCount() {
	return 2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * events.gameView.startState.IEPlayerChosePlayerCount#getSpecialTicketArray
     * ()
     */
    @Override
    public Integer[] getSpecialTicketArray() {
	Integer[] specialTicketArray = new Integer[4];
	specialTicketArray[0] = 3;
	specialTicketArray[1] = 3;
	specialTicketArray[2] = 0;
	specialTicketArray[3] = 0;
	return specialTicketArray;
    }

}
