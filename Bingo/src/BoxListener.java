import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BoxListener extends MouseAdapter {
	
	private int mouseX;
	private int mouseY;
	private Boolean clicked;
	
	public Boolean getClicked(){
		return clicked;
	}
	
	public void setClicked(Boolean a) {
		clicked = a;
	}
	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}


	
	@Override
	public void mouseClicked(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		clicked = true;
		
		System.out.println(mouseX + " "+ mouseY + " " + clicked);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
