package io.github.dinglydo.evaluator.parser;

import io.github.dinglydo.evaluator.expressions.Distributor;
import io.github.dinglydo.evaluator.expressions.Polynomial;
import io.github.dinglydo.evaluator.primitive.Term;

class TermNode implements ExpressionNode
{
    public final boolean positive;
    public final ExpressionNode expression;

    public TermNode(ExpressionNode e, boolean positive)
    {
        this.expression = e;
        this.positive = positive;
    }

    @Override
    public Polynomial getPolynomial()
    {
        Polynomial result = expression.getPolynomial();
        assert result.terms.size() == 1;
        if (positive) return result;
        return new Polynomial(result.terms.get(0).negate());
    }
}
