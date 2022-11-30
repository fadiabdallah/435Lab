import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	String url = "jdbc:sqlite:./db/test.db";
    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Sign IN");
    JButton signUpButton = new JButton("Sign Up");
    JCheckBox showPassword = new JCheckBox("Show Password");
 
   
    Login() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
 
    }

   
    public void setLayoutManager() {
        container.setLayout(null);
    }
 
    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        signUpButton.setBounds(200, 300, 100, 30);
 
 
    }
 
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(signUpButton);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        signUpButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
 
    @SuppressWarnings("deprecation")
	@Override
    public void actionPerformed(ActionEvent e) {
        //Coding Part of LOGIN button
        if (e.getSource() == loginButton) {
            String user;
            String pass;
            user = userTextField.getText();
            pass = passwordField.getText();    
            if (user.length()==0|| pass.length()==0) {
           	 JOptionPane.showMessageDialog(this,"invalid arguments");
           	 return;
            }
           
            
            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }
                Statement stmt =conn.createStatement();
        		ResultSet rs;
        		System.out.println("hello");
        		rs= stmt.executeQuery("select * from Users");
        		boolean found =false;
        		while(rs.next())
        		{
        			int id=rs.getInt("PersonID");
        			String name=rs.getString("userNAME");
        			String password=rs.getString("password");
        			int score=rs.getInt("hightScore");
        			System.out.println( id+"   "+name+" hellooooo "+password+"   "+score);
        			if (name.equalsIgnoreCase(user) && pass.contentEquals(password)) {
        				//JOptionPane.showMessageDialog(this, "Sign in Succesful");
        				
        				varaibles.User=name;gameOptions start= new gameOptions();
        				start.setVisible(true);
        				System.out.println("setting User name to "+name);
        				System.out.println("here I am ");
        				Login.getFrames()[0].setVisible(false);
        				found=true;
        			}
        			else {
        				System.out.println("here");
        			}
        			

        		}if ( found==false) {
        			JOptionPane.showMessageDialog(this, "Wrong password or name");}
        		
        	conn.close();
        	System.out.println("connection is closed");
            } catch (SQLException e1) {
                System.out.println(e1.getMessage());
            }
 
        }
        //Coding Part of RESET button
        if (e.getSource() ==signUpButton ) {
        	 String user;
             String pass;
             user = userTextField.getText();
             pass = passwordField.getText(); 
             if (user.length()==0|| pass.length()==0) {
            	 JOptionPane.showMessageDialog(this,"invalid arguments");
            	 return;
             }
             varaibles.User=user;
        	 try (Connection conn = DriverManager.getConnection(url)) {
                 if (conn != null) {
                     DatabaseMetaData meta = conn.getMetaData();
                     System.out.println("The driver name is " + meta.getDriverName());
                     System.out.println("A new database has been created.");
                 }
                   
                 Statement stmt =conn.createStatement();
         		System.out.println("hello");
         		stmt.execute("insert into Users values ("+varaibles.playerindex+++",'"+user+"','"+pass+"',0)");
         		//stmt.execute("insert into Scores values('"+Commons.User+"',0)");
         				gameOptions start= new gameOptions();
         				start.setVisible(true);
         				Login.getFrames()[0].setVisible(false);
         				conn.close();
         			

         	}
         		

              catch (SQLException e1) {
                 System.out.println(e1.getMessage());
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
    public static void main(String[] a) {
        Login frame = new Login();
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10, 10, 370, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
 
    }
}
 

 
 
