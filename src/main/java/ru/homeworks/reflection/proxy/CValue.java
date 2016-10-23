package ru.palata6.reflection.proxy;

import java.lang.reflect.Method;

/**
 * Created by atonk on 12.10.2016.
 */
class CValue {

    private Method method;
    private Object[] args;

    public CValue(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) return true;
        if (object == null || object.getClass() != getClass()) return false;
        CValue cacheValue=(CValue)object;

        if (cacheValue.method.equals(this.method)) {
            for (int i = 0; i < args.length; i++) {
                Object o = args[i];
                if (!o.equals(cacheValue.args[i])) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result=0;
        for (Object arg : args) {
            result+=arg.hashCode();
        }
        result+=this.method.hashCode()*4;
        return result;
    }
}
