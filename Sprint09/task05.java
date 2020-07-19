import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class MyUtils {


    public Stream<String> nameList(Map<String, Stream<String>> map) {

        if (map == null) {
            throw new NullPointerException();
        }
        Stream<String> fin = Arrays.stream(new String[]{});
        for (Stream<String> curr : map.values()) {
            if (curr != null) {
                fin = Stream.concat(fin, curr);

            }
        }

        return fin.filter(a -> a != null && !a.isEmpty() && !a.matches("\\s"))
                .map(a -> a.replaceAll("\\s", ""))
                .map(a -> {
                    a = a.toLowerCase();
                    if (a.length() > 1) {
                        return a.substring(0, 1).toUpperCase() + a.substring(1);
                    } else {
                        return "";
                    }
                })
                .filter(s -> s.length() > 0)
                .distinct()
                .sorted();
    }
}