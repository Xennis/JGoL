package core.msgpump;

/**
 * Contains the constants which are the identifier for the type of the message.
 * 
 * @author Jasper S.
 * @version 3.0
 */
public class Message {

    public static final int ADD_COMPONENT = 10;
    public static final int REMOVE_COMPONENT = 11;
    public static final int ADD_LISTENER = 12;
    public static final int ADD_MENUBAR = 13;
    public static final int COMPONENT_END = 20;

    public static final int LOG_INFO = 20;
    public static final int LOG_INFO_TWO_PARAM = 21;
    public static final int LOG_DEBUG = 22;
    public static final int LOG_ERROR = 23;

    public static final int LOG_END = 30;

    public static final int SHUTDOWN_REQUEST = 30;

    public static final int SWITCH_BEGIN = 40;
    public static final int SWITCH_TO_STARTSTATE = 41;
    public static final int SWITCH_TO_GAMESTATE = 42;
    public static final int SWITCH_TO_ENDSTATE = 43;
    public static final int SWITCH_END = 50;

    public static final int SAVE_GAME = 100;

    private int identifier;

    private Object args1, args2;

    /**
     * The Message contains up to two Objects of any class. The identifier is
     * used to identify the message and to typecast the Objects back to there
     * original classtype.
     * 
     * @param identifier
     *            the identifier, MUST BE one of the constant fields of message,
     *            all other identifiers are ignored
     * @param args1
     *            an object
     * @param args2
     *            an object
     */
    public Message(int identifier, Object args1, Object args2) {
	this.identifier = identifier;
	this.args1 = args1;
	this.args2 = args2;
    }

    /**
     * Get the identifier of the message.
     * 
     * @return the identifier of the message
     */
    public int getIdentifier() {
	return identifier;
    }

    /**
     * Get the first argument of the message.
     * 
     * @return args1 the first object
     */
    public Object getArgs1() {
	return args1;
    }

    /**
     * Get the second argument of the message.
     * 
     * @return args2 the second object
     */
    public Object getArgs2() {
	return args2;
    }
}
