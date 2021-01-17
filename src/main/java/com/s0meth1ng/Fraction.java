package com.s0meth1ng;

import org.jetbrains.annotations.NotNull;
import java.util.Objects;

public class Fraction implements IFraction {

    private int numerator, denominator;

    public Fraction(int numerator, int denominator) throws ArithmeticException {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        setDenominator(denominator);
        setNumerator(numerator);
    }
    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }
    public void setDenominator(int denominator) throws ArithmeticException {
        if (denominator == 0) {
            throw new ArithmeticException("Invalid denominator value");
        }
        this.denominator = denominator;
        if (denominator < 0) {
            this.numerator = -this.numerator;
            this.denominator = -this.denominator;
        }
    }
    public int getNumerator() {
        return numerator;
    }
    public int getDenominator() {
        return denominator;
    }

    public void normalize() {
        if (this.numerator == 0) {
            this.setDenominator(1);
            return;
        }
        int hcf = getHighestCommonFactor(numerator, denominator);
        if (hcf > 1) {
            this.setNumerator(this.numerator / hcf);
            this.setDenominator(this.denominator / hcf);
        }
    }

    private int getHighestCommonFactor(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        //equals
        if (a == b) {
            return a;
        }
        //equals to 1
        if ((a == 1) || (b == 1)) {
            return 1;
        }
        //both even
        if ((a%2 == 0) && (b%2 == 0)) {
            return 2*getHighestCommonFactor(a/2, b/2);
        }
        //both odd
        if ((a%2 == 1) && (b%2 == 1)) {
            if (a > b) {
                return getHighestCommonFactor((a-b)/2, b);
            } else {
                return getHighestCommonFactor((b-a)/2, a);
            }
        }
        //one even, one odd
        if (a%2 == 0) {
            return getHighestCommonFactor(a/2, b);
        } else {
            return getHighestCommonFactor(a, b/2);
        }
    }

    private int getLowestCommonDenominator(Fraction fraction) {
        return this.denominator * fraction.getDenominator() / getHighestCommonFactor(this.denominator, fraction.getDenominator());
    }

    private int getNewNumerator(int newDenominator) {
        return this.numerator * (newDenominator/this.denominator);
    }

    private Fraction getFractionFromInt(int n, int denominator) {
        return new Fraction(denominator * n, denominator);
    }

    @Override
    public Fraction plus(@NotNull Fraction fraction) {
        int newDenominator = getLowestCommonDenominator(fraction);
        int newNumerator = this.getNewNumerator(newDenominator) + fraction.getNewNumerator(newDenominator);
        return new Fraction(newNumerator, newDenominator);
    }
    @Override
    public Fraction plus(int n) {
        return this.plus(getFractionFromInt(n, this.denominator));
    }

    @Override
    public Fraction minus(@NotNull Fraction fraction) {
        int newDenominator = getLowestCommonDenominator(fraction);
        int newNumerator = this.getNewNumerator(newDenominator) - fraction.getNewNumerator(newDenominator);
        return new Fraction(newNumerator, newDenominator);
    }
    @Override
    public Fraction minus(int n) {
        return this.minus(getFractionFromInt(n, this.denominator));
    }

    @Override
    public Fraction multiply(@NotNull Fraction fraction) {
        return new Fraction(this.numerator * fraction.getNumerator(), this.denominator * fraction.getDenominator());
    }
    @Override
    public Fraction multiply(int n) {
        return new Fraction(this.numerator * n, this.denominator);
    }

    @Override
    public Fraction divide(@NotNull Fraction fraction) throws ArithmeticException {
        if (fraction.getNumerator() == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return new Fraction(this.numerator * fraction.getDenominator(), this.denominator * fraction.getNumerator());
    }
    @Override
    public Fraction divide(int n) throws ArithmeticException {
        if (n == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return new Fraction(this.numerator, this.denominator * n);
    }

    public boolean equals(int n) {
        if (this.numerator % this.denominator == 0) {
            return (this.numerator / this.denominator) == n;
        }
        return false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction f = (Fraction) o;
        return this.numerator == f.getNumerator() &&
                this.denominator == f.getDenominator();
    }
    @Override
    public int hashCode() {
        return Objects.hash(getNumerator(), getDenominator());
    }

    public String toString() {
        return String.format("%s/%s", this.numerator, this.denominator);
    }

}
