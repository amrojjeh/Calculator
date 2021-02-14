package io.github.dinglydo.evaluator.parser;

import io.github.dinglydo.evaluator.expressions.Polynomial;

import java.util.ArrayList;

public class AdditionNode implements ExpandableNode
{
    ArrayList<TermNode> nodes;

    public AdditionNode()
    {
        nodes = new ArrayList<>();
    }

    public AdditionNode(ExpressionNode e, boolean positive)
    {
        this();
        add(e, positive);
    }

    @Override
    public Polynomial getPolynomial()
    {
        Polynomial result = new Polynomial();
        for (TermNode n : nodes)
            result = result.add(n.getPolynomial());
        return result;
    }

    @Override
    public void add(ExpressionNode e, boolean positive)
    {
        nodes.add(new TermNode(e, positive));
    }
}
