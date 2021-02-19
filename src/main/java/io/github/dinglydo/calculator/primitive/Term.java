package io.github.dinglydo.calculator.primitive;

import io.github.dinglydo.calculator.expressions.Polynomial;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Term
{
    public static Term ZERO = new Term("0");
    public static Term UNIT = new Term("1");
    public static Term NUNIT = new Term("-1");

    public final BigDecimal coefficient;

    // If vars is empty, it's assumed to be x^0
    public final Set<Variable> vars;

    public Term(@NotNull BigDecimal number, @NotNull Set<Variable> variables)
    {
        coefficient = number;
        vars = Collections.unmodifiableSet(variables);
    }

    public Term(String n)
    {
        this(n, "");
    }

    public Term(String n, String variables)
    {
        coefficient = new BigDecimal(n);
        vars = variables.chars()
                .mapToObj(i -> (char)i)
                .collect(Collectors.toMap(c -> c, c -> new BigDecimal(1), (v0, v1) -> v0.add(BigDecimal.ONE)))
                .entrySet()
                .stream()
                .map(e -> new Variable(e.getKey(), e.getValue()))
                .collect(Collectors.toUnmodifiableSet());
    }

    /**
     * Adds two terms. Assumes they're similar
     * @param term The term to be added
     * @return The added terms
     */
    public Term add(Term term)
    {
        if (coefficient.compareTo(BigDecimal.ZERO) == 0) return term;
        if (!isSimilar(term))
            throw new IllegalArgumentException(String.format("Could not add terms %s + %s. They're dissimilar.", this.toString(), term.toString()));
        return new Term(coefficient.add(term.coefficient), vars);
    }

    public Term multiply(double other) { return multiply(new BigDecimal(other)); }

    public Term multiply(BigDecimal other)
    {
        if (other.compareTo(BigDecimal.ZERO) == 0) return ZERO;
        return new Term(coefficient.multiply(other), vars);
    }

    public Term multiply(char other) { return multiply(new Variable(other)); }

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
        return coefficient.compareTo(BigDecimal.ZERO) == 0;
    }

    public boolean isPositive()
    {
        return coefficient.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isNegative()
    {
        return coefficient.compareTo(BigDecimal.ZERO) < 0;
    }

    @Override
    public boolean equals(Object other)
    {
        if (other instanceof Term)
            return equals((Term)other);
        return false;
    }

    public boolean equals(Term other)
    {
        return coefficient.compareTo(other.coefficient) == 0 && vars.equals(other.vars);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder(coefficient.toString());
        for (Variable v : vars)
            builder.append(v.toString());
        return builder.toString();
    }


    public Polynomial toPolynomial()
    {
        return new Polynomial(this);
    }
}
