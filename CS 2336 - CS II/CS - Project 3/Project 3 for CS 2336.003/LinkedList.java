
//part of package tripod. This is a: tentacle. Required are: head, tentacle.
package bipod;
import java.io.*;
import bipod.Node;

public class LinkedList
{
    //head of linked list
    private Node head;

    //default and overloaded
    LinkedList()
    {head = null;}
    LinkedList(Node h)
    {this.head = h;}

    //Get your mind out of the gutter. Just returns start of linked list.
    Node getHead()
    {return head;}

    //inspects the whole list out outputs to console. Recursive.
    public void inspectList(Node node, PrintWriter output)
    {
        //move to next node. output this one.
        if(node.getNext() != null)
        {
            node.inspectNode(node, output);
            output.println();
            inspectList(node.getNext(), output);
        }
        //grabs last node in list
        else
        {
            node.inspectNode(node, output);
            output.println();
        }
    }

    //adds and sorts a node
    void addNode(String name, String stats) throws NullPointerException
    {
        //boolean to determine duplicates
        boolean duplicate = false;
        //used to sort through until a match is or isn't found
        Node temp = head;
        try
        {
            if(temp != null)
            {
                while(temp !=null)
                {
                    //name's equal, so we're adding to it.
                    if(name.equals(temp.getName()))
                    {
                        temp.addToNode(temp, stats);
                        duplicate = true;
                        break;
                    }
                    //move to next
                    else if(!name.equals(temp.getName()))
                    {
                        temp =temp.getNext();
                    }
                }
            }
            else
                throw new NullPointerException();
        }
        //doesn't really need to do or output anything
        catch(NullPointerException ex)
        {/*I will always catch one at minimum since head = null at first.*/}

        //If we didn't find a duplicate
        if(duplicate == false)
        {
            Node i = new Node(name, stats);

            //If head is null, now we're head
            if (head==null)
            head = i;
            //else if the first letters alphabetically first, we're now in front
            else if (i.getName().charAt(0) < head.getName().charAt(0))
            {
                i.setNext(head);
                head = i;
            }
            //else sort alphabetically until end
            else
            {
                Node p1 = head;
                while (p1.getNext() != null)
                {
                    if ((p1.getNext().getName().charAt(0)) > (i.getName().charAt(0)))
                    {
                        i.setNext(p1.getNext());
                        p1.setNext(i);
                        return;
                    }
                    p1 = p1.getNext();
                }
                p1.setNext(i);
            }
        }
    }

