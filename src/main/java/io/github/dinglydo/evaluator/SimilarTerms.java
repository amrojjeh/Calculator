package io.github.dinglydo.evaluator;

import java.util.ArrayList;

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

    public Term evaluate()
    {
        Term result = new Term(0);
        for (Term term : terms)
            result = result.add(term);
        return result;
    }
}
