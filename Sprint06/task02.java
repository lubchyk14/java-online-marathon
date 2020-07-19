import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.*;
public class App {
    static Consumer <double []> cons = (double[] array)-> {
        for(int i =0; i<array.length;i++){
            if (array[i]>2){
                array[i]=array[i]*0.8;
            }else{
                array[i]=array[i]*0.9;
            }
        }
    };

    public static double[] getChanged(double[] initialArray,Consumer<double[]> consumer) {
        double [] arr = new double[initialArray.length]; 
        System.arraycopy(initialArray,0,arr,0,arr.length);
        
        consumer.accept(arr);
        return  arr;

    }
}