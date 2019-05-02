
//Part of package [bipod]. This is:tentacle . Requires: head, tentacle
package bipod;
import java.io.*;
import java.util.*;

class Node
{   //left point, right pointer, value is holds.
    private Node left;
    private Node right;
    private double coeff;
    private double value;

    //needed selectively for fractions
    private int denomer;

    //Selectively needed for definite integrals
    private int intefrom;
    private int inteto;

    //Default node(with junk), node with known (>=1) denominator, node with definite attached
    Node()
    {left = null; right= null; value = (Math.PI*-1);}
    //makes a something node.
    Node(int denomer, double coeff, double value)
    {this.value=value;this.coeff=coeff;this.denomer=denomer; this.intefrom =-0;}
    Node(int intefrom, int inteto, int denomer, double coeff, double value)
    {this.value=value;this.coeff=coeff;this.denomer=denomer;this.intefrom = intefrom; this.inteto=inteto;}

    //get and set left pointers
    Node getLeft()
    {return left;}
    void setLeft(Node n)
    {left = n;}

    //get and set right pointers
    Node getRight()
    {return right;}
    void setRight(Node n)
    {right = n;}

    //get and set coefficients
    double getCoeff()
    {return coeff;}
    void setCoeff(double coeff)
    {this.coeff = coeff;}

    //get and set the exponent.
    //named value from grandfathered in testing name
    double getVal()
    {return value;}
    void setVal(double val)
    {value = val;}

    //Get and set for denominator
    int getDenomer()
    {return denomer;}
    void setDenomer(int denomer)
    {this.denomer = denomer;}

    //get and set integral start + end points
    int getIntefrom()
    {return intefrom;}
    void setIntefrom(int intefrom)
    {this.intefrom = intefrom;}
    int getInteto()
    {return inteto;}
    void setInteto(int inteto)
    {this.inteto = inteto;}


    //nice debug code to inspect self
    public void inspectSelf()
    {
        if(left == null)
            System.out.println("Left is not pointing to anything.");
        else
            System.out.println("Left is pointing to mem address "+left.getVal());
        if(right == null)
            System.out.println("Right is not pointing to anything.");
        else
            System.out.println("Right is pointing to mem address "+right.getVal());
        //never going to have negative pi. Used as junk value
        if(value == (Math.PI*-1))
            System.out.println("Value isn't anything at the moment.");
        else
            System.out.println("The value in this leaf is "+value);
    }
}
