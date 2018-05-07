import java.util.ArrayList;
public class Player {
	private Deck hand;
	private int num;//Which player(1-4)
	private boolean user = false; //user = true
	
	public Player(Deck d) {
		ArrayList<Card> handArr = new ArrayList<Card>();
		for(int i = 0; i < 13; i++) {
			handArr.add(d.drawCard());
		}
		hand = new Deck(handArr);
	}
	

	public Deck getHand() {
		return this.hand;
	}
	
	public int  getNumber() {
		return this.num;
	}
	public boolean getUser() {
		return this.user;
	}
	//sets number determind by order (whoever has 3 of diamonds goes first)
	public void setNumber(int number) {
		this.num = number;
	}
	public void setUser() {
		this.user = true;
	}
	
}
