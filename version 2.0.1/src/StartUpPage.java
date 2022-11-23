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
public class StartUpPage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	String url = "jdbc:sqlite:Game3/db/test1.db";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartUpPage frame = new StartUpPage();
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
	public StartUpPage() {
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
		Speed.setForeground(Color.black);
		Speed.setBounds(36, 82, 117, 25);
		contentPane.add(Speed);
		
		textField = new JTextField();
		textField.setBounds(163, 82, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAliendToKill = new JLabel("Score");
		lblAliendToKill.setForeground(Color.black);
		lblAliendToKill.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		lblAliendToKill.setBounds(36, 138, 117, 25);
		contentPane.add(lblAliendToKill);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(163, 138, 86, 20);
		contentPane.add(textField_1);
		
		JButton btnNewButton = new JButton("Play!!");
		btnNewButton.setBackground(Color.green);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int speed= Math.abs(Integer.parseInt(textField.getText())-100);
				int Kills= Integer.parseInt(textField_1.getText());
				variables.DELAY=((speed*37/100));
				variables.NUMBER_OF_ALIENS_TO_DESTROY=Kills;
				SpaceInvaders second = new SpaceInvaders();
				second.setVisible(true);
				StartUpPage.getFrames()[1].setVisible(false);
			}
		});
		btnNewButton.setBounds(160, 174, 89, 23);
		contentPane.add(btnNewButton);
		

		
		JLabel scoreDisplay = new JLabel("");
		scoreDisplay.setForeground(Color.GREEN);
		scoreDisplay.setFont(new Font("Sitka Subheading", Font.BOLD, 15));
		scoreDisplay.setBounds(294, 100, 117, 25);
		contentPane.add(scoreDisplay);
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			Statement stmt =conn.createStatement();
        ResultSet rs;
		
		rs= stmt.executeQuery("select * from Users where userName='"+variables.User+"'");
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
