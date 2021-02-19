package io.github.dinglydo.calculator.expressions;

import io.github.dinglydo.calculator.visitors.ExpressionVisitor;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This was created to contrast from {@code Polynomial}, as {@code Polynomial} assume it consists of the most primitive
 * of expressions, while {@code ExpressionsSum} is free to add any expression.
 */
public class ExpressionSum implements Expression
{
    public final List<Expression> expressions;

    public ExpressionSum(Expression... expressionsToAdd)
    {
        this(Arrays.asList(expressionsToAdd));
    }

    public ExpressionSum(@NotNull List<Expression> expressionsToAdd)
    {
        expressions = Collections.unmodifiableList(expressionsToAdd);
    }

    public ExpressionSum add(Expression e)
    {
        LinkedList<Expression> list = new LinkedList<>(expressions);
        list.add(e);
        return new ExpressionSum(list);
    }

    @Override
    public void accept(ExpressionVisitor visitor)
    {
        visitor.visit(this);
    }
}
