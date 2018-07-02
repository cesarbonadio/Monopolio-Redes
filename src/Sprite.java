
import java.awt.Graphics2D;
import java.awt.Image;

/**
 * A sprite to be displayed on the screen. Note that a sprite
 * contains no state information, i.e. it's just the set of images and 
 * not the location.
 * 
 * @W1007 Homework #5 Source Code - Don Yu
 */
public class Sprite {
	
	/** The current image that should be displayed */
	private Image image;
	
	/**
	 * Create a new sprite based on an image
	 * @param image The image that is this sprite
	 */
	public Sprite(Image image) {
		this.image = image;
	}
	
	/**
	 * Get the width of the drawn sprite
	 * @return The width in pixels of this sprite
	 */
	public int getWidth() {
		return image.getWidth(null);
	}

	/**
	 * Get the height of the drawn sprite
	 * @return The height in pixels of this sprite
	 */
	public int getHeight() {
		return image.getHeight(null);
	}
	
	/**
	 * Draw the sprite onto the graphics context provided
	 * @param g The graphics context on which to draw the sprite
	 * @param x The x location at which to draw the sprite
	 * @param y The y location at which to draw the sprite
	 */
	public void draw(Graphics2D g2,int x,int y) {
		g2.drawImage(image,x,y, null);
	}
	
	/**
	 * This method changes the image to a new image.
	 * @param image
	 */
	public void changeImage(Image image) {
		this.image = image;
	}
}
