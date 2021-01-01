package io.github.dinglydo.evaluator;

import org.jetbrains.annotations.NotNull;

public class Variable
{
    public final char letter;
    public final Number degree;

    /**
     * Constructs a variable such as 'x' or 'y'
     * @param letter Must be a letter
     */
    public Variable(char letter)
    {
        this(letter, 1);
    }

    public Variable(char letter, int degree)
    {
        this(letter, new Number(degree));
    }

    public Variable(char letter, @NotNull Number degree)
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

    public boolean equals(Variable var)
    {
        return var.letter == letter;
    }

    public Variable changePowerBy(Number n)
    {
        return new Variable(letter, degree.add(n));
    }

    public Variable changePowerBy(double n)
    {
        return changePowerBy(new Number(n));
    }

    @Override
    public String toString()
    {
        return letter + "";
    }
}
