/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE ${last.version} modeling language!*/

package telecome.telephone.Integer;

// line 8 "../../../IntegerMutation_uModel_attributeTyped_int.ump"
public class A
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //A Attributes
  private String x;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public A(String aX)
  {
    x = aX;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setX(String aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public String getX()
  {
    return x;
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "x" + ":" + getX()+ "]";
  }
}