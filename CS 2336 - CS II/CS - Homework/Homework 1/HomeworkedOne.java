
import java.util.*;

public class HomeworkedOne
{
    public static void main(String args[])
    {
        System.out.print("Enter x and y coordinates: ");

        Scanner in = new Scanner(System.in);
        double x = in.nextDouble();
        double y = in.nextDouble();

        if( (y > 0) && (x > 0) && (x + 2*y < 200) )
         System.out.println("The point is in the triangle.");
        else
         System.out.println("The point is not in the triangle.");
    }
}
