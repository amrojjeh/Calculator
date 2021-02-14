package io.github.dinglydo.evaluator.parser;

import io.github.dinglydo.evaluator.expressions.Polynomial;
import io.github.dinglydo.evaluator.lexer.Lexer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest
{
    @ParameterizedTest
    @ValueSource(strings = {"-2 + 5", "30 + 20 - 50"})
    public void shouldParse(String input)
    {
        assertDoesNotThrow(() -> Parser.parse(Lexer.lex(input)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-2x + ", "- + 2"})
    public void shouldNotParse(String input)
    {
        assertThrows(ParseException.class, () -> Parser.parse(Lexer.lex(input)));
    }

    @Test
    public void polynomialEvaluation()
    {
        String input = "-2 + 5";
        try {
            Polynomial p = Parser.parse(Lexer.lex(input)).getPolynomial();
            Polynomial result = p.simplify();
            assertEquals("+ 3.0 ", result.toString());
        } catch (ParseException ignored) {

        }
    }
}