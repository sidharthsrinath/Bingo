import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class board extends JPanel {
	private int width = 800;
	private int height = 800;
	private BoxListener listener = new BoxListener();
	
	int mouseX = listener.getMouseX();//x coordinate of mouse
	int mouseY = listener.getMouseY();;//y coordinate of mouse
	int mouseSide = 1;//side length of mouse
	Pair mousePair = new Pair(mouseX, mouseY);//pair object that conatins coordinates of mouse
	
	
	int[] xVal = {100,220,340,460,580}; //array of x coordinates for numBoxes
	int[] yVal = {100,220,340,460,580}; //array of y coordinates for numBoxes
	
	ArrayList<Pair> coordinates = new ArrayList<Pair>();

	private ArrayList<String> values = number(25);//arraylist of 25 random integers in string form
	public ArrayList<Number> nums; //arraylist of the Number objects that will go on the board		
	
	
	public board() {
		
		coordinates = setCoordinates(xVal,yVal);
		
		nums = setNumbers(25, values, coordinates);
		
		JFrame frame = new JFrame("Bingo");
		frame.getContentPane().addMouseListener(listener);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
		frame.setVisible(true);
		
			
		repaint();
		
		
		
		
	}
	
	public BoxListener getListener() {
		return listener;
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		g.setColor(new Color(141,177,171));
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(206,227,151));//area where bingo will take place
		g.fillRect(100, 100, 600, 600);
		
		for(int i = 0; i < nums.size(); i++) {//draw the numbers
			nums.get(i).paint(g);
		}
		
		g.setColor(Color.black);
		for(int i = 100; i <= width-100; i+=120) { //drawing the horizontal gridlines
			g.drawLine(i,100,i,height-100);
		}
		for(int i = 100; i <= height-100; i+=120) {//drawing vertical gridlines
			g.drawLine(100,i,width-100,i);
		}
		
		correct(nums);
		
	}
	
	public ArrayList<String> number(int a){ //creates an ArrayList of length "a" digits with random integers between 1 and 30
		ArrayList<String> numbers = new ArrayList<String>();
		
		
		// add something to check that there arent the same numbers multiple times
		for(int i = 0; i < a; i++ ) {
			numbers.add(String.valueOf((int)(Math.random()*30)));
		}
		
		return numbers;
		 
	}
	
	public ArrayList<Pair> setCoordinates(int[] a, int[] b) {
		
		for(int x = 0; x < a.length; x++) {
			for(int y = 0; y < b.length; y++) {
				coordinates.add(new Pair(a[x],b[y]));
			}
		}
		return coordinates;
	}
	
	public ArrayList<Number> setNumbers(int length, ArrayList<String> vals, ArrayList<Pair> coords) {
		
		ArrayList<Number> numObjects = new ArrayList<Number>();
		
		for(int i = 0; i < length; i++) {
			numObjects.add(new Number(vals.get(i),coords.get(i).getA(),coords.get(i).getB()));
		}
		
		return numObjects;
	}
	
	public void correct(ArrayList<Number> n) {
		
		for(int i = 0; i < n.size(); i++) {
			Number s = n.get(i);
			
			Pair number = new Pair(s.getX(),s.getX());
			int numberSide = 120;
			
			//if(listener.getClicked()) {
				if(intersect(number, numberSide, mousePair, mouseSide)) {
					s.makeCorrect();
				//}
			}
		}
	}
	
	public boolean intersect(Pair a, int sideBox, Pair b, int sideMouse) {//method that checks if two rectangles intersect (mouse and number box)
		
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
	
	public static void main(String[] args) {
		
		board n  = new board();
		
	}

	
}
