import java.awt.EventQueue;
import javax.swing.JFrame;



@SuppressWarnings("serial")
public class SpaceInvaders extends JFrame  {
    /**
     * This is the class that will initiate the UI to be displayed on the screen and will also start the music once the player started playing
     * 
     */
    public SpaceInvaders() {

        initUI();
    }

    private void initUI() {
    	//SoundHandler.RunMusic(".\\muze\\MainTheme.wav" );
    	if (varaibles.freakMode) add(new game());
    	else {
    		add(new game());
    	}
        setTitle("Space Invaders");
        setSize(varaibles.BOARD_HEIGHT+100, varaibles.BOARD_HEIGHT);

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