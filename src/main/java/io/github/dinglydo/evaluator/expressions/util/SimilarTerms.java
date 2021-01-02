package io.github.dinglydo.evaluator.expressions.util;

import io.github.dinglydo.evaluator.primitive.Term;

import java.util.ArrayList;

/**
 * A helper class for the Polynomial class
 */
public class SimilarTerms
{
    public final ArrayList<Term> terms;

    public SimilarTerms(Term term)
    {
        terms = new ArrayList<>();
        terms.add(term);
    }

    public boolean isSimilar(Term term)
    {
        return terms.get(0).isSimilar(term);
    }

    public Term simplify()
    {
        Term result = new Term(0);
        for (Term term : terms)
            result = result.add(term);
        return result;
    }
}
