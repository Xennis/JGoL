package gameview.sView;

import events.EventConstants;
import events.IAEvent;
import events.logic.global.ELanguageSwitched;
import events.logic.startState.EDataMapData;
import gameview.ImagePanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import core.IConfig;

/**
 * A panel to display a map Preview and infos about the ticket types and the max
 * figure count. The number of players and the number of specialtickets can be
 * set.
 * 
 * @author Raphael A.
 * @version 3.0
 */
public class SGMapSettingPanel extends JPanel {

    // ----------------------------------------------------------------------------------------------------------------------------------
    // ActionListener
    /**
     * The actionListener for this class
     * 
     * @author Raphael
     * 
     */
    public class SMapSettingHandler implements ActionListener {
	/**
	 * method for the menuitem eventhandling
	 * 
	 * @since 1.2
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param e a action event
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
	    // Sets if there are changes in the ticket combo boxes, set the
	    // values
	    // to the specialTicketArray
	    if (e.getSource().equals(doubleTicketsThiefs)) {
		specialTicketArray[0] = doubleTicketsThiefs.getSelectedIndex();
	    }
	    if (e.getSource().equals(blackTicketsThiefs)) {
		specialTicketArray[1] = blackTicketsThiefs.getSelectedIndex();
	    }
	    if (e.getSource().equals(doubleTicketsPolice)) {
		specialTicketArray[2] = doubleTicketsPolice.getSelectedIndex();
	    }
	    if (e.getSource().equals(blackTicketsPolice)) {
		specialTicketArray[3] = blackTicketsPolice.getSelectedIndex();
	    }
	    // parse the selected value of the player count and save it at the
	    // chosenPlayerNumber int.
	    if (e.getSource().equals(numberOfPlayers)) {
		chosenPlayerString = (String) numberOfPlayers.getSelectedItem();
		chosenPlayerNumber = Integer.parseInt(chosenPlayerString);
		sGameModePanel.setChosenPlayer(chosenPlayerNumber);

	    }
	    // checks if the checkbox of the "possible thief function" and calls
	    // the showposthief() function
	    if (e.getSource().equals(checkThiefFunction)) {
		if (checkThiefFunction.isSelected()) {
		    sGameModePanel.sState.getPGV().setShowPosThiefs(true);
		} else {
		    sGameModePanel.sState.getPGV().setShowPosThiefs(false);
		}
	    }
	}
    }

    // ----------------------------------------------------------------------------------------------------------------------------------
    // init all elements
    SGameModePanel sGameModePanel;
    String ticketString, maxFiguresString;
    Integer[] specialTicketArray = { 3, 2, 0, 0 };
    String[] numbers = { "2", "3", "4" };
    String[] numbersd = { "2", "3" };

    String[] numbersz = { "2" };
    JComboBox<String> numberOfPlayers = new JComboBox<String>(numbers);
    JComboBox<String> doubleTicketsThiefs, doubleTicketsPolice,
	    blackTicketsThiefs, blackTicketsPolice;
    JComponent mapPreviewBox, playerNumberBox, infoBox, ticketTypeBox,
	    maxFiguresBox, eastBox, ticketChooseBox, showThiefBox;
    JList<String> ticketList;
    JLabel players, tickets, maxFigures, possibleThief;
    JCheckBox checkThiefFunction;
    TitledBorder settingBoxBorder, playerNumberBoxBorder, mapPreviewBoxBorder,
	    infoBoxBorder, ticketTypBoxBorder, maxFiguresBoxBorder,
	    ticketChooseBoxBorder, showThiefBoxBorder;

    ImagePanel currentMapImage;

    IConfig config;

    private final Map<String, String> language;

    SState sState;
    // Config temp
    String chosenMap = new String("No map chosen");
    String chosenPlayerString = new String("2");

    int chosenPlayerNumber = 2;

    private static final long serialVersionUID = 1L;

    // ----------------------------------------------------------------------------------------------------------------------------------
	/**
	 * The constructor
	 * 
	 * @param sGameModePanel
	 *            gameMode panel
	 * @param sState
	 *            the start state
	 * @param language
	 *            map with language data
	 */
    public SGMapSettingPanel(final SGameModePanel sGameModePanel,
	    final SState sState, final Map<String, String> language) {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.language = language;
	this.sState = sState;
	this.sGameModePanel = sGameModePanel;
    }

