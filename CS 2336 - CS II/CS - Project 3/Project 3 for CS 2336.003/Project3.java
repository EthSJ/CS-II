
//part of package tripod. This is a: head. Required are: tentacle(s).
package bipod;
import java.util.*;
import java.io.*;

public class Project3
{
    public static void main(String[] args) throws IOException
    {
        //output for file
        File out = new File("leader.txt");
        PrintWriter output = new PrintWriter(out);

        //Test if file is there.
        File file;
        Scanner input;
        try
        {
            file = new File("stats.txt");
            input = new Scanner(file);
        }
        catch(IOException ex)
        {
            //to appease below. Doesn't matter what it gets input from anyways
            input = new Scanner(System.in);

            //getting file path
            String absolutePath = out.getAbsolutePath();
            String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));

            //error message
            System.out.println("File was not found at: ");
            System.out.println(filePath + "\\stats.txt");
            System.out.println("Please place stats.txt there and run program again.");
            System.exit(0);
        }

        //create linked list
        LinkedList chain = new LinkedList();

        //gets input so long as seperated by spaces.
        // Doesn't care which line it's on
        String name;
        String stats_raw;
        while(input.hasNextLine())
        {
            name = input.next();
            stats_raw = input.next();
            chain.addNode(name, stats_raw);
        }

        //output alpha sorted list. Puts nice newline buffer
        Node we = chain.getHead();
        chain.inspectList(we, output);
        output.println();

        //start with league leaders now
        output.println("League Leader(s):");

        //sorts for BA and then outputs leader.
        output.print("BATTING AVERAGE: ");
        output.println();
        chain.sort('a');
        int count = 0;
        //returns place to account for ties
        for(int place=0; place<3;place++)
        {
             count = chain.win(count,place+1, 'a', output);
        }
        count= 0;
        output.println();

        //sorts for OBP then outputs leader
        output.print("ON BASE PERCENTAGE: ");
        output.println();
        chain.sort('b');
        //returns place to account for ties
        for(int place=0; place<3;place++)
        {
             count = chain.win(count,place+1, 'b', output);
        }
        count= 0;
        output.println();

        //sorts for H then outputs the leader
        output.print("HITS: ");
        output.println();
        chain.sort('c');
        //returns place to account for ties
        for(int place=0; place<3;place++)
        {
             count = chain.win(count,place+1, 'c', output);
        }
        count= 0;
        output.println();

        //sorts for BB then outputs the leader
        output.print("WALKS: ");
        output.println();
        chain.sort('d');
        //returns place to account for ties
        for(int place=0; place<3;place++)
        {
             count = chain.win(count,place+1, 'd', output);
        }
        count= 0;
        output.println();

        //sorts for K then outputs the leader
        output.print("STRIKEOUTS: ");
        output.println();
        chain.sort('e');
        //returns place to account for ties
        for(int place=0; place<3;place++)
        {
             count = chain.win(count,place+1, 'e', output);
        }
        count= 0;
        output.println();

        //Sorts for HBP and then outputs the leader
        output.print("HITS BY PITCH: ");
        output.println();
        chain.sort('f');
        //returns place to account for ties
        for(int place=0; place<3;place++)
        {
             count = chain.win(count,place+1, 'f', output);
        }

        //close up the files. we're all done!
        input.close();
        output.close();
    }
}
