//Project 2
import java.util.*;
import java.io.*;
public class ProjectTwo
{
    public static void main(String[] args) throws IOException
    {
        //variables. Turn is how many wished to watch, count is for dowhile loop
        //turns parses to int to fill turn.
        int turn, count=0;
        String turns;

        //Scanners for input and System.in
        Scanner input = new Scanner(System.in);
        File file = new File("world.txt");
        Scanner fileIn = new Scanner(file);

        //how many turns to watch. It will parse to an int
        System.out.print("I want to watch this many turns: ");
        turns = input.nextLine();
        turn = Integer.parseInt(turns);

        //checks if file is open and can read. Stops if it can't
        boolean isOpen =file.canRead();
        if(isOpen == false)
        {
           System.out.println("I can't find world.txt! It's the end of the world(.txt)!");
           System.exit(0);
        }

        //new objects for base, ants, and beetles.
        Base base = new Base();
        Ant ants = new Ant();
        Beetle beetles = new Beetle();

        //populates the base map.
        base.populate(fileIn);

        //don't need world now. Close up.
        fileIn.close();

        base.output();
        System.out.println("This is the base grid at turn 0");
        System.out.println();

        do
        {
            //switches all the critters to be ready to breed.
            ants.readyBreed();
            beetles.readyBreed();

            //ready move
            ants.readyMove();
            beetles.readyMove();

            //ants breed countdown
            for(int y = 0; y<10;y++)
            {
                for(int x=0;x<10;x++)
                {
                    if(ants.grid[y][x] instanceof Ant)
                    {
                        ants.grid[y][x].setBreed(ants.grid[y][x].getBreed() -1);
                    }
                }
            }
            //beetles breed countdown
            for(int y = 0; y<10;y++)
            {
                for(int x=0;x<10;x++)
                {
                    if(beetles.grid[y][x] instanceof Beetle)
                    {
                        beetles.grid[y][x].setBreed(beetles.grid[y][x].getBreed() -1);
                    }
                }
            }
            //ants move

            //beetles move
            beetles.move();

            //beetles starve
            beetles.setStarve();

            //if the ants and beetles are ready to breed. it does.
            ants.breed();
            beetles.breed();

            //prints what the grid looks like
            base.output();

            //Prints out turn number and enter to continue prompt
            System.out.println("End of turn "+(count+1));
            System.out.println("Press enter to continue");
            input.nextLine();
            count++;
        }while(count !=turn);

    }
}


//base class
class Base
{   public static Base[][] grid = new Base[10][10];
    protected int breed;
    protected char character;
    protected boolean moved;
    protected boolean justspawned;

    Base()
    {moved = true;character=' ';justspawned=true;}

    char getChar()
    {return character;}
    void setChar(char character)
    {this.character =character;}

    void setMoved(boolean moved)
    {this.moved = moved;}
    boolean getMoved()
    {return moved;}

    int getBreed()
    {return breed;}
    void setBreed(int breed)
    {this.breed = breed;}

    void breed()
    {/*System.out.println("Use Ant or Beetle override");*/}

    void move()
    {/*System.out.println("Use Ant or Beetle override");*/}

    void output()
    {
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                System.out.print(grid[x][y].getChar());
            }
            System.out.println();
        }
    }

    void populate(Scanner fileIn)
    {
        for(int y=0;y<10;y++)
        {
            //gets the line
            String to = fileIn.nextLine();

            //loops for x. It gets char, and then deletes base grid and replaces
            //as it finds. checks for B or b for beetle and A and a for ant.
            for(int x =0;x<10;x++)
            {
                //This makes a base grid.
                grid[y][x] = new Base();

                //replaces grid with a critter
                char get = to.charAt(x);
                if(get == 'B' || get=='b')
                {
                    grid[y][x]=null;
                    grid[y][x]= new Beetle();
                }
                else if (get == 'A'|| get=='a')
                {
                    grid[y][x]=null;
                    grid[y][x]= new Ant();
                }
            }

        }
    }

}


//ant class
class Ant extends Base
{   protected int nearme;

    Ant()
    {breed=3;character='a';moved=true;nearme=0;justspawned=true;}

