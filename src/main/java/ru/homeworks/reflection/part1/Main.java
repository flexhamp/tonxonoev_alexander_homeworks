package ru.homeworks.reflection.part1;

import ru.homeworks.reflection.proxy.SimpleClass;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by Alexander on 08.10.2016.
 */
public class Main {
    public static void main(String[] args) {
        SimpleClass simpleClass=new SimpleClass();
        getMethods(simpleClass.getClass());
        getGetters(simpleClass.getClass());
        try {
            boolean result=checkStrings(simpleClass);
            System.out.println(result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }
    public static void getMethods(Class<?> clazz){
        System.out.println("_----_Методы класса, включая родительские_---_");
        System.out.println(clazz.getName());
        Method[] methods=clazz.getDeclaredMethods();
        for (Method method: methods){
            System.out.println(method);
        }

        Class<?> tmpClazz=clazz.getSuperclass();
        while (tmpClazz!=null){
            methods=tmpClazz.getDeclaredMethods();
            for (Method method: methods){
                System.out.println(method);
            }
            tmpClazz=tmpClazz.getSuperclass();
        }
    }

    public static void getGetters(Class<?> clazz){
        System.out.println("_------_Геттеры_------_");
        System.out.println(clazz.getName());
        Method[] methods=clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().substring(0,3).contains("get")){
                System.out.println(method);
            }
        }
    }

    public static <T> boolean checkStrings(T type) throws IllegalAccessException {
        Class<?> clazz=type.getClass();
        System.out.println("_------_Проверка константных строк_------_");
        System.out.println(clazz.getName());
        Field[] fields=clazz.getDeclaredFields();
        for (Field field: fields){
            int fieldModifier=field.getModifiers();
            field.setAccessible(true);
            if (Modifier.isStatic(fieldModifier)
                    && Modifier.isFinal(fieldModifier)
                    && field.getType().getName().equals(String.class.getName())){
                if (field.get(type).equals(field.getName())){
                    System.out.println(field);

                } else
                {
                    return false;
                }
            }
        }
        return true;
    }
}
