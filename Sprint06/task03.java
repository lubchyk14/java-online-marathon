import java.util.function.BinaryOperator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    static BinaryOperator <String> greetingOperator = (p1, p2)->{
        return "Hello "+p1+" "+p2+"!!!";
    };

    public static List<String> createGreetings(List<Person> people, BinaryOperator<String> operator){
        return people.stream()
                .map((Person p )->{ return  operator.apply(p.name,p.surname); })
                .collect(Collectors.toList());

    }
}