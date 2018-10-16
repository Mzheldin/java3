package hw7;

import java.lang.reflect.Method;

public class Tests {

    static ReflectionLogic rL;
    private Object object;

    public Tests(Object object){
        this.object = object;
    }

    public static void start(Class object){
//        Method[] methods = this.getClass().getDeclaredMethods();
//        for (Method m:methods) if (m.isAnnotationPresent(BeforeSuite.class)) m.invoke();
//        for (int i = 1; i < 11; i++){
//            for (Method m:methods) {
//                if ((m.isAnnotationPresent(Test.class)) && (m.getAnnotation(Test.class).priority() == i)) m.invoke()
//            }
//        }
//        for (Method m:methods) if (m.isAnnotationPresent(AfterSuite.class)) m.invoke();
    }

    @BeforeSuite
    public void before(){}

    @Test(priority = 1)
    public void testEquals(Object obj, String name, Object... objects){
        rL = new ReflectionLogic(object);
        Method method = rL.getMethod(name);
        if (method == null) {
            System.out.println("Неверное название метода");
            return;
        }
        if (method.getReturnType().toString().equals("void")) {
            System.out.println("Метод не возвращает значения");
            return;
        }
        if (obj.equals(rL.methodInvoke(method, objects))) System.out.println("Тест пройден");
        else System.out.println("Результат не соответвтует ожидаемому");
    }

    @Test(priority = 2)
    public void testBool(boolean bool, String name, Object... objects){
        rL = new ReflectionLogic(object);
        Method method = rL.getMethod(name);
        if (method == null) {
            System.out.println("Неверное название метода");
            return;
        }
        if (!(method.getReturnType().toString().equals("boolean"))) {
            System.out.println("Метод не возвращает логического значения");
            return;
        }
        if (bool == (boolean)(rL.methodInvoke(method, objects))) System.out.println("Тест пройден");
        else System.out.println("Результат не соответвтует ожидаемому");
    }

    @AfterSuite
    public void after(){}

}
