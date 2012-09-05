/**
 * 
 */
package gameview;

import core.msgpump.IMsgPump;
import core.msgpump.MsgPumpStub;
import logic.Constants;
import events.IAEvent;

/**
 * Stub for {@link gameview.KIGameView}
 * 
 * @author Fabian
 * 
 */
public class KIGameViewStubHard implements IKiGameView {

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
	return Constants.TYPE_KI_EASY;
    }

    @Override
    public int getViewID() {
	// TODO Auto-generated method stub
	return 0;
    }

    @Override
    public boolean isActive() {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public IMsgPump getMsgPump() {
	// TODO Auto-generated method stub
	return new MsgPumpStub();
    }

    @Override
    public void destroy() {
	// TODO Auto-generated method stub

    }

    @Override
    public void setActive(boolean isActive) {
	// TODO Auto-generated method stub

    }

}
