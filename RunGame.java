import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Button;
import javafx.scene.control.*;


public class RunGame extends Application {
	//Our display:
	Deck deckObject = new Deck();
	Game game = new Game(deckObject);
	Player user = game.getUser();
	ArrayList<Card> cardsPlayedArr = new ArrayList<Card>();
	ArrayList<Card> cardsSelected = new ArrayList<Card>();
	ArrayList<Button> buttonsSelected = new ArrayList<Button>();
	Player[] playersByNum = new Player[4]; //player order based on the container
	String[] aiLevel = new String[4];
	boolean[] passArr = new boolean[4];
	boolean win = false;
	int maxCardPlay = 2; // this value can be changed with alterations of the code 
	boolean userFree = false;
	
	int numCardPlay = 1; 
	boolean userFirst = (game.getUser().getNumber() == 1);
	boolean firstMove = true;
	int firstPlayerIndex; //the index of the first user after the player;
	
	GridPane[] playersContainer = new GridPane[5];
	VBox userBox = new VBox();
	VBox centerCardDisplay = new VBox();
	ImageView[] cardDisplayedArr = new ImageView[2];
	VBox cardsPlayedDisplay = new VBox();
	Text cardPlayed = new Text();
	Button submitButton = new Button("Submit");
	Button passButton = new Button("Pass");

	
	//ARRAHYLIST OF PLAYED CARDS
	//CHECK WILL CHECK IF RANK IS HIGHER THAN THE LAST ONE IN THE ARRAYLIST
	//GOES BACK AND FORTH BTWN PLAYER AND USER --> WILL NULL USER WHEN NECESARY
	//CONTIUPUSLY WRITES TO CENTER AND VBOX 
	//if multiple cards --> another method that keeps calling the vcheckInput and the check input will create an array to check
	
