package gameview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

/**
 * ImagePanel for the map.
 * 
 * @author Felix
 *
 */
public class ImagePanel extends JPanel implements IImagePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BufferedImage img;
	private Dimension size;
	private Dimension originalSize;

	/**
	 * Create a image panel for the map.
	 * 
	 * @param img Map iamge
	 */
	public ImagePanel(BufferedImage img) {
		super();
		this.img = img;
		size = new Dimension(img.getWidth(), img.getHeight());
		originalSize = new Dimension(size);
		setImageSize();
	}

	/**
	 * @return the size of the imagePanel
	 */
	public Dimension getSize() {
		return size;
	}

	/**
	 * Set the size of the image
	 */
	public void setImageSize() {
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setLayout(null);
		repaint();
	}

	/**
	 * Scale the image.
	 * 
	 * @param scaling which sets the scale of the image
	 */
	public void scaleImage(double scaling) {
		size = new Dimension(
				(int) Math.round(originalSize.getWidth() * scaling),
				(int) Math.round(originalSize.getHeight() * scaling));
		setImageSize();
	}

	/**
	 * Pain all components.
	 * 
	 * @param g
	 *            g which paints a chosen graphic
	 */
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_SPEED);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(img, 0, 0, (int) size.getWidth(), (int) size.getHeight(),
				null);
	}
}
