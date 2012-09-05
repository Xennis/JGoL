package core;

import java.io.*;
import java.util.*;

import core.msgpump.IMsgPump;

/**
 * This Class is used for static filesystem-scanning methods
 * 
 * @version 0.2.1
 * @author Jasper
 * 
 */
public final class FileScanner {

    /**
     * No object should be created of this
     * 
     * @since 0.1
     */
    private FileScanner() {
    }

	/**
	 * /** Scans the directory's children for files with a specific extension.
	 * If files are found their path is returned as {@link LinkedList}
	 * 
	 * @param dir
	 *            the directory which children are to be scanned
	 * @param extension
	 *            the extension of the files to be searched
	 * @param msgPump
	 *            the message pump
	 * @since 0.1
	 * @return a list of Strings containing the paths to the Files
	 */
    public static List<String> scanDir(String dir, String extension,
	    IMsgPump msgPump) {
	List<String> filePaths = new LinkedList<String>();
	File rootDir = new File(dir);

	if (rootDir.isDirectory()) {
	    File[] subDirs = rootDir.listFiles();

	    for (int i = 0; i < subDirs.length; i++) {
		String[] tempStringArray = subDirs[i].list();
		for (int ii = 0; ii < tempStringArray.length; ii++) {
		    if (tempStringArray[ii].endsWith(extension)) {
			filePaths.add(subDirs[i].toString() + File.separator
				+ tempStringArray[ii]);
			msgPump.logInfo("Habe ein " + extension
				+ "-File gefunden. path + name: "
				+ subDirs[i].toString() + File.separator
				+ tempStringArray[ii]);
		    }
		}
	    }
	}
	return filePaths;
    }

}
