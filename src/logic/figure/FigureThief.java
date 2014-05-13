package logic.figure;

import java.awt.image.BufferedImage;
import java.util.Map;

import logic.player.IAPlayer;

import events.EventConstants;
import events.IAEvent;
import events.gameview.gameState.IEPlayerRequestedMove;

/**
 * This class holds specific information about a thief figure in the game.
 * 
 * @author Fabian R.
 * @version 3.0
 * 
 */
public class FigureThief extends AFigure implements IFigureThief {

    /**
     * Create a new figure of the type thief.
     * 
     * @param owner
     *            owner of the figure
     * @param figureID
     *            figure identification integer
     * @param position
     *            start position on the map
     * @param tokens
     *            start tokens, ticket types as identifier and number of this
     *            type as value
     * @param icon
     *            figure icon
     */
    public FigureThief(IAPlayer owner, int figureID, String position,
	    Map<String, Integer> tokens, BufferedImage icon) {
	super(owner, figureID, position, tokens, icon);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IFigureThief#addTicket(java.lang.String)
     */
    @Override
    public void addTicket(String ticket) {
	tokens.put(ticket, tokens.get(ticket) + 1);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#isThief()
     */
    @Override
    public boolean isPolice() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#isThief()
     */
    @Override
    public boolean isThief() {
	return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.IFigureThief#processEvent(events.IAEvent)
     */
    @Override
    public void processEvent(IAEvent e) {
	if (e.getEventType() == EventConstants.PLAYER_REQUESTED_MOVE) {
	    IEPlayerRequestedMove ee = (IEPlayerRequestedMove) e;
	    this.addTicket(ee.getTicketType());
	}
    }

}
