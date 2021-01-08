package com.s0meth1ng;

public class Main {

    public static void main(String[] args) {
        Fraction f = new Fraction(2, 12);
        Fraction f1 = new Fraction(2, -15);
        System.out.println(f.plus(f1).toString());
    }
}