public class Blackjack{
	public static void main(String[] args){
		int choice = 0;
		Deck deck = new Deck(1);
		int multiplicity = 0; // number of complete decks to play with
		int players = 0;
		Card dealt; // card most recently dealt
		int ace = 0; // number of aces in hand
		int ace2 = 0; // number of aces in split deck
		int temp = 0;
		int count = 2; // number of cards in hand

		System.out.println("Enter how many complete decks you wish to play with: ");
		multiplicity = IO.readInt();
		deck = new Deck(multiplicity);
		deck.shuffle();

		/* while (choice != 4){
			System.out.println("Menu");
			System.out.println("1. Shuffle Deck");
			System.out.println("2. Deal cards");		
			System.out.println("3. Is the deck empty?");
			System.out.println("4. Exit");

			choice = IO.readInt();

			if (choice == 1){
				deck.shuffle();
			} else if (choice == 2){
				System.out.println(deck.deal());
			} else if (choice == 3){
				System.out.println(deck.isEmpty());
			} else if (choice != 4){
				System.out.println("Error");
			}
		} */

		System.out.println("Enter how many players:");
		players = IO.readInt();
		while (players > 6){
			System.out.println("Error, there may only be 1-6 players");
			players = IO.readInt();
		}
		

		System.out.println("Enter starting earnings per player: ");
		double earnings = IO.readDouble();
		while (earnings <= 0){
			System.out.println("Error, please enter a positive starting amount: ");
			earnings = IO.readDouble();
		}


		double[] bankroll = new double[players];
		for (int i=0; i<players;i++){
			bankroll[i] = earnings;
		}

		Card[][] hands = new Card[players+1][2];
		int[] scores = new int[players+1];
		int[] splitScores = new int[players]; // array containing the scores if any player splits
			for (int p=0; p<players; p++){
				splitScores[p] = 0;
			}

		double[] bettings = new double[players]; // contains each player's bettings
		double[] insurance = new double[players]; // contains each player's insurance
			for (int p=0; p<players; p++){
				insurance[p] = 0;
			}


		do {

			for (int i=0; i<players; i++){
				System.out.println("Player " + (i+1) + ", enter how much you wish to bet this round: ");
				bettings[i] = IO.readDouble();
				while (bettings[i] <= 0){
					System.out.println("Error, player must bet a positive amount. Please reenter: ");
					bettings[i] = IO.readDouble();
				}
				while (bettings[i] > bankroll[i]){
					System.out.println("Error, Player does not have enough to bet this much. Please reenter.");
					System.out.println("Player " + (i+1) +  " current standings: " + bankroll[i]);
					bettings[i] = IO.readDouble();
				}
			}

			System.out.println();
			System.out.println("Dealing...");
			System.out.println();

			try {
				Thread.sleep(1500);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();	
			}

			
			// dealing of cards, displaying players' and dealer's hands

			for (int i=0;i<players+1;i++){
				for (int k=0;k<2;k++){
					hands[i][k] = deck.deal();
				}
			}

			for (int i=0; i<players+1;i++){
				scores[i] = hands[i][0].getValue() + hands[i][1].getValue();
			}

			for (int i=0; i<players;i++){
				System.out.println();
				if (hands[i][0].isAce() && hands[i][1].isAce()) // if both cards are Ace
					System.out.println("Player " + (i+1) + " score: 12");
				else 
					System.out.println("Player " + (i+1) + " score: " + scores[i]);
				System.out.println(hands[i][0]);
				System.out.println(hands[i][1]);
			}

			System.out.println();
			System.out.println("Dealer's score: ?");
			System.out.println(hands[hands.length-1][0]);
			System.out.println("?");
			System.out.println();

			for (int i=0; i<players; i++){
				if (i>0 && splitScores[i-1] == hands[i-1][0].getValue()){
						i--;
						temp = scores[i];
						scores[i] = splitScores[i];
						splitScores[i] = temp;

						System.out.println("Player " + (i+1) + " split #2 score: " + scores[i]);
					}

				if (scores[i] == 21){
					System.out.println("Player " + (i+1) + " score: 21! Congratulations!");
					System.out.println();

					try {
						Thread.sleep(1500);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}

				}

				while ( (scores[i] < 21 || ( hands[i][0].isAce() && hands[i][1].isAce() ) ) ){
					if (hands[i][0].isAce())
						ace++;
					if (hands[i][1].isAce())
						ace++;
					if (hands[i][0].isAce() && hands[i][1].isAce() && ace>0){
						scores[i] = scores[i] - 10;
						ace--;
					}

					System.out.println("Player " + (i+1) + " score: " + scores[i] + ", please select an option: ");
					System.out.println("1. Hit");
					System.out.println("2. Stand");
					if (count == 2) // option only available with 2 cards
						System.out.println("3. Double Down");
					if (hands[i][0].isSame(hands[i][1]) && scores[i] == 2*hands[i][0].getValue()) // option available only if two cards have same face
						System.out.println("4. Split");
					if (hands[players][0].isAce() && insurance[i] == 0) // option only available if dealer's showing card is Ace and no previous insurance placed
						System.out.println("5. Place Insurance");
					System.out.println("0. Hint");

					choice = IO.readInt();

					while (!(choice>= 0 && choice <= 5)){
						System.out.println("Error, please reenter option:");
						choice = IO.readInt();
					}

					if (choice == 1){
						dealt = deck.deal();
						count ++;
						if (dealt.isAce())
							ace++;
						System.out.println();
						System.out.println(dealt);
						scores[i] = scores[i] + dealt.getValue();
						if (scores[i] > 21){
							if (ace > 0){
								scores[i] = scores[i] - 10;
								ace--;
								System.out.println("Player " + (i+1) + " score: " + scores[i]);
								System.out.println();
							} else {
								System.out.println("Player " + (i+1) + " total score: " + scores[i]);
								System.out.println("Player " + (i+1) + " busts!");
								count = 2;
							}
							System.out.println();
							try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
						} else if (scores[i] < 21){

							System.out.println("Player " + (i+1) + " total score: " + scores[i]);
							ace = 0;
							System.out.println();
							try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
						} else {
							System.out.println("Player " + (i+1) + "score: 21! Congratulations!");
							System.out.println();
							ace = 0;
							try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
							count = 2;
						}

					} else if (choice == 2){
						System.out.println();
						System.out.println("Player " + (i+1) + " standing score: " + scores[i]);
						ace = 0;
						System.out.println();

						try {
							Thread.sleep(1500);
						} catch(InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
						count = 2;
						break;

					} else if (choice == 3 && count == 2){
						bettings[i] = 2*bettings[i];
						dealt = deck.deal();
						count ++;
						if (dealt.isAce())
							ace++;
						System.out.println();
						System.out.println(dealt);
						scores[i] = scores[i] + dealt.getValue();
						if (scores[i] > 21){
							if (ace > 0){
								scores[i] = scores[i] - 10;
								ace--;
								System.out.println("Player " + (i+1) + " score: " + scores[i]);
								System.out.println();
							} else {
								System.out.println("Player " + (i+1) + " total score: " + scores[i]);
								System.out.println("Player " + (i+1) + " busts!");
							}
							System.out.println();
							
						} else if (scores[i] < 21){

							System.out.println("Player " + (i+1) + " total score: " + scores[i]);
							ace = 0;
							System.out.println();
							
						} else {
							System.out.println("Player " + (i+1) + "score: 21! Congratulations!");
							System.out.println();
							ace = 0;
							
						}	

						try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}

						count = 2;
						break;

					} else if (choice == 4 && (hands[i][0].isSame(hands[i][1])) && scores[i] == 2*hands[i][0].getValue()){
						scores[i] = scores[i]/2;
						splitScores[i] = scores[i];
						ace = ace/2;
						ace2 = ace;

						System.out.println("Player " + (i+1) + " split #1 score: " + scores[i]);

					} else if (choice == 5 && hands[players][0].isAce()){
						System.out.println("Enter how much you wish to place as Insurance:");
						System.out.println("(You may only bet up to half your original bet, " + bettings[i]/2 + ")");
						insurance[i] = IO.readDouble();
					} else if (choice == 0){
						double dealtScore = 0;
						for (int k=0; k<(players+1 - ; k++){
							dealtScore = dealtScore + scores[i];
						}	
						double totalScore = multiplicity*52*(85.0/13.0); // calculates average score of cards remaining in deck based on what was already dealt
						double probableScore = (double)(totalScore - dealtScore)/(deck.getCards()); 
						System.out.println("Average possible rank of next card: " + probableScore);
						if (scores[i] + probableScore <= 21){ // determines whether this average will bust player
							System.out.println("We suggest you Hit! It is not probable the next card will bust you!");
						} else {
							System.out.println("We suggest you Stand! It is probable the next card will bust you!");
						}
					}

				}
			}

			System.out.println("Dealer's score: " + scores[players]);
			System.out.println(hands[players][0]);
			System.out.println(hands[players][1]);
			if (hands[players][0].isAce())
				ace++;
			if (hands[players][1].isAce())
				ace++;
			System.out.println();
			
			try {
				Thread.sleep(1500);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			while (scores[players] < 17){
				System.out.println("Dealer Hits!");
				System.out.println();
				dealt = deck.deal();
				if (dealt.isAce())
					ace++;
				scores[players] = scores[players] + dealt.getValue();
				System.out.println(dealt);
				System.out.println("Dealer's total score: " + scores[players]);
				System.out.println();

				try {
					Thread.sleep(1500);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

			} if (scores[players] > 21){
				if (ace>0){
					scores[players] = scores[players] - 10;
					ace--;
					System.out.println("Dealer's score: " + scores[players]);
					System.out.println();

					try {
						Thread.sleep(1500);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}

				} else {
					System.out.println("Dealer's total score: " + scores[players]);
					System.out.println("Dealer busts!");
					ace=0;
					System.out.println();

					try {
						Thread.sleep(1500);
					} catch(InterruptedException ex) {
						Thread.currentThread().interrupt();
					}

					for (int i=0;i<players;i++){
						if (scores[i] <= 21){
							bankroll[i] = bankroll[i] + bettings[i];
							System.out.println("Player " + (i+1) + " wins $" + bettings[i] + "!");
							if (splitScores[i] != 0){
								if (splitScores[i] <= 21){
									bankroll[i] = bankroll[i] + bettings[i];
									System.out.println("Player " + (i+1) + " wins $" + bettings[i] + " from split deck!");
								} else {
									bankroll[i] = bankroll[i] - bettings[i];
									System.out.println("Player " + (i+1) + " loses $" + bettings[i] + " from split deck!");
								}
								splitScores[i] = 0;
							}
							System.out.println("Current bankroll: $" + bankroll[i]);
							System.out.println();

							try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}

						} else {
							bankroll[i] = bankroll[i] - bettings[i];
							System.out.println("Player " + (i+1) + " loses $" + bettings[i] + "!");
							if (splitScores[i] != 0){
								if (splitScores[i] <= 21){
									bankroll[i] = bankroll[i] + bettings[i];
									System.out.println("Player " + (i+1) + " wins $" + bettings[i] + " from split deck!");
								} else {
									bankroll[i] = bankroll[i] - bettings[i];
									System.out.println("Player " + (i+1) + " loses $" + bettings[i] + " from split deck!");
								}
								splitScores[i] = 0;
							}
							System.out.println("Current bankroll: $" + bankroll[i]);
							System.out.println();

							try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}

						}
					}
				}
			} else {
				System.out.println("Dealer Stands!");
				ace=0;
				System.out.println();

				try {
					Thread.sleep(1500);
				} catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}

				if (hands[players][0].isAce() && hands[players][1].isTen()){
					for (int b=0; b<players; b++){
						if (insurance[b] != 0){
							bankroll[b] = bankroll[b] + 2*insurance[b];
							System.out.println("Player " + (b+1) + " wins $" + insurance[b] + " from Insurance!");
							bankroll[b] = 0;
							System.out.println();

							try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
						}
					}
				} else if (hands[players][0].isAce() && !hands[players][1].isTen()){
					for (int b=0; b<players; b++){
						if (insurance[b] != 0){
							bankroll[b] = bankroll[b] - insurance[b];
							System.out.println("Player " + (b+1) + " loses $" + insurance[b] + " from Insurance!");
							bankroll[b] = 0;
							System.out.println();

							try {
								Thread.sleep(1500);
							} catch(InterruptedException ex) {
								Thread.currentThread().interrupt();
							}
						}
					}
				}

				for (int i=0; i<players;i++){
					if (scores[i] > scores[players] && scores[i] <= 21){
						bankroll[i] = bankroll[i] + bettings[i];
						System.out.println("Player " + (i+1) + " wins $" + bettings[i] + "!");
						if (splitScores[i] != 0){
								if (splitScores[i] > 21 || (scores[players] <= 21 && splitScores[i] < scores[players])){
									bankroll[i] = bankroll[i] - bettings[i];
									System.out.println("Player " + (i+1) + " loses $" + bettings[i] + " from split deck!");
								} else if (splitScores[i] > scores[players] && splitScores[i] <= 21){
									bankroll[i] = bankroll[i] + bettings[i];
									System.out.println("Player " + (i+1) + " wins $" + bettings[i] + " from split deck!");
								} else {
									System.out.println("Player " + (i+1) + " pushes with split deck!");
								}
								splitScores[i] = 0;
							}
						System.out.println("Current bankroll: $" + bankroll[i]);
						System.out.println();

						try {
							Thread.sleep(1500);
						} catch(InterruptedException ex) {
							Thread.currentThread().interrupt();
						}

					} else if (scores[i] < scores[players]){
						bankroll [i] = bankroll[i] - bettings[i];
						System.out.println("Player " + (i+1) + " loses $" + bettings[i] + "!");
						if (splitScores[i] != 0){
								if (splitScores[i] > 21 || (scores[players] <= 21 && splitScores[i] < scores[players])){
									bankroll[i] = bankroll[i] - bettings[i];
									System.out.println("Player " + (i+1) + " loses $" + bettings[i] + " from split deck!");
								} else if (splitScores[i] > scores[players] && splitScores[i] <= 21){
									bankroll[i] = bankroll[i] + bettings[i];
									System.out.println("Player " + (i+1) + " wins $" + bettings[i] + " from split deck!");
								} else {
									System.out.println("Player " + (i+1) + " pushes with split deck!");
								}
								splitScores[i] = 0;
							}
						System.out.println("Current bankroll: $" + bankroll[i]);
						System.out.println();

						try {
							Thread.sleep(1500);
						} catch(InterruptedException ex) {
							Thread.currentThread().interrupt();
						}

					} else if (scores[i] == scores[players]){
						System.out.println("Player " + (i+1) + " pushes!");
						if (splitScores[i] != 0){
								if (splitScores[i] > 21 || (scores[players] <= 21 && splitScores[i] < scores[players])){
									bankroll[i] = bankroll[i] - bettings[i];
									System.out.println("Player " + (i+1) + " loses $" + bettings[i] + " from split deck!");
								} else if (splitScores[i] > scores[players] && splitScores[i] <= 21){
									bankroll[i] = bankroll[i] + bettings[i];
									System.out.println("Player " + (i+1) + " wins $" + bettings[i] + " from split deck!");
								} else {
									System.out.println("Player " + (i+1) + " pushes with split deck!");
								}
								splitScores[i] = 0;
							}
						System.out.println("Current bankroll: $" + bankroll[i]);
						System.out.println();

						try {
							Thread.sleep(1500);
						} catch(InterruptedException ex) {
							Thread.currentThread().interrupt();
						}

					} else {
						bankroll [i] = bankroll[i] - bettings[i];
						System.out.println("Player " + (i+1) + " loses $" + bettings[i] + "!");
						if (splitScores[i] != 0){
								if (splitScores[i] > 21 || (scores[players] <= 21 && splitScores[i] < scores[players])){
									bankroll[i] = bankroll[i] - bettings[i];
									System.out.println("Player " + (i+1) + " loses $" + bettings[i] + " from split deck!");
								} else if (splitScores[i] > scores[players] && splitScores[i] <= 21){
									bankroll[i] = bankroll[i] + bettings[i];
									System.out.println("Player " + (i+1) + " wins $" + bettings[i] + " from split deck!");
								} else {
									System.out.println("Player " + (i+1) + " pushes with split deck!");
								}
								splitScores[i] = 0;
							}
						System.out.println("Current bankroll: $" + bankroll[i]);
						System.out.println();

						try {
							Thread.sleep(1500);
						} catch(InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
					}
				}
			}

			try {
				Thread.sleep(500);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			for (int i=0; i<players;i++){
				if (bankroll[i] == 0.0){
					double max = bankroll[0];
					for (int k=0; k<players; k++){
						if (bankroll[k] > max)
							max = bankroll[k];
					}
					System.out.println("Final bankroll: ");
					for (int t=0;t<players;t++){
						System.out.println("Player " + (t+1) + ": $" + bankroll[t]);
					}
					System.out.println();
					System.out.println("Winner(s) with $" + max + ":");
					for (int w=0;w<players;w++){
						if (bankroll[w] == max){
							System.out.println("Player " + (w+1));
						}
					}
					System.out.println("Thanks for playing!");
					System.out.println();
					System.exit(0);
				}
			}

			System.out.println("Next round:");
			System.out.println();

			try {
				Thread.sleep(1500);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}

			System.out.println("Shuffling...");

			try {
				Thread.sleep(1500);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();	
			}


		} while (1 == 1);
	}
}
