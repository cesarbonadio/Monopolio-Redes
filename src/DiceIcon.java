/**
 * This class is for the dice token. 
 * @author kshah
 *
 */
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.*;

public class DiceIcon implements Icon {
	
	
	/** The sprite that represents the dice */
	private Sprite sprite;
	/** The current x location of the dice */ 
	private int x;
	/** The current y location of the dice */
	private int y;
	
	public DiceIcon(String dice, int x, int y) {
		Image image = new ImageIcon(dice).getImage();
		
		this.sprite = new Sprite(image);
		this.x = x;
		this.y = y;
	}

	@Override
	public int getIconHeight() {
		// TODO Auto-generated method stub
		return 196;
	}

	@Override
	public int getIconWidth() {
		// TODO Auto-generated method stub
		return 68;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;
		sprite.draw(g2, this.x, this.y);
	}
	
	/**
	 * This method is used to change the image of the dice
	 * after the dice have been created.
	 * @param image, the image to be changed to.
	 */
	public void changeDice(String image) {
		sprite.changeImage(new ImageIcon(image).getImage());
	}
}
