package core;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import core.msgpump.IMsgPump;

/**
 * scanning the filesystem for map files and savegames create and hold
 * filedependend configuration objects
 * 
 * @author Jasper, Felix, Fabian, Raphael
 * @version 0.5.6
 */
public class RessourceCache implements IRessourceCache, Serializable {

    private static final long serialVersionUID = 107284861873259265L;

    /**
     * Object which holds all configuration details.
     * 
     * @since 0.1
     */
    private IConfig config;

    /**
     * Paths to all found .ini files.
     * 
     * @since 0.2
     */
    private String[] mapIniPaths;

    /**
     * @since 0.5.6
     */
    List<BufferedImage> figureIcons;

    /**
     * @since 0.5.6
     */
    private List<String> figureIconPaths;

    private int currentConfigIndex;

    /**
     * The names of all available maps.
     * 
     * @since 4.5
     */
    private List<String> mapNames;

    /**
     * boolean whether scan for IniPaths was already done.
     * 
     * @since 0.2
     */
    private boolean pathsScanned;

    /**
     * @since 0.2
     */
    private boolean configRead;

    /**
     * The BufferedImage object for the map
     * 
     * @since 0.2
     */
    private BufferedImage map;

    /**
     * the map for the coordinates of the stations
     * 
     * @since 0.3.6
     */
    private Map<String, Point> coordinates;

    /**
     * the map for the links between the stations
     * 
     * @since 0.3.6
     */
    private IStationMap links;

    /**
     * the language file object
     * 
     * @since 0.4.2
     */
    Languages lang;

    /**
     * the boolean whether the languages are loaded
     */
    private boolean languagesLoad;

    /**
     * The constructor only does basic initializations of the objects variables
     * 
     * @since 0.1
     */

    /**
     * The msgPump of the Game Application Layer for logging purpose
     * 
     * @since 2.0
     */
    IMsgPump msgPump;

