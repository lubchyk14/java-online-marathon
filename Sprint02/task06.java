

import java.util.*;

abstract class Shape  {
    private String name;

    public abstract double  getArea();

    public Shape(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shape)) return false;
        Shape shape = (Shape) o;
        return Objects.equals(name, shape.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
class Circle extends Shape implements Comparable<Circle> {
    private double radius;



    public Circle(String name, double radius) {
        super(name);
        this.radius = radius;
    }
    public double getRadius() {
        return radius;
    }

    @Override
    public int compareTo(Circle o) {
        Double th = getArea();
        Double t = o.getArea();
        return  t.compareTo(th);
    }

    @Override
    public double getArea() {
        return Math.PI*Math.pow(radius,2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Circle)) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 
                && this.getName().compareTo(circle.getName())==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius,getName());
    }
}
class Rectangle extends Shape implements Comparable<Rectangle> {
    private double height;
    private double width;

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public Rectangle(String name, double height, double width) {
        super(name);
        this.height = height;
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.height, height) == 0 &&
                Double.compare(rectangle.width, width) == 0 &&
                this.getName().compareTo(rectangle.getName())==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width,getName());
    }

    @Override
    public int compareTo(Rectangle o) {
        Double th = getArea();
        Double ta = o.getArea();
        return  ta.compareTo(th);
    }

    @Override
    public double getArea() {
        return 2*width+2*height;
    }
    // Code
}
public class MyUtils {
    public List<Shape> maxAreas(List<Shape> shapes) {
        if(shapes.size()==1){return shapes;}
        Queue<Circle> qCircle = new PriorityQueue<>();
        Queue<Rectangle> qRect = new PriorityQueue<>();
        for (Shape s : shapes){
            if(s.getName().equals("Circle")){
                qCircle.add((Circle)s);
            }else{
                qRect.add((Rectangle)s);
            }
        }
        List<Shape> fin = new ArrayList<>();
        Circle maxCircle = qCircle.poll();
        Rectangle maxRect = qRect.poll();
        for(Shape s : shapes){
            if(s.getName().equals("Circle") && s.getArea()==maxCircle.getArea()){
                fin.add(s);
            }
            if(s.getName().equals("Rectangle") && s.getArea()==maxRect.getArea()){
                fin.add(s);
            }
        }
    return fin;

    }
}

