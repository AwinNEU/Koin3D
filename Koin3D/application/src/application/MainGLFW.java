/**
 * 
 */
package application;

import java.awt.image.Raster;

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Color;
//import org.eclipse.swt.graphics.Cursor;
//import org.eclipse.swt.graphics.ImageData;
//import org.eclipse.swt.graphics.PaletteData;
//import org.eclipse.swt.graphics.RGB;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Shell;

import application.scenegraph.SceneGraph;
import application.scenegraph.SceneGraphIndexedFaceSet;
import application.scenegraph.ShadowTestSceneGraph;
import application.scenegraph.Soleil;
import application.viewer.glfw.SoQtWalkViewer;
import jscenegraph.database.inventor.SbColor;
import jscenegraph.database.inventor.SbVec3f;
import jscenegraph.database.inventor.nodes.SoCamera;
import jscenegraph.database.inventor.nodes.SoSeparator;
import jsceneviewerglfw.inventor.qt.SoQt;
import jsceneviewerglfw.inventor.qt.SoQtCameraController;
import jsceneviewerglfw.inventor.qt.viewers.SoQtFullViewer;
import jsceneviewerglfw.Cursor;
import jsceneviewerglfw.Display;
import jsceneviewerglfw.SWT;
import loader.TerrainLoader;

/**
 * @author Yves Boyadjian
 *
 */
public class MainGLFW {
	
	public static final float MINIMUM_VIEW_DISTANCE = 1.0f;

	public static final float MAXIMUM_VIEW_DISTANCE = SceneGraphIndexedFaceSet.SUN_FAKE_DISTANCE * 1.5f;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = new Display();
		//Shell shell = new Shell(display);
		//shell.setLayout(new FillLayout());
		
		TerrainLoader l = new TerrainLoader();
		Raster rw = l.load("ned19_n47x00_w122x00_wa_mounttrainier_2008\\ned19_n47x00_w122x00_wa_mounttrainier_2008.tif");
		Raster re = l.load("ned19_n47x00_w121x75_wa_mounttrainier_2008\\ned19_n47x00_w121x75_wa_mounttrainier_2008.tif");
		
		SoQt.init("demo");
		
		int style = 0;//SWT.NO_BACKGROUND;
		
		//SoSeparator.setNumRenderCaches(0);
		//SceneGraph sg = new SceneGraphQuadMesh(r);
		int overlap = 13;		
		SceneGraph sg = new SceneGraphIndexedFaceSet(rw,re,overlap);
		//SceneGraph sg = new ShadowTestSceneGraph();
		
		SoQtWalkViewer viewer = new SoQtWalkViewer(SoQtFullViewer.BuildFlag.BUILD_NONE,SoQtCameraController.Type.BROWSER,/*shell*/null,style);

		viewer.buildWidget(style);
		viewer.setHeadlight(false);
		
		
		viewer.setSceneGraph(sg.getSceneGraph());

		viewer.setUpDirection(new SbVec3f(0,0,1));

		viewer.getCameraController().setAutoClipping(false);
		
		SoCamera camera = viewer.getCameraController().getCamera();
		
		camera.nearDistance.setValue(MINIMUM_VIEW_DISTANCE);
		camera.farDistance.setValue(MAXIMUM_VIEW_DISTANCE);
		
		camera.position.setValue(0,0,0);
		camera.orientation.setValue(new SbVec3f(0,1,0), -(float)Math.PI/2.0f);
		
		viewer.getCameraController().changeCameraValues(camera);
		
		viewer.getSceneHandler().setBackgroundColor(new SbColor(0,0,1));
		
		sg.setPosition(/*sg.getCenterX()/2*/0,sg.getCenterY(),5000);
		
		final double startDate = (double)System.nanoTime() /1e9 - 60*60*4.5 / TimeConstants.JMEMBA_TIME_ACCELERATION;
		
		viewer.addIdleListener((viewer1)->{
			double nanoTime = System.nanoTime();
			double nowSec = nanoTime / 1e9 - startDate;
			double nowHour = nowSec / 60 / 60;
			double nowDay = 80;//nowHour / 24; // always summer
			double nowGame = nowHour * TimeConstants.JMEMBA_TIME_ACCELERATION/*GTA_SA_TIME_ACCELERATION*/;
			double Phi = 47;
			SbVec3f sunPosition = Soleil.soleil_xyz((float)nowDay, (float)nowGame, (float)Phi);
			sg.setSunPosition(new SbVec3f(sunPosition.y(),-sunPosition.x(),sunPosition.z()));
		});		
		
		
		//shell.open();
		
	    // create a cursor with a transparent image
//	    Color white = display.getSystemColor(SWT.COLOR_WHITE);
//	    Color black = display.getSystemColor(SWT.COLOR_BLACK);
//	    PaletteData palette = new PaletteData(new RGB[] { white.getRGB(), black.getRGB() });
//	    ImageData sourceData = new ImageData(16, 16, 1, palette);
//	    sourceData.transparentPixel = 0;
	    Cursor cursor = new Cursor();//display, /*sourceData*/null, 0, 0);
	    
//	    shell.getDisplay().asyncExec(new Runnable() {
//	        public void run() {
//	    		shell.setFullScreen(true);
//	            shell.forceActive();
//	        }
//	    });
//	    shell.forceActive();
//	    shell.forceFocus();
		
	    viewer.setCursor(cursor);
	    boolean success = viewer.setFocus();
	    
	    viewer.start();
	    
	    //viewer.getGLWidget().maximize();
	    
		// run the event loop as long as the window is open
//		while (!shell.isDisposed()) {
//		    // read the next OS event queue and transfer it to a SWT event
//		    if (!display.readAndDispatch())
//		     {
//		    // if there are currently no other OS event to process
//		    // sleep until the next OS event is available
//			  //viewer.getSceneHandler().getSceneGraph().touch();
//		        display.sleep();
//		    	//viewer.idle();
//		     }
//		}
	    System.gc();
	    System.runFinalization();
	    
	    display.loop();
	    
	    viewer.destructor();

		// disposes all associated windows and their components
		display.dispose();		
	}

}