
//
import java.util.*;
import java.io.*;

public class Project_5
{
    public static void main(String[] args) throws IOException
    {
        //yar! Here be variables, matey!
        String inputted, name, play;
        char team;
        int secondSpace =0;
        int count=0;

        //output for file
        File out = new File("BoxScore.txt.");
        PrintWriter output = new PrintWriter(out);

        //Test if file is there.
        File file;
        Scanner input;
        try
        {
            file = new File("PlayByPlay.txt");
            input = new Scanner(file);
        }
        catch(IOException ex)
        {
            //to appease below. Doesn't matter what it gets input from anyways
            input = new Scanner(System.in);

            //getting file path
            String absolutePath = out.getAbsolutePath();
            String filePath = absolutePath.substring(0,absolutePath.lastIndexOf(File.separator));

            //error message + full stop.
            System.out.println("File was not found at: ");
            System.out.println(filePath + "\\PlayByPlay.txt");
            System.out.println("Check placement and name then try again.");
            System.exit(0);
        }
        //points at what has the plays in it (codes)
        //technically hardcoded, so look for this function to update codes.
        HashMap playtable = buildPlay();

        //attempts to approx a size for the array assuming each line is a new person.
        Scanner linecount = new Scanner(new File("PlayByPlay.txt"));
        while(linecount.hasNextLine())
        {linecount.nextLine();count++;}

        //makes the array of players with guesstimated size
        Player[] array = new Player[count];

        //player HashMap. populated later
        HashMap player = new HashMap();

        //the actual work of interpretting.
        while(input.hasNextLine())
        {
            //get next line and assign first char( A or H) to team
            inputted = input.nextLine();
            team = inputted.charAt(0);

            //from first letter of name to second space, find and mark second space.
            for(int i = 2; i< inputted.length();i++)
                if(inputted.charAt(i)==' ')
                    {secondSpace =i; break;}

            //name is first letter of name to the second space.
            //play is everything that's left.
            name= inputted.substring(2,secondSpace);
            play = inputted.substring(secondSpace+1, inputted.length());

            //we already have the player (check via HashMap)
            if(player.containsKey(name))
            {
                //increase stats (play key, name key, player[] array)
                incremeantor((String)playtable.get(play),(int)player.get(name),array);
            }
            //we don't have player
            else
            {
                //search for loop an empty spot to place the player in array.
                for(int i=0;i<array.length;i++)
                {
                    //found an empty spot
                    if(array[i] == null)
                    {
                        //put the player into the HashMap
                        player.put(name, i);
                        //if away team, make the player and then increase player with play just gotten
                        if(team == 'A')
                        {
                            array[i] = new Player(false, name);
                            //increase stats (play key, name key, player[] array)
                            incremeantor((String)playtable.get(play),(int)player.get(name),array);
                        }
                        //if home team (only other option), make the player and then increase player with play just gotten
                        else
                        {
                            array[i] = new Player(true, name);
                            //increase stats (play key, name key, player[] array)
                            incremeantor((String)playtable.get(play),(int)player.get(name),array);
                        }
                        //break out of loop, we don't need to try and add a second person until we have one.
                        break;
                    }
                }
            }
        }
        //output the nice formatted output
        //*IMPORTANT* this sorts which voids HashMap quick reference
        //use last or near last.
        formattedOutput(array, output);
        output.println();

        //prints out the league leaders
        leagueLeaders(array, output);

        //close up the files. we're all done!
        input.close();
        output.close();

    }
    //increases player statistics
    public static Player[] incremeantor(String play, int key, Player[] array)
    {
        //every code is a plate appearance.
        //"Each plate appearance will be given in a file"
        //increase it by 1.
        array[key].setPA(array[key].getPA()+1);

        //At-bat requires multiple checks to increase.
        if(play.equals("H") || play.equals("O")|| play.equals("K") || play.equals("at_Bat"))
            array[key].setAt_bats(array[key].getAt_bats()+1);

        //switch to find what to do next (Was formerly a ton of if)
        switch(play)
        {
            case "H":array[key].setH(array[key].getH()+1);
                break;
            case "O":array[key].setO(array[key].getO()+1);
                break;
            case "K":array[key].setK(array[key].getK()+1);
                break;
            case "W":array[key].setW(array[key].getW()+1);
                break;
            case "S":array[key].setS(array[key].getS()+1);
                break;
            case "HBP":array[key].setHBP(array[key].getHBP()+1);
                break;
        }

        //no need to check for divide by 0 because input reads plate appearance codes
        //therefore no player, no plate appear; one player, at least one plate appear.
        array[key].setOBP((double)(array[key].getH()+array[key].getHBP() +array[key].getW()) / array[key].getPA());

        //check to avoid divide by 0. don't need to check top because 0/any num == 0
        if(array[key].getAt_bats() != 0)
            array[key].setBA(((double)array[key].getH() / (double)array[key].getAt_bats()));

        //return the modified player array
        return array;
    }

