public class Average{
	public static void main(String[] args){
		System.out.println("Enter 3 numbers");
		int x = IO.readInt();
		int y = IO.readInt();
		int z = IO.readInt();
		System.out.println("Enter your name");
		String name = IO.readString();
		double average = (x + y + z) / 3;
		IO.outputStringAnswer(name + ", the average of your numbers is " + average);
	}
}