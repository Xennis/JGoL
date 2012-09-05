package gameview.sView;

import events.EventConstants;
import events.IAEvent;
import events.logic.global.ELanguageSwitched;
import gameview.PlayerGameView;

import java.util.Map;

import core.IMain;

/**
 * This is the top class for the "SState". Its the controlling unit. Every panel
 * is created here and added to or removed from the mainframe. Also the events
 * will be fired from this class.
 * 
 * @author Raphael A.
 * @version 3.0
 * 
 */
public class SState {

    private final PlayerGameView pGV;

    SMenuPanel pMenu;
    SGameModePanel pGameMode;
    SPlayerSettingPanel pPlayerSetting;

    Map<String, String> language;

    /**
     * The Constructor to initialize the panels.
     * 
     * @param pGV player gameView
     * @param language Map with language data
     */
    public SState(final PlayerGameView pGV, final Map<String, String> language) {

	this.pGV = pGV;
	this.language = language;
	pMenu = new SMenuPanel(this, language);
	pMenu.init();
	pGameMode = new SGameModePanel(this, language);
	pGameMode.init();
	pGV.eventGenerator.fireNewPlayerSwitchedToNGV(this);
	pGV.getMsgPump().addComponent(pMenu);

    }

    /**
     * Getter for the PlayerGameView
     * 
     * @return pGV the player gameView
     */
    public PlayerGameView getPGV() {
	return pGV;
    }

    /**
     * If the "New game" Button is pressed the "maplist" and the current map
     * have to be loaded. This method is called before the GameMode panel is
     * added to the Mainframe
     */
    public void initGameMode() {
	pGV.eventGenerator.fireNewPlayerSwitchedToNGV(this);
	// mapSelected(this, "Gangs of Lübeck");
    }

    /**
     * This method fires a event if a map is chosen. With fired map title the
     * map preview and the map informations can be shown.
     * 
     * @param source Source of the event
     * @param mapName Name of the map
     */
    public void mapSelected(final Object source, final String mapName) {
	pGV.eventGenerator.fireNewPlayerRequestedMapData(source, mapName);
    }

    // ----------------------------------------------------------------------------------------------------------

    /**
     * Remove or add one of the sView panels.
     * 
     * @param onPane
     *            = true -> add; false -> remove
     */
    public void onPanePGameMode(final boolean onPane) {
	if (onPane) {
	    mapSelected(this, pGameMode.maps[0]);
	    pGV.getMsgPump().addComponent(pGameMode);
	} else {
	    pGV.getMsgPump().removeComponent(pGameMode);
	}
    }

    // ----------------------------------------------------------------------------------------------------------
    /**
     * Remove or add one of the sView panels.
     * 
     * @param onPane
     *            = true -> add; false -> remove
     */
    public void onPanePMenu(final boolean onPane) {
	if (onPane) {
	    pGV.getMsgPump().addComponent(pMenu);
	} else {
	    pGV.getMsgPump().removeComponent(pMenu);
	}
    }

    /**
     * Remove or add one of the sView panels.
     * 
     * @param onPane
     *            = true -> add; false -> remove
     */
    public void onPanePPlayerSetting(final boolean onPane) {
	if (onPane) {
	    pPlayerSetting = new SPlayerSettingPanel(this, language);
	    pPlayerSetting.init();
	    pGV.getMsgPump().addComponent(pPlayerSetting);

	} else {
	    pGV.getMsgPump().removeComponent(pPlayerSetting);
	    pPlayerSetting = null;
	}
    }

	/**
	 * This method fires a event if the player number is changed. This could
	 * happen in the SMapSettingPanel
	 * 
	 * @param source
	 *            Source of the event
	 * @param chosenNumber
	 *            Number of choosen players
	 * @param specialTicketArray
	 *            Number of special ticket types
	 */
    public void playerNumberSelect(final Object source, final int chosenNumber,
	    final Integer[] specialTicketArray) {
	pGV.eventGenerator.fireNewChosePlayerCountEvent(source, chosenNumber,
		specialTicketArray);
    }

    /**
     * This method commits the events to every panel.
     * 
     * @param event A event
     */
    public void processEvent(final IAEvent event) {
	if (event.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
	    language = ((ELanguageSwitched) event).getLanguage();
	}
	pMenu.processEvent(event);
	pGameMode.processEvent(event);
	if (pPlayerSetting != null) {
	    pPlayerSetting.processEvent(event);
	}
    }

    /**
     * This method fires the NewSwitchStateEvent and changes the current state
     * to "Game_State_Play"
     */
    public void startGame() {
	// IMain.GAME_STATE_PLAY);
	// Call the init Player Array method which fills the Player objects with
	// the data.
	pPlayerSetting.initPlayerArray();
	for (int i = 0; i < pPlayerSetting.getplayerArray().length; i++) {
	    pGV.eventGenerator.fireNewPlayerSetPropertiesEvent(this,
		    pPlayerSetting.getplayerArray()[i]);
	}

	pGV.eventGenerator.fireNewSwitchStateEvent(this, IMain.GAME_STATE_PLAY);
    }

}
