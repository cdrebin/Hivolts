import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

//Claire Drebin 
//September 15, 2015 

public class DrawHivolts extends JFrame{
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
		
		g.setColor(Color.CYAN);
		g.fillOval(randomXCoord(), randomYCoord(), 40, 40);
		
		g.setColor(Color.MAGENTA);
		g.fillOval(randomXCoord(), randomYCoord(), 40, 40);
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
}