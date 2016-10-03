public class TwoSmallest{
	
	public static void main(String[] args){
		System.out.println("Enter a terminanting value:");
		double terminate = IO.readDouble();
		double smallest = 0;
		double smaller = 0;

		System.out.println("Enter a sequence of real numbers:");
		double value = IO.readDouble();
		
		while (value == terminate){
			IO.reportBadInput();
			System.out.println("There are not enough numbers to determine the two smallest. Enter more numbers in the sequence: ");
			value = IO.readDouble();
		}

		int count;
		for (count = 1; value != terminate; count ++){
			if (count == 1){
				smallest = value;
			} else if (count == 2){
				if (value < smallest){
					smaller = smallest;
					smallest = value;
				} else {
					smaller = value;
				}
			} else {
				if (value < smaller && value >= smallest){
					smaller = value;
				} else if (value <= smallest){
				smaller = smallest;
				smallest = value;
				}
			}
			value = IO.readDouble();

			if (count < 2){
				while (value == terminate){
					IO.reportBadInput();
					System.out.println("There are not enough numbers to determine the two smallest. Enter more numbers in the sequence: ");
					value = IO.readDouble();
				}
			} 
		}	

		System.out.println("The smallest two values are: ");
		IO.outputDoubleAnswer(smallest);
		IO.outputDoubleAnswer(smaller);
		
	}

}
