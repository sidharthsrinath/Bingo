import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FinishedScreen {
	
	private Boolean won = false;
	final int height = 1000, width = 1000;
	final int x = 0, y = 0;

	public FinishedScreen() {
	}
	
	public void setWon(boolean a) {
		won = a;
	}
	
	public boolean getWon() {
		return won;
	}
	
	public void paint(Graphics g) {	
		if(won) {
		g.setColor(new Color(1f,0f,0f,.8f));
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.WHITE);
		g.drawString("Game won ", 330, 500);
		}
	}
}