	/**
	 * Adds the local actionListener to evry component.
	 * 
	 * @param action
	 *            action listener
	 */
    public void addListener(final ActionListener action) {
	numberOfPlayers.addActionListener(action);
	doubleTicketsPolice.addActionListener(action);
	doubleTicketsThiefs.addActionListener(action);
	blackTicketsPolice.addActionListener(action);
	blackTicketsThiefs.addActionListener(action);
	checkThiefFunction.addActionListener(action);
    }

    // ----------------------------------------------------------------------------------------------------------------------------------
    // Checks

    /**
     * This method checks if there are to less start positions for 4,3 or 2
     * players. If there are only 2 startpositions there can only be 2 players.
     * If there less the 2 the map cannot be played.
     */
    public void checkNumberOfPlayer() {
	// resets the numberOfPlayers ComboBox and fill it with a new array
	// the array's numbersd and numbersz are filled with numbers from 2-3 or
	// 2.
	if (config.getStartat().size() == 3) {
	    playerNumberBox.remove(numberOfPlayers);
	    numberOfPlayers = null;
	    numberOfPlayers = new JComboBox<String>(numbersd);
	    playerNumberBox.add(numberOfPlayers);
	    numberOfPlayers.setSelectedIndex(0);
	    sGameModePanel.next.setEnabled(true);
	    sGameModePanel.next.setForeground(Color.DARK_GRAY);
	} else if (config.getStartat().size() == 2) {
	    playerNumberBox.remove(numberOfPlayers);
	    numberOfPlayers = null;
	    numberOfPlayers = new JComboBox<String>(numbersz);
	    playerNumberBox.add(numberOfPlayers);
	    numberOfPlayers.setSelectedIndex(0);
	    sGameModePanel.next.setEnabled(true);
	    sGameModePanel.next.setForeground(Color.DARK_GRAY);
	} else if (config.getStartat().size() < 2) {
	    // If there are less then 2 startpositions disable the next button
	    // and print the error. This map is not able to play.
	    sState.getPGV()
		    .getMsgPump()
		    .logError(
			    "Map cannot be played. Insufficient startpositions.");
	    sGameModePanel.next.setEnabled(false);
	    sGameModePanel.next.setForeground(Color.WHITE);
	} else {
	    // If there are no problems with the map set the default array to
	    // the numberOfPlayer comboBox.
	    final int index = numberOfPlayers.getSelectedIndex();
	    playerNumberBox.remove(numberOfPlayers);
	    numberOfPlayers = null;
	    numberOfPlayers = new JComboBox<String>(numbers);
	    playerNumberBox.add(numberOfPlayers);
	    numberOfPlayers.setSelectedIndex(index);
	    sGameModePanel.next.setEnabled(true);
	    sGameModePanel.next.setForeground(Color.DARK_GRAY);
	}
	chosenPlayerString = (String) numberOfPlayers.getSelectedItem();
	chosenPlayerNumber = Integer.parseInt(chosenPlayerString);
	sGameModePanel.setChosenPlayer(chosenPlayerNumber);
	numberOfPlayers.setFont(new Font("Arial", 1, 20));
	addListener(new SMapSettingHandler());
    }

    // ----------------------------------------------------------------------------------------------------------------------------------
    // get the components
    /**
     * Returns the configured infoBox. This contains the ticket types and the
     * max figure count
     * 
     * @return infoBox Box with information about a map
     */
    public JComponent getInfoBox() {
	// init the components
	infoBox = new JPanel();
	ticketTypeBox = new JPanel();
	maxFiguresBox = new JPanel();

	// Set the layout to a BoxLayout with Y orientation.
	// So the components are vertical.
	infoBox.setLayout(new BoxLayout(infoBox, BoxLayout.Y_AXIS));

	// Setup the Borders
	infoBoxBorder = new TitledBorder(
		new EtchedBorder(EtchedBorder.LOWERED), "Map info",
		TitledBorder.LEFT, TitledBorder.TOP);
	infoBoxBorder.setTitleColor(Color.black);
	ticketTypBoxBorder = new TitledBorder(new EtchedBorder(
		EtchedBorder.LOWERED), "Ticket types", TitledBorder.LEFT,
		TitledBorder.TOP);
	maxFiguresBoxBorder = new TitledBorder(new EtchedBorder(
		EtchedBorder.LOWERED), language.get("gMMaxFigures"),
		TitledBorder.LEFT, TitledBorder.TOP);

	ticketTypeBox.setBorder(ticketTypBoxBorder);
	maxFiguresBox.setBorder(maxFiguresBoxBorder);
	infoBox.setBorder(infoBoxBorder);

	// Set the background to white
	ticketTypeBox.setBackground(Color.white);
	maxFiguresBox.setBackground(Color.white);
	infoBox.setBackground(Color.white);

	// add the comoinents to the infoBox and return this infoBox
	infoBox.add(ticketTypeBox);
	infoBox.add(maxFiguresBox);
	return infoBox;
    }

