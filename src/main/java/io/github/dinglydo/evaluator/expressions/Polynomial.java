package io.github.dinglydo.evaluator.expressions;

import io.github.dinglydo.evaluator.expressions.util.SimilarTerms;
import io.github.dinglydo.evaluator.primitive.Term;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

/**
 * A summation of {@code Term}s. It's capable of merging similar terms through {@code simplify}, and is immutable.
 */
public class Polynomial
{
    public final List<Term> terms;

    /**
     * Constructs a polynomial using a Term array
     * @param termsToAdd Term[]
     */
    public Polynomial(Term... termsToAdd)
    {
        LinkedList<Term> list = new LinkedList<>();
        for (Term t : termsToAdd)
            list.push(t);

        terms = Collections.unmodifiableList(list);
    }

    /**
     * Constructs a polynomial using a Term list
     * @param termsToAdd List<Term>
     */
    public Polynomial(@NotNull List<Term> termsToAdd)
    {
        terms = Collections.unmodifiableList(termsToAdd);
    }

    /**
     * Returns a new polynomial with the added term. Does not modify this instance
     * @param term Term term
     * @return The resulting polynomial
     */
    public Polynomial add(Term term)
    {
        LinkedList<Term> list = new LinkedList<>(terms);
        list.add(term);
        return new Polynomial(list);
    }

    /**
     * Returns a new polynomial with the added but negated term. Does not modify this instance
     * @param term Term term
     * @return The resulting polynomial
     */
    public Polynomial subtract(Term term)
    {
        return add(term.negate());
    }

    /**
     * Simplifies the polynomial by adding all similar terms.
     * @return The resulting polynomial
     */
    public Polynomial simplify()
    {
        ArrayList<SimilarTerms> listOfSimilarTerms = new ArrayList<>();
        LinkedList<Term> result = new LinkedList<>();

        for (Term term : terms)
        {
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
            Term term = similarTerm.simplify();
            if (!term.isZero()) result.add(term);
        }

        return new Polynomial(result);
    }

    /**
     * Returns the polynomial in a string format. Does not follow standard order
     * @return String
     */
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
