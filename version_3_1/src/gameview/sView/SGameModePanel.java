package gameview.sView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import core.IConfig;
import events.EventConstants;
import events.IAEvent;
import events.logic.global.ELanguageSwitched;
import events.logic.startState.EDataMapNames;

/**
 * This is the panel which is shown after pressing "new game" in the start menu.
 * The left one is for choosing the map. If you had chosen a map in the right
 * panel, settings for the gameplay will appear. You can set the number of
 * players. More options are possible.
 * 
 * @author Raphael A.
 * @version 3.0
 */
public class SGameModePanel extends JPanel {

    /**
     * The action listener created for the gameModePanel
     * 
     * @author Raphael
     * 
     */
    public class SGameModeHandler implements ActionListener {
	/**
	 * method for the menuitem eventhandling
	 * 
	 * @since 1.2
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param event a action event
	 */
	@Override
	public void actionPerformed(final ActionEvent event) {
	    if (event.getSource().equals(next)) {
		sState.onPanePGameMode(false);
		sState.onPanePPlayerSetting(true);
		sState.playerNumberSelect(event.getSource(),
			chosenPlayerNumber,
			sMapSettingPanel.getSpecialTicketArray());
	    }
	    if (event.getSource().equals(abort)) {
		mapList.setSelectedIndex(0);
		sState.onPanePGameMode(false);
		sState.onPanePMenu(true);
	    }
	}
    }

    /**
     * This is the ListSelectionListener which checks the chosen map in the
     * maplist.
     * 
     * @author Raphael
     * 
     */
    public class SharedListSelectionHandler implements ListSelectionListener {
	@Override
	public void valueChanged(final ListSelectionEvent event) {
	    final ListSelectionModel lsm = (ListSelectionModel) event
		    .getSource();
	    final boolean isAdjusting = event.getValueIsAdjusting();
	    if (!lsm.isSelectionEmpty()) {
		next.setEnabled(true);
		next.setForeground(Color.DARK_GRAY);
		sState.playerNumberSelect(event.getSource(),
			chosenPlayerNumber,
			sMapSettingPanel.getSpecialTicketArray());
		// Find out which indexes are selected.
		final int minIndex = lsm.getMinSelectionIndex();
		final int maxIndex = lsm.getMaxSelectionIndex();
		for (int i = minIndex; i <= maxIndex; i++) {
		    if (isAdjusting) {
			if (lsm.isSelectedIndex(i)) {
			    sState.mapSelected(event, maps[i]);
			}
		    }
		}
	    }
	}
    }

    // initialization of every gui Element.
    JComponent contentBox, settingBox, mapBox, ticketChooseBox;
    SGMapSettingPanel sMapSettingPanel;
    JButton next, abort;
    JList<String> mapList;
    JLabel gameModeTitel;
    TitledBorder settingBoxBorder, mapBoxBorder, ticketChooseBoxBorder;
    GridBagConstraints gc;
    String ticketString, maxFiguresString;
    IConfig config;

    String[] maps = { "Karten nicht geladen" };
    ListSelectionModel listSelectionModel;

    String[] tickets = { "0", "1", "2", "3", "4", "5" };
    JComboBox<String> mapComboBox, doubleTicketsThiefs, doubleTicketsPolice,
	    blackTicketsThiefs, blackTicketsPolice;
    Dimension minSize = new Dimension(5, 10);

