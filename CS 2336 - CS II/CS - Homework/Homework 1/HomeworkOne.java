
import java.util.*;

public class HomeworkOne
{
    public static void main(String args[])
    {
       //the scanner and what it reads in.
       Scanner qwerty = new Scanner(System.in);
       int breakup =qwerty.nextInt();

       //splits up the inputted number
       int num=0;
       while(breakup > 0)
         {
            num += breakup % 10;
            breakup /= 10;
         }
        breakup = num;

       //ouput with newline
       System.out.println(breakup);
    }
}