    //sorts the node based on a character sorter
    public void sort(char sorter)
    {
        //sets up temporary holders
	Node i = head;
	String nameHolder;

        //Find how long the list is and gets ready for that
	int counter = 0;
	while (i !=null)
	{
		i = i.getNext();
		counter++;
	}
        //sets i back to head to begin processing
	i = head;
        //sorts for stat [insert stat here]
        if(sorter =='a')
        {
            //loops through and moves up to top
            for (int j=0; j<counter; j++)
            {
                while (i.getNext() != null)
                {
                    //stats need to swap, so hold on and swap them + name.
                    if (i.getBA() < i.getNext().getBA())
                    {
                        double holder = i.getBA();
                        i.setBA(i.getNext().getBA());
			i.getNext().setBA(holder);

                        holder = i.getH();
                        i.setH(i.getNext().getH());
			i.getNext().setH((int)holder);

                        holder = i.getOBP();
                        i.setOBP(i.getNext().getOBP());
			i.getNext().setOBP(holder);

                        holder = i.getK();
                        i.setK(i.getNext().getK());
			i.getNext().setK((int)holder);

                        holder = i.getBB();
                        i.setBB(i.getNext().getBB());
			i.getNext().setBB((int)holder);

                        holder = i.getHBP();
                        i.setHBP(i.getNext().getHBP());
			i.getNext().setHBP((int)holder);

                        holder = i.getS();
                        i.setS(i.getNext().getS());
			i.getNext().setS((int)holder);

                         holder = i.getAB();
                        i.setAB(i.getNext().getAB());
			i.getNext().setAB((int)holder);

			nameHolder = i.getName();
			i.setName(i.getNext().getName());
			i.getNext().setName(nameHolder);
                    }
                    i = i.getNext();
                }
                i = head;
            }
        }
        if(sorter =='b')
        {
            //loops through and moves up to top
            for (int j=0; j<counter; j++)
            {
                while (i.getNext() != null)
                {
                    //move all stats over
                    if (i.getOBP() < i.getNext().getOBP())
                    {
                        double holder = i.getBA();
                        i.setBA(i.getNext().getBA());
			i.getNext().setBA(holder);

                        holder = i.getH();
                        i.setH(i.getNext().getH());
			i.getNext().setH((int)holder);

                        holder = i.getOBP();
                        i.setOBP(i.getNext().getOBP());
			i.getNext().setOBP(holder);

                        holder = i.getK();
                        i.setK(i.getNext().getK());
			i.getNext().setK((int)holder);

                        holder = i.getBB();
                        i.setBB(i.getNext().getBB());
			i.getNext().setBB((int)holder);

                        holder = i.getHBP();
                        i.setHBP(i.getNext().getHBP());
			i.getNext().setHBP((int)holder);

                        holder = i.getS();
                        i.setS(i.getNext().getS());
			i.getNext().setS((int)holder);

                         holder = i.getAB();
                        i.setAB(i.getNext().getAB());
			i.getNext().setAB((int)holder);

			nameHolder = i.getName();
			i.setName(i.getNext().getName());
			i.getNext().setName(nameHolder);
                    }
                    i = i.getNext();
                }
                i = head;
            }
        }
        if(sorter =='c')
        {
            //loops through and moves up to top
            for (int j=0; j<counter; j++)
            {
                while (i.getNext() != null)
                {
                    //move all stats over
                    if (i.getH() < i.getNext().getH())
                    {
                        double holder = i.getBA();
                        i.setBA(i.getNext().getBA());
			i.getNext().setBA(holder);

                        holder = i.getH();
                        i.setH(i.getNext().getH());
			i.getNext().setH((int)holder);

                        holder = i.getOBP();
                        i.setOBP(i.getNext().getOBP());
			i.getNext().setOBP(holder);

                        holder = i.getK();
                        i.setK(i.getNext().getK());
			i.getNext().setK((int)holder);

                        holder = i.getBB();
                        i.setBB(i.getNext().getBB());
			i.getNext().setBB((int)holder);

                        holder = i.getHBP();
                        i.setHBP(i.getNext().getHBP());
			i.getNext().setHBP((int)holder);

                        holder = i.getS();
                        i.setS(i.getNext().getS());
			i.getNext().setS((int)holder);

                         holder = i.getAB();
                        i.setAB(i.getNext().getAB());
			i.getNext().setAB((int)holder);

			nameHolder = i.getName();
			i.setName(i.getNext().getName());
			i.getNext().setName(nameHolder);
                    }
                    i = i.getNext();
                }
                i = head;
            }
        }
        if(sorter =='d')
        {
            //loops through and moves up to top
            for (int j=0; j<counter; j++)
            {
                while (i.getNext() != null)
                {
                    //move all stats over
                    if (i.getBB() < i.getNext().getBB())
                    {
                        double holder = i.getBA();
                        i.setBA(i.getNext().getBA());
			i.getNext().setBA(holder);

                        holder = i.getH();
                        i.setH(i.getNext().getH());
			i.getNext().setH((int)holder);

                        holder = i.getOBP();
                        i.setOBP(i.getNext().getOBP());
			i.getNext().setOBP(holder);

                        holder = i.getK();
                        i.setK(i.getNext().getK());
			i.getNext().setK((int)holder);

                        holder = i.getBB();
                        i.setBB(i.getNext().getBB());
			i.getNext().setBB((int)holder);

                        holder = i.getHBP();
                        i.setHBP(i.getNext().getHBP());
			i.getNext().setHBP((int)holder);

                        holder = i.getS();
                        i.setS(i.getNext().getS());
			i.getNext().setS((int)holder);

                         holder = i.getAB();
                        i.setAB(i.getNext().getAB());
			i.getNext().setAB((int)holder);

			nameHolder = i.getName();
			i.setName(i.getNext().getName());
			i.getNext().setName(nameHolder);
                    }
                    i = i.getNext();
                }
                i = head;
            }
        }
        if(sorter =='e')
        {
            //loops through and moves up to top
            for (int j=0; j<counter; j++)
            {
                while (i.getNext() != null)
                {
                    //move all stats over
                    if (i.getK() < i.getNext().getK())
                    {
                        double holder = i.getBA();
                        i.setBA(i.getNext().getBA());
			i.getNext().setBA(holder);

                        holder = i.getH();
                        i.setH(i.getNext().getH());
			i.getNext().setH((int)holder);

                        holder = i.getOBP();
                        i.setOBP(i.getNext().getOBP());
			i.getNext().setOBP(holder);

                        holder = i.getK();
                        i.setK(i.getNext().getK());
			i.getNext().setK((int)holder);

                        holder = i.getBB();
                        i.setBB(i.getNext().getBB());
			i.getNext().setBB((int)holder);

                        holder = i.getHBP();
                        i.setHBP(i.getNext().getHBP());
			i.getNext().setHBP((int)holder);

                        holder = i.getS();
                        i.setS(i.getNext().getS());
			i.getNext().setS((int)holder);

                         holder = i.getAB();
                        i.setAB(i.getNext().getAB());
			i.getNext().setAB((int)holder);

			nameHolder = i.getName();
			i.setName(i.getNext().getName());
			i.getNext().setName(nameHolder);
                    }
                    i = i.getNext();
                }
                i = head;
            }
        }
        if(sorter =='f')
        {
            //loops through and moves up to top
            for (int j=0; j<counter; j++)
            {
                while (i.getNext() != null)
                {
                    //move all stats over
                    if (i.getHBP() < i.getNext().getHBP())
                    {
                        double holder = i.getBA();
                        i.setBA(i.getNext().getBA());
			i.getNext().setBA(holder);

                        holder = i.getH();
                        i.setH(i.getNext().getH());
			i.getNext().setH((int)holder);

                        holder = i.getOBP();
                        i.setOBP(i.getNext().getOBP());
			i.getNext().setOBP(holder);

                        holder = i.getK();
                        i.setK(i.getNext().getK());
			i.getNext().setK((int)holder);

                        holder = i.getBB();
                        i.setBB(i.getNext().getBB());
			i.getNext().setBB((int)holder);

                        holder = i.getHBP();
                        i.setHBP(i.getNext().getHBP());
			i.getNext().setHBP((int)holder);

                        holder = i.getS();
                        i.setS(i.getNext().getS());
			i.getNext().setS((int)holder);

                         holder = i.getAB();
                        i.setAB(i.getNext().getAB());
			i.getNext().setAB((int)holder);

			nameHolder = i.getName();
			i.setName(i.getNext().getName());
			i.getNext().setName(nameHolder);
                    }
                    i = i.getNext();
                }
                i = head;
            }
        }
    }

