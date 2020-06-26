class ArrayUtil {
    public static  <T> T setAndReturn(T[] arr, T elem, int index){
        
        return arr[index]=elem;
    }

}