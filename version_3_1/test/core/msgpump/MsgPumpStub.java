/**
 * 
 */
package core.msgpump;

import gameview.generalView.MainWindowListener;

import javax.swing.JComponent;
import javax.swing.JMenuBar;

/**
 * @author Fabian
 * 
 */
public class MsgPumpStub implements IMsgPump {

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#hasMsg()
     */
    @Override
    public boolean hasMsg() {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#getNextMsg()
     */
    @Override
    public Message getNextMsg() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#addComponent(javax.swing.JComponent)
     */
    @Override
    public void addComponent(JComponent component) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#removeComponent(javax.swing.JComponent)
     */
    @Override
    public void removeComponent(JComponent component) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logInfo(java.lang.String)
     */
    @Override
    public void logInfo(String infoString) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logDebug(java.lang.String)
     */
    @Override
    public void logDebug(String debugString) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logError(java.lang.String)
     */
    @Override
    public void logError(String errorString) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logInfoSource(java.lang.String,
     * java.lang.String)
     */
    @Override
    public void logInfoSource(String source, String input) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#requestShutdown()
     */
    @Override
    public void requestShutdown() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#switchToStartState()
     */
    @Override
    public void switchToStartState() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#switchtoGameState()
     */
    @Override
    public void switchtoGameState() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#switchToEndState()
     */
    @Override
    public void switchToEndState() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * core.msgpump.IMsgPump#addListener(gameView.generalView.MainWindowListener
     * )
     */
    @Override
    public void addListener(MainWindowListener l) {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#addMenuBar(javax.swing.JMenuBar)
     */
    @Override
    public void addMenuBar(JMenuBar menubar) {
	// TODO Auto-generated method stub

    }

}
