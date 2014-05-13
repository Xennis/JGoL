/**
 * 
 */
package gameview.sView;

import logic.Constants;

/**
 * @author Fabian
 * 
 */
public class SPlayerStubThief implements ISPlayer {

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getName()
     */
    @Override
    public String getName() {
	return "Peter";
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getType()
     */
    @Override
    public int getType() {
	return Constants.THIEF_PLAYER_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getColor()
     */
    @Override
    public String getColor() {
	return "red";
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getFigureCount()
     */
    @Override
    public int getFigureCount() {
	return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getIcon()
     */
    @Override
    public int getIcon() {
	return 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getBotOrPlayer()
     */
    @Override
    public int getBotOrPlayer() {
	return Constants.TYPE_PLAYER;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#setBotOrPlayer(int)
     */
    @Override
    public void setBotOrPlayer(int botOrPlayer) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#isKiPlayer()
     */
    @Override
    public boolean isKiPlayer() {
	// TODO Auto-generated method stub
	return false;
    }

}
