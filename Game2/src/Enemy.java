import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends GameObject implements EntityB{
	


	
	
	
	private Textures tex ;
	Random r = new Random();
	private int speed = r.nextInt(3)+1;		//targets will be of diff speeds
	

	
   public Enemy(double x, double y, Textures tex){
	   super(x, y); 
	   this.tex = tex;
	   }
	   
	   
	public void tick(){
		
		y += speed;		//targets will be of diff speeds
		

		
		if(y > (Game.HEIGHT * Game.SCALE)) { 		//when target reaches the end it will go back up
			x = r.nextInt(640);
			y=-10;
			}
		
	}
	
    public void render(Graphics g){
    	g.drawImage(tex.enemy, (int)x, (int)y, null);
    	
    }

    public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	


	public double getX() {

		return x;
	}
	
	public double getY() {
		return y;
	}
}