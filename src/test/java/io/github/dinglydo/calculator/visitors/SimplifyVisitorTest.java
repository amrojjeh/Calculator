package io.github.dinglydo.calculator.visitors;

import io.github.dinglydo.calculator.expressions.Polynomial;
import io.github.dinglydo.calculator.primitive.Term;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplifyVisitorTest
{
    @Test
    public void addingVariables()
    {
        Polynomial expected = new Polynomial(new Term("7", "x"));

        Polynomial p = new Polynomial(new Term("5", "x"), new Term("2", "x"));
        Polynomial actual = SimplifyVisitor.simplify(p);

        assertEquals(expected, actual);
    }
}
