
import java.util.*;
import java.io.*;

public class ProjectOne
{
    public static void main(String[] args) throws IOException
    {
        //The name and then the raw stats, unedited, just copied.
        //parallel arrays.
        String[] name = new String[30];
        String[] raw = new String[30];

        //The stats themselves. Parallel above as well.
        int[] stats_K = new int[30];
        int[] stats_H=new int[30];
        int[] stats_BB=new int[30];
        int[] stats_HBP=new int[30];

        //adjusted stats. Parallel above.
        double[] stats_BA=new double[30];
        double[] stats_OBPercent=new double[30];

        //Handle the input and output to files. Named as such for accuracy.
        PrintWriter output = new PrintWriter(new File("leader.txt"));
        Scanner input = new Scanner(new File("stats.txt"));

        //goes through and fills in the name and raw arrays
        int count = 0;
        while(input.hasNextLine())
        {
            name[count] = input.next();
            raw[count] = input.next();
            count++;
        }

        //total number of actual used array slots.
        int size = count;

        //calculates and fill stats.
        //ex: for 1 sets stats_Xxxx to the returned value of the raw stats at 1.
       for(int i= 0;i < size; i++ )
        {
            stats_K[i] = calcK(raw[i]);
            stats_H[i] = calcH(raw[i]);
            stats_BB[i] = calcBB(raw[i]);
            stats_HBP[i] = calcHBP(raw[i]);
            stats_BA[i] = calcBA(raw[i]);
            stats_OBPercent[i] = calcOBPercent(raw[i]);
        }

        //outputs individual scores + a nice line break;
        output.println("Individual Scores: ");
        for(int i = 0; i<size;i++)
        {
            output.println("   Name: " +name[i]);
            output.print("   BA: ");
            output.printf("%.3f",stats_BA[i]);
            output.println();
            output.print("   OB%: ");
            output.printf("%.3f",stats_OBPercent[i]);
            output.println();
            output.println("   H: " +stats_H[i]);
            output.println("   BB: " +stats_BB[i]);
            output.println("   K: " + stats_K[i]);
            output.println("   HBP: " +stats_HBP[i]);
            output.println();
        }

        //calls topdog to output to output all the league leaders in each catagory.
        topdog(name, stats_K, stats_BA,stats_OBPercent, stats_H, stats_BB, stats_HBP, output);

        //don't forget to close up files at the end!
        input.close();
        output.close();
    }

    //calculates K
    public static int calcK(String raw)
    {
        //converts to char array and readies counter
        int counter=0;
        char[] charRaw = raw.toCharArray();

        //every time a K is seen, count it.
        for(int i=0;i<raw.length();i++)
        {
            if(charRaw[i] == 'K')
            {
                counter++;
            }
        }

        return counter;
    }

    //calculates H
    public static int calcH(String raw)
    {
        //converts to char array and readies counter
        int counter=0;
        char[] charRaw = raw.toCharArray();

        //every time a H is seen, count it.
        for(int i=0;i<raw.length();i++)
        {
            if(charRaw[i] == 'H')
            {
                counter++;
            }
        }
        return counter;
    }


    //calculates BB
    public static int calcBB(String raw)
    {
        //converts to char array and readies counter
        int counter=0;
        char[] charRaw = raw.toCharArray();

        //every time a W is seen, count it.
        for(int i=0;i<raw.length();i++)
        {
            if(charRaw[i] == 'W')
            {
                counter++;
            }
        }
        return counter;
    }

    //Calculates HBP
    public static int calcHBP(String raw)
    {
        //converts to char array and readies counter
        int counter=0;
        char[] charRaw = raw.toCharArray();

        //every time a P is seen, count it.
        for(int i=0;i<raw.length();i++)
        {
            if(charRaw[i] == 'P')
            {
                counter++;
            }
        }
        return counter;
    }

    //calulates BA
    public static double calcBA(String raw)
    {
        //converts to char array and readies counter
        double counter=0, counter2 =0;
        char[] charRaw = raw.toCharArray();

        //every time a H is seen, count it. Every time an at-bat is seen, count it.
        for(int i=0;i<raw.length();i++)
        {
            if(charRaw[i] == 'H')
            {
                counter++;
            }
            if(charRaw[i] == 'H'||charRaw[i] == 'O'||charRaw[i] == 'K')
            {
                counter2++;
            }
        }

        //combines and maths the two values then returns.
        double BA = counter/counter2;
        return BA;
    }

    //calculates OB%
    public static double calcOBPercent(String raw)
    {
        //converts to char array and readies counter
        double counter=0, counter2 =0;
        char[] charRaw = raw.toCharArray();

        //every time a h/W/p is seen, count it. Every time at plate, count it.
        for(int i=0;i<raw.length();i++)
        {
            if(charRaw[i] == 'H'||charRaw[i] == 'P'||charRaw[i] == 'W')
            {
                counter++;
            }
            if(charRaw[i] == 'H'||charRaw[i] == 'O'||charRaw[i] == 'K'||charRaw[i] == 'P'||charRaw[i] == 'W'||charRaw[i] == 'S')
            {
                counter2++;
            }
        }

        //combines and maths the two values then returns.
        double OBp = counter/counter2;
        return OBp;
    }

    //displays the top leaders
    public static void topdog(String[] name, int[] K, double[] BA, double[] OBP, int[] H, int[] BB, int[] HBP, PrintWriter output)
    {
        //sets up header.
        output.println("LEAGUE LEADERS: ");

        //loops through and find the top scoring value of all stats.
        double BATop=0, OBPTop=0, HTop=0, BBTop=0, KTop=0, HBPTop=0;
        for(int i =0; i<30;i++)
        {
            if(BA[i] > BATop)
            {
                BATop = BA[i];
            }
            if(H[i] > HTop)
            {
                HTop = H[i];
            }
            if(BB[i] > BBTop)
            {
                BBTop = BB[i];
            }
            if(K[i] > KTop)
            {
                KTop = K[i];
            }
            if(HBP[i] > HBPTop)
            {
                HBPTop = HBP[i];
            }
        }

        //The following all match the top value and print the name parallel to it.
        output.print("   BA: ");
        for(int i =0; i<30;i++)
        {
            if(BATop == BA[i])
            {
                output.println(name[i]);
            }
        }
        output.print("   OB%: ");
        for(int i =0; i<30;i++)
        {
            if(OBPTop == OBP[i])
            {
                output.println(name[i]);
            }
        }
        output.print("   H: ");
        for(int i =0; i<30;i++)
        {
            if(HTop == H[i])
            {
                output.println(name[i]);
            }
        }
        output.print("   BB: ");
        for(int i =0; i<30;i++)
        {
            if(BBTop == BB[i])
            {
                output.println(name[i]);
            }
        }
        output.print("   K: ");
        for(int i =0; i<30;i++)
        {
            if(KTop == K[i])
            {
                output.println(name[i]);
            }
        }
        output.print("   HBP: ");
        for(int i =0; i<30;i++)
        {
            if(HBPTop == HBP[i])
            {
                output.println(name[i]);
            }
        }
    }
}
