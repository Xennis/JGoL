package gameview.sView;

/**
 * The SPlayer Interface
 * 
 * @author Raphael A.
 * @version 3.0
 */
public interface ISPlayer {

	/**
	 * Get id player or bot
	 * @return id player or bot
	 */
    public abstract int getBotOrPlayer();

    /**
     * Get player color.
     * @return player color
     */
    public abstract String getColor();

    /**
     * Get number of figures.
     * 
     * @return number of figures
     */
    public abstract int getFigureCount();

    /**
     * Get figure icon.
     * 
     * @return figure icon
     */
    public abstract int getIcon();

    /**
     * Get player name
     * 
     * @return player name
     */
    public abstract String getName();

    /**
     * Get player type.
     * 
     * @return player type
     */
    public abstract int getType();

    /**
     * Check player is ki player.
     * 
     * @return True, if is ki player
     */
    public abstract boolean isKiPlayer();

    /**
     * Set player is human or bot
     * 
     * @param botOrPlayer ki or human id
     */
    public abstract void setBotOrPlayer(int botOrPlayer);

}