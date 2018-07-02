import java.util.ArrayList;
import java.util.Random;

/**
 * This is the main game class where the progression of the game occurs.
 *
 * @author kshah
 *
 */
public class Game {

	private static int dice1;
	private static int dice2;
	private static MonopolyBoard board;
	private static Player player1;
	private static Player player2;
	private static Player currentPlayer;
	private static Player otherPlayer;
	private static int thisOne;
	private static ConexionSerial conexion;
	private static boolean alreadyRolled = false;
	private static boolean paidRent = false;

	public Game(MonopolyBoard board, Player p1, Player p2, int currentOne, ConexionSerial connect) {
		Game.board = board;
		conexion = connect;
		thisOne = currentOne;
		player1 = p1;
		player1.setPlayerNum(1);
		player2 = p2;
		player2.setPlayerNum(2);
 		currentPlayer = p1;
 		otherPlayer = p2;
		p1.setTurn(true);
		board.changeTextArea(player1.getName() + " is the red one.\n" + player2.getName() + " is the blue one.\n\n");
		board.changeTextArea(player1.getName() + "'s properties will have red blocks\nand " 
					+ player2.getName() + "'s properties will have blue blocks on them\n\n");
		board.changeTextArea(player1.getName() + " has $" + player1.getCash() + "\n" + player2.getName()
					+ " has $" + player2.getCash() + "\n\n");
	}

	public static void roll() {
		Random gen = new Random();
		dice1 = gen.nextInt(6)+1;
		dice2 = gen.nextInt(6)+1;
		board.changeDice(dice1, dice2);
	}
	
	public static void endTurn() {
		//Only let the player end their turn if they have already rolled
		if(alreadyRolled) {
			board.changeTextArea(currentPlayer.getName() + " has $" + currentPlayer.getCash() + "\n");
			board.changeTextArea(currentPlayer.getName() + "'s turn ended.\n\n");
			if(player1.isTurn()) {
				player1.setTurn(false);
				player2.setTurn(true);
				currentPlayer = player2;
				otherPlayer = player1;
			}
			else if(player2.isTurn()) {
				player1.setTurn(true);
				player2.setTurn(false);
				currentPlayer = player1;
				otherPlayer = player2;
			}
			board.changeDice(0, 0);
			alreadyRolled = false;
			paidRent = false;
		}
		//Otherwise, tell them to roll.
		else {
			board.changeTextArea(currentPlayer.getName() + " please roll first\n");
		}
		board.updateInformation(player1.getCash(), player2.getCash());
	}
	
	public static void buy() {
		//A player can only buy a property if they have already rolled
		if(alreadyRolled) {
			int currentSpace = currentPlayer.getSpaceNum();
			//This checks if the property is actually ownable.
			if(BoardSpace.getSpaceAttribute(currentSpace, 0)!=-1) {
				//If the property isn't owned
				if(BoardSpace.getSpaceAttribute(currentSpace, 0) == 0) {
					int cost = BoardSpace.getSpaceAttribute(currentSpace, 1);
					//If they have enough money
					if(currentPlayer.getCash()-cost>=0) {
						currentPlayer.changeCash(-cost);
						BoardSpace.setSpaceAttribute(currentSpace, 0, currentPlayer.getPlayerNum());
						board.changeTextArea(currentPlayer.getName() + " just purchased " 
								+ BoardSpace.getName(currentSpace) + " for $" + cost + "\n");
						board.placeOwnerToken(currentSpace, currentPlayer.getPlayerNum());
						board.updateInformation(player1.getCash(), player2.getCash());
					}
					//Otherwise tell them they don't.
					else
						board.changeTextArea("You cannot afford this property.\n");
				}
				//Tell them that the property is owned
				else {
					if(BoardSpace.getSpaceAttribute(currentSpace, 0) == currentPlayer.getPlayerNum())
						board.changeTextArea("Property already owned by you\n");
					else
						board.changeTextArea("Property already owned by " + otherPlayer.getName() + "\n");
				}
			}
			//Otherwise tell the player that the property is now ownable.
			else {
				board.changeTextArea("This space cannot be purchased.\n");
			}
		}
		//Otherwise, tell them to roll.
		else {
			board.changeTextArea(currentPlayer.getName() + " please roll first\n");
		}
	}
	
