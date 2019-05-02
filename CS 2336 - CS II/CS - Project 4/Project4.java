
//Part of package [ ]. This is: . Requires: .
package blaher;
import java.util.*;
import java.io.*;

public class Project4
{
    public static void main(String[] args)
    {
        BinaryTree leafy = new BinaryTree();

        //testing adding
        leafy.insertNode(5);
        leafy.insertNode(6);
        leafy.insertNode(9);
        leafy.insertNode(1);
        leafy.insertNode(8);

        //testing searching
        Node spot = leafy.locate(leafy.getRoot(), 9);

        //testing removing
        leafy.remove(leafy.getRoot(), 8);

        //testing printing.
        leafy.print(leafy.getRoot());
        System.out.println();

    }

}

class BinaryTree extends Node
{
    //the root of the node.
    private Node root;

    //The constructors.
    BinaryTree()
    {root = null;}
    BinaryTree(Node n)
    {this.root = n;}

    //get and set,
    Node getRoot()
    {return root;}
    void setRoot(Node n)
    {this.root = n;}

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

   //locates a node.
    Node locate (Node n, int find)
    {
        if (n == null)
            return null;
        else
            if (find == n.getVal())
                return n;
            else if (find < n.getVal())
                return locate(n.getLeft(), find);
            else
                return locate(n.getRight(), find);
    }

    //prints out the binary tree
    public void print(Node n)
    {
        //if node null, do nothing.
        if (n == null)
            return;
        //otherwise, print out the value.
        else
        {
            //print left
            print(n.getLeft());

            //print my value
            System.out.print(n.getVal()+", ");

            //prints left and right nodes.
            print(n.getRight());
        }
    }

    //inserts a node into the list.
    public void insertNode(int value)
    {
        this.root = insertNode(this.root, new Node(value));
    }
    //goes with insertNode
    private Node insertNode(Node currentParent, Node newNode)
    {

        if (currentParent == null)
        {
            return newNode;
        }
        else if (newNode.getVal() > currentParent.getVal())
        {
            currentParent.setRight(insertNode(currentParent.getRight(), newNode));
        }
        else if (newNode.getVal() < currentParent.getVal())
        {
            currentParent.setLeft(insertNode(currentParent.getLeft(), newNode));
        }
        return currentParent;
    }

    //part 1 of node removal. Used to help disconnect and lift out.
    public Node lift(Node t, Node remove)
    {
        if (t.getRight() == null)
        {
            remove.setVal(t.getVal());
            return t.getLeft();
        }
        else
        {
            t.setRight(lift(t.getRight(), remove));
            return t;
        }
    }

    //part 2 of node removal. Requires one above.
    public Node remove(Node n, int remove)
    {
        //Empty BinTree
        if (n == null)
            return null;
        //else if we have the matching value to remove in our node.
        else if (remove == n.getVal() )
        {
            //determine null areas
            if (n.getLeft()==null)
                return n.getRight();
            else if (n.getRight()==null)
                return n.getLeft();
            //otherwise start to disconnect the part
            else
            {
                n.setLeft(lift(n.getLeft(), n));
                return n;
            }
        }
        //otherwise, search for value and then try again.
        else
        {
            if (remove < n.getVal() )
                n.setLeft(remove(n.getLeft(), remove));
            else
                n.setRight(remove(n.getRight(), remove));
            return n;
        }
    }


}



class Node
{
    //left point, right pointer, value is holds.
    private Node left;
    private Node right;
    private double value;

    //makes a junk node. negative pi is junk value.
    Node()
    {left = null; right= null; value = (Math.PI*-1);}
    //makes a something node.
    Node(double value)
    {this.value=value;}

    Node getLeft()
    {return left;}
    void setLeft(Node n)
    {left = n;}

    Node getRight()
    {return right;}
    void setRight(Node n)
    {right = n;}

    double getVal()
    {return value;}
    void setVal(double val)
    {value = val;}

    //node inspects itself to determine if it's
    //orphaned, and it has a value, that's not anything.
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
