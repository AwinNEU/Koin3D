/**
 * 
 */
package jsceneviewer;

import jsceneviewer.GLData.Profile;

/**
 * @author Yves Boyadjian
 *
 */
public class GLData {
    /*
     * New fields not in SWT's GLData
     */

    public static enum Profile {
        CORE, COMPATIBILITY;
    }

    public static enum API {
        GL, GLES;
    }

    public static enum ReleaseBehavior {
        NONE, FLUSH;
    }

	public boolean doubleBuffer;
	public int redSize;
	public int greenSize;
	public int blueSize;
	public int alphaSize;
	public int depthSize;
	public boolean stereo;
	public int majorVersion;
	public int minorVersion;
	public API api;
	public Profile profile;
	public boolean debug;
	public int accumGreenSize;
	public int accumAlphaSize;
	public int accumBlueSize;
	public int accumRedSize;

}