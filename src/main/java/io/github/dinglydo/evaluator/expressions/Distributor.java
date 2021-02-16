package io.github.dinglydo.evaluator.expressions;

import io.github.dinglydo.evaluator.primitive.Term;

import java.util.stream.Collectors;
import java.util.List;

/**
 * A distributor object can distribute a term to a polynomial
 */
public class Distributor implements Expression
{
    public final Expression expression;
    public final Term term;

    /**
     * Constructs a Distributor object where Term t is distributed to Polynomial p
     * @param p Polynomial p
     * @param t Term t
     */
    public Distributor(Expression p, Term t)
    {
        expression = p;
        term = t;
    }

    /**
     * Simplifies the polynomial and distributes the term, in that order.
     * @return The simplified polynomial
     */
    @Override
    public Polynomial simplify()
    {
        Polynomial simplified = expression.simplify();
        List<Term> terms = simplified.terms.stream().map(t -> t.multiply(term)).collect(Collectors.toList());
        return new Polynomial(terms);
    }
}
