import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Number implements ActionListener{
	
	private String value;
	private int x, y;
	private final int width = 100, height = 100;
	public boolean clicked;
	
	Rectangle numBox = new Rectangle(x,y,width,height);
	
	
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
		
		g.setFont(new Font("TimesRoman",Font.PLAIN, 80));
		g.drawString(value, x, y);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
 }
