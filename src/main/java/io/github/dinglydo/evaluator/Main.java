package io.github.dinglydo.evaluator;

public class Main
{
    public static void main(String[] args)
    {
        Calculator c = new Calculator();
        c.add(new Term(0));
        c.add(new Term(5));
        c.add(new Term(10));

        // 5x
        Term t4 = new Term(5);
        t4.multiply(new Variable('x'));
        c.add(t4);

        System.out.println(c.toString());

        System.out.println("Time to simplify!");

        c.evaluate();

        System.out.println(c.toString());

    }
}
