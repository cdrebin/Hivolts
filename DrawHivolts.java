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
			g.drawLine(Gx2Sx(i), 0, Gx2Sx(i), Gh2Sh(GRIDH));
			//horizontal
			g.drawLine(0, Gy2Sy(i), Gw2Sw(GRIDW), Gy2Sy(i));
		}
	
	}
	
	 public int Gx2Sx (double gx) {
			int sX =(int)( (gx / GRIDW) * canvasW + canvasOx); //convert to canvas coordinates, add canvas offset
			return sX;
		}
	 
	 public int Gy2Sy (double gy) {
			int sY =(int)( (gy / GRIDH) * canvasH + canvasOy); //convert to canvas coordinates, add canvas offset
			return sY;
		}
	 
	 public int Gw2Sw (double gW){
		 int sW =(int)( (gW) * (canvasW / GRIDW));
		 return sW;
	 }
	 
	 public int Gh2Sh(double gH){
		 int sH =(int)( (gH) * (canvasH / GRIDH) + canvasOy);
		 return sH;
	 }
	 
	 public int randomXCoord(){
		 int randomCoord = (int)(Math.random() * 12);
		 randomCoord = Gx2Sx(randomCoord);
		 return randomCoord;
	 }
	 
	 public int randomYCoord(){
		 int randomCoord = (int)(Math.random() * 12);
		 randomCoord = Gy2Sy(randomCoord);
		 return randomCoord;
	 } 
}