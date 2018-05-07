public class Game{
	Player[] playerArr;
	
	public Game(Deck d) {
		Player[] playerArray = {new Player(d),new Player(d),new Player(d),new Player(d)};
		this.playerArr = playerArray;
		playerArr[0].setUser();
		
		int playerIndex = determinePlayerOne();
		playerArr[playerIndex].setNumber(1);
		int playerNum = 2;
		while(playerNum<5) {
			playerIndex++;
			if(playerIndex == playerArr.length)
				playerIndex = 0;
			playerArr[playerIndex].setNumber(playerNum);
			playerNum++;
		}
	}
	public int determinePlayerOne() { //gives us the index at which player one is
		for(int i = 0; i < playerArr.length; i++) {
			if(playerArr[i].getHand().getCardByRank(1)!= null) {
				return i;
			}
		}
		return -1;
	}
	//Player 1 - 4
	public Player getPlayer(int pNum) {
		for(int i = 0; i < playerArr.length; i++) {
			if(playerArr[i].getNumber() == pNum) {
				return playerArr[i];
			}
		}
		return null;
		
	}
	public Player[] getPlayers() {
		return this.playerArr;
	}
	public Player getUser() {
		return playerArr[0];
	}
}