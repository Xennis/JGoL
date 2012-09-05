package gameview.gView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import logic.Constants;

import events.EventConstants;
import events.IAEvent;
import events.gameview.global.EPossibleThief;
import events.logic.gameState.EFigureKilled;
import events.logic.gameState.EInitNewGame;
import events.logic.gameState.EInitNewGameDone;
import events.logic.gameState.ENextFigure;
import events.logic.gameState.ENextRound;
import events.logic.gameState.IEPlayerMoved;
import events.logic.global.ELanguageSwitched;
import gameview.ImagePanel;
import gameview.PlayerGameView;
/**
 * Gamestate of JGoL, initializes a mappanel and severall other views such as figurelist etc for the playing phase. 
 * Basically this is the interface for the players.
 * Can receive and send events which are neccessary for the playing phase.
 * 
 * 
 * @author Figedi
 *
 */
public class GState implements ActionListener, ChangeListener {

	// <<Constants>>

	final int oRIGINALHEIGHT = 17;
	final int oRIGINALWIDTH = 33;

	// <<fields>>
	// lists containing passed data
	Map<String, GinvisibleRadioButton> buttonList;
	// original Coordinates of Buttons
	Map<String, Point> originalButtonList;
	// Scrolling properties, original sizes
	double scalingFactor = 1.0;
	Dimension originalSize;

	// <<GUI properties>>
	volatile ImagePanel mapPanel;
	BufferedImage refImage;
	JScrollPane scrollPane, listScroll;
	JPanel orientationPanel, playerChangedPanel;
	JSlider slider;
	JList<Object> sidebarJList;
	JLabel roundCounter, yourFigures, nextPlayer;
	JTabbedPane tabbedPane;
	JButton confButton, nextPlayerButton;
	JCheckBox doubleTicket;
	JTable tableThiefPositions, tableThiefTickets;
	// multilanguage support
	Map<String, String> languageMap;
	// event handling
	PlayerGameView pGV;

	// <<Event handling>>
	// List for all Figures, adressed by figure ID
	Map<Integer, EInitNewGame> figureList;
	// List for last known position of thieves
	// boolean indicating whether all figures are loaded
	boolean loadingDone = false;
	// current figure id
	int currentFigureID = -1;
	// player changed
	boolean playerChanged = false;
	int currentPlayer = -1;
	// current chosen position for figure id, current position
	String chosenPosition = "-1";
	// current chosen token for moving to another position
	String chosenToken;
	// current scrollrefposition, must be separated of chosenposition (last
	// known pos is in chosenpos & when a point is chosen but u want to
	// scroll..)
	String scrollRef = "-1";
	// current reachable links for position
	Map<String, Set<String>> currentReachableLinks;
	// Current round number
	int roundNumber = 1;
	// list for mappainting which thieves should be shown this round
	Map<Integer, String> thievesToShow;
	// cache of thievesshow, for the tabbar
	Map<Integer, String> thievesToShowCache;
	// queue for the correct order of figures in the jlist
	List<Integer> figureQueue;
	// thiefLog of used tickets
	Map<Integer, String> thiefLog;
	// double ticket checked value
	boolean doubleTicketChecked = false;
	// possible thieves show toggle
	boolean showPossibleThieves = false;
	// possible Thief positions
	Map<Integer, Set<String>> possibleThief;
	// boolean indicating whether a PGV has changed
	boolean pGVMove = true;

	/**
	 * 
	 * Constructor for MapView, creates a basic instance of the gameview
	 * (nonscaled) and other Views (not filled with data)
	 * 
	 * @param pGV
	 *            the playergameview, used for fireing events
	 * @param radioCoordinates
	 *            the coordinate map with the position data for each station
	 * @param map
	 *            a BufferdImage containing the actual map read from
	 *            resourcecache
	 * @param languageMap
	 *            the languagemap for multilanguage purposes
	 */
	public GState(PlayerGameView pGV, Map<String, Point> radioCoordinates,
			BufferedImage map, Map<String, String> languageMap) {
		//
		this.pGV = pGV;
		this.languageMap = languageMap;
		this.refImage = map;
		scrollPane = new JScrollPane();
		mapPanel = new ImagePanel(map);
		initMapScroller();
		originalSize = mapPanel.getSize();

		buttonList = new HashMap<String, GinvisibleRadioButton>();
		originalButtonList = radioCoordinates;
		figureList = new HashMap<Integer, EInitNewGame>();
		thiefLog = new HashMap<Integer, String>();
		thievesToShow = new HashMap<Integer, String>();
		thievesToShowCache = new HashMap<>();
		currentReachableLinks = new HashMap<>();
		initGridBagLayout();

		// initialisation on mainframe
		addGamePanels();
		// init of botthread
	}

	/**
	 * 
	 * This method paints all figures in figurelist on the map. On top of that,
	 * it puts the buttons on the map with the positions saved in
	 * originalButtonList
	 * 
	 * 
	 * @throws NullPointerException
	 *             in case the new mappanel in the repaint procedure is built
	 *             too slow, this one could throw a nullpointer
	 */
	public void setRadioMap() throws NullPointerException {
		// resetting the radiomap and creating buttongroup

		buttonList = new HashMap<String, GinvisibleRadioButton>();
		for (Map.Entry<String, Point> entry : originalButtonList.entrySet()) {
			Point tmpPoint = entry.getValue();
			String i = entry.getKey();
			// Creating the button with all its properties
			GinvisibleRadioButton own = new GinvisibleRadioButton();
			own.setBounds((int) tmpPoint.getX(), (int) tmpPoint.getY(),
					oRIGINALWIDTH, oRIGINALHEIGHT);
			own.setName(i);
			own.setActionCommand(i);
			own.addActionListener(this);

			mapPanel.add(own);

			buttonList.put(i, own);
			if (i.equals(chosenPosition)
					&& (pGVMove || (!pGVMove && figureList.get(currentFigureID)
							.getType() == Constants.POLICE_PLAYER_ID))) {
				addFigureIcon(chosenPosition, figureList.get(currentFigureID)
						.getFigureIcon(), figureList.get(currentFigureID)
						.getColor(), false);

			}

			if ((currentReachableLinks != null && currentReachableLinks
					.containsKey(i)) && pGVMove) {
				own.setAvailable();
			}

		}
		addFiguresToMap();
		scrollPane.setViewportView(mapPanel);

	}

