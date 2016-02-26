
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;


public class Grid extends JPanel implements Runnable {

	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT= 400;

	private boolean running = false;

	private Cell c;
	private Cell a;
	private ArrayList<Cell> snake;
	private Input inputs;

	private boolean movingRight = true, movingLeft = false, movingUp = false, movingDown = false;
	
	private int x = 5;
	private int y = 15;
	private int cellSize = 20;
	private int snakeSize = 1;
	private int cols = WIDTH / cellSize;
	private int rows = HEIGHT / cellSize;
	
	private class Input implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
					
			if(key == KeyEvent.VK_D && !movingLeft) {
				stopMoving();
				movingRight = true;
			}
			if(key == KeyEvent.VK_A && !movingRight) {
				stopMoving();
				movingLeft = true;
			}
			if(key == KeyEvent.VK_W && !movingDown) {
				stopMoving();
				movingUp = true;
			}
			if(key == KeyEvent.VK_S && !movingUp) {
				stopMoving();
				movingDown = true;
			}
		}
		public void keyReleased(KeyEvent e) {
		}
		public void stopMoving() {
			movingRight = false;
			movingLeft = false;
			movingUp = false;
			movingDown = false;
		}
		
	}


	public Grid() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));	
		inputs = new Input();	
		addKeyListener(inputs);
		snake = new ArrayList<Cell>();
		start();
	}
	
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
		running = true;
		makeApple();
	}
	
	public void stop() { 
	
	}
	
	public void run() {
		while(running) {
			tick();
			repaint();
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void makeApple() {
		Random rand = new Random();
		int num = rand.nextInt(WIDTH / cellSize);
		int num2 = rand.nextInt(HEIGHT / cellSize);
		a = new Cell(num, num2, cellSize, "apple");
	
		
	}
	
	public void addCell() {
		c = new Cell(x, y, cellSize, "snake");
		snake.add(c);
	}
	
	
	public void tick() {
		addCell();
		if(snake.size() > snakeSize) {
			snake.remove(0);
		}
		
		if(movingRight) {x++;}
		if(movingLeft) {x--;}
		if(movingUp) {y--;}
		if(movingDown) {y++;}
		
		if((a.getX() == snake.get(snakeSize - 1).getX()) && (a.getY() == snake.get(snakeSize - 1).getY())) {
			snakeSize++;
			makeApple();
			addCell();	
		}
		
		if(snake.get(snakeSize - 1).getX() > cols - 1 || snake.get(snakeSize - 1).getX() < 0) {
			running = false;
		}
		if(snake.get(snakeSize - 1).getY() > rows - 1 || snake.get(snakeSize - 1).getY() < 0) {
			running = false;
		}
	
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, WIDTH, HEIGHT);	
		g.setColor(Color.BLACK);
		for(int i = 0; i < WIDTH /  20; i++) {
			g.drawLine(i * 20, 0, i * 20, HEIGHT);
		}
		for(int i = 0; i < HEIGHT / 20; i++) {
			g.drawLine(0, i * 20, WIDTH, i * 20);
		}
		for(int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
		a.draw(g);
	}


	
}
