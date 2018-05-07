
public class Card implements Comparable{
	private String suit;
	private String num;
	private int rank;
	
	public Card(String suit, String num, int rank) {
		this.suit = suit;
		this.num = num;
		this.rank = rank;
	}
	public String getNum() {
		return this.num;
	}
	public String getSuit() {
		return this.suit;
	}
	public int getRank() {
		return this.rank;
	}
	
	//im not sure if we need this
	public void setNum(String num) {
		this.num = num;
	}
	public void setSuit(String suit) {
		this.suit = suit;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String toString(){
		return (this.getNum() + ","+ this.getSuit() + ", Rank: " + this.getRank());
	}
	public int compareTo(Object o){
		if(this.getRank() > ((Card)o).getRank())
			return 1;
		if(this.getRank() < ((Card)o).getRank())
			return -1;
		return 0;
	}

	
}
