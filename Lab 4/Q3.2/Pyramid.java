import java.lang.Math;
public class Pyramid extends Triangle{
    double area=0, slantheight;
    public Pyramid(double height, double base){
        super(height, base);
    }

    public double getHeight(){
        return super.height;
    }

    public double getBase(){
        return super.base;
    }

    public double calculateArea(){
        area=area + base*base;
        slantheight = Math.sqrt(0.5*super.base*0.5*super.base+super.height*super.height);
        area = area + 4*0.5*super.base*slantheight;
        return area;
    }
}
