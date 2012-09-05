package gameview.sView;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import events.EventConstants;
import events.IAEvent;
import events.logic.global.ELanguageSwitched;

/**
 * A panel to configure a specific player. You can set the player name,
 * type(police or thief), figure color, figure count. When the next Button of
 * the PlayerSettingPanel is pressed by the every information user will be sent
 * in an event.
 * 
 * @author Raphael A.
 * 
 */
public class SPlayerPanel extends JPanel {

    // ------------------------------------------------------------------------------------------------------------------
    // classes
    /**
     * This class extends PlainDocument and gives a standard JTextField a limit
     * length. So it's impossible, that the player name gets to long.
     * 
     * @author Raphael
     * 
     */
    public class JTextFieldLimit extends PlainDocument {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private final int limit;
	// optional uppercase conversion
	private boolean toUppercase = false;

	JTextFieldLimit(final int limit) {
	    super();
	    this.limit = limit;
	}

	/**
	 * Create JText with field limit
	 * 
	 * @param limit The limit
	 * @param upper True, if to uppercase
	 */
	JTextFieldLimit(final int limit, final boolean upper) {
	    super();
	    this.limit = limit;
	    toUppercase = upper;
	}

	/* (non-Javadoc)
	 * @see javax.swing.text.PlainDocument#insertString(int, java.lang.String, javax.swing.text.AttributeSet)
	 */
	@Override
	public void insertString(final int offset, String str,
		final AttributeSet attr) throws BadLocationException {
	    if (str == null) {
		return;
	    }

	    if (getLength() + str.length() <= limit) {
		if (toUppercase) {
		    str = str.toUpperCase();
		}
		super.insertString(offset, str, attr);
	    }
	}
    }

