/**
 * 
 */
package jscenegraph.coin3d.inventor.VRMLnodes;

import jscenegraph.database.inventor.SbRotation;
import jscenegraph.database.inventor.SbVec3f;
import jscenegraph.database.inventor.SoType;
import jscenegraph.database.inventor.fields.SoFieldData;
import jscenegraph.database.inventor.fields.SoSFRotation;
import jscenegraph.database.inventor.fields.SoSFVec3f;
import jscenegraph.database.inventor.nodes.SoSubNode;

/**
 * @author BOYADJIAN
 *
 */
public class SoVRMLTransform extends SoVRMLGroup {

	private final SoSubNode nodeHeader = SoSubNode.SO_NODE_HEADER(SoVRMLTransform.class,this);
	   
	   public                                                                     
	    static SoType       getClassTypeId()        /* Returns class type id */   
	                                    { return SoSubNode.getClassTypeId(SoVRMLTransform.class);  }                   
	  public  SoType      getTypeId()      /* Returns type id      */
	  {
		  return nodeHeader.getClassTypeId();
	  }
	  public                                                                  
	    SoFieldData   getFieldData()  {
		  return nodeHeader.getFieldData();
	  }
	  public  static SoFieldData[] getFieldDataPtr()                              
	        { return SoSubNode.getFieldDataPtr(SoVRMLTransform.class); }    	  	
	
	  public final SoSFVec3f translation = new SoSFVec3f();
	  public final SoSFRotation rotation = new SoSFRotation();
	  public final SoSFVec3f scale = new SoSFVec3f();
	  public final SoSFRotation scaleOrientation = new SoSFRotation();
	  public final SoSFVec3f center = new SoSFVec3f();

/*!
  Constructor.
*/
public SoVRMLTransform()
{
	SoVRMLTransform_commonConstructor();
}

/*!
  Constructor. \a numchildren is the expected number of children.
*/
public SoVRMLTransform(int numchildren) {
  super(numchildren);
  SoVRMLTransform_commonConstructor();
}

private void
SoVRMLTransform_commonConstructor()
{
	nodeHeader.SO_VRMLNODE_INTERNAL_CONSTRUCTOR(SoVRMLTransform.class);

	nodeHeader.SO_VRMLNODE_ADD_EXPOSED_FIELD(translation,"translation", new SbVec3f(0.0f, 0.0f, 0.0f));
  nodeHeader.SO_VRMLNODE_ADD_EXPOSED_FIELD(rotation,"rotation", (SbRotation.identity()));
  nodeHeader.SO_VRMLNODE_ADD_EXPOSED_FIELD(scale,"scale", new SbVec3f(1.0f, 1.0f, 1.0f));
  nodeHeader.SO_VRMLNODE_ADD_EXPOSED_FIELD(scaleOrientation,"scaleOrientation", (SbRotation.identity()));
  nodeHeader.SO_VRMLNODE_ADD_EXPOSED_FIELD(center,"center", new SbVec3f(0.0f, 0.0f, 0.0f));
}


	/*!
	  \copydetails SoNode::initClass(void)
	*/
	public static void initClass()
	{
	  //SO_NODE_INTERNAL_INIT_CLASS(SoVRMLTransform, SO_VRML97_NODE_TYPE);
		  SoSubNode.SO__NODE_INIT_CLASS(SoVRMLTransform.class, "VRMLTransform", SoVRMLGroup.class);
	}

}