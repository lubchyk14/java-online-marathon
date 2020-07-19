

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

class Person{
    String name;

    Person(String name){
        this.name = name;
    }

    DecisionMethod goShopping = (t,r)->{
        if(t.equals("product1") && r>10){
            return  true;
        }else {
            return false;
        }
    };
    public static void main(String[] args) {

    }
}
@FunctionalInterface
interface DecisionMethod {
    public boolean  decide (String t,int r );

}

class Shop{
    public List<DecisionMethod> clients = new ArrayList<>();

    public int sale(String product, int percent) {
        return (int)clients.stream().filter(a->a.decide(product,percent)).count();
    }


}
