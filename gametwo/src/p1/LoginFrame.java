package p1;

import javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener  {
	int level;
	int value;
	boolean loggedin=false ; 
	
	
	Connection connection = null;
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton signupButton = new JButton("SIGNUP");
    JButton resetButton = new JButton("RESET");
    JCheckBox showPassword = new JCheckBox("Show Password");
    
    
    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        
    	try {
    		
    		Class.forName("org.sqlite.JDBC");
    		connection = DriverManager.getConnection("jdbc:sqlite:./game2.db");
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	} catch (SQLException e1) {
    		e1.printStackTrace();
    	}
    	
 
    }
 
    public void setLayoutManager() {

    }
 
    public void setLocationAndSize() {
 
 
    }
 
    public void addComponentsToContainer() {
        getContentPane().setLayout(null);
        userLabel.setBounds(50, 150, 100, 30);
        container.add(userLabel);
        passwordLabel.setBounds(50, 220, 100, 30);
        container.add(passwordLabel);
        userTextField.setBounds(150, 150, 150, 30);
        container.add(userTextField);
        passwordField.setBounds(150, 220, 150, 30);
        container.add(passwordField);
        showPassword.setBounds(150, 250, 150, 30);
        container.add(showPassword);
        loginButton.setBounds(50, 300, 100, 30);
        container.add(loginButton);
        resetButton.setBounds(200, 300, 100, 30);
        container.add(resetButton);
        signupButton.setBounds(130, 350, 100, 30);
        container.add(signupButton);
        
        JLabel Score = new JLabel("Score");
        Score.setBounds(50, 57, 46, 14);
        getContentPane().add(Score);
        
        JButton Levelone = new JButton("Level1");
        Levelone.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		level=1;
        	}
        });
        Levelone.setBounds(180, 27, 89, 23);
        getContentPane().add(Levelone);
        
        JButton leveltwo = new JButton("level2");
        leveltwo.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		level=2;
        	}
        });
        leveltwo.setBounds(180, 57, 89, 23);
        getContentPane().add(leveltwo);
        
        JButton levelthree = new JButton("level3");
        levelthree.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		level=3;
        	}
        });
        levelthree.setBounds(180, 83, 89, 23);
        getContentPane().add(levelthree);
        
        JSlider slider = new JSlider();
        slider.setMinorTickSpacing(1);
        slider.setValue(1);
        slider.setMajorTickSpacing(1);
        slider.setMaximum(17);
        slider.setMinimum(-17);
        slider.setBounds(82, 113, 200, 26);
        getContentPane().add(slider);
         value = slider.getValue();
    }
    
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
        signupButton.addActionListener(this);
    }
 
 
    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed (ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            
            
          try {
    	  String auth = "SELECT (count(*) > 0) as found FROM user WHERE  username = ? AND password = ?";
    	  PreparedStatement authstat = connection.prepareStatement(auth);
			authstat.setString(1, userText);
			authstat.setString(2, pwdText);
	          ResultSet check = authstat.executeQuery();
	          
	          if (check.next()) {
	                boolean found = check.getBoolean(1); 
	                if (found) {
	                    JOptionPane.showMessageDialog(this, "Login Successful");
	                    loggedin=true;

	                } else {
	                   JOptionPane.showMessageDialog(this, "Invalid Username or Password");
	                }
	          
	          }
		} catch (SQLException e1) {e1.printStackTrace();}
          
          
           
    }
        //Coding Part of RESET button
        if (e.getSource() == resetButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
        
        if (e.getSource() == signupButton) {
            String userText;
            String pwdText;
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            
          try {
        	  
  	      String insertuser = "INSERT INTO user(username,password,score) VALUES(?,?,?)";
  	      PreparedStatement insertuserstat = connection.prepareStatement(insertuser);
  	      insertuserstat.setString(1, userText);
  	      insertuserstat.setString(2, pwdText);
  	      insertuserstat.executeUpdate();
		} catch (Exception e2) {
				System.err.println(e2);
		}

       
        }
        
        
        
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
 
 
        }
    }
    public void updateScore(int Score) {
    	 String sql = "UPDATE user SET score = ?  ";
    	  PreparedStatement insertuserstat;
		try {
			insertuserstat = connection.prepareStatement(sql);
		     insertuserstat.setString(1, String.valueOf(Score));
		  	   
	  	      insertuserstat.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	 
 
    }
}
 
