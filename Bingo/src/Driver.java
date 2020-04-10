import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Driver implements MouseListener{
	
	board bingo = new board();
	
	int mouseX;
	int mouseY;
	int mouseSide = 1;
	Pair mousePair = new Pair(mouseX, mouseY);
	
	int numsLength = bingo.nums.size();
	
	public Driver() {
		
	}
	
	public void paint(Graphics g) {
		bingo.paint(g);
		updateClicked();
	}
	
	public void updateClicked() {
		
		for(int i = 0; i < numsLength; i++) {
			Number s = bingo.nums.get(i);
			
			Pair number = new Pair(s.getX(),s.getX());
			int numberSide = 120;
			
			bingo.nums.get(i).setClicked(boxHit(number,numberSide,mousePair,mouseSide));
			
		}
	}
	
	public boolean boxHit(Pair a, int sideBox, Pair b, int sideMouse) {//method that checks if two rectangles intersect (mouse and number box)
		
		int x1 = a.getA();//x, y, and side lengths for the number objects
		int y1 = a.getB();
		int width1 = sideBox;
		int height1 = sideBox;
		
		int x2 = b.getA();//x, y, and side lengths for mouse object
		int y2 = b.getB();
		int width2 = sideMouse;
		int height2 = sideMouse;
		
		
		Rectangle box = new Rectangle(x1,y1,width1,height1); //number rectangle
		Rectangle mouse = new Rectangle(x2,y2,width2,height2); //mouse rectangle
		
		return box.intersects(mouse); //true = mouse clicked on number 
	}
	
	public void mouseClicked(MouseEvent e) {		
		mouseX = e.getX();
		mouseY = e.getY();
		
		System.out.println(mouseX + " "+ mouseY);
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
