import java.lang.Math;
public class Sphere extends Circle{
    public Sphere(double radius){
        super(radius);
    }

    public double getRadius(){
        return super.radius;
    }

    public double calculateArea(){
        return (4*Math.PI*super.radius*super.radius);
    }
}
