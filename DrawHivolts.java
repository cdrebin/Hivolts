import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

//Claire Drebin 
//September 15, 2015 

public class DrawHivolts extends JFrame{
	private final Color YELLOW = new Color(0xEE9605);

	//screen refers to the pop-up window 
	 int screenW;
	 int screenH;
	 
	 //flag refers to the grid drawn
	 final int GRIDW = 12;
	 final int GRIDH = 12;
	 
	 //canvas is screen without borders
	 int canvasW = 480;
	 int canvasH = 480;
	 
	 //canvas origin coordinates
	 int canvasOx = 0;
	 int canvasOy = 22;

	 Character you = new Character(randomXCoord(), randomYCoord());
	 //Character mho1 = new Character(randomXCoord(), randomYCoord());
	 Character mho[] = new Character[12];
	 	 
	public DrawHivolts() {
		init();
	}
	
	public void init() {
		setSize(1200, 1200);
		setBackground(Color.BLACK);
		repaint();
	
		//set screen width and height to actual values of window
		 screenW = getWidth();
		 screenH = getHeight();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(canvasOx, canvasOy, canvasW, canvasH);
		drawGrid(g);
		// draw smileys
		initializeMhos(mho);
		for (int i = 0; i < mho.length; i++) {
			drawSmiley(g, mho[i].getXCoord(), mho[i].getYCoord(), YELLOW, Color.BLACK, 0, 25);
		}
		drawSmiley(g, you.getXCoord(), you.getYCoord(), Color.BLACK, YELLOW, 180, 22);
		drawFrences(g);
	}
	
	public void drawGrid(Graphics g){
		
		g.setColor(Color.ORANGE);
		for(int i = 0; i < 13; i++){
			//vertical
			g.drawLine(Gridx2Screenx(i), 0, Gridx2Screenx(i), GridHeight2ScreenHeight(GRIDH));
			//horizontal
			g.drawLine(0, Gridy2Screeny(i), GridWidth2ScreenWidth(GRIDW), Gridy2Screeny(i));
		}
	
	}
	
	public void drawSmiley(Graphics g, int x, int y, Color Color1, Color Color2, int degree, int mouth) {
		g.setColor(Color1);
		g.fillOval(x, y, 40, 40);
		g.setColor(Color2);
		g.drawOval(x, y, 40, 40);
		g.fillOval(x+10, y+10, 5, 10);
		g.fillOval(x+25, y+10, 5, 10);
		g.drawArc(x+10, y+mouth, 20, 10, degree, 180);
	}
	
	public void drawFrences(Graphics g) {
		int x = 0; int y = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 12; j++) {
				drawSmiley(g, Gridx2Screenx(x), Gridy2Screeny(y), Color.BLACK, YELLOW, 180, 22);
				x++;
			}
			y = 11; x = 0;
		}
		y = 1;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				drawSmiley(g, Gridx2Screenx(x), Gridy2Screeny(y), Color.BLACK, YELLOW, 180, 22);
				y++;
			}
			y = 1; x = 11;
		}
	}
	
	 public int Gridx2Screenx (double gx) {
			int sX =(int)( (gx / GRIDW) * canvasW + canvasOx); //convert to canvas coordinates, add canvas offset
			return sX;
		}
	 
	 public int Gridy2Screeny (double gy) {
			int sY =(int)( (gy / GRIDH) * canvasH + canvasOy); //convert to canvas coordinates, add canvas offset
			return sY;
		}
	 
	 public int GridWidth2ScreenWidth (double gW){
		 int sW =(int)( (gW) * (canvasW / GRIDW));
		 return sW;
	 }
	 
	 public int GridHeight2ScreenHeight(double gH){
		 int sH =(int)( (gH) * (canvasH / GRIDH) + canvasOy);
		 return sH;
	 }
	 
	 public int randomXCoord(){
		 int randomCoord = (int)(1 + Math.random() * 10);
		 randomCoord = Gridx2Screenx(randomCoord);
		 return randomCoord;
	 }
	 
	 public int randomYCoord(){
		 int randomCoord = (int)(1 + Math.random() * 10);
		 randomCoord = Gridy2Screeny(randomCoord);
		 return randomCoord;
	 } 
	 
	 public void initializeMhos(Character array[]) {
		 for (int i = 0; i < array.length; i++) {
			 array[i] = new Character(randomXCoord(), randomYCoord());
		 }
	 }
}
