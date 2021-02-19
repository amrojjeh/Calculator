package io.github.dinglydo.calculator.lexer;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest
{
    @Test
    public void noError()
    {
        String input = "20x + 3";

        LinkedList<Token> expectedTokens = new LinkedList<>();
        expectedTokens.add(new Token(TokenType.NUMBER, "20", 0, 2));
        expectedTokens.add(new Token(TokenType.VARIABLE, "x", 2, 3));
        expectedTokens.add(new Token(TokenType.PLUSMINUS, "+", 4, 5));
        expectedTokens.add(new Token(TokenType.NUMBER, "3", 6, 7));
        expectedTokens.add(new Token(TokenType.TERMINATE, "", 7, 7));

        LinkedList<Token> tokens = Lexer.lex(input);
        assertEquals(expectedTokens.size(), tokens.size());
        for (int x = 0; x < tokens.size(); ++x)
        {
            Token actualToken = tokens.get(x);
            Token expectedToken = expectedTokens.get(x);

            assertEquals(actualToken.kind, expectedToken.kind);
            assertEquals(actualToken.value, expectedToken.value);
            assertEquals(actualToken.start, expectedToken.start);
            assertEquals(actualToken.end, expectedToken.end);
        }
    }
}