package io.github.dinglydo.evaluator;

import org.jetbrains.annotations.NotNull;

public class Number
{
    public final double num;

    public Number(double x) { num = x; }

    public Number add(@NotNull Number n) { return new Number(num + n.num); }

    public Number subtract(Number n) { return new Number(num - n.num); }

    public Number divide(Number n) { return new Number(num / n.num); }

    public Number multiply(Number n) { return new Number(num * n.num); }

    public Number abs() { return new Number(Math.abs(num)); }

    public boolean isZero() { return num == 0; }

    public boolean isPositive() { return num >= 0; }

    public boolean isNegative() { return num < 0; }

    @Override
    public String toString() { return num + ""; }
}
