package p1;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Player extends GameObject implements EntityA{
	
	private double velX =0;		//for the speed of shooter 
	private double velY =0;
	private Textures tex;
	
	Game game;
	Controller controller;
      //position of player (x,y) 

	

	
	
	
	

	
	
	
	public Player (double x, double y, Textures tex, Game game, Controller controller){
		
		super(x, y);
		this.tex =tex;
		this.game = game;
		this.controller = controller;
		
	
	}
	
	

	
	
	public void tick(){
		
//		x+= velX;		//for speed 
//		y+= velY;
//		
//		
//		
//		if(x<= 0) 
//			x=0;
//		
//		
//		if(x<= 640)  
//			x=640;
//		
//		
//		if(y<= 0) 
//			y=0;
//		
//		
//		if(y<= 480 -32) 
//			y=480 - 32;
		
		
		for(int i = 0; i < game.eb.size();i++) 
		{
			EntityB tempEnt = game.eb.get(i);
			
			if(Physics.Collision(this,  tempEnt)) 
			{
				
				Game.HEALTH -=1;
				//System.exit(0);

			}
		}
		if (Game.HEALTH==10) {
			System.exit(0);
		}

	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	
	public void render(Graphics g) {
		
		g.drawImage(tex.player, (int)x, (int)y, null); //drawImage only takes int types but x and y are double so we cast them as ints  
			
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
