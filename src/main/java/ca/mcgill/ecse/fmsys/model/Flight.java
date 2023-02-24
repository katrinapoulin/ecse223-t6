/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.31.1.5860.78bb27cc6 modeling language!*/

package ca.mcgill.ecse.fmsys.model;
import java.util.*;

// line 10 "../../../../../flightmanagementsystem.ump"
public class Flight
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Flight> flightsByFlightNumber = new HashMap<String, Flight>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Flight Attributes
  private String flightNumber;

  //Flight Associations
  private FMS fMS;
  private Airport fromAirport;
  private Airport toAirport;
  private Plane plane;
  private List<Person> passengers;
  private Person pilot;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Flight(String aFlightNumber, FMS aFMS, Airport aFromAirport, Airport aToAirport)
  {
    if (!setFlightNumber(aFlightNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate flightNumber. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    boolean didAddFMS = setFMS(aFMS);
    if (!didAddFMS)
    {
      throw new RuntimeException("Unable to create flight due to fMS. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFromAirport = setFromAirport(aFromAirport);
    if (!didAddFromAirport)
    {
      throw new RuntimeException("Unable to create departingFlight due to fromAirport. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddToAirport = setToAirport(aToAirport);
    if (!didAddToAirport)
    {
      throw new RuntimeException("Unable to create arrivingFlight due to toAirport. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    passengers = new ArrayList<Person>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFlightNumber(String aFlightNumber)
  {
    boolean wasSet = false;
    String anOldFlightNumber = getFlightNumber();
    if (anOldFlightNumber != null && anOldFlightNumber.equals(aFlightNumber)) {
      return true;
    }
    if (hasWithFlightNumber(aFlightNumber)) {
      return wasSet;
    }
    flightNumber = aFlightNumber;
    wasSet = true;
    if (anOldFlightNumber != null) {
      flightsByFlightNumber.remove(anOldFlightNumber);
    }
    flightsByFlightNumber.put(aFlightNumber, this);
    return wasSet;
  }

  public String getFlightNumber()
  {
    return flightNumber;
  }
  /* Code from template attribute_GetUnique */
  public static Flight getWithFlightNumber(String aFlightNumber)
  {
    return flightsByFlightNumber.get(aFlightNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithFlightNumber(String aFlightNumber)
  {
    return getWithFlightNumber(aFlightNumber) != null;
  }
  /* Code from template association_GetOne */
  public FMS getFMS()
  {
    return fMS;
  }
  /* Code from template association_GetOne */
  public Airport getFromAirport()
  {
    return fromAirport;
  }
  /* Code from template association_GetOne */
  public Airport getToAirport()
  {
    return toAirport;
  }
  /* Code from template association_GetOne */
  public Plane getPlane()
  {
    return plane;
  }

  public boolean hasPlane()
  {
    boolean has = plane != null;
    return has;
  }
  /* Code from template association_GetMany */
  public Person getPassenger(int index)
  {
    Person aPassenger = passengers.get(index);
    return aPassenger;
  }

  public List<Person> getPassengers()
  {
    List<Person> newPassengers = Collections.unmodifiableList(passengers);
    return newPassengers;
  }

  public int numberOfPassengers()
  {
    int number = passengers.size();
    return number;
  }

  public boolean hasPassengers()
  {
    boolean has = passengers.size() > 0;
    return has;
  }

  public int indexOfPassenger(Person aPassenger)
  {
    int index = passengers.indexOf(aPassenger);
    return index;
  }
  /* Code from template association_GetOne */
  public Person getPilot()
  {
    return pilot;
  }

  public boolean hasPilot()
  {
    boolean has = pilot != null;
    return has;
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
      existingFMS.removeFlight(this);
    }
    fMS.addFlight(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setFromAirport(Airport aFromAirport)
  {
    boolean wasSet = false;
    if (aFromAirport == null)
    {
      return wasSet;
    }

    Airport existingFromAirport = fromAirport;
    fromAirport = aFromAirport;
    if (existingFromAirport != null && !existingFromAirport.equals(aFromAirport))
    {
      existingFromAirport.removeDepartingFlight(this);
    }
    fromAirport.addDepartingFlight(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setToAirport(Airport aToAirport)
  {
    boolean wasSet = false;
    if (aToAirport == null)
    {
      return wasSet;
    }

    Airport existingToAirport = toAirport;
    toAirport = aToAirport;
    if (existingToAirport != null && !existingToAirport.equals(aToAirport))
    {
      existingToAirport.removeArrivingFlight(this);
    }
    toAirport.addArrivingFlight(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setPlane(Plane aNewPlane)
  {
    boolean wasSet = false;
    if (plane != null && !plane.equals(aNewPlane) && equals(plane.getNextFlight()))
    {
      //Unable to setPlane, as existing plane would become an orphan
      return wasSet;
    }

    plane = aNewPlane;
    Flight anOldNextFlight = aNewPlane != null ? aNewPlane.getNextFlight() : null;

    if (!this.equals(anOldNextFlight))
    {
      if (anOldNextFlight != null)
      {
        anOldNextFlight.plane = null;
      }
      if (plane != null)
      {
        plane.setNextFlight(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPassengers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOptionalOne */
  public boolean addPassenger(Person aPassenger)
  {
    boolean wasAdded = false;
    if (passengers.contains(aPassenger)) { return false; }
    Flight existingBoardingFlight = aPassenger.getBoardingFlight();
    if (existingBoardingFlight == null)
    {
      aPassenger.setBoardingFlight(this);
    }
    else if (!this.equals(existingBoardingFlight))
    {
      existingBoardingFlight.removePassenger(aPassenger);
      addPassenger(aPassenger);
    }
    else
    {
      passengers.add(aPassenger);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removePassenger(Person aPassenger)
  {
    boolean wasRemoved = false;
    if (passengers.contains(aPassenger))
    {
      passengers.remove(aPassenger);
      aPassenger.setBoardingFlight(null);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPassengerAt(Person aPassenger, int index)
  {  
    boolean wasAdded = false;
    if(addPassenger(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePassengerAt(Person aPassenger, int index)
  {
    boolean wasAdded = false;
    if(passengers.contains(aPassenger))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPassengers()) { index = numberOfPassengers() - 1; }
      passengers.remove(aPassenger);
      passengers.add(index, aPassenger);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPassengerAt(aPassenger, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setPilot(Person aNewPilot)
  {
    boolean wasSet = false;
    if (aNewPilot == null)
    {
      Person existingPilot = pilot;
      pilot = null;
      
      if (existingPilot != null && existingPilot.getWorkingFlight() != null)
      {
        existingPilot.setWorkingFlight(null);
      }
      wasSet = true;
      return wasSet;
    }

    Person currentPilot = getPilot();
    if (currentPilot != null && !currentPilot.equals(aNewPilot))
    {
      currentPilot.setWorkingFlight(null);
    }

    pilot = aNewPilot;
    Flight existingWorkingFlight = aNewPilot.getWorkingFlight();

    if (!equals(existingWorkingFlight))
    {
      aNewPilot.setWorkingFlight(this);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    flightsByFlightNumber.remove(getFlightNumber());
    FMS placeholderFMS = fMS;
    this.fMS = null;
    if(placeholderFMS != null)
    {
      placeholderFMS.removeFlight(this);
    }
    Airport placeholderFromAirport = fromAirport;
    this.fromAirport = null;
    if(placeholderFromAirport != null)
    {
      placeholderFromAirport.removeDepartingFlight(this);
    }
    Airport placeholderToAirport = toAirport;
    this.toAirport = null;
    if(placeholderToAirport != null)
    {
      placeholderToAirport.removeArrivingFlight(this);
    }
    Plane existingPlane = plane;
    plane = null;
    if (existingPlane != null)
    {
      existingPlane.delete();
    }
    while( !passengers.isEmpty() )
    {
      passengers.get(0).setBoardingFlight(null);
    }
    if (pilot != null)
    {
      pilot.setWorkingFlight(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "flightNumber" + ":" + getFlightNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "fMS = "+(getFMS()!=null?Integer.toHexString(System.identityHashCode(getFMS())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "fromAirport = "+(getFromAirport()!=null?Integer.toHexString(System.identityHashCode(getFromAirport())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "toAirport = "+(getToAirport()!=null?Integer.toHexString(System.identityHashCode(getToAirport())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "plane = "+(getPlane()!=null?Integer.toHexString(System.identityHashCode(getPlane())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "pilot = "+(getPilot()!=null?Integer.toHexString(System.identityHashCode(getPilot())):"null");
  }
}