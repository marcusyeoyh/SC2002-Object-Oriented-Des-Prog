
public class Cuboid extends Rectangle{
    protected double height;
    public Cuboid(double length, double breath, double height){
        super(length, breath);
        this.height=height;
    }

    public double getLength(){
        return super.length;
    }

    public double getBreath(){
        return super.breath;
    }

    public double calculateArea(){
        return (2*super.length*super.breath+2*super.length*this.height+2*super.breath*this.height);
    }
}
