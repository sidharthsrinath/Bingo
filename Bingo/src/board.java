import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class board extends JPanel{
	private int width = 800;
	private int height = 800;
	
	//yes 
	
	int[] xVal = {100,220,340,460,580}; //array of x coordinates for numBoxes
	int[] yVal = {100,220,340,460,580}; //array of y coordinates for numBoxes
	

	ArrayList<Pair> coordinates = new ArrayList<Pair>();
	
	
	
	private ArrayList<String> values = number(25);//arraylist of 25 random integers in string form
	private ArrayList<Number> nums; //arraylist of the Number objects that will go on the board
	
	Number nine = new Number(values.get(0), xVal[2],yVal[4]);
	
	
	// new Number(String value, int xPosition, int yPosition);   Number object template
	
	
	
	public board() {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
		frame.setVisible(true);
		
		
		
		coordinates = setCoordinates(xVal,yVal);
		
		/*for(int i = 0; i < coordinates.size(); i++) {
			System.out.println(i +" "+coordinates.get(i));
		}*/
		
		nums = setNumbers(25, values, coordinates);
		
		/*for(int i = 0; i < nums.size(); i++) {
		System.out.println(nums.get(i));
		}*/
		
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
		
		//nine.paint(g);
		
		
		
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
	
	
	public static void main(String[] args) {
		
		board n  = new board();
		
	}
}
