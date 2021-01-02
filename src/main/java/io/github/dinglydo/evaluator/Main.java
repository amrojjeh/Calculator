package io.github.dinglydo.evaluator;

import io.github.dinglydo.evaluator.expressions.Distributor;
import io.github.dinglydo.evaluator.expressions.Polynomial;
import io.github.dinglydo.evaluator.primitive.Term;

public class Main
{
    public static void main(String[] args)
    {
        Term t1 = new Term(0);
        Term t2 = new Term(5);
        Term t3 = new Term(10);
        Term t4 = new Term(5).negate();
        Term t5 = new Term(5, 'x').multiply('x');
        Term t6 = new Term(10, 'x');
        Term t7 = new Term(25, 'y', 'x');
        Term t8 = new Term(3).multiply(new Term(3, 'x')).multiply(new Term(3, 'y', 'z'));

        Polynomial p = new Polynomial(t1, t2, t3, t4, t5, t6, t7, t8);

        System.out.println(p.toString());

        System.out.println("Time to simplify!");

        p = p.simplify();

        System.out.println(p.toString());

        System.out.println("Now let's distribute 2x!");

        p = new Distributor(p, new Term(2, 'x')).simplify();
        System.out.println(p.toString());
    }
}
