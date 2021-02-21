package io.github.dinglydo.calculator.visitors;

import io.github.dinglydo.calculator.expressions.*;
import io.github.dinglydo.calculator.primitive.Term;
import io.github.dinglydo.calculator.util.Distributor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class SimplifyVisitor implements ExpressionVisitor
{
    private Polynomial result = null;

    private SimplifyVisitor()
    {
    }

    public static Polynomial simplify(@NotNull Expression e)
    {
        Objects.requireNonNull(e, "Expression has to be null");
        SimplifyVisitor sv = new SimplifyVisitor();
        e.accept(sv);
        return sv.result;
    }

    @Override
    public void visit(@NotNull Polynomial p)
    {
        List<Term> terms = p.terms.stream()
                .collect(Collectors.toMap(t -> t.vars, t -> t.coefficient, BigDecimal::add))
                .entrySet()
                .stream()
                .map(k -> new Term(k.getValue(), k.getKey()))
                .filter(t -> !t.isZero())
                .collect(Collectors.toList());

        this.result = new Polynomial(terms);
    }

    @Override
    public void visit(@NotNull Factors d)
    {
        for (Expression e : d.expressions)
            if (result == null)
                result = simplify(e);
            else
                result = Distributor.distributePolynomials(result, simplify(e));
    }

    @Override
    public void visit(@NotNull ExpressionSum es)
    {
        result = new Polynomial();
        for (Expression e : es.expressions)
            result = result.add(simplify(e));
        result = simplify(result);
    }

    @Override
    public void visit(@NotNull RaiseExpression re) {
        throw new UnsupportedOperationException("Cannot simplify raised expressions -- yet");
    }
}