    /**
     * This method sets the mapPreviewBox
     * 
     * @return mapPreviewBox Box for map preview
     */
    public JComponent getMapPreviewBox() {
	// Init the panel
	mapPreviewBox = new JPanel();

	// Init and set the Border
	mapPreviewBoxBorder = new TitledBorder(new EtchedBorder(
		EtchedBorder.LOWERED), language.get("gMMapPreview"),
		TitledBorder.LEFT, TitledBorder.TOP);
	mapPreviewBox.setBorder(mapPreviewBoxBorder);
	mapPreviewBox.setMinimumSize(new Dimension(50, 50));
	mapPreviewBox.setPreferredSize(new Dimension(200, 200));
	mapPreviewBox.setMaximumSize(new Dimension(Short.MAX_VALUE,
		Short.MAX_VALUE));
	// Set the background color to white
	mapPreviewBox.setBackground(Color.white);
	return mapPreviewBox;
    }

    /**
     * Get the player number box
     * 
     * @return playerNumberBox Box with number of players
     */
    public JComponent getPlayerNumberBox() {
	// Init the panel
	playerNumberBox = new JPanel();
	// set the layout to flowlayout
	playerNumberBox.setLayout(new FlowLayout());
	// Init and set the border
	playerNumberBoxBorder = new TitledBorder(new EtchedBorder(
		EtchedBorder.LOWERED), language.get("gMNumberOfPlayers"),
		TitledBorder.LEFT, TitledBorder.TOP);
	playerNumberBox.setBorder(playerNumberBoxBorder);
	// set the background color to white
	playerNumberBox.setBackground(Color.white);
	// Setup the players label
	players = new JLabel(" " + language.get("gMPlayer"));
	players.setFont(new Font("Arial", 1, 20));
	playerNumberBox.add(numberOfPlayers);
	playerNumberBox.add(players);
	return playerNumberBox;

    }

    /**
     * This method returns the configured "showthiefbox" whiche contents the
     * checkbox for the possible thief position function
     * 
     * @return showThiefBox Box with show thief option
     */
    public JComponent getShowThiefBox() {
	// Init the panel
	showThiefBox = new JPanel();
	// Set the layout to borderLayout
	showThiefBox.setLayout(new BorderLayout());
	showThiefBox.setBackground(Color.WHITE);
	// Init and set the Border
	showThiefBoxBorder = new TitledBorder(new EtchedBorder(
		EtchedBorder.LOWERED), language.get("gMShowThiefBox"),
		TitledBorder.LEFT, TitledBorder.TOP);
	showThiefBox.setBorder(showThiefBoxBorder);

	// Get the "gMPossibleThiefLabel" text from the language file and set
	// this text
	// to the possibleThief label
	possibleThief = new JLabel(language.get("gMPossibleThiefLabel"));
	// Set the font
	possibleThief.setFont(new Font("Arial", 1, 14));

	checkThiefFunction = new JCheckBox();
	checkThiefFunction.setSelected(false);

	showThiefBox.setMinimumSize(new Dimension(60, 60));
	showThiefBox.setPreferredSize(new Dimension(200, 60));
	showThiefBox.setMaximumSize(new Dimension(Short.MAX_VALUE,
		Short.MAX_VALUE));
	showThiefBox.add(possibleThief, BorderLayout.CENTER);
	showThiefBox.add(checkThiefFunction, BorderLayout.EAST);

	return showThiefBox;
    }

    /**
     * Returns the specialTicketArray which contains the count of evry
     * 
     * @return array with number of special tickets
     */
    public Integer[] getSpecialTicketArray() {
	return specialTicketArray;
    }

    /**
     * This method return's the ticketChooseBox, which includes the
     * specialticket choose boxes.
     * 
     * @return ticketChooseBox Box for special ticket choosing
     */
    public JComponent getTicketChooseBox() {
	ticketChooseBox = new JPanel();
	ticketChooseBox.setLayout(new GridBagLayout());

	final GridBagLayout gbOrientationPanel = new GridBagLayout();
	gbOrientationPanel.columnWidths = new int[] { 0 };
	gbOrientationPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };

