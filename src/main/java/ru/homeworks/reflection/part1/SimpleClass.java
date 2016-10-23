package ru.homeworks.reflection.proxy;

/**
 * Created by atonk on 12.10.2016.
 */
public class SimpleClass {
    static final String SUNDAY = "SUNDAY";
    public static final String MONDAY = "MONDAY";
    public static final String TUESDAY = "TUESDAY";
    private static final String WEDNESDAY = "WEDNESDAY";
    private static final String THURSDAY="THURSDAY";
    protected static final String FRIDAY = "FRIDAY!";
    protected static final String SATURDAY="SATUDRAY!";

    private int simplePrivate(int a){
        System.out.println("simplePrivate return:"+a);
        return a;
    }

    public void simplePublic(){
        System.out.println("simplePublic have  printed this.");
    }

    public static String getMONDAY() {
        return MONDAY;
    }

    public static String getTUESDAY() {
        return TUESDAY;
    }
}
