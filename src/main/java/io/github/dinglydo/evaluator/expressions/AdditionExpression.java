package io.github.dinglydo.evaluator.expressions;

import io.github.dinglydo.evaluator.Number;
import io.github.dinglydo.evaluator.SimilarTerms;
import io.github.dinglydo.evaluator.Term;

import java.util.Stack;
import java.util.ArrayList;

public class AdditionExpression
{
    Stack<Term> terms;

    public AdditionExpression()
    {
        terms = new Stack<>();
    }

    public void add(Term term)
    {
        terms.add(term);
    }

    public void subtract(Term term)
    {
        terms.add(term.multiply(new Number(-1)));
    }

    public void evaluate()
    {
        ArrayList<SimilarTerms> listOfSimilarTerms = new ArrayList<>();

        while (!terms.empty())
        {
            Term term = terms.pop();
            boolean foundMatch = false;
            for (SimilarTerms similarTerm : listOfSimilarTerms)
            {
                if (similarTerm.isSimilar(term))
                {
                    foundMatch = true;
                    similarTerm.terms.add(term);
                    break;
                }
            }
            if (!foundMatch) listOfSimilarTerms.add(new SimilarTerms(term));
        }

        for (SimilarTerms similarTerm : listOfSimilarTerms)
        {
            Term term = similarTerm.evaluate();
            if (!term.isZero()) terms.add(term);
        }
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (Term term : terms)
        {
            if (term.isPositive()) builder.append("+ ");
            else builder.append("- ");
            builder.append(term.abs().toString()).append(" ");
        }
        return builder.toString();
    }
}
