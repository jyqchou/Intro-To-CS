public class WordCount{
	public static void main(String[] args){
		System.out.println("Enter a string:");
		String s = IO.readString();
		System.out.println("Enter the a minimum length: ");
		int min = IO.readInt();
		IO.outputIntAnswer(countWords(s, min));
	}

	public static int countWords(String original, int minLength){
		if (minLength == 0){
			minLength = 1;
		}
		original = original.concat(" ");
		int wCount = 0;
		int lCount = 0;
		for (int i=0; i<original.length(); i++){
			if (Character.isLetter(original.charAt(i))){
				lCount ++;
			} else if (original.charAt(i) == ' '){
				if (lCount >= minLength){
					wCount ++;
				} lCount = 0;
			}
		} return wCount;
	}
}