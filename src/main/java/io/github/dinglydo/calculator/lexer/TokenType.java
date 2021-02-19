package io.github.dinglydo.calculator.lexer;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum TokenType
{
    NUMBER("\\d+(\\.\\d+)?|(\\.\\d+)"),
    VARIABLE("[A-z]+"),
    PLUSMINUS("[+-]"),
    MULTDIV("[*/]"),
    LPAREN("\\("),
    RPAREN("\\)"),

    WHITESPACE("\\s+"),
    INVALID("."),
    TERMINATE("");

    public final String regex;

    TokenType(String regex)
    {
        this.regex = regex;
    }

    public static String getFullRegex()
    {
        String general = "(?<%s>%s)";
        return Arrays.stream(TokenType.values())
                .map(tt -> String.format(general, tt.name(), tt.regex))
                .collect(Collectors.joining("|"));
    }
}
