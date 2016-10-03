// This class represents the deck of cards from which cards are dealt to players.
public class Deck
{
	private Card[] deck;
	private int multiplicity=1;
	private int cards = 52*multiplicity;
	
	
	// This constructor builds a deck of 52 cards.
	public Deck(int multiplicity)
	{
		deck = new Card[52*multiplicity];
		this.multiplicity = multiplicity;
		cards = 52*multiplicity;
		for (int d=1; d<=multiplicity; d++){
			for (int suit=0; suit<=3; suit++){
				for (int face=1; face<=13; face ++){
					deck[cards-1] = new Card(suit, face);
						cards--;
				}
			}
		}
		cards = deck.length;
	}

	
	// This method takes the top card off the deck and returns it.
	public Card deal()
	{
		if (isEmpty()){
			shuffle();
		}
		cards --;
		return deck[cards]; 
	}
	
	
	// this method returns true if there are no more cards to deal, false otherwise
	public boolean isEmpty()
	{
		if (cards <= 0){
			return true;
		} else {
			return false;
		}
	}
	
	//this method puts the deck int some random order
	public void shuffle(){
		//fill this method in

		for (int i=1; i<=10;i++){ // rearranges cards 10 times
			for (int k=1; k<=52*multiplicity; k++){
				int r = (int)(Math.random()*((52)*multiplicity));
				Card temp = deck[k-1];
				deck[k-1] = deck[r];
				deck[r] = temp;
			}
		cards = deck.length;
		}
	}

	public int getCards(){
		return cards;
	}
}