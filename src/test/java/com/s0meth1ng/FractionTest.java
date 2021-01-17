package com.s0meth1ng;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FractionTest {

    private static final Fraction f1 = new Fraction(22, 242);
    private static final Fraction f2 = new Fraction(1, 4);
    public static final int num = 3;

    @Test
    void normalize() {
        Fraction expected = new Fraction(1, 11);

        Fraction actual = new Fraction(22, 242);
        actual.normalize();
        assertEquals(expected, actual);

        expected.normalize();
        assertEquals(expected, actual);
    }

    @Test
    void normalizeWhenNumeratorEqualsZero() {
        Fraction expected = new Fraction(0, 1);

        Fraction actual = new Fraction(0, 12);
        actual.normalize();

        assertEquals(expected, actual);
    }

    @Test
    void setDenominatorBelowZero() {
        Fraction expected = new Fraction(1, 2);

        Fraction actual = new Fraction(1, -2);
        actual.setDenominator(-2);

        assertEquals(expected, actual);
    }

    @Test
    void plus() {
        Fraction expected = new Fraction(44 + 121, 484);
        operationTestForFraction(expected, f1::plus);

        expected = new Fraction(22 + 242*3, 242);
        operationTestForInt(expected, f1::plus);
    }

    @Test
    void minus() {
        Fraction expected = new Fraction(44 - 121, 484);
        operationTestForFraction(expected, f1::minus);

        expected = new Fraction(22 - 242*3, 242);
        operationTestForInt(expected, f1::minus);
    }

    @Test
    void multiply() {
        Fraction expected = new Fraction(22 * 1, 242 * 4);
        operationTestForFraction(expected, f1::multiply);

        expected = new Fraction(22 * 3, 242);
        operationTestForInt(expected, f1::multiply);
    }

    @Test
    void divide() {
        Fraction expected = new Fraction(22 * 4, 242 * 1);
        operationTestForFraction(expected, f1::divide);

        expected = new Fraction(22, 242 * 3);
        operationTestForInt(expected, f1::divide);

        Fraction zeroDivide = new Fraction(0, 1);
        assertThrows(ArithmeticException.class, () -> f1.divide(zeroDivide));

        int zero = 0;
        assertThrows(ArithmeticException.class, () -> f1.divide(zero));
    }

    @FunctionalInterface
    interface OperationFraction {
        Fraction operation(Fraction f);
    }
    void operationTestForFraction(Fraction expected, OperationFraction test) {
        Fraction actual = test.operation(f2);
        assertNotNull(actual);
        assertEquals(expected, actual);

        //NPE or IAE depends on configuration. That's why Exception is used
        //https://stackoverflow.com/questions/40847472/why-nonnull-annotation-checked-at-runtime
        assertThrows(Exception.class, () -> test.operation(null));
    }

    @FunctionalInterface
    interface OperationInt {
        Fraction operation(int n);
    }
    void operationTestForInt(Fraction expected, OperationInt test) {
        Fraction actual = test.operation(num);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void fractionToString() {
        String expected = "1/3";

        String actual = new Fraction(1, 3).toString();

        assertEquals(expected, actual);
    }

    @Test
    void denominatorEqualsZero() {
        assertThrows(ArithmeticException.class, () -> new Fraction(1, 0));
    }

    @Test
    void denominatorIsBelowZero() {
        Fraction expected = new Fraction(-1, 2);

        Fraction actual = new Fraction(1, -2);

        assertEquals(expected, actual);
    }

    @Test
    void fractionEquality() {
        assertEquals(f1, f1);

        Fraction expected = new Fraction(22, 242);
        assertEquals(expected, f1);
    }

    @Test
    void fractionNonEquality() {
        Object unexpected = new Fraction(22, 243);
        assertNotEquals(unexpected, f1);

        unexpected = new Fraction(23, 242);
        assertNotEquals(unexpected, f1);

        unexpected = new Fraction(23, 243);
        assertNotEquals(unexpected, f1);

        unexpected = new Fraction(11, 121);
        assertNotEquals(unexpected, f1);

        unexpected = new Fraction(44, 484);
        assertNotEquals(unexpected, f1);

        unexpected = null;
        assertFalse(f1.equals(unexpected));

        unexpected = 0;
        assertFalse(f1.equals(unexpected));
    }

    @Test
    void intEquality() {
        int expected = 1;
        Fraction actual = new Fraction(9, 9);
        assertTrue(actual.equals(expected));

        expected = 5;
        actual = new Fraction(45, 9);
        assertTrue(actual.equals(expected));

        expected = -3;
        actual = new Fraction(-27, 9);
        assertTrue(actual.equals(expected));
    }

    @Test
    void intNonEquality() {
        int unexpected = 1;
        Fraction actual = new Fraction(8, 9);
        assertFalse(actual.equals(unexpected));
        actual = new Fraction(10, 9);
        assertFalse(actual.equals(unexpected));

        unexpected = 3;
        actual = new Fraction(8, 3);
        assertFalse(actual.equals(unexpected));
        actual = new Fraction(10, 3);
        assertFalse(actual.equals(unexpected));
        actual = new Fraction(12, 3);
        assertFalse(actual.equals(unexpected));

        unexpected = -4;
        actual = new Fraction(-9, 2);
        assertFalse(actual.equals(unexpected));
        actual = new Fraction(-7, 2);
        assertFalse(actual.equals(unexpected));
    }

    @Test
    void hashEquality() {
        assertEquals(f1.hashCode(), f1.hashCode());

        Fraction expected = new Fraction(22, 242);
        assertEquals(expected.hashCode(), f1.hashCode());
    }
}