
import java.util.*;

public class HW5
{
        //so I can actually say Add, sub, mul, div, pow and not get confused.
	public enum Operation {ADD, SUB, MUL, DIV, POW};

        //links above to an acutal part
	public static HashMap<String, Operation> operators = new HashMap<String, Operation>();
	static
        {
            operators.put("+", Operation.ADD);
            operators.put("-", Operation.SUB);
            operators.put("*", Operation.MUL);
            operators.put("/", Operation.DIV);
            operators.put("^", Operation.POW);
	}

        //Stack. Not sure what it is, kinda working ahead to get this to work.
	private Stack<Integer> stack;

        //makes the stack
	public HW5()
        {
		stack = new Stack<Integer>();
	}

    //evaluates
    public Integer evaluate(String postfixExpression)
    {
	// tokenize the string first
	LinkedList<Object> tokens = tokenize(postfixExpression);
	// process each item in the LinkedList
	for(Object item : tokens)
        {
            // separate the current item into two categories
            if (item instanceof Operation)
            {
                // for operators, pop the top two operands from the stack,
		// calculate the result of the single operation, and push to top
		stack.push(operateSingle(stack.pop(), stack.pop(), (Operation)item) );
            }
            else
            {
                //just operands, push to stack
		stack.push((Integer)item);
            }
	}
	return stack.pop();
    }

    //figure out what the token (char or /*-+) is we're dealing with
    private LinkedList<Object> tokenize(String input)
    {
	Scanner tokens = new Scanner(input);
        //the linked list that's required. I think. Is this what was asked?
	LinkedList<Object> list = new LinkedList<Object>();
	String token;
	while (tokens.hasNext())
        {
            token = tokens.next();
            if (operators.containsKey(token))
            {
                list.add(operators.get(token));
            }
            else
            {
                list.add(Integer.valueOf(token));
            }
	}
	return list;
    }

    //calculate the correct
    private Integer operateSingle(Integer op2, Integer op1, Operation operation)
    {
	switch(operation)
        {
            case ADD:
                return op1+op2;
            case SUB:
                return op1-op2;
            case MUL:
                return op1*op2;
            case DIV:
                return op1/op2;
            case POW:
		return (int)(Math.pow((double)op1, (double)op2));
	}
	return null;
    }

    //test if the whole thing works.
    public static void main(String[] args)
    {
	String line = "";
	Scanner s = new Scanner(System.in);
	HW5 postfix = new HW5();
        System.out.println("Enter a postfix expression or E to exit");
        line = s.nextLine();
        System.out.println("Result is: " + postfix.evaluate(line));
    }
}
