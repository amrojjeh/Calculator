package io.github.dinglydo.evaluator;

import io.github.dinglydo.evaluator.expressions.AdditionExpression;

public class Main
{
    public static void main(String[] args)
    {
        AdditionExpression c = new AdditionExpression();
        c.add(new Term(0));
        c.add(new Term(5));
        c.add(new Term(10));
        c.subtract(new Term(5));

        Term t4 = new Term(5, 'x');
        c.add(t4);

        Term t5 = new Term(10, 'x');
        c.add(t5);

        Term t6 = new Term(25, 'y', 'x');
        c.add(t6);

        System.out.println(c.toString());

        System.out.println("Time to simplify!");

        c.evaluate();

        System.out.println(c.toString());

    }
}
