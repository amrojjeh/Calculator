package io.github.dinglydo.calculator.lexer;

import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    @NotNull
    private static TokenType getTokenTypeFromMatcher(@NotNull Matcher matcher)
    {
        for (TokenType tt : TokenType.values())
        {
            if (matcher.group(tt.name()) != null)
                return tt;
        }
        return TokenType.INVALID;
    }

    @NotNull
    public static LinkedList<Token> lex(@NotNull String line)
    {
        LinkedList<Token> result = new LinkedList<>();
        Pattern pattern = Pattern.compile(TokenType.getFullRegex());
        Matcher m = pattern.matcher(line);
        while (m.find())
        {
            TokenType type = getTokenTypeFromMatcher(m);
            if (type != TokenType.WHITESPACE)
                result.add(new Token(type, m.group(), m.start(), m.end()));
        }
        return result;
    }
}
