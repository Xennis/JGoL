package core;

import core.pedobear.IPedoLogger;

public interface IMain {

    // Paths
    public static final String MEDIA_DIR = "./media";
    public static final String MAP_DIR = "./media/maps";
    // State
    public static final int GAME_STATE_START = 0;
    public static final int GAME_STATE_PLAY = 1;
    public static final int GAME_STATE_END = 2;
    // Other
    public final int LOGGING_LEVEL = IPedoLogger.LOG_LEVEL_ERROR;

    public static final int FPS = 10;
    public static final String VERSION = "3.0";
    public static final String DEFAULT_LANGUAGE = "English";

    /**
     * Main initilizing.
     */
    public abstract void init();

    /**
     * Logging some system-information for debugging.
     * 
     * @since 0.3.5
     */
    public abstract void logSystemInformations();

    /**
     * Initialize the gui.
     */
    public abstract void initGUI();

    /**
     * Set look and feel.
     */
    public abstract void dropDownMLook();

    // ----------------------------------------------------------------------------------------------------

    /**
     * The Mainloop
     * 
     * @since 0.2
     */
    public abstract void start();

    /**
     * Get the gameState as integer
     * @return the gameState as integer
     */
    public abstract int getGameState();

    /**
     * Check a shutdown was requested.
     * 
     * @return True, if a shutdown was requested
     */
    public abstract boolean isShutdownRequested();

    /**
     * Get the ressource cache.
     * 
     * @return the ressource cache
     */
    public abstract RessourceCache getCache();

}