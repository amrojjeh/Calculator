package io.github.dinglydo.calculator.primitive;

import java.math.BigDecimal;

/**
 * A mathematical variable. Stores the letter which represents the variable and the degree.
 * Letters are case sensitive
 */
public class Variable
{
    public final char letter;

    // TODO: Make fraction once that's supported
    public final BigDecimal degree;

    /**
     * Constructs a variable such as 'x' or 'y' with a degree of 1
     * @param letter Must be a letter
     */
    public Variable(char letter)
    {
        this(letter, new BigDecimal(1));
    }

    /**
     * Constructs a variable such as 'x' or 'y' with the chose degree
     * @param letter Must be a letter
     * @param degree An integer. Negative operations are not supported yet
     */
    public Variable(char letter, int degree)
    {
        this(letter, new BigDecimal(degree));
    }

    /**
     * Constructs a variable such as 'x' or 'y' with the chose degree
     * @param letter Must be a letter
     * @param degree An integer. Negative operations are not supported yet
     */
    public Variable(char letter, BigDecimal degree)
    {
        this.degree = degree;
        if (Character.isAlphabetic(letter))
            this.letter = letter;
        else
            throw new IllegalArgumentException("letter was not a letter. Instead, it was " + letter);
    }

    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Variable)
            return equals((Variable)object);
        return false;
    }

    /**
     * Checks if two variables are the same. They're the same if they have the same letter and degree
     * @param var The other variable
     * @return True if the degree and letter are the same
     */
    public boolean equals(Variable var) { return var.letter == letter && var.degree.equals(degree); }

    /**
     * Return a new variable with the new power as changed by n
     * @param n The power to change by
     * @return The resulting variable
     */
    public Variable changePowerBy(BigDecimal n) { return new Variable(letter, degree.add(n)); }

    @Override
    public int hashCode()
    {
        return letter;
    }

    @Override
    public String toString()
    {
        if (degree.compareTo(BigDecimal.ONE) == 0)
            return String.format("%c", letter);
        return String.format("(%c^%s)", letter, degree.toString());
    }
}
