import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

@SuppressWarnings("serial")
public class game extends JPanel {
	int AlienCreated=0;
	int alienShoted=0;
	Random random= new Random();
    private Dimension d;
   
    private Player player;
    private target alien;
    private Shot shot;									
    private Shot shot1;			
    private double deaths = 0;
    private boolean inGame = true;
    private Timer timer;
    private  int ShotLeft=2;
    double shotCounter=0;
    String url = "jdbc:sqlite:./db/test.db";
    String url1 = "jdbc:sqlite:./db/test1.db";

    /**
     * The Board class is the initial UI of our game. Once the user logged in the board will pop up with the according parameters that will 
     * help the player know where he is in the game in terms of progress.
     */
    public game() {

        initBoard();
        gameInit();
    }
/**
 * The initBoard is  responsible of displaying the board to the player
 */
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(varaibles.BOARD_WIDTH+100, varaibles.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(varaibles.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }
/**
 * gameInit will spawn the player and the following aliens that the player has to kill in all random directions depending on the 
 * number of aliens the user specified he wanted to kill.
 */

    private void gameInit() {
    	/**
    	 * 
    	 * 
    	 * 
    	 * 
    	 * CREATE a player , enemy and 2 shots to set them visible later ; 
    	 */

      
    	alien = new target(200,240,random.nextInt(8));
    	AlienCreated=1;
        player = new Player();
        shot = new Shot();
      
        shot1=new Shot();
    }

    
    private void drawPlayer(Graphics g) {

        if (player.isVisible()) {

            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }
       
        if (player.isDying()) {

            player.die();
            inGame = false;
        }
    }
    private void gameOver(Graphics g) throws SQLException {

        g.setColor(Color.black);
        g.fillRect(0, 0, varaibles.BOARD_WIDTH, varaibles.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(90, varaibles.BOARD_WIDTH / 2 - 60, varaibles.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(90, varaibles.BOARD_WIDTH / 2 - 60, varaibles.BOARD_WIDTH - 100, 50);
        
        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        Double score=(deaths/shotCounter)*200;
        
       
        Connection conn = DriverManager.getConnection(url1);
        Statement stmt =conn.createStatement();
        ResultSet currentHightScore= stmt.executeQuery("select * from Users where userName='"+varaibles.User+"'");
        int currentScore=-1;
        try {
    	    currentScore=currentHightScore.getInt("hightScore");
           System.out.println("old high score is: " +currentScore);
    
	} catch (Exception e) {
		System.out.println("do nothing");
	}
       
        if (currentScore<score) {
        	  
              stmt =conn.createStatement();
        	
             
        	try {
            conn.createStatement().execute("insert into  Users values(1,'"+varaibles.User+"','pass',"+score.intValue()+")");
        	}
        	catch (Exception e) {
                conn.createStatement().execute("update Users set hightScore="+score.intValue()+" where userName='"+varaibles.User+"'");
			}
        	
        }
        //varaibles.freakMode=false;
        gameOptions start= new gameOptions();
 		start.setVisible(true);
 		Window win = SwingUtilities.getWindowAncestor(this);
 		win.dispose();
        
       

    }
    public void  follow(boolean shoot) {
    	/**
    	 * 
    	 * 
    	 * 
    	 * shooting akes the shot visible and decrease the 2 as a mazimum to 1 and increases the shot counter 
    	 * 
    	 */
   	 int x = player.getX();
        int y = player.getY();
     if(shoot==true){
   	if (inGame) {
   		

           if (!shot.isVisible()&& ShotLeft>0) {
           	ShotLeft--;
           	shotCounter++;
           	
           
               shot = new Shot(x, y);
           }
           
       }
   }
   }
    private void drawAlien(Graphics g) {
    	/**
    	 * 
    	 * 
    	 * WHen alien dies ; a new one is intitated with keeping vount of the total numbers 
    	 */
    	if(alien.isDying()) {
    		
    		 g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
    		 int ra=random.nextInt(7);
         
         	alien=new target(200,240,ra);
         	AlienCreated++;
     
         	return;
    	}
    	if (alien.isVisible()) {

            g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
           
        }
    	else {
     
        	int ra=random.nextInt(8);
        
        	alien=new target(200,240,ra);
        	AlienCreated++;
        	
        }
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
        if (shot1.isVisible()) {

            g.drawImage(shot1.getImage(), shot1.getX(), shot1.getY(), this);
        }
        
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    private void drawBombing(Graphics g) {
        target.Bomb b = alien.getBomb();
        if (!b.isDestroyed()) {
            g.drawImage(b.getImage(), b.getX(), b.getY(), this);
          
        }
        target.Bomb b1 = alien.getBomb1();
        if (!b1.isDestroyed()) {
            g.drawImage(b1.getImage(), b1.getX(), b1.getY(), this);
       
        }
        
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.orange);

        if (inGame) {
        	
       
          
            g.drawString("Score: "+deaths, 360, 25);
           
            drawPlayer(g);
            drawAlien(g);
            drawShot(g);
            drawBombing(g);
            
        } else {

            if (timer.isRunning()) {
                timer.stop();
            }
            try {
				gameOver(g);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        }
        
        

       
    }


    private void update() {

        if (AlienCreated-1 == varaibles.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
           
        }

        
        player.act();
       

        // shot
        if(alien.isVisible()) {
        	int alienX=alien.getX();
        	int alienY= alien.getY();
        	if (alienX<-10||alienX>410||alienY<-10) {
        		alien.die();
        		ShotLeft=2;
        		varaibles.alienTimer=0;
        		
        	}
        	else {
        		  alien.act();
        	}
        }if(varaibles.alienTimer>10){
        Random genrator =new Random();
        int chance1=genrator.nextInt(24);
        int chance2= genrator.nextInt(24)+1;
        if ( chance1==chance2) {
        	if(alien.getBomb().isDestroyed()==true) {
        	alien.getBomb().initBomb(alien.x,alien.y);
        	alien.getBomb().setDestroyed(false);}
        	else if(alien.getBomb1().isDestroyed()==true) {
            	alien.getBomb1().initBomb(alien.x,alien.y);
            	alien.getBomb1().setDestroyed(false);}
        }
        }
        if(varaibles.alienTimer==20) {
        	
        	System.out.println(alien.getBomb().isDestroyed());
        	shot=new Shot(210,280);
        	shotCounter++;
        	
        }
        
        if (shot.isVisible() && alien.isVisible()) {
        	int alienX=alien.getX();
         	int alienY= alien.getY();
            int shotX = shot.getX();
            int shotY = shot.getY();

            if (alien.isVisible() && shot.isVisible()) {
                if (shotX >= (alienX)
                        && shotX <= (alienX + 30)
                        && shotY >= (alienY)
                        && shotY <= (alienY) +40){

                    var ii = new ImageIcon(".\\images\\explosion.png");
                 
                    alien.setImage(ii.getImage());
                    alien.setDying(true);
                   
                    
                    ShotLeft=2;
                    deaths++;
                    shot.die();
                    varaibles.alienTimer=0;
                }
            }
           
            int y = shot.getY();
            
            int x=shot.getX();
            int dir=alien.direction;
            if(dir==2) {
            	
            	x-=4;
            	y-=9;
            }
            else if(dir==1) {
            	
            	x-=4;
            	y-=8;
            }
            else if (dir==3) {
            	y -= 14;
            }
            else if (dir==0) {
            	x-=12;
            	y-=12;
            }
            else if (dir==6) {
            	x+=12;
            	y-=12;
            }
            else if (dir==5) {
            	x+=8;
            	y-=8;
            }
            else if (dir==7) {
            	x+=10;
            	y-=4;
            }
            else if (dir==8) {
            	x-=10;
            	y-=4;
            }
            else {
            	x+=3;
            	y-=11;
            }
            
            
            
            if (y < 0) {
                shot.die();
                
            } else {
                shot.setY(y);
                shot.setX(x);
            }
        }
        int bombX= alien.getBomb().x;
        int bombY= alien.getBomb().y;
        int bomb1X= alien.getBomb1().x;
        int bomb1Y= alien.getBomb1().y;
        int shotX = shot.getX();
        int shotY = shot.getY();
        if (!alien.getBomb().isDestroyed()) {

        	alien.getBomb().setY(alien.getBomb().getY() + 1);

            if (alien.getBomb().getY() >= varaibles.GROUND - varaibles.BOMB_HEIGHT) {

            	alien.getBomb().setDestroyed(true);
            }
        }
        if (!alien.getBomb1().isDestroyed()) {

        	alien.getBomb1().setY(alien.getBomb1().getY() + 1);

            if (alien.getBomb1().getY() >= varaibles.GROUND - varaibles.BOMB_HEIGHT) {

            	alien.getBomb1().setDestroyed(true);
            }
        }
        
        if (!alien.getBomb().isDestroyed() && shot.isVisible()) {
            if (shotX >= (bombX)
                    && shotX <= (bombX + 30)
                    && shotY >= (bombY)
                    && shotY <= (bombY) +40){

                var ii = new ImageIcon(".\\images\\explosion.png");
            
                alien.getBomb().setImage(ii.getImage());
                alien.getBomb().setDying(true);
               
               
                ShotLeft=2;
               
                shot.die();
            }
            else {
            	 alien.getBomb().setY(alien.getBomb().getY() + 4);
            	 if (alien.getBomb().getY() >= varaibles.GROUND - varaibles.BOMB_HEIGHT) {

                     alien.getBomb().setDestroyed(true);
                 }
            }
            if (!alien.getBomb1().isDestroyed() && shot.isVisible()) {
                if (shotX >= (bomb1X)
                        && shotX <= (bomb1X + 30)
                        && shotY >= (bomb1Y)
                        && shotY <= (bomb1Y) +40){

                    var ii = new ImageIcon(".\\images\\explosion.png");
              
                    alien.getBomb1().setImage(ii.getImage());
                    alien.getBomb1().setDying(true);
                   
                   
                    ShotLeft=2;
                    //deaths++;
                    shot.die();
                }
                else {
                	 alien.getBomb1().setY(alien.getBomb1().getY() + 4);
                	 if (alien.getBomb1().getY() >= varaibles.GROUND - varaibles.BOMB_HEIGHT) {

                         alien.getBomb1().setDestroyed(true);
                     }
                }
           
            
        }
        }
        
        
       
    }
    private void doGameCycle() {
    	
        update();
        repaint();
    }

    

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    public class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            player.keyReleased(e);
        }
        @Override
        public void keyPressed(KeyEvent e) {
        	
            player.keyPressed(e);

            int x = player.getX();
            int y = player.getY();
         
            int key = e.getKeyCode();
          System.out.println(key);
            if (key == 65) {
            	
                if (inGame) {
                	
                    if (!shot.isVisible()&& ShotLeft>0) {
                    	ShotLeft--;
                    	shotCounter++;
                    	
                    	
                        shot = new Shot(x, y);
                    }
                    
                }
                key= -1;
                 
            }
            
            if (key == 83 ) {

                if (inGame) {

                    if (!shot1.isVisible()&&ShotLeft>0) {
                    	ShotLeft--;
                    	shotCounter++;
                    	
                        shot1 = new Shot(x, y);
                    }
                    
                }
            }
       
        
        }
    }
}