    //a hardcoded like way to build the play HashMap
    public static HashMap buildPlay()
    {
        //look at all these hardcoded codes.
        //LOOK AT THEM!
        //They follow a play(input), code (key)
        HashMap play = new HashMap();
        play.put("1-3", "O");
        play.put("2-3", "O");
        play.put("3u", "O");
        play.put("3-1", "O");
        play.put("3-4", "O");
        play.put("4-3", "O");
        play.put("5-3", "O");
        play.put("6-3", "O");
        play.put("7-3", "O");
        play.put("8-3", "O");
        play.put("P1", "O");
        play.put("P2", "O");
        play.put("P3", "O");
        play.put("P4", "O");
        play.put("P5", "O");
        play.put("P6", "O");
        play.put("F7", "O");
        play.put("F8", "O");
        play.put("F9", "O");
        play.put("DP", "O");
        play.put("FC", "O");
        play.put("K", "K");
        play.put("1B", "H");
        play.put("2B", "H");
        play.put("3B", "H");
        play.put("HR", "H");
        play.put("BB", "W");
        play.put("S1", "S");
        play.put("S2", "S");
        play.put("S3", "S");
        play.put("S5", "S");
        play.put("SF7", "S");
        play.put("SF8", "S");
        play.put("SF9", "S");
        play.put("HBP", "HBP");
        play.put("E1", "at_Bat");
        play.put("E2", "at_Bat");
        play.put("E3", "at_Bat");
        play.put("E4", "at_Bat");
        play.put("E5", "at_Bat");
        play.put("E6", "at_Bat");
        play.put("E7", "at_Bat");
        play.put("E8", "at_Bat");
        play.put("E9", "at_Bat");
        return play;

    }

    //outputs to the file the formatted output.
    //*IMPORTANT* this sorts which voids hashmap quick reference
    //use last or near last.
    public static void formattedOutput(Player[] array, PrintWriter output)
    {
        //output of catagories
        output.println("Player\t\tAB\tH\tBB\tK\tHBP\tSAC\tBA\tOBP\tPA");
        //bubble sorting of names!
        int j, key =0;
        boolean flag = true;   // set flag to true to begin first pass
        Player temp;   //holding variable
        while ( flag )
        {
            flag= false;    //set flag to false awaiting a possible swap
            for( j=0;  j < array.length -1;  j++ )
            {
                //don't bother sorting nulls.
                if(array[j]!=null && array[j+1]!= null)
                {
                   if ( array[j].getName().compareToIgnoreCase(array[j+1].getName()) > 0)   // change to > for ascending sort
                   {
                          temp = array[ j ];                //swap elements
                          array[ j ] = array[ j+1 ];
                          array[ j+1 ] = temp;
                          flag = true;              //shows a swap occurred
                  }
                }
            }
        }
        //all sorted!

        //away team and while not null
        while(array[key] != null)
        {
            //print out team member specifis and then move down.
            if(array[key].isHomeTeam() == false)
            {
                output.print(array[key].getName());
                if(array[key].getName().length() > 6)
                    output.print("\t");
                else
                    output.print("\t\t");
                output.print(array[key].getAt_bats()+"\t"+array[key].getH()+"\t"+array[key].getW()
                        +"\t"+array[key].getK()+"\t"+array[key].getHBP()+"\t"+array[key].getS()+"\t");
                output.printf("%.3f", array[key].getBA());
                output.print("\t");
                output.printf("%.3f", array[key].getOBP());
                output.print("\t");
                output.println(array[key].getPA());
                key++;
            }
            //not away team? Just move down
            else
            {
                key++;
            }
        }
        //team output buffer, start at start
        output.println();
        key=0;
        //home team. Does the same thing as above, but for home team.
        while(array[key] != null)
        {
            if(array[key].isHomeTeam() == true)
            {
                output.print(array[key].getName());
                if(array[key].getName().length() > 6)
                    output.print("\t");
                else
                    output.print("\t\t");
                output.print(array[key].getAt_bats()+"\t"+array[key].getH()+"\t"+array[key].getW()
                        +"\t"+array[key].getK()+"\t"+array[key].getHBP()+"\t"+array[key].getS()+"\t");
                                output.printf("%.3f", array[key].getBA());
                output.print("\t");
                output.printf("%.3f", array[key].getOBP());
                output.print("\t");
                output.println(array[key].getPA());
                key++;
            }
            else
            {
                key++;
            }
        }

    }

