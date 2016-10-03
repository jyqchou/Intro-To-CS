public class Pyramid{
	public static void main(String[] args){

		System.out.println("Enter a number:");
		int n = IO.readInt();

		for (int j = 1; j <= n; j++){
			for (int space = n-j; space > 0; space--){
				System.out.print(" ");	
			} 
			for (int i = 1; i<=2*2j-1; i++){
				System.out.print("*");
			} System.out.println();
		} 
	}
}