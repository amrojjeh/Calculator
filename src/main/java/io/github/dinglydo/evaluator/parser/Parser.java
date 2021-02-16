package io.github.dinglydo.evaluator.parser;

import io.github.dinglydo.evaluator.expressions.*;
import io.github.dinglydo.evaluator.lexer.Token;
import io.github.dinglydo.evaluator.lexer.TokenType;
import io.github.dinglydo.evaluator.primitive.Term;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.LinkedList;

public class Parser
{
    public static Expression parse(LinkedList<Token> tokens) throws ParseException
    {
        return expression(tokens);
    }

    private static Expression expression(LinkedList<Token> tokens) throws ParseException
    {
        // expression -> signed_term sum_op
        ExpressionSum result = new ExpressionSum(signedTerm(tokens));
        return sumOp(tokens, result);
    }

    private static Expression signedTerm(LinkedList<Token> tokens) throws ParseException
    {
        // signed_term -> PLUSMINUS term
        TokenType lookahead = lookahead(tokens);

        return switch (lookahead) {
            case PLUSMINUS -> {
                Token t = tokens.pop();
                if (t.value.equals("+"))
                    yield term(tokens);
                else
                    yield new Distributor(term(tokens), new Term(-1));
            }
            case TERMINATE -> throw new ParseException("String terminated early. Was expecting a term.", tokens.pop().start);
            default -> term(tokens);
        };
    }

    private static Expression term(LinkedList<Token> tokens) throws ParseException
    {
        // term -> NUMBER term_op
        TokenType lookahead = lookahead(tokens);
        if (lookahead == TokenType.NUMBER)
        {
            Token t = tokens.pop();
            double val = Double.parseDouble(t.value);
            Expression e = new Polynomial(new Term(val));
            return e;
        }

        throw new ParseException("Expected number.", tokens.pop().start);
    }

    private static Expression sumOp(@NotNull LinkedList<Token> tokens, Expression e) throws ParseException
    {
        // sum_op -> PLUSMINUS term
        // sum_op -> TERMINATE

        TokenType lookahead = lookahead(tokens);
        ExpressionSum result;

        if (e instanceof ExpressionSum)
            result = (ExpressionSum)e;
        else
            result = new ExpressionSum(e);

        return switch (lookahead)
        {
            case TERMINATE -> result;
            case PLUSMINUS -> {
                Token t = tokens.pop();
                result = result.add(new Distributor(signedTerm(tokens), new Term(t.value.equals("+") ? 1 : -1)));
                yield sumOp(tokens, result);
            }
            default -> throw new ParseException("Was expecting an end or another sum.", tokens.pop().start);
        };
    }

    @NotNull
    private static TokenType lookahead(@NotNull LinkedList<Token> tokens)
    {
        Token lookahead = tokens.peek();
        if (lookahead == null) return TokenType.TERMINATE;
        return lookahead.kind;
    }
}
