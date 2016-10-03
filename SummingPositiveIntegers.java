public class SummingPositiveIntegers{
	public static void main(String[] args){
		System.out.println("Enter number:");
			int a = IO.readInt();
			int b = 0;
		while (a >= 0){
			b = b + a;
			System.out.println("Enter number:");
			a = IO.readInt();
		}
		System.out.println("The sum of all entered non-negative numbers is:");
		System.out.println(b);
	}
}