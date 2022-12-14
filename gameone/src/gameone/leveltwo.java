package gameone;


/**
 * @author sahartabatabaie, fadiabdallah, mohamadayoub
 * @since October9
 * {@link main()}
 */

import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class leveltwo extends JFrame {

	private JPanel contentPane;
	private JTextField answer;
	public int exp; 
	
	
	public int scoretwo;
	public int result ;
	public String generate() {
		String[] operators = {"+", "-"};
		
		/**
		 * + and - are the two operators used in arithmetic calculations in this level
		 */
		
		
		Random rand = new Random();
		int operator = rand.nextInt(2);
		int a = rand.nextInt(100);
		int b = rand.nextInt(100);
		
		
		/**
		 * @param a 
		 * is a random integer chosen from 0 to 99
		 * @param b 
		 * is a random integer chosen from 0 to 99
		 */
		
		
		
		/**
		 * generate a random arithmetic equation
		 * @return generate 
		 * generate a new random arithmetic equation
		 */
		
		if (operator==0) {
			 result = a + b ; 
		}
		else {
			 result = a  - b ; 
		}
		
		String quation = String.valueOf(a) + operators[operator] + String.valueOf(b) ; 
		return (quation);
	
	}

	/**
	 * Launch the application.
	 */
	
	
	/**
	 * @param args
	 * This is the main method 
	 */
	
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					leveltwo frame = new leveltwo();
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
	public leveltwo() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.PINK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel firstlabel = new JLabel("Solve the following : ");
		firstlabel.setForeground(Color.BLUE);
		firstlabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstlabel.setBounds(158, 69, 141, 16);
		contentPane.add(firstlabel);
		
		answer = new JTextField();
		answer.setBackground(Color.ORANGE);
		answer.setForeground(Color.RED);
		answer.setBounds(158, 200, 130, 26);
		contentPane.add(answer);
		answer.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Your Answer");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(180, 185, 86, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel equation = new JLabel("Ready ?");
		equation.setForeground(Color.BLUE);
		equation.setHorizontalAlignment(SwingConstants.CENTER);
		equation.setBounds(158, 122, 141, 16);
		contentPane.add(equation);
		
		
		JLabel score = new JLabel("Your Score : ");
		score.setBounds(287, 22, 157, 16);
		contentPane.add(score);
		
		JButton submit = new JButton("Submit");
		submit.setBackground(Color.ORANGE);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(exp<=10){int studentanswer= Integer.parseInt(answer.getText());
				if (studentanswer==result) {
					firstlabel.setText("Solve the following :");
					scoretwo+=10000;
					equation.setText(generate());
					exp+=1; 
				}
				
				
				/**
				 * if answer is correct add 10000 to scoreone
				 * @return scoreone increased by 10000
				 */
				
				
				else {
					firstlabel.setText("Wrong Answer !");
					scoretwo-=500;
				}
				
				
				/**
				 * if answer is wrong subtract 500 from scoreone
				 * @return scoreone decreased by 500
				 */
				
				
				
				
				score.setText("Your Score is :" + String.valueOf(scoretwo));}
				else {
					equation.setText("Your Test Is Done !");
					
				}
				answer.setText("");
			
			}	
		});
		submit.setBounds(168, 226, 117, 29);
		contentPane.add(submit);
		

		
		JButton start = new JButton("START");
		start.setBackground(Color.ORANGE);
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equation.setText(generate());
				
				
			
			
			

			
			}
		});
		start.setBounds(158, 17, 117, 29);
		contentPane.add(start);
	}
}
