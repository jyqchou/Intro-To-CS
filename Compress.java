public class Compress{
	public static void main(String[] args){
		System.out.println("Enter a string:");
		IO.outputStringAnswer(compress(IO.readString()));
	}

	public static String compress(String original){
		original = original.concat("4");
		String s = "";
		int count = 1;
		for (int i=0; i<original.length() && original.charAt(i) != '4'; i++){
			if (original.charAt(i) == original.charAt(i+1)){
				count ++;
			} else {
				if (count == 1){
					s = s + original.charAt(i);
					count = 1;
				} else {
					s = s + count + original.charAt(i);
					count = 1;
				}
			} 
		} return s;
	}
}