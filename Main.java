
import javax.swing.JFrame;


public class Main {

	private JFrame frame;
	private Grid g;
	
	public Main() {
		frame = new JFrame();
		g = new Grid();
		g.setFocusable(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(g);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		new Main();	
	}
	
	
}
