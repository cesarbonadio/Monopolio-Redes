/**
 * This class is used to represent the player in this game. 
 * @author kshah
 *
 */
public class Player {
	private int totalCash;
	private int totalAssets;
	private int spaceNum;
	private int playerNum;
	private boolean isTurn = false;
	private String name;
	
	public Player(int initCash, String name) {
		totalCash = initCash;
		this.name = name;
	}
	
	public int getCash() {
		return totalCash;
	}
	
	public void changeCash(int cash) {
		totalCash+=cash;
	}
	
	public int getTotalAssets() {
		return totalAssets;
	}
	
	public void setTurn(boolean turn) {
		isTurn = turn;
	}
	
	public boolean isTurn() {
		return isTurn;
	}

	public int getSpaceNum() {
		return spaceNum;
	}

	public void setSpaceNum(int spaceNum) {
		this.spaceNum = spaceNum;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public String getName() {
		return name;
	}
}
