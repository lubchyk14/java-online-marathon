
import java.util.Arrays;
import java.util.function.Predicate;

public class MyUtils  {

    public static int getCount(int[] arr,Predicate<Integer> pred ) {
        Integer[] array = Arrays.stream( arr ).boxed().toArray( Integer[]::new );
        int i = (int)Arrays.stream(array).filter(pred).count();
        return i;
        // Write your code here

    }
}