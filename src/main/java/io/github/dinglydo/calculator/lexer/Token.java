package io.github.dinglydo.calculator.lexer;

public class Token
{
    public final TokenType kind;
    public final String value;
    public final int start;
    public final int end;

    public Token(TokenType kind, String value, int start, int end)
    {
        this.kind = kind;
        this.value = value;
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString()
    {
        return String.format("%d-%d: %s(%s)", start, end, kind, value);
    }
}
