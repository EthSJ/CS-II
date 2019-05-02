
//Part of package [bipod]. This is:tentacle . Requires: head, tentacle .
package bipod;
import java.io.*;
import java.util.*;

class BinaryTree extends Node
{   //The node head/root
    private Node root;

    //The constructors. 1)Default 2)Provide node
    BinaryTree()
    {root = null;}
    BinaryTree(Node n)
    {this.root = n;}

    //get and set for root
    Node getRoot()
    {return root;}
    void setRoot(Node n)
    {this.root = n;}

    //Self insectors for debug reasons
    //Inspects that root node exists. Useful for debug.
    public void inspectSelfRoot()
    {System.out.println("The mem address for root is "+ this.root);}
    //inspects the immediate values around node. Give it root to check around root.
    public void inspectSelfValues(Node n)
    {
        if(n.getLeft() != null)
            System.out.println("Left value is "+n.getLeft().getVal());
        else
            System.out.println("Left value's null");
        if(n.getRight() != null)
            System.out.println("Right value is "+n.getRight().getVal());
        else
            System.out.println("Right value's null.");
        System.out.println("My value is "+n.getVal());
    }

   //Need to find a node? This locates a node.
    Node locate (Node n, double find)
    {
        //want to return this result
        Node result = null;

        //if it cannot be found
        if (n == null)
            return null;
        //if we found it, return it
        if (n.getVal() == find)
            return n;
        //if left isn't null, move there to find it
        if (n.getLeft() != null)
            result = locate(n.getLeft(),find);
        //if right isn't null, move there to find it
        if (result == null)
            result = locate(n.getRight(),find);
        //return the result
        return result;
    }

    //Prints. First does unmodified. Second does the integral solving and printing
    //prints out the binary tree
    public void print(Node n, PrintWriter output)
    {
        //if node null, do nothing.
        if (n == null)
           return;
        //otherwise, print out the value.
        else
        {
            //print left
            print(n.getLeft(), output);

            //print my value
            if(n.getDenomer() != -0)
                output.print("("+(int)n.getCoeff()+"/"+n.getDenomer()+")"+"x^"+(int)n.getVal()+" + ");
            else
                output.print((int)n.getCoeff()+"x^"+(int)n.getVal()+" + ");


            //prints left and right nodes.
            print(n.getRight(), output);
        }
    }

    //used to assist simplifying (Greated Common Denominator)
    public int gcd(int num, int den)
    {
        int s;
        if (num > den)
            s = den;
        else
            s = num;
        for (int i = s; i > 0; i--)
        {
            if ((num % i == 0) && (den % i == 0))
                return i;
        }
        return -1;
    }

    //prints out the binary tree in solved form
    public void printIntegral(Node n, PrintWriter output)
    {
        //if node null, do nothing.
        if (n == null)
            return;
        //otherwise, print out the value.
        else
        {
            //print left
            printIntegral(n.getLeft(), output);

            //replace the placeholder with something meaningful
            if(n.getDenomer() ==-0)
                n.setDenomer((int)n.getVal()+1);
            //otherwise, calculates correct denominator
            else
                n.setDenomer(((int)n.getVal()+1)*n.getDenomer());

            //solves for simplifying
            int gcd = gcd((int)n.getCoeff(), n.getDenomer());
            if (gcd > 1)
            {
                n.setCoeff(n.getCoeff()/gcd);
                n.setDenomer(n.getDenomer()/gcd);
            }
            //solves for numerator being bigger
            else if(n.getCoeff()%n.getDenomer() ==0)
            {
                n.setCoeff(n.getCoeff()/n.getDenomer());
                n.setDenomer(n.getDenomer()/n.getDenomer());
            }
            //print definite
            if(n.getIntefrom() != -0)
            {
                //no one denominator
                if(n.getDenomer()!=1 && n.getDenomer() !=-1)
                    output.print("("+(int)n.getCoeff()+"/"+(int)n.getDenomer()+")"+"x^"+(int)(n.getVal()+1)+" + ");
                //1/1 output
                else if(((n.getVal()+1)==1 && n.getCoeff()==-1) || ((n.getVal()+1)==-1 && n.getCoeff()==1))
                    output.print("-x^"+(int)(n.getVal()+1)+" + ");
                //if denominator's -1
                else if(n.getDenomer()==-1)
                    output.print("-x^"+(int)(n.getVal()+1)+" + ");
                //if denom and coeff are both 1
                else if(n.getDenomer()==1 && n.getCoeff()==1)
                    output.print("x^"+(int)(n.getVal()+1)+" + ");
                //if denom is 1 and co is -1
                else if(n.getDenomer()==1 && n.getCoeff()==-1)
                    output.print("-x^"+(int)(n.getVal()+1)+" + ");
                //when all else fails, default to this.
                else if(n.getDenomer()==1)
                    output.print((int)n.getCoeff()+"x^"+(int)(n.getVal()+1)+" + ");
            }
            //print indefinite
            //denom is not 1/-1
            else if(n.getDenomer()!=1 && n.getDenomer() !=-1)
                output.print("("+(int)n.getCoeff()+"/"+(int)(n.getDenomer())+")"+"x^"+(int)(n.getVal()+1)+" + ");
            //denom + coeff are 1 but mismatched
            else if((n.getDenomer()==1 && n.getCoeff()==-1) || (n.getDenomer()==-1 && n.getCoeff()==1))
                output.print("-x^"+(int)(n.getVal()+1)+" + ");
            //denom and coe are one but not mismatched
            else if(n.getDenomer()==1 && n.getCoeff()==1)
                output.print("x^"+(int)(n.getVal()+1)+" + ");
            //denom is one
            else if(n.getDenomer()==1)
                output.print((int)n.getCoeff()+"x^"+(int)(n.getVal()+1)+" + ");


            //prints right nodes.
            printIntegral(n.getRight(), output);
        }
    }

