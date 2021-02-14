package io.github.dinglydo.evaluator.parser;

public interface ExpandableNode extends ExpressionNode
{
    void add(ExpressionNode e, boolean positive);
}
