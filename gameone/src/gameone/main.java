package gameone;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.Color;

public class main extends JFrame {

	private JPanel contentPane;
	public int score ; 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
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
	public main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane scoreis = new JTextPane();
		scoreis.setText("Your Score is :");
		scoreis.setBounds(161, 47, 106, 16);
		contentPane.add(scoreis);
		
		JButton levelone = new JButton("Level 1");
		levelone.setBackground(Color.BLUE);
		levelone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelone one = new levelone();
				one.setVisible(true);
				score+=one.scoreone ;
				scoreis.setText("Total Score :" + String.valueOf(score));
			}
		});
		levelone.setBounds(146, 87, 117, 29);
		contentPane.add(levelone);
		
		JButton leveltwo = new JButton("Level 2");
		leveltwo.setBackground(Color.YELLOW);
		leveltwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				leveltwo two = new leveltwo();
				two.setVisible(true);
				score+=two.scoretwo ;
				scoreis.setText("Total Score :" + String.valueOf(score));
				
			}
		});
		leveltwo.setBounds(146, 128, 117, 29);
		contentPane.add(leveltwo);
		
		JButton levelthree = new JButton("Level 3");
		levelthree.setBackground(Color.RED);
		levelthree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				levelthree three = new levelthree();
				three.setVisible(true);
				score+=three.scorethree ;
				scoreis.setText("Total Score :" + String.valueOf(score));
			}
		});
		levelthree.setBounds(146, 169, 117, 29);
		contentPane.add(levelthree);
		

	}
}
