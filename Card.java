
public class Card {

	private Rank rank;
	private Suit suit;
	
	public Card(Rank rank, Suit suit)
	{
		if (rank == null || suit == null)
		{
			throw new NullPointerException();
		}
		
		this.rank = rank;
		this.suit = suit;
	}
	
	public Rank getRank()
	{
		return rank;
	}
	
	public Suit getSuit()
	{
		return suit;
	}
	
	public int compareTo(Card card)
	{
		int suitMatcher = this.suit.compareTo(card.suit);
		if (suitMatcher != 0) {
			return suitMatcher;
		}
		
		return this.rank.compareTo(card.rank);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Card card2;
		if (obj instanceof Card) {
			card2 = (Card) obj;
		} else {
			return false;
		}
		
		if (card2.getRank() == rank && card2.getSuit() == suit) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() 
	{
		final int primeNum = 31;
	    int result = 1;
	    result = primeNum * result + ((rank == null) ? 0 : rank.hashCode());
	    result = primeNum * result + ((suit == null) ? 0 : suit.hashCode());
	    return result;
	}

	@Override
	public String toString() {
		return rank.toString() + suit.toString();
	}
	
}
