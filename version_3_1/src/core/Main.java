package core;

import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.util.Properties;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import logic.ILogic;
import logic.Logic;

import core.msgpump.Message;
import core.msgpump.MsgPump;
import core.pedobear.IPedoLogger;
import core.pedobear.PedoConsoleLogger;
import core.pedobear.PedoLogger;

/**
 * The mainclass of the game, completely rebuild, for version 2.0. Takes
 * parameters to switch the mode of the game. Three parameters are supported:
 * -nogui, -logconsoleonly, -log, logSilent
 * 
 * @author Jodder
 * @version 3.0
 */
public final class Main implements IMain {

    private static Main main;

    private boolean noGUI;
    private int loggingMode;

    private IPedoLogger debugLog;
    private MainFrame mainFrame;
    private RessourceCache cache;
    ILogic logic;
    MsgPump msgPump;
    private boolean shutdownRequested;
    private Timer timer;
    int tmpTimer;
    int loopCounter;
    int gameState = 0;

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#init()
     */
    @Override
    public void init() {
	timer = new Timer();
	msgPump = new MsgPump();
	initLog();
	if (!noGUI) {
	    initGUI();
	}
	initCache();
	initLogic();
	timer.getDelta();
    }

    void initLog() {
	if (loggingMode != 0 && loggingMode != IPedoLogger.CONSOLE_ONLY) {
	    try {
		debugLog = PedoLogger.getNewLogger(timer.getTime() + "_debug_"
			+ System.getProperties().getProperty("user.name"),
			loggingMode, LOGGING_LEVEL);
		logSystemInformations();
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
		System.out.println("There was no chance to build a Logger");
		loggingMode = 0;
	    }
	} else if (loggingMode == IPedoLogger.CONSOLE_ONLY) {
	    debugLog = new PedoConsoleLogger(LOGGING_LEVEL);
	    logSystemInformations();
	}
    }

    void initCache() {
	cache = new RessourceCache(msgPump);
	getCache().scanDir();
	if (!noGUI) {
	    getCache().loadFigureIcons();
	}
    }

