package logic.figure;

import java.awt.image.BufferedImage;
import java.util.Map;

import logic.player.IAPlayer;

import events.IAEvent;

/**
 * This class holds specific information about a police figure in the game.
 * 
 * @author Fabian R.
 * @version 3.0
 * 
 */
public class FigurePolice extends AFigure implements IFigurePolice {

    /**
     * Create a new figure of the type police.
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
    public FigurePolice(IAPlayer owner, int figureID, String position,
	    Map<String, Integer> tokens, BufferedImage icon) {
	super(owner, figureID, position, tokens, icon);
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IFigurePolice#processEvent(events.IAEvent)
     */
    @Override
    public void processEvent(IAEvent e) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#isThief()
     */
    @Override
    public boolean isThief() {
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see logic.figure.IAFigure#isThief()
     */
    @Override
    public boolean isPolice() {
	return true;
    }

}
