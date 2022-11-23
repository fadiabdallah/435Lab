import javax.swing.ImageIcon;



import java.awt.event.KeyEvent;

public class Player extends Sprite {

    @SuppressWarnings("unused")
	private int width;
    private boolean auto= false;
    public Player() {
    	// set visible ==true 
        initPlayer();
    }

    private void initPlayer() {
   
        var playerImg = "Game3\\images\\player.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        int START_X = 210;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);
    }

    public void act() {
    	//System.out.println("function act is called");
        x += dx;
        if (auto==true)dx=0;

        if (x <= 2) {
        	System.out.println("touched the boundaries");
            x = 2;
        }

        if (x >= 415) {
        	System.out.println("touched the boundaries");
            x =415;
        }
    }

    public void keyPressed(KeyEvent e) {
    	//System.out.println(e.getKeyCode());
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = -8;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 8;
        }
    }
    public void act1( int direction, Frame Board, Game alien) {
    	System.out.println("direction is :"+direction);
    	if ( auto==false)auto=true;
    	if (direction==3) {
    		
    		if(x>230) {
    			dx=-8;
    			Board.follow(false);
    			return;
    		}
    		else if (x<200) {
    			dx=8;
    			Board.follow(false);
    			return;
    		}
    		Board.follow(true);
			
    		
    	}
    	if (direction==2||direction==1) {
    		if(x<alien.x-5&&x>alien.x-15) {
    			dx=0;
    			Board.follow(true);
    		}
    		else if (x>alien.x-10) {
    			dx=-8;
    			Board.follow(false);
    			return;
    		}
    		else {
    			dx=8;
    			Board.follow(false);
    			return;
    		}
    		
    	}
    	if (direction==0) {
    		if(x<alien.x-5&&x>alien.x-20) {
    			dx=0;
    			Board.follow(true);
    		}
    		else if (x>alien.x-5) {
    			dx=-8;
    			Board.follow(false);
    			return;
    		}
    		else {
    			dx=8;
    			Board.follow(false);
    			return;
    		}
    		
    	}
    	if (direction==6) {
    		if(x>alien.x+30&&x<alien.x+35) {
    			dx=0;
    			Board.follow(true);
    		}
    		else if (x>alien.x+40) {
    			dx=-8;
    			Board.follow(false);
    			return;
    		}
    		else if (x<alien.x+30){
    			dx=8;
    			Board.follow(false);
    			return;
    		}
    		
    	}
    	if (direction==5||direction==4) {
    		if(x>alien.x+20&&x<alien.x+25) {
    			dx=0;
    			System.out.println("we are here yow");
    			Board.follow(true);
    		}
    		else if (x>alien.x+50) {
    			dx=-8;
    			Board.follow(false);
    			return;
    		}
    		else if ( x<alien.x+20) {
    			dx=8;
    			Board.follow(false);
    			return;
    		}
    		
    	}
    }

   public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {

            dx = 0;
        }
    }
}