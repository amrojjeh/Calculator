package io.github.dinglydo.calculator.parser;

import io.github.dinglydo.calculator.expressions.Polynomial;
import io.github.dinglydo.calculator.lexer.Lexer;
import io.github.dinglydo.calculator.visitors.SimplifyVisitor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest
{
    @ParameterizedTest
    @ValueSource(strings = {"-2 + 5",
            "30 + 20 - 50",
            "5x + 2x + 5 * 2"})
    public void shouldParseSimpleExpressions(String input)
    {
        assertDoesNotThrow(() -> Parser.parse(Lexer.lex(input)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"- + 2"})
    public void shouldParseRadicalExpressions(String input)
    {
        assertDoesNotThrow(() -> Parser.parse(Lexer.lex(input)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"-2x + ",
            "+++2",
            "5.x"})
    public void shouldNotParse(String input)
    {
        assertThrows(LLParseException.class, () -> Parser.parse(Lexer.lex(input)));
    }

    @Test
    public void polynomialEvaluation()
    {
        String input = "-2 + 5 + 20 + -5.5";
        try {
            Polynomial result = SimplifyVisitor.simplify(Parser.parse(Lexer.lex(input)));
            assertEquals("+ 17.5 ", result.toString());
        } catch (ParseException ignored) {

        }
    }
}
