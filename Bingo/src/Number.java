import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Number {
	
	private String value;
	private int x, y;
	private final int width = 120, height = 120;
	private boolean isClicked;
	private Color c = new Color(1f,0f,0f,0f);
	private boolean marked = false;

	
	//Rectangle numBox = new Rectangle(x,y,width,height);
	
	public boolean getIsClicked() {
		return isClicked;
	}
	public void setIsClicked(Boolean a) {
		this.isClicked = a;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String toString() {
		return value + " "+x+" "+y;
	}
	
	public Number(String value) {
		this.value = value;
	}
	public Number(String value, int x, int y){
		this.value = value;
		this.x = x;
		this.y = y;
		
		
	}
	
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillRect(x, y, width, height);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN, 80));
		g.drawString(value,x+20, y+100);
		
	}
	
	public void makeCorrect() {
		if(marked) { 
			c = new Color(1f,0f,0f,0f);
			marked = !marked;
		}else {
			c = new Color(1f,0f,0f,.5f);
			marked = !marked;
		}
		isClicked = true;
		
	}

	
		
	
 }
