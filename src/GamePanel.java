import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;



@SuppressWarnings("serial")
public class GamePanel extends JPanel
{
	private int stars[][];
	int highlight = 0, starDelay = 5000, starsCreated = 0;
	Ship ship;
	ArrayList <Star> starArray = new ArrayList<Star>();
	ArrayList <Bullet> bulletArray = new ArrayList<Bullet>();
	GamePlay game;
	ActionListener starListener = null;
	Timer bulletTimer;
	Timer starTimer;
	boolean pause = true;
	private final int DELAY = 20;
	Timer t;
	Font titleFont;

	/**
	 * Creates the number of balls desired and adds each to an array list
	 */
	private void createStars() {
		starArray.add(new Star(this));
	}

	public void createShip() {
		ship = new Ship(this);
		add(ship);
	}

	public void createBullet() {
		bulletArray.add(new Bullet(this));
	}

	/**
	 * moves each ball in the array at a random speed determined by the random number generator
	 */
	public void move() {
		for (Star i : starArray) {
			i.move(0,1);
		}
		repaint();
	}

	public int getShipX() {
		return ship.getXPosition();
	}

	public int getShipY() {
		return ship.getYPosition();
	}

	public int[] getStarX(){
		int n, x[] = null;

		if(starArray.size() > 0){
			x = new int[ starArray.size() ];
			for(n=0;n<starArray.size();n++) {
				x[n] = (starArray.get(n)).getXPosition();
			}
		}

		return x;
	}

	public int[] getStarY(){
		int n, y[] = null;

		if(starArray.size() > 0) {
			y = new int[starArray.size()];
			for(n=0;n<starArray.size();n++){
				y[n] = (starArray.get(n)).getYPosition();
			}
		}

		return y;
	}

	public int[] getBulletX(){
		int n, x[] = null;

		if(bulletArray.size() > 0){
			x = new int[ bulletArray.size() ];
			for(n=0;n<bulletArray.size();n++)
			{
				x[n] = (bulletArray.get(n)).getXPosition();
			}
		}

		return x;
	}

	public int[] getBulletY(){
		int n, y[] = null;

		if(bulletArray.size() > 0){
			y = new int[ bulletArray.size() ];
			for(n=0;n<bulletArray.size();n++){
				y[n] = (bulletArray.get(n)).getYPosition();
			}
		}

		return y;
	}


	public GamePanel() {
		setBounds(0, 0, 600, 600); //create the bounds
		initStars(); //put the glowy white dots in the background
		setFocusable(true); //set the panel focusable
		addKeyListener(new myKeyListener()); //add the key listener
		createShip();
		titleFont = new Font("Comic Sans MS", Font.BOLD, 30);
		ActionListener bulletListener = new BulletListener();
		starListener = new StarListener();
		bulletTimer = new Timer(100, bulletListener); //bullet timer
		starTimer = new Timer(starDelay, starListener); //star drop timer


		ActionListener listener = new TimerListener();
		t = new Timer(DELAY, listener);



		game = new GamePlay();
	}

	private void initStars() {
		stars = new int[500][3];
		for(int[] s : stars){
			s[0] = (int)(Math.random()*getWidth());
			s[1] = (int)(Math.random()*getHeight());
			s[2] = (int)(Math.random()*5); //star "radius"
		}
	}

	private void drawStars(Graphics g){
		g.setColor(new Color(255, 255, 255));
		for(int[] s : stars){
			g.fillOval(s[0], s[1], s[2], s[2]);
		}
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(0, 0, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
		drawStars(g);

		if (!pause) {
			for (Star i : starArray) {
				i.drawComponent(g);
			}

			for (Bullet i : bulletArray) {
				i.drawComponent(g);
			}
			ship.drawComponent(g);

			g.setColor(Color.CYAN);
			g.drawString(String.format("%04d", game.totalPoints), 400, 550);
		} else {
			//draw menu
			g.setColor(Color.CYAN);
			g.setFont(titleFont);
			g.drawString("Star Blasters", 200, 150);

			g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			g.setColor(highlight == 0? new Color(178, 102, 255) : Color.CYAN);
			g.drawString("Play/Resume", 225, 250);
			g.setColor(highlight == 1? new Color(178, 102, 255) : Color.CYAN);
			g.drawString("Exit", 260, 300);

			g.setColor(Color.CYAN);
			g.drawString("Instructions: Try and shoot as many stars as possible!", 120, 400);
			g.drawString("Use the arrow keys to move the ship and the space bar to shoot.", 70, 425);
			g.drawString("If you get hit by a star...GAME OVER", 180, 450);
		}

	}

	private class BulletListener implements ActionListener {

		@Override 
		public void actionPerformed(ActionEvent event){
			int n;

			for(n=0;n<bulletArray.size();n++)
			{
				bulletArray.get(n).move(3);
				if(bulletArray.get(n).offscreen())
				{
					bulletArray.remove(n);
				}
			}
			gamePoints();
		}
	}

	private class StarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			createStars();
			if(starsCreated++ % 10 == 0)
			{
				starDelay -= starDelay >= 1000? 500 : 0;
				t = new Timer(starDelay, starListener);
				t.setRepeats(true);
				t.start();
			}
		}

	}

	/**
	 * An action listener to move the balls in the panel when the timer is running.
	 */
	private class TimerListener implements ActionListener {

		@Override 
		public void actionPerformed(ActionEvent event){
			int n;

			move();
			checkGameOver();

			for(n=0;n<starArray.size();n++)
			{
				if(starArray.get(n).offscreen())
				{
					starArray.remove(n);
				}
			}
		}
	}

	private void gamePoints() {
		game.gamePoints(this);
	}

	private class myKeyListener extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent k)
		{
			int key = k.getKeyCode();

			if (key == KeyEvent.VK_LEFT) {    	
				ship.moveLeft();
			}

			if (key == KeyEvent.VK_RIGHT) {

				ship.moveRight();
			}

			if (key == KeyEvent.VK_SPACE) {
				if(bulletArray.size() < 4)
				{
					createBullet();
				}

				bulletTimer.setRepeats(true);
				bulletTimer.start();
			}

			if (key == KeyEvent.VK_ESCAPE) {
				pause ^= true;
				if(!pause){
					bulletTimer.start();
					starTimer.start();
					t.start();
				}else{
					bulletTimer.stop();
					starTimer.stop();
					t.stop();
				}
			}

			if (key == KeyEvent.VK_ENTER){
				switch(highlight) {
				case 0: //resume play

					t.start(); //star movement timer
					starTimer.start();
					bulletTimer.start();
					pause = false;
					break;
				case 1: //exit game
					System.exit(0);
					break;
				}
			}

			if (key == KeyEvent.VK_UP) {
				highlight ^= 1;
			}

			if (key == KeyEvent.VK_DOWN) {
				highlight ^= 1;
			}
			repaint();

		}
	}

	private void checkGameOver(){

		//for every star
		for(int i = 0; i < starArray.size(); i++){

			//check it's coordinates against the ships. If the star hits the ship...game over. Otherwise do nothing
			if((starArray.get(i).getYPosition() + 5) >= (ship.getYPosition() - 5) && (starArray.get(i).getYPosition() - 5) <= (ship.getYPosition() + 5)){
				if((starArray.get(i).getXPosition() + 5) >= (ship.getXPosition() - 5) && (starArray.get(i).getXPosition() - 5) <= (ship.getXPosition() + 5)){
					System.out.println("game over");
					System.exit(0);
				}
			}
		}

	}
}
