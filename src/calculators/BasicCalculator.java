package calculators;

import utils.MyStack;

import java.util.Scanner;

public class BasicCalculator
{
    public int solve(String input)
    {
        MyStack stack = new MyStack();
        String S = input;
        String P = infixToPostfix(S, stack);
        int R = solvePostfix(P, stack);

        //System.out.println("The postfix expression for the input infix is: " + P);
        //System.out.println("The final result after evaluating the postfix is: " + R);
        return R;
    }

    static String readInfix()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter expression: ");
        return sc.nextLine();
    }

    static int solvePostfix(String post, MyStack stack)
    {
        int i = 0, result, left, right;

        while(i < post.length())
        {
            result = 0;
            char c = post.charAt(i);
            if(c >= '0' && c <= '9')
            {
                stack.push(c);
            }
            else
            {
                right = Integer.parseInt(stack.pop().toString());
                left = Integer.parseInt(stack.pop().toString());

                if(c == '+') result = left + right;
                else if(c == '-') result = left - right;
                else if(c == '*') result = left * right;
                else if(c == '/') result = left / right;
                else result = (int) Math.pow(left, right);

                stack.push(result);
            }
            i++;
        }
        if(stack.size() == 1) result = Integer.parseInt(stack.pop().toString());
        else throw new IllegalArgumentException("Postfix has error");
        return result;
    }
    static String infixToPostfix(String infix, MyStack stack)
    {
        String postFix = "";
        char c;
        for(int i = 0; i < infix.length(); i++)
        {
            c = infix.charAt(i);
            if(c >= '0' && c <= '9')
            {
                postFix += c;
            }

            else if(c == '(')
            {
                stack.push(Character.toString(c));
            }

            else if(c == ')')
            {
                while(stack.peek().equals("(") == false)
                {
                    postFix += stack.pop();
                }

                stack.pop();
            }
            else if(c == '+' || c == '-' || c == '*' || c == '/' || c == '^')
            {
                while(stack.isEmpty() == false && precedenceOnStack(stack.peek().toString().charAt(0)) > precedenceCurItem(c))
                {
                    postFix += stack.pop();
                }
                stack.push(c);
            }
            else { } //throw new IllegalArgumentException("Invalid character within infix expression");
        }

        while(!stack.isEmpty())
        {
            postFix += stack.pop();
        }

        return postFix;
    }

    static int precedenceOnStack(char c)
    {
        if(c == '(') return 0;
        else if(c == '^') return 5;
        else if(c == '*' || c == '/' || c == '%') return 4;
        else if(c == '+' || c == '-') return 2;
        else throw new IllegalArgumentException("In precedenceOnStack char '" + c + "' invalid");
    }

    static int precedenceCurItem(char c)
    {
        if(c == '(') return 100;
        if(c == ')') return 0;
        else if(c == '^') return 6;
        else if(c == '*' || c == '/' || c == '%') return 3;
        else if(c == '+' || c == '-') return 1;
        else throw new IllegalArgumentException("In precedenceCurItem char '" + c + "' invalid");
    }
}
