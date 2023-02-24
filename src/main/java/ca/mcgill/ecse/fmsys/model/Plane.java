/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.fmsys.model;

// line 23 "../../../../../flightmanagementsystem.ump"
public class Plane
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static int nextPlaneId = 1;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Plane Attributes
  private String model;

  //Autounique Attributes
  private int planeId;

  //Plane Associations
  private FMS fMS;
  private Flight nextFlight;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Plane(String aModel, FMS aFMS, Flight aNextFlight)
  {
    model = aModel;
    planeId = nextPlaneId++;
    boolean didAddFMS = setFMS(aFMS);
    if (!didAddFMS)
    {
      throw new RuntimeException("Unable to create plane due to fMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddNextFlight = setNextFlight(aNextFlight);
    if (!didAddNextFlight)
    {
      throw new RuntimeException("Unable to create plane due to nextFlight. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setModel(String aModel)
  {
    boolean wasSet = false;
    model = aModel;
    wasSet = true;
    return wasSet;
  }

  public String getModel()
  {
    return model;
  }

  public int getPlaneId()
  {
    return planeId;
  }
  /* Code from template association_GetOne */
  public FMS getFMS()
  {
    return fMS;
  }
  /* Code from template association_GetOne */
  public Flight getNextFlight()
  {
    return nextFlight;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFMS(FMS aFMS)
  {
    boolean wasSet = false;
    if (aFMS == null)
    {
      return wasSet;
    }

    FMS existingFMS = fMS;
    fMS = aFMS;
    if (existingFMS != null && !existingFMS.equals(aFMS))
    {
      existingFMS.removePlane(this);
    }
    fMS.addPlane(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setNextFlight(Flight aNewNextFlight)
  {
    boolean wasSet = false;
    if (aNewNextFlight == null)
    {
      //Unable to setNextFlight to null, as plane must always be associated to a nextFlight
      return wasSet;
    }
    
    Plane existingPlane = aNewNextFlight.getPlane();
    if (existingPlane != null && !equals(existingPlane))
    {
      //Unable to setNextFlight, the current nextFlight already has a plane, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Flight anOldNextFlight = nextFlight;
    nextFlight = aNewNextFlight;
    nextFlight.setPlane(this);

    if (anOldNextFlight != null)
    {
      anOldNextFlight.setPlane(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    FMS placeholderFMS = fMS;
    this.fMS = null;
    if(placeholderFMS != null)
    {
      placeholderFMS.removePlane(this);
    }
    Flight existingNextFlight = nextFlight;
    nextFlight = null;
    if (existingNextFlight != null)
    {
      existingNextFlight.setPlane(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "planeId" + ":" + getPlaneId()+ "," +
            "model" + ":" + getModel()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fMS = "+(getFMS()!=null?Integer.toHexString(System.identityHashCode(getFMS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "nextFlight = "+(getNextFlight()!=null?Integer.toHexString(System.identityHashCode(getNextFlight())):"null");
  }
}