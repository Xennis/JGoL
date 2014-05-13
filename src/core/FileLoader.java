package core;

import java.awt.Point;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import javax.imageio.ImageIO;

import core.msgpump.IMsgPump;

/**
 * The FileLoader class is used to do the input file services. It contains only
 * static methods which are used by the ressourceCache to obtain the desired
 * data.
 * 
 * 
 * @author Jasper, Fabian
 * @version 0.3.6
 */
public final class FileLoader {

    /**
     * No object should be created of this
     * 
     * @since 0.1
     */
    private FileLoader() {
    }

	/**
	 * This method is used to obtain the image of a map. The path can be an
	 * absolute or a relative path as an String object {@link String}
	 * 
	 * @see core.RessourceCache
	 * @see core.Config
	 * @param iniFilePath
	 *            the path to the ini file from the iniFilePath array in the
	 *            ressourceCache. Example: "media/maps/HL001/luebeck.ini"
	 * @param fileName
	 *            the name of the file, no path just the name and suffix.
	 *            Example: "luebeck.jpg"
	 * @param msgPump
	 *            the message pump
	 * @return the image object of the desired map picture
	 * @since 0.2.0
	 */
    public static BufferedImage loadImage(String iniFilePath, String fileName,
	    IMsgPump msgPump) {
	File temp = new File(iniFilePath);
	String directory = temp.getParent();
	return loadImage(directory + File.separator + fileName, msgPump);
    }

	/**
	 * Load an image as buffered image.
	 * 
	 * @param filePathWithName
	 *            File name
	 * @param msgPump
	 *            the message pump
	 * @return A buffered image
	 */
    public static BufferedImage loadImage(String filePathWithName,
	    IMsgPump msgPump) {
	BufferedImage img = null;
	try {
	    img = ImageIO.read(new File(filePathWithName));
	} catch (IOException e) {
	    msgPump.logError("LEFU! Error while loading the Image");
	}
	return img;
    }

    /**
     * This method is used to obtain the desired coordinates of the stations in
     * the game. The path can be an absolute or a relative path as an String
     * object {@link String}
     * 
     * @see core.RessourceCache
     * @see core.Config
     * @param iniFilePath
     *            the path to the ini file from the iniFilePath array in the
     *            ressourceCache. Example: "media/maps/HL001/luebeck.ini"
     * @param fileName
     *            the name of the file, no path just the name and suffix.
     *            Example: "luebeck.jpg"
     * @param msgPump
     *            Message pump
     * @return a Map with the identifier as Key and the x,y coordinates as
     *         {@link java.awt.Point}
     * @since 0.3.6
     */
    public static Map<String, Point> loadCoordinates(String iniFilePath,
	    String fileName, IMsgPump msgPump) {
	File temp = new File(iniFilePath);
	String directory = temp.getParent();
	Map<String, Point> map = new HashMap<String, Point>();

	try {
	    File file = new File(directory + File.separator + fileName);
	    BufferedReader input = new BufferedReader(new FileReader(file));
	    String line = "";

	    while ((line = input.readLine()) != null) {

		String[] split = line.split(" ");

		String identifier = split[0];
		Point point = new Point(Integer.parseInt(split[1]),
			Integer.parseInt(split[2]));

		map.put(identifier, point);
		msgPump.logInfo("Habe einen Punkt der coordinaten Map hinzugefügt, identifier: "
			+ identifier + "Koordinaten " + map.get(identifier));
	    }
	} catch (IOException e) {
	    msgPump.logError("Error core.FileLoader: Loading coordinates");
	}

	return map;
    }

    /**
     * Return links from map files
     * 
     * @param iniFilePath
     *            the path to the ini file from the iniFilePath array in the
     *            ressourceCache. Example: "media/maps/HL001/luebeck.ini"
     * @param fileName
     *            File name of the links
     * @param msgPump
     *            Message pump
     * @return Map with all stations on the game map
     */
    public static IStationMap loadLinks(String iniFilePath, String fileName,
	    IMsgPump msgPump) {
	File temp = new File(iniFilePath);
	String directory = temp.getParent();
	StationMap map = new StationMap();

	try {
	    File file = new File(directory + File.separator + fileName);
	    BufferedReader input = new BufferedReader(new FileReader(file));
	    String line = "";

	    while ((line = input.readLine()) != null) {

		if (!line.isEmpty()) {
		    String[] split = line.split(" ");

		    String identifier = split[0];

		    if (!map.containsKey(identifier)) {
			map.put(identifier, new Station(identifier));
		    }

		    map.get(identifier).addLink(split[1], split[2]);
		    msgPump.logInfo("Habe einen Link der links Map hinzugefügt, link: "
			    + map.get(identifier));
		}
	    }
	} catch (IOException e) {
	    msgPump.logError("Error core.FileLoader: Loading links");
	}

	return map;
    }

}
