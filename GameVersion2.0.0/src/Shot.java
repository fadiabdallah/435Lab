

import javax.swing.ImageIcon;
/**
 * Class responsible for shot from player to target
 * @param x,y are the coordinates of the shot to be illustrated 
 */
public class Shot extends Sprite {

    public Shot() {
    }

    public Shot(int x, int y) {

        initShot(x, y);
    }

    private void initShot(int x, int y) {

        var shotImg = "Game3\\images\\shot.png";
        var ii = new ImageIcon(shotImg);
        setImage(ii.getImage());
        

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}