public class PigLatin{
	public static void main(String[] args){
		System.out.println("Enter a string to be translated into Pig Latin:");
		String s = IO.readString();
		IO.outputStringAnswer(translate(s));
	}

	public static String translate(String original){
		if (original.equals("")){
			return "";
		}
		String s = original.toLowerCase();
		if (s.charAt(0) == 'a' || s.charAt(0) == 'e' || s.charAt(0) == 'i' || s.charAt(0) == 'o' || s.charAt(0) == 'u'){
			return s.concat("way");
		} else {
			s = s.substring(1) + s.charAt(0) + "ay";
			return s;
		}
	}
}