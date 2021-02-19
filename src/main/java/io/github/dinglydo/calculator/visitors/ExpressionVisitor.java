package io.github.dinglydo.calculator.visitors;

import io.github.dinglydo.calculator.expressions.Factors;
import io.github.dinglydo.calculator.expressions.ExpressionSum;
import io.github.dinglydo.calculator.expressions.Polynomial;
import io.github.dinglydo.calculator.expressions.RaiseExpression;
import org.jetbrains.annotations.NotNull;

public interface ExpressionVisitor
{
    void visit(@NotNull Polynomial p);
    void visit(@NotNull Factors d);
    void visit(@NotNull ExpressionSum es);
    void visit(@NotNull RaiseExpression re);
}
