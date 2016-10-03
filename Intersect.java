public class Intersect{
	public static void main(String[] args){
		System.out.println("Enter the constant d:");
		double d = IO.readDouble();
		System.out.println("Enter the constant f:");
		double f = IO.readDouble();
		System.out.println("Enter the constant g:");
		double g = IO.readDouble();
		System.out.println("Enter the constant m:");
		double m = IO.readDouble();
		System.out.println("Enter the constant b:");
		double b = IO.readDouble();

		//mx + b = dx^2 + fx + g
		//Quadratic Equation: (-b(+/-)sqrt(b^2-4ac))/2a
		
		double x1 = ((m-f)+Math.sqrt((f-m)*(f-m)-4*d*(g-b)))/(2*d);
		double x2 = ((m-f)-Math.sqrt((f-m)*(f-m)-4*d*(g-b)))/(2*d);
		double y1 = m*x1+b;
		double y2 = m*x2+b;

		//Error Conditions: affected by d=0 and discriminant, b^2-4ac
		if (d == 0 && f != m) {
			System.out.println("The intersection is:");
			System.out.println("(" + (b-g)/(f-m) + ", " + (m*(b-g)/(f-m) + b) + ")");
		} else if (d == 0 && f == m && g == b) {
			System.out.println("There are infinitely many intersections along the line:");
			System.out.println("y = " + m + "x + " + b);
		} else if (d == 0 && f == m && g != b) {
			System.out.println("There are no intersections.");
		} else if ((f-m)*(f-m)-4d*(g-b) < 0) {
			System.out.println("There are no intersections.");
		} else if ((f-m)*(f-m)-4d*(g-b) == 0) {
			System.out.println("The intersection is: ");
			System.out.println("(" + x1 + ", " + y1 + ")");
		} else if ((f-m)*(f-m)-4d*(g-b) > 0) {
		System.out.println("The intersections are:");
		System.out.println("(" + x1 + ", " + y1 + ")");
		System.out.println("(" + x2 + ", " + y2 + ")");
		}
	}	
}