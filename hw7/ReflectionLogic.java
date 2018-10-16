package hw7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionLogic {

    Class object;
    Method[] methods;
    Object obj;

    public ReflectionLogic(Object object){
        this.obj = object;
        this.object = object.getClass();
        methods = this.object.getDeclaredMethods();
    }

    public Method getMethod(String name){
        for (Method o: methods) {
            if (o.getName().equals(name))
                return o;
        }
        return null;
    }

    public Object methodInvoke(Method method, Object... objects){
        try {
            return method.invoke(obj, objects);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }

    }

//    public Object createObject() {
//        try {
//            return object.newInstance();
//        } catch (InstantiationException | IllegalAccessException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}
