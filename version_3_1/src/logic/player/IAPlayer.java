package logic.player;

import java.awt.Color;
import java.util.List;
import logic.figure.IAFigure;

import events.IAEvent;

/**
 * The interface to APlayer
 * 
 * @author Jasper S.
 * @version 3.0
 * 
 */
public interface IAPlayer {

    /**
     * 
     * @param figure
     *            figure object
     */
    public abstract void addFigure(IAFigure figure);

    /**
     * Destroy a figure of the player
     * 
     * @param figureID
     *            identification integer of the figure
     */
    public abstract void destroyFigure(int figureID);

    /**
     * Process a {@link events.IAEvent}.
     * 
     * @param e A event
     */
    public abstract void processEvent(IAEvent e);

    /**
     * Get name of the player.
     * 
     * @return player name
     */
    public abstract String getName();

    /**
     * Get color of the player.
     * 
     * @return player color
     */
    public abstract Color getColor();

    /**
     * Get identification integer of the player.
     * 
     * @return player identification integer
     */
    public abstract int getId();

    /**
     * Get player type as integer.
     * 
     * @see logic.player.IAPlayer
     * @return player type as integer
     */
    public abstract int getType();

    /**
     * Get a list of all figures of the player.
     * 
     * @return list of all figures
     */
    public abstract List<IAFigure> getFigures();

    /**
     * Get the color name of the player as String.
     * 
     * @return Color name as string
     */
    public abstract String getColorName();

    /**
     * Check player is thief player.
     * 
     * @return true, if player is thief player
     */
    public abstract boolean isThief();

    /**
     * Check player is police player.
     * 
     * @return true, if player is police player
     */
    public abstract boolean isPolice();

    /**
     * Check player has more then one figure.
     * 
     * @return True, if player has more then one figure
     */
    public abstract boolean hasFigures();

    /**
     * Returns the id of the owner of the game View.
     * 
     * @return gameView id (int)
     */
    public abstract int getOwnerGV();

}