    Dimension prefSize = new Dimension(5, 25);

    Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);

    private final Map<String, String> language;
    SState sState;
    // Config temp
    String chosenMap = new String("No map chosen");

    String chosenPlayerString = new String("2");

    int chosenPlayerNumber = 2;

    private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param sState
	 *            The start state
	 * @param language
	 *            Map with language data
	 */
    public SGameModePanel(final SState sState,
	    final Map<String, String> language) {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	this.language = language;
	this.sState = sState;
    }

    /**
     * Add listeners.
     * 
     * @param action Action listener
     */
    public void addListener(final ActionListener action) {
	next.addActionListener(action);
	abort.addActionListener(action);
    }

    /**
     * Method to set up the "map box" panel and to add the map list to the pane.
     * 
     * @return mapBox Box for the map
     */
    public JComponent getMapBox() {
	mapBox = new JPanel();
	mapBox.setLayout(new BoxLayout(mapBox, BoxLayout.X_AXIS));
	mapBoxBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED),
		language.get("gMMaps"), TitledBorder.LEFT, TitledBorder.TOP);
	mapBoxBorder.setTitleColor(Color.black);
	mapBox.setBorder(mapBoxBorder);
	mapBox.setBackground(Color.WHITE);
	mapList = getMapList();
	mapBox.add(mapList);
	return mapBox;
    }

    /**
     * Method to set up the map list settings.
     * 
     * @return mapList List with map names
     */
    public JList<String> getMapList() {
	mapList = new JList<String>(maps);
	mapList.setSelectedIndex(0);
	mapList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	mapList.setLayoutOrientation(JList.VERTICAL);
	mapList.setVisibleRowCount(-1);
	mapList.setFont(new Font("Arial", 1, 20));
	mapList.setBackground(Color.white);
	listSelectionModel = mapList.getSelectionModel();
	listSelectionModel
		.addListSelectionListener(new SharedListSelectionHandler());
	return mapList;
    }

    /**
     * This method returns a String array which contains every map name.
     * 
     * @return String[] maps
     */
    public String[] getMaps() {
	return maps;
    }

    /**
     * The init method which initializes every GUI element of the game mode
     * Panel.
     */
    public void init() {
	setBackground(Color.DARK_GRAY);
	sMapSettingPanel = new SGMapSettingPanel(this, sState, language);
	sMapSettingPanel.init();

	setupTitle();

	setupContentBox();

	setupButtonBox();

	addListener(new SGameModeHandler());
    }

    /**
     * Process a event
     * 
     * @param event A event
     */
    public void processEvent(final IAEvent event) {
	if (sMapSettingPanel != null) {
	    sMapSettingPanel.processEvent(event);
	}
	if (event.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
	    switchLanguage(((ELanguageSwitched) event).getLanguage());
	} else if (event.getEventType() == EventConstants.DATA_MAP_NAMES) {
	    maps = ((EDataMapNames) event).getMapNames().toArray(maps);
	    sState.getPGV().getMsgPump().logInfo("Map names loaded");
	    mapBox.remove(mapList);
	    mapList = getMapList();
	    mapBox.add(mapList);
	}
    }

    /**
     * If the player number is configured in the SGMapSettingPanel, the number
     * has to be delivered to the gameModePanel so it can be send with the
     * NewChosePlayerCountEvent.
     * 
     * @param number Number of players
     */
    public void setChosenPlayer(final int number) {
	chosenPlayerNumber = number;
    }

    /**
     * Setup of the "button box" panel with the two buttons "next" and "abort".
     */
    public void setupButtonBox() {
	next = new JButton(language.get("next"));
	abort = new JButton(language.get("abort"));
	abort.setFont(new Font("Arial", 1, 20));
	abort.setForeground(Color.DARK_GRAY);
	next.setFont(new Font("Arial", 1, 20));
	next.setForeground(Color.DARK_GRAY);
	final JComponent buttonBox = new JPanel();
	buttonBox.setLayout(new BoxLayout(buttonBox, BoxLayout.LINE_AXIS));

	buttonBox.add(new Box.Filler(minSize, new Dimension(
		Short.MAX_VALUE - 100, 100), maxSize));
	buttonBox.add(abort);
	buttonBox.add(new Box.Filler(minSize, prefSize, maxSize));
	buttonBox.add(next);
	buttonBox.add(new Box.Filler(minSize, prefSize, maxSize));
	buttonBox.setBackground(Color.DARK_GRAY);
	add(buttonBox);
    }

    /**
     * Setup of the big "content box" panel which holds the "map box" and the
     * "settings box" panel.
     */
    public void setupContentBox() {
	contentBox = new JPanel();
	contentBox.setLayout(new GridBagLayout());
	contentBox.setBackground(Color.DARK_GRAY);
	// contentBox.setMinimumSize(new Dimension(400, 150));

	final GridBagLayout gbOrientationPanel = new GridBagLayout();
	gbOrientationPanel.columnWidths = new int[] { 0, 0, 0, 0 };
	gbOrientationPanel.rowHeights = new int[] { 0 };
	gbOrientationPanel.columnWeights = new double[] { 0.07, 0.10, 0.70,
		0.13 };
	gbOrientationPanel.rowWeights = new double[] { 0.95, 0.05 };
	contentBox.setLayout(gbOrientationPanel);
	gc = new GridBagConstraints();
	gc.insets = new Insets(0, 0, 0, 0);
	gc.gridx = 0;
	gc.gridy = 0;
	contentBox.add(Box.createHorizontalGlue(), gc);
	gc.gridx = 1;
	contentBox.add(getMapBox(), gc);
	gc.fill = GridBagConstraints.BOTH;
	gc.gridx = 2;
	contentBox.add(sMapSettingPanel, gc);
	gc.gridx = 3;
	contentBox.add(Box.createHorizontalGlue(), gc);

	gc.gridy = 100;
	gc.gridwidth = 4;
	gc.gridx = 0;
	gc.gridy = 1;
	contentBox.add(Box.createHorizontalGlue(), gc);
	add(contentBox);
    }

    /**
     * Setup of the titel
     */
    public void setupTitle() {
	gameModeTitel = new JLabel(language.get("gMTitel"));
	gameModeTitel.setFont(new Font("Arial", 1, 42));
	gameModeTitel.setAlignmentX(CENTER_ALIGNMENT);
	gameModeTitel.setForeground(Color.white);
	add(new Box.Filler(minSize, prefSize, maxSize));
	add(gameModeTitel);
	add(new Box.Filler(minSize, prefSize, maxSize));
    }

    /**
     * This method changes every element title or text to the current language
     * if requested.
     * 
     * @param language Map with lanugage data
     */
    public void switchLanguage(final Map<String, String> language) {
	gameModeTitel.setText(language.get("gMTitel"));
	mapBoxBorder.setTitle(language.get("gMMaps"));
	next.setText(language.get("next"));
	abort.setText(language.get("abort"));
    }
}
