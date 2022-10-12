public class Rectangle {
    protected double length;
    protected double breath;

    public Rectangle(double length, double breath){
        this.length = length;
        this.breath=breath;
    }

    public double getLength(){
        return this.length;
    }

    public double getBreath(){
        return this.breath;
    }

    public double calculateArea(){
        return (length*breath);
    }
}
