import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class board extends JFrame implements ActionListener,MouseListener, KeyListener{	
	
	//global variables
	int width = 1000;
	int height = 1000;
	int mouseX;//x coordinate of mouse
	int mouseY;//y coordinate of mouse
	int mouseSide = 1;//side length of mouse
	Pair mousePair = new Pair(mouseX, mouseY);//pair object that conatains coordinates of mouse
	Timer t;

	FinishedScreen m = new FinishedScreen();
	
	//callout of the number you're supposed to find on their board
	int index = 0;
	ArrayList<String> calloutValues = numberString(1000);
	Callout callout = new Callout(calloutValues,440, 50, 0);
	Pair calloutCoords = new Pair(440, 50);
	 
	
	int startX = 200;
	int startY = 200;
	int interval = 120;

	//Number objects
	int[] xVal; //array of x coordinates for numBoxes
	int[] yVal; //array of y coordinates for numBoxes
	Pair[][] coordinates;
	private String[][] numValues = number(25);//arraylist of 25 random integers in string form
	public Number[][] nums; //arraylist of the Number objects that will go on the board		
	
	
	board() {
		
		xVal = createCoordArray(startX,interval);
		yVal = createCoordArray(startY,interval);
		coordinates = setCoordinates(xVal,yVal);
		nums = setNumbers(25, numValues, coordinates);
				
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
		c.addKeyListener(this);
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		g.setColor(new Color(141,177,171));
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(206,227,151));//area where bingo will take place
		g.fillRect(startX, startY, 600, 600);
		
		

		for(int r = 0; r < nums.length; r++) {//draw the numbers
			for(int c = 0; c < nums[0].length; c++) {
				nums[r][c].paint(g);
			}
		}
		
		g.setColor(Color.black);
		for(int i = startX; i <= width-startX; i+=interval) { //drawing the horizontal gridlines
			g.drawLine(i,startY,i,height-startY);
		}
		for(int i = startY; i <= height-startY; i+=interval) {//drawing vertical gridlines
			g.drawLine(startX,i,width-startY,i);
		}
		
		
		callout.paint(g);
		
		m.paint(g);
	}
	
	//works
	public int[] createCoordArray(int startVal, int interval) { //creates array of x/y values for numbers based off starting location 
		int[] coordArray = new int[5];
		for(int i = 0; i < coordArray.length; i++) {
			coordArray[i] = startVal + (i*interval);
		}
		return coordArray;
	}

	//works
	public Pair[][] setCoordinates(int[] a, int[] b) {//makes a 2D array of pair objects that contain coordinates of numbers
		
		Pair[][] coords = new Pair[5][5];
		
		for(int x = 0; x < a.length; x++) {
			for(int y = 0; y < b.length; y++) {
				coords[x][y] = new Pair(a[x],b[y]);
			}
		}
		
		
		return coords;
		
	}
	
	//creates multiples of same number
	public String[][] number(int a){ //creates an ArrayList of length "a" digits with random integers between 1 and 30
		
		String[][] numbers = new String[(int) Math.sqrt(a)][(int) Math.sqrt(a)];
		
		// add something to check that there arent the same numbers multiple times
		for(int r = 0; r < numbers.length; r++ ) {
			for(int c = 0; c< numbers[0].length; c++) {
				numbers[r][c] = String.valueOf((int)(Math.random()*30));
			}
		}
		
		
		return numbers;
		 
	}
	
	public ArrayList<String> numberString(int a){ //creates an ArrayList of length "a" digits with random integers between 1 and 30
		
		ArrayList<String> numbers = new ArrayList<String>();
		
		// add something to check that there arent the same numbers multiple times
		for(int r = 0; r < a; r++ ) {
				numbers.add(String.valueOf((int)(Math.random()*30)));
		}
		
		
		return numbers;
		 
	}
	
	//works
	public Number[][] setNumbers(int length, String[][] vals, Pair[][] coords) {
		
		Number[][] numObjects = new Number[5][5];
		int counter = 0;
		
		for(int r = 0; r < numObjects.length; r++) {
			for(int c = 0; c < numObjects[0].length; c++) {
				numObjects[r][c] = new Number(vals[r][c], coords[r][c].getA(),coords[r][c].getB());
			}
		}
		
		return numObjects;
	}
	
	//works BUT uses repaint which does a weird refreshing thing so fix that if needed
	public void correct(Number[][] n, Callout f) { //makes marked tiles correct
		
		for(int r = 0; r < n.length; r++) {
			for(int c = 0; c < n[0].length; c++) {
			Number s = n[r][c];
			
			int x = s.getX();
			int y = s.getY();	
			int numberSide = 120;
			
				if(intersect(x, y,numberSide)&&!s.getIsClicked()) {
					if(s.getValue().equals(callout.getValue())) {
						s.setIsClicked(true);	
						f.forward();
						s.makeCorrect();			
						repaint(); //find a better method to use than repaint()
				//System.out.println(i + " " +s.getIsClicked());
					}
				}
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
	
	public boolean gameWon(board a) { //NEED TO FINISH AN
		
		boolean won = false;
		
		
		//condition 1: vertical line of 5
		for(int r = 0; r < a.nums.length; r++) {
			int counter = 0;
			for(int c = 0; c <  a.nums[0].length; c++) {
				if(a.nums[r][c].correct) counter++;
			}
			if(counter == 5) {
				won = true;
				return won;
			}
		}
		
		//condition 2: horizontal line
		for(int c = 0; c < a.nums[0].length; c++) {
			int counter = 0;
			for(int r = 0; r <  a.nums.length; r++) {
				if(a.nums[r][c].correct) counter++;
			}
			if(counter == 5) {
				won = true;
				return won;
			}
		}
		
		//condition 3: diagonal line left to right
		int count = 0;
		for(int r = 0; r < a.nums.length; r++) {
			for(int c = r; c <  r+1; c++) {
				if(a.nums[r][c].correct) count++;
			}
		}
		if(count == 5) {
			won = true;
			return won;
		}
		
		//condition 4: diagonal line right to left
		int counter = 0;
		for(int r = 4; r > 0; r--) {
			for(int c = r; c <  r+1; c++) {
				if(a.nums[r][c].correct) counter++;
			}
		}
		if(counter == 5) {
			won = true;
			return won;
		}
		
		return false;
	
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();//update mouse x and y coordinates
		mouseY = e.getY();
		correct(nums,callout);
		
		if (e.getClickCount() == 2 && !e.isConsumed()) {
		     e.consume();
		     callout.forward();
		     repaint();
		}
		
		if(gameWon(this)) {
			m.setWon(true);
		}
		
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		board n  = new board();
		
	}
	
}
