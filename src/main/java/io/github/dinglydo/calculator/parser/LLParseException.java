package io.github.dinglydo.calculator.parser;

import io.github.dinglydo.calculator.lexer.Token;

import java.text.ParseException;

public class LLParseException extends ParseException
{
    public final Token token;

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s the detail message
     * @param t the token which caused the parsing error
     */
    public LLParseException(String s, Token t)
    {
        super(s, t.start);
        token = t;
    }

    public Token getToken()
    {
        return token;
    }
}
