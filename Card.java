// This class represents one playing card.
public class Card
{
	// Card suits (provided for your convenience - use is optional)
	public static final int SPADES   = 0;
	public static final int HEARTS   = 1;
	public static final int CLUBS    = 2;
	public static final int DIAMONDS = 3;

	// Card faces (provided for your convenience - use is optional)
	public static final int ACE      = 1;
	public static final int TWO      = 2;
	public static final int THREE    = 3;
	public static final int FOUR     = 4;
	public static final int FIVE     = 5;
	public static final int SIX      = 6;
	public static final int SEVEN    = 7;
	public static final int EIGHT    = 8;
	public static final int NINE     = 9;
	public static final int TEN      = 10;
	public static final int JACK     = 11;
	public static final int QUEEN    = 12;
	public static final int KING     = 13;


	// define fields here
	private int suit;
	private int face;
	
	// This constructor builds a card with the given suit and face, turned face down.
	public Card(int cardSuit, int cardFace)
	{
		suit = cardSuit;
		face = cardFace;
	}

	// This method retrieves the suit (spades, hearts, etc.) of this card.
	public int getSuit()
	{
		return suit;
	}
	
	// This method retrieves the face (ace through king) of this card.
	public int getFace()
	{
		return face;
	}
	
	// This method retrieves the numerical value of this card
	// (usually same as card face, except 1 for ace and 10 for jack/queen/king)
	public int getValue()
	{
		if (face == 11 || face == 12 || face == 13){
			return 10;
		} else if (face == 1){
			return 11;
		} else {
			return face;
		}
	}

	public String toString(){
		String a = "";
		if (face == 11){
			a = a + "Jack";
		} else if (face == 12){
			a = a + "Queen";
		} else if (face == 13){
			a = a + "King";
		} else if (face == 1){
			a = a + "Ace";
		} else {
			a = a + Integer.toString(getValue());
		}
		a = a + " of ";
		if (suit == 0){
			a = a + "Spades";
		} else if (suit == 1){
			a = a + "Hearts";
		} else if (suit == 2){
			a = a + "Spades";
		} else if (suit ==3){
			a = a + "Diamonds";
		}
		return a;
	}

	public boolean isAce(){
		if (face == 1)
			return true;
		else
			return false;
	}

	public boolean isTen(){
		if (face >= 10 && face <= 13)
			return true;
		else
			return false;
	}

	public boolean isSame(Card c){
		if (this.face == c.face)
			return true;
		else
			return false;
	}
	
}