    /**
     * Create object to hold all ressource information.
     * 
     * @param msgPump The message pump
     */
    public RessourceCache(IMsgPump msgPump) {
	this.msgPump = msgPump;
	config = new Config(msgPump);
	pathsScanned = false;
	configRead = false;
	languagesLoad = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getConfig()
     */
    @Override
    public IConfig getConfig() {
	if (configRead) {
	    return config;
	} else {
	    return null;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getConfigByName(java.lang.String)
     */
    @Override
    public IConfig getConfigByName(String mapName) {
	if (!pathsScanned) {
	    scanDir();
	}
	for (int i = 0; i < mapIniPaths.length; i++) {
	    loadConfig(i);
	    if (config.getName().equals(mapName)) {
		break;
	    }
	}
	return getConfig();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getCoordinates()
     */
    @Override
    public Map<String, Point> getCoordinates() {
	return coordinates;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getFigureIcons()
     */
    @Override
    public List<BufferedImage> getFigureIcons() {
	if (figureIcons == null) {
	    loadFigureIcons();
	}
	return figureIcons;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getLanguage(java.lang.String)
     */
    @Override
    public Map<String, String> getLanguage(String langName) {
	if (languagesLoad) {
	    return lang.getLanguage(langName);
	}
	loadLanguages();
	return getLanguage(langName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getLanguageNames()
     */
    @Override
    public List<String> getLanguageNames() {
	if (languagesLoad) {
	    return lang.getLanguageNames();
	}
	loadLanguages();
	return getLanguageNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getLinks()
     */
    @Override
    public IStationMap getLinks() {
	return links;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getMap()
     */
    @Override
    public BufferedImage getMap() {
	if (map == null) {
	    loadMap();
	}
	return map;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getMapIniPaths()
     */
    @Override
    public String[] getMapIniPaths() {
	if (pathsScanned) {
	    return mapIniPaths;
	} else {
	    return null;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getMapNames()
     */
    @Override
    public List<String> getMapNames() {
	if (!pathsScanned) {
	    scanDir();
	}
	if (mapNames == null) {
	    mapNames = new LinkedList<String>();
	    for (int i = 0; i < mapIniPaths.length; i++) {
		loadConfig(i);
		mapNames.add(config.getName());
	    }
	}
	return mapNames;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadConfig(int)
     */
    @Override
    public synchronized boolean loadConfig(int configIniNumber) {
	if (pathsScanned) {
	    currentConfigIndex = configIniNumber;
	    try {
		Properties prop = getConfigProperties(mapIniPaths[configIniNumber]);
		config.setName(prop.getProperty("name"));
		config.setCoordinates(prop.getProperty("coordinates"));
		config.setLinks(prop.getProperty("links"));
		config.setMap(prop.getProperty("map"));
		config.setStartat(Arrays.asList(prop.getProperty("start_at")
			.split(",")));
		config.setShowat(convertArrayToList(prop.getProperty("show_at")
			.split(",")));
		config.setTicketTypes(prop.getProperty("ticket_types").split(
			","));
		config.setThiefTokens(new HashMap<String, Integer>());
		config.setPoliceTokens(new HashMap<String, Integer>());
		for (String elem : config.getTicketTypes()) {
		    config.putPoliceTokens(elem, Integer.parseInt(prop
			    .getProperty("police_" + elem)));
		    config.putThiefTokens(elem,
			    Integer.parseInt(prop.getProperty("thief_" + elem)));
		}
	    } catch (NullPointerException e) {
		msgPump.logError("Please Check your ini for consistent identifier naming:"
			+ e.getMessage());
		return false;
	    }
	    configRead = true;
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * Load properties from config file
     * 
     * @param fileName
     *            Path to config file
     * @return Properties from file
     */
    Properties getConfigProperties(String fileName) {
	Properties prop = new Properties();
	try {
	    BufferedReader str;
	    str = new BufferedReader(new InputStreamReader(new FileInputStream(
		    fileName), Charset.forName("UTF-8")));
	    prop.load(str);
	    str.close();
	} catch (FileNotFoundException e) {
	    msgPump.logError("There is no freakin' File damnit:"
		    + e.getMessage());
	} catch (IOException e) {
	    msgPump.logError("General IO Error, Please review your " + fileName
		    + " and try again:" + e.getMessage());
	}
	return prop;

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#convertArrayToList(java.lang.String[])
     */
    @Override
    public List<Integer> convertArrayToList(String[] ref) {
	List<Integer> list = new LinkedList<Integer>();
	try {

	    for (int i = 0; i < ref.length; i++) {
		list.add(Integer.parseInt(ref[i]));
	    }

	} catch (NumberFormatException e) {
	    msgPump.logError("Error during coordinate transformation, please check config file");
	    return null;
	}
	return list;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadCoordinates()
     */
    @Override
    public boolean loadCoordinates() {
	if (configRead) {
	    coordinates = FileLoader.loadCoordinates(
		    mapIniPaths[currentConfigIndex], config.getCoordinates(),
		    msgPump);
	    return true;
	}

	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadFigureIcons()
     */
    @Override
    public boolean loadFigureIcons() {
	if (pathsScanned) {
	    figureIcons = new LinkedList<BufferedImage>();
	    for (String i : figureIconPaths) {
		BufferedImage img = FileLoader.loadImage(i, msgPump);
		figureIcons.add(img);
	    }
	    return true;
	} else {
	    return false;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadLanguages()
     */
    @Override
    public void loadLanguages() {
	lang = new Languages(msgPump);
	languagesLoad = true;

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadLinks()
     */
    @Override
    public synchronized boolean loadLinks() {
	if (configRead) {
	    links = FileLoader.loadLinks(mapIniPaths[currentConfigIndex],
		    config.getLinks(), msgPump);
	    return true;
	}
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadMap()
     */
    @Override
    public boolean loadMap() {
	if (configRead) {
	    map = FileLoader.loadImage(mapIniPaths[currentConfigIndex],
		    config.getMap(), msgPump);
	    return true;
	}
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#scanDir()
     */
    @Override
    public void scanDir() {
	List<String> temp = new LinkedList<String>();

	temp = FileScanner.scanDir(IMain.MAP_DIR, "ini", msgPump);
	mapIniPaths = new String[temp.size()];

	for (int i = 0; i < mapIniPaths.length; i++) {
	    mapIniPaths[i] = temp.get(i).toString();
	}
	figureIconPaths = FileScanner.scanDir(IMain.MEDIA_DIR, "png", msgPump);
	pathsScanned = true;
    }

}
