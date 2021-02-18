package io.github.dinglydo.evaluator.primitive;

import org.jetbrains.annotations.NotNull;

/**
 * A wrapper for a double. Represents an immutable number in a math expression
 */
public class Number
{
    public final double num;

    /**
     * Constructs a Number with a double, x
     * @param x The double number which to store
     */
    public Number(double x) { num = x; }

    /**
     * Add to the number. Returns a new number. It does not modify this instance.
     * @param n The number to add
     * @return The result, as it does not modify this instance
     */
    public Number add(double n) { return add(new Number(n)); }

    /**
     * Add to the number. Returns a new number. It does not modify this instance.
     * @param n The number to add
     * @return The result, as it does not modify this instance
     */
    public Number add(@NotNull Number n) { return new Number(num + n.num); }

    /**
     * Subtracts from the number. Returns a new number. It does not modify this instance.
     * @param n The number to subtract
     * @return The result, as it does not modify this instance
     */
    public Number subtract(double n) { return subtract(new Number(n)); }

    /**
     * Subtracts from the number. Returns a new number. It does not modify this instance.
     * @param n The number to subtract
     * @return The result, as it does not modify this instance
     */
    public Number subtract(@NotNull  Number n) { return new Number(num - n.num); }

    /**
     * Divide this number by n. Returns a new number. It does not modify this instance.
     * @param n The number to divide by
     * @return The result, as it does not modify this instance
     */
    public Number divide(double n) { return divide(new Number(n)); }

    /**
     * Divide this number by n. Returns a new number. It does not modify this instance.
     * @param n The number to divide by
     * @return The result, as it does not modify this instance
     */
    public Number divide(@NotNull Number n) { return new Number(num / n.num); }

    /**
     * Multiply this number by a double n. Return a new number. It does not modify this instance
     * @param n The number to multiply by
     * @return The result, as it does not modify this instance
     */
    public Number multiply(double n) { return multiply(new Number(n)); }

    /**
     * Multiply this number by a double n. Return a new number. It does not modify this instance
     * @param n The number to multiply by
     * @return The result, as it does not modify this instance
     */
    public Number multiply(Number n) { return new Number(num * n.num); }

    /**
     * The absolute value of this number. It does not modify this instance
     * @return The absolute value
     */
    public Number abs() { return new Number(Math.abs(num)); }

    /**
     * Negates the number and returns it. It does not modify this instance
     * @return Equivalent to multiply(-1)
     */
    public Number negate() { return multiply(-1); }

    /**
     * Is the number 0?
     * @return True if the num == 0
     */
    public boolean isZero() { return num == 0; }

    /**
     * Is the number 0 or positive?
     * @return True if the num >= 0
     */
    public boolean isPositive() { return num >= 0; }

    /**
     * Is the number less than 0?
     * @return True if num < 0
     */
    public boolean isNegative() { return num < 0; }

    /**
     * @return The string version of this object
     */

    @Override
    public int hashCode()
    {
        return Double.hashCode(num);
    }

    @Override
    public String toString() { return num + ""; }
}
