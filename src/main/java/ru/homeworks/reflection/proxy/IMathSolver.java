package ru.homeworks.reflection.proxy;


import java.math.BigInteger;

/**
 * Created by Alexander on 09.10.2016.
 */

public interface IMathSolver {

    BigInteger fibbonacci(int n);

    @Cache
    BigInteger factorial(int n);


}