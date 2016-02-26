import java.awt.Color;
import java.awt.Graphics;


public class Cell {

	private int x, y, cellSize;
	private String type;
	
	public Cell(int x, int y, int tileSize, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
		cellSize = tileSize;
	}
	
	
	public void draw(Graphics g) {
		if(type.equals("snake")) {
			g.setColor(Color.BLACK);
			g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
			g.setColor(Color.YELLOW);
			g.fillRect(x * cellSize + 2, y * cellSize + 2, cellSize - 4, cellSize - 4);
		}
		if(type.equals("apple")) {
			g.setColor(Color.BLACK);
			g.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
			g.setColor(Color.RED);
			g.fillRect(x * cellSize + 2, y * cellSize + 2, cellSize - 4, cellSize - 4);
		}	
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
	
	
}
