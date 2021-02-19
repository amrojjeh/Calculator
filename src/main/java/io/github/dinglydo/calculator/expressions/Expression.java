package io.github.dinglydo.calculator.expressions;

import io.github.dinglydo.calculator.visitors.ExpressionVisitor;

public interface Expression
{
    void accept(ExpressionVisitor visitor);
}
