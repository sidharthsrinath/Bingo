import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Driver implements MouseListener{
	
	board bingo = new board();
	
	public Driver() {
		
	}
	
	public void paint(Graphics g) {
		bingo.paint(g);
	}
	
	public boolean hit(Pair a, int sideBox, Pair b, int sideMouse) {
		
		int x1 = a.getA();
		int y1 = a.getB();
		int width1 = sideBox;
		int height1 = sideBox;
		
		int x2 = b.getA();
		int y2 = b.getB();
		int width2 = 1;
		int height2 = 1;
		
		
		Rectangle box = new Rectangle(x1,y1,width1,height1);
		Rectangle mouse = new Rectangle(x2,y2,width2,height2);
		
		return box.intersects(mouse);
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