	public static void dice(){
		//If the player hasn't already rolled
		if(!alreadyRolled) {
			roll();
			board.changeTextArea(currentPlayer.getName() + " rolled a " + (dice1+dice2) + "\n");
			int temp = dice1+dice2 + currentPlayer.getSpaceNum();
			//If the dice total + player space exceeds 39, which
			//is boardwalk, then the space should be set to a 0, and
			//200 should be added to the player total.
			if(temp > 39) {	
				temp%=40;
				currentPlayer.changeCash(200);
			}
			//Set the player space to the new space.
			currentPlayer.setSpaceNum(temp);
			board.changeTextArea(currentPlayer.getName() + " landed on " + BoardSpace.getName(temp) + "\n");
			//Move the player token to the new place.
			board.moveToken(currentPlayer.getSpaceNum(), currentPlayer.getPlayerNum());
			alreadyRolled=true;
		}
		//Otherwise tell them they have already rolled.
		else 
			board.changeTextArea("You have already rolled\n");
		//CREATE A NEW METHOD THAT DEALS WITH EXCEPTIONS. SAME AS THE EVALUATE METHOD HERE, WITHOUT BUY.
		evaluateBoard();
		board.updateInformation(player1.getCash(), player2.getCash());
	}

	public static void evaluateBoard() {
		if(!paidRent) {
			int rent;
			int owner = BoardSpace.getSpaceAttribute(currentPlayer.getSpaceNum(), 0);
			//First of all, handling the rent condition
			if(owner>0) {
				//If the current player is not the owner of the property and
				//he hasn't paid rent yet.
				if(currentPlayer.getPlayerNum()!=owner && !paidRent) {
					rent = BoardSpace.getSpaceAttribute(currentPlayer.getSpaceNum(), 2);
					currentPlayer.changeCash(-rent);
					otherPlayer.changeCash(rent);
					board.changeTextArea(currentPlayer.getName() + " just payed " + rent + " to " + otherPlayer.getName() + "\n");
					paidRent = true;
				}
			}
			//Then handling all of the other spaces, such as GO and freeparking.
			else if(owner == -1) {
				int tempSpace = currentPlayer.getSpaceNum();
				int money = BoardSpace.getSpaceAttribute(currentPlayer.getSpaceNum(), 2);
				//If the space is either go or free parking, add the money.
				if(tempSpace == 0 || tempSpace == 20) {
					board.changeTextArea("You earned " + Math.abs(money) +"\n");
					currentPlayer.changeCash(money);
					paidRent = true;
				}
				else if(tempSpace == 4 || tempSpace == 38) {
					board.changeTextArea("You lost " + Math.abs(money) + "\n");
					currentPlayer.changeCash(money);
					paidRent = true;
				}
				else if(tempSpace == 30) {
					currentPlayer.changeCash(money);
					board.moveToken(10, currentPlayer.getPlayerNum());
					board.changeTextArea("You paid " + Math.abs(money) + "\n");
					paidRent = true;
				}
			}
		}
	}
	
	
	public static void myProperties() {
		// TODO Auto-generated method stub
		ArrayList<String> properties = new ArrayList<String>();
		for(int a = 0; a<40; a++) {
			if(BoardSpace.getSpaceAttribute(a, 0) == currentPlayer.getPlayerNum()) {
				properties.add(BoardSpace.getName(a));
			}
		}
		if(!properties.isEmpty()) {
			board.changeTextArea("Your Properties are: \n");
			for(String c:properties) {
				board.changeTextArea("   " + c + "\n");
			} 
		}
		else
			board.changeTextArea("You don't own any properties\n");
	}
}
