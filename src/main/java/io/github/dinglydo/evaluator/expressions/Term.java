package io.github.dinglydo.evaluator.expressions;

import io.github.dinglydo.evaluator.primitive.Number;
import io.github.dinglydo.evaluator.primitive.Variable;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Term implements Expression
{
    public final Number coefficient;

    // If vars is empty, it's assumed to be x^0
    public final Set<Variable> vars;

    public Term(@NotNull Number number, @NotNull Set<Variable> variables)
    {
        coefficient = number;
        vars = Collections.unmodifiableSet(variables);
    }

    public Term(double n)
    {
        this(n, "");
    }

    public Term(double n, String variables)
    {
        coefficient = new Number(n);
        Variable[] temp = new Variable[variables.length()];
        for (int i = 0; i < temp.length; ++i)
            temp[i] = new Variable(variables.charAt(i));
        this.vars = Set.of(temp);
    }

    /**
     * Adds two terms. Assumes they're similar
     * @param term The term to be added
     * @return The added terms
     */
    public Term add(Term term)
    {
        if (coefficient.isZero()) return term;
        if (!isSimilar(term))
            throw new IllegalArgumentException(String.format("Could not add terms %s + %s. They're dissimilar.", this.toString(), term.toString()));
        return new Term(coefficient.add(term.coefficient), vars);
    }

    public Term multiply(double other) { return multiply(new Number(other)); }

    public Term multiply(Number other) { return new Term(coefficient.multiply(other), vars); }

    public Term multiply(char other) { return multiply(new Variable(other)); }

    // TODO: Check if a degree becomes 0, as the variable should be removed
    public Term multiply(Variable other)
    {
        Set<Variable> variables = new LinkedHashSet<>();
        boolean alreadyExists = false;
        for (Variable v : vars)
        {
            if (v.letter == other.letter)
            {
                variables.add(v.changePowerBy(other.degree));
                alreadyExists = true;
            }
            else
                variables.add(v);
        }
        if (!alreadyExists)
            variables.add(other);

        return new Term(coefficient, variables);
    }

    public Term multiply(Term other)
    {
        Term result = other.multiply(coefficient);
        for (Variable v : vars)
            result = result.multiply(v);
        return result;
    }

    public Term abs() { return new Term(coefficient.abs(), vars); }

    public Term negate() { return new Term(coefficient.negate(), vars); }

    /**
     * Two terms are considered similar if they contain the same variables with the same degrees
     * @param term The other term
     * @return True if the terms are similar, otherwise false
     */
    public boolean isSimilar(Term term)
    {
        return vars.equals(term.vars);
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

    @Override
    public Polynomial simplify()
    {
        return new Polynomial(this);
    }
}