	/**
	 * Helper method for setRadioMap/resizeHMap, adds all figures from
	 * figurelist on the map according to its type
	 * 
	 * 
	 */
	private void addFiguresToMap() {
		for (Map.Entry<Integer, EInitNewGame> entr : figureList.entrySet()) {
			// if show possible thieves toggle is on, paint a transluscent
			// figure
			if (showPossibleThieves && possibleThief.containsKey(entr.getKey())
					&& !possibleThief.get(entr.getKey()).isEmpty()) {
				Iterator<String> itr = possibleThief.get(entr.getKey())
						.iterator();
				while (itr.hasNext()) {
					addFigureIcon(itr.next(), entr.getValue().getFigureIcon(),
							entr.getValue().getColor(), true);

				}
			}
			if (thievesToShow.containsKey(entr.getKey())
					&& entr.getValue().getType() == Constants.THIEF_PLAYER_ID) {
				addFigureIcon(thievesToShow.get(entr.getKey()), entr.getValue()
						.getFigureIcon(), entr.getValue().getColor(), false);
				// if its a cop, paint him on the map
			} else if (entr.getValue().getType() == Constants.POLICE_PLAYER_ID) {
				addFigureIcon(entr.getValue().getFigurePosition(), entr
						.getValue().getFigureIcon(),
						entr.getValue().getColor(), false);
			}

			// not in thievestoshow and not a cop, but in the same team?
			else if (entr.getValue().getId() == figureList.get(currentFigureID)
					.getId()) {
				addFigureIcon(entr.getValue().getFigurePosition(), entr
						.getValue().getFigureIcon(),
						entr.getValue().getColor(), false);

			}
		}
	}

	/**
	 * Add the GamePanel on the Mainframe
	 * 
	 * 
	 */
	public void addGamePanels() {
		pGV.getMsgPump().addComponent(orientationPanel);
	}

	/**
	 * Destroy all relevant gamestateData and remove panels from MainFrame
	 * 
	 * 
	 */
	public void destroy() {
		// destroyOldView();
		thiefLog.clear();

		thievesToShow.clear();
		figureQueue.clear();
		currentReachableLinks.clear();
		figureList.clear();
		buttonList.clear();
		originalButtonList.clear();
		orientationPanel.removeAll();
		loadingDone = false;
		if (playerChanged) {
			pGV.getMsgPump().removeComponent(playerChangedPanel);
		} else {
			pGV.getMsgPump().removeComponent(orientationPanel);
		}
	}

	/**
	 * 
	 * Scroll to a point on the map (with centralization)
	 * 
	 * @param currentPos
	 *            The stationname to which it should be scrolled
	 */
	public void scrollToPoint(String currentPos) {
		if (!currentPos.equals("-1")) {
			Point dmy = originalButtonList.get(currentPos);
			int x = (int) (dmy.x * scalingFactor)
					- scrollPane.getViewport().getSize().width / 2;
			int y = (int) (dmy.y * scalingFactor)
					- scrollPane.getViewport().getSize().height / 2;
			if (x < 0) {
				x = 0;
			}
			if (y < 0) {
				y = 0;
			}
			scrollPane.getViewport().setViewPosition(new Point(x, y));
		}
	}

