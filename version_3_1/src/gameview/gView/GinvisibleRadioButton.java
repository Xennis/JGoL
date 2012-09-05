package gameview.gView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JRadioButton;

/**
 * Ginvisible radio buttons for a station on the map.
 * 
 * @author Felix
 *
 */
public class GinvisibleRadioButton extends JRadioButton {
	/**
     * 
     */
	private static final long serialVersionUID = 9041823448215027328L;
	
	boolean hasColor;
	BufferedImage bufImg;

	/**
	 * Create new ginvisible radio button object for the map.
	 * 
	 * @param color True, if has color
	 */
	public GinvisibleRadioButton(boolean color) {
		super();
		hasColor = color;
	}

	/**
	 * Create new ginvisible radio button object for the map.
	 */
	public GinvisibleRadioButton() {
		super();
		hasColor = false;
	}

	/**
	 * paint method for the button, only paints a green rectangle when is
	 * active, else no paint at all
	 * 
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		if (hasColor) {
			g.setColor(new Color(15, 184, 27, 128));
			g.fillRect(0, 0, getWidth(), getHeight());
		}

	}

	/**
	 * 
	 * returns the status whether the button is available
	 * 
	 * @return the status of the button
	 */
	public boolean getBool() {
		return hasColor;
	}

	/**
	 * fills the button with a green color, so it is available (visually)
	 * 
	 * 
	 */
	public void setAvailable() {
		hasColor = true;
	}

	/**
	 * sets the Button unavailable (no green fill)
	 * 
	 */
	public void setUnavailable() {
		hasColor = false;
	}

}
