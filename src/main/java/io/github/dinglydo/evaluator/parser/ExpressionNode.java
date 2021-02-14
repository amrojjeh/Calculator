package io.github.dinglydo.evaluator.parser;

import io.github.dinglydo.evaluator.expressions.Polynomial;

public interface ExpressionNode
{
    Polynomial getPolynomial();
}
