package gameview.gView;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/** Helper Class for Gfigurecop, creates a Figureicon with given Image (can also be thief)
 * 
 * @author Felix K.
 *
 */
public class GFigureCop extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8721230926793302241L;

	BufferedImage bufimg;
	double scaleX = 1.0, scaleY = 1.0;
	Color bgColor;
	boolean paintGhost = false;

	/**
	 * The constructor which sets the image of the Figure
	 * @param img Icon of the figure
	 */
	public GFigureCop(BufferedImage img) {
		bufimg = img;
	}

	/**
	 * The constructor which sets the image of the Figure and the color
	 * @param img Icon of the figure
	 * @param color Color of the figure
	 */
	public GFigureCop(BufferedImage img, Color color) {
		bufimg = img;
		bgColor = color;
	}

	/**
	 * Sets the scale of the figure
	 * 
	 * 
	 * @param scaleX
	 *            scalingfactor for x-axis
	 * @param scaleY
	 *            scalingfactor for y-axis
	 */
	public void setScale(double scaleX, double scaleY) {
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	}

	/**
	 * Sets the color of the figure
	 * 
	 * 
	 * @param color
	 *            a Color object containing the wished color
	 */
	public void setColor(Color color) {
		bgColor = color;
	}

	/**
	 * sets the figure to be painted transparent or not
	 * 
	 * 
	 * @param ghostly
	 *            boolean accepting true or false
	 */
	public void setGhost(boolean ghostly) {
		this.paintGhost = ghostly;
	}

	/**
	 * Paint method for figurecop, this method paints the figure, if it should
	 * be painted with lower transparency, it uses an Alphacomposite to lower
	 * it. on top of that it is scaled classwide by the scalingfactor
	 * 
	 * @param g Graphics
	 */
	@Override
	public void paint(Graphics g) {
		if (bufimg != null) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
					RenderingHints.VALUE_COLOR_RENDER_SPEED);
			if (paintGhost) {
				AlphaComposite ac = java.awt.AlphaComposite.getInstance(
						AlphaComposite.SRC_OVER, 0.6F);
				g2.setComposite(ac);

			}
			if (bgColor != null) {
				g2.setColor(bgColor);
				g2.fillRect(0, (int) (bufimg.getHeight() * scaleY)
						- (int) (10 * scaleY), (int) (33 * scaleX),
						(int) (10 * scaleY));
			}
			g2.scale(scaleX, scaleY);

			g2.drawImage(bufimg, null, 0, 0);

		}
	}

}
