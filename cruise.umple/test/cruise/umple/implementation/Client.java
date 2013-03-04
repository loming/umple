/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.16.0.2388 modeling language!*/



/**
 * attributes on both sides of the constraint's boolean expression
 */
// line 2 "BasicConstraint3.ump"
public class Client
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Client Attributes
  private int minAge;
  private int age;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Client(int aMinAge, int aAge)
  {
    if ( !(aAge>minAge))
    { 
     throw new RuntimeException("Please provide a valid age"); 
    }
    if ( !(age>aMinAge))
    { 
     throw new RuntimeException("Please provide a valid minAge"); 
    }
    minAge = aMinAge;
    age = aAge;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setMinAge(int aMinAge)
  {
    boolean wasSet = false;
    if (age>aMinAge)
    {
    minAge = aMinAge;
    wasSet = true;
    }
    return wasSet;
  }

  public boolean setAge(int aAge)
  {
    boolean wasSet = false;
    if (aAge>minAge)
    {
    age = aAge;
    wasSet = true;
    }
    return wasSet;
  }

  public int getMinAge()
  {
    return minAge;
  }

  public int getAge()
  {
    return age;
  }

  public void delete()
  {}

}