// Write your code here
public class Product {
    private String name;
    private double price;
    private static int counter ;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        counter++;
    }

    public Product() {
        counter++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public static int count(){
        return counter;
    }
}
