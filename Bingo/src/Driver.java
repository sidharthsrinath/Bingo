import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Driver implements MouseListener{
	
	board bingo = new board();
	
	public Driver() {
		
	}
	
	public void paint(Graphics g) {
		bingo.paint(g);
	}
	
	public boolean hit(Pair a, Pair b) {
		
		int x1 = a.getA();
		int y1 = a.getB();
		
		int x2 = b.getA();
		int y2 = b.getB();
		
		return false;
	}
	
	public void mouseClicked(MouseEvent e) {		
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}
	
	public static void main(String[] args) {
		Driver n = new Driver();
	}
	
}