	ticketChooseBox.setLayout(gbOrientationPanel);

	ticketChooseBox.setBackground(Color.white);
	ticketChooseBoxBorder = new TitledBorder(new EtchedBorder(
		EtchedBorder.LOWERED), language.get("gMTicketChoose"),
		TitledBorder.LEFT, TitledBorder.TOP);
	ticketChooseBox.setBorder(ticketChooseBoxBorder);
	doubleTicketsThiefs = new JComboBox<String>(language.get(
		"gMThiefDoubleTickets").split(","));
	blackTicketsThiefs = new JComboBox<String>(language.get(
		"gMThiefBlackTickets").split(","));
	doubleTicketsPolice = new JComboBox<String>(language.get(
		"gMPoliceDoubleTickets").split(","));
	blackTicketsPolice = new JComboBox<String>(language.get(
		"gMPoliceBlackTickets").split(","));
	doubleTicketsThiefs.setFont(new Font("Arial", 1, 14));
	blackTicketsThiefs.setFont(new Font("Arial", 1, 14));
	doubleTicketsPolice.setFont(new Font("Arial", 1, 14));
	blackTicketsPolice.setFont(new Font("Arial", 1, 14));
	doubleTicketsThiefs.setSelectedIndex(3);
	blackTicketsThiefs.setSelectedIndex(2);

	final GridBagConstraints gc = new GridBagConstraints();
	gc.insets = new Insets(2, 3, 4, 5);
	gc.fill = GridBagConstraints.HORIZONTAL;
	gc.gridy = 0;
	ticketChooseBox.add(doubleTicketsThiefs, gc);
	doubleTicketsThiefs.setAlignmentX(LEFT_ALIGNMENT);
	gc.gridy = 1;
	ticketChooseBox.add(blackTicketsThiefs, gc);
	gc.gridy = 2;
	ticketChooseBox.add(doubleTicketsPolice, gc);
	gc.gridy = 3;
	ticketChooseBox.add(blackTicketsPolice, gc);
	return ticketChooseBox;
    }

    /**
     * The init method which initializes every element.
     * 
     */
    public void init() {
	setLayout(new BorderLayout());
	setBackground(Color.white);

	eastBox = new JPanel();
	eastBox.setBackground(Color.LIGHT_GRAY);
	eastBox.setLayout(new BoxLayout(eastBox, BoxLayout.Y_AXIS));

	this.add(getMapPreviewBox(), BorderLayout.CENTER);
	eastBox.add(getPlayerNumberBox());
	eastBox.add(new Box.Filler(new Dimension(100, 0),
		new Dimension(100, 80), new Dimension(100, 100)));
	eastBox.add(getTicketChooseBox());
	eastBox.add(new Box.Filler(new Dimension(100, 0),
		new Dimension(100, 80), new Dimension(100, 100)));
	eastBox.add(getInfoBox());
	eastBox.add(new Box.Filler(new Dimension(100, 0),
		new Dimension(100, 80), new Dimension(100, 100)));
	eastBox.add(getShowThiefBox());
	eastBox.add(new Box.Filler(new Dimension(100, 0), new Dimension(100,
		500), new Dimension(100, 700)));
	this.add(eastBox, BorderLayout.EAST);
	addListener(new SMapSettingHandler());
    }

    /**
     * When the DATA_MAP_DATA event is processed this method writes the html
     * String with the max figure count from the current config. The Label
     * tickets will also be added to the ticketTypeBox.
     */
    public void maxFiguresSet() {
	final int a = config.getStartat().size();
	maxFiguresString = new String("<html><br>" + a + "</html>");
	maxFigures = new JLabel(maxFiguresString);
	maxFigures.setFont(new Font("Arial", 1, 20));
	maxFiguresBox.add(maxFigures);
    }

    /**
     * processes the language and Data_Map_Data event which deliveres the chosen
     * map(image) and the map informations from the .ini file.
     * 
     * @param event A event
     */
    public void processEvent(final IAEvent event) {
	if (event.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
	    switchLanguage(((ELanguageSwitched) event).getLanguage());
	} else if (event.getEventType() == EventConstants.DATA_MAP_DATA) {
	    config = ((EDataMapData) event).getConfig();
	    ticketTypeBox.removeAll();
	    maxFiguresBox.removeAll();
	    ticketTypeSet();
	    maxFiguresSet();
	    checkNumberOfPlayer();
	    currentMapImage = (ImagePanel) ((EDataMapData) event).getMapImage();
	    currentMapImage.scaleImage(((float) mapPreviewBox.getWidth() - 10)
		    / currentMapImage.img.getWidth());
	    mapPreviewBox.removeAll();
	    mapPreviewBox.add(currentMapImage, BorderLayout.CENTER);

	}
    }

