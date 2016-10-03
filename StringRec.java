public class StringRec
{
	// DO NOT DECLARE ANY VARIABLES HERE
	// (you may declare local variables inside methods)

	public static String decompress(String compressedText)
	{
		if (compressedText.length()<=1){
			return compressedText;
		}
		char c = compressedText.charAt(0);

		if (Character.isLetter(c)){
			return compressedText.charAt(0) + decompress(compressedText.substring(1));
		} else {
			if (compressedText.charAt(0) <= '0'){
				return decompress(compressedText.substring(2));
			} else {
				char s = compressedText.charAt(0);
				int x = s - '1';
				compressedText = x + compressedText.substring(1);
				return compressedText.charAt(1) + decompress(compressedText);
			}
		}

	}

	public static void main(String[] args){
		System.out.println(decompress("q9w5e2rt5y4qw2Er3T"));
	}
}