    //Sums of the top of the integral, and bottom
    //sum of bottom half of integral
    public double sumIntBottom(Node n)
    {
        //left, right, and sum
        double sum, lefts, rights;
        //if it's null, return 0.
        if ( n == null )
        {
            sum = 0;
            return sum;
        }
        else
        {
            //left sum and right sum. recursive
            lefts  = sumIntBottom( n.getLeft() );
            rights = sumIntBottom( n.getRight() );

            //the actual sum
            sum = ((n.getCoeff()/(n.getDenomer()))*Math.pow(n.getIntefrom(), (n.getVal()+1))) + lefts + rights;
            return sum;
        }
    }
    //returns the sum for the integer in top
    public double sumIntTop(Node n)
    {
        //left, right, and sum
        double sum, lefts, rights;
        //if it's null, return 0.
        if ( n == null )
        {
            sum = 0;
            return sum;
        }
        else
        {
            //left sum and right sum. recursive
            lefts  = sumIntTop( n.getLeft() );
            rights = sumIntTop( n.getRight() );

            //the actual sum
            sum = ((n.getCoeff()/(n.getDenomer()))*Math.pow(n.getInteto(), (n.getVal()+1))) + lefts + rights;
            return sum;
        }
    }

    //the insert nodes. Top acess for indef, middle is indef, bottom is used by both to add
    //inserts a node into the list. This is the accessable call.
    public void insertNode(int denomer, double coeff, double value)
    {
        this.root = insertNode(this.root, new Node(denomer, coeff, value));
    }
    //inserts a node into the list. This is the accessable call. This is for a definite node.
    public void insertDefNode(int intefrom, int inteto, int denomer, double coeff, double value)
    {
        this.root = insertNode(this.root, new Node(intefrom, inteto, denomer, coeff, value));
    }
    //goes with insertNode. This part is nonaccessable
    private Node insertNode(Node currentParent, Node newNode)
    {
        //node I'm at is null
        if (currentParent == null)
        {
            return newNode;
        }
        //we need to go right!
        else if (newNode.getVal() < currentParent.getVal())
        {
            currentParent.setRight(insertNode(currentParent.getRight(), newNode));
        }
        //we need to move left!
        else if (newNode.getVal() > currentParent.getVal())
        {
            currentParent.setLeft(insertNode(currentParent.getLeft(), newNode));
        }
        //return where we're at
        return currentParent;
    }

    //the deletes
    //access to delete
    public void delete(int toDelete)
    {
      root = delete(root, toDelete);
    }
    //the part that actually deletes
    private Node delete(Node p, int toDelete)
    {
      //cannot find
      if (p == null)
          System.out.println("Cannot find the number "+toDelete);
      else
          //move left
        if (p.getVal() < toDelete)
            p.setLeft(delete (p.getLeft(), toDelete));
        //move right and then search lefts of the right as needed
        else
            if (p.getVal()  > toDelete)
                p.setRight(delete (p.getRight(), toDelete));
                    else
                    {
                        if (p.getLeft() == null)
                            return p.getRight();
                        else
                            if (p.getRight() == null)
                                return p.getLeft();
                        else
                        {
                            // get data from the rightmost node in the left subtree
                            p.setVal(retrieveData(p.getLeft()));
                            // delete the rightmost node in the left subtree
                            p.setLeft(delete(p.getLeft(), (int)p.getVal()));
                        }
                    }
      return p;
   }
    //when some needs replacing. Helper to delete
    private double retrieveData(Node p)
    {
      while (p.getRight() != null)
          p = p.getRight();

      return p.getVal();
   }

}
