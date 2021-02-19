package io.github.dinglydo.calculator.expressions;

import io.github.dinglydo.calculator.visitors.ExpressionVisitor;

import java.math.BigDecimal;


public class RaiseExpression implements Expression
{
    public BigDecimal degree;
    public Expression expression;

    public RaiseExpression(Expression e, BigDecimal degree)
    {
        this.expression = e;
        this.degree = degree;
    }

    @Override
    public void accept(ExpressionVisitor visitor)
    {
        visitor.visit(this);
    }
}
