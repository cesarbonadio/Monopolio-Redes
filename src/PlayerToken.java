import java.awt.*;
import javax.swing.*;

/**
 * The class that represents the player animation sprite
 * 
 * @1007 Homework #5 Source Code - Don Yu
 */
public class PlayerToken implements Icon {
	
	/** The sprite that represents the player */
	private Sprite sprite;
	/** The current x location of the player */ 
	private int x;
	/** The current y location of the player */
	private int y;
	
	private int path;
	
	//COORDINATES OF EVERY SPACE FOR PATH 1
	final int[] path1BoardXCoordinates = {700,610,550,490,430,370,310,250,190,130, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15,15,130,190,250,310,370,430,490,550,610,710,710,710,710,710,710,710,710,710,710}; 
	final int[] path1BoardYCoordinates = {740,740,740,740,740,740,740,740,740,740,740,640,580,520,460,400,340,280,220,160,40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40,160,220,280,340,400,460,520,580,640};
	
	//COORDINATES OF EVERY SPACE FOR PATH 2
	final int[] path2BoardXCoordinates = {700,610,550,490,430,370,310,250,190,130, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50,50,130,190,250,310,370,430,490,550,610,675,675,675,675,675,675,675,675,675,675}; 
	final int[] path2BoardYCoordinates = {700,700,700,700,700,700,700,700,700,700,700,640,580,520,460,400,340,280,220,160,80, 80, 80, 80, 80, 80, 80, 80, 80, 80, 80,160,220,280,340,400,460,520,580,640};
	/**
	 * Create a new player token
	 *  
	 * @param game The game in which the ship is being created
	 * @param x The initial x location of the player
	 * @param y The initial y location of the player
	 */
	public PlayerToken(String token, int x, int y, int path) {
		Image image = new ImageIcon(token).getImage();
		
		this.sprite = new Sprite(image);
		this.x = x;
		this.y = y;
		this.path = path;
	}
	
	/**
	 * translating the token based on its squareNumber
	 */
	public void translate(int spaceNumber) {
		//If you want to take path 1, use the coordinates for path 1
		if(path == 1) {
			x = path1BoardXCoordinates[spaceNumber];
			y = path1BoardYCoordinates[spaceNumber];
		}
		//Otherwise use path 2
		else {
			x = path2BoardXCoordinates[spaceNumber];
			y = path2BoardYCoordinates[spaceNumber];
		}
	}

	/**
	 * Get the height of this icon which should be the entire screen
	 * @return the height of the icon
	 */
	public int getIconHeight() {
		return 750;
	}

	/**
	 * Get the width of this icon which should cover the entire screen
	 * @return the width of the icon
	 */
	public int getIconWidth() {
		return 750;
	}

	/**
	 * Draw this player to the graphics context provided
	 * @param g The graphics context on which to draw
	 */
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		sprite.draw(g2, this.x, this.y);
	}

}
