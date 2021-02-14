package io.github.dinglydo.evaluator.parser;

import io.github.dinglydo.evaluator.expressions.Polynomial;
import io.github.dinglydo.evaluator.primitive.Term;

public class NumberNode implements ExpressionNode
{
    public double num;

    public NumberNode(double num)
    {
        this.num = num;
    }

    @Override
    public Polynomial getPolynomial()
    {
        return new Polynomial(new Term(num));
    }
}
