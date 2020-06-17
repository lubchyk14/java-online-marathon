
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


interface DrinkReceipt {
    String getName();
    DrinkReceipt addComponent(String componentName, int componentCount);
}
interface DrinkPreparation {
    Map <String,Integer> makeDrink();

}
interface Rating {
    int getRating();
}
class Caffee implements DrinkReceipt, DrinkPreparation, Rating {
    private String name;
    private int rating;
    private Map<String,Integer> ingredients = new HashMap<>();


    public Caffee(String name, int rating) {
        this.name = name;
        this.rating = rating;

    }
    public Map<String,Integer> getComponents(){
        return ingredients;
    }
    public DrinkReceipt addComponent(String componentName, int componentCount){
        ingredients.put(componentName, componentCount);
        return this;
    }

    @Override
    public String getName() {
        return this.name;
    }
    @Override
    public int getRating() {
        return this.rating;
    }
    @Override
    public Map<String, Integer> makeDrink() {
        this.addComponent("Water", 100).addComponent("Arabica", 20);
        return ingredients;
    }

}
class Espresso extends Caffee {
    public Espresso(String name, int rating ) {
        super(name, rating);
    }

    @Override
    public String getName() {

        return super.getName();
    }
    @Override
    public Map<String, Integer> makeDrink() {
        this.addComponent("Water", 50).addComponent("Arabica", 20);
        return this.getComponents();
    }
}
class Cappuccino extends Caffee {
    public Cappuccino(String name, int rating) {
        super(name, rating);
    }

    @Override
    public Map<String, Integer> makeDrink() {
        this.addComponent("Water", 100)
                .addComponent("Arabica", 20)
                .addComponent("Milk", 50);
        return this.getComponents();
    }


}
public class MyUtils {
    public Map<String, Double> averageRating(List<Caffee> coffees) {
        Map<String,Double> answer = new HashMap<>();
        Map<String,ArrayList<Integer>> list = new HashMap<>();

        for (Caffee c : coffees){
            if(list.containsKey(c.getName())){
                Integer sum = list.get(c.getName()).get(0);
                Integer count = list.get(c.getName()).get(1);
                list.put(c.getName(),new ArrayList<Integer>(Arrays.asList(sum+c.getRating(),count+1)));
            }else{
                list.put(c.getName(),new ArrayList<Integer>(Arrays.asList(c.getRating())));
                list.get(c.getName()).add(1);
            }
        }
        for (String s : list.keySet()){
            List <Integer> l = list.get(s);
            answer.put(s, new Double(l.get(0)/l.get(1)));
        }


        return answer;
        
    }
    
}


