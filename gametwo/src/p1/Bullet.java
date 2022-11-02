package p1;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Bullet extends GameObject implements EntityA{
	
	


    private Textures tex;
	private Game game;
	
	public Bullet(double x, double y, Textures tex, Game game) {
		super(x, y);
		this.tex = tex;
		this.game = game;
		
		
	}
	
	
	public void tick() {
		
		y -= 10;
		
		
	
		
	}
	
	public Rectangle getBounds(int width, int height) {
		return new Rectangle((int)x, (int)y, width, height);
	}

	
	public void render (Graphics g) {
		g.drawImage(tex.missile, (int)x, (int)y, null );
		
	}
	
	public double getY() {
		return y;
	}


	public double getX() {

		return x;
	}



	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	

}