    void initLogic() {
	logic = new Logic(getCache(), msgPump, noGUI);
	logic.init(cache.getLanguage("clingon"), cache.getLanguageNames());
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#logSystemInformations()
     */
    @Override
    public void logSystemInformations() {
	Properties systemProps = System.getProperties();
	debugLog.info("Java RE Version: "
		+ systemProps.getProperty("java.version"));
	debugLog.info("Java RE Vendor: "
		+ systemProps.getProperty("java.vendor"));
	debugLog.info("Java installation directory: "
		+ systemProps.getProperty("java.home"));
	debugLog.info("Java VM Version: "
		+ systemProps.getProperty("java.vm.version"));
	debugLog.info("OS Name: " + systemProps.getProperty("os.name"));
	debugLog.info("OS Version: " + systemProps.getProperty("os.version"));
	debugLog.info("Running as User: "
		+ systemProps.getProperty("user.name"));
	debugLog.info("user home dir " + systemProps.getProperty("user.home"));
	debugLog.info("user current working dir: "
		+ systemProps.getProperty("user.dir"));
	debugLog.info("Spielversion JGoL v." + VERSION);
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#initGUI()
     */
    @Override
    public void initGUI() {
	mainFrame = new MainFrame("JGoL");
	mainFrame.setSize(1280, 720);
	mainFrame.setLocationRelativeTo(null);
	mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	mainFrame.setUndecorated(true);
	mainFrame.setVisible(true);
	mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	dropDownMLook();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#dropDownMLook()
     */
    @Override
    public void dropDownMLook() {
	try {
	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (Exception e) {
	    msgPump.logError("Fehler beim initialisieren des Look and Feels!");
	}
    }

    // ----------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#start()
     */
    @Override
    public void start() {
	while (!shutdownRequested) {
	    while (msgPump.hasMsg()) {
		processMsg(msgPump.getNextMsg());
	    }
	    logic.onRender();
	    logic.processEvents();

	    if (!noGUI && mainFrame.isActive()) {
		mainFrame.setVisible(true);
		mainFrame.repaint();
	    }

	    sleep();
	    loopCounter++;
	}
    }

    /**
     * Process a {@link core.msgpump.Message}.
     * 
     * @param nextMsg The {@link core.msgpump.Message}
     */
    private void processMsg(Message nextMsg) {

	if (nextMsg.getIdentifier() < Message.COMPONENT_END && !noGUI) {
	    dispatchCompMsg(nextMsg);
	} else if (nextMsg.getIdentifier() < Message.LOG_END) {
	    dispatchLogMsg(nextMsg);
	} else if (nextMsg.getIdentifier() == Message.SHUTDOWN_REQUEST) {
	    requestShutdown();
	} else if (nextMsg.getIdentifier() >= Message.SWITCH_BEGIN) {
	    dispatchGameStateSwitchmsg(nextMsg);
	}

    }

    /**
     * Dispatch comp message.
     * 
     * @param msg The {@link core.msgpump.Message}
     */
    private void dispatchCompMsg(Message msg) {

	switch (msg.getIdentifier()) {
	case (Message.ADD_COMPONENT):
	    mainFrame.add((JComponent) msg.getArgs1());
	    break;
	case (Message.REMOVE_COMPONENT):
	    mainFrame.remove((JComponent) msg.getArgs1());
	    break;
	case (Message.ADD_LISTENER):
	    mainFrame.addWindowListener((WindowListener) msg.getArgs1());
	    break;
	case (Message.ADD_MENUBAR):
	    mainFrame.setJMenuBar((JMenuBar) msg.getArgs1());
	    break;
	default:
	    break;
	}
    }

    /**
     * Dispatch log message.
     * 
     * @param msg The {@link core.msgpump.Message}
     */
    private void dispatchLogMsg(Message msg) {
	if (loggingMode != 0) {
	    switch (msg.getIdentifier()) {
	    case (Message.LOG_DEBUG):
		debugLog.debug((String) msg.getArgs1());
		break;
	    case (Message.LOG_ERROR):
		debugLog.error((String) msg.getArgs1());
		break;
	    case (Message.LOG_INFO):
		debugLog.info((String) msg.getArgs1());
		break;
	    case (Message.LOG_INFO_TWO_PARAM):
		debugLog.info((String) msg.getArgs1(), (String) msg.getArgs2());
		break;
	    default:
		break;
	    }
	}
    }

    /**
     * Dispatch gameState switch message.
     * 
     * @param msg The {@link core.msgpump.Message}
     */
    void dispatchGameStateSwitchmsg(Message msg) {
	switch (msg.getIdentifier()) {
	case (Message.SWITCH_TO_STARTSTATE):
	    gameState = GAME_STATE_START;
	    break;
	case (Message.SWITCH_TO_GAMESTATE):
	    gameState = GAME_STATE_PLAY;
	    break;
	case (Message.SWITCH_TO_ENDSTATE):
	    gameState = GAME_STATE_END;
	    break;
	default:
	    break;
	}
    }

    /**
     * Requsted a shutdown.
     */
    private void requestShutdown() {
	shutdownRequested = true;
    }

    /**
     * Thread sleeping.
     */
    private void sleep() {
	try {
	    if ((tmpTimer = (1000 / FPS) - timer.getDelta()) >= 0) {
		Thread.sleep(tmpTimer);
		timer.getDelta();
	    } else {
		msgPump.logDebug("negative Zeitmessung: " + tmpTimer);
	    }
	} catch (Exception e) {
	    msgPump.logDebug("Exception während Thread.sleep im Mainloop "
		    + e.toString());
	}
    }

    // ----------------------------------------------------------------------------------------------------

    /**
     * Shutdown system.
     */
    private void shutdown() {
	if (!noGUI) {
	    mainFrame.dispose();
	    if (!(loggingMode == 0)) {
		debugLog.shutdown();
	    }
	    System.exit(0);
	}
    }

    // ----------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#getGameState()
     */
    @Override
    public int getGameState() {

	return gameState;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#isShutdownRequested()
     */
    @Override
    public boolean isShutdownRequested() {
	return shutdownRequested;
    }

    /* (non-Javadoc)
     * @see core.IMain#getCache()
     */
    @Override
    public RessourceCache getCache() {
	return cache;
    }

    /**
     * Set the loggin mode.
     * 
     * @param loggingMode The mode as integer
     */
    public void setLoggingMode(int loggingMode) {
	this.loggingMode = loggingMode;
	initLog();
    }

    // ----------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     * 
     * @see core.IMain#getCache()
     */

    /**
     * private constructor, for singleton purpose
     * 
     * @param args Argument for logging
     * @since 2.0
     */
    private Main(String[] args) {
	loggingMode = 0;

	for (int i = 0; i < args.length; i++) {
	    if (args[i].equals("-nogui")) {
		noGUI = true;
	    } else if (args[i].equals("-logconsoleonly")) {
		loggingMode = IPedoLogger.CONSOLE_ONLY;
	    } else if (args[i].equals("-log")) {
		loggingMode = IPedoLogger.LOG_AND_CONSOLE;
	    } else if (args[i].equals("-logsilent")) {
		loggingMode = IPedoLogger.LOG_ONLY;
	    }
	}
    }

    /**
     * get the only instance of the game.
     * 
     * @since 2.0
     * @return main, the instance of the game
     */
    public static Main getInstance() {
	if (main == null) {
	    String[] temp = { "-nogui" };
	    initMain(temp);
	    main.init();
	}
	return main;
    }

	/**
	 * the one and only initalisation of the game. Should only be called by the
	 * main method.
	 * 
	 * @param args
	 *            Arguments for main method
	 */
	private static void initMain(String[] args) {
		if (main == null) {
			main = new Main(args);
		}
	}

	/**
	 * main method, valid parameters: -nogui, -logconsoleonly, -log, - logsilent
	 * 
	 * @param args
	 *            Arguments for main method
	 */
    public static void main(String[] args) {
	initMain(args);
	getInstance().init();
	getInstance().start();
	getInstance().shutdown();
    }

}
