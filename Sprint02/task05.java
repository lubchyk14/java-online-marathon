import java.util.List;

abstract class Shape {
    abstract  double getPerimeter();
}
class Rectang extends  Shape{

    private double height;
    private double width;

    @Override
    public double getPerimeter() {
        return 2*width+2*height;
    }

    public Rectang(double height, double width) {
        this.height = height;
        this.width = width;
    }
}
class Square extends Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }

    @Override
    public double getPerimeter() {
        return 4*width;
    }
}
class MyUtils {
    public double sumPerimeter(List<?> firures) {
        if(firures == null){
            return 0.00;
        }
        double sum = 0;
        for(Object s : firures){
            if(s==null){
                continue;
            }
            sum+=((Shape) s).getPerimeter();
        }
        return sum;
    }
}

