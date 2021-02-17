package io.github.dinglydo.evaluator.primitive;

import io.github.dinglydo.evaluator.expressions.Term;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TermTest
{
    @Test
    public void addingTerms()
    {
        Term t1 = new Term(50);
        Term t2 = new Term(27);
        Term result = t1.add(t2);

        assertTrue(result.coefficient.num == 77.0);
    }
}
