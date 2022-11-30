import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class gameOptions extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String url = "jdbc:sqlite:./db/test1.db";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gameOptions frame = new gameOptions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gameOptions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.gray);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Speed = new JLabel("Speed");
		Speed.setBackground(Color.WHITE);
		Speed.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		Speed.setForeground(Color.orange);
		Speed.setBounds(36, 82, 117, 25);
		contentPane.add(Speed);
		
		textField = new JTextField();
		textField.setBounds(163, 82, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAliendToKill = new JLabel("Score");
		lblAliendToKill.setForeground(Color.orange);
		lblAliendToKill.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		lblAliendToKill.setBounds(36, 138, 117, 25);
		contentPane.add(lblAliendToKill);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 138, 86, 20);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Play!");
		btnNewButton.setBackground(Color.green);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Randomize Speed initialization 
				// Get Maximum Kills from menu 
				// Initialize frame of game 
				int speed= Math.abs(Integer.parseInt(textField.getText())-100);
				int Kills= Integer.parseInt(textField_1.getText());
				varaibles.DELAY=((speed*37/100));
				varaibles.NUMBER_OF_ALIENS_TO_DESTROY=Kills;
				SpaceInvaders second = new SpaceInvaders();
				second.setVisible(true);
				gameOptions.getFrames()[0].dispose();
			}
		});
		
		btnNewButton.setBounds(160, 174, 89, 23);
		contentPane.add(btnNewButton);
	
		
		JLabel Welcome = new JLabel("");
		Welcome.setForeground(Color.orange);
		Welcome.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		Welcome.setBackground(Color.WHITE);
		Welcome.setBounds(85, 22, 242, 25);
		contentPane.add(Welcome);
		
		
		JLabel scoreDisplay = new JLabel("");
		scoreDisplay.setForeground(Color.orange);
		scoreDisplay.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		scoreDisplay.setBounds(294, 100, 117, 25);
		contentPane.add(scoreDisplay);
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt =conn.createStatement();
        ResultSet rs;
		
		rs= stmt.executeQuery("select * from Users where userName='"+varaibles.User+"'");
		int score1=-1;
		while(rs.next()) {
			score1=rs.getInt("hightScore");
		}
	
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
}}
