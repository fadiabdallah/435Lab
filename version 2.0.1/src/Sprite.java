import java.awt.Image;

public class Sprite {

    private boolean visible;
    private Image image;
    private boolean dying;

    int x;
    int y;
    int dx;

    public Sprite() {

        visible = true;
    }
	
    public void die() {

        visible = false;
    }
	
    public boolean isVisible() {

        return visible;
    }

    protected void setVisible(boolean visible) {

        this.visible = visible;
    }
	/**
	 * insert images in game
	 **/
    public void setImage(Image image) {

        this.image = image;
    }
	/**
	 *fetch images from images file	 
	 **/
    public Image getImage() {

        return image;
    }
	/**
	 * set x
	 */
    public void setX(int x) {

        this.x = x;
    }
	/**
	 * Set y
	 */
    public void setY(int y) {

        this.y = y;
    }
	/**
	 * finds y position to run automation
	 */
    public int getY() {

        return y;
    }
	/**
	 * finds x position to run automation
	 */
    public int getX() {

        return x;
    }
/**
 * @param dying confirms the death of target
 */
    public void setDying(boolean dying) {

        this.dying = dying;
    }
	    public boolean isDying() {

        return this.dying;
    }
}