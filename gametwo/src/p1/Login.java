package p1; 

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Login {
	public static boolean enter= false;
	public static final long serialVersionUID= 1l;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12 * 9; 
	public static final int SCALE=2;
	public static void enterGame() {
if (enter) {
	 LoginFrame logframe=new LoginFrame();
     logframe.setTitle("Login Form");
     logframe.setVisible(true);
     logframe.setBounds(10,10,370,600);
     logframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     logframe.setResizable(false);
	 Game game =new Game(logframe.value,logframe.level);
      		JFrame frame = new JFrame(game.TITLE);
      		frame.add(game);
      		frame.pack();
      		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      		frame.setResizable(false);
      		frame.setLocationRelativeTo(null);
      		game.start();
      		logframe.updateScore(game.score);
            frame.setVisible(true);
      		}
	}
    public static void main(String[] a) throws IOException {
    	   LoginFrame logframe=new LoginFrame();
           logframe.setTitle("Login Form");
           logframe.setVisible(true);
           logframe.setBounds(10,10,370,600);
           logframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           logframe.setResizable(false);
           Game game =new Game(logframe.value,logframe.level);
      		
      		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE)) ;
      		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE)) ;
      		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT *SCALE)) ;
      		
      		
      																					//size of game window 
      		if (enter) {
      			
      		
      		JFrame frame = new JFrame(game.TITLE);
      		frame.add(game);
      		frame.pack();
      		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      		frame.setResizable(false);
      		frame.setLocationRelativeTo(null);
      		game.start();
      		logframe.updateScore(game.score);
            frame.setVisible(true);
            logframe.dispose();
        		}
        while(true) {  
        if (logframe.loggedin==true) {
        System.out.println("hiiiiiii");

        }
        
        
   		
   	}}
     
           
}
    