    /**
     * This method changes every element title or text to the current language
     * if requested.
     * 
     * @param language Map with language data
     */
    public void switchLanguage(final Map<String, String> language) {

	players.setText(language.get("gMPlayer"));
	mapPreviewBoxBorder.setTitle(language.get("gMMapPreview"));
	playerNumberBoxBorder.setTitle(language.get("gMNumberOfPlayers"));
	infoBoxBorder.setTitle(language.get("gMMapInfo"));
	ticketTypBoxBorder.setTitle(language.get("gMTicketTyp"));
	maxFiguresBoxBorder.setTitle(language.get("gMMaxFigures"));
	possibleThief.setText(language.get("gMPossibleThiefLabel"));
	showThiefBoxBorder.setTitle(language.get("gMShowThiefBox"));
	switchTicketLanguage(language);
    }

    /**
     * This method switches the ticket language.
     * 
     * @param language Map with language data
     */
    public void switchTicketLanguage(final Map<String, String> language) {

	// Because each ComboBox is filled with strings the have to be
	// initialized again.
	ticketChooseBox.removeAll();
	ticketChooseBox.setBackground(Color.white);
	ticketChooseBoxBorder = new TitledBorder(new EtchedBorder(
		EtchedBorder.LOWERED), language.get("gMTicketChoose"),
		TitledBorder.LEFT, TitledBorder.TOP);
	ticketChooseBox.setBorder(ticketChooseBoxBorder);

	final Integer[] indexArray = new Integer[4];

	indexArray[0] = doubleTicketsThiefs.getSelectedIndex();
	indexArray[1] = blackTicketsThiefs.getSelectedIndex();
	indexArray[2] = doubleTicketsPolice.getSelectedIndex();
	indexArray[3] = blackTicketsPolice.getSelectedIndex();

	doubleTicketsThiefs = new JComboBox<String>(language.get(
		"gMThiefDoubleTickets").split(","));
	blackTicketsThiefs = new JComboBox<String>(language.get(
		"gMThiefBlackTickets").split(","));
	doubleTicketsPolice = new JComboBox<String>(language.get(
		"gMPoliceDoubleTickets").split(","));
	blackTicketsPolice = new JComboBox<String>(language.get(
		"gMPoliceBlackTickets").split(","));

	doubleTicketsThiefs.setSelectedIndex(indexArray[0]);
	blackTicketsThiefs.setSelectedIndex(indexArray[1]);
	doubleTicketsPolice.setSelectedIndex(indexArray[2]);
	blackTicketsPolice.setSelectedIndex(indexArray[3]);

	doubleTicketsThiefs.setFont(new Font("Arial", 1, 14));
	blackTicketsThiefs.setFont(new Font("Arial", 1, 14));
	doubleTicketsPolice.setFont(new Font("Arial", 1, 14));
	blackTicketsPolice.setFont(new Font("Arial", 1, 14));

	final GridBagConstraints gc = new GridBagConstraints();
	gc.insets = new Insets(2, 3, 4, 5);
	gc.fill = GridBagConstraints.HORIZONTAL;
	gc.gridy = 0;
	ticketChooseBox.add(doubleTicketsThiefs, gc);
	doubleTicketsThiefs.setAlignmentX(LEFT_ALIGNMENT);
	gc.gridy = 1;
	ticketChooseBox.add(blackTicketsThiefs, gc);
	gc.gridy = 2;
	ticketChooseBox.add(doubleTicketsPolice, gc);
	gc.gridy = 3;
	ticketChooseBox.add(blackTicketsPolice, gc);

    }

    /**
     * When the DATA_MAP_DATA event is processed this method writes the html
     * String with ticket types from the current config. The Label tickets will
     * also be added to the ticketTypeBox.
     */
    public void ticketTypeSet() {
	ticketString = new String("<html>");
	for (int i = 0; i < config.getTicketTypes().length; i++) {
	    ticketString = ticketString.concat("<br/>"
		    + config.getTicketTypes()[i]);
	}
	ticketString = ticketString.concat("<br/></html>");
	tickets = new JLabel(ticketString);
	tickets.setFont(new Font("Arial", 1, 14));
	ticketTypeBox.add(tickets);
    }
}
