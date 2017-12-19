

import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Ship extends JComponent
{
	private final int DISTANCE = 15;
	private int x;
	private int y;

	public Ship(GamePanel panel) {
		x = 300;
		y = panel.getHeight() - 120;
	}

	public Ship() {
		// TODO Auto-generated constructor stub
	}

	public void drawComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon i = new ImageIcon("spaceship-11-hi.png");
		i.paintIcon(this, g, x-45, y);
	}

	public void moveRight() {	
		x += DISTANCE;
		if((x + 49) > 600)
		{
			x = 551;
		}
		repaint();
	}

	public void moveLeft() {
		x -= DISTANCE;
		if((x - 49) < 0)
		{
			x = 49;
		}
		repaint();
	}

	public int getXPosition(){
		return x;
	}

	public int getYPosition(){
		return y;
	}
}
