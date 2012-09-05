package core.msgpump;

import gameview.generalView.MainWindowListener;

import javax.swing.JComponent;
import javax.swing.JMenuBar;

/**
 * Similar to the event system, used to deliver events to the game application
 * layer. Used for logging and basic user interface control. Nothing else. Used
 * are hasMsg and getNextMsg methods very similar to a iterator.
 * 
 * @author Jasper S.
 * @version 2.0
 * 
 */
public interface IMsgPump {

    /**
     * Returns true if the iteration has more elements. (In other words, returns
     * true if getNextMsg would return an element rather than throwing an
     * exception.)
     * 
     * @return true if the iterator has more elements.
     */
    public abstract boolean hasMsg();

    /**
     * Returns the next element in the iteration.
     * 
     * @return the next element in the iteration.
     */
    public abstract Message getNextMsg();

    /**
     * A JComponent must be added to the MainFrame to be visible, since the
     * MainFrame is part of the Game Application Layer (core) there must be a
     * way to communicate the component to the MainFrame. This is done by this
     * method.
     * 
     * @param component
     *            the JComponent which should be added to the MainFrame
     */
    public abstract void addComponent(JComponent component);

    /**
     * Remove a JComponent from the MainFrame.
     * 
     * @param component
     *            the JComponent that should be removed
     */
    public abstract void removeComponent(JComponent component);

    /**
     * The Logger is part of the Game Application Layer. Since we don't want to
     * pass the Logger to every single Class of this project we schedule the
     * logging over the MsgPump. Due to this the logging is very flexible in
     * terms of logging to console or file and logging levels, and can not be
     * destroyed by any object run wild.
     * 
     * Info's are messages that arn't realy needed, debuginformations that arn't
     * needed for debugging anymore / right now are switched to info, and
     * initialization informations are passed here.
     * 
     * @param infoString
     *            the Message to be displayed in the log.
     */
    public abstract void logInfo(String infoString);

    /**
     * The Logger is part of the Game Application Layer. Since we don't want to
     * pass the Logger to every single Class of this project we schedule the
     * logging over the MsgPump. Due to this the logging is very flexible in
     * terms of logging to console or file and logging levels, and can not be
     * destroyed by any object run wild.
     * 
     * Debug's are messages that are used while development and game testing.
     * Neat way to keep track of everything that is of interest for the
     * programmer.
     * 
     * @param debugString
     *            the message to be displayed in the log.
     */
    public abstract void logDebug(String debugString);

    /**
     * The Logger is part of the Game Application Layer. Since we don't want to
     * pass the Logger to every single Class of this project we schedule the
     * logging over the MsgPump. Due to this the logging is very flexible in
     * terms of logging to console or file and logging levels, and can not be
     * destroyed by any object run wild.
     * 
     * Errors are messages that give important information about the state of
     * the game. The purpose is to inform about problems that arn't fixed in the
     * current release. The releases of the game will by default only log error
     * messages.
     * 
     * @param errorString
     *            the message to be displayed in the log.
     */
    public abstract void logError(String errorString);

    /**
     * The Logger is part of the Game Application Layer. Since we don't want to
     * pass the Logger to every single Class of this project we schedule the
     * logging over the MsgPump. Due to this the logging is very flexible in
     * terms of logging to console or file and logging levels, and can not be
     * destroyed by any object run wild.
     * 
     * Info's are messages that arn't realy needed, debuginformations that arn't
     * needed for debugging anymore / right now are switched to info, and
     * initialization informations are passed here. This method was implanted a
     * long time ago and isn't realy of use right now. But does not have any
     * problems with security so no deprecation is needed.
     * 
     * @param source
     *            the source of the log
     * @param input
     *            and the input message
     */
    public abstract void logInfoSource(String source, String input);

    /**
     * Request a clean shutdown of the game
     * 
     */
    public abstract void requestShutdown();

    /**
     * Switch to the initial state of the game
     * 
     */
    public abstract void switchToStartState();

    /**
     * Access the gamestate only when all informations are stored in the logic.
     */
    public abstract void switchtoGameState();

    /**
     * The endState, end of the game, from here you can go to the startstate
     */
    public abstract void switchToEndState();

    /**
     * 
     * Add a listener to the MainFrame
     * 
     * @param l
     *            the listener
     */
    public abstract void addListener(MainWindowListener l);

    /**
     * Same as add Listener or addComponent, just for menubars
     * 
     * @param menubar
     *            the JMenubar to be added to the Mainframe.
     */
    public abstract void addMenuBar(JMenuBar menubar);

}