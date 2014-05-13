package gameview.generalView;

import events.AEvent;
import events.EventConstants;
import events.logic.global.ELanguageSwitched;
import gameview.PlayerGameView;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import core.IMain;

/**
 * The Menubar. It porvid's several functions: 1.Start new game 2. End game
 * 3.language switch 4.surrender 5.pos. thief position function 6.Informations
 * about the product
 * 
 * @author Raphael A., Jasper Schinghammer
 * @version 3.0
 */
public class MenuBar {
	JDialog infodialog;
	JLabel infoLabel1;
	JButton closeBttn;

	JCheckBoxMenuItem possibleThief;
	JFrame frame = new JFrame();
	JMenuBar menubar;
	JMenu menu, sMenu, oMenu, playerOptions;
	List<JRadioButtonMenuItem> languageRadioButtons;
	ButtonGroup languageButtonGroup;
	JMenuItem about, newGameMI, endGameMI, surrender;
	PlayerGameView pGV;

	String aboutDialogLabelText;
	private boolean possibleThiefsisAllowed;

	/**
	 * The constructor which is also the init method. It gets the player game
	 * view the language map an the language names. Every element is set.
	 * 
	 * @param pGV
	 *            Player game view
	 * @param language
	 *            Map with language data
	 * @param languageNames
	 *            List with all language names
	 */
	public MenuBar(PlayerGameView pGV, Map<String, String> language,
			List<String> languageNames) {
		// initializing the menus
		this.pGV = pGV;
		menubar = new JMenuBar();
		sMenu = initMenu("Game", language.get("menu_game"));
		menu = initMenu("Info", language.get("menu_info"));
		oMenu = initMenu("Options", language.get("menu_options"));
		playerOptions = initMenu("Playeroptions", language.get("playeroptions"));
		menubar.add(sMenu);
		menubar.add(playerOptions);
		menubar.add(oMenu);
		menubar.add(menu);
		surrender = initMenuItem("surrender", language.get("surrender"));
		possibleThief = new JCheckBoxMenuItem(language.get("possibleThief"));
		playerOptions.add(surrender);
		playerOptions.add(possibleThief);

		about = initMenuItem("About", language.get("menu_about"));
		closeBttn = new JButton(language.get("menudialog_close"));
		closeBttn.setActionCommand("Close");
		aboutDialogLabelText = language.get("menudialog_aboutdialog");
		menu.add(about);
		newGameMI = initMenuItem("New game", language.get("menuitem_newgame"));
		endGameMI = initMenuItem("End game", language.get("menuitem_endgame"));
		sMenu.add(newGameMI);
		sMenu.addSeparator();
		sMenu.add(endGameMI);
		addActionListener();

		// add the menu to the JFrame
		pGV.getMsgPump().addMenuBar(menubar);
		// initialize the JDialog to the JFrame
		initDialog();
		closeBttn.addActionListener(new InfoButtonHandler());
		languageRadioButtons = setAndGetLangRButtons(languageNames);
		// And the Optionbuttons
	}

	/**
	 * Adds the MenuHandler to all components
	 */
	private void addActionListener() {
		about.addActionListener(new MenuHandler());
		endGameMI.addActionListener(new MenuHandler());
		newGameMI.addActionListener(new MenuHandler());
		surrender.addActionListener(new MenuHandler());
		possibleThief.addActionListener(new MenuHandler());
	}

	/**
	 * Sets the language Button Group and returns the List<JRadioButtonMenuItem>
	 * 
	 * @param languageNames List with all languages names
	 * @return the List<JRadioButtonMenuItem>
	 */
	private List<JRadioButtonMenuItem> setAndGetLangRButtons(
			List<String> languageNames) {
		List<JRadioButtonMenuItem> tempList = new LinkedList<JRadioButtonMenuItem>();
		languageButtonGroup = new ButtonGroup();
		for (int i = 0; i < languageNames.size(); i++) {
			JRadioButtonMenuItem tempButton = new JRadioButtonMenuItem(
					languageNames.get(i));
			tempButton.addActionListener(new MenuRadioButtonHandler());
			if (languageNames.get(i).equals(IMain.DEFAULT_LANGUAGE)) {
				tempButton.setSelected(true);
			}
			tempList.add(tempButton);
			oMenu.add(tempButton);
			languageButtonGroup.add(tempButton);
		}
		return tempList;
	}

	/**
	 * Initializes a JMenu with a specified title
	 * 
	 * @param actionCommand
	 *            of the menu
	 * @param title
	 *            of the menu
	 * @return the JMenu
	 */
	public JMenu initMenu(String actionCommand, String title) {
		JMenu temp = new JMenu(title);
		temp.setActionCommand(actionCommand);
		return temp;
	}

	/**
	 * Initializes an menu item with a specified title
	 * 
	 * @param actionCommand
	 *            of the menu item
	 * @param title
	 *            of the menu item
	 * @return the JMenuItem
	 */
	public JMenuItem initMenuItem(String actionCommand, String title) {
		JMenuItem temp = new JMenuItem(title);
		temp.setActionCommand(actionCommand);
		return temp;
	}

