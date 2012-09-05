/**
 * 
 */
package gameview;

import core.msgpump.IMsgPump;
import logic.Constants;
import events.IAEvent;

/**
 * @author Fabian
 * 
 */
public class PlayerGameViewStub implements IGameView {

    /*
     * (non-Javadoc)
     * 
     * @see gameView.IGameView#processEvent(events.IAEvent)
     */
    @Override
    public void processEvent(IAEvent e) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.IGameView#init()
     */
    @Override
    public void init() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.IGameView#onRender()
     */
    @Override
    public void onRender() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.IGameView#getViewType()
     */
    @Override
    public int getViewType() {
	return Constants.TYPE_PLAYER;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.IGameView#getViewID()
     */
    @Override
    public int getViewID() {
	// TODO Auto-generated method stub
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see gameView.IGameView#getMsgPump()
     */
    @Override
    public IMsgPump getMsgPump() {
	// TODO Auto-generated method stub
	return null;
    }

}
