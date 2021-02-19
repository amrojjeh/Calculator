package io.github.dinglydo.calculator.expressions;

import io.github.dinglydo.calculator.primitive.Term;
import io.github.dinglydo.calculator.visitors.ExpressionVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * A summation of {@code Term}s. It's capable of merging similar terms through {@code simplify}, and is immutable.
 * All simplifications should result in a polynomial.
 */
public class Polynomial implements Expression
{
    public final List<Term> terms;

    /**
     * Constructs a polynomial using a Term array
     * @param termsToAdd Term[]
     */
    public Polynomial(Term... termsToAdd)
    {
        this(Arrays.asList(termsToAdd));
    }

    /**
     * Constructs a polynomial using a Term list
     * @param termsToAdd List<Term>
     */
    public Polynomial(@NotNull List<Term> termsToAdd)
    {
        terms = Collections.unmodifiableList(termsToAdd);
    }

    /**
     * Returns a new polynomial with the added term. Does not modify this instance
     * @param term Term term
     * @return The resulting polynomial. Does not simplify.
     */
    public Polynomial add(Term term)
    {
        LinkedList<Term> list = new LinkedList<>(terms);
        list.add(term);
        return new Polynomial(list);
    }

    /**
     * Returns a new polynomial with the added polynomials. Does not modify this instance.
     * @param other Polynomial polynomial
     * @return The resulting polynomial. Does not simplify.
     */
    public Polynomial add(Polynomial other)
    {
        LinkedList<Term> list = new LinkedList<>(terms);
        list.addAll(other.terms);
        return new Polynomial(list);
    }

    /**
     * Returns a new polynomial with the added but negated term. Does not modify this instance
     * @param term Term term
     * @return The resulting polynomial
     */
    public Polynomial subtract(Term term)
    {
        return add(term.negate());
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Polynomial)
            return equals((Polynomial) other);
        return false;
    }

    public boolean equals(Polynomial other)
    {
        return terms.equals(other.terms);
    }

    /**
     * Returns the polynomial in a string format. Does not follow standard order
     * @return String
     */
    @Override
    public String toString()
    {
        if (terms.size() == 0)
            return Term.ZERO.toString();

        StringBuilder builder = new StringBuilder();
        for (Term term : terms)
        {
            if (term.isPositive()) builder.append("+ ");
            else builder.append("- ");
            builder.append(term.abs().toString()).append(" ");
        }
        return builder.toString();
    }

    @Override
    public void accept(ExpressionVisitor visitor)
    {
        visitor.visit(this);
    }
}
