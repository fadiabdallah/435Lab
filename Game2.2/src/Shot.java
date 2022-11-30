

import javax.swing.ImageIcon;
/**
 * This is a class that illustrates and makes the sound when the player shoots a bullet on the alien
 * @param x,y are the coordinates of the shot to be illustrated 
 */
public class Shot extends Sprite {

    public Shot() {
    }

    public Shot(int x, int y) {

        initShot(x, y);
        
    }

    private void initShot(int x, int y) {

        var shotImg = ".\\images\\shott.png";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());
      

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}