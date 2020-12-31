package io.github.dinglydo.evaluator;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;

public class Term
{
    public Number coefficient;

    // If vars is empty, it's assumed to be x^0
    public LinkedHashSet<Variable> vars;

    public Term(@NotNull Number number)
    {
        coefficient = number;
        vars = new LinkedHashSet<>();
    }

    public Term(double n)
    {
        coefficient = new Number(n);
        vars = new LinkedHashSet<>();
    }

    /**
     * Adds two terms. Assumes they're similar
     * @param term The term to be added
     * @return The added terms
     */
    public Term add(Term term)
    {
        if (coefficient.isZero()) vars = term.vars;
        coefficient.add(term.coefficient);
        return this;
    }

    public Term multiply(Number n)
    {
        coefficient.multiply(n);
        return this;
    }

    public Term multiply(Variable v)
    {
        vars.add(v);
        return this;
    }

    /**
     * Two terms are considered similar if they contain the same variables with the same degrees
     * @param term The other term
     * @return True if the terms are similar, otherwise false
     */
    public boolean isSimilar(Term term)
    {
        return term.vars.equals(vars);
    }

    // TODO: Actually simplify once we support variables
    public Term simplify()
    {
        return this;
    }

    public boolean isZero()
    {
        return coefficient.isZero();
    }

    public boolean isPositive()
    {
        return coefficient.isPositive();
    }

    public boolean isNegative()
    {
        return coefficient.isNegative();
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder(coefficient.toString());
        for (Variable v : vars)
            builder.append(v.toString());
        return builder.toString();
    }
}
