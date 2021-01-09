package com.s0meth1ng;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    private static Fraction f1;
    private static Fraction f2;

    @BeforeAll
    public static void setup() {
        f1 = new Fraction(22, 242);
        f2 = new Fraction(1, 4);
    }

    @Test
    void normalize() {
        Fraction expected = new Fraction(1, 3);

        Fraction actual = new Fraction(3, 9);

        assertEquals(expected.getNumerator(), actual.getNumerator());
        assertEquals(expected.getDenominator(), actual.getDenominator());
    }

    @Test
    void plus() {
        Fraction expected = new Fraction(15, 44);

        Fraction actual = f1.plus(f2);

        assertNotNull(actual);
        assertEquals(expected.getNumerator(), actual.getNumerator());
        assertEquals(expected.getDenominator(), actual.getDenominator());
    }

    @Test
    void minus() {
        Fraction expected = new Fraction(-7, 44);

        Fraction actual = f1.minus(f2);

        assertNotNull(actual);
        assertEquals(expected.getNumerator(), actual.getNumerator());
        assertEquals(expected.getDenominator(), actual.getDenominator());
    }

    @Test
    void multiply() {
        Fraction expected = new Fraction(1, 44);

        Fraction actual = f1.multiply(f2);

        assertNotNull(actual);
        assertEquals(expected.getNumerator(), actual.getNumerator());
        assertEquals(expected.getDenominator(), actual.getDenominator());
    }

    @Test
    void divide() {
        Fraction expected = new Fraction(4, 11);

        Fraction actual = f1.divide(f2);

        assertNotNull(actual);
        assertEquals(expected.getNumerator(), actual.getNumerator());
        assertEquals(expected.getDenominator(), actual.getDenominator());

        Fraction zeroDivide = new Fraction(0, 1);
        assertThrows(ArithmeticException.class, () -> {
            Fraction ex = f1.divide(zeroDivide);
        });
    }

    @Test
    void fractionToString() {
        String expected = "1/3";

        String actual = new Fraction(1, 3).toString();

        assertEquals(expected, actual);
    }

    @Test
    void denominatorEqualsZero() {
        assertThrows(ArithmeticException.class, () -> {
            Fraction ex = new Fraction(1, 0);
        });
    }

    @Test
    void denominatorIsBelowZero() {
        Fraction expected = new Fraction(-1, 2);

        Fraction actual = new Fraction(1, -2);

        assertEquals(expected.getNumerator(), actual.getNumerator());
        assertEquals(expected.getDenominator(), expected.getDenominator());
    }

    @Test
    void numeratorEqualsZero() {
        Fraction expected = new Fraction(0, 1);

        Fraction actual = new Fraction(0, 12);

        assertEquals(expected.getNumerator(), actual.getNumerator());
        assertEquals(expected.getDenominator(), actual.getDenominator());
    }

}