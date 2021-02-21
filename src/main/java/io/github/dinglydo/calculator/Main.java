package io.github.dinglydo.calculator;

import io.github.dinglydo.calculator.expressions.Polynomial;
import io.github.dinglydo.calculator.lexer.Lexer;
import io.github.dinglydo.calculator.parser.LLParseException;
import io.github.dinglydo.calculator.parser.Parser;
import io.github.dinglydo.calculator.visitors.SimplifyVisitor;

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
                    Polynomial p = SimplifyVisitor.simplify(Parser.parse(Lexer.lex(input)));
                    System.out.println(p.toString());
                } catch (LLParseException e) {
                    System.out.printf("%s%s.%s%n", FAIL + BOLD, e.getMessage(), ENDC);
                    System.out.println(input);
                    int diff = e.end - 1 - e.start;
                    if (diff > 0)
                        System.out.printf("%s^%s^%n", " ".repeat(e.start), "~".repeat(diff - 1));
                    else
                        System.out.printf("%s^%n", " ".repeat(e.start));
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