    /**
     * A document listener which calls check every time when there are changes
     * in the field
     * 
     * @author Raphael
     * 
     */
    class PlayerDocumentListener implements DocumentListener {
	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(final DocumentEvent e) {
	    sPSPanel.checkNext();
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(final DocumentEvent e) {
	    if (!sema) {
		sema = true;
		sPSPanel.checkNext();
		sema = false;
	    }

	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(final DocumentEvent e) {
	    sPSPanel.checkNext();
	}

    }

    /**
     * The ActionListener class
     * 
     * @author Raphael
     * 
     */
    class SPlayerHandler implements ActionListener {
	/**
	 * method for the menuitem eventhandling
	 * 
	 * @since 1.2
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param e A action event
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {

	    if (e.getSource().equals(typeChooseComboBox)) {
		type = typeChooseComboBox.getSelectedIndex();
	    } else if (e.getSource().equals(colorComboBox)) {
		colorIndex = colorComboBox.getSelectedIndex();

		for (int i = 0; i < colorSet.length; i++) {
		    if (colorComboBox.getSelectedItem().equals(colorSet[i])) {
			color = colorSetEng[i];
			break;
		    }
		}

		// if a figure is added or removed the current figure count has
		// to be increased and current figure count set.
	    } else if (e.getSource().equals(plusFigur)) {
		figureCount++;
		sPSPanel.setMaxFigureCount(sPSPanel.getMaxFigureCount() - 1);
		int temp = Integer.parseInt(figureCountLabel.getText());
		temp = temp + 1;
		figureCountLabel.setText(String.valueOf(temp));
		sPSPanel.checkFigureCount();
		sPSPanel.initMaxFigureBox();
	    } else if (e.getSource().equals(minusFigur)) {
		figureCount--;
		sPSPanel.setMaxFigureCount(sPSPanel.getMaxFigureCount() + 1);
		int temp = Integer.parseInt(figureCountLabel.getText());
		temp = temp - 1;
		figureCountLabel.setText(String.valueOf(temp));
		sPSPanel.checkFigureCount();
		sPSPanel.initMaxFigureBox();
	    } else if (e.getSource().equals(botOrPlayerComboBox)) {
		botOrPlayer = botOrPlayerComboBox.getSelectedIndex();
		// Sets a random name to the name text field if a bot is chosen
		if (botOrPlayerComboBox.getSelectedIndex() == 1) {
		    setRandomNameKevin();
		} else if (botOrPlayerComboBox.getSelectedIndex() == 2) {
		    setRandomNameHard();
		} else {
		    nameTextField.setText(language.get("pSPlayerName"));
		    nameTextField.setEditable(true);
		    nameTextField.setBackground(Color.WHITE);
		}
	    }
	    sPSPanel.checkNext();

	}
    }

    /**
     * This is the Focus Listner for the NameTextField. If the focus is gained
     * the nameTextField will be checked by this method. If the
     * "Insert a player name!" String in any language is still there the field
     * becomes empty. If there is something different nothing happens. When the
     * focus is lost and the field is still empty the "Insert a player name!"
     * String will be placed again.
     * 
     * @author Raphael
     * 
     */
    public class SPlayerNameTextFielListener implements FocusListener {

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(final FocusEvent listAction) {
	    if (listAction.getSource().equals(nameTextField)) {
		if (nameTextField.getText()
			.equals(language.get("pSPlayerName"))) {
		    nameTextField.setText("");
		}
	    }
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(final FocusEvent a) {
	    if (a.getSource().equals(nameTextField)) {
		if (nameTextField.getText().equals("")) {
		    nameTextField.setText(language.get("pSPlayerName"));
		}
		playerName = nameTextField.getText();
	    }
	    sPSPanel.checkNext();
	}
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Init all Components
    String playerXString = new String("Player ");
    String playerName;
    String color;
    int colorIndex;
    int type;
    int figureCount = 1;
    int id;
    int botOrPlayer = 0;
    boolean sema;
    JLabel playerX, figure, typeChoose, colorChoose, botLabel;;
    JTextField nameTextField = new JTextField(20);
    String[] typeSet, colorSet;
    String[] colorSetEng = { "Blue", "Red", "Black", "Green", "Yellow", "Gray" };
    Color[] colors = { Color.BLUE, Color.RED, Color.black, Color.GREEN,
	    Color.YELLOW, Color.GRAY };
    // ImageIcon[] figureImageIcons;
    List<BufferedImage> bufferdFigureIcons;
    BufferedImage[] bufferedImageIconArray;
    ImageIcon[] imageIconArray;

    JCheckBox kICheckBox;

    JComboBox<String> typeChooseComboBox, botOrPlayerComboBox;

    JComboBox<?> colorComboBox;

    JComboBox<ImageIcon> figureIconComboBox;

    JLabel figureCountLabel = new JLabel("1");

    JButton plusFigur = new JButton("+");

    JButton minusFigur = new JButton("-");

    SPlayerSettingPanel sPSPanel;

    private Map<String, String> language;

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------------------------------------------------------------------------
	/**
	 * The constructor. When a new SPlayerPanel is created the init() method is
	 * called.
	 * 
	 * @param sPSPanel
	 *            SPlayer settings panel
	 * @param colorIndex
	 *            index of color
	 * @param type
	 *            player type
	 * @param id
	 *            player id
	 * @param language
	 *            map with language data
	 */
    public SPlayerPanel(final SPlayerSettingPanel sPSPanel,
	    final int colorIndex, final int type, final int id,
	    final Map<String, String> language) {
	this.colorIndex = colorIndex;
	this.type = type;
	this.id = id;
	this.sPSPanel = sPSPanel;
	this.language = language;
	init();
    }

    /**
     * every interactive component gets the ActionListener
     * 
     * @param action Action listener
     */
    public void addListener(final ActionListener action) {
	typeChooseComboBox.addActionListener(action);
	colorComboBox.addActionListener(action);
	plusFigur.addActionListener(action);
	minusFigur.addActionListener(action);
	botOrPlayerComboBox.addActionListener(action);
    }

    /**
     * Add all components to the panel. I used the grid bag layout so I have to
     * define all positions.
     * 
     */
    public void build() {
	final GridBagConstraints gc = new GridBagConstraints();
	gc.insets = new Insets(5, 5, 5, 5);
	gc.gridx = 0;
	gc.gridy = 0;
	this.add(playerX, gc);
	gc.gridx = 1;
	gc.gridwidth = 4;
	this.add(nameTextField, gc);
	gc.gridwidth = 1;
	gc.gridx = 6;
	this.add(colorChoose, gc);
	gc.gridx = 7;
	this.add(colorComboBox, gc);
	gc.gridx = 8;
	this.add(plusFigur, gc);
	gc.gridx = 9;
	this.add(minusFigur, gc);
	gc.gridx = 1;
	gc.gridy = 1;
	gc.gridwidth = 2;
	this.add(botOrPlayerComboBox, gc);
	gc.gridwidth = 1;
	gc.gridx = 3;
	this.add(typeChoose, gc);
	gc.gridx = 4;
	this.add(typeChooseComboBox, gc);
	gc.gridx = 7;
	this.add(figure, gc);
	gc.gridx = 8;
	gc.gridwidth = 2;
	this.add(figureCountLabel, gc);
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Extra functions
    /**
     * If there are enough figures enable or disable the minus button
     * 
     * @param enable True, if enable
     */
    public void enableMinus(final Boolean enable) {
	minusFigur.setEnabled(enable);
    }

    /**
     * If there are enough figures enable or disable the plus button
     * 
     * @param enable True, if enable
     */
    public void enablePlus(final Boolean enable) {
	plusFigur.setEnabled(enable);
    }

    /**
     * Get bot or human id.
     * 
     * @return bot or human id
     */
    public int getBotOrPlayer() {
	return botOrPlayer;
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Getter and setter! No need of explain.
    /**
     * Get player color.
     * 
     * @return color player color
     */
    public String getColor() {
	return color;
    }

    /**
     * @return colorComboBox
     */
    public JComboBox<?> getColorComboBox() {
	return colorComboBox;
    }

    /**
     * @return colorIndex
     */
    public int getColorIndex() {
	return colorIndex;
    }

    /**
     * @return figureCount
     */
    public int getFigureCount() {
	return figureCount;
    }

    /**
     * @return figureIconComboBox
     */
    public JComboBox<ImageIcon> getFigureIconComboBox() {
	return figureIconComboBox;
    }

    /**
     * @return icon
     */
    public ImageIcon getIcon() {
	final int index = figureIconComboBox.getSelectedIndex();
	final ImageIcon icon = figureIconComboBox.getItemAt(index);
	return icon;
    }

    /**
     * @return figureIconComboBox.getSelectedIndex()
     */
    public int getIconID() {
	return figureIconComboBox.getSelectedIndex();
    }

    /**
     * @return nameTextField
     */
    public JTextField getNameTextField() {
	return nameTextField;
    }

    /**
     * @return nameTextField.getText();
     */
    public String getPlayerName() {
	return nameTextField.getText();
    }

    /**
     * @return playerXString
     */
    public String getPlayerXString() {
	return playerXString;
    }

    /**
     * @return type
     */
    public int getType() {
	return type;
    }

    /**
     * @return typeChoose the Label "Type:"
     */
    public JLabel getTypeChoose() {
	return typeChoose;
    }

    // ------------------------------------------------------------------------------------------------------------------
    // Core methods
    /**
     * The is the first method called which sets the titles and component
     * properties. Also the build() method is called which adds every elemt to
     * the panel.
     */
    public void init() {

	// Init Panel
	setLayout(new GridBagLayout());
	setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	setBackground(Color.white);
	final Border loweredetched = BorderFactory
		.createEtchedBorder(EtchedBorder.LOWERED);
	setBorder(loweredetched);

	// Init the values of the Strings
	playerXString = language.get("pSPlayer") + String.valueOf(id) + ":  ";
	playerName = playerXString;
	typeSet = language.get("pSTypes").split(",");

	// Init all Label's and ComboBox's
	playerX = new JLabel(playerXString);
	figure = new JLabel(language.get("pSFigureCount"));
	typeChoose = new JLabel(language.get("pSType"));
	colorChoose = new JLabel(language.get("pSColor"));
	typeChooseComboBox = new JComboBox<String>(typeSet);
	botOrPlayerComboBox = new JComboBox<String>(language.get(
		"pSBotOrPlayer").split(","));

	// Settings for the components
	typeChooseComboBox.setSelectedIndex(type);
	playerX.setFont(new Font("Arial", 0, 18));
	figureCountLabel.setFont(new Font("Arial", 0, 18));
	minusFigur.setFont(new Font("Arial", 0, 18));
	plusFigur.setFont(new Font("Arial", 0, 18));

	figureCountLabel.setPreferredSize(new Dimension(30, 12));
	nameTextField.setDocument(new JTextFieldLimit(40));
	nameTextField.setText(language.get("pSPlayerName"));

	// String array with the labels displayed.
	colorSet = language.get("pSColors").split(",");
	// The actual colors

	setupColorChooseBox();
	build();

	// When the language is different to eng. this is necessary to send the
	// color name in english.
	colorComboBox.setSelectedIndex(colorIndex);

	for (int i = 0; i < colorSet.length; i++) {
	    if (colorComboBox.getSelectedItem().equals(colorSet[i])) {
		color = colorSetEng[i];
	    } else {
		color = (String) colorComboBox.getSelectedItem();
	    }
	}
	addListener(new SPlayerHandler());
	nameTextField.addFocusListener(new SPlayerNameTextFielListener());
	nameTextField.getDocument().addDocumentListener(
		new PlayerDocumentListener());
    }

    /**
     * Process logic events.
     * 
     * @param event A event
     */
    public void processEvent(final IAEvent event) {
	if (event.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
	    switchLanguage(((ELanguageSwitched) event).getLanguage());
	}
    }

    /**
     * This method sets adds and sets the figureIconComboBox if there are the
     * necessary data.
     * 
     * @param bufferedFigureIcons List with all figure icons
     */
    public void setBufferdFigureIcons(
	    final List<BufferedImage> bufferedFigureIcons) {
	// Create an imageicon array
	imageIconArray = new ImageIcon[bufferedFigureIcons.size()];
	bufferedImageIconArray = new BufferedImage[bufferedFigureIcons.size()];

	// Fill the array with the icons from the bufferedFigureIcons list
	for (int i = 0; i < bufferedFigureIcons.size(); i++) {
	    imageIconArray[i] = new ImageIcon(bufferedFigureIcons.get(i));
	    bufferedImageIconArray[i] = bufferedFigureIcons.get(i);

	}

	// setup the icon comboBox
	figureIconComboBox = new JComboBox<ImageIcon>(imageIconArray);
	figureIconComboBox.setPreferredSize(new DimensionUIResource(80, 80));
	figureIconComboBox.setMaximumRowCount(3);
	// check the type and the id to set a correct first selected index.
	if (type == 0) {
	    figureIconComboBox.setSelectedIndex(id + 1);
	}
	if (type == 1) {
	    figureIconComboBox.setSelectedIndex(id + 8);
	}
	// Add the combo box
	final GridBagConstraints gc = new GridBagConstraints();
	gc.gridheight = 2;
	this.add(figureIconComboBox, gc);
    }

    /**
     * Sets the "Color:" Label red if there is an conflict with a other player.
     * 
     * @param red True, if color should be red
     */
    public void setDisableColors(final boolean red) {
	if (red) {
	    colorChoose.setForeground(Color.RED);
	} else {
	    colorChoose.setForeground(Color.black);
	}
    }

    /**
     * This method is used to set up a random name to a hard bot player.
     */
    public void setRandomNameHard() {
	final String[] firstName = language.get("FirstName").split(",");
	final String[] secondName = language.get("SecondName").split(",");
	final Random generator = new Random();

	final int indexOne = generator.nextInt(firstName.length);
	final int indexTwo = generator.nextInt(secondName.length);
	nameTextField.setEditable(false);
	nameTextField.setText(firstName[indexOne] + " " + secondName[indexTwo]
		+ " (Bot)");
	nameTextField.setBackground(Color.LIGHT_GRAY);
    }

    /**
     * We proudly present the one an only Kevinator. This method is used to set
     * up a random name to a easy bot player.
     */
    public void setRandomNameKevin() {
	final String[] kevArray = language.get("KevArray").split(",");
	final Random generator = new Random();

	final int index = generator.nextInt(kevArray.length);
	nameTextField.setEditable(false);
	nameTextField.setText(kevArray[index] + "-Kevin(Bot)");
	nameTextField.setBackground(Color.LIGHT_GRAY);
    }

    /**
     * Sets the "Type:" Label. Used to make it red or black.
     * 
     * @param typeChoose Choosen type
     */
    public void setTypeChoose(final JLabel typeChoose) {
	this.typeChoose = typeChoose;
    }

    /**
     * This is the method which makes the colorChooseBox colorfully.
     */
    private void setupColorChooseBox() {

	colorSet = language.get("pSColors").split(",");
	// A HashMap to combine the both arrays
	final HashMap<String, Color> farbeMap = new HashMap<String, Color>();
	for (int i = 0; i < colorSet.length; i++) {
	    farbeMap.put(colorSet[i], colors[i]);
	}
	final Iterator<Entry<String, Color>> it = farbeMap.entrySet()
		.iterator();
	final ArrayList<String> colorArrayList = new ArrayList<String>();
	while (it.hasNext()) {
	    final Entry<String, Color> entry = it.next();
	    colorArrayList.add(entry.getKey());
	}
	final Object[] colorArr = colorArrayList.toArray();
	colorComboBox = new JComboBox<Object>(colorArr);
	// set a adjusted renderer. With highlighting and color background
	colorComboBox.setRenderer(new DefaultListCellRenderer() {
	    private static final long serialVersionUID = 1L;

	    @Override
	    public Component getListCellRendererComponent(final JList<?> list,
		    final Object value, final int index,
		    final boolean isSelected, final boolean cellHasFocus) {
		final Color color = farbeMap.get(value);
		setText(String.valueOf(value));
		setBackground(color);
		setForeground(color);
		// If the value is selected turn the background brighter
		setBackground(isSelected ? color.darker() : color);
		setForeground(isSelected ? color.darker() : color.darker());
		return this;
	    }
	});
    }

    /**
     * This method changes every element title or text to the current language
     * if requested.
     * 
     * @param language Map with language data
     */
    void switchLanguage(final Map<String, String> language) {

	// If the standard text is still in the nametextfield change it to the
	// new version
	// Else ignore it and ceep the content
	boolean changeField = false;
	if (nameTextField.getText().equals(this.language.get("pSPlayerName"))) {
	    changeField = true;
	}
	this.language = language;
	if (changeField) {
	    final String test = language.get("pSPlayerName");
	    nameTextField.setText(test);
	}

	helpSwitchLanguage();
	// Get the current index
	int index = colorComboBox.getSelectedIndex();
	// Remove the current component
	remove(colorComboBox);
	colorComboBox = null;
	// restore it with new language
	setupColorChooseBox();
	// add it again
	final GridBagConstraints gc = new GridBagConstraints();
	gc.gridx = 7;
	gc.gridy = 0;
	this.add(colorComboBox, gc);
	colorComboBox.setSelectedIndex(index);
	// Set color to the right value in english
	for (int i = 0; i < colorSet.length; i++) {
	    if (colorComboBox.getSelectedItem().equals(colorSet[i])) {
		color = colorSetEng[i];
		break;
	    }
	}
	// And the same for the botOrPlayerComboBox
	index = botOrPlayerComboBox.getSelectedIndex();
	remove(botOrPlayerComboBox);
	botOrPlayerComboBox = null;
	botOrPlayerComboBox = new JComboBox<String>(language.get(
		"pSBotOrPlayer").split(","));
	gc.gridx = 1;
	gc.gridy = 1;
	gc.gridwidth = 2;
	this.add(botOrPlayerComboBox, gc);
	gc.gridwidth = 1;
	botOrPlayerComboBox.setSelectedIndex(index);

	// Add the actionListener again bacause the elements were destroyed.
	addListener(new SPlayerHandler());
    }

    public void helpSwitchLanguage() {
	// The labels and combobox titles will be updated to the new language
	playerXString = language.get("pSPlayer");
	playerXString = playerXString.concat(String.valueOf(id) + ":  ");
	playerX.setText(playerXString);
	colorChoose.setText(language.get("pSColor"));
	typeChoose.setText(language.get("pSType"));
	figure.setText(language.get("pSFigureCount"));
	typeChoose.setText(language.get("pSType"));
	colorChoose.setText(language.get("pSColor"));
	typeSet = language.get("pSTypes").split(",");

	// get the current selected index so it will not be lost after a
	// language switch.
	int index = typeChooseComboBox.getSelectedIndex();
	// remove the current items and add the new one.
	typeChooseComboBox.removeAllItems();
	typeChooseComboBox.addItem(typeSet[0]);
	typeChooseComboBox.addItem(typeSet[1]);
	// finaly set the old index
	typeChooseComboBox.setSelectedIndex(index);

    }
}
