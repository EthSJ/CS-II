
//part of package tripod. This is a: tentacle. Required are: head, tentacle.
package bipod;
import java.io.*;

public class Node
{  //The name and stats and next.
    private String name;
    private int K, H, BB, HBP, S, AB;
    private double BA, OBP;
    private Node next;

    //constructors. Ovl is used here because it parses data too
    Node()
    {next=null; name="null"; K=H=BB=HBP=S=0; BA=OBP=0;}
    Node(String name, String stats)
    {
        //sets easy stuff
        next = null;
        this.name = name;

        //starts parsing stats data to determine individual stats
        int counter=0;
        char[] charRaw = stats.toCharArray();
        //every time a H is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'H')
            {
                counter++;
            }
        }
        H = counter;
        counter=0;

        //every time S is seen, count is
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'S')
            {
                counter++;
            }
        }
        S = counter;
        counter=0;

        //every time a K is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'K')
            {
                counter++;
            }
        }
        K = counter;
        counter=0;

        //every time a W is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'W')
            {
                counter++;
            }
        }
        BB = counter;
        counter=0;

        //every time a P is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'P')
            {
                counter++;
            }
        }
        HBP = counter;
        counter=0;

        //readies counter2 for more complex calculations
        double counter2 =0;

        //every time a H is seen, count it. Every time an at-bat is seen, count it.
        for(int i=0;i<stats.length();i++)
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
        this.AB = (int)counter2;
        this.BA =(double)Math.round(BA * 1000d) / 1000d;
        counter = 0;
        counter2=0;

        //every time a h/W/p is seen, count it. Every time at plate, count it.
        for(int i=0;i<stats.length();i++)
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
        this.OBP = (double)Math.round(OBp * 1000d) / 1000d;
    }

    //set and get H
    public int getH()
    {return H;}
    public void setH(int H)
    {this.H=H;}

    //set and get K
    public int getK()
    {return K;}
    public void setK(int K)
    {this.K=K;}

    //set and get BB
    public int getBB()
    {return BB;}
    public void setBB(int BB)
    {this.BB=BB;}

    //set and get HBP
    public int getHBP()
    {return HBP;}
    public void setHBP(int HBP)
    {this.HBP=HBP;}

    //get and set BA
    public double getBA()
    {return BA;}
    public void setBA(double BA)
    {this.BA=BA;}

    //get and set OBPercent
    public double getOBP()
    {return OBP;}
    public void setOBP(double OBP)
    {this.OBP=OBP;}

    //get and set At-hats
    public int getAB()
    {return AB;}
    public void setAB(int AB)
    {this.AB=AB;}

    //set and get S
    public int getS()
    {return S;}
    public void setS(int S)
    {this.S=S;}

    //get and set for name
    public String getName()
    {return name;}
    void setName(String name)
    {this.name = name;}

    //gets and sets node
    public Node getNext()
    {return next;}
    public void setNext(Node next)
    {this.next = next;}

    //inspects and outputs each part of the node
    public void inspectNode(Node node, PrintWriter output)
    {
        output.println(node.name+" ");
        output.println("At-bats: "+node.AB);
        output.println("Hits: "+node.H);
        output.println("Walks: "+node.BB);
        output.println("Strikeout: "+node.K);
        output.println("Hits By Pitch: "+node.HBP);
        output.println("Sacrifice: "+node.S);
        output.println("Batting average: "+node.BA);
        output.println("On Base Percentage: "+node.OBP);
    }

    //adds to the node currently being inspected if a duplicate is found.
    public void addToNode(Node node, String stats)
    {
        //see Node. The only change from it to this is that
        //we're adding to the stat, not making a new node with stats
        int counter=0;
        char[] charRaw = stats.toCharArray();
        //every time a H is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'H')
            {
                counter++;
            }
        }
        node.H += counter;
        counter=0;

        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'S')
            {
                counter++;
            }
        }
        node.S += counter;
        counter=0;

        //every time a H is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'K')
            {
                counter++;
            }
        }
        node.K += counter;
        counter=0;

        //every time a H is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'W')
            {
                counter++;
            }
        }
        node.BB += counter;
        counter=0;
        //every time a H is seen, count it.
        for(int i=0;i<stats.length();i++)
        {
            if(charRaw[i] == 'P')
            {
                counter++;
            }
        }
        node.HBP += counter;
        counter=0;
                //converts to char array and readies counter
        double counter2 =0;

        //every time a H is seen, count it. Every time an at-bat is seen, count it.
        for(int i=0;i<stats.length();i++)
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
        node.AB +=counter2;
        node.BA = (node.BA +(double)Math.round(BA * 1000d) / 1000d)/2;
        counter = 0;
        counter2= 0;

        //every time a h/W/p is seen, count it. Every time at plate, count it.
        for(int i=0;i<stats.length();i++)
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
        node.OBP = (node.OBP + (double)Math.round(OBp * 1000d) / 1000d)/2;
    }
}
