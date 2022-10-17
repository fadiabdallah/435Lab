import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;




public class Game extends Canvas implements Runnable {
	
	
	public static final long serialVersionUID= 1l;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12 * 9; 
	public static final int SCALE=2;
	public final String TITLE ="shooting game";
	
	private boolean running =false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	
	private BufferedImage player;
	
	
	
	private Player p;
	
	
	
	public void init() {
		requestFocus();   //helps the icon move without pressing on window screen
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("/sahar.png"); //load the shooter from res file
			
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
		addKeyListener(new KeyInput(this));  //p is the shooter loaded 
		p= new Player (200, 200, this);
		//(320, 435, this)

		
	}
	
	
	
	private synchronized void start() {
		if (running)
			return; //exit method 
		
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
		
	private synchronized void stop() {
		if (!running)
			return;	
		
		
		running = false;
		try {
			thread.join();  //joins all threads together and waits them to die 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
		
	}
	
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta =0; 
		int updates=0;
		int frames=0;
		long timer = System.currentTimeMillis();
		
		
			while(running) {
				
				
				// game loop
				
				
				
				
				long now = System.nanoTime();
				delta += (lastTime - now) / ns;
				lastTime = now;
				
				if (delta>= 1){
					tick();
					updates++;
					delta--;
				} 
				render();
				frames++;
				
				
				if(System.currentTimeMillis()  - timer > 1000){ 
					timer += 1000;  
					System.out.println(updates + "Ticks, Fps " + frames);   
					updates = 0;    
					frames = 0;
				}
				
				
			}
			
			stop();
	}
	
	private void tick() {
		
		p.tick();
	}
	
	
	private void render() {
		
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) { 
			createBufferStrategy(3); //3 buffers 
			return;
				
			
		}
		
		Graphics g = bs.getDrawGraphics();
		//////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		g.drawImage(player,  100,  100, this);
		
		p.render(g);
		
		
		g.dispose();
		bs.show();
		
	}
	
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_RIGHT ) {
			p.setX(p.getX() + 5);
		} else if (key == KeyEvent.VK_LEFT) {			//use right, left, up, down for directions of shooter 
			p.setX(p.getX() - 5);
		}else if (key == KeyEvent.VK_DOWN) {
			p.setX(p.getY() + 5);
		}else if (key == KeyEvent.VK_UP) {
			p.setX(p.getY() - 5);
		}
		
		
		
	}

	public void keyReleased (KeyEvent e) {
		

		
		
	} 
	
	
	
	
	public static void main (String args[]) {
		Game game =new Game();
		
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE)) ;
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE)) ;
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE)) ;
		
		
																					//size of game window 
		
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}

	public BufferedImage getSpriteSheet() {
		
		return spriteSheet;
	}
	

}
