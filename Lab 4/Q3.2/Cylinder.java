import java.lang.Math;
public class Cylinder implements Shape{
    protected double radius;
    protected double height;
    
    public Cylinder(double radius,double height){
        this.radius=radius;
        this.height=height;
    }

    public double getHeight(){
        return this.height;
    }

    public double getRadius(){
        return this.radius;
    }

    public double calculateArea(){
        return (2*Math.PI*this.radius*this.height+2*Math.PI*this.radius*this.radius);
    }
}
