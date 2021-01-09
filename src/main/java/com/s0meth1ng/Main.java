package com.s0meth1ng;

public class Main {

    public static void main(String[] args) {
        Fraction f1 = new Fraction(2, 12);
        Fraction f2 = new Fraction(2, -15);
        Fraction res = f1.plus(f2);
        System.out.println(res.toString());
    }
}