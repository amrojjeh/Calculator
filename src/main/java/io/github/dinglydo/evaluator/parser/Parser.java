package io.github.dinglydo.evaluator.parser;

import io.github.dinglydo.evaluator.lexer.Token;
import io.github.dinglydo.evaluator.lexer.TokenType;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.LinkedList;

public class Parser
{
    public static ExpressionNode parse(LinkedList<Token> tokens) throws ParseException
    {
        return expression(tokens);
    }

    private static ExpressionNode expression(LinkedList<Token> tokens) throws ParseException
    {
        // expression -> signed_term sum_op
        AdditionNode result = new AdditionNode(signedTerm(tokens), true);
        return sumOp(tokens, result);
    }

    private static ExpressionNode signedTerm(LinkedList<Token> tokens) throws ParseException
    {
        // signed_term -> PLUSMINUS term
        TokenType lookahead = lookahead(tokens);

        return switch (lookahead) {
            case PLUSMINUS -> {
                Token t = tokens.pop();
                if (t.value.equals("+"))
                    yield term(tokens);
                else
                    yield new AdditionNode(term(tokens), false);
            }
            case TERMINATE -> throw new ParseException("String terminated early. Was expecting a term.", tokens.pop().start);
            default -> term(tokens);
        };
    }

    private static ExpressionNode term(LinkedList<Token> tokens) throws ParseException
    {
        // term -> NUMBER term_op
        TokenType lookahead = lookahead(tokens);
        if (lookahead == TokenType.NUMBER)
        {
            Token t = tokens.pop();
            double val = Double.parseDouble(t.value);
            ExpressionNode e = new AdditionNode(new NumberNode(val), true);
            return e;
        }

        throw new ParseException("Expected number.", tokens.pop().start);
    }

    private static ExpressionNode sumOp(@NotNull LinkedList<Token> tokens, ExpressionNode e) throws ParseException
    {
        // sum_op -> PLUSMINUS term
        // sum_op -> TERMINATE

        TokenType lookahead = lookahead(tokens);
        AdditionNode result;

        if (e instanceof AdditionNode)
            result = (AdditionNode)e;
        else
            result = new AdditionNode(e, true);

        return switch (lookahead)
        {
            case TERMINATE -> result;
            case PLUSMINUS -> {
                Token t = tokens.pop();
                result.add(term(tokens), t.value.equals("+"));
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
