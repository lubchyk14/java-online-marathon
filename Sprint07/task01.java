import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CamelCase{

}
class CheckCamelCase{
    public static final String CAMELCASE_PATTERN = "[a-z]+[A-Z]{0,1}[a-z]*[A-Z]{0,1}[a-z]*";
    public static boolean checkAndPrint(Class clazz){
        boolean ans = true;
        for (Method method : clazz.getMethods()){
            if(method.getAnnotation(CamelCase.class)!=null &&
                    ! method.getName().matches(CAMELCASE_PATTERN)){
                System.out.println(String.format("method %s.%s doesn't satisfy camelCase " +
                                "naming convention", clazz.getName(),method.getName()));
                ans = false;
            }
        }
        return ans;

    }

}
class ClassForAnnot {
    @CamelCase
    public static void example() {
    }

    @CamelCase
    public void Example() {
    }

    public static void _main(String args[]) {
    }
}
public class Class1{
    @CamelCase
    public void correct(){}
    @CamelCase
    public void InCorrect(){}
    @CamelCase
    public void JustMethod(){}
}

public class Class2{
    @CamelCase
    public void correct(){}
    @CamelCase
    public void oneMoreCorrect(){}
}