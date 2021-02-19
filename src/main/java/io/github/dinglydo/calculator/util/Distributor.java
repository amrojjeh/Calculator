package io.github.dinglydo.calculator.util;

import io.github.dinglydo.calculator.expressions.Polynomial;
import io.github.dinglydo.calculator.primitive.Term;

public class Distributor
{
    public static Polynomial distributePolynomials(Polynomial p1, Polynomial p2)
    {
        Polynomial result = new Polynomial();
        for (Term f1 : p2.terms)
            for (Term f2 : p1.terms)
                result = result.add(f2.multiply(f1));
        return result;
    }
}
