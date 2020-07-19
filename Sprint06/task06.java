import java.util.function.Predicate;
import java.util.function.BiFunction;
import java.util.ArrayList;
import java.util.List;

class MyUtils{
    public static int findMaxByCondition(List<Integer> numbers, Predicate<Integer> pr) {
        return numbers.stream()
                .filter(pr)
                .mapToInt(i->i)
                .max().getAsInt();

    }

    public static void main(String[] args) {

    }


}


class User {
    public final List<Integer> values = new ArrayList<Integer>();

    int getFilterdValue(BiFunction<List<Integer>,Predicate<Integer>,Integer> a , Predicate<Integer> pred){
        return a.apply(values,pred);

    }

    int getMaxValueByCondition(Predicate<Integer> predicate) {
        BiFunction<List<Integer>,Predicate<Integer>,Integer> ra = MyUtils::findMaxByCondition;
        return getFilterdValue(ra,predicate);

    }
}