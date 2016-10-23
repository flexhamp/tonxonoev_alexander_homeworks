package ru.homeworks.reflection.proxy.beanUtils;

/**
 * Created by atonk on 12.10.2016.
 */



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alexander on 11.10.2016.
 */
public class BeanUtils {
    /**
     * *	Scans object "from" for	all	getters.
     * If object "to"
     * contains correspondent setter, it will invoke it
     * to set property value for "to" which	equals to the property
     * of "from".
     * <p/>
     * The	type in	setter should be compatible	to the value returned
     * by getter (if not, no invocation performed).
     * Compatible means that parameter	type in setter should
     * be the same	or be superclass of	the	return type	of	the	getter.
     * <p/>
     * The method takes care only about public methods.
     *
     * @param to   Object which properties will be set.
     * @param from Object which properties	will be	used to	get	values.
     **/
    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        Class<?> sender = from.getClass();
        Class<?> receiver = to.getClass();

        Method[] methodsFromSender = sender.getMethods(); // только паблик методы
        Method[] methodsFromRecivier = receiver.getMethods();

        // cоздадим листы геттеров и сеттеров
        ArrayList<Method> gettersFromSender = new ArrayList<>();
        ArrayList<Method> settersFromReceiver = new ArrayList<>();
        for (Method method : methodsFromSender) {
            if (method.getName().substring(0, 3).contains("get")) {
                gettersFromSender.add(method);
                // System.out.println(method.getReturnType()+" "+method.getName());
            }

        }
        System.out.println("SET RECEIVER");
        for (Method method : methodsFromRecivier) {
            if (method.getName().substring(0, 3).contains("set")) {
                settersFromReceiver.add(method);
                // System.out.println(method.getReturnType()+" "+method.getName());
            }
        }


        // сравниваем геттеры сендера и сеттеры ресивера
        // для каждого геттера будем искать подходящий сеттер
        for (Method getter : gettersFromSender) {
            for (Method setter : settersFromReceiver) {
                if (getter.getName().substring(3).equals(setter.getName().substring(3))
                        && (getter.getReturnType().equals(setter.getParameterTypes()[0]) ||
                        getter.getReturnType().equals(setter.getParameterTypes()[0].getSuperclass()))) {

                    Object o = getter.invoke(from, null);
                    setter.invoke(to, o);
                }
            }
        }


    }

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Receiver receiver = new Receiver("Sample", 200, false);
        Sender sender = new Sender("USER", 100, true);

        System.out.println("RECEIVER");
        System.out.println(receiver);
        System.out.println("SENDER");
        System.out.println(sender);

        assign(receiver, sender);


        System.out.println("RECEIVER");
        System.out.println(receiver);
        System.out.println("SENDER");
        System.out.println(sender);
    }
}