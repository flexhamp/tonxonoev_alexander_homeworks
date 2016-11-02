package ru.homeworks.reflection.part1;

/**
 * Created by atonk on 12.10.2016.
 */
public class SimpleParentClass {
    private int simplePrivateParentValue;
    public double simplePublicParentValue;

    public SimpleParentClass() {
        simplePrivateParentValue=1;
        simplePublicParentValue=2.0;
    }

    public SimpleParentClass(int simplePrivateParentValue, double simplePublicParentValue) {
        this.simplePrivateParentValue = simplePrivateParentValue;
        this.simplePublicParentValue = simplePublicParentValue;
    }

    private int getSimplePrivateParentValue(){
        return  simplePrivateParentValue;
    }
    public void printSomethingSimple(String str){
        System.out.println(str);
    }
}