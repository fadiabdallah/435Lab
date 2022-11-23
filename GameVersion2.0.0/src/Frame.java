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
public class Frame extends JPanel {
	int AlienCreated=0;
	int alienShoted=0;
	Random random= new Random();
    private Dimension d;
    //private List<Alien> aliens;
    private Player player;
    private Game alien;
    private Shot shot;									
    private Shot shot1;			
    private double deaths = 0;
    private boolean inGame = true;
    private Timer timer;
    private  int ShotLeft=2;
    double shotCounter=0;
    String url = "jdbc:sqlite:Game3/db/test.db";
    String url1 = "jdbc:sqlite:Game3/db/test1.db";

    /**
     * The Board class is the initial UI of our game. Once the user logged in the board will pop up with the according parameters that will 
     * help the player know where he is in the game in terms of progress.
     */
    public Frame() {

        initBoard();
        gameInit();
    }
/**
 * The initBoard is  responsible of displaying the board to the player
 */
    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(variables.BOARD_WIDTH+100, variables.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(variables.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }
/**
 * gameInit will spawn the player and the following aliens that the player has to kill in all random directions depending on the 
 * number of aliens the user specified he wanted to kill.
 */

    private void gameInit() {

      
    	alien = new Game(200,240,random.nextInt(7));
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
        g.fillRect(0, 0, variables.BOARD_WIDTH, variables.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(90, variables.BOARD_WIDTH / 2 - 60, variables.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(90, variables.BOARD_WIDTH / 2 - 60, variables.BOARD_WIDTH - 100, 50);
        
        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        int score=(int) ((deaths/shotCounter)*1000);
        g.drawString("Congratulations! your score is: "+score, (variables.BOARD_WIDTH - fontMetrics.stringWidth("Game Won!")) / 2-25,
                variables.BOARD_WIDTH / 2-27);
        JOptionPane.showMessageDialog(this,"Congratulations! your score is: "+score );
        Connection conn = DriverManager.getConnection(url1);
        Statement stmt =conn.createStatement();
        ResultSet currentHightScore= stmt.executeQuery("select * from Users where userName='"+variables.User+"'");
        int currentScore=-1;
        try {
    	    currentScore=currentHightScore.getInt("hightScore");
          
    
	} catch (Exception e) {
		System.out.println("do nothing");
	}
       
        if (currentScore<score) {
        	  
              stmt =conn.createStatement();
        	
              System.out.println("inserting score of"+variables.User+ " hes a score of "+ score);
        	try {
            conn.createStatement().execute("insert into  Users values(1,'"+variables.User+"','pass',"+score+")");
        	}
        	catch (Exception e) {
                conn.createStatement().execute("update Users set hightScore="+score+" where userName='"+variables.User+"'");
			}
        	System.out.println("done");
        }
        StartUpPage start= new StartUpPage();
 		start.setVisible(true);
 		Window win = SwingUtilities.getWindowAncestor(this);
 		win.dispose();
        
       

    }
    public void  follow(boolean shoot) {
   	 int x = player.getX();
        int y = player.getY();
     if(shoot==true){
   	if (inGame) {
   		

           if (!shot.isVisible()&& ShotLeft>0) {
           	ShotLeft--;
           	shotCounter++;
           	
           	//System.out.println("one is called");
               shot = new Shot(x, y);
           }
           
       }
   }
   }
    private void drawAlien(Graphics g) {
    	if(alien.isDying()) {
    		
    		 g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
    		 int ra=random.nextInt(7);
         	//System.out.println("ra is "+ra);
         	alien=new Game(200,240,ra);
         	AlienCreated++;
         	//System.out.println("Alien created is:"+AlienCreated);
         	return;
    	}
    	if (alien.isVisible()) {

            g.drawImage(alien.getImage(), alien.getX(), alien.getY(), this);
           
        }
    	else {
        	//System.out.println("deleting the alien");
        	int ra=random.nextInt(7);
        	//System.out.println("ra is "+ra);
        	alien=new Game(200,240,ra);
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

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.orange);

        if (inGame) {
        	
  
            
            g.drawString("Score: "+deaths*2, 350, 25);
          
          
            drawPlayer(g);
            drawAlien(g);
            drawShot(g);
            
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
        
        

       // Toolkit.getDefaultToolkit().sync();
    }


    private void update() {

        if (AlienCreated-1 == variables.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
           
        }

        // player
        //player.act1(alien.direction,this, alien);
        player.act();
       

        // shot
        if(alien.isVisible()) {
        	int alienX=alien.getX();
        	int alienY= alien.getY();
        	if (alienX<-10||alienX>410||alienY<-10) {
        		alien.die();
        		ShotLeft=2;
        		variables.alienTimer=0;
        		//System.out.println("alien out");
        	}
        	else {
        		  alien.act();
        	}
        }

        if(variables.alienTimer==21) {
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

                    var ii = new ImageIcon("Game3\\images\\explosion.png");
                 
                    alien.setImage(ii.getImage());
                    alien.setDying(true);
                   
                    
                    ShotLeft=2;
                    deaths++;
                    shot.die();
                    variables.alienTimer=0;
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
        	//System.out.println(e.getKeyCode());
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
            //shotcount=0;
        //}
        }
    }
}