	/**
	 * Init the drag and scroll feature with this method and on top of that the
	 * mousewheel zoom feature
	 * 
	 */
	public void initMapScroller() {

		MouseInputAdapter mia = new MouseInputAdapter() {
			private final Point draggedPoint = new Point();

			// when mouse is dragged, get position and translate it to desired
			// location
			// considering the refposition from mousePressed (draggedpoint)
			// scrollrecttovisible for no flickering, viewposition was buggy

			public void mouseDragged(MouseEvent e) {
				scrollRef = "-1";
				JViewport viewPort = scrollPane.getViewport();
				Point currentPosition = viewPort.getViewPosition();

				Point cp = SwingUtilities.convertPoint(mapPanel, e.getPoint(),
						viewPort);
				currentPosition.translate(draggedPoint.x - cp.x, draggedPoint.y
						- cp.y);
				mapPanel.scrollRectToVisible(new Rectangle(currentPosition,
						viewPort.getSize()));
				draggedPoint.setLocation(cp);
			}

			// generate refposition

			public void mousePressed(MouseEvent e) {
				JViewport viewPort = scrollPane.getViewport();
				mapPanel.setCursor(Cursor
						.getPredefinedCursor(Cursor.HAND_CURSOR));

				Point cp = SwingUtilities.convertPoint(mapPanel, e.getPoint(),
						viewPort);
				draggedPoint.setLocation(cp);
			}

			// just fanciness, removing cursor when mouse is pressed and showing
			// it when mouse released

			public void mouseReleased(MouseEvent e) {
				mapPanel.setCursor(Cursor
						.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		};
		mapPanel.addMouseWheelListener(new MouseWheelListener() {

			public void mouseWheelMoved(MouseWheelEvent e) {
				if (e.getWheelRotation() > 0 && scalingFactor > 0.1) {
					scalingFactor -= 0.1;
				} else {
					scalingFactor += 0.1;
				}
				repaintImage();
				slider.setValue((int) (scalingFactor * 10));
			}
		});
		mapPanel.addMouseMotionListener(mia);
		mapPanel.addMouseListener(mia);
	}

	/**
	 * Init the gridbagLayout and put all basic elements on the orientationpanel
	 * 
	 */
	public void initGridBagLayout() {

		orientationPanel = new JPanel();
		orientationPanel.setBackground(Color.DARK_GRAY);

		GridBagLayout gbOrientationPanel = new GridBagLayout();
		gbOrientationPanel.columnWidths = new int[] { 0, 0, 0 };
		gbOrientationPanel.rowHeights = new int[] { 0, 0, 0, 0, 0 };
		gbOrientationPanel.columnWeights = new double[] { 0.775, 0.0125, 0.2 };
		gbOrientationPanel.rowWeights = new double[] { 0.0125, 0.7, 0.2375,
				0.0125, 0.0375 };
		orientationPanel.setLayout(gbOrientationPanel);
		initializeLabel();

		initFigureLabel();
		setRadioMap();
		initScrollPane();

		initializeSlider();

		initalizeJList();

		initializeTabBar();

		initDoubleCheckBox();

		initConfButton();
	}

	/**
	 * 
	 * Helper method for initgridbaglayout, initializes the confirmationbutton
	 * for player movements
	 * 
	 */
	private void initConfButton() {
		confButton = new JButton(languageMap.get("confirmText"));
		confButton.setEnabled(false);
		confButton.addActionListener(this);
		GridBagConstraints gcConfButton = new GridBagConstraints();
		gcConfButton.gridwidth = 1;
		gcConfButton.fill = GridBagConstraints.BOTH;
		gcConfButton.gridx = 2;
		gcConfButton.gridy = 4;
		orientationPanel.add(confButton, gcConfButton);
	}

	/**
	 * Helper method for initgridbaglayout, initializes the checkbox for
	 * doubletickets
	 * 
	 */
	private void initDoubleCheckBox() {
		doubleTicket = new JCheckBox(languageMap.get("doubleTicketText"));
		// firstly disabled, font color gray
		doubleTicket.setForeground(Color.gray);
		doubleTicket.setFont(new Font("Arial", Font.BOLD, 14));
		doubleTicket.setEnabled(false);
		doubleTicket.addChangeListener(new ChangeListener() {

			public void stateChanged(ChangeEvent e) {
				doubleTicketChecked = doubleTicket.isSelected();
			}
		});
		GridBagConstraints gcDouble = new GridBagConstraints();
		gcDouble.gridwidth = 1;
		gcDouble.gridheight = 1;
		gcDouble.fill = GridBagConstraints.BOTH;
		gcDouble.gridx = 2;
		gcDouble.gridy = 3;
		orientationPanel.add(doubleTicket, gcDouble);
	}

	/**
	 * Helper method for initGridgbaglayout, initializes the yourfigure label
	 * 
	 */
	private void initFigureLabel() {
		yourFigures = new JLabel(languageMap.get("yourfigures"));
		yourFigures.setForeground(Color.WHITE);
		yourFigures.setFont(new Font("Arial", Font.BOLD, 14));
		GridBagConstraints gcYourFigures = new GridBagConstraints();
		gcYourFigures.insets = new Insets(0, 0, 5, 0);
		gcYourFigures.gridx = 2;
		gcYourFigures.gridy = 0;
		orientationPanel.add(yourFigures, gcYourFigures);
	}

	/**
	 * Helper method for initGridbagLayout, initializes the scrollPane
	 * 
	 */
	private void initScrollPane() {
		scrollPane = new JScrollPane();
		GridBagConstraints gcScrollpane = new GridBagConstraints();
		gcScrollpane.insets = new Insets(0, 0, 0, 5);
		gcScrollpane.gridheight = 4;
		gcScrollpane.fill = GridBagConstraints.BOTH;
		gcScrollpane.gridx = 0;
		gcScrollpane.gridy = 1;
		orientationPanel.add(scrollPane, gcScrollpane);
	}

	/**
	 * Initialize the top status element, this one shows roundcounter and
	 * several status messages which are important to the game
	 * 
	 */
	public void initializeLabel() {
		String dmy = languageMap.get("Gamestats");
		dmy = dmy.replace("<1>", String.valueOf(roundNumber)).replace("<2>",
				String.valueOf(currentFigureID));
		roundCounter = new JLabel(dmy);
		roundCounter.setForeground(Color.WHITE);
		roundCounter.setFont(new Font("Arial", Font.BOLD, 14));
		roundCounter.setMaximumSize(new Dimension(500, 100));
		roundCounter.setPreferredSize(new Dimension(500, 100));
		roundCounter.setMinimumSize(new Dimension(500, 20));
		GridBagConstraints gcRoundCounter = new GridBagConstraints();
		gcRoundCounter.insets = new Insets(0, 0, 5, 5);
		gcRoundCounter.gridx = 0;
		gcRoundCounter.gridy = 0;
		orientationPanel.add(roundCounter, gcRoundCounter);
	}

	/**
	 * Initialize the slider element for manual map zooming
	 * 
	 */
	public void initializeSlider() {
		slider = new JSlider();
		// Adding Labels
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(1, new JLabel("0.1"));
		labelTable.put(3, new JLabel("0.3"));
		labelTable.put(6, new JLabel("0.6"));
		labelTable.put(10, new JLabel("1.0"));
		labelTable.put(14, new JLabel("1.4"));
		labelTable.put(20, new JLabel("2.0"));
		labelTable.put(26, new JLabel("2.6"));
		labelTable.put(32, new JLabel("3.2"));
		labelTable.put(40, new JLabel("4.0"));
		labelTable.put(50, new JLabel("5.0"));
		labelTable.put(60, new JLabel("6.0"));
		labelTable.put(72, new JLabel("7.2"));
		labelTable.put(84, new JLabel("8.4"));
		labelTable.put(100, new JLabel("10.0"));

		// Slider Properties
		slider.setLabelTable(labelTable);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		slider.setMajorTickSpacing(2);
		slider.setPaintTicks(true);
		slider.setValue((int) (scalingFactor * 10));

		GridBagConstraints gcSlider = new GridBagConstraints();
		gcSlider.gridheight = 4;
		gcSlider.fill = GridBagConstraints.BOTH;
		gcSlider.insets = new Insets(0, 0, 5, 5);
		gcSlider.gridx = 1;
		gcSlider.gridy = 1;

		slider.setOrientation(SwingConstants.VERTICAL);
		orientationPanel.add(slider, gcSlider);
	}

	/**
	 * 
	 * Initialize the playerChangeScreen, this screen removes the
	 * orientationpanel from mainframe and puts an own panel on the mainframe,
	 * thus hiding all gamedata so players can change seats
	 * 
	 */
	public void initPlayerChangeScreen() {
		pGV.getMsgPump().removeComponent(orientationPanel);
		Dimension minSize = new Dimension(5, 40);
		Dimension prefSize = new Dimension(5, 80);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
		playerChangedPanel = new JPanel();
		pGV.getMsgPump().addComponent(playerChangedPanel);

		playerChangedPanel.setBackground(Color.darkGray);
		playerChangedPanel.setLayout(new BoxLayout(playerChangedPanel,
				BoxLayout.Y_AXIS));
		playerChangedPanel.add(new Box.Filler(minSize, prefSize, maxSize));

		String content = languageMap.get("playerchange").replace("<1>",
				figureList.get(currentFigureID).getName());
		nextPlayer = new JLabel(content);
		nextPlayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextPlayer.setFont(new Font("Arial", Font.BOLD, 20));
		nextPlayer.setForeground(Color.white);
		playerChangedPanel.add(new Box.Filler(minSize, prefSize, maxSize));

		playerChangedPanel.add(nextPlayer);
		playerChangedPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		nextPlayerButton = new JButton(languageMap.get("confirmText"));
		nextPlayerButton.addActionListener(this);
		nextPlayerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		nextPlayerButton.setMinimumSize(new Dimension(50, 25));
		nextPlayerButton.setPreferredSize(new Dimension(260, 50));
		nextPlayerButton.setMaximumSize(new Dimension(280, 50));
		nextPlayerButton.setForeground(Color.DARK_GRAY);
		playerChangedPanel.add(nextPlayerButton);
	}

	/**
	 * Initializes a decision frame if more than one ticket is available for a
	 * move
	 * 
	 * 
	 * @return the tickettype as string or abort if the screen has been closed
	 *         or abort has been pressed
	 */
	public String initializeConfScreen() {
		JFrame frame = new JFrame();
		// noMovePossible depreceated, since this is now handled directly in
		// confirmbutton (actionperformed)
		Iterator<String> itr = currentReachableLinks.get(chosenPosition)
				.iterator();
		Object[] content = new Object[currentReachableLinks.get(chosenPosition)
				.size() + 1];
		int i = 0;
		try {
			while (itr.hasNext()) {
				content[i] = itr.next();
				i++;
			}
			// appending abort button at last position
			content[i] = languageMap.get("abortText");
		} catch (ArrayIndexOutOfBoundsException e) {
			pGV.getMsgPump().logError(
					"Index out of bounds when trying to fill option dialog");
		}
		String question = languageMap.get("ConfDesc")
				.replace("<1>", String.valueOf(currentFigureID))
				.replace("<2>", String.valueOf(chosenPosition));

		int n = JOptionPane.showOptionDialog(frame, question, null,
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, content, null);
		if (n == content.length - 1 || n == -1) {
			return "abort";
		} else {
			return (String) content[n];
		}

	}

	/**
	 * Initializes the TabBar element with Stats about thieves
	 * 
	 */
	public void initializeTabBar() {
		int idx = 0;
		if (tabbedPane != null) {
			idx = tabbedPane.getSelectedIndex();
			tabbedPane.removeAll();
			orientationPanel.remove(tabbedPane);
		} else {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		}
		tabbedPane.setDoubleBuffered(true);
		GridBagConstraints gcTabbedPane = new GridBagConstraints();
		gcTabbedPane.fill = GridBagConstraints.BOTH;
		gcTabbedPane.insets = new Insets(0, 0, 5, 0);
		gcTabbedPane.gridx = 2;
		gcTabbedPane.gridy = 2;
		initJTables();

		tabbedPane.setSelectedIndex(idx);
		orientationPanel.add(tabbedPane, gcTabbedPane);
	}

	/**
	 * Initializes the jtables within the tabbar
	 * 
	 */
	private void initJTables() {
		// thief's last known position
		tableThiefTickets = new JTable();
		tableThiefTickets.setPreferredSize(new Dimension(600, 300));
		tableThiefTickets.setBackground(Color.DARK_GRAY);
		tableThiefTickets.setForeground(Color.white);
		tableThiefTickets.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollContainer = new JScrollPane(tableThiefTickets,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab(languageMap.get("TabThiefTickets"), null,
				scrollContainer, null);
		tableThiefTickets.setEnabled(false);

		tableThiefPositions = new JTable();
		tableThiefPositions.setModel(fillThiefPositionTable(thievesToShowCache,
				"Figure", "Last known Position"));
		tableThiefPositions.setPreferredSize(new Dimension(600, 300));
		tableThiefPositions.setBackground(Color.DARK_GRAY);
		tableThiefPositions.setForeground(Color.white);
		tableThiefPositions.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableThiefPositions.setEnabled(false);
		JScrollPane scrollcontainer1 = new JScrollPane(tableThiefPositions,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabbedPane.addTab(languageMap.get("TabThiefPos"), null,
				scrollcontainer1, null);
		tableThiefTickets.setModel(fillThiefTicketTable(thiefLog, "Event"));
		tableThiefPositions.setModel(setTHiefPositionData(thievesToShowCache));

	}

	/**
	 * Helper method for data filling of thief position screen.
	 * 
	 * 
	 * @param data
	 *            a Hashmap with data, should be thief cache
	 * @return a tablemodel ready to be bound into the jtable
	 */
	private TableModel setTHiefPositionData(Map<Integer, String> data) {
		String[] columnNames = languageMap.get("thiefPosition").split(",");
		try {
			return fillThiefPositionTable(data, columnNames[0], columnNames[1]);

		} catch (IndexOutOfBoundsException e) {
			pGV.getMsgPump().logError(
					"Error during ThiefPosition table data filling, check your language file"
							+ e.getStackTrace());
		}
		return null;
	}

	/**
	 * Helper method for inittabbar (for contents of thief tickets)
	 * 
	 * 
	 * @param data
	 *            A HashMap with data which should be shown in the table
	 * @param title
	 *            The title of the columns
	 * @return a Tablemodel which is used for the content filling of the table
	 */
	private TableModel fillThiefTicketTable(Map<Integer, String> data,
			String title) {
		String[] column = { title };
		DefaultTableModel model = new DefaultTableModel(column, 0);
		for (Map.Entry<Integer, String> entry : data.entrySet()) {
			String value = entry.getValue();
			String[] languageReplaces = languageMap.get("thiefTickets").split(
					",");
			value = value.replace("<1>", languageReplaces[0]);
			value = value.replace("<2>", languageReplaces[1]);
			value = value.replace("<3>", languageReplaces[2]);
			value = value.replace("<4>", languageReplaces[3]);
			model.addRow(new Object[] { value });
		}
		return model;
	}

	/**
	 * 
	 * Helper method for inittabbar(for contents of thief positions)
	 * 
	 * @param data
	 *            A HashMap with data, which should be shown in the table
	 * @param left
	 *            The left column title
	 * @param right
	 *            The right column title
	 * @return a Tablemodel which is used for the content filling of the table
	 */
	private TableModel fillThiefPositionTable(Map<Integer, String> data,
			String left, String right) {
		String[] column = { left, right };
		DefaultTableModel model = new DefaultTableModel(column, 0);
		for (Map.Entry<Integer, String> entry : data.entrySet()) {
			model.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		return model;
	}

	/**
	 * Initializes the Listeners for the sidebarJList
	 * 
	 */
	private void initializeListListener() {

		sidebarJList.addListSelectionListener(new ListSelectionListener() {

			public void valueChanged(ListSelectionEvent e) {
				// first we get the index of the selection, since this one
				// doesnt correspond to the actual figure id, we need to extract
				// it and
				// after that we are able to query our figurelist for comparsion
				int idx = sidebarJList.getSelectedIndex();
				// >=0 assures that the list is filled, this may not be the case
				// in some weird, not synchronized cases.
				if (idx >= 0) {
					// extract figureid with figurequeue
					int id = figureQueue.get(idx);
					String position = figureList.get(id).getFigurePosition();
					try {

						testZoomRules(id, position);
					} catch (Exception f) {
						pGV.getMsgPump().logError(
								"Error during figure list selection scroll"
										+ f.getMessage() + " (cause) "
										+ f.getCause());
					}
				}
			}
		});
		sidebarJList.setCellRenderer(new ListCellRenderer<Object>() {

			public Component getListCellRendererComponent(JList<?> list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				Component component = (Component) value;
				int idx = sidebarJList.getSelectedIndex();

				// copied from above, does the same thing, just with
				// highlighting
				if (idx >= 0) {
					try {
						testHighlightRules(component, isSelected, idx);
					} catch (Exception f) {
						pGV.getMsgPump().logError(
								"Error during figure list selection highlight"
										+ f.getMessage() + " (cause) "
										+ f.getCause());
					}
				}

				return component;
			}
		});
	}

	/**
	 * Initializes the JList element on the right, showing all figures of the
	 * game
	 * 
	 */

	public void initalizeJList() {
		sidebarJList = new JList<Object>();
		sidebarJList.setLayoutOrientation(JList.VERTICAL);
		sidebarJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sidebarJList.setBackground(Color.LIGHT_GRAY);
		initializeListListener();
		GridBagConstraints gcListScroll = new GridBagConstraints();
		gcListScroll.fill = GridBagConstraints.BOTH;
		gcListScroll.insets = new Insets(0, 0, 5, 0);
		gcListScroll.gridx = 2;
		gcListScroll.gridy = 1;
		listScroll = new JScrollPane(sidebarJList);

		orientationPanel.add(listScroll, gcListScroll);
	}

	/**
	 * Helper method for the figurelist, determines whether to scroll to a
	 * figure or not
	 * 
	 * 
	 * @param id
	 *            the figureId which is selected
	 * @param position
	 *            the Stationposition
	 * @throws Exception
	 *             if a selection is out of range or nulled in figurelist due to
	 *             sync reasons, log the result
	 */
	private void testZoomRules(int id, String position) throws Exception {
		// thiefs can always see policemen and therefore are
		// able to scroll there all the time
		if (figureList.get(currentFigureID).getType() == Constants.THIEF_PLAYER_ID
				&& figureList.get(id).getType() == Constants.POLICE_PLAYER_ID) {
			scrollRef = position;
		}
		// cops can only scroll to thieves when they are visible
		else if (figureList.get(currentFigureID).getType() == Constants.POLICE_PLAYER_ID
				&& figureList.get(id).getType() == Constants.THIEF_PLAYER_ID
				&& thievesToShow.containsKey(id)) {
			scrollRef = position;
		} else if (figureList.get(currentFigureID).getType() == figureList.get(
				id).getType()) {
			scrollRef = position;

		}
		// scrolling to own figure is possible too :)
		else if (id == currentFigureID) {
			scrollRef = position;

		} else {
			scrollRef = "-1";
		}
		repaintImage();
	}

	/**
	 * Helper method for the cellrenderer of the figureList, determines whether
	 * to highlight a cell or not
	 * 
	 * 
	 * @param component
	 *            The component of the cellrenderer, call by ref
	 * @param isSelected
	 *            the boolean indicating whether a selection is made
	 * @param idx
	 *            the index of the selection
	 * @throws Exception
	 *             if a selection is out of range due to sync reasons, log the
	 *             result
	 */
	private void testHighlightRules(Component component, boolean isSelected,
			int idx) throws Exception {
		int id = figureQueue.get(idx);
		if (figureList.get(currentFigureID).getType() == Constants.THIEF_PLAYER_ID
				&& figureList.get(id).getType() == Constants.POLICE_PLAYER_ID) {
			component.setBackground(isSelected ? Color.white : Color.lightGray);
			component.setForeground(isSelected ? Color.lightGray : Color.white);
		} else if (figureList.get(currentFigureID).getType() == Constants.POLICE_PLAYER_ID
				&& figureList.get(id).getType() == Constants.THIEF_PLAYER_ID
				&& thievesToShow.containsKey(id)) {
			component.setBackground(isSelected ? Color.white : Color.lightGray);
			component.setForeground(isSelected ? Color.lightGray : Color.white);

		} else if (figureList.get(currentFigureID).getType() == figureList.get(
				id).getType()) {
			component.setBackground(isSelected ? Color.white : Color.lightGray);
			component.setForeground(isSelected ? Color.lightGray : Color.white);
		} else if (id == currentFigureID) {
			component.setBackground(isSelected ? Color.white : Color.lightGray);
			component.setForeground(isSelected ? Color.lightGray : Color.white);
		}
	}

	/**
	 * 
	 * Helper method for the figureList, this method fills the List with Data
	 * 
	 * 
	 */
	public void addFigureToList() {
		// if loading isnt done or no new player, return
		if (loadingDone && figureList != null) {
			Map<Integer, JPanel> listElements = new HashMap<>();
			for (Map.Entry<Integer, EInitNewGame> entry : figureList.entrySet()) {
				BufferedImage img = entry.getValue().getFigureIcon();
				ImageIcon imgIcon = new ImageIcon(img);
				String content = "<html>"
						+ languageMap
								.get("figurelist")
								.replace(
										"<1>",
										String.valueOf(entry.getValue()
												.getFigureId()))
								.replace("<2>", entry.getValue().getName());
				content += "<br />"
						+ entry.getValue().getFigureTokens().toString()
						+ "</html>";
				JLabel figureName = new JLabel(content);
				if (entry.getValue().getFigureId() == currentFigureID) {
					figureName.setForeground(Color.red);
				}
				JLabel figureIcon = new JLabel(imgIcon);
				JPanel pan = new JPanel(new FlowLayout(FlowLayout.CENTER));
				pan.add(figureName);
				pan.add(figureIcon);
				listElements.put(entry.getValue().getFigureId(), pan);
			}

			// Creating an array for list setup with correct order, this is
			// needed
			// since JList only accepts a Vector or Array (+ we need to
			// preserve
			// the player queue
			JPanel[] panelArray = new JPanel[figureList.size()];
			int selectedindex = -1;
			if (figureQueue != null) {
				for (int i = 0; i < figureList.size(); i++) {
					panelArray[i] = listElements.get(figureQueue.get(i));
					if (figureQueue.get(i) == currentFigureID) {
						selectedindex = i;
					}
				}
			}

			sidebarJList.setListData(panelArray);
			sidebarJList.setSelectedIndex(selectedindex);

		}
	}

	/**
	 * Helper method for actionPerformed, this method sends to the event system
	 * a move of a player The position is already chosen when clicking on the
	 * map on a available point
	 * 
	 * @param token
	 *            The token which should be chosen for the move
	 */
	public void sendPlayerMove(String token) {
		// if player has Chosen a token in conf screen and conf
		// screen is active, fire event
		pGV.eventGenerator.firePlayerRequestedMove(this, token, chosenPosition,
				doubleTicketChecked);
		confButton.setEnabled(false);
		doubleTicket.setSelected(false);
		// and kill values& windows after it
		chosenToken = null;
	}

	/**
	 * Helper method for actionPerformed, this method registers a position when
	 * the user clicked on the map On top of that, the scrollreference is set to
	 * this point, so when the user zooms, it is being centralized to that point
	 * 
	 * @param pos
	 *            the Stationname on which the user clicked
	 */
	public void registerChosenPosition(String pos) {
		// assert that it really is a button on the map and look in
		// CurrentReachableLinks if move is valid
		if (currentReachableLinks.containsKey(pos)) {
			chosenPosition = pos;
			EInitNewGame dmy = figureList.get(currentFigureID);
			dmy.setPosition(chosenPosition);
			confButton.setEnabled(true);
			figureList.put(currentFigureID, dmy);
			scrollRef = chosenPosition;
			repaintImage();
		}
	}

	/**
	 * 
	 * Gamestate wide actionPerformed method, this method handles Station
	 * Clicking on the map and COnfirmationbutton-Clicking
	 * 
	 * @param e
	 *            the event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {
			if (playerChanged) {
				pGV.getMsgPump().removeComponent(playerChangedPanel);
				pGV.getMsgPump().addComponent(orientationPanel);

				playerChanged = false;
				switchLanguage(languageMap);
				repaintImage();

			} else if (pGVMove) {

				JButton refButton = (JButton) e.getSource();
				processPgVButtonPressed(refButton);
			}
		} else if (e.getSource() instanceof GinvisibleRadioButton
				&& buttonList.containsKey(e.getActionCommand()) && pGVMove) {

			GinvisibleRadioButton buttonClicked = (GinvisibleRadioButton) e
					.getSource();
			String event = buttonClicked.getName();
			if (currentReachableLinks.containsKey(event)) {
				registerChosenPosition(event);
				confButton.setEnabled(true);

			}

		}
	}

	/**
	 * Helper method for button pressed while a human player moves, determines
	 * what ticket to choose or autochooses the only possible ticket then sends
	 * playermove to logic
	 * 
	 * 
	 * @param refButton
	 *            the button which is pressed, only the confirm button is being
	 *            used here
	 */
	private void processPgVButtonPressed(JButton refButton) {
		boolean sthChanged = false;
		if (refButton.getText().equals(languageMap.get("confirmText"))) {
			if (currentReachableLinks.containsKey(chosenPosition)
					&& currentReachableLinks.get(chosenPosition).size() > 1) {

				String n = initializeConfScreen();
				// ticket button chosen
				if (!n.equals("abort")) {
					sthChanged = false;
					chosenToken = n;
					sendPlayerMove(chosenToken);

				}
				// abort pressed
				else {
					sthChanged = true;
				}
				confButton.setEnabled(sthChanged);
			}
			// only 1 ticket available, auto choose it
			else if (currentReachableLinks.containsKey(chosenPosition)
					&& currentReachableLinks.get(chosenPosition).size() == 1) {
				Iterator<String> itr = currentReachableLinks
						.get(chosenPosition).iterator();
				while (itr.hasNext()) {
					chosenToken = itr.next();
				}
				sendPlayerMove(chosenToken);
			}
			// no ticket available, auto choose "none"
			else {
				sendPlayerMove("none");
			}
		}
	}

	/**
	 * 
	 * Destroys the mapPanel
	 * 
	 */
	public void destroyOldView() {
		scrollPane.remove(mapPanel);
		mapPanel.img.flush();
		mapPanel = null;
	}

	/**
	 * Resets the Image size (resetting scalingfactor to 1.0)
	 * 
	 * 
	 */
	public void resetImageSize() {
		scalingFactor = 1.0;
		repaintImage();
	}

	/**
	 * Repaint method for theMapPanel, this method deletes the old Panel and
	 * sets up a new one Use when new Figures should be painted on the map or
	 * the zooming factor has changed
	 * 
	 * 
	 */
	public void repaintImage() {
		if (!playerChanged) {
			// maintaining scrollposition

			Point tmp = scrollPane.getViewport().getViewPosition();
			// destroy old objects
			// normal repaint
			try {

				mapPanel.removeAll();
				mapPanel.scaleImage(scalingFactor);
				// "resizing" but with recent scalingfactor
				resizeHmap(scalingFactor, scalingFactor);

			} catch (NullPointerException e) {
				pGV.getMsgPump().logError(
						"Error during mapPanel repaint (Nullpointer) "
								+ e.getStackTrace());
			}
			if (!scrollRef.equals("-1") && pGVMove) {
				scrollToPoint(scrollRef);
			}

			else {
				scrollPane.getViewport().setViewPosition(tmp);
			}
		}
	}

	/**
	 * 
	 * Resizes components of the mappanel, for example Station coordinates are
	 * multiplied with the scalingfactor or figure sizes
	 * 
	 * 
	 * @param xFactor
	 *            scalingfactor for x-axis
	 * @param yFactor
	 *            scalingfactor for y-axis
	 * @throws NullPointerException
	 *             in case the new mappanel in the repaint procedure is built
	 *             too slow, this one could throw a nullpointer
	 */
	public void resizeHmap(double xFactor, double yFactor)
			throws NullPointerException {
		Map<String, GinvisibleRadioButton> dmyMap = new HashMap<String, GinvisibleRadioButton>();

		for (Map.Entry<String, GinvisibleRadioButton> entry : buttonList
				.entrySet()) {
			GinvisibleRadioButton dmy = entry.getValue();
			// cloning old buttons
			String i = entry.getKey();
			int x = (int) Math
					.round(originalButtonList.get(i).getX() * xFactor);
			int y = (int) Math
					.round(originalButtonList.get(i).getY() * yFactor);
			int w = (int) Math.round((oRIGINALWIDTH * xFactor));
			int h = (int) Math.round((oRIGINALHEIGHT * yFactor));
			dmy.setBounds(x, y, w, h);
			dmy.setUnavailable();

			mapPanel.add(dmy);
			dmyMap.put(i, dmy);
			// paint figure on recent position
			if (i.equals(chosenPosition)
					&& (pGVMove || (!pGVMove && figureList.get(currentFigureID)
							.getType() == Constants.POLICE_PLAYER_ID))) {
				if (figureList.get(currentFigureID) != null) {
					addFigureIcon(chosenPosition,
							figureList.get(currentFigureID).getFigureIcon(),
							figureList.get(currentFigureID).getColor(), false);
				}
			}

			// paint available buttons green

			if ((currentReachableLinks != null && currentReachableLinks
					.containsKey(i)) && pGVMove) {
				dmy.setAvailable();
			}
		}

		addFiguresToMap();
		// replacing entries
		buttonList = dmyMap;
		// update the RadioButton map with new Buttons

		scrollPane.setViewportView(mapPanel);
	}

	/**
	 * This method processes incoming events and changes gameData by calling the
	 * needed functions
	 * 
	 * 
	 * @param e
	 *            the event object, is filtered by the method itself through
	 *            global constants
	 */
	public void processEvent(IAEvent e) {
		if (e.getEventType() < EventConstants.LOGIC_END_GLOBAL) {
			processLogicGlobal(e);
		} else if (e.getEventType() < EventConstants.LOGIC_END_GAME_STATE) {
			processLogicGameState(e);
		}

	}

	/**
	 * Helper method for event processing, this method processes all global
	 * relevant events (such as language switch)
	 * 
	 * 
	 * @param e
	 *            the language event
	 */
	private void processLogicGlobal(IAEvent e) {
		if (e.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
			switchLanguage(((ELanguageSwitched) e).getLanguage());
		}
	}

	/**
	 * Helper method for event processing, this method processes all game
	 * relevant events
	 * 
	 * 
	 * @param e
	 *            the passed Eventobject
	 */
	private void processLogicGameState(IAEvent e) {
		if (e.getEventType() == EventConstants.INIT_NEW_GAME) {
			/*
			 * INIT NEW GAME fills the GState with data, it basically gives me
			 * all figures of every Player ==> saving it locally in a figurelist
			 */
			figureList.put(((EInitNewGame) e).getFigureId(), (EInitNewGame) e);
		} else if (e.getEventType() == EventConstants.INIT_NEW_GAME_DONE) {
			/*
			 * since the figurelist doesnt preserve the order of the figures
			 * (Hashmap -> itneger -> natural order) we send the playerqueue and
			 * set the boolean loadingDone = true so that other functions can
			 * start
			 */
			figureQueue = ((EInitNewGameDone) e).getFigureQueue();
			loadingDone = true;

		} else if (e.getEventType() == EventConstants.NEXT_FIGURE_EVENT) {
			/*
			 * NEXT FIGURE EVENT gives the GState the next figure id for moving,
			 * incl. its current position and a list of reachable links for
			 * painting reasons
			 */
			processNextFigureData(e);
			processNextFigureUpdate(e);

		} else if (e.getEventType() == EventConstants.NEXT_ROUND) {
			/*
			 * NEXT ROUND gives GState the recent round and a boolean whether to
			 * show thieves or not
			 */
			processNextRound(e);
		} else if (e.getEventType() == EventConstants.FIGURE_KILLED) {
			processFigureKilled(e);
		} else if (e.getEventType() == EventConstants.MOVE_DENIED_EVENT) {
			// resetting values, just incase
			chosenToken = null;
			playerChanged = false;
		} else if (e.getEventType() == EventConstants.POSSIBLE_THIEF_EVENT) {
			EPossibleThief ethief = (EPossibleThief) e;
			showPossibleThieves = ethief.getToggle();
			repaintImage();
		} else if (e.getEventType() == EventConstants.PLAYER_MOVED_EVENT) {
			IEPlayerMoved temp = (IEPlayerMoved) e;
			EInitNewGame dmy = figureList.get(currentFigureID);
			if (dmy != null) {
				dmy.setPosition(temp.getNewPosition());
			}
		}
	}

	/**
	 * Helper method for processEvent, this method proccesses a next round event
	 * (status update)
	 * 
	 * 
	 * @param e
	 *            the passed EventObject
	 */
	private void processNextRound(IAEvent e) {
		// get new roundNumber

		roundNumber = ((ENextRound) e).getNumer();
		// get the thievesToShow list

		if (pGVMove) {
			roundCounter.setForeground(Color.white);
			roundCounter.setText(languageMap.get("Gamestats")

			.replace("<1>", String.valueOf(roundNumber))
					.replace("<2>", String.valueOf(currentFigureID)));
		}
	}

	/**
	 * 
	 * Helper method for processEvent, this method processes a figure killed
	 * event (removal from figurelist, figurequeue, and the status cache, then
	 * updating the game elements)
	 * 
	 * @param e
	 *            the passed EventObject
	 */
	private void processFigureKilled(IAEvent e) {
		int id = ((EFigureKilled) e).getFigureID();
		if (figureList.containsKey(id)) {
			figureList.remove(id);
		}
		if (thievesToShowCache.containsKey(id)) {
			thievesToShowCache.remove(id);
		}
		for (int i = 0; i < figureQueue.size(); i++) {
			if (figureQueue.get(i) == id) {
				figureQueue.remove(i);
			}
		}
	}

	/**
	 * Helper method for processEvent, this method processes a next figure event
	 * (choosing new position, figureid, thievetoshow, thievelog)
	 * 
	 * 
	 * @param e
	 *            the passed EventObject
	 */
	private void processNextFigureData(IAEvent e) {
		// -----------------Basic data of the event---------------------
		ENextFigure temp = ((ENextFigure) e);
		possibleThief = temp.getPossibleThief();
		chosenPosition = temp.getPosition();
		if (pGVMove) {
			scrollRef = chosenPosition;
		}
		thievesToShow = temp.getShowThieves();
		if (!thievesToShow.isEmpty()) {
			thievesToShowCache.putAll(thievesToShow);
		}
		currentFigureID = temp.getFigureId();
		currentReachableLinks = temp.getReachableLinks();
		thiefLog = temp.getThiefLog();
		// first encounter?
		if (currentPlayer == -1) {
			currentPlayer = temp.getPlayerId();
			playerChanged = false;
		}

		// if not first time, check old player and compare it with new
		// one
		else {
			playerChanged = currentPlayer == temp.getPlayerId() ? false : true;
			currentPlayer = temp.getPlayerId();
		}
		if (temp.useDoubleTicketIsAllowed()) {
			doubleTicket.setEnabled(true);
			doubleTicket.setForeground(Color.white);
		} else {
			doubleTicket.setForeground(Color.gray);
			doubleTicket.setEnabled(false);
		}
	}

	/**
	 * Helper method for processEvent, this method uses the changed next figure
	 * data and updates the GUI elements
	 * 
	 * 
	 * @param e
	 *            the passed EventObject
	 */
	private void processNextFigureUpdate(IAEvent e) {

		// ---------Updating the GUI according to new
		// data---------------

		// if no move is possible, enable continue button
		if (currentReachableLinks.size() < 1) {
			confButton.setEnabled(true);
		}
		// update figure highlighting
		try {
			addFigureToList();
		} catch (ClassCastException ex) {
			ex.printStackTrace();
			ex.getCause();
			System.exit(100);

		}

		// update titlelabel
		roundCounter.setText(languageMap.get("Gamestats")
				.replace("<1>", String.valueOf(roundNumber))
				.replace("<2>", String.valueOf(currentFigureID)));
		// update statistics
		tableThiefTickets.setModel(fillThiefTicketTable(thiefLog, "Event"));
		tableThiefPositions.setModel(setTHiefPositionData(thievesToShowCache));
		// paint reachable links (handled in resize or setradiomap)

		// bot move
		if (figureList.containsKey(currentFigureID)
				&& figureList.get(currentFigureID).getOwnerGV() != pGV
						.getViewID()) {
			pGVMove = false;
			roundCounter.setForeground(Color.red);
			roundCounter.setText(languageMap.get("botWaitDesc") + "(ID: "
					+ currentFigureID + ")...");
			playerChanged = false;

		}
		// player move
		else {
			roundCounter.setForeground(Color.white);
			roundCounter.setText(languageMap.get("Gamestats")
					.replace("<1>", String.valueOf(roundNumber))
					.replace("<2>", String.valueOf(currentFigureID)));
			pGVMove = true;
		}
		// if a player changes, put the playerchangescreen ontop
		if (playerChanged && pGVMove) {
			initPlayerChangeScreen();
		}
		// repaint mapPanel

		repaintImage();
	}

	/**
	 * switch the language of the gui
	 * 
	 * 
	 * @param refMap
	 *            the languagemap which includes new language data
	 */
	public void switchLanguage(Map<String, String> refMap) {
		languageMap = refMap;

		if (playerChanged) {
			nextPlayer.setText(languageMap.get("playerchange").replace("<1>",
					figureList.get(currentFigureID).getName()));
			nextPlayerButton.setText(languageMap.get("confirmText"));
		} else {
			if (pGVMove) {
				roundCounter.setText(languageMap.get("Gamestats")
						.replace("<1>", String.valueOf(roundNumber))
						.replace("<2>", String.valueOf(currentFigureID)));
			} else {
				roundCounter.setForeground(Color.red);
				roundCounter.setText(languageMap.get("botWaitDesc") + "(ID: "
						+ currentFigureID + ")...");
			}
			yourFigures.setText(languageMap.get("yourfigures"));
			doubleTicket.setText(languageMap.get("doubleTicketText"));
			addFigureToList();
			initializeTabBar();
			confButton.setText(languageMap.get("confirmText"));
		}

	}

	/**
	 * Adds a figureIcon on the map
	 * 
	 * 
	 * @param stationName
	 *            The referencestation for coordinate reasons
	 * @param picture
	 *            the picture of the figure
	 * @param color
	 *            the figurecolor
	 * @param paintTransparent
	 *            boolean indication whether the figur should be painted
	 *            transparent, for possiblethieves
	 * 
	 * @throws NullPointerException
	 *             in case the new mappanel in the repaint procedure is built
	 *             too slow, this one could throw a nullpointer
	 */
	public void addFigureIcon(String stationName, BufferedImage picture,
			Color color, boolean paintTransparent) throws NullPointerException {
		if (buttonList.containsKey(stationName)) {
			GFigureCop cop = new GFigureCop(picture, color);
			cop.setDoubleBuffered(true);
			int w = (int) Math.round(picture.getWidth() * scalingFactor);
			int h = (int) Math.round(picture.getHeight() * scalingFactor);
			int x = buttonList.get(stationName).getX();
			int y = buttonList.get(stationName).getY() - h;
			cop.setScale(scalingFactor, scalingFactor);
			cop.setBounds(x, y, w, h);
			cop.setGhost(paintTransparent);
			mapPanel.add(cop);
		}
	}

	/**
	 * method for the slider, is called when the slider value is changed and
	 * updates the scalingfactor
	 * 
	 * @param e
	 *            the state has changed event
	 * 
	 */
	public void stateChanged(ChangeEvent e) {
		scalingFactor = ((double) slider.getValue()) / 10;
		repaintImage();
	}

}