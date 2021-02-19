package io.github.dinglydo.calculator.primitive;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TermTest
{
    @Test
    public void addingTerms()
    {
        Term t1 = new Term("50");
        Term t2 = new Term("27");
        Term result = t1.add(t2);


        assertEquals(result.coefficient.compareTo(BigDecimal.valueOf(77.0)), 0);
    }

    @Test
    public void varSetsEqual()
    {
        Term t1 = new Term("2", "x");
        Term t2 = new Term("5", "x");
        assertEquals(t1.vars, t2.vars);
    }
}
