package ru.homeworks.reflection.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by atonk on 12.10.2016.
 */
public class MyProxy implements InvocationHandler {
    private final Object delegate;
    private Map<CValue, Object> cacheValues = new HashMap();

    public MyProxy(Object delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res=null;
        if (method.isAnnotationPresent(Cache.class)){
            System.out.println("Метод маркирован @Cache");
            CValue cValue=new CValue(method,args);
            if (cacheValues.containsKey(cValue)){
                res=cacheValues.get(cValue);
            }

            if (res==null){

                res=method.invoke(delegate, args);
                cacheValues.put(cValue, res);
            } else {
                System.out.println("[Значение было получено из кэша]");
            }
        } else {
            res=method.invoke(delegate, args);
        }
        return res;
    }
}
