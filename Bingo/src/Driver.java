import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Driver {
	
	board bingo = new board();
	
	
	
	int numsLength = bingo.nums.size();
	
	public Driver() {
		
	}
	
	public void paint(Graphics g) {
		bingo.paint(g);
	}
	
	
	
	
	


	
}
