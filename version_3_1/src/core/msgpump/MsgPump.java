package core.msgpump;

import gameview.generalView.MainWindowListener;

import java.util.LinkedList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenuBar;

public class MsgPump implements IMsgPump {

    List<Message> messageList;

    /**
     * Get a MessagePump
     * 
     */
    public MsgPump() {
	messageList = new LinkedList<Message>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#hasMsg()
     */
    @Override
    public synchronized boolean hasMsg() {
	if (messageList.isEmpty()) {
	    return false;
	} else {
	    return true;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#getNextMsg()
     */
    @Override
    public synchronized Message getNextMsg() {
	if (!messageList.isEmpty() /* && !messageList.get(0).equals(null) */) {
	    Message tempMsg = messageList.get(0);
	    messageList.remove(0);
	    return tempMsg;
	} else {
	    return new Message(Integer.MAX_VALUE, null, null);
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#addComponent(javax.swing.JComponent)
     */
    @Override
    public synchronized void addComponent(JComponent component) {
	messageList.add(new Message(Message.ADD_COMPONENT, component, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#removeComponent(javax.swing.JComponent)
     */
    @Override
    public synchronized void removeComponent(JComponent component) {
	messageList.add(new Message(Message.REMOVE_COMPONENT, component, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logInfo(java.lang.String)
     */
    @Override
    public synchronized void logInfo(String infoString) {
	messageList.add(new Message(Message.LOG_INFO, infoString, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logDebug(java.lang.String)
     */
    @Override
    public synchronized void logDebug(String debugString) {
	messageList.add(new Message(Message.LOG_DEBUG, debugString, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logError(java.lang.String)
     */
    @Override
    public synchronized void logError(String errorString) {
	messageList.add(new Message(Message.LOG_ERROR, errorString, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#logInfoSource(java.lang.String,
     * java.lang.String)
     */
    @Override
    public synchronized void logInfoSource(String source, String input) {
	messageList.add(new Message(Message.LOG_INFO_TWO_PARAM, source, input));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#requestShutdown()
     */
    @Override
    public synchronized void requestShutdown() {
	messageList.add(new Message(Message.SHUTDOWN_REQUEST, null, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#switchToStartState()
     */
    @Override
    public synchronized void switchToStartState() {
	messageList.add(new Message(Message.SWITCH_TO_STARTSTATE, null, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#switchtoGameState()
     */
    @Override
    public synchronized void switchtoGameState() {
	messageList.add(new Message(Message.SWITCH_TO_GAMESTATE, null, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#switchToEndState()
     */
    @Override
    public synchronized void switchToEndState() {
	messageList.add(new Message(Message.SWITCH_TO_ENDSTATE, null, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * core.msgpump.IMsgPump#addListener(gameView.generalView.MainWindowListener
     * )
     */
    @Override
    public synchronized void addListener(MainWindowListener l) {
	messageList.add(new Message(Message.ADD_LISTENER, l, null));
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.msgpump.IMsgPump#addMenuBar(javax.swing.JMenuBar)
     */
    @Override
    public synchronized void addMenuBar(JMenuBar menubar) {
	messageList.add(new Message(Message.ADD_MENUBAR, menubar, null));
    }

}
