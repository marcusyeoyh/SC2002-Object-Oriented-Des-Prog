import java.lang.Math;
public class Cone implements Shape{
    protected double radius;
    protected double height;

    public Cone(double radius, double height){
        this.radius = radius;
        this.height = height;
    }

    public double getRadius(){
        return this.radius;
    }

    public double getHeight(){
        return this.height;
    }

    public double calculateArea(){
        return (Math.PI*this.radius*(this.radius+Math.sqrt(this.height*this.height+this.radius*this.radius)));
    }
}
