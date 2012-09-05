/**
 * 
 */
package gameview.sView;

import logic.Constants;

/**
 * Stub for {@link gameview.sView.SPlayer}: name=Hans, police
 * 
 * @author Fabian
 * 
 */
public class SPlayerStubPolice implements ISPlayer {

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getName()
     */
    @Override
    public String getName() {
	return "Hans";
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getType()
     */
    @Override
    public int getType() {
	return Constants.POLICE_PLAYER_ID;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.sView.ISPlayer#getColor()
     */
    @Override
    public String getColor() {
	return "yellow";
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
	return 0;
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
	return false;
    }

}
