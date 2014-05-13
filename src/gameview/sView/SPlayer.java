package gameview.sView;

import logic.Constants;

/**
 * This class is an container for the player settings. The created object will
 * be send by the NewPlayerSetPropertiesEvent.
 * 
 * @author Raphael A.
 * @version 3.0
 * 
 */
public class SPlayer implements ISPlayer {

    private final String name;
    private final int type;
    private final String color;
    private final int figureCount;
    private final int icon;
    private int botOrPlayer;

    /**
     * The constructor.
     * 
     * @param name
     *            Player name
     * @param type
     *            Player type as Integer
     * @param color
     *            Player color as String
     * @param figureCount
     *            Number of figures
     * @param icon
     *            Player icon
     */
    SPlayer(final String name, final int type, final String color,
	    final int figureCount, final int icon) {
	this.name = name;
	this.type = type;
	this.color = color;
	this.figureCount = figureCount;
	this.icon = icon;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getBotOrPlayer()
     */
    @Override
    public int getBotOrPlayer() {
	return botOrPlayer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getColor()
     */
    @Override
    public String getColor() {
	return color;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getFigureCount()
     */
    @Override
    public int getFigureCount() {
	return figureCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getIcon()
     */
    @Override
    public int getIcon() {
	return icon;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getName()
     */
    @Override
    public String getName() {
	return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getType()
     */
    @Override
    public int getType() {
	return type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#isKiPlayer()
     */
    @Override
    public boolean isKiPlayer() {
	if (botOrPlayer == Constants.TYPE_KI_EASY
		|| botOrPlayer == Constants.TYPE_KI_HARD) {
	    return true;
	} else {
	    return false;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#setBotOrPlayer(int)
     */
    @Override
    public void setBotOrPlayer(final int botOrPlayer) {
	this.botOrPlayer = botOrPlayer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#toString()
     */
    @Override
    public String toString() {
	return "SPlayer [name=" + name + ", type=" + type + ", color=" + color
		+ ", figureCount=" + figureCount + ", icon=" + icon
		+ "botOrPlayer" + botOrPlayer + "]";
    }

}
