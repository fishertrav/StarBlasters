import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class Star extends JComponent{

	private int rx, ry;
	final Color FINAL_COLOR = randomColor();
	int width;
	int height;

	/**
	 * Constructor for the ball component. A ball panel must be passed as a parameter.
	 * @param starPanel is passed so that the boundaries for the balls will be known.
	 */
	public Star(GamePanel starPanel) {
		width = starPanel.getWidth();
		rx = (int)(Math.random() * (width - 72)) + 36;
		ry = -30;
	}

	/**
	 * Moves the stars
	 */
	public void move(int dx, int dy) {
		rx += dx;
		ry += dy;
	}

	public boolean offscreen(){
		return(ry > 600);
	}

	/**
	 * Draws the balls and sets the final color for each one.
	 * @param g
	 */
	public void drawComponent(Graphics g) {
		g.setColor(FINAL_COLOR);
		//g.fillOval(rx, ry, BALL_RADIUS, BALL_RADIUS);
		Polygon p = new Polygon();
		p.addPoint(rx,ry);//point 1
		p.addPoint((rx+4), (ry+12));//point 2
		p.addPoint((rx+15),(ry+12));//point 3
		p.addPoint((rx+6),(ry+18));//point 4
		p.addPoint((rx+10),(ry+30));//point 5
		p.addPoint((rx),(ry+22));//point 6
		p.addPoint((rx-10),(ry+30));//point 7
		p.addPoint((rx-6),(ry+18));//point 8
		p.addPoint((rx-15),(ry+12));//point 9
		p.addPoint((rx-4),(ry+12));//point 10
		g.fillPolygon(p);
		g.drawPolygon(p);
	}

	/**
	 * Generates a random color for the component.
	 * @return a random color from a list of nine
	 */
	private Color randomColor(){
		int c = (int)(Math.random()*3);
		switch(c){
		case 0: return Color.YELLOW;
		case 1: return Color.ORANGE;
		case 2: return Color.WHITE;
		default: return Color.YELLOW;
		}
	}

	public int getXPosition(){
		return rx;
	}

	public int getYPosition(){
		return ry;
	}
}
