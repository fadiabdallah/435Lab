import javax.swing.ImageIcon;

public class Game extends Sprite {

   
	public int  direction;
	/**
	 * creates targets in different directions
	 * 
	 */
    public Game(int x, int y , int direction) {
    	this.direction=direction;
    	
        initAlien(x, y);
    }
	/**
	 * @param x,y coordinates of target
	 */
    private void initAlien(int x, int y) {

        this.x = x;
        this.y = y;

      

        var alienImg = "Game3\\images\\alien1.png";
        var ii = new ImageIcon(alienImg);
        

        setImage(ii.getImage());
    }

    public void act() {
    	variables.alienTimer++;
    	
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
    	this.y-=5;
    }
   
}