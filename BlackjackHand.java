import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
public class BlackjackHand {

	private static final int MAX_VALUE = 21;
	private List<Card> cards = new ArrayList<>();
	private int value = 0;
	private int numAcesAs11 = 0;
	private static final Map<Rank, Integer> CARD_VALUES = new HashMap<>();
	
	static {
		for (int i = 2; i <= 10; i++)
		{
			CARD_VALUES.put(Rank.values()[i-2], i);
		}
		
		CARD_VALUES.put(Rank.JACK, 10);
		CARD_VALUES.put(Rank.QUEEN, 10);
		CARD_VALUES.put(Rank.KING, 10);
		CARD_VALUES.put(Rank.ACE, 11);
	}
	
	public BlackjackHand(Card c1, Card c2)
	{
		cards.add(c1);
		cards.add(c2);
	}
	
	public void addCard(Card card)
	{
		if (card == null)
		{
			throw new NullPointerException();
		}
		
		if (value < MAX_VALUE)
		{
			cards.add(card);
			value = getValue();
		}
		
		/*if (card.getRank().equals(Rank.ACE))
		{
			numAcesAs11++;
		} */
	}
	
	public static Map<Rank, Integer> getCardValues()
	{
		return new HashMap<>(CARD_VALUES);
	}

	public List<Card> getCards()
	{
		List<Card> cards2 = new ArrayList<>();
		for (Card cardGetter: cards)
		{
			cards2.add(cardGetter);
		}
		return cards2;
	}
	
	public int getValue() 
	{
		value = 0;
		for (Card valueGetter: cards) {
			if (valueGetter.getRank().equals(Rank.ACE)) {
				if (value + 11 <= MAX_VALUE) {
					numAcesAs11++;
					value = value + 11;
					} else {
						value = value + 1;
					}
				}
			else {
			value = value + CARD_VALUES.get(valueGetter.getRank());
			}
		}
		
		while (value > MAX_VALUE && numAcesAs11 > 0)
		{
			value = value - 10;
			numAcesAs11--;
		} 
		
		return value;
	}
	
	public int size() {
		return cards.size();
	}
	
	@Override
	public String toString() {
		return cards.toString();
	}
}
