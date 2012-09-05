package gameview.sView;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.EventConstants;
import events.IAEvent;
import events.logic.global.ELanguageSwitched;

/**
 * The first panel. The user can chose between a new game, to load a game or
 * exit the game
 * 
 * @author Raphael A.
 * @version 3.0
 */
public class SMenuPanel extends JPanel {

    /**
     * The ActionListener
     * 
     * @author Raphael
     * 
     */
    class SMenuHandler implements ActionListener {
	/**
	 * method for the menuitem eventhandling
	 * 
	 * @since 1.2
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * @param e a action event
	 */
	@Override
	public void actionPerformed(final ActionEvent e) {
	    if (e.getSource().equals(newGameButton)) {
		sState.onPanePMenu(false);
		sState.onPanePGameMode(true);
		// sState.initGameMode();
	    }
	    if (e.getSource().equals(exitGameButton)) {
		sState.getPGV().getMsgPump().requestShutdown();
	    }
	}
    }

    JButton newGameButton, exitGameButton;

    JLabel gOLTitel;
    Dimension minSize = new Dimension(5, 40);
    Dimension prefSize = new Dimension(5, 80);

    Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);

    private final Map<String, String> language;

    SState sState;

    static final long serialVersionUID = 1L;

    /**
     * Constructor which gets the SState and the language
     * 
     * @param sState The start state
     * @param language map with language data
     */
    public SMenuPanel(final SState sState, final Map<String, String> language) {
	this.language = language;
	this.sState = sState;

    }

    /**
     * Each panel got his own Action Listener. Each ActionEvent starts the right
     * methods in SState.java
     * 
     * @param action Action listener
     */
    public void addListener(final ActionListener action) {
	newGameButton.addActionListener(action);
	exitGameButton.addActionListener(action);
    }

    /**
     * This method initializes every element and add it to the panel.
     */
    public void init() {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	setBackground(Color.DARK_GRAY);

	// init the buttons
	newGameButton = new JButton(language.get("newGame"));
	exitGameButton = new JButton(language.get("exitGame"));

	// Init the titel "Gangs of Lübeck"
	gOLTitel = new JLabel(language.get("gameName"));
	gOLTitel.setFont(new Font("Arial", 1, 50));
	gOLTitel.setAlignmentX(Component.CENTER_ALIGNMENT);
	gOLTitel.setForeground(Color.white);
	add(new Box.Filler(minSize, prefSize, maxSize));

	add(gOLTitel, this);
	add(new Box.Filler(minSize, prefSize, maxSize));

	setButtonSettings(newGameButton);
	setButtonSettings(exitGameButton);
	final JLabel credits = new JLabel(language.get("about"));
	credits.setFont(new Font("Arial", 0, 14));
	credits.setForeground(Color.WHITE);
	add(credits);

	addListener(new SMenuHandler());

    }

    /**
     * only processes the language event.
     * 
     * @param event A event
     */
    public void processEvent(final IAEvent event) {
	if (event.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
	    switchLanguage(((ELanguageSwitched) event).getLanguage());
	}
    }

    /**
     * Sets the setting for the button's and add them to the pane.
     * 
     * @param button A button
     */
    public void setButtonSettings(final JButton button) {
	button.setFont(new Font("Arial", 0, 20));
	button.setAlignmentX(Component.CENTER_ALIGNMENT);
	button.setMinimumSize(new Dimension(50, 25));
	button.setPreferredSize(new Dimension(260, 50));
	button.setMaximumSize(new Dimension(280, 50));
	button.setForeground(Color.DARK_GRAY);
	button.setFont(new Font("Arial", 1, 20));
	button.setForeground(Color.DARK_GRAY);
	add(button, this);
	add(new Box.Filler(minSize, prefSize, maxSize));

    }

    /**
     * This method changes every element title or text to the current language
     * if requested.
     * 
     * @param language Map with language data
     */
    void switchLanguage(final Map<String, String> language) {
	newGameButton.setText(language.get("newGame"));
	exitGameButton.setText(language.get("exitGame"));
	gOLTitel.setText(language.get("gameName"));
    }

}
