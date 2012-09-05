package events;

/**
 * Contains all constants for the events. This constants are used to identify
 * every event.
 * 
 * @author Jasper S., Fabian R., Raphael A., Felix
 *         K.
 * @version 3.0
 * @since 1.0
 */
public interface EventConstants {

    /*
     * Logic -> GameView
     */
    // Global (10xx)
    public static final int LANGUAGE_SWITCHED = 1000;
    public static final int LOGIC_END_GLOBAL = 1099;
    // StartState (11xx)
    public static final int PLAYER_CREATED = 1100;
    public static final int FIGURE_CREATED = 1101;
    public static final int DATA_MAP_NAMES = 1152;
    public static final int DATA_MAP_DATA = 1153;
    public static final int DATA_PLAYER_SETTINGS_PANEL = 1154;
    public static final int LOGIC_END_START_STATE = 1199;
    // GameState main (12xx)
    public static final int INIT_NEW_GAME = 1200;
    public static final int INIT_NEW_GAME_DONE = 1201;
    public static final int POSSIBLE_THIEF_EVENT = 1202;
    public static final int THIEF_SHOW = 1203;
    public static final int PLAYER_MOVED_EVENT = 1204;
    public static final int LOGIC_END_GAME_STATE_MAIN = 1249;
    // GameState playing (125x)
    public static final int NEXT_FIGURE_EVENT = 1251;
    public static final int MOVE_DENIED_EVENT = 1252;
    public static final int NEXT_ROUND = 1253;
    public static final int FIGURE_KILLED = 1254;
    public static final int LOGIC_END_GAME_STATE = 1299;
    // EndState (13xx)
    public static final int PLAYER_WON = 1300;
    public static final int LOGIC_END_END_STATE = 1399;
    // End
    public static final int LOGIC_END = 1999;

    /*
     * GameView -> Logic
     */
    // Global (20xx)
    public static final int NEW_GAME_EVENT = 2000;
    public static final int END_GAME_EVENT = 2001;
    public static final int START_GAME_EVENT = 2002;
    public static final int REQUESTED_CLOSE = 2003;
    public static final int SWITCH_GAMESTATE_EVENT = 2004;
    public static final int REQUESTED_LANGUAGE_SWITCH = 2005;
    public static final int GAMEVIEW_END_GLOBAL = 2099;
    // StartState (21xx)
    public static final int PLAYER_SWITCHED_TO_NEWGAME_VIEW = 2101; // NOTE:
								    // general
    public static final int PLAYER_REQUESTED_MAP_DATA = 2102;
    public static final int PLAYER_CHOSE_PLAYER_COUNT = 2103;
    public static final int PLAYER_SET_PROPERTIES = 2104;
    public static final int PLAYER_ADDED_FIGURE = 2105;
    public static final int GAMEVIEW_END_START_STATE = 2199;
    // GameState (22xx)
    public static final int PLAYER_SURRENDER = 2200;
    public static final int PLAYER_REQUESTED_MOVE = 2201;
    public static final int GAMEVIEW_END_GAME_STATE = 2299;
    // End
    public static final int GAMEVIEW_END = 2999;

}