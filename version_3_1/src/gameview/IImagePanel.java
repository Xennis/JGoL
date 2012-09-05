package gameview;

import java.awt.Dimension;
import java.awt.Graphics;

public interface IImagePanel {

	/**
	 * Get size of the image.
	 * 
	 * @return the size of the image
	 */
	public abstract Dimension getSize();

	/**
	 * Set the size of the image
	 */
	public abstract void setImageSize();

	/**
	 * @param scaling
	 *            which sets the scale of the image
	 */
	public abstract void scaleImage(double scaling);

	/*
	 * (non-Javadoc)
	 * 
	 * @see gameView.gView.IGImagePanel#paintComponent(java.awt.Graphics)
	 */
	public abstract void paintComponent(Graphics g);

}