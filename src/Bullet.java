import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Bullet extends JComponent{

	private int rx, ry;
	final Color FINAL_COLOR = randomColor();
	int width;
	int height;
	int startingXPosition;
	int startingYPosition;

	/**
	 * Constructor for the ball component. A ball panel must be passed as a parameter.
	 * @param panel is passed so that the boundaries for the balls will be known.
	 */
	public Bullet(GamePanel panel) {
		width = panel.getWidth();
		height = panel.getHeight();
		startingXPosition = panel.getShipX();
		startingYPosition = panel.getShipY() - 10;
		rx = startingXPosition;
		ry = startingYPosition;
	}
	/**
	 * Moves the balls according to the setIncrement() method
	 */
	public void move(int dy) {
		rx = startingXPosition;//get position of the space ship
		ry = ry- dy;
	}
	
	public boolean offscreen()
	{
		return(ry <= 0);
	}

	/**
	 * Draws the balls and sets the final color for each one.
	 * @param g
	 */
	public void drawComponent(Graphics g) {
		g.setColor(FINAL_COLOR);        

		int[] y = {ry, ry+2, ry+40,ry+43,ry+50,ry+50,ry};
		int[] x = {rx+5,rx,rx,rx+5,rx+10,rx+20,rx+20};
		g.fillPolygon(x, y, 3);        
	}

	/**
	 * Generates a random color for the component.
	 * @return a random color from a list of nine
	 */
	private Color randomColor(){
		int c = (int)(Math.random()*4);
		switch(c){
		case 0: return Color.RED;
		case 1: return Color.MAGENTA;
		case 2: return Color.ORANGE;
		case 3: return Color.YELLOW;
		case 4: return Color.PINK;
		default: return Color.WHITE;
		}
	}

	public int getXPosition(){
		return rx;
	}

	public int getYPosition(){
		return ry;
	}
}