	/**
	 * Initializes the info dialog
	 */
	public void initDialog() {
		infodialog = new JDialog((JFrame) menubar.getTopLevelAncestor(),
				"About", true);
		infoLabel1 = new JLabel(aboutDialogLabelText);
		infodialog.setBounds(390, 260, 300, 250);
		infodialog.setResizable(false);
		infodialog.setLocationRelativeTo(null);
		infodialog.add(infoLabel1, BorderLayout.NORTH);
		infodialog.add(closeBttn, BorderLayout.SOUTH);
	}

	/**
	 * Enables or disables menu items depending on the current state
	 * 
	 * @param newState
	 *            the new state
	 */
	public void switchState(int newState) {
		if (newState == 0) {
			newGameMI.setEnabled(false);
			playerOptions.setEnabled(false);
			possibleThiefsisAllowed = false;
		}
		if (newState == 1) {
			newGameMI.setEnabled(true);
			playerOptions.setEnabled(true);
			if (possibleThiefsisAllowed) {
				possibleThief.setEnabled(true);
			} else {
				possibleThief.setEnabled(false);
			}
		}
		if (newState == 2) {
			newGameMI.setEnabled(true);
			playerOptions.setEnabled(false);
		}
	}

	/**
	 * Enables or disables the possible thief position function.
	 * 
	 * @param possibleThiefsisAllowed
	 *            = true => enabled, = false => disabled
	 */
	public void setPossibleThiefsisAllowed(boolean possibleThiefsisAllowed) {
		this.possibleThiefsisAllowed = possibleThiefsisAllowed;
	}

	/**
	 * Makes the info Dialog visible
	 */
	public void showInfo() {
		infodialog.setVisible(true);

	}

	/**
	 * Makes the info Dialog invisible
	 */
	public void hideInfo() {
		infodialog.setVisible(false);
	}

	/**
	 * Processes the switch game event and the language switched event.
	 * @param e event
	 */
	public void processEvent(AEvent e) {
		if (e.getEventType() == EventConstants.SWITCH_GAMESTATE_EVENT) {
			switchState(1);
		} else if (e.getEventType() == EventConstants.LANGUAGE_SWITCHED) {
			switchLanguage(((ELanguageSwitched) e).getLanguage());
		}
	}

	/**
	 * Switches the text and tiles of all components to the version of the 
	 * current language.
	 * @param language Map with language data
	 */
	public void switchLanguage(Map<String, String> language) {
		newGameMI.setText(language.get("menuitem_newgame"));
		endGameMI.setText(language.get("menuitem_endgame"));
		sMenu.setText(language.get("menu_game"));
		aboutDialogLabelText = language.get("menudialog_aboutdialog");
		infoLabel1.setText(aboutDialogLabelText);
		closeBttn.setText(language.get("menudialog_close"));
		oMenu.setText(language.get("menu_options"));
		menu.setText(language.get("menu_info"));
		about.setText(language.get("menu_about"));
		surrender.setText(language.get("surrender"));
		possibleThief.setText(language.get("possibleThief"));
		playerOptions.setText(language.get("playeroptions"));

	}

	/**
	 * This is an action listener which listen to the "menu" menu items.
	 * @author Jasper S.
	 * @version 1.2
	 */
	public class MenuHandler implements ActionListener {
		/**
		 * method for the menuitem eventhandling
		 * 
		 * @since 1.2
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 * @param e a action event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(about)) {
				pGV.getMsgPump().logInfo("Zeige den about-dialog");
				showInfo();
			} else if (e.getSource().equals(newGameMI)) {
				pGV.eventGenerator.fireNewGameEvent(this);
			} else if (e.getSource().equals(endGameMI)) {
				pGV.getMsgPump().requestShutdown();
			} else if (e.getSource().equals(surrender)) {
				pGV.eventGenerator.firePlayerSurrenderEvent(this);
			} else if (e.getSource().equals(possibleThief)) {
				pGV.eventGenerator.firePossibleThiefEvent(this,
						possibleThief.isSelected());
			}
		}
	}

	/**
	 * Info button handler.
	 * 
	 * @author Jasper S.
	 * @version 1.2
	 */
	public class InfoButtonHandler implements ActionListener {
		/**
		 * method for the button handling on the JDialog
		 * 
		 * @since 1.2
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 * @param e A action event
		 * 
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Close")) {
				hideInfo();
			}
		}
	}

	/**
	 * Hanlder for menu radio button.
	 * 
	 * @author Jasper S.
	 * @version 1.2
	 * 
	 */
	public class MenuRadioButtonHandler implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			pGV.getMsgPump().logInfo(
					"Neue Sprache gewählt: " + e.getActionCommand());
			pGV.eventGenerator.fireNewRequestedLanguageSwitch(this,
					e.getActionCommand());
		}

	}
}