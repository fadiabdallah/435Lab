import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//whenever we press a key on keyboard this class is called 


public class KeyInput extends KeyAdapter{
	
	Game game;
	
	public KeyInput(Game game) {
		
		this.game = game;
		
		
	}
	
	
	
	
	public void keyPressed(KeyEvent e){
		
		game.keyPressed(e);
		
	}

	public void keyReleased (KeyEvent e) {
		
		game.keyReleased(e);
		
	}
	
}
