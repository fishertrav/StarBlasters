import java.awt.BorderLayout;
import javax.swing.*;


public class StarBlasters
{
	public static void main(String[] args) {
		JFrame frame = new JFrame("Star Blasters");
		GamePanel panel = new GamePanel();
		frame.setSize(600, 600);
		frame.add(panel, BorderLayout.CENTER); //add our new panel
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setVisible(true);
		panel.requestFocus(true);
		
	}
}
