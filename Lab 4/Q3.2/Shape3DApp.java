import java.util.Scanner;
public class Shape3DApp {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int size, select;
        double a, b, c, totalArea = 0;

        System.out.println("Please input the number of shapes");
        size = scan.nextInt();
        for(int i = 0; i<size; i++){
            System.out.println("Please select the shape");
            System.out.println("Pyramid-------(1)");
            System.out.println("Sphere----------(2)");
            System.out.println("Cuboid---------(3)");
            System.out.println("Cone---------(4)");
            System.out.println("Cylinder---------(5)");
            select = scan.nextInt();
            switch(select){
                case 1:
                System.out.println("Please input the height and base of the Pyramid");
                a = scan.nextDouble();
                b = scan.nextDouble();
                Pyramid pyramid = new Pyramid(a,b);
                totalArea = totalArea + pyramid.calculateArea();
                break;
                case 2:
                System.out.println("Please input the radius of the Sphere");
                a = scan.nextDouble();
                Sphere sphere = new Sphere(a);
                totalArea = totalArea + sphere.calculateArea();
                break;
                case 3:
                System.out.println("Please input the length, breath and height of the Cuboid");
                a = scan.nextDouble();
                b = scan.nextDouble();
                c = scan.nextDouble();
                Cuboid cuboid = new Cuboid(a,b,c);
                totalArea = totalArea + cuboid.calculateArea();
                break;
                case 4:
                System.out.println("Please input the radius and height of the Cone");
                a = scan.nextDouble();
                b = scan.nextDouble();
                Cone cone = new Cone(a,b);
                totalArea = totalArea + cone.calculateArea();
                break;
                case 5:
                System.out.println("Please input the radius and height of the Cylinder");
                a = scan.nextDouble();
                b = scan.nextDouble();
                Cylinder cylinder = new Cylinder(a,b);
                totalArea = totalArea + cylinder.calculateArea();
                break;
            }
        }
        System.out.println("\nThe combined area is: " + totalArea);
    }
}
