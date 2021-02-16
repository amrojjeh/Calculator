package io.github.dinglydo.evaluator;

import io.github.dinglydo.evaluator.expressions.Polynomial;
import io.github.dinglydo.evaluator.lexer.Lexer;
import io.github.dinglydo.evaluator.parser.Parser;

import java.text.ParseException;
import java.util.Scanner;

public class Main
{
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args)
    {
        boolean running = true;
        while (running)
        {
            String input = prompt().trim();
            if (input.equals("q") || input.equals("equals"))
                running = false;
            else {
                try {
                    Polynomial p = Parser.parse(Lexer.lex(input)).simplify();
                    System.out.println(p.toString());
                } catch (ParseException e) {
                    System.out.println("Couldn't parse input. " + e.toString());
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
