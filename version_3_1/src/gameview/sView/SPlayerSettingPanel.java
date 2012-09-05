package gameview.sView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import events.EventConstants;
import events.IAEvent;
import events.logic.global.ELanguageSwitched;
import events.logic.startState.EDataPlayerSettingsPanel;

/**
 * Within this panel there are the configurations for each player. In dependency
 * on the player number there are 2-4 SPlayerPanels. This panel only create's
 * and interact's with this panels to check if the values are conform to start a
 * game.
 * 
 * @author Raphael A.
 * @version 3.0
 * 
 */
public class SPlayerSettingPanel extends JPanel {

    /**
     * The ActionListener.
     * 
     * @author Raphael
     * 
     */
    public class SPlayerSettingHandler implements ActionListener {
	/**
	 * method for the menuitem eventhandling
	 * 
	 * @since 1.2
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param event A action event
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
	    if (event.getSource().equals(startGameButton)) {
		// disable the "startgame" button
		startGameButton.setEnabled(false);
		sState.startGame();
		sState.onPanePPlayerSetting(false);

	    }
	    if (event.getSource().equals(abortButton)) {
		// disable the "startgame" button
		startGameButton.setEnabled(false);
		sState.onPanePPlayerSetting(false);
		sState.onPanePGameMode(true);

	    }
	}
    }

    private static final long serialVersionUID = 1L;
    // define every element.
    JComponent contentBox = new JPanel();
    JComponent playerPanelBox, maxFigureBox;

    JButton startGameButton, abortButton;
    JLabel playerSettingsTitel, maxFigureBoxLabel, figureCountLabel;
    Dimension minSize = new Dimension(5, 10);
    Dimension prefSize = new Dimension(5, 25);

    Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);

    GridBagConstraints gc;
    List<BufferedImage> bufferdFigureIcons;

    Map<String, String> language;
    SState sState;

    TitledBorder playerPanelBoxBorder;

    LineBorder maxFigBorder;
    // TemP Data
    int playerCount = 2;
    int maxFigureCount = 10;
    int compareSetMaxFigureCount = 10;
    String name = new String("Player ");
    String color;
    int type;
    int figureCount = 1;
    int botOrPlayer;
    int icon;

    int count = 0;

    ISPlayer[] playerArray;

    /**
     * The constructor to commit the current language an the SState. Also the
     * Layout will be set.
     * 
     * @param sState The start state
     * @param language Map with language data
     */
    public SPlayerSettingPanel(final SState sState,
	    final Map<String, String> language) {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.language = language;
	this.sState = sState;
    }

    /**
     * This method adds a actionListener to the both buttons next and abort.
     * 
     * @param actionListener action listener
     */
    public void addListener(final ActionListener actionListener) {
	startGameButton.addActionListener(actionListener);
	abortButton.addActionListener(actionListener);
    }

    // --------------------------------------------------------------------------------------------------------------------------
    // The check.
    /**
     * This method checks the current chosen colors. If no conflicts exist it
     * returns true.
     * 
     * @return check, which is true when there are no conflicts exist.
     */
    public synchronized boolean checkColors() {
	boolean check = true;
	final Integer[] colorArray = new Integer[playerPanelBox
		.getComponentCount()];
	// gets the current selected Colors.
	for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
	    colorArray[i] = ((SPlayerPanel) playerPanelBox.getComponent(i))
		    .getColorIndex();
	    ((SPlayerPanel) playerPanelBox.getComponent(i))
		    .setDisableColors(false);
	}
	// compare the indexes. If there are conflicts, set in the conflicted
	// panels color labels to red and returns false so the
	// "start game button" is disabled
	for (int i = 0; i < colorArray.length; i++) {
	    for (int j = 0; j < playerPanelBox.getComponentCount(); j++) {
		if (i != j) {
		    if (colorArray[i] == ((SPlayerPanel) playerPanelBox
			    .getComponent(j)).getColorIndex()) {

			((SPlayerPanel) playerPanelBox.getComponent(j))
				.setDisableColors(true);
			check = false;
		    }
		}
	    }
	}
	// if there are no conflicts return true else false;
	return check;
    }

