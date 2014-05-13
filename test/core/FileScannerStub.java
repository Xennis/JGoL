/**
 * 
 */
package core;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Fabian
 * 
 */
public final class FileScannerStub {

	/**
	 * Private constructor for this stub.
	 */
	private FileScannerStub() {
	}

    /*
     * (non-Javadoc)
     * 
     * @see core.FileLoader#loadImage(java.lang.String, java.lang.String)
     */
    public static List<String> scanDir(String dir, String extension) {
	List<String> langList = new LinkedList<String>();
	langList.add(".\\mediaTest\\languages\\de.lang");
	langList.add(".\\mediaTest\\languages\\eng.lang");
	langList.add(".\\mediaTest\\languages\\es.lang");
	return langList;
    }

}
