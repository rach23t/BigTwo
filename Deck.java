import java.util.ArrayList;
import java.util.Arrays;
public class Deck{
	private ArrayList<Card> deck;
	
	private String[] suits = {"Diamonds", "Clubs", "Hearts", "Spades"};
	private String[] cardNums = {"Three", "Four", "Five", "Six","Seven", "Eight", "Nine","Ten","Jack","Queen", "King", "Ace", "Two"};

	
	//creates a sorted deck
	/*public Deck() {
		int counter = 0;
		for(int j = 0; j < cardNums.length; j++) {
			for(int i = 0; i < suits.length; i++) {
				deck[counter] = new Card(suits[i], cardNums[j], counter+1);
					counter ++;
			}
		}
	}*/
	
	//creates a shuffled deck
	public Deck() {
		this.deck = new ArrayList<Card>(52);
		ArrayList<Card> indexList = new java.util.ArrayList<Card>();
		int counter = 0;
		for(int j = 0; j < cardNums.length; j++) {
			for(int i = 0; i < suits.length; i++) {
				indexList.add(new Card(suits[i], cardNums[j], counter+1));
					counter ++;
			}
		}	 
		
		int randomIndex;
		while(indexList.size() > 0) {
			randomIndex = (int)(Math.random()*(indexList.size()));
			//System.out.println(randomIndex);
			deck.add(indexList.remove(randomIndex));
		}
		
	}
	
	//creates a deck object that is a hand and that acts as a deck- has same properties 
	public Deck(ArrayList<Card> handArr) { 
		this.deck = handArr;
	}
	//retrieves cards from array we made 
	public Card getCardAt(int index) {
		return deck.get(index);
	}
	public Card getCardByRank(int rank) {
		for(int i = 0; i < deck.size(); i++) {
			if(this.getCardAt(i).getRank() == rank) {
				return this.getCardAt(i);
			}
		}
		return null; 
	}
	public Card drawCard(){
		return deck.remove(0);
	}
	public boolean drawCard(Card card) {
		return deck.remove(card);
	}
	public Card drawCard(int rank) {
		Card card = getCardByRank(rank);
		deck.remove(card);
		return card;
	}
	public Card drawCardByIndex(int index) {
		return deck.remove(index);
	}
	public int getSize() {
		return deck.size();
	}
	public void sort(){
		Card[] deckArr = new Card[deck.size()];
		for(int i = 0; i < deckArr.length; i++) {
			deckArr[i] = deck.get(i);
		}
		Arrays.sort(deckArr);
		
		for(int i = 0; i < deckArr.length; i++) {
			deck.set(i, deckArr[i]);
		}
		
	}
	public Card[][] getDoubles(){
		//sorted deck
		int numDoubles = 0;
		for(int i = 0; i < deck.size()-1; i++) {
			if(deck.get(i).getNum().equals(deck.get(i+1).getNum()))
				numDoubles++;
		}
		//System.out.println("IM IN GETDOUBLES AND SO FAR ITS OK, number of doubles: " + numDoubles);
		if(numDoubles == 0)
			return null;
		Card[][] doubleArr = new Card[numDoubles][2];
		int count = 0;
		for(int i = 0; i < deck.size()-1; i++) {
			if(deck.get(i).getNum().equals(deck.get(i+1).getNum())) {
				doubleArr[count][0] = deck.get(i);
				doubleArr[count][1] = deck.get(i+1);
				count++;
				if(count == numDoubles) {
					//System.out.println("IM IN GETDOUBLES AND SO FAR ITS OK, number of doubles: " + numDoubles);
					return doubleArr;
				}
					
			}
				
		}
		System.out.println("IM IN GETDOUBLES AND SO FAR ITS OK");
		return null;
	}
	public boolean inDouble(Card card) {
		
		
		if( this.getDoubles() == null) {
			//System.out.println("I GOT INTO INDOUBLE");
			return false;
		}
		Card[][] doubleArr = this.getDoubles();
			
		for(int i = 0; i < doubleArr.length; i++) {
			for(int j = 0; j< 2; j++) {
				if(card.equals(doubleArr[i][j]))
					return true;
			}
		}
		return false;
	}
	//HANDS 
	//populates the array of hands 
	/*
	public void getHands() {
		for(int i = 0; i < 4; i++) {
			Hand newHand = new Hand(startAt);
			handArray[i] = newHand;
			startAt = startAt+12;
		}
	}
	public Hand getHandAt(int index) {
		return handArray[index];
	} */
}
