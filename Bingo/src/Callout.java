import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Callout {
	
	private ArrayList <String> values;
	private String value;
	private int x, y;
	private final int width = 120, height = 120;
	private boolean isClicked;
	private Color c = new Color(1f,0f,0f,.5f);
	private boolean marked = false;
	private int index = 0;

	
	//Rectangle numBox = new Rectangle(x,y,width,height);
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public boolean getIsClicked() {
		return isClicked;
	}
	public void setIsClicked(Boolean a) {
		this.isClicked = a;
	}
	public ArrayList<String> getValues() {
		return values;
	}
	public void setValues(ArrayList<String> values) {
		this.values = values;
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
	
	public Callout() {
	}
	
	public Callout(String value) {
		this.value = value;
	}
	public Callout(ArrayList <String> values, int x, int y, int index){
		this.values = values;
		this.x = x;
		this.y = y;
		this.index = index;
		value = values.get(index);
	}
	
	public void paint(Graphics g) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman",Font.PLAIN, 80));
		if(value.length() < 2) {
			g.drawString(value,x+40, y+90);
		}else {
			g.drawString(value,x+20, y+90);
		}
		
	}
	
	public void forward() {
		index+=1;
		value = values.get(index);
	}
	
	/*
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
	*/
	
		
	
 }
