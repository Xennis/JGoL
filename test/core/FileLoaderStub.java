/**
 * 
 */
package core;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Fabian
 * 
 */
public final class FileLoaderStub {

	/**
	 * Private constructor for this stub.
	 */
	private FileLoaderStub() {
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see core.FileLoader#loadImage(java.lang.String, java.lang.String)
     */
    public static BufferedImage loadImage(String iniFilePath, String fileName) {
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.FileLoader#loadImage(java.lang.String)
     */
    public static BufferedImage loadImage(String filePathWithName) {
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.FileLoader#loadCoordinates(java.lang.String, java.lang.String)
     */
    public static Map<String, Point> loadCoordinates(String iniFilePath,
	    String fileName) {
	Map<String, Point> coordinates = new HashMap<String, Point>();
	coordinates.put("1", new Point(705, 501));
	coordinates.put("2", new Point(781, 452));
	coordinates.put("3", new Point(879, 422));

	return coordinates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.FileLoader#loadLinks(java.lang.String, java.lang.String)
     */
    public static IStationMap loadLinks(String iniFilePath, String fileName) {
	IStationMap links = new StationMap();
	links.put("1", new Station("1"));
	links.put("2", new Station("2"));

	links.get("1").addLink("walk", "2");
	links.get("1").addLink("walk", "5");

	links.get("2").addLink("walk", "1");
	links.get("2").addLink("walk", "3");

	links.get("2").addLink("swim", "32");
	links.get("2").addLink("swim", "153");

	links.get("1").addLink("car", "3");
	links.get("1").addLink("car", "15");

	return links;
    }
}
