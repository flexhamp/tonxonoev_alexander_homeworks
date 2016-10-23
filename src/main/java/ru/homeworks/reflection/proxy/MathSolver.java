package ru.palata6.reflection.proxy;

import java.math.BigInteger;

/**
 * Created by atonk on 12.10.2016.
 */
public class MathSolver implements IMathSolver {


    @Override
    public BigInteger fibbonacci(int n) {
        BigInteger res;
        if (n == 1 || n == 2) {
            res = BigInteger.valueOf(1);
        } else {
            res = fibbonacci(n - 2).add(fibbonacci(n - 1));
        }
        return res;
    }

    @Override
    public BigInteger factorial(int n) {
        BigInteger res;
        if (n < 0) {
            res = BigInteger.valueOf(1);
        } else {
            if (n < 2) {
                return BigInteger.valueOf(1);
            } else {

                res = factorial(n - 1).multiply(BigInteger.valueOf(n));
            }
        }
        return res;
    }
}