    //does a lot of the same thing just repeated a lot.
    public static void leagueLeaders(Player[] array, PrintWriter output)
    {
        //variables to determine place.
        double greatest =0;
        double lastGrabbed;
        double place=0, place2=0;

        output.println("LEADERS");
        //leader for batting avg
        output.println("BATTING AVERAGE");
        //search for highest
        for(int i =0;i<array.length;i++)
        {
            if(array[i] != null)
            {
                if(array[i].getBA() > greatest)
                    greatest = array[i].getBA();
            }
            else
                break;
        }
        //so long as we're not all fighting for a 0.
        if(greatest !=0)
        {
            //output the score formatted.
            output.printf("%.3f", greatest);
            output.print("\t");
            //search for those in first with it
            for(int i=0;i<array.length;i++)
            {
                if(array[i] != null)
                {
                    if(array[i].getBA() == greatest)
                    {
                        if(place != 0)
                            output.print(", ");
                        place++;
                        output.print(array[i].getName());
                        place2 = place;
                        if(place == 3)
                            break;
                    }
                }
                else
                    break;
            }
            //line
            output.println();
            //they weren't all a tie for first
            while(place !=3 && place < 3)
            {
                //sort off last highest
                lastGrabbed = greatest;
                greatest=0;
                //look for next highest
                for(int i =0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getBA() > greatest && array[i].getBA() < lastGrabbed)
                            greatest = array[i].getBA();
                    }
                    else
                        break;
                }
                output.printf("%.3f", greatest);
                output.print("\t");
                for(int i=0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getBA() == greatest)
                        {
                            if(place != place2)
                                output.print(", ");
                            place++;
                            output.print(array[i].getName());
                        }
                    }
                    else
                    {
                        place2 = place;
                        break;
                    }
                }
                output.println();
            }
        }
        else
            output.println("No score over 0 present");

        greatest =0;
        lastGrabbed=0;
        place=0; place2=0;
        output.println();
        //on base percent
        output.println("ON-BASE PERCENTAGE");
        //search for highest
        for(int i =0;i<array.length;i++)
        {
            if(array[i] != null)
            {
                if(array[i].getOBP() > greatest)
                    greatest = array[i].getOBP();
            }
            else
                break;
        }
        if(greatest !=0)
        {
            output.printf("%.3f", greatest);
            output.print("\t");
            for(int i=0;i<array.length;i++)
            {
                if(array[i] != null)
                {
                    if(array[i].getOBP() == greatest)
                    {
                        if(place != 0)
                            output.print(", ");
                        place++;
                        output.print(array[i].getName());
                        place2 = place;
                        if(place == 3)
                            break;
                    }
                }
                else
                    break;
            }
            output.println();
            while(place !=3 && place < 3)
            {
                lastGrabbed = greatest;
                greatest=0;
                for(int i =0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getOBP() > greatest && array[i].getOBP() < lastGrabbed)
                            greatest = array[i].getOBP();
                    }
                    else
                        break;
                }
                output.printf("%.3f", greatest);
                output.print("\t");
                for(int i=0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getOBP() == greatest)
                        {
                            if(place != place2)
                                output.print(", ");
                            place++;
                            output.print(array[i].getName());
                        }
                    }
                    else
                    {
                        place2 = place;
                        break;
                    }
                }
                output.println();
            }
        }
        else
            output.println("No score over 0 present");

        greatest =0;
        lastGrabbed=0;
        place=0; place2=0;
        output.println();
        output.println("HITS");
        //search for highest
        for(int i =0;i<array.length;i++)
        {
            if(array[i] != null)
            {
                if(array[i].getH() > greatest)
                    greatest = array[i].getH();
            }
            else
                break;
        }
        if(greatest !=0)
        {
            output.printf("%.3f", greatest);
            output.print("\t");
            for(int i=0;i<array.length;i++)
            {
                if(array[i] != null)
                {
                    if(array[i].getH() == greatest)
                    {
                        if(place != 0)
                            output.print(", ");
                        place++;
                        output.print(array[i].getName());
                        place2 = place;
                        if(place == 3)
                            break;
                    }
                }
                else
                    break;
            }
            output.println();
            while(place !=3 && place < 3)
            {
                lastGrabbed = greatest;
                greatest=0;
                for(int i =0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getH() > greatest && array[i].getH() < lastGrabbed)
                            greatest = array[i].getH();
                    }
                    else
                        break;
                }
                output.printf("%.3f", greatest);
                output.print("\t");
                for(int i=0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getH() == greatest)
                        {
                            if(place != place2)
                                output.print(", ");
                            place++;
                            output.print(array[i].getName());
                        }
                    }
                    else
                    {
                        place2 = place;
                        break;
                    }
                }
                output.println();
            }
        }
        else
            output.println("No score over 0 present");


        greatest =0;
        lastGrabbed=0;
        place=0; place2=0;
        output.println();
        output.println("WALKS");
        //search for highest
        for(int i =0;i<array.length;i++)
        {
            if(array[i] != null)
            {
                if(array[i].getW() > greatest)
                    greatest = array[i].getW();
            }
            else
                break;
        }
        if(greatest !=0)
        {
            output.printf("%.3f", greatest);
            output.print("\t");
            for(int i=0;i<array.length;i++)
            {
                if(array[i] != null)
                {
                    if(array[i].getW() == greatest)
                    {
                        if(place != 0)
                            output.print(", ");
                        place++;
                        output.print(array[i].getName());
                        place2 = place;
                        if(place == 3)
                            break;
                    }
                }
                else
                    break;
            }
            output.println();
            while(place !=3 && place < 3)
            {
                lastGrabbed = greatest;
                greatest=0;
                for(int i =0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getW() > greatest && array[i].getW() < lastGrabbed)
                            greatest = array[i].getW();
                    }
                    else
                        break;
                }
                output.printf("%.3f", greatest);
                output.print("\t");
                for(int i=0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getW() == greatest)
                        {
                            if(place != place2)
                                output.print(", ");
                            place++;
                            output.print(array[i].getName());
                        }
                    }
                    else
                    {
                        place2 = place;
                        break;
                    }
                }
                output.println();
            }
        }
        else
            output.println("No score over 0 present");


        greatest =0;
        lastGrabbed=0;
        place=0; place2=0;
        output.println();
        output.println("STRIKEOUTS");
        //search for highest
        for(int i =0;i<array.length;i++)
        {
            if(array[i] != null)
            {
                if(array[i].getK() > greatest)
                    greatest = array[i].getK();
            }
            else
                break;
        }
        if(greatest !=0)
        {
            output.printf("%.3f", greatest);
            output.print("\t");
            for(int i=0;i<array.length;i++)
            {
                if(array[i] != null)
                {
                    if(array[i].getK() == greatest)
                    {
                        if(place != 0)
                            output.print(", ");
                        place++;
                        output.print(array[i].getName());
                        place2 = place;
                        if(place == 3)
                            break;
                    }
                }
                else
                    break;
            }
            output.println();
            while(place !=3 && place < 3)
            {
                lastGrabbed = greatest;
                greatest=0;
                for(int i =0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getK() > greatest && array[i].getK() < lastGrabbed)
                            greatest = array[i].getK();
                    }
                    else
                        break;
                }
                output.printf("%.3f", greatest);
                output.print("\t");
                for(int i=0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getK() == greatest)
                        {
                            if(place != place2)
                                output.print(", ");
                            place++;
                            output.print(array[i].getName());
                        }
                    }
                    else
                    {
                        place2 = place;
                        break;
                    }
                }
                output.println();
            }
        }
        else
            output.println("No score over 0 present");


        greatest =0;
        lastGrabbed=0;
        place=0; place2=0;
        output.println();
        output.println("HIT BY PITCH");
        //search for highest
        for(int i =0;i<array.length;i++)
        {
            if(array[i] != null)
            {
                if(array[i].getHBP() > greatest)
                    greatest = array[i].getHBP();
            }
            else
                break;
        }
        if(greatest !=0)
        {
            output.printf("%.3f", greatest);
            output.print("\t");
            for(int i=0;i<array.length;i++)
            {
                if(array[i] != null)
                {
                    if(array[i].getHBP() == greatest)
                    {
                        if(place != 0)
                            output.print(", ");
                        place++;
                        output.print(array[i].getName());
                        place2 = place;
                        if(place == 3)
                            break;
                    }
                }
                else
                    break;
            }
            output.println();
            while(place !=3 && place < 3)
            {
                lastGrabbed = greatest;
                greatest=0;
                for(int i =0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getHBP() > greatest && array[i].getHBP() < lastGrabbed)
                            greatest = array[i].getHBP();
                    }
                    else
                        break;
                }
                output.printf("%.3f", greatest);
                output.print("\t");
                for(int i=0;i<array.length;i++)
                {
                    if(array[i] != null)
                    {
                        if(array[i].getHBP() == greatest)
                        {
                            if(place != place2)
                                output.print(", ");
                            place++;
                            output.print(array[i].getName());
                        }
                    }
                    else
                    {
                        place2 = place;
                        break;
                    }
                }
                output.println();
            }
        }
        else
            output.println("No score over 0 present");
    }

}

