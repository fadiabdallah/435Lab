import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {
	
	private double velX =0;		//for the speed of shooter 
	private double velY =0;
	
	
	private double x;      //position of player (x,y) 
	private double y;
	

	
	private BufferedImage player;
	
	public Player (double x, double y, Game game) {
		
		this.x = x;   
		this.y = y;
		
		SpriteSheet ss = new SpriteSheet (game.getSpriteSheet());
		player = ss.grabImage(1, 1, 32, 32);		

	}
	
	

	
	
	public void tick(){
		
		x+= velX;
		y+= velY;
		
		
		
		if(x<= 0) 
			x=0;
		
		
		if(x<= 640)  
			x=640;
		
		
		if(y<= 0) 
			y=0;
		
		
		if(y<= 480 -32) 
			y=480 - 32;

	}
	
	
	public void render(Graphics g) {
		
		g.drawImage(player, (int)x, (int)y, null); //drawImage only takes int types but x and y are double so we cast them as ints  
			
	}
	
	
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x){ 
		this.x = x; 
	}
	
	public void setY(double y){ 
		this.y = y; 
	}
	
	public void setVelX(double velX){ 				//setters
		this.velX = velX;
	}
	
	public void setVelY(double velY){ 
		this.velY = velY;
	}
	
	

}
