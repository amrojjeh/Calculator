package io.github.dinglydo.evaluator;

import io.github.dinglydo.evaluator.expressions.Polynomial;
import io.github.dinglydo.evaluator.lexer.Lexer;
import io.github.dinglydo.evaluator.parser.LLParseException;
import io.github.dinglydo.evaluator.parser.Parser;

import java.util.Scanner;

public class Main
{
    private static final Scanner scanner = new Scanner(System.in);
    private static final String BOLD = "\033[1m";
    private static final String FAIL = "\033[91m";
    private static final String ENDC = "\033[0m";


    public static void main(String[] args)
    {
        boolean running = true;
        while (running)
        {
            String input = prompt().trim();
            if (input.equals("q") || input.equals("quit"))
                running = false;
            else {
                try {
                    Polynomial p = Parser.parse(Lexer.lex(input)).simplify();
                    System.out.println(p.toString());
                } catch (LLParseException e) {
                    System.out.printf("%sCouldn't parse input.%s%n", FAIL + BOLD, ENDC);
                    System.out.println(input);
                    int diff = e.getToken().end - 1 - e.getToken().start;
                    if (diff > 0)
                        System.out.printf("%s^%s^%n", " ".repeat(e.getToken().start), "~".repeat(diff - 1));
                    else
                        System.out.printf("%s^%n", " ".repeat(e.getToken().start));
                }
            }
        }
    }

    public static String prompt()
    {
        System.out.print(">> ");
        return scanner.nextLine();
    }
}