    //outputs the winner of each catagory
    int win(int count, int place, char sorter, PrintWriter output) throws NullPointerException
    {
        //grabs the sorter for personalized output
        if(sorter =='a')
        {
            Node i = head;
            for(int h=0; h<count; h++)
            {
                i = i.getNext();
            }
            //batting average leader
            try
            {
                //try to see if there're at least three and tied.
                if(i.getBA() == i.getNext().getBA() && i.getNext().getBA() == i.getNext().getNext().getBA())
                {
                    output.print(i.getBA()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print("place tie: "+i.getName()+", "+i.getNext().getName()+", and "+i.getNext().getNext().getName());
                    output.println();
                    return count+3;
                }
                else
                    throw new NullPointerException();
            }
            catch(NullPointerException ex)
            {
                try
                {
                    //try to see if there are at least two and tied.
                    if(i.getBA() == i.getNext().getBA())
                    {
                        output.print(i.getBA()+"\t");
                        output.print(place);
                        if(place ==1)
                            output.print("st ");
                        else if(place==2)
                            output.print("nd ");
                        else
                            output.print("rd ");
                        output.print("place tie: " + i.getName() +" and "+i.getNext().getName());
                        output.println();
                        return count+2;
                    }
                    else
                        throw new NullPointerException();
                }
                catch(NullPointerException ej)
                {
                    //just one person there and/or no tie.
                    output.print(i.getBA()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");;
                    output.print( ": "+i.getName()+"." );
                    output.println();
                    return count+1;
                }
            }
        }
        if(sorter =='b')
        {
            Node i = head;
            for(int h=0; h<count; h++)
            {
                i = i.getNext();
            }
            //leader for OBP
            try
            {
                //try to get 3 way tie for third if it's possible
                if(i.getOBP() == i.getNext().getOBP() && i.getNext().getOBP() == i.getNext().getNext().getOBP())
                {
                    output.print(i.getOBP()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print("place tie: "+i.getName()+", "+i.getNext().getName()+" and "+i.getNext().getNext().getName());
                    output.println();
                    return count+3;
                }
                else
                    throw new NullPointerException();
            }
            catch(NullPointerException ex)
            {
                //try to get two way tie
                try
                {
                    if(i.getOBP() == i.getNext().getOBP())
                    {
                    output.print(i.getOBP()+"\t");
                    output.print(place);
                        if(place ==1)
                            output.print("st ");
                        else if(place==2)
                            output.print("nd ");
                        else
                            output.print("rd ");
                        output.print("place tie: " + i.getName() +" and "+i.getNext().getName());
                        output.println();
                        return count+2;
                    }
                    else
                        throw new NullPointerException();
                }
                catch(NullPointerException ej)
                {
                    //only one person and/or a leader
                    output.print(i.getOBP()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print( ": "+i.getName()+"." );
                    output.println();
                    return count+1;
                }
            }
        }
        if(sorter =='c')
        {
            Node i = head;
            for(int h=0; h<count; h++)
            {
                i = i.getNext();
            }
            //get the leader for H
            try
            {
                //try to get 3 way tie for third, if it exists
                if(i.getH() == i.getNext().getH() && i.getNext().getH() == i.getNext().getNext().getH())
                {
                    output.print(i.getH()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print("place tie: "+i.getName()+", "+i.getNext().getName()+" and "+i.getNext().getNext().getName());
                    output.println();
                    return count+3;
                }
                else
                    throw new NullPointerException();
            }
            catch(NullPointerException ex)
            {
                //try to get two way tie, if it exists
                try
                {
                    if(i.getH() == i.getNext().getH())
                    {
                        output.print(i.getH()+"\t");
                        output.print(place);
                        if(place ==1)
                            output.print("st ");
                        else if(place==2)
                            output.print("nd ");
                        else
                            output.print("rd ");
                        output.print("place tie: " + i.getName() +" and "+i.getNext().getName());
                        output.println();
                        return count+2;
                    }
                    else
                        throw new NullPointerException();
                }
                catch(NullPointerException ej)
                {
                    //there one person and/or no tie
                    output.print(i.getH()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print( ": "+i.getName()+"." );
                    output.println();
                    return count+1;
                }
            }
        }
        if(sorter =='d')
        {
            Node i = head;
            for(int h=0; h<count; h++)
            {
                i = i.getNext();
            }
            // try and get 3 way tie if it exists
            try
            {
                if(i.getBB() == i.getNext().getBB() && i.getNext().getBB() == i.getNext().getNext().getBB())
                {
                    output.print(i.getBB()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print("place tie: "+i.getName()+", "+i.getNext().getName()+" and "+i.getNext().getNext().getName());
                    output.println();
                    return count+3;
                }
                else
                    throw new NullPointerException();
            }
            catch(NullPointerException ex)
            {
                //try to get 2 way tie, if it exists
                try
                {
                    if(i.getBB() == i.getNext().getBB())
                    {
                        output.print(i.getBB()+"\t");
                        output.print(place);
                        if(place ==1)
                            output.print("st ");
                        else if(place==2)
                            output.print("nd ");
                        else
                            output.print("rd ");
                        output.print("place tie: " + i.getName() +" and "+i.getNext().getName());
                        output.println();
                        return count+2;
                    }
                    else
                        throw new NullPointerException();
                }
                catch(NullPointerException ej)
                {
                    //there's one person and/or no tie
                    output.print(i.getBB()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print( ": "+i.getName()+". " );
                    output.println();
                    return count+1;
                }
            }
        }
        if(sorter =='e')
        {
            Node i = head;
            for(int h=0; h<count; h++)
            {
                i = i.getNext();
            }
            //try to get 3 way tie for K
            try
            {
                if(i.getK() == i.getNext().getK() && i.getNext().getK() == i.getNext().getNext().getK())
                {
                    output.print(i.getK()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print("place tie: "+i.getName()+", "+i.getNext().getName()+" and "+i.getNext().getNext().getName());
                    output.println();
                    return count+3;
                }
                else
                    throw new NullPointerException();
            }
            catch(NullPointerException ex)
            {
                //try to get 2 way tie
                try
                {
                    if(i.getK() == i.getNext().getK())
                    {
                        output.print(i.getK()+"\t");
                        output.print(place);
                        if(place ==1)
                            output.print("st ");
                        else if(place==2)
                            output.print("nd ");
                        else
                            output.print("rd ");
                        output.print("place tie: " + i.getName() +" and "+i.getNext().getName());
                        output.println();
                        return count+2;
                    }
                    else
                        throw new NullPointerException();
                }
                catch(NullPointerException ej)
                {
                    //there's only one person and/or no tie
                    output.print(i.getK()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print( ": "+i.getName()+". " );
                    output.println();
                    return count+1;
                }
            }
        }
        if(sorter =='f')
        {
            Node i = head;
            for(int h=0; h<count; h++)
            {
                i = i.getNext();
            }
            //get 3 way tie for HBP if it exists
            try
            {
                if(i.getHBP() == i.getNext().getHBP() && i.getNext().getHBP() == i.getNext().getNext().getHBP())
                {
                    output.print(i.getHBP()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print("place tie: "+i.getName()+", "+i.getNext().getName()+" and "+i.getNext().getNext().getName());
                    output.println();
                    return count+3;
                }
                else
                    throw new NullPointerException();
            }
            catch(NullPointerException ex)
            {
                //get 2 way tie if it exists
                try
                {
                    if(i.getHBP() == i.getNext().getHBP())
                    {
                        output.print(i.getHBP()+"\t");
                        output.print(place);
                        if(place ==1)
                            output.print("st ");
                        else if(place==2)
                            output.print("nd ");
                        else
                            output.print("rd ");
                        output.print("place tie: " + i.getName() +" and "+i.getNext().getName());
                        output.println();
                        return count+2;
                    }
                    else
                        throw new NullPointerException();
                }
                catch(NullPointerException ej)
                {
                    //there's one person and/or no tie
                    output.print(i.getHBP()+"\t");
                    output.print(place);
                    if(place ==1)
                        output.print("st ");
                    else if(place==2)
                        output.print("nd ");
                    else
                        output.print("rd ");
                    output.print( ": "+i.getName()+". " );
                    output.println();
                    return count+1;
                }
            }
        }
        //return my place in so I don't have to redo from a tied spot
        //places include 3 down (3 way down), 2 down (two way tier), and 1 down(no tie)
        return count;
    }
}
