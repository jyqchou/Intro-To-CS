public class Scores{
	
	public static void main(String[] args){
		System.out.println("Enter the number of judges on the panel:");
		int judges = IO.readInt();
		double topScore = 0;
		double bottomScore = 0; 
		double newScore = 0;
		double total = 0;

		while (judges <= 2){
			IO.reportBadInput();
			System.out.println("The number of judges must be at least 3. Please reenter the number of judges: ");
			judges = IO.readInt();
		} for (int scoreCount = 1; scoreCount <= judges; scoreCount ++){
			System.out.println("Enter the score given by judge: ");
			newScore = IO.readDouble();
			while (newScore < 0 || newScore > 10){
				IO.reportBadInput();
				System.out.println("Please enter a score between 0.0 and 10.0.");
				newScore = IO.readDouble();
			}
			total = total + newScore;	
			if (scoreCount == 1){
				topScore = newScore;
			} else if (scoreCount == 2){
				if (newScore > topScore){
					bottomScore = topScore;
					topScore = newScore;
				} else {
					bottomScore = newScore;
				}
			} else {
				if (newScore < bottomScore){
					bottomScore = newScore;
				} else if (newScore > topScore){
					topScore = newScore;
				}
			}
		}
		double average = ((total - topScore - bottomScore)/(judges - 2));
		System.out.println("The average score is:");
		IO.outputDoubleAnswer(average);
	}
}