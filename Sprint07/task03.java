

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface TestSuite {
    String [] value();
}
class TestSuitHandler{
    public static void  run(Class<?> clazz){
        if(clazz.getAnnotation(TestSuite.class)==null){
            System.out.println("Class "+ clazz.getName()+" isn't annotated");
            return;
        }
        for(String s :clazz.getAnnotation(TestSuite.class).value()){
            try {
                Method method = clazz.getMethod(s);
                if(method.getParameterCount()==0){

                    System.out.println(String.format("\t -- Method %s.%s started --",clazz.getName(),s));
                    Object o =method.invoke(clazz.getDeclaredConstructor().newInstance());
                    
                    
                    System.out.println(String.format("\t -- Method %s.%s finished --",clazz.getName(),s));
                }

            } catch (NoSuchMethodException e) {
                System.out.println(String.format("Method with name %s doesn't exists " +
                        "or not public in class %s",s,clazz.getName()));;
            } catch (IllegalAccessException e) {
                System.out.println(String.format("Method with name %s doesn't exists " +
                        "or not public in class %s",s,clazz.getName()));;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    
}


