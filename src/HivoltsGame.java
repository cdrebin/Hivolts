import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

//Claire Drebin 
//September 15, 2015 

public class HivoltsGame extends JFrame{
	private final Color CYAN = new Color(0x91D8E2);

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
	 Character fences[] = new Character[20];
	 	 
	public HivoltsGame() {
		init();
	}
	
	public void init() {
		setSize(1200, 1200);
		setBackground(Color.WHITE);
		repaint();
	
		//set screen width and height to actual values of window
		 screenW = getWidth();
		 screenH = getHeight();
		//initializeArray(mho);
		//initializeArray(fences);
		initializeFencesAndMhos(mho, fences);
	}
	
	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(canvasOx, canvasOy, canvasW, canvasH);
		drawGrid(g);
		// draw smileys
		for (int i = 0; i < mho.length; i++) {
			drawSmiley(g, Gridx2Screenx(mho[i].getXCoord()), Gridy2Screeny(mho[i].getYCoord()),Color.WHITE, CYAN, 0, 25);
		}		
		for (int i = 0; i < fences.length; i++) {
			drawOneFence(g, Gridx2Screenx(fences[i].getXCoord()), Gridy2Screeny(fences[i].getYCoord()));
		}
		drawSmiley(g, you.getXCoord(), you.getYCoord(), CYAN, Color.WHITE, 180, 22);
		drawFences(g);
	}
	
	public void drawGrid(Graphics g){
		g.setColor(CYAN);
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
	
	public void drawFences(Graphics g) {
		int x = 0; int y = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 12; j++) {
				drawOneFence(g, Gridx2Screenx(x), Gridy2Screeny(y));
				x++;
			}
			y = 11; x = 0;
		}
		y = 1;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				drawOneFence(g, Gridx2Screenx(x), Gridy2Screeny(y));
				y++;
			}
			y = 1; x = 11;
		}
	}
	
	public void drawOneFence(Graphics g, int x, int y) {
		g.setColor(CYAN);
		for (int i = 0; i < 3; i++) {
			g.fillRect(x+5, y+5, 6, 35);
			int[] triangleX = {x+5, x+8, x+11};
			int[] triangleY = {y+5, y, y+5};
			g.fillPolygon(triangleX, triangleY, 3);
			x += 12;
		}
		g.fillRect(x-36, y+13, 40, 5);
		g.fillRect(x-36, y+28, 40, 5);
	}
	
	/*	for (int i = 0; i < mho.length; i++) {
			drawSmiley(g, mho[i].getXCoord(), mho[i].getYCoord(),Color.WHITE, CYAN, 0, 25);
		}		
		for (int i = 0; i < fences.length; i++) {
			drawOneFence(g, fences[i].getXCoord(), fences[i].getYCoord());
		}
		drawSmiley(g, you.getXCoord(), you.getYCoord(), CYAN, Color.WHITE, 180, 22); 
	*/
	 
	 public void initializeArray(Character array[]) {
		 for (int i = 0; i < array.length; i++) {
		 }
	 }
	 
	public void initializeFencesAndMhos(Character array1[], Character array2[]) {
		ArrayList<Integer[]> array = coords();
		ArrayList<Integer[]> newArray = new ArrayList<Integer[]>();
		for (int i = 0; i < 33; i++) {
			int n = (int)(Math.random() * array.size());
			newArray.add(array.get(n));
			array.remove(n);
		}
		for (int i = 0; i < 12; i++) {
			 array1[i] = new Character(newArray.get(i)[0], newArray.get(i)[1]);
		}			
		for (int i = 0; i < 20; i++) {
			 array2[i] = new Character(newArray.get(i+12)[0], newArray.get(i+12)[1]);
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
	 
	/**
	 * gets ArrayList with 144 arrays starting from [0,0] to [11,11]
	 * @return
	 */
	public ArrayList<Integer[]> coords() {
		ArrayList<Integer[]> array = new ArrayList<Integer[]>();
		// Creates ArrayList with 121 arrays starting from [1, 1] to [10, 10]
		for (int i = 1; i < 11; i++) {
			for (int j = 1; j < 11; j++) {
				Integer point[] = {i, j};
				array.add(point);
			}
		}
		return array;
	}
	 
	 public void move(UserKeyPress k){
		 
	 }

}
