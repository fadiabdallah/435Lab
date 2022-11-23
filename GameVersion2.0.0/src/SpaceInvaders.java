import java.awt.EventQueue;
import javax.swing.JFrame;



@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame  {
    /**
     * User Interface 
     * 
     */
    public SpaceInvaders() {

        initUI();
    }

    private void initUI() {
    
        add(new Frame());
        
        setTitle("Space Game");
        setSize(variables.BOARD_HEIGHT+100, variables.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new SpaceInvaders();
            ex.setVisible(true);
        });
    }
}