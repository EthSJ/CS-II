
import java.util.*;
public class HW4
{
    //start of part one
    public static void main(String[] args) throws Exception
    {
        //check for bad string. set false by default
        boolean good = false;

        // Create a Scanner
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter a string
        System.out.print("Enter a hex number: ");
        String hex = input.nextLine();

        //while we've got a bad value:
        //try to see it has a bad value. If it does, or is too short, throw an error
        //else call it good
        //catch the bad value and try again.
        while(good == false)
        {
            try
            {
                if(hex.contains(" ") || hex.length() !=8 || !hex.matches(".*[^G-Z].*"))
                {
                    throw new HexFormatException();
                }
                else
                    good=true;
            }
            catch(HexFormatException qwerty)
            {
                System.out.println(qwerty.getMessage());
                System.out.print("Please reenter:");
                hex = input.nextLine();
            }
        }

        System.out.println("The decimal value for hex number "+ hex + " is " + hexToDecimal(hex.toUpperCase()));
    }

    //changes hex to decimal
    public static int hexToDecimal(String hex) throws Exception
    {
        int decimalValue = 0;
        for (int i = 0; i < hex.length(); i++)
        {
            char hexChar = hex.charAt(i);
            //try to catch the bad value if it's not a correct number
            try
            {
                if(Character.getNumericValue(hex.charAt(i)) <0)
                {
                    throw new NumberFormatException();
                }
            }
            catch(NumberFormatException asdf)
            {
                System.out.print(asdf.getMessage());
            }
            //sends to parse letter
            decimalValue = decimalValue * 16 + hexCharToDecimal(hexChar);
        }

        return decimalValue;
    }

    //changes char to decimal
    public static int hexCharToDecimal(char ch)
    {
        if (ch >= 'A' && ch <= 'F')
            return 10 + ch - 'A';
        else // ch is '0', '1', ..., or '9'
            return ch - '0';
    }
}
//number exception
class NumberFormatException extends Exception
{
      //exception call for format bad
      public NumberFormatException()
      {
          super("That is not a hex value.");
      }
}
//hex string exception
class HexFormatException extends Exception
{
      //exception call for format bad.
      public HexFormatException()
      {
          super("That is not a hex value.");
      }


}


//start of part 2 of HW
class Octogon extends GeometricObject implements Comparable<Octogon>, Cloneable
{
    private int side;

    //set basic side to 1
    Octogon()
    { side = 1; }

    //takes a side. If the side is negative, throw exception
    Octogon(int side) throws NegLegException
    {
        try
        {
            if(side < 0)
                throw new NegLegException();
        }
        catch(NegLegException poi)
        {
            System.out.println(poi.getMessage());

        }
    }
    //gets side
    int getSide()
    {return side;}

    //overrides get perimeter.
    @Override
    public double getPerimeter()
    {
        return side+side+side+side+side+side+side+side;
    }

    //overrides area.
    @Override
    public double getArea()
    {
        return (2+4/22)*side*side;
    }

    //override clone method. Throws exception if it's not a supported clone.
   @Override
   public Object clone()throws CloneNotSupportedException
   {
	return (Octogon)super.clone();
   }

   //compares two. overrides compareTo
   @Override
   public int compareTo(Octogon o)
   {

	int compareQuantity = ((Octogon) o).getSide();
	return this.side - compareQuantity;
   }

}

//the negative length exception. Simply sets message
class NegLegException extends Exception
{
      //exception call to super to set message
      public NegLegException()
      {
          super("Cannot have a negative side length. Please try again");
      }
}

//base abstract geometricobject class
abstract class GeometricObject
{
    //variables
    private String color;
    private boolean filled;
    private java.util.Date dateCreated;


    //sets new object to basic unfilled white
    protected GeometricObject()
    {
      color="white";
      filled = false;
      dateCreated = new java.util.Date();
    }

    //sets date and colors and filled. Overloaded
    protected GeometricObject(String color, boolean filled)
    {
      dateCreated = new java.util.Date();
      this.color = color;
      this.filled = filled;
    }


    //gets color
    public String getColor()
    {
      return color;
    }


    //sets color
    public void setColor(String color)
    {
      this.color = color;
    }


    //gets filled
    public boolean isFilled()
    {
      return filled;
    }


    //sets filled.
    public void setFilled(boolean filled)
    {
      this.filled = filled;
    }

    //sets date created
    public java.util.Date getDateCreated()
    {
      return dateCreated;
    }

    //overrides the toString method
    @Override
    public String toString()
    {
      return "created on " + dateCreated + "\ncolor: " + color +
        " and filled: " + filled;
    }

    //abstract getArea. Overriden above
    public abstract double getArea();

    //abstract getPerimeter. Overriden above
    public abstract double getPerimeter();
}
