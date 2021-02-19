package io.github.dinglydo.calculator.parser;

import io.github.dinglydo.calculator.expressions.*;
import io.github.dinglydo.calculator.lexer.Token;
import io.github.dinglydo.calculator.lexer.TokenType;
import io.github.dinglydo.calculator.primitive.Term;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

public class Parser
{
    public static Expression parse(LinkedList<Token> tokens) throws LLParseException
    {
        Expression result = expression(tokens);
        if (lookahead(tokens) != TokenType.TERMINATE)
        {
            Token t = tokens.pop();
            throw new LLParseException("Character (" + t.value + ") could not be parsed", t);
        }
        return result;
    }

    private static Expression expression(LinkedList<Token> tokens) throws LLParseException
    {
        // expression -> signed_term sum_op
        ExpressionSum result = new ExpressionSum(signedTerm(tokens));
        return sumOp(tokens, result);
    }

    private static Expression signedTerm(LinkedList<Token> tokens) throws LLParseException
    {
        // signed_term -> PLUSMINUS term
        // signed_term -> term
        TokenType lookahead = lookahead(tokens);

        return switch (lookahead) {
            case PLUSMINUS -> {
                Token t = tokens.pop();
                if (t.value.equals("+"))
                    yield term(tokens);
                yield new Factors(term(tokens), Term.NUNIT.toPolynomial());
            }
            case TERMINATE -> throw new LLParseException("String terminated early. Was expecting a term.", tokens.pop());
            default -> term(tokens);
        };
    }

    private static Expression term(LinkedList<Token> tokens) throws LLParseException
    {
        // term -> signed_factor term_op
        return termOp(tokens, signedFactor(tokens));
    }

    private static Expression signedFactor(LinkedList<Token> tokens) throws LLParseException
    {
        // signed_factor -> PLUSMINUS factor
        // signed_factor -> factor
        TokenType lookahead = lookahead(tokens);

        return switch (lookahead) {
            case PLUSMINUS -> {
                Token t = tokens.pop();
                if (t.value.equals("+"))
                    yield factor(tokens);
                yield new Factors(factor(tokens), Term.NUNIT.toPolynomial());
            }
            case TERMINATE -> throw new LLParseException("Was expecting a number or variable.", tokens.pop());
            default -> factor(tokens);
        };
    }

    private static Expression factor(LinkedList<Token> tokens) throws LLParseException
    {
        // factor -> NUMBER
        // factor -> VARIABLES
        TokenType lookahead = lookahead(tokens);
        return switch (lookahead) {
            case NUMBER -> new Term(tokens.pop().value).toPolynomial();
            case VARIABLE -> new Term("1", tokens.pop().value).toPolynomial();
            default -> throw new LLParseException("Was expecting a number or variable.", tokens.pop());
        };
    }

    private static Expression termOp(LinkedList<Token> tokens, Expression e) throws LLParseException
    {
        // term_op -> MULTDIV signed_factor term_op
        // term_op -> VARIABLE term_op
        // term_op -> TERMINATE
        TokenType lookahead = lookahead(tokens);

        if (lookahead == TokenType.MULTDIV)
        {
            Token t = tokens.pop();
            if (t.value.equals("*"))
                if (e instanceof Factors)
                    return termOp(tokens, ((Factors) e).multiply(signedFactor(tokens)));
                else
                    return termOp(tokens, new Factors(e, signedFactor(tokens)));
            // TODO: Add support for fractions
            throw new LLParseException("Division is not supported yet.", t);
        }

        else if (lookahead == TokenType.VARIABLE)
        {
            Token t = tokens.pop();
            if (e instanceof Factors)
                return termOp(tokens, ((Factors) e).multiply(new Term("1", t.value).toPolynomial()));
            else
                return termOp(tokens, new Factors(e, new Term("1", t.value).toPolynomial()));
        }

        return e;
    }

    private static Expression sumOp(@NotNull LinkedList<Token> tokens, Expression e) throws LLParseException
    {
        // sum_op -> PLUSMINUS term
        // sum_op -> TERMINATE

        TokenType lookahead = lookahead(tokens);
        ExpressionSum result;

        if (e instanceof ExpressionSum)
            result = (ExpressionSum)e;
        else
            result = new ExpressionSum(e);

        if (lookahead == TokenType.PLUSMINUS)
        {
            Token t = tokens.pop();
            result = result.add(new Factors(signedTerm(tokens), t.value.equals("+") ? Term.UNIT.toPolynomial() : Term.NUNIT.toPolynomial()));
            return sumOp(tokens, result);
        }

        return result;
    }

    @NotNull
    private static TokenType lookahead(@NotNull LinkedList<Token> tokens)
    {
        Token lookahead = tokens.peek();
        if (lookahead == null) return TokenType.TERMINATE;
        return lookahead.kind;
    }
}