	@Override 
	public void start(Stage primaryStage) {
		cardPlayed.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 14));
		cardPlayed.setFill(Color.WHITE);
		
		BorderPane outsideContainer = new BorderPane();
		outsideContainer.setStyle("-fx-background: #148729;");
		
		VBox p1Box = new VBox();
			Text pLabels = new Text();
			pLabels.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
			pLabels.setFill(Color.WHITE);
			pLabels.setText("Player One");
			p1Box.getChildren().add(pLabels);
		GridPane p1 = new GridPane();
			p1.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: white;" + "-fx-border-width: 3");
			p1.setPrefSize(200,150);
			p1.setMinSize(200,150);
			p1Box.getChildren().add(p1);
			p1Box.setAlignment(Pos.CENTER);
			p1.setAlignment(Pos.CENTER);
			p1.setVgap(5);
		
		VBox p2Box = new VBox();
		Text pLabels2 = new Text();
		pLabels2.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
		pLabels2.setFill(Color.WHITE);
		pLabels2.setText("Player Two");
		p2Box.getChildren().add(pLabels2);
		GridPane p2 = new GridPane();
			p2.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: white;" + "-fx-border-width: 3");
			p2.setPrefSize(200,150);
			p2.setMinSize(200,150);
			p2Box.getChildren().add(p2);
			p2Box.setAlignment(Pos.CENTER);
			p2.setAlignment(Pos.CENTER);
			p2.setVgap(5);
		
		VBox p3Box = new VBox();
		Text pLabels3 = new Text();
		pLabels3.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
		pLabels3.setFill(Color.WHITE);
		pLabels3.setText("Player Three");
		p3Box.getChildren().add(pLabels3);
		GridPane p3 = new GridPane();
			p3.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: white;" + "-fx-border-width: 3");	
			p3.setPrefSize(200,150);
			p3.setMinSize(200,150);
			p3Box.getChildren().add(p3);
			p3Box.setAlignment(Pos.CENTER);
			p3.setAlignment(Pos.CENTER);
			p3.setVgap(5);
		
		VBox p4Box = new VBox();
		Text pLabels4 = new Text();
		pLabels4.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
		pLabels4.setFill(Color.WHITE);
		pLabels4.setText("Player Four");
		p4Box.getChildren().add(pLabels4);
		GridPane p4 = new GridPane();
			p4.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: white;" + "-fx-border-width: 3");
			p4.setPrefSize(200,150);
			p4.setMinSize(200,150);
			p4Box.getChildren().add(p4);
			p4Box.setAlignment(Pos.CENTER);
			p4.setAlignment(Pos.CENTER);
			p4.setVgap(5);
			
		GridPane playerContainer = new GridPane();
			//playerContainer.setHgap(1);
			//playerContainer.setVgap(1);
			playerContainer.add(p1Box, 0, 1);
			playerContainer.add(p2Box, 1, 1);
			playerContainer.add(p3Box, 2, 1);
			playerContainer.add(p4Box, 3, 1);
			playerContainer.setHgap(10);
			playerContainer.setVgap(20);
			//playerContainer.setAlignment(Pos.CENTER);
		outsideContainer.setTop(playerContainer);
		
		
		//centerCardDisplay.getChildren().add(new Label("Cards Played:"));
			cardDisplayedArr[0] = new ImageView();
			cardDisplayedArr[1] = new ImageView();
			centerCardDisplay.getChildren().add(cardPlayed);
			centerCardDisplay.getChildren().add(cardDisplayedArr[0]);
			centerCardDisplay.getChildren().add(cardDisplayedArr[1]);
			
			centerCardDisplay.setAlignment(Pos.CENTER);
			
			
		outsideContainer.setCenter(centerCardDisplay);
		
		VBox cardsPlayedDisplayBox = new VBox();
		Label cardLabel = new Label("Cards Played:");
		cardLabel.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
		cardLabel.setTextFill(Color.WHITE);
		cardsPlayedDisplayBox.getChildren().add(cardLabel);
		
		ScrollPane sp = new ScrollPane();
		sp.setContent(cardsPlayedDisplay);
		sp.setHbarPolicy(ScrollBarPolicy.NEVER);
		sp.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        sp.setPrefSize(300,500);
        cardsPlayedDisplayBox.getChildren().add(sp);
		outsideContainer.setRight(cardsPlayedDisplayBox);
		
		Text userLabel = new Text("Player: " + game.getUser().getNumber());
		userLabel.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
		userLabel.setFill(Color.WHITE);
		userBox.getChildren().add(userLabel);
		GridPane user = new GridPane();
		user.setHgap(5);
		user.setVgap(5);
		//user.setAlignment(Pos.CENTER);
		userBox.getChildren().add(user);
		HBox userButtons = new HBox();
			Text buttonLabel = new Text("Submit your selection: ");
			buttonLabel.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 18));
			buttonLabel.setFill(Color.WHITE);
			userButtons.getChildren().add(buttonLabel);
			passButton.setOnAction(e -> pass(game.getUser().getNumber()));
			submitButton.setOnAction(e -> submit());
			userButtons.getChildren().add(submitButton);
			userButtons.getChildren().add(passButton);
			userButtons.setSpacing(10);
		userBox.getChildren().add(userButtons);
		userBox.setSpacing(10);;
		outsideContainer.setBottom(userBox);
		outsideContainer.setPadding(new Insets(10,15,15,15));
		
		playersContainer[0] = p1; playersContainer[1] = p2; playersContainer[2] = p3; playersContainer[3] = p4;
		playersContainer[4] = user;

		setUpBoard();
		
	//	BackgroundImage sceneBg = new BackgroundImage(new Image("img/bg.jpg", 35,50,false,true), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		         // BackgroundSize.DEFAULT);
		Scene scene = new Scene(outsideContainer, 1150, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public void setUpBoard() {
		 //Displays all people's cards for me , the developer 
		Player[] players = game.getPlayers();
		for(int i = 0; i < players.length; i++) {
			System.out.println("Index " + i + ": " + players[i].getNumber());
		}
		System.out.println();
		for(int i = 0; i < players.length; i++) {
			for(int j = 0; j < 13; j ++) {
				System.out.println(players[i].getHand().getCardAt(j).toString());
			}
			System.out.println(players[i].getUser());
			System.out.println(players[i].getNumber());
			System.out.println("\n ");	
		}
	
		
		String[] levelChoices = {"a","b","c"};//types of AI levels 
		int count = 0;
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j<4;j++) {
				if(game.getPlayers()[j].getNumber() == (i+1)) {
					playersByNum[i] = game.getPlayers()[j];
					if(game.getPlayers()[j].getUser() == false) {
						aiLevel[i] = levelChoices[count];
						if(aiLevel[i].equals("b")||aiLevel[i].equals("c") ) {
							playersByNum[i].getHand().sort();
						}
						System.out.println("Player " + (i+1) + " AI Level" + aiLevel[i]);
						count++;
					}
					break;
				}
			}
		}
		
		
		Image backImg = new Image("img/back.jpg",25,40, false, true);
		//labels players by number
		for(int i = 0; i < 4; i++) {
			//String playerLabel = new String("Player: " + (i+1));
			//playersContainer[i].add(new Label(playerLabel), 0, 0);
			if(playersByNum[i].getUser() == true) {
				Text txt = new Text("You are Player " + (i+1));
				txt.setFont(Font.font("times new roman", FontWeight.NORMAL, FontPosture.REGULAR, 18));
				txt.setFill(Color.WHITE);
				HBox txtCont = new HBox();
				txtCont.getChildren().add(txt);
				txtCont.setAlignment(Pos.CENTER);
				playersContainer[i].add(txt, 1, 1);
				if(i == 3) 
					firstPlayerIndex = 0;
				else
					firstPlayerIndex = i+1;
	
				continue;
			}
			for(int j = 0; j < 7; j++) {
				playersContainer[i].add(new ImageView(backImg),j,0);
			}
			for(int j = 7; j < 13; j++) {
				playersContainer[i].add(new ImageView(backImg),j-7,1);
			}
		}
		
		//USER DISPLAY 

		//userBox.getChildren().add(new Label("Player: " + user.getNumber());
		Deck playerHand = game.getUser().getHand();
		for(int i = 0; i < 7; i ++) {
			Card cardPassed = playerHand.getCardAt(i);
			Image cardImg =  new Image("img/" + cardPassed.getRank() + ".jpg", 35,50,false,true);
			Button newButton = new Button();
			newButton.setGraphic(new ImageView(cardImg));
			
			newButton.setOnAction(e -> select(cardPassed, newButton));
			//newButton.setDisable(true); DONT DO THIS---> WHEN ITS NOT IN USE MAKE THE GRIDPANE GET THE CHILDREN AND DISABLE THE CHILDREN!!
			
			playersContainer[4].add(newButton,i,0);
		} 
		for(int i = 7; i < 13; i ++) {
			Card cardPassed = playerHand.getCardAt(i);
			Image cardImg =  new Image("img/" + cardPassed.getRank() + ".jpg", 35,50,false,true);
			Button newButton = new Button();
			newButton.setGraphic(new ImageView(cardImg));
			
			newButton.setOnAction(e -> select(cardPassed, newButton));
			//newButton.setDisable(true); SEE ABOVE FOR COMMENTS 
			
			playersContainer[4].add(newButton,i-7,1);
		} 
		
		makeFirstMove();
	}
	/*public void playGame() {
		makeFirstMove();
		boolean player = (game.getUser().getNumber() != 0);
		while(win == false) {
			if(player) {
				makePlayerMove();
			}
			else {
				makeUserMove();
			}
			player = !player;
		}
	} */
	public void pass(int pNum) {
		//pNum refers to number of the container that will do the passing
		passArr[pNum-1] = true;
		if(game.getPlayer(pNum).equals(game.getUser()) && firstMove == false) { //if its the user making the pass 
			Text cardString = new Text("You have passed your turn");
			cardString.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
			cardString.setFill(Color.WHITE);
			cardsPlayedDisplay.getChildren().add(cardString);
			cardPlayed.setText(cardString.getText());
			cardDisplayedArr[0].setImage(null);
			cardDisplayedArr[1].setImage(null);
			makePlayerMove();
		}
	}
	public void makeFirstMove() {
		if(userFirst) {
			cardPlayed.setText("You are player one. Please select your first play. Make sure you include a three of diamonds into your play");
			return;
		}
		makePlayerMove();
		
		//firstMove = false;
	}

	public void select(Card card, Button currentButton) {
		if(cardsSelected.indexOf(card) != -1) {
			//WE ALREADY PICKED THIS CARD SILLY, REMOVE AND DESELECT
			cardsSelected.remove(card);
			currentButton.setStyle("-fx-border-style: none");
			buttonsSelected.remove(currentButton);
			return;
		}
		if(cardsSelected.size() == numCardPlay) {
			//HANDLE WHAT HAPPENS WHEN THE USER TRIES TO SELECT MORE 
			if(userFree == false) {
				cardsSelected.remove(cardsSelected.size()-1);
				buttonsSelected.get(buttonsSelected.size() -1).setStyle("-fx-border-style: none");
				buttonsSelected.remove(buttonsSelected.size() -1);
			}
			else {
				if(cardsSelected.size()> maxCardPlay)
					return;
			}
			
			
		}
		cardsSelected.add(card);
		buttonsSelected.add(currentButton);
		currentButton.setStyle("-fx-border-style: solid inside;" + "-fx-border-color: blue;");
	}
	public void submit() {
		/*CHECK INPUT*/
		if(cardsSelected.size() != numCardPlay && userFree == false) {
			//let them know they messed up, tell them to pick
			cardPlayed.setText("Please input the correct number of cards for your play.");
			return;
		}
		if(checkInput()) {
			
			passArr[game.getUser().getNumber() - 1] = false;
			makeUserMove();//display
		}
		else {
			if(firstMove == false && userFree == false) {
				cardPlayed.setText("Make sure your input is able to beat the last play. Player " + (game.getUser().getNumber()==1?4:game.getUser().getNumber()-1) + 
						" has just played "+ cardsPlayedArr.get(cardsPlayedArr.size()-1));
			}
		}
		
		//if(checkInput()) then reset all cardsSelected and buttonsSelected
	}
	public boolean checkInput() {
		if(firstMove) {
			
			boolean firstRank = false;
			
			for(int i = 0; i < cardsSelected.size(); i++) {
				if(cardsSelected.get(i).getRank() == 1) {
					firstRank = true;
					break;
				}
			}
			if(firstRank == false) {
				cardPlayed.setText("Please include Three of Diamonds in your selection.");
				return false;
			}
			//else
			
			cardPlayed.setText("You have played a Three of Diamonds ");
			for(int i = 0; i < cardsSelected.size(); i++) {
				cardsPlayedArr.add(cardsSelected.get(i));
				System.out.println(cardsSelected.get(i).getRank());
			}
			
			firstMove = false;
			return true;
		}
		else {
			//check if the card(s) selected is/are grater than the last card in cardsPlayed
			//this is bc cards will be added in ascending order so u gotta beat the highest added
			if(passButton.isDisabled() == true) { //free move
				if(cardsSelected.size() ==2) {
					if(cardsSelected.get(0).getNum().equals(cardsSelected.get(1).getNum()) ==false)
						return false;
				}
				passButton.setDisable(false);
				numCardPlay = cardsSelected.size();
				userFree = false;
				return true;
			}
			int highestRank = cardsSelected.get(0).getRank();
			for(int i = 0; i < cardsSelected.size(); i++) {
				if(cardsSelected.get(i).getRank() > highestRank) {
					highestRank = cardsSelected.get(i).getRank();
				}
			}
			
			if(numCardPlay == 1) {
				if(highestRank > cardsPlayedArr.get(cardsPlayedArr.size() - 1).getRank()) 
					return true;
				return false;
			}
			if(numCardPlay == 2) {
				if(cardsSelected.get(0).getNum().equals(cardsSelected.get(1).getNum())) {
					if(highestRank > cardsPlayedArr.get(cardsPlayedArr.size() - 1).getRank()) 
						return true;
					return false;
				}
			}
			return false;
		}
		//DISPLAY THE CARD(S) 
		//CLEAR THE CARDS AND BUTTONS SELECTED 
		//makeUserMove(card, currentButton); ==>CALL FROM SUBMIT OR ELSEWHERE MAYBE OR MAYBE HERE 
	}
	public void makeUserMove() {
		cardDisplayedArr[1].setImage(null);
		cardPlayed.setText(null);
		for(int i = 0; i < cardsSelected.size(); i++) {
			Deck cardsSelect =  new Deck(cardsSelected);
			cardsSelect.sort();	
			
			//display the card image 
			Image cardImg =  new Image("img/" + cardsSelected.get(i).getRank() + ".jpg", 45,60,false,true);
			cardDisplayedArr[i].setImage(cardImg);
	
			Text cardString = new Text("You have played " + cardsSelected.get(i).toString());
			cardString.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
			cardString.setFill(Color.WHITE);
			cardPlayed.setText(cardPlayed.getText() + " " + cardString.getText());
			cardsPlayedDisplay.getChildren().add(cardString);
			
		}
		//DELETE THE BUTTON
		for(int i = 0; i < cardsSelected.size(); i++) {
			cardsPlayedArr.add(cardsSelected.get(i));
			game.getUser().getHand().drawCard(cardsPlayedArr.get(cardsPlayedArr.size() -1));
			playersContainer[4].getChildren().remove(buttonsSelected.get(i));
		}
		//clear the selections
		cardsSelected.removeAll(cardsSelected);
		buttonsSelected.removeAll(buttonsSelected);
		
		if(game.getUser().getHand().getSize()== 0) {
			winner(game.getUser().getNumber());
		}
		//SHIFT ALL THE CARDS?
		
		makePlayerMove();
		/*
		 	go through all of the player containers --> this may be visually confusing 
		 	maybe make the players vertical as well? in the right side 
		 	
		 	loop through the ai players until it gets back to the user
		 	pass in the index that we are trying to select the play for 
		 	
		 	make the selection based on rank
		 	then display (perhaps separate function)
		 	
		 */
	}
	public void makePlayerMove() {
		//MAKE THIS ONLY WORK FOR ONE PLAYER ==> HAVE THE FOR LOOP BE EXTERNAL
		// THE NUMBER U PASS IN -> THE NUMBER OF PLAYER 
		//---> CORRESPOND THE PLAYER NUMBER TO ITS CONTAINER SOMEHOW AND PASS THAT NUMBER INTO THE DISPLAY FUNCTION TO DISPLAY
		
		if(firstMove) {
			Deck playerHand = game.getPlayer(1).getHand();
			Card draw = playerHand.drawCard(1);//THIS IS WHERE U EDIT WHAT U SELECT LOL
			cardsPlayedArr.add(draw);
			
			Text cardString = new Text("Player 1 has played " + draw.toString());
			cardString.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
			cardString.setFill(Color.WHITE);
			cardsPlayedDisplay.getChildren().add(cardString);
			int numCards = playerHand.getSize();
			playersContainer[0].getChildren().remove(numCards);
			
			
			for(int i = 1; i <4; i++) {
				
				if(playersByNum[i].getUser()==true) {
					firstMove = false;
					return;
				}
				playerHand = playersByNum[i].getHand();
				for(int j = 0; j < playerHand.getSize(); j++) {
					if(playerHand.getCardAt(j).compareTo(cardsPlayedArr.get(cardsPlayedArr.size() - 1)) > 0) {
						draw = playerHand.drawCardByIndex(j);
						break;
					}
						
				}
				cardsPlayedArr.add(draw);
				
				cardString = new Text("Player " + (i+1) +" has played " + draw.toString()); 
				cardString.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
				cardString.setFill(Color.WHITE);
				cardsPlayedDisplay.getChildren().add(cardString);
				
				numCards = playerHand.getSize();
				playersContainer[i].getChildren().remove(numCards);
				
			}
			
		}
		int numPlays = 1;
		int index = firstPlayerIndex;
		
		while(numPlays < 4) {
			//PLACE TIMER HERE! 
			
			Deck playerHand = playersByNum[index].getHand();
			ArrayList<Card> draw = makeMove(index);
			if(draw == null) {
				passArr[index] = true;
				Text cardString = new Text("Player " + (index+1) +" has passed their turn");
				cardString.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
				cardString.setFill(Color.WHITE);
				cardsPlayedDisplay.getChildren().add(cardString);
			}
			else {
				passArr[index] = false;

				int numCards = playerHand.getSize();  
				System.out.println("Numcards: " + numCards);
				
				
				for(int i = 0; i < draw.size(); i++) {
					cardsPlayedArr.add(draw.get(i));
					playersContainer[index].getChildren().remove(numCards);//WATCH IT HERE
					Text cardString = new Text("Player " + (index+1) +" has played " + draw.get(i).toString()); 
					cardString.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					cardString.setFill(Color.WHITE);
					cardsPlayedDisplay.getChildren().add(cardString);
					
				}
				
			}
			//check for a winner
			if(playerHand.getSize() == 0) { 
				winner(index+1);
				break;
			}
			index++;
			if(index == 4)
				index = 0;
			numPlays++;
		}

		
		//checks if the user has a free pass
		checkUserFree();
	
	}
	public ArrayList<Card> makeMove(int index) {
		Deck playerHand = playersByNum[index].getHand();
		
		boolean free = true;
		int numRounds = 0;
		int idx = index + 1;
		if(idx == 4) 
			idx = 0;
		while(numRounds<3) {
			if(passArr[idx] == false) {
				free = false;
				break;
			}
			idx++;
			if(idx == 4) 
				idx = 0;
			numRounds++;
		}
		//System.out.println(index + " " + free);
		
		Card card;
		ArrayList<Card> cardList = new ArrayList<Card>();
		if(free) {
			Text cardString = new Text("Player " + (index+1) +" has a free turn."); 
			cardString.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
			cardString.setFill(Color.WHITE);
			cardsPlayedDisplay.getChildren().add(cardString);
			int numPlay = (int)(Math.random() * 2);
			if(numPlay == 1) 
				numCardPlay = 2;
			else
				numCardPlay = 1;
			
			if(numCardPlay == 2) {
				Card[][] doublesArr = playerHand.getDoubles();
				if(doublesArr == null) {
					numCardPlay = 1;
				}
				else {
					cardString = new Text("Player " + (index+1) +" has declared doubles play."); 
					cardString.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
					cardString.setFill(Color.WHITE);
					cardsPlayedDisplay.getChildren().add(cardString);
					
					card = playerHand.drawCard(doublesArr[0][0].getRank());
					cardList.add(card);
					card = playerHand.drawCard(doublesArr[0][1].getRank());
					cardList.add(card);
					return cardList;
				}
			}
			if(numCardPlay == 1) {
				switch(aiLevel[index]) {
				case("a"): int rand  = (int)(Math.random() * (playerHand.getSize()));
							card = playerHand.drawCardByIndex(rand);
							cardList.add(card);
							return cardList;
				case("b"): card = playerHand.drawCard(); //MAYBE SMARTEN HIM UP TOO
							cardList.add(card);
							return cardList;
				case("c"): card = playerHand.drawCard(); //MAKE AI THREE BE THE SMARTEST AND PICK A DOUBLE OR TRIPLE 
							cardList.add(card);
							return cardList;
				}
			}
			
			
		}
		
		Card compare = cardsPlayedArr.get(cardsPlayedArr.size() - 1);	
		if(numCardPlay == 1) {
			switch(aiLevel[index]) {
			case("a"): for(int i = 0; i < playerHand.getSize(); i++) {
							card = playerHand.getCardAt(i);
							if(compare.compareTo(card) < 0) {
								playerHand.drawCard(card.getRank());
								cardList.add(card);
								return cardList;
							}
						}	
						break;
			case("b"): 	int lowestCardNum = playerHand.getSize();
						for(int i = 0; i < 4; i++) {
							if(lowestCardNum > playersByNum[i].getHand().getSize()) {
								lowestCardNum = playersByNum[i].getHand().getSize();
							}
						}
						for(int i = 0; i < playerHand.getSize(); i++) {
						card = playerHand.getCardAt(i);
							if(compare.compareTo(card) < 0 && (lowestCardNum<= 7?true:!playerHand.inDouble(card))) { // not in a double / triple until someone has less than 4 cards
								playerHand.drawCard(card.getRank());
								cardList.add(card);
								return cardList;
							}
						}	
						break;
			case("c"):lowestCardNum = playerHand.getSize();
					for(int i = 0; i < 4; i++) {
						if(lowestCardNum > playersByNum[i].getHand().getSize()) {
							lowestCardNum = playersByNum[i].getHand().getSize();
						}
					}
					// add if the size < 4 u can use the 2s  	
					for(int i = 0; i < playerHand.getSize(); i++) {
						card = playerHand.getCardAt(i);
						if(compare.compareTo(card) < 0 && (lowestCardNum < 6 ?true:!playerHand.inDouble(card))) { // not in a double / triple until someone has less than 4 cards
							if(card.getNum() == "Two" && lowestCardNum <= 5) 
								return null;
							playerHand.drawCard(card.getRank());
							cardList.add(card);
							return cardList;
						}
					}	
					break;
			}
			
			if(numCardPlay == 2) {
				Card[][] doublesArr = playerHand.getDoubles();
				System.out.println("Player " + (index+1) + "double array length " + doublesArr.length);
				if (doublesArr == null)
					return null;
				for(int i = 0; i < doublesArr.length; i++) {
					for(int j = 0; j < doublesArr[i].length; j++) {
						card = doublesArr[i][j];
						if(compare.compareTo(card) < 0) {
							if(doublesArr[i][0].compareTo(doublesArr[i][1]) > 0) {
								cardList.add(doublesArr[i][0]);
								cardList.add(doublesArr[i][1]);
								playerHand.drawCard(doublesArr[i][0]);
								playerHand.drawCard(doublesArr[i][1]);
							}
							else {
								cardList.add(doublesArr[i][1]);
								cardList.add(doublesArr[i][0]);
								playerHand.drawCard(doublesArr[i][1]);
								playerHand.drawCard(doublesArr[i][0]);
							}
							return cardList;
						}
						
						
					}
				}
			}
			System.out.println("The last card played is " + compare.toString());
			playerHand.sort(); //WE ARE GONNA DO THIS WHEN WE DETERMINE THE AI --> SILLY ONE AINT GETTIN SORTED LOLZ
			
			
			
			
			//we can mess around with how this happens:
			//AI1: FINDS THE SMALLEST RANK BIGGER THAN COMPARE; => DUMBEST 
			//AI2: FINDS THE FIRST RANK BIGGER THAN COMPARE
			//AI3: FINDS THE SMALLEST RANK BIGGER THAN COMPARE THAT ISNT PART OF A DOUBLE/TRIPLE/FOUR DEAL
					//SMARTEST ONE
				
		}
		return null;
	} 
	public void checkUserFree() {
		int idx = game.getUser().getNumber(); //this will give us the player after the user 
		int numRounds = 0;
		boolean free = true;
		if(idx == 4) 
			idx = 0;
		while(numRounds<3) {
			if(passArr[idx] == false) {
				free = false;
				break;
			}
			idx++;
			if(idx == 4) 
				idx = 0;
			numRounds++;
		}	
		if(free) {
			userFree = true;
			Text cardString = new Text("You have a free turn."); 
			cardString.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
			cardString.setFill(Color.WHITE);
			cardsPlayedDisplay.getChildren().add(cardString);
			passButton.setDisable(true);
		}
	}
	public Card[] getDouble(int rankToBeat) {
		return new Card[2];
	}
	public void winner(int playerNum) {
		//make playerNum an index --> playerNum-1
		cardPlayed.setText("PLAYER " + playerNum + " HAS WON");
		cardPlayed.setFont(Font.font("times new roman", FontWeight.BOLD, FontPosture.REGULAR, 28));
		cardPlayed.setFill(Color.WHITE);
		submitButton.setDisable(true);
		passButton.setDisable(true);
		
		//DISABLE ALL THE STUFFS --> MAYBE GET IT TO BE ABLE TO PLAY AGAIN??????
		
		//setUpBoard();
	}

	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}
