package io.github.dinglydo.evaluator;

import org.jetbrains.annotations.NotNull;

public class Variable
{
    public final char letter;

    /**
     * Constructs a variable such as 'x' or 'y'
     * @param letter Must be a letter
     */
    public Variable(char letter)
    {
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

    @Override
    public String toString()
    {
        return letter + "";
    }
}
