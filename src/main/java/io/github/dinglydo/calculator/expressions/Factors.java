package io.github.dinglydo.calculator.expressions;

import io.github.dinglydo.calculator.visitors.ExpressionVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * A series of expressions multiplying each other
 */
public class Factors implements Expression
{
    public final List<Expression> expressions;

    /**
     * Constructs a Distributor object that multiplies a sequence of {@code Expression}s
     * @param expressionsToMultiply Expressions to be multiplied
     */
    public Factors(Expression... expressionsToMultiply)
    {
        this(Arrays.asList(expressionsToMultiply));
    }

    /**
     * Constructs a Distributor object that multiplies a sequence of {@code Expression}s
     * @param expressionsToMultiply Expressions to be multiplied
     */
    public Factors(@NotNull List<Expression> expressionsToMultiply)
    {
        expressions = Collections.unmodifiableList(expressionsToMultiply);
    }

    /**
     * Multiplies the distribution by another {@code Expression}. {@code 2(2 + 2).multiply(2) -> 4(2 +2 )}
     * @param e the other {@code Expression}
     * @return the resulting distribution
     */
    public Factors multiply(Expression e)
    {
        LinkedList<Expression> list = new LinkedList<>(expressions);
        list.add(e);
        return new Factors(list);
    }

    @Override
    public void accept(ExpressionVisitor ev)
    {
        ev.visit(this);
    }
}
