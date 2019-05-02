
//Part of package [bipod]. This is:head . Requires: tentacle, tentacle .
package bipod;
import java.util.*;
import java.io.*;

public class Project4
{
    public static void main(String[] args) throws IOException
    {
        //most variables needed
        int a=-0, b=-0;
        int integralSymbol=0;
        int meaningfulspace = 0;
        double sum = 0;
        String inputed ="";
        String expression = "";
        String integral ="";

        //output for file
        File out = new File("answers.txt");
        PrintWriter output = new PrintWriter(out);

        //Test if file is there.
        File file;
        Scanner input;
        try
        {
            file = new File("integrals.txt");
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
            System.out.println(filePath + "\\integrals.txt");
            System.out.println("Please place integrals.txt there and run program again.");
            System.exit(0);
        }
        //set up binarytree
        BinaryTree leafy = new BinaryTree();

        //loops over each and every line
        while(input.hasNextLine())
        {
            //reset all the variables to work with them again
            a=-0; b=-0;
            integralSymbol=0;
            meaningfulspace = 0;
            sum = 0;
            inputed ="";
            expression = "";
            integral ="";

            //get the next line and ready the tree!
            inputed = input.nextLine();
            leafy.setRoot(null);

            //following group sets up for calculations
            //determines where the integral symbol is then breaks
            for(int i=0;i<inputed.length();i++)
                if(inputed.charAt(i) == '|')
                {
                    integralSymbol = i;
                    break;
                }
            //finds the first meaningful space (Between | and expression)
            for(int i=0;i<inputed.length();i++)
                if(inputed.charAt(i) == ' ' && i >= integralSymbol)
                {
                    meaningfulspace = i;
                    break;
                }
            //fills in expression without spaces and fills in integral points without spaces
            for(int i = 0; i<inputed.length();i++)
                if(i >meaningfulspace && inputed.charAt(i) != ' ')
                    expression += inputed.charAt(i);
                else if(inputed.charAt(i) !=' ')
                    integral += inputed.charAt(i);

            //definite integral parsing to split a and b up
            if(integralSymbol !=0)
            {
                String intfrom="", intto="";
                for(int i=0; i<integralSymbol;i++)
                    intfrom += integral.charAt(i);
                for(int i=integralSymbol+1;i<integral.length();i++)
                    intto += integral.charAt(i);
                a = Integer.parseInt(intfrom);
                b = Integer.parseInt(intto);
            }
            //actual numbers parsing
            String coeffStr="",valStr="", denomerStr="";
            int place=0;
            boolean denomerer = false, negEx=false, expwn = true, noX = false;
            do
            {
                //reset strings
                coeffStr="";valStr=""; denomerStr="";

                //break if D because dx
                if(expression.charAt(place) =='d')
                    break;
                //add negative into coeff and move forward
                if(expression.charAt(place) =='-')
                {
                    coeffStr+='-';
                    place++;
                }
                //skip over plus
                else if(expression.charAt(place)=='+')
                    place++;
                //we have a dennominator! move forward and turn denomerer on
                if(expression.charAt(place)=='(')
                {
                    place++;
                    denomerer=true;
                }
                //coefficient parsing for denominator
                while(expression.charAt(place)!='/' && denomerer==true)
                {
                    coeffStr+=expression.charAt(place);
                    place++;
                }
                //skip denominator division symbol
                if(expression.charAt(place)=='/' && denomerer==true)
                    place++;
                //combine into denominator string
                while(expression.charAt(place)!=')' && denomerer==true)
                {
                    denomerStr+=expression.charAt(place);
                    place++;
                }
                //skip out of fraction. Time to turn denomer off
                if(expression.charAt(place)==')' && denomerer==true)
                {
                    denomerer=false;
                    place++;
                }
                //no denominator, get the coefficient
                while(expression.charAt(place) !='x' && expression.charAt(place)!='+' && expression.charAt(place) !='-' && denomerer==false)
                {
                    coeffStr+=expression.charAt(place);
                    //make sure we're not just a number without an x. if are, set true!
                    if(expression.charAt(place+1)=='d' || expression.charAt(place+1)=='-'|| expression.charAt(place+1)=='+')
                    {
                        place++;
                        noX=true;
                        break;
                    }
                    //otherwise move forward by one.
                    else
                        place++;
                }
                //if we've got on x get an exponent value of 0.
                if(noX==true && denomerer==false)
                {
                    valStr+='0';
                }
                //we're at an x, mvoe forward
                if(expression.charAt(place) =='x' && noX==false && denomerer==false)
                {
                    place++;
                    //we're at a ^. move forward
                    if(expression.charAt(place)=='^')
                    {
                        place++;
                        //grab negative exponent and the rest of the exponent
                        if(expression.charAt(place)=='-')
                        {
                            valStr+='-';
                            place++;
                            while(expression.charAt(place)!='+' && expression.charAt(place)!='-')
                            {
                                valStr+=expression.charAt(place);
                                place++;
                            }
                        }
                        //nothing tirkcy there, grab the exponent
                        else if(expression.charAt(place) !='+' && expression.charAt(place)!='-')
                        {
                            valStr+=expression.charAt(place);
                            place++;
                        }
                    }
                }

                //default a null exponent
                if(valStr=="")
                    valStr="1";
                if(valStr=="-")
                    valStr="-1";

                //default a null coefficient
                if(coeffStr=="")
                    coeffStr="1";
                if(coeffStr.charAt(0)=='-' && coeffStr.length() < 1)
                    coeffStr="-1";

                //combine a node if there's a duplicate
                Node spot = leafy.locate(leafy.getRoot(), Integer.parseInt(valStr));
                if(spot != null)
                {
                    spot.setCoeff(spot.getCoeff()+Integer.parseInt(coeffStr));
                }

                //make node with indefinite
                if(a ==-0 && b==-0)
                {
                    //we have a denominator
                    if(denomerStr != "")
                    {
                        leafy.insertNode(Integer.parseInt(denomerStr), Integer.parseInt(coeffStr), Integer.parseInt(valStr));
                    }
                    //we don't have a denominator
                    else
                    {
                        leafy.insertNode(-0, Integer.parseInt(coeffStr), Integer.parseInt(valStr));
                    }
                }
                //definite integrals
                else
                {
                    //we have a denominator
                    if(denomerStr != "")
                    {
                        leafy.insertDefNode(a, b,Integer.parseInt(denomerStr), Integer.parseInt(coeffStr), Integer.parseInt(valStr));
                    }
                    //we don't have a denominator
                    else
                    {
                        leafy.insertDefNode(a, b, -0, Integer.parseInt(coeffStr), Integer.parseInt(valStr));
                    }
                }

                //delete out the term of the expression we just solved and start at beginning again.
                String copy = expression;
                expression = copy.substring(place, copy.length());
                place=0;
                //reset flags. Some redundancy to prevent errors
                noX=false;
                denomerer=false;



            }while(place < expression.length());

            //checks for a indefinite. adds a plus C to the end.
            if(leafy.getRoot().getIntefrom() == -0)
            {
                leafy.printIntegral(leafy.getRoot(), output);
                output.println("C");
            }
            //otherwise is definite.
            else
            {

                //prints out integral, the sum, and then caps a 0 on the end
                //0 on end is to fix a problem/quirk of printing nodes. It is NOT! a node.
                leafy.printIntegral(leafy.getRoot(), output);
                sum =leafy.sumIntTop(leafy.getRoot()) - leafy.sumIntBottom(leafy.getRoot());
                output.print("0, ("+a+"|"+b+") ");
                output.print(" = ");
                output.printf("%.3f", sum);
                output.println();
            }
        }
        //close up input
        output.close();
        input.close();
    }
}