    @Override
    int getBreed()
    {return breed;}
    @Override
    void setBreed(int breed)
    {this.breed = breed;}

    void readyBreed()
    {
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                if(grid[x][y] instanceof Ant)
                    grid[x][y].justspawned = false;
            }
        }
    }

    void readyMove()
    {
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                if(grid[x][y] instanceof Ant)
                    grid[x][y].moved = false;
            }
        }
    }

    @Override
    void move()
    {
        int temp=0;
        for(int y=0;y<10;y++)
        {
            for(int x =0;x<10;x++)
            {
                if(grid[y-1][x].getChar() == 'B' && y+1<10 && moved == false)
                {

                    temp = ((Ant)grid[y][x]).breed;
                    grid[y+1][x] = null;
                    grid[y+1][x] = new Ant();
                    ((Ant)grid[y+1][x]).breed = temp;
                    ((Ant)grid[y][x+1]).moved = true;
                }
                else if(grid[y][x+1].getChar() == 'B' && x-1 >=0&& moved == false)
                {
                    temp = ((Ant)grid[y][x]).breed;
                    grid[y][x-1] = null;
                    grid[y][x-1] = new Ant();
                    ((Ant)grid[y][x-1]).breed = temp;
                    ((Ant)grid[y][x+1]).moved = true;
                }
                else if(grid[y+1][x].getChar() == 'B' && y-1>=0&& moved == false)
                {
                    temp = ((Ant)grid[y][x]).breed;
                    grid[y-1][x] = null;
                    grid[y-1][x] = new Ant();
                    ((Ant)grid[y-1][x]).breed = temp;
                    ((Ant)grid[y][x+1]).moved = true;
                }
                else if(grid[y][x-1].getChar() == 'B' && x+1 <10&& moved == false)
                {
                    temp = ((Ant)grid[y][x]).breed;
                    grid[y][x+1] = null;
                    grid[y][x+1] = new Ant();
                    ((Ant)grid[y][x+1]).breed = temp;
                    ((Ant)grid[y][x+1]).moved = true;
                }
            }
        }
    }

    void awarenessCheck()
    {
        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                if(grid[y][x] instanceof Ant)
                {
                    ((Ant)grid[y][x]).nearme=0;
                }
            }
        }

        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                if(grid[y][x] instanceof Ant)
                {
                    //check normal cardinal directions
                    if(y-1 >= 0)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}
                    if(x-1 >= 0)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}
                    if(y+1 < 10)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}
                    if(x+1 < 10)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}

                    //checks combination cardinal directions
                    if(y-1 >=0 && x-1 >=0)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}
                    if(x+1 < 10 && y-1 >=0)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}
                    if(y+1 < 10 && x-1 >=0)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}
                    if(x+1 < 10 && y+1 < 10)
                        if(grid[y][x] instanceof Ant)
                        {((Ant)grid[y][x]).nearme++;}
                }
            }
        }
    }

    @Override
    void breed()
    {

        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                if(grid[y][x] instanceof Ant && grid[y][x].justspawned==false && grid[y][x].breed ==0)
                {
                    if(y-1>=0)
                    {
                        if(grid[y-1][x].getChar() == ' ')
                        {
                            grid[y-1][x] = null;
                            grid[y-1][x] = new Ant();
                            grid[y][x].breed = 4;
                        }
                        else if(x+1 <10)
                        {
                            if(grid[y][x+1].getChar()==' ')
                            {
                                grid[y][x+1]=null;
                                grid[y][x+1] = new Ant();
                                grid[y][x].breed = 4;
                            }
                            else if(y+1 < 10)
                            {
                                if(grid[y+1][x].getChar() == ' ')
                                {
                                    grid[y+1][x] = null;
                                    grid[y+1][x] = new Ant();
                                    grid[y][x].breed = 4;
                                }
                                else if(x-1 >=0)
                                {
                                    if(grid[y][x-1].getChar()==' ')
                                    {
                                        grid[y][x-1]=null;
                                        grid[y][x-1] = new Ant();
                                        grid[y][x].breed = 4;
                                    }
                                }
                            }
                        }
                    }
                    else if(x+1 <10)
                    {
                        if(grid[y][x+1].getChar()==' ')
                        {
                            grid[y][x+1]=null;
                            grid[y][x+1] = new Ant();
                            grid[y][x].breed = 4;
                        }
                        else if(y+1 < 10)
                        {
                            if(grid[y+1][x].getChar() == ' ')
                            {
                                grid[y+1][x] = null;
                                grid[y+1][x] = new Ant();
                                grid[y][x].breed = 4;
                            }
                            else if(x-1 >=0)
                            {
                                if(grid[y][x-1].getChar()==' ')
                                {
                                   grid[y][x-1]=null;
                                   grid[y][x-1] = new Ant();
                                   grid[y][x].breed = 4;
                                }
                            }
                        }
                    }
                    else if(y+1 < 10)
                    {
                        if(grid[y+1][x].getChar() == ' ')
                        {
                            grid[y+1][x] = null;
                            grid[y+1][x] = new Ant();
                            grid[y][x].breed = 4;
                        }
                        else if(x-1 >=0)
                        {
                            if(grid[y][x-1].getChar()==' ')
                            {
                                grid[y][x-1]=null;
                                grid[y][x-1] = new Ant();
                                grid[y][x].breed = 4;
                            }
                        }
                    }
                    else if(x-1 >=0)
                    {
                            if(grid[y][x-1].getChar()==' ')
                            {
                                grid[y][x-1]=null;
                                grid[y][x-1] = new Ant();
                                grid[y][x].breed = 4;
                            }
                    }
                }
            }
        }
    }

}