class Player
{
    private String name;
    private double BA, OBP;
    private int H, W, O, K, S, at_bats, HBP, PA;
    private boolean homeTeam;

    //default is never used
    Player()
    {this.BA=this.OBP=this.S=this.H=this.W=this.HBP=this.O=this.K=this.PA=this.at_bats=0; this.homeTeam=true;this.name="Brb guys";}
    //creates a new player with a team and a name.
    Player(boolean homeTeam, String name)
    {this.BA=this.OBP=this.S=this.H=this.W=this.O=this.HBP=this.K=this.PA=this.at_bats=0; this.homeTeam = homeTeam;this.name=name;}


    //look at all these set and gets!
    //similar ones are grouped.
    public double getBA()
    {return BA;}
    public void setBA(double BA)
    {this.BA = BA;}

    public double getOBP()
    {return OBP;}
    public void setOBP(double OBP)
    {this.OBP = OBP;}

    public int getHBP()
    {return HBP;}
    public void setHBP(int HBP)
    {this.HBP = HBP;}

    public int getH()
    {return H;}
    public void setH(int H)
    {this.H = H;}

    public int getW()
    {return W;}
    public void setW(int W)
    { this.W = W;}

    public int getO()
    {return O;}
    public void setO(int O)
    {this.O = O;}

    public int getPA()
    {return PA;}
    public void setPA(int PA)
    {this.PA = PA;}

    public int getK()
    {return K;}
    public void setK(int K)
    {this.K = K;}

    public int getS()
    {return S;}
    public void setS(int S)
    {this.S = S;}

    public int getAt_bats()
    {return at_bats;}
    public void setAt_bats(int at_bats)
    {this.at_bats = at_bats;}

    public boolean isHomeTeam()
    {return homeTeam;}
    public void setHomeTeam(boolean homeTeam)
    {this.homeTeam = homeTeam;}

    public String getName()
    {return name;}
    public void setName(String name)
    {this.name = name;}
}
