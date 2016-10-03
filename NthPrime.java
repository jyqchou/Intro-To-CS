public class NthPrime{

	public static boolean Prime(int x){
		if (x == 0 || x == 1){
			return false;
		} else if (x == 2){
			return true;
		} else if (x > 2){
			for (int count = 2; count < x; count ++){
				if (x%count == 0){
					return false;	
				} 
			} 
		} return true;
	}  

	public static void main(String[] args){
		System.out.println("Enter a value for n to compute the nth prime number: ");
		int n = IO.readInt();
		int count1 = 1;
		int count2 = 2;

		while (n <= 0){
			IO.reportBadInput();
			System.out.println("Enter a value greater than 0 for n: ");
			n = IO.readInt();
		} 

		while (count1 < n){
			count2 ++;
			if (NthPrime.Prime(count2)){
				count1 ++;
			}
		}
		
		if (n % 10 == 1 && n%100 != 11){
			System.out.println("The " + n + "st prime number is: ");
		IO.outputIntAnswer(count2);	
		} else if (n % 10 == 2 && n%100 != 12){
			System.out.println("The " + n + "nd prime number is: ");
		IO.outputIntAnswer(count2);	
		} else if (n % 10 == 3 && n%100 != 13){
			System.out.println("The " + n + "rd prime number is: ");
		IO.outputIntAnswer(count2);	
		} else {
			System.out.println("The " + n + "th prime number is: ");
		IO.outputIntAnswer(count2);	
		}
	}
}