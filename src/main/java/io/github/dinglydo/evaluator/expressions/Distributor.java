package io.github.dinglydo.evaluator.expressions;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A series of expressions multiplying each other
 */
public class Distributor implements Expression
{
    public final List<Expression> expressions;

    /**
     * Constructs a Distributor object that multiplies a sequence of {@code Expression}s
     * @param expressionsToMultiply Expressions to be multiplied
     */
    public Distributor(Expression... expressionsToMultiply)
    {
        this(Arrays.asList(expressionsToMultiply));
    }

    /**
     * Constructs a Distributor object that multiplies a sequence of {@code Expression}s
     * @param expressionsToMultiply Expressions to be multiplied
     */
    public Distributor(@NotNull List<Expression> expressionsToMultiply)
    {
        expressions = Collections.unmodifiableList(expressionsToMultiply);
    }

    /**
     * Multiplies the distribution by another Term. {@code 2(2 + 2).multiply(2) -> 4(2 +2 )}
     * @param t the other Term
     * @return the resulting distribution
     */
    public Distributor multiply(Expression e)
    {
        LinkedList<Expression> list = new LinkedList<>(expressions);
        list.add(e);
        return new Distributor(list);
    }

    /**
     * Simplifies the polynomial and distributes the term, in that order.
     * @return The simplified polynomial
     */
    @Override
    public Polynomial simplify()
    {
        Polynomial result = null;

        for (Expression e : expressions)
            if (result == null)
                result = e.simplify();
            else
                result = distributePolynomial(result, e.simplify());

        return result;
    }

    /**
     * Assumes both are simplified
     * @param p1
     * @param p2
     * @return Returns the distribution
     */
    private Polynomial distributePolynomial(Polynomial p1, Polynomial p2)
    {
        Polynomial result = new Polynomial();
        for (Term f1 : p2.terms)
            for (Term f2 : p1.terms)
                result = result.add(f2.multiply(f1));
        return result;
    }
}