//beetle subclass
class Beetle extends Base
{   private int starve;

    Beetle()
    {starve=6;breed=8;character='B';justspawned=true;moved = true;}

    int getStarve()
    {return starve;}

    void readyBreed()
    {
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                if(grid[x][y] instanceof Beetle)
                    grid[x][y].justspawned = false;
            }
        }
    }

     void readyMove()
    {
        for(int x=0;x<10;x++)
        {
            for(int y=0;y<10;y++)
            {
                if(grid[x][y] instanceof Beetle)
                    grid[x][y].moved = false;
            }
        }
    }

    // sets beetle starve.
    void setStarve()
    {
        for(int y = 0; y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                if(grid[y][x] instanceof Beetle)
                {
                    ((Beetle)grid[y][x]).starve =((Beetle)grid[y][x]).getStarve() -1;
                    if(((Beetle)grid[y][x]).starve==0)
                    {
                        grid[y][x] = null;
                        grid[y][x] = new Base();
                    }
                }
            }
        }
    }

    @Override
    void breed()
    {

        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                if(grid[y][x] instanceof Beetle && grid[y][x].justspawned==false && grid[y][x].breed ==0)
                {
                    if(y-1>=0)
                    {
                        if(grid[y-1][x].getChar() == ' ')
                        {
                            grid[y-1][x] = null;
                            grid[y-1][x] = new Beetle();
                            grid[y][x].breed = 9;
                        }
                        else if(x+1 <10)
                        {
                            if(grid[y][x+1].getChar()==' ')
                            {
                                grid[y][x+1]=null;
                                grid[y][x+1] = new Beetle();
                                grid[y][x].breed = 9;
                            }
                            else if(y+1 < 10)
                            {
                                if(grid[y+1][x].getChar() == ' ')
                                {
                                    grid[y+1][x] = null;
                                    grid[y+1][x] = new Beetle();
                                    grid[y][x].breed = 9;
                                }
                                else if(x-1 >=0)
                                {
                                    if(grid[y][x-1].getChar()==' ')
                                    {
                                        grid[y][x-1]=null;
                                        grid[y][x-1] = new Beetle();
                                        grid[y][x].breed = 9;
                                    }
                                }
                            }
                        }
                    }
                    else if(x+1 <10)
                    {
                        if(grid[y][x+1].getChar()==' ')
                        {
                            grid[y][x+1]=null;
                            grid[y][x+1] = new Beetle();
                            grid[y][x].breed = 9;
                        }
                        else if(y+1 < 10)
                        {
                            if(grid[y+1][x].getChar() == ' ')
                            {
                                grid[y+1][x] = null;
                                grid[y+1][x] = new Beetle();
                                grid[y][x].breed = 9;
                            }
                            else if(x-1 >=0)
                            {
                                if(grid[y][x-1].getChar()==' ')
                                {
                                   grid[y][x-1]=null;
                                   grid[y][x-1] = new Beetle();
                                   grid[y][x].breed = 9;
                                }
                            }
                        }
                    }
                    else if(y+1 < 10)
                    {
                        if(grid[y+1][x].getChar() == ' ')
                        {
                            grid[y+1][x] = null;
                            grid[y+1][x] = new Beetle();
                            grid[y][x].breed = 9;
                        }
                        else if(x-1 >=0)
                        {
                            if(grid[y][x-1].getChar()==' ')
                            {
                                grid[y][x-1]=null;
                                grid[y][x-1] = new Beetle();
                                grid[y][x].breed = 9;
                            }
                        }
                    }
                    else if(x-1 >=0)
                    {
                            if(grid[y][x-1].getChar()==' ')
                            {
                                grid[y][x-1]=null;
                                grid[y][x-1] = new Beetle();
                                grid[y][x].breed = 9;
                            }
                    }
                }
            }
        }
    }

    @Override
    void move()
    {
        for(int y=0;y<10;y++)
        {
            for(int x=0;x<10;x++)
            {
                if(grid[y][x].getChar() == 'B')
                {
                    //impossible to have a shortest of 12 (off of grid). Allows for checking.
                    int shortest = 12;
                    boolean left=false, right=false, up=false, down=false;
                    for(int i = 0;i<10;i++)
                    {
                        if(grid[i][x].getChar()=='a')
                        {
                            int distance = Math.abs((y-i));
                            if(shortest > distance)
                                shortest = distance;


                        }
                        if(grid[y][i].getChar()=='a')
                        {
                            int distance = Math.abs(x-i);
                            if(shortest > distance)
                                 shortest= distance;
                            //there is a tie
                            if(shortest==distance)
                            {

                            }
                        }
                    }
                    System.out.println("There is an ant "+shortest+" spaces away from me");
                    //Is there even an ant on the grid?
                    int checkx=12,checky=12;

                    //

                    //no ants found. Moves to farthest edge
                    if(shortest==12)
                    {

                    }
                    System.out.println("There is an "+grid[y][x+shortest].getChar()+" shortest is "+shortest+" and moved is "+moved);
                    if(grid[y][x+shortest].getChar()=='a' && grid[y][x].moved == false && shortest !=12)
                    {
                        System.out.println("The ant is  to my right");
                        int temp;
                        temp = ((Beetle)grid[y][x]).breed;
                        grid[y][x+1] = null;
                        grid[y][x+1] = new Beetle();
                        ((Beetle)grid[y][x+1]).breed = temp;
                        ((Beetle)grid[y][x+1]).moved = true;
                        grid[y][x] = new Base();

                    }
                    /*else if(grid[y][x-shortest].getChar()=='a' && shortest != 12&& moved == false)
                    {
                        System.out.println("The ant is  to my left");
                        int temp;
                        temp = ((Beetle)grid[y][x]).breed;
                        grid[y][x-1] = null;
                        grid[y][x-1] = new Beetle();
                        ((Beetle)grid[y][x-1]).breed = temp;
                        ((Beetle)grid[y-1][x]).moved = true;
                    }
                    else if(grid[y+shortest][x].getChar()=='a' && shortest != 12&& moved == false)
                    {
                        System.out.println("The ant is to my down");
                        int temp;
                        temp = ((Beetle)grid[y][x]).breed;
                        grid[y+1][x] = null;
                        grid[y+1][x] = new Beetle();
                        ((Beetle)grid[y+1][x]).breed = temp;
                        ((Beetle)grid[y-1][x]).moved = true;
                    }
                    else if(grid[y-shortest][x].getChar()=='a' && shortest != 12 && moved == false)
                    {
                        System.out.println("The ant is  to my up");
                        int temp;
                        temp = ((Beetle)grid[y][x]).breed;
                        grid[y-1][x] = null;
                        grid[y-1][x] = new Beetle();
                        ((Beetle)grid[y-1][x]).breed = temp;
                        ((Beetle)grid[y-1][x]).moved = true;
                    }*/

                }

            }
        }

    }

}
