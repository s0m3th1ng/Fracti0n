package com.s0meth1ng;

public interface IFraction {
    Fraction plus(Fraction fraction);
    Fraction plus(int n);
    Fraction minus(Fraction fraction);
    Fraction minus(int n);
    Fraction multiply(Fraction fraction);
    Fraction multiply(int n);
    Fraction divide(Fraction fraction);
    Fraction divide(int n);
}