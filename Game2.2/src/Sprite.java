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
	/**
	 * responsible for the dying sfx and animation
	 */
    public void die() {

        visible = false;
    }
	/**
	 * A class that will make objects visible for players
	 * 
	 */
    public boolean isVisible() {

        return visible;
    }

    protected void setVisible(boolean visible) {

        this.visible = visible;
    }
	/**
	 * Class that will enable images in our game
	 */
    public void setImage(Image image) {

        this.image = image;
    }
	/**
	 * class that gets the images from the folders required
	 */
    public Image getImage() {

        return image;
    }
	/**
	 * Set the x component to a specific value
	 */
    public void setX(int x) {

        this.x = x;
    }
	/**
	 * Set the y component to a specific value
	 */
    public void setY(int y) {

        this.y = y;
    }
	/**
	 * Gets us the value of Y in order to move the player and the aliens
	 */
    public int getY() {

        return y;
    }
	/**
	 * Gets us the value of X in order to move the player and the aliens
	 */
    public int getX() {

        return x;
    }
/**
 * helps us keep track the number of dead aliens
 * @param dying parameter that indicates if the alien died or not
 */
    public void setDying(boolean dying) {

        this.dying = dying;
    }
	/**
	 * enables the sfx for dying aliens once shot
	 */
    public boolean isDying() {

        return this.dying;
    }
}