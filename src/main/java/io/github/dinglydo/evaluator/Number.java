package io.github.dinglydo.evaluator;

import org.jetbrains.annotations.NotNull;

public class Number
{
    public double num;

    public Number(double x)
    {
        num = x;
    }

    public void add(@NotNull Number n)
    {
        num += n.num;
    }

    public void subtract(Number n)
    {
        num -= n.num;
    }

    public void divide(Number n)
    {
        num /= n.num;
    }

    public void multiply(Number n)
    {
        num *= n.num;
    }

    public boolean isZero()
    {
        return num == 0;
    }

    public boolean isPositive()
    {
        return num >= 0;
    }

    public boolean isNegative()
    {
        return num < 0;
    }

    @Override
    public String toString()
    {
        return num + "";
    }
}
