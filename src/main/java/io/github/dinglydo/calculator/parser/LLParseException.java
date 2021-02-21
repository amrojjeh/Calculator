package io.github.dinglydo.calculator.parser;

import io.github.dinglydo.calculator.lexer.Token;
import io.github.dinglydo.calculator.lexer.TokenType;

import java.text.ParseException;

public class LLParseException extends ParseException
{
    public final TokenType actual;

    public final int start;
    public final int end;

    /**
     * Constructs a ParseException with the specified detail message and
     * offset.
     * A detail message is a String that describes this particular exception.
     *
     * @param s the detail message
     * @param actual the token which caused the parsing error
     * @param start the start of the error
     * @param end the end of the error
     */
    public LLParseException(String s, TokenType actual, int start, int end)
    {
        super(s, start);
        this.actual = actual;
        this.start = start;
        this.end = end;
    }
}
