package ru.palata6.reflection.proxy;

import java.lang.reflect.Proxy;
import java.math.BigInteger;

/**
 * Created by Alexander on 10.10.2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        IMathSolver iMathSolver1=new MathSolver();
        IMathSolver iMathSolver=(IMathSolver)
                Proxy.newProxyInstance(Main.class.getClassLoader(),
                        new Class[]{IMathSolver.class}, new MyProxy(iMathSolver1));


        BigInteger result =iMathSolver.fibbonacci(3);
        System.out.println(result);
        result=iMathSolver.fibbonacci(4);
        System.out.println(result);
        result=iMathSolver.fibbonacci(5);
        System.out.println(result);
        result=iMathSolver.fibbonacci(6);
        System.out.println(result);
        result=iMathSolver.fibbonacci(6);
        System.out.println(result);

        result=iMathSolver.factorial(250);
        System.out.println(result);

        result=iMathSolver.factorial(250);
        System.out.println(result);

    }
}