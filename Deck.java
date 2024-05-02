import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
public class Deck {

	private List<Card> cards;
	public Deck()
	{
	cards = new ArrayList<>();
		/*for (Rank cardRank : Rank.values()) {
			for (Suit cardSuit: Suit.values()) {
				cards.add(new Card(cardRank, cardSuit));
			}
		} */ 
	//Old way of doing it. Sorted by rank first, then suit. Test cases want the other way around
	
	for (Suit cardSuit: Suit.values())
	{
		for (Rank cardRank: Rank.values())
		{
			cards.add(new Card(cardRank, cardSuit));
		}
	}
	}
	
	public Card draw() {
		if (cards.isEmpty())
		{
			return null;
		}
		
		Card cardToReturn = cards.get(0);
		cards.remove(0);
		return cardToReturn;
	}
	
	public List<Card> draw(int count)
	{
		List<Card> cards2;
		int index = 0;
		cards2 = new ArrayList<>();
		if (count < 0)
		{
			return cards2;
		}
		
		if (cards.isEmpty())
		{
			return cards2;
		}
		
		Iterator<Card> cardIterator = cards.iterator(); //Have to add an Iterator to avoid exception :(
		
		/*if (count > cards.size())
		{
			for (Card cardRemover: cards)
			{
				cards2.add(cardRemover);
				cards.remove(index);
				index++;
			}
			return cards2;
		} */
		
		while(cardIterator.hasNext() && index < count)
		{
			Card toBeIterated = cardIterator.next();
			cards2.add(toBeIterated);
			cardIterator.remove();
			index++;
		}
		
		return cards2;
		
		/*while (index <= count)
		{
			Card cardToReturn = cards.get(0);
			cards.remove(0);
			cards2.add(cardToReturn);
			index++;
		}
		return cards2; */
	}
	
	public void shuffle()
	{
		Collections.shuffle(cards);
	}
	
	public int size()
	{
		return cards.size();
	}
	
	public List<Card> getCardsByRank(Rank rank)
	{
		List<Card> cards2 = new ArrayList<>();
		for (Card cardHelper: cards)
		{
			if (cardHelper.getRank().equals(rank))
			{
				cards2.add(cardHelper);
			}
		}
		return cards2;
	}
	
	@Override
	public String toString()
	{
		return cards.toString();
	}
}
