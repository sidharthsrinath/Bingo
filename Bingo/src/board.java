import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class board extends JFrame implements ActionListener,MouseListener{	
	
	//global variables
	int width = 1000;
	int height = 1000;
	int mouseX;//x coordinate of mouse
	int mouseY;//y coordinate of mouse
	int mouseSide = 1;//side length of mouse
	Pair mousePair = new Pair(mouseX, mouseY);//pair object that conatins coordinates of mouse
	Timer t;
	
	int startX = 200;
	int startY = 200;
	int interval = 120;

	//Number objects
	int[] xVal; //array of x coordinates for numBoxes
	int[] yVal; //array of y coordinates for numBoxes
	ArrayList<Pair> coordinates = new ArrayList<Pair>();
	private ArrayList<String> values = number(25);//arraylist of 25 random integers in string form
	public ArrayList<Number> nums; //arraylist of the Number objects that will go on the board		
	
	public int[] createCoordArray(int startVal, int interval) { 
		int[] coordArray = new int[5];
		for(int i = 0; i < coordArray.length; i++) {
			coordArray[i] = startVal + (i*interval);
		}
		return coordArray;
	}
	
	board() {
		
		xVal = createCoordArray(startX,interval);
		yVal = createCoordArray(startY,interval);
		coordinates = setCoordinates(xVal,yVal);
		nums = setNumbers(25, values, coordinates);
		
		setTitle("Bingo");
		setSize(width,height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		t = new Timer(17, this);
		t.start();
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		//adding the mouselistener
		c.addMouseListener(this); //in order for mouseListener to be recognized it MUST be added to the content pane
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
				
		g.setColor(new Color(141,177,171));
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(206,227,151));//area where bingo will take place
		g.fillRect(startX, startY, 600, 600);
		
		for(int i = 0; i < nums.size(); i++) {//draw the numbers
			nums.get(i).paint(g);
		}
		
		g.setColor(Color.black);
		for(int i = startX; i <= width-startX; i+=interval) { //drawing the horizontal gridlines
			g.drawLine(i,startY,i,height-startY);
		}
		for(int i = startY; i <= height-startY; i+=interval) {//drawing vertical gridlines
			g.drawLine(startX,i,width-startY,i);
		}
		
		
		
	}
	
	//works
	public ArrayList<String> number(int a){ //creates an ArrayList of length "a" digits with random integers between 1 and 30
		
		ArrayList<String> numbers = new ArrayList<String>();
		
		// add something to check that there arent the same numbers multiple times
		for(int i = 0; i < a; i++ ) {
			numbers.add(String.valueOf((int)(Math.random()*30)));
		}
		
		for(int i = 0; i < numbers.size(); i++) {
			for(int j = i+1; j < numbers.size(); j++) {
				while(numbers.get(i).equals(numbers.get(j))) {
					//System.out.println(numbers.get(i)+" "+numbers.get(j));
					numbers.set(j,String.valueOf((int)(Math.random()*30)));
				}
			}
		}
		
		return numbers;
		 
	}
	
	//works
	public ArrayList<Pair> setCoordinates(int[] a, int[] b) {
		
		for(int x = 0; x < a.length; x++) {
			for(int y = 0; y < b.length; y++) {
				coordinates.add(new Pair(a[x],b[y]));
			}
		}
		return coordinates;
	}
	
	//works
	public ArrayList<Number> setNumbers(int length, ArrayList<String> vals, ArrayList<Pair> coords) {
		
		ArrayList<Number> numObjects = new ArrayList<Number>();
		
		for(int i = 0; i < length; i++) {
			numObjects.add(new Number(vals.get(i),coords.get(i).getA(),coords.get(i).getB()));
		}
		
		return numObjects;
	}
	
	//works BUT uses repaint which does a weird refreshing thing so fix that if needed
	public void correct(ArrayList<Number> n) { //makes marked tiles correct
		
		for(int i = 0; i < n.size(); i++) {
			Number s = n.get(i);
			
			int x = s.getX();
			int y = s.getY();	
			int numberSide = 120;
			
			if(intersect(x, y,numberSide)) {
				s.setIsClicked(true);
				s.makeCorrect();
				repaint(); //find a better method to use than repaint()
				//System.out.println(i + " " +s.getIsClicked());
			}
		}
	}
	
	//works
	public boolean intersect(int x, int y, int sideBox) {//method that checks if two rectangles intersect (mouse and number box)
		
		int x1 = x;//x, y, and side lengths for the number objects
		int y1 = y;
		int width1 = sideBox;
		int height1 = sideBox;
		
		int x2 = mouseX;//x, y, and side lengths for mouse object
		int y2 = mouseY;
		int width2 = mouseSide;
		int height2 = mouseSide;
		
		
		Rectangle box = new Rectangle(x1,y1,width1,height1); //number rectangle
		Rectangle mouse = new Rectangle(x2,y2,width2,height2); //mouse rectangle
		
		return box.intersects(mouse); //true = mouse clicked on number 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();//update mouse x and y coordinates
		mouseY = e.getY();
		correct(nums);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//repaint();
	}

	public static void main(String[] args) {
		board n  = new board();
	}
	
}
