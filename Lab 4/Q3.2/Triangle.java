public class Triangle implements Shape{
    protected double height;
    protected double base;

    public Triangle(double height, double base){
        this.height=height;
        this.base=base;
    }

    public double getHeight(){
        return this.height;
    }

    public double getBase(){
        return this.base;
    }

    public double calculateArea(){
        return (0.5*this.height*this.base);
    }
}
