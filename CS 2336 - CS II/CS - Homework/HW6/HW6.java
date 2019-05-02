

//#25.3 - pg. 960 (iterative inorder traversal using a stack)
/*
(Implement inorder traversal without using recursion) Implement the inorder
method in BST using a stack instead of recursion. Write a test program that
prompts the user to enter 10 integers, stores them in a BST, and invokes the
inorder method to display the elements.


*/
import java.util.*;
public class HW6
{
    public static void main(String[] args)
    {
        int input =0;
        BinaryTree leafy = new BinaryTree();
        Scanner s = new Scanner(System.in);
        for(int i=0; i<10;i++)
        {
            System.out.print("input integer number "+(i+1)+": ");
            input = s.nextInt();
            leafy.insertNode(input);
        }
        leafy.inorder();

    }
}
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

    public void inorder() {
        if (root == null) {
            return;
        }

        //keep the nodes in the path that are waiting to be visited
        Stack<Node> stack = new Stack<Node>();
        Node node = root;

        //first node to be visited will be the left one
        while (node != null) {
            stack.push(node);
            node = node.getRight();
        }

        // traverse the tree
        while (stack.size() > 0)
        {

            // visit the top node
            node = stack.pop();
            System.out.print(node.getVal() + " ");
            if (node.getLeft() != null)
            {
                node = node.getLeft();

                // the next node to be visited is the leftmost
                while (node != null)
                {
                    stack.push(node);
                    node = node.getRight();
                }
            }
        }
    }

    //inserts a node into the list. This is the accessable call. This is for a definite node.
    public void insertNode(int value)
    {
        this.root = insertNode(this.root, new Node(value));
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

}

class Node
{   //left point, right pointer, value is holds.
    private Node left;
    private Node right;
    private int value;


    //Default node(with junk), node with known (>=1) denominator, node with definite attached
    Node()
    {left = null; right= null; value = 0;}
    //makes a something node.
    Node(int value)
    {this.value = value; left = null; right = null;}

    //get and set left pointers
    Node getLeft()
    {return left;}
    void setLeft(Node n)
    {left = n;}
    Node getRight()
    {return right;}
    void setRight(Node n)
    {right = n;}

    int getVal()
    {return value;}
    void setVal(int val)
    {this.value = val;}

}
