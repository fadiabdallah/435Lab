package p1;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;





public class Game extends Canvas implements Runnable {
	
	public int angle;
	public int level;
	public int score; 
	public static final long serialVersionUID= 1l;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12 * 9; 
	public static final int SCALE=2;
	public final String TITLE ="shooting game";
	
	private boolean running =false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	
	private BufferedImage background = null;
	
	private BufferedImage player;
	
	
	
	private boolean is_shooting = false;	//ma feena to shoot same place wara baaed
	
	
	private int enemy_count = 5;
	private int enemy_killed = 0;
	
	public Game(int angle , int level) {this.angle=angle;
	this.level=level;}
//	public void addangle() {
//		System.out.println("hi2");
//		if (this.angle<17)
//			this.angle+=1;
//		
//	}
//	public void lowerangle() {
//		if (this.angle>-17)
//			this.angle-=1;
		
	
	
	public int getEnemy_count() {
		return enemy_count;
	}



	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}



	public int getEnemy_killed() {
		return enemy_killed;
	}



	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}

	private Player p;
	private Controller c;
	private Textures tex;
	private Menu menu;
	
	
	public LinkedList<EntityA>ea;
	public LinkedList<EntityB>eb;
	
	
	public static int HEALTH = 100 * 2;
	
	
	public static enum STATE{
		MENU,
		GAME
	};
	
	public static STATE State = STATE.MENU;
	
	
	public void init() {
		requestFocus();   //helps the icon move without pressing on window screen
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			
			spriteSheet = loader.loadImage("/sahar.png"); //load the shooter from res file
			background = loader.loadImage("/background.png"); //load background image
			
		}catch(IOException e){
			
			e.printStackTrace();
			
		}
		//addKeyListener(new KeyInput(this));  //p is the shooter loaded 
		
		tex= new Textures(this);
		
		
		

		c = new Controller(tex, this);
		p= new Player (320, 435, tex, this, c);
		menu = new Menu();
		
		ea = c.getEntityA();
		eb = c.getEntityB();
		
		
		
		this.addKeyListener(new KeyInput(this));
		this.addMouseListener(new MouseInput());
		
		
		c.createEnemy(enemy_count,angle,level);
		
		
	}
	
	
	
	public synchronized void start() {
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
				delta += (now - lastTime ) / ns;
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
					System.out.println(this.HEALTH);

					updates = 0;    
					frames = 0;
				}
				
				
			}
			
			stop();
	}
	
	private void tick() {
		if(State == STATE.GAME) {
			p.tick();
			c.tick();
		}
		
		if(enemy_killed >= enemy_count) {
			enemy_count +=2;
			enemy_killed = 0;
			c.createEnemy(enemy_count,angle,level);
			
		}
		
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
		g.drawImage(background, 0, 0, null);
		
		if(State == STATE.GAME) {
		p.render(g);
		c.render(g);
		
		g.setColor(Color.gray);				//health bar
		g.fillRect(5,  5,  200,  50);
		
		
		g.setColor(Color.green);
		g.fillRect(5,  5,  HEALTH,  50);
		
		g.setColor(Color.white);
		g.drawRect(5,  5,  HEALTH,  50);
		
		
		}else if(State == STATE.MENU) {
			menu.render(g);
			
		}
		
		g.dispose();
		bs.show();
		
	}
	
	
	public void keyPressed(KeyEvent e){
		
		int key = e.getKeyCode();
		if(State == STATE.GAME) {
		if (key == KeyEvent.VK_RIGHT ) {
			p.setX(p.getX() + 15);
		} else if (key == KeyEvent.VK_LEFT) {			//use right, left, up, down for directions of shooter 
			p.setX(p.getX() - 15);
		}else if (key == KeyEvent.VK_DOWN) {
			p.setX(p.getY() + 15);
		}else if (key == KeyEvent.VK_UP) {
			p.setX(p.getY() - 15);
		}else if (key == KeyEvent.VK_SPACE && !is_shooting) {
			c.addEntity(new Bullet(p.getX(), p.getY(), tex, this));
			is_shooting = true;
					//space for bullet shooting 
		}}
		
		
		
	}

	public void keyReleased (KeyEvent e) {
		int key = e.getKeyCode(); 
		if(key == KeyEvent.VK_RIGHT){
			p.setVelX(0);
		}else if (key == KeyEvent.VK_LEFT){ 
			p.setVelX(0);
		}else if (key == KeyEvent.VK_DOWN){ 
			p.setVelY(0);
		}else if (key == KeyEvent.VK_UP){ 
			p.setVelY(0);
		}else if (key == KeyEvent.VK_SPACE){ 
			is_shooting = false;
		}


		
		
	} 
	
	
	
	
	public static void main (String args[]) {
		Game game =new Game(0
				,1);
		
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
