package gameview.eView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import events.EventConstants;
import events.IAEvent;
import events.logic.endState.EPlayerWon;
import gameview.PlayerGameView;

/**
 * This is the end state, which displayes the winner and the log.
 * 
 * @author Raphael A., Jasper S., Felix K.
 * 
 */
public class EState implements ActionListener {

	private String winnerString;
	Map<String, String> language;
	JLabel winnerLabel;
	JButton continueButton, logButton;
	JPanel bgPanel;
	PlayerGameView pGV;
	Map<Integer, String> log;
	JDialog logDialog;

	/**
	 * The constructor which gets the current player game view and the language
	 * map. Also the winnerString and the log are initialized.
	 * 
	 * @param languageMap Map with languages data
	 * @param pgV Player game view
	 */
	public EState(Map<String, String> languageMap, PlayerGameView pgV) {
		this.pGV = pgV;
		winnerString = "";
		language = languageMap;
		log = new HashMap<Integer, String>();
		init();
	}

	/**
	 * Inits all elements.
	 */
	public void init() {
		Dimension minSize = new Dimension(5, 40);
		Dimension prefSize = new Dimension(5, 80);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
		bgPanel = new JPanel();
		bgPanel.setBackground(Color.darkGray);
		bgPanel.setLayout(new BoxLayout(bgPanel, BoxLayout.Y_AXIS));
		bgPanel.add(new Box.Filler(minSize, prefSize, maxSize));
		String content = language.get("eStateDesc")
				.replace("<1>", winnerString);
		winnerLabel = new JLabel(content);
		winnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		winnerLabel.setFont(new Font("Arial", Font.BOLD, 20));
		winnerLabel.setForeground(Color.white);
		bgPanel.add(new Box.Filler(minSize, prefSize, maxSize));

		bgPanel.add(winnerLabel);
		bgPanel.add(new Box.Filler(minSize, prefSize, maxSize));

		continueButton = new JButton(language.get("continueText"));
		continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		continueButton.setMinimumSize(new Dimension(50, 25));
		continueButton.setPreferredSize(new Dimension(260, 50));
		continueButton.setMaximumSize(new Dimension(280, 50));
		continueButton.setForeground(Color.DARK_GRAY);

		continueButton.addActionListener(this);

		bgPanel.add(continueButton);

		logButton = new JButton(language.get("logText"));
		logButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		logButton.setMinimumSize(new Dimension(50, 25));
		logButton.setPreferredSize(new Dimension(260, 50));
		logButton.setMaximumSize(new Dimension(280, 50));
		logButton.setForeground(Color.DARK_GRAY);

		logButton.addActionListener(this);

		bgPanel.add(logButton);

		pGV.getMsgPump().addComponent(bgPanel);
	}

	/**
	 * destroy's the panel
	 */
	public void destroy() {
		pGV.getMsgPump().removeComponent(bgPanel);
		bgPanel = null;
	}

	/**
	 * Processes the Player_WON event.
	 * 
	 * @param e
	 *            event
	 */
	public void processEvent(IAEvent e) {
		switch (e.getEventType()) {
		case EventConstants.PLAYER_WON:
			this.processEventPlayerWon((EPlayerWon) e);
			break;
		default:
			break;
		}
	}

	/**
	 * Process {@link events.logic.endState.EPlayerWon} event: Get all winning
	 * player.
	 * 
	 * @param e
	 *            The {@link events.logic.endState.EPlayerWon} event
	 */
	private void processEventPlayerWon(EPlayerWon e) {

		String winnerString = "";
		for (String playerName : e.getWinningPlayers()) {
			winnerString += playerName + ", ";
		}

		if (winnerString.matches(".*, ")) {
			winnerString = winnerString.substring(0, winnerString.length() - 2);
		}
		log = e.getLog();

		winnerLabel.setText(language.get("eStateDesc").replace("<1>",
				winnerString));
	}

	/**
	 * switches the text of the buttons to the current language version.
	 */
	public void switchLanguage() {
		winnerLabel.setText(language.get("eStateDesc").replace("<1>",
				winnerString));
		continueButton.setText(language.get("continueText"));
	}

	/**
	 * Init the log diolog
	 */
	public void initLogDialog() {
		logDialog = new JDialog((JFrame) bgPanel.getTopLevelAncestor(), "Log",
				true);
		logDialog.setSize(new Dimension(600, 500));

		logDialog.setLocationRelativeTo(null);
		JTable logTable = new JTable(fillLogTable(log, ""));
		logTable.setEnabled(false);
		JScrollPane pane = new JScrollPane(logTable);
		logDialog.add(pane, BorderLayout.NORTH);
		JButton closeButton = new JButton(language.get("logClose"));
		closeButton.addActionListener(this);
		logDialog.add(closeButton, BorderLayout.SOUTH);

		logDialog.setVisible(true);

	}

	/**
	 * fills the log table with the data
	 * 
	 * @param data
	 *            Data for log table
	 * @param left
	 *            Data for left column
	 * @return the table filled with the data
	 */
	private TableModel fillLogTable(Map<Integer, String> data, String left) {
		String[] column = { left };
		DefaultTableModel model = new DefaultTableModel(column, 0);
		for (Map.Entry<Integer, String> entry : data.entrySet()) {
			model.addRow(new Object[] { entry.getValue() });
		}
		return model;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			String refText = ((JButton) e.getSource()).getText();
			if (refText.equals(language.get("continueText"))) {
				pGV.eventGenerator.fireNewGameEvent(this);

			} else if (refText.equals(language.get("logText"))) {
				initLogDialog();
			} else if (refText.equals(language.get("logClose"))) {
				logDialog.setVisible(false);
			}
		}
	}
}
