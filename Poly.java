public class Poly{
	public static void main(String[] args){
		System.out.println("Enter the first root:");
		int r1 = IO.readInt();
		System.out.println("Enter the second root:");
		int r2 = IO.readInt();
		System.out.println("Enter the third root:");
		int r3 = IO.readInt();
		//(x-r1)(x-r2) = x^2 - r2*x - r1*x + r1*r2 = ax^2 + bx + c
		int a = 1;
		int b = ((-1)*(r1+r2));
		int c = (r1 * r2);
		//(ax^2 + bx + c) (x - r3) = ax^3 + bx^2 + cx + -r3*ax^2 + -r3*bx + -r3*c = ax^3 + bx^2 + cx + d
		int ax3 = 1;
		int bx2 = b - r3;
		int cx = c + (b * (-1)*r3);
		int d = c * (-1)*r3;;
		System.out.println("x^3 + " + bx2 + "x^2 + " + cx + "x + " + d);
	}
}