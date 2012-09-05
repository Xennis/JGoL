/**
 * 
 */
package core;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Fabi
 * 
 */
public class RessourceCacheStub implements IRessourceCache {

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getConfig()
     */
    @Override
    public IConfig getConfig() {
	return new ConfigStub();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getConfigByName(java.lang.String)
     */
    @Override
    public IConfig getConfigByName(String string) {
	return new ConfigStub();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getCoordinates()
     */
    @Override
    public Map<String, Point> getCoordinates() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getFigureIcons()
     */
    @Override
    public List<BufferedImage> getFigureIcons() {
	List<BufferedImage> figureIcons = new LinkedList<BufferedImage>();
	figureIcons
		.add(new BufferedImage(100, 200, BufferedImage.TYPE_INT_RGB));
	figureIcons.add(new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB));
	return figureIcons;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getLanguage(java.lang.String)
     */
    @Override
    public Map<String, String> getLanguage(String langName) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getLanguageNames()
     */
    @Override
    public List<String> getLanguageNames() {
	return new LanguagesStub().getLanguageNames();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getLinks()
     */
    @Override
    public IStationMap getLinks() {
	return new StationMapStub();
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getMap()
     */
    @Override
    public BufferedImage getMap() {
	return new BufferedImage(300, 400, BufferedImage.TYPE_INT_RGB);
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getMapIniPaths()
     */
    @Override
    public String[] getMapIniPaths() {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#getMapNames()
     */
    @Override
    public List<String> getMapNames() {
	List<String> mapNames = new LinkedList<String>();
	mapNames.add("Gangs of Lübeck");
	mapNames.add("Testkarte");
	mapNames.add("2 player test");
	mapNames.add("Low ticket test");
	return mapNames;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadConfig(int)
     */
    @Override
    public boolean loadConfig(int configIniNumber) {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadCoordinates()
     */
    @Override
    public boolean loadCoordinates() {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadFigureIcons()
     */
    @Override
    public boolean loadFigureIcons() {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadLanguages()
     */
    @Override
    public void loadLanguages() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadLinks()
     */
    @Override
    public boolean loadLinks() {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#loadMap()
     */
    @Override
    public boolean loadMap() {
	// TODO Auto-generated method stub
	return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#scanDir()
     */
    @Override
    public void scanDir() {
	// TODO Auto-generated method stub

    }

    /*
     * (non-Javadoc)
     * 
     * @see core.IRessourceCache#convertArrayToList(java.lang.String[])
     */
    @Override
    public List<Integer> convertArrayToList(String[] ref) {
	List<Integer> list = new LinkedList<Integer>();
	list.add(24);
	list.add(23);
	list.add(-1);
	return list;
    }

}
