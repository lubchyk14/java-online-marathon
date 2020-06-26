
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

class ArrayUtil {

    public static <T extends Number> double averageValue(Array<T> arr){
        if(arr == null){
            throw new NullPointerException();
        }
        double sum=0;
        for(int i=0;i<arr.length();i++){
            sum+=arr.get(i).doubleValue();

        }
        
        return sum/arr.length();
    }
}
