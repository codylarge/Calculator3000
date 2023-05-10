package facades;

import calculators.BasicCalculator;

import java.util.Scanner;

public class BasicCalcManager
{
    BasicCalculator bc = new BasicCalculator();
    public void solveBasicExpression()
    {
            Scanner sc = new Scanner(System.in);
            System.out.print("Please enter expression: ");
            String expression = sc.nextLine();
            int result = bc.solve(expression);
            System.out.println(expression + " = " + result);
    }
}
