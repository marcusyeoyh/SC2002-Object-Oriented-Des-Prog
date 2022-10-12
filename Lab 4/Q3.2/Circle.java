import java.lang.Math;
public class Circle implements Shape{
    protected double radius;

    public Circle(double radius){
        this.radius=radius;
    }

    public double getRadius(){
        return this.radius;
    }

    public double calculateArea(){
        return (Math.PI*radius*radius);
    }
}