    /**
     * The checkFigureCount() compares the figure count of every panel with the
     * maximal figure count and set the current figure count.
     */
    public synchronized void checkFigureCount() {
	for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
	    // when the figurecount is 1 the minus button has to be disabled.
	    // So the figurecount cannot fell lower than 1
	    if (((SPlayerPanel) playerPanelBox.getComponent(i))
		    .getFigureCount() == 1) {
		((SPlayerPanel) playerPanelBox.getComponent(i))
			.enableMinus(false);
	    } else {
		((SPlayerPanel) playerPanelBox.getComponent(i))
			.enableMinus(true);
	    }
	    // The the current figure count including the figurecount of this
	    // player
	    // which will be compared with the max. figure count.
	    count = count
		    + ((SPlayerPanel) playerPanelBox.getComponent(i))
			    .getFigureCount();
	}
	// If the max figure count is reached disable all plus buttons!
	if (count == compareSetMaxFigureCount) {
	    for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
		((SPlayerPanel) playerPanelBox.getComponent(i))
			.enablePlus(false);
	    }
	    // Is'nt the max figure count reached enable all plus buttons!
	} else if (count < compareSetMaxFigureCount && count > 0) {
	    for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
		((SPlayerPanel) playerPanelBox.getComponent(i))
			.enablePlus(true);
	    }
	}
	// reset count
	count = 0;
    }

    /**
     * This method returns true if the icons are not conflicted.
     * 
     * @return check True, if the icons are not conflicted
     */
    public synchronized boolean checkIcons() {
	boolean check = true;
	// Create an array which will contain evry index of the icon Box
	final Integer[] iconArray = new Integer[playerPanelBox
		.getComponentCount()];
	// Fill the array with the index's
	for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
	    try {
		iconArray[i] = ((SPlayerPanel) playerPanelBox.getComponent(i))
			.getFigureIconComboBox().getSelectedIndex();
	    } catch (final Exception e) {
		sState.getPGV().getMsgPump().logDebug(e.getMessage());
	    }
	}
	// compare the index's if there are the same somewhere set check to
	// false
	for (int i = 0; i < iconArray.length; i++) {
	    for (int j = 0; j < playerPanelBox.getComponentCount(); j++) {
		if (i != j) {
		    if (iconArray[i] == ((SPlayerPanel) playerPanelBox
			    .getComponent(j)).getFigureIconComboBox()
			    .getSelectedIndex()) {
			check = false;
		    }
		}
	    }
	}
	return check;
    }

    /**
     * This method checks if the NameTextfields are filled with a name. Yes?=
     * true.
     * 
     * @return check, which is true when there are no conflicts exist.
     */
    public synchronized boolean checkNames() {
	try {
	    // Create an array which will contain evry name
	    Set<String> nameSet = new HashSet<>();
	    for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
		final String name = new String(
			((SPlayerPanel) playerPanelBox.getComponent(i))
				.getNameTextField().getText());
		// if there is the standard text or an empty string in the
		// textfield return false!
		if (name.equals(language.get("pSPlayerName"))
			|| name.equals("Insert a player name!")
			|| name.equals("")) {
		    return false;
		}
		// checks if the name is already contained in the name set, if
		// so, return false
		if (nameSet.contains(name)) {
		    return false;
		} else {
		    nameSet.add(name);
		}
	    }

	    // No problems? No? Okay : return true!
	    return true;
	} catch (final Exception e) {
	    sState.getPGV().getMsgPump().logError(e.getMessage());
	    return false;
	}
    }

    /**
     * This method is called on every onRender() and when something is changed
     * by the user. If there is everything configured for the game the
     * StartGameButton set enabled.
     */
    public synchronized void checkNext() {
	if (checkNames() && checkTypes() && checkColors() && checkIcons()) {
	    startGameButton.setEnabled(true);
	    startGameButton.setForeground(Color.DARK_GRAY);
	} else {
	    startGameButton.setEnabled(false);
	    startGameButton.setForeground(Color.WHITE);
	}

    }

    /**
     * A method which checks the police and the thief count. No conflicts =
     * true.
     * 
     * @return check, which is true when there are no conflicts exist.
     */
    public synchronized boolean checkTypes() {
	boolean check = true;
	int countthiefs = 0;
	int countpolice = 0;
	// Resets every type label to black foreground color.
	for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
	    JLabel label = new JLabel();
	    label = ((SPlayerPanel) playerPanelBox.getComponent(i))
		    .getTypeChoose();
	    label.setForeground(Color.BLACK);
	    ((SPlayerPanel) playerPanelBox.getComponent(i))
		    .setTypeChoose(label);
	    // counts the number of police and thief players.
	    if (((SPlayerPanel) playerPanelBox.getComponent(i)).getType() == 0) {
		countthiefs++;
	    } else {
		countpolice++;
	    }
	}
	// if there only police or thiefs return false and set the type label to
	// an red foreground.
	if (countpolice == 0 | countthiefs == 0) {
	    check = false;
	    for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
		JLabel label = new JLabel();
		label = ((SPlayerPanel) playerPanelBox.getComponent(i))
			.getTypeChoose();
		label.setForeground(Color.RED);
		((SPlayerPanel) playerPanelBox.getComponent(i))
			.setTypeChoose(label);
	    }
	}
	return check;
    }

    /**
     * This method commits the empty maxFigureBox.
     * 
     * @return maxFigureBox Maximum figure box
     */
    public JComponent getMaxFigureBox() {
	maxFigureBox = new JPanel();
	maxFigureBox.setLayout(new BoxLayout(maxFigureBox, BoxLayout.X_AXIS));
	final Border loweredetched = BorderFactory
		.createEtchedBorder(EtchedBorder.LOWERED);
	maxFigureBox.setBorder(loweredetched);
	maxFigureBox.setBackground(Color.DARK_GRAY);
	initMaxFigureBox();
	return maxFigureBox;
    }

    /**
     * This getter is necessary to compare the figurecounts of each player.
     * 
     * @return maxFigureCount Maximum of figures
     */
    public int getMaxFigureCount() {
	return maxFigureCount;
    }

    /**
     * Get array with all players-
     * 
     * @return Array with all players
     */
    ISPlayer[] getplayerArray() {
	return playerArray;
    }

    // --------------------------------------------------------------------------------------------------------------------------

    /**
     * The init method which calls every method that "setup" the GUI elements.
     * Also the SPlayerSettingHandler will be added to have an actionListener
     */
    public void init() {
	setBackground(Color.DARK_GRAY);

	setupTitle();

	setupContentBox();

	setupButtonBox();

	addListener(new SPlayerSettingHandler());
    }

    /**
     * This method initializes every element of the maxFigureBox. This method is
     * also called if the current figure count is changed.
     */
    public void initMaxFigureBox() {
	// Because ths box will be updated several times when the figurecount is
	// changed by the player
	// the components have to be removed and restored every time.
	maxFigureBox.removeAll();
	figureCountLabel = new JLabel(String.valueOf(maxFigureCount));
	maxFigureBox.add(maxFigureBoxLabel);
	maxFigureBox.add(figureCountLabel);
	maxFigureBoxLabel.setFont(new Font("Arial", 1, 20));
	maxFigureBoxLabel.setForeground(Color.WHITE);
	figureCountLabel.setFont(new Font("Arial", 1, 20));
	figureCountLabel.setForeground(Color.WHITE);
    }

    /**
     * This method initializes the player array which contains the player
     * settings in an SPlayer object for each player. IMPORTANT STUFF!!!
     */
    public void initPlayerArray() {
	playerArray = new ISPlayer[playerPanelBox.getComponentCount()];

	for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
	    name = ((SPlayerPanel) playerPanelBox.getComponent(i))
		    .getPlayerName();
	    type = ((SPlayerPanel) playerPanelBox.getComponent(i)).getType();
	    color = ((SPlayerPanel) playerPanelBox.getComponent(i)).getColor();
	    figureCount = ((SPlayerPanel) playerPanelBox.getComponent(i))
		    .getFigureCount();
	    icon = ((SPlayerPanel) playerPanelBox.getComponent(i)).getIconID();
	    botOrPlayer = ((SPlayerPanel) playerPanelBox.getComponent(i))
		    .getBotOrPlayer();

	    final ISPlayer player = new SPlayer(name, type, color, figureCount,
		    icon);
	    ((SPlayer) player).setBotOrPlayer(botOrPlayer);
	    playerArray[i] = player;
	}

    }

    /**
     * This method processes the incoming events. LANGUAGE_SWITCHED = language
     * is changed -> go and change that things! PLAYER_COUNT = we got X Players
     * -> go and make me some SPlayerPanels!
     * 
     * @param event A event
     */
    public void processEvent(final IAEvent event) {
	if (event.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
	    switchLanguage(((ELanguageSwitched) event).getLanguage());
		// process Event (language)->evry playerpanel
		for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
		    if (playerPanelBox.getComponent(i) != null) {
			((SPlayerPanel) playerPanelBox.getComponent(i))
				.processEvent(event);
		    }
		}
	} else if (event.getEventType() == EventConstants.DATA_PLAYER_SETTINGS_PANEL) {
	    final EDataPlayerSettingsPanel ee = (EDataPlayerSettingsPanel) event;
	    // get all map infos and the player count. After this event is
	    // resived the player
	    // panels can be created.
	    maxFigureCount = ee.getMaxFigureCount() - ee.getPlayerCount();
	    compareSetMaxFigureCount = ee.getMaxFigureCount();
	    playerCount = ee.getPlayerCount();
	    // create the player panels.
	    setPlayerPanelBox();
	    for (int i = 0; i < playerPanelBox.getComponentCount(); i++) {
		((SPlayerPanel) playerPanelBox.getComponent(i))
			.setBufferdFigureIcons(ee.getFigureIcons());
	    }
	    initMaxFigureBox();
	}
    }

    // --------------------------------------------------------------------------------------------------------------------------
    // Getter and setter
    /**
     * Set maximum of figures.
     * 
     * @param maxFigureCount Maximum of figures
     */
    public void setMaxFigureCount(final int maxFigureCount) {
	this.maxFigureCount = maxFigureCount;
    }

    /**
     * This method create the SPlayerPanels and commit different initialization
     * data. Also the methods getPlayerInfos() and checkFigureCount are called.
     * The getPlayerInfos() method is a test method which print the values of
     * every element that is necessary of every SPlayerPanel. The
     * checkFigureCount() compares the figure count of every panel with the
     * maximal figure count and set the current figure count.
     */
    public void setPlayerPanelBox() {
	int typeSet = 0;
	playerPanelBox.removeAll();
	playerPanelBox.setBorder(playerPanelBoxBorder);
	playerPanelBoxBorder = new TitledBorder(new LineBorder(Color.black),
		language.get("pSPlayerPanelBox"), TitledBorder.LEFT,
		TitledBorder.TOP);
	playerPanelBox.setBorder(playerPanelBoxBorder);
	playerPanelBox.setBackground(Color.white);
	// Create PlayerPanels depending on the chosen player count.
	// SPlayerPanel.SPlayerPanel(SPlayerSettingPanel sPSPanel,
	// int colorIndex, int type, int id, Map<String, String> language)

	for (int i = 0; i < playerCount; i++) {
	    // set different types to every player
	    if (i == 0 | i == 2) {
		typeSet = 1;
	    } else {
		typeSet = 0;
	    }
	    final SPlayerPanel playerPanel = new SPlayerPanel(this, i, typeSet,
		    i + 1, language);
	    playerPanelBox.add(playerPanel);

	}
	checkFigureCount();
    }

    /**
     * Setup of the "button box" panel with the two buttons "next" and "abort".
     * Also the maxFigureBox will be added at the left side.
     */
    public void setupButtonBox() {
	abortButton = new JButton(language.get("abort"));
	abortButton.setFont(new Font("Arial", 1, 20));
	abortButton.setForeground(Color.DARK_GRAY);
	startGameButton = new JButton(language.get("pSStartGame"));
	startGameButton.setFont(new Font("Arial", 1, 20));
	startGameButton.setForeground(Color.WHITE);
	startGameButton.setEnabled(false);
	maxFigureBoxLabel = new JLabel(language.get("pSFiguresLeft"));
	final JComponent buttonBox = new JPanel();
	buttonBox.setLayout(new BoxLayout(buttonBox, BoxLayout.X_AXIS));

	buttonBox.add(new Box.Filler(minSize, new Dimension(
		Short.MAX_VALUE - 100, 100), maxSize));
	buttonBox.add(getMaxFigureBox());
	buttonBox.add(new Box.Filler(minSize, prefSize, maxSize));

	buttonBox.add(abortButton);
	buttonBox.add(new Box.Filler(minSize, prefSize, maxSize));

	buttonBox.add(startGameButton);
	buttonBox.add(new Box.Filler(minSize, prefSize, maxSize));
	buttonBox.setBackground(Color.DARK_GRAY);

	add(buttonBox);
    }

    /**
     * Setup of the big "content box" panel which holds the "map box" and the
     * "settings box" panel.
     */
    public void setupContentBox() {
	playerPanelBox = new JPanel();
	playerPanelBox
		.setLayout(new BoxLayout(playerPanelBox, BoxLayout.Y_AXIS));
	setPlayerPanelBox();
	contentBox = new JPanel();
	contentBox.setLayout(new GridBagLayout());
	contentBox.setBackground(Color.darkGray);
	gc = new GridBagConstraints();

	gc.fill = GridBagConstraints.VERTICAL;
	gc.weightx = 0.15;
	gc.gridx = 0;
	gc.gridy = 0;
	contentBox.add(Box.createHorizontalGlue(), gc);

	contentBox.setMinimumSize(new Dimension(400, 300));
	gc.fill = GridBagConstraints.HORIZONTAL;
	gc.weightx = 0.70;
	gc.weighty = 1;
	gc.gridx = 1;
	gc.gridy = 0;
	contentBox.add(playerPanelBox, gc);
	gc.fill = GridBagConstraints.BOTH;
	// contentBox.add(sMapSettingPanel, gc);
	gc.weightx = 0.15;
	gc.ipady = Short.MAX_VALUE;
	gc.gridx = 2;
	gc.gridy = 0;
	contentBox.add(Box.createHorizontalGlue(), gc);
	add(contentBox);
    }

    /**
     * Setup of the title. Nothing else
     */
    public void setupTitle() {
	playerSettingsTitel = new JLabel(language.get("pSTitel"));
	playerSettingsTitel.setFont(new Font("Arial", 1, 42));
	playerSettingsTitel.setAlignmentX(CENTER_ALIGNMENT);
	playerSettingsTitel.setForeground(Color.white);
	add(new Box.Filler(minSize, prefSize, maxSize));
	add(playerSettingsTitel);
	add(new Box.Filler(minSize, prefSize, maxSize));
    }

    /**
     * This method changes every element title or text to the current language
     * if requested.
     * 
     * @param language Map with language data
     */
    public void switchLanguage(final Map<String, String> language) {
	this.language = language;
	playerSettingsTitel.setText(language.get("pSTitel"));
	startGameButton.setText(language.get("next"));
	abortButton.setText(language.get("abort"));
	playerPanelBoxBorder.setTitle(language.get("pSPlayerPanelBox"));
	maxFigureBoxLabel.setText(language.get("pSFiguresLeft"));

    }
}
