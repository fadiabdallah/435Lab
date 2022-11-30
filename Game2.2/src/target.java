import javax.swing.ImageIcon;

public class target extends Sprite {

	private Bomb bomb;
	private Bomb bomb1;
	public int  direction;
	/**
	 * the Alien constructor is used to generate multiple aliens throughout the game in several direction originating from the same point.
	 * @param int x specifies the x coordinate
	 * @param int y  the y coordinate and the 
	 * @param int direction specififes the angle of the launch of the target
	 * 
	 */
    public target(int x, int y , int direction) {
    	this.direction=direction;
    	
        initAlien(x, y);
    }
	/**
	 * @param x,y are the coordinates the alien is going to go in.
	 */
    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        bomb1 = new Bomb(x, y);
        var alienImg = ".\\images\\alien1.png";
        var ii = new ImageIcon(alienImg);
        

        setImage(ii.getImage());
    }

    public void act() {
    	varaibles.alienTimer++;
    	/**
    	 * 
    	 * 
    	 * 
    	 * 
    	 * Specify speed of moving object based on the directions ; movement through x and y is proportional to directions ; 
		 * 7 directions are possible ie : THE ANGLES ASKED IN THE DOCUMENT ;
		 * when the direction is 0 the target goes straight and the movement is only through the y axis on dir = 3 
    	 */
    
 
    	if (direction==3) {
    		this.y-=5;
    		return;
    	}
    	else if ( direction==2) {
    		this.x=this.x-2;
    		this.y-=4;
    		return;
    	}
    	else if ( direction==1) {
    		this.x=this.x-2;
    		this.y-=3;
    		return;
    		
    	}
    	else if ( direction==0) {
    		this.x=this.x-3;
    		this.y-=2;
    		return;
    	}
    	else if ( direction==6) {
    		this.x=this.x+3;
    		this.y-=2;
    		return;
    	}
    	else if ( direction==5) {
    		this.x=this.x+3;
    		this.y-=3;
    		return;
    	}
    	else if ( direction==4) {
    		this.x=this.x+1;
    		this.y-=4;
    		return;
    	}
    	else if (direction==7) {
    	
    		this.x=this.x+4;
    		this.y-=-4;
		}
    	else if (direction==8) {
    		System.out.println("dirrection is 8 ");
    		this.x=this.x-4;
    		this.y-=-4;
		}
    	this.y-=5;
    }
    public Bomb getBomb() {

        return bomb;
    }
    public Bomb getBomb1() {

        return bomb1;
    }
    public class Bomb extends Sprite {
    	/**
    	 * 
    	 * 
    	 * 
    	 * 
    	 * this class refers to the obstacle that gets down of the TARGETS 
		 * 
		 * 
    	 */

        private boolean destroyed;

        public Bomb(int x, int y) {

            initBomb(x, y);
        }

        public void initBomb(int x, int y) {

            setDestroyed(true);

            this.x = x;
            this.y = y;

            var bombImg = ".\\images\\rsz_bomb1.png";
            var ii = new ImageIcon(bombImg);
            setImage(ii.getImage());
        }

        public void setDestroyed(boolean destroyed) {

            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {

            return destroyed;
        }
    }
   
}