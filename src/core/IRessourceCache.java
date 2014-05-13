package core;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public interface IRessourceCache {

    /**
     * This is the IO-method used to read an .ini file and create an Config
     * object from the obtained data.
     * 
     * @see core.Config
     * @param configIniNumber
     *            ini number of config
     * @return if loading the config did work
     * @since 0.3
     */
    public abstract boolean loadConfig(int configIniNumber);

    /**
     * Scans the directory for new maps scan for save games needs to be
     * implanted and save them in the iniPaths String array
     * 
     * @see core.FileScanner
     * @since 0.2
     */
    public abstract void scanDir();

    /**
     * getter method for mapInipaths
     * 
     * @since 0.2
     * @return array of Strings containing the relative path to each ini file
     */
    public abstract String[] getMapIniPaths();

    /**
     * getter method for the config-object
     * 
     * @since 0.2
     * @return configuration for current map
     */
    public abstract IConfig getConfig();

    /**
     * looks for a config file by its name. Returns the config object.
     * 
     * @param mapName
     *            map name
     * @return configuration for the map
     */
    public abstract IConfig getConfigByName(String mapName);

    /**
     * load the Map with the current config.
     * 
     * @see core.FileLoader
     * @return the boolean if the map could be read
     */
    public abstract boolean loadMap();

    /**
     * getter for the Image map object
     * 
     * @return BufferedImage object of the map.
     */
    public abstract BufferedImage getMap();

    /**
     * getter for the coordinates Map object
     * 
     * @return the Coordinates Map
     */
    public abstract Map<String, Point> getCoordinates();

    /**
     * load the Coordinates.
     * 
     * @see core.FileLoader
     * @return the boolean if the coordinates file could be read
     */
    public abstract boolean loadCoordinates();

    /**
     * getter for the staion Map object
     * 
     * @return Map with all station.
     */
    public abstract IStationMap getLinks();

    /**
     * load the Links.
     * 
     * @see core.FileLoader
     * @return the boolean if the coordinates file could be read
     */
    public abstract boolean loadLinks();

    /**
     * loads the languages from the media/language directory.
     * 
     */
    public abstract void loadLanguages();

    /**
     * returns the language corresponding to the parameter, if no language with
     * the name is found english is retourned by default
     * 
     * @param langName
     *            the name of the language
     * @return the languages hashMap containing all displayed and localized
     *         strings.
     */
    public abstract Map<String, String> getLanguage(String langName);

	/**
	 * Get a list with all languages names.
	 * 
	 * @return List with all languages names.
	 */
	public abstract List<String> getLanguageNames();

	/**
	 * Get a list with all map names.
	 * 
	 * @return List with all map names
	 */
	public abstract List<String> getMapNames();

	/**
	 * Load the figure icons from media dir.
	 * 
	 * @return True, if figure icons were loaded
	 */
	public abstract boolean loadFigureIcons();

	/**
	 * Get a list with all figure icons.
	 * 
	 * @return List with all figure icons
	 */
	public abstract List<BufferedImage> getFigureIcons();

	/**
	 * Convert an array of String to list of Integer.
	 * 
	 * @param ref
	 *            Stringarray to be converted
	 * @return The resulting List of Intergers
	 */
	public abstract List<Integer> convertArrayToList(String[] ref);

}