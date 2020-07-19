
import java.util.Set;
import java.util.function.Predicate;

class MyUtils {
    static Predicate<Integer> getPredicateFromSet(Set<Predicate<Integer>> set ){
        return set.stream().reduce(Predicate::and).get();
    }
}