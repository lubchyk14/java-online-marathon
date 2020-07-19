import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyUtils {
    public Stream<Integer> duplicateElements(Stream<Integer> stream) {
        
        Set<Integer> set = new HashSet<>();
        return stream.filter(s-> s!=null && !set.add(s))
                .distinct()
                .sorted();

    }
}