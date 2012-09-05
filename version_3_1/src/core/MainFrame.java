package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * Contains the main frame.
 * 
 * @author Jasper S.
 * @version 1.0
 */
public class MainFrame extends JFrame {

    /**
	 * 
	 */
    private static final long serialVersionUID = -6298241460975306283L;

    private Image dbImage;
    private Graphics dbg;

    public MainFrame(String name) {
	super(name);
	getContentPane().setBackground(Color.DARK_GRAY);
    }

    /**
     * Double buffered painting, for better visual effect and no flickering
     * 
     * @since 1.1
     * @param g Graphics
     */
    public void update(Graphics g) {

	if (dbImage == null) {
	    dbImage = createImage(this.getSize().width, this.getSize().height);
	    dbg = dbImage.getGraphics();
	}

	dbg.setColor(getBackground());
	dbg.fillRect(0, 0, this.getSize().width, this.getSize().height);

	dbg.setColor(getForeground());
	paint(dbg);

	g.drawImage(dbImage, 0, 0, this);

    }
}
