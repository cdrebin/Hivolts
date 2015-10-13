import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

//Claire Drebin 
//September 15, 2015 

public class HivoltsGameState extends JFrame{
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

	 //set you character with random coordinates
	 Character you = new Character();
	 //Character mho1 = new Character(randomXCoord(), randomYCoord());
	 
	 //initalize character and fence arrays
	 Character mho[] = new Character[12];
	 Character fences[] = new Character[20];
	 
	 UserKeyPress k = new UserKeyPress();
	 
	//Hivolts constructor
	public HivoltsGameState() {
		init();
	}
	
	/**
	 * Initializes window, grid, keyListener, fence and mho arrays
	 */
	public void init() {
		setSize(1200, 1200);
		setBackground(Color.WHITE);
		repaint();
		
		addKeyListener(k);
		
		//set screen width and height to actual values of window
		 screenW = getWidth();
		 screenH = getHeight();
		//initializeArray(mho);
		//initializeArray(fences);
		initializeGame(mho, fences, you);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
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
		drawSmiley(g, Gridx2Screenx(you.getXCoord()), Gridy2Screeny(you.getYCoord()), CYAN, Color.WHITE, 180, 22);
		drawFences(g);
		
		
		UpdateGameState(k);
	}
	
	/**
	 * Draw grid on screen
	 * @param g Graphics object
	 */
	public void drawGrid(Graphics g){
		g.setColor(CYAN);
		for(int i = 0; i < 13; i++){
			//vertical
			g.drawLine(Gridx2Screenx(i), 0, Gridx2Screenx(i), GridHeight2ScreenHeight(GRIDH));
			//horizontal
			g.drawLine(0, Gridy2Screeny(i), GridWidth2ScreenWidth(GRIDW), Gridy2Screeny(i));
		}
	
	}
	
	/**
	 * Draws a simple smiley face with eyes and a mouth
	 * @param g Graphics Object
	 * @param x x-coordinate of smiley face
	 * @param y y-coordinate of smiley face
	 * @param Color1 interior color of smiley face
	 * @param Color2 exterior color of smiley face (outline)
	 * @param degree degree of angle for arc
	 * @param mouth distance from top of smiley face to top of mouth
	 */
	public void drawSmiley(Graphics g, int x, int y, Color Color1, Color Color2, int degree, int mouth) {
		g.setColor(Color1);
		g.fillOval(x, y, 40, 40);
		g.setColor(Color2);
		g.drawOval(x, y, 40, 40);
		g.fillOval(x+10, y+10, 5, 10);
		g.fillOval(x+25, y+10, 5, 10);
		g.drawArc(x+10, y+mouth, 20, 10, degree, 180);
	}
	
	/**
	 * draws the fences lining the exterior of the grid
	 * @param g Graphics object
	 */
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
	
	/**
	 * Draws a single fence 
	 * @param g Graphics object
	 * @param x x-coordinate of fence
	 * @param y y-coordinate of fence
	 */
	public void drawOneFence(Graphics g, int x, int y) {
		g.setColor(CYAN);
		
		//draws triangle on top of the fence
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
	 
	public void initializeGame(Character mhos[], Character fences[], Character you) {
		ArrayList<Integer[]> array = coords();
		ArrayList<Integer[]> newArray = new ArrayList<Integer[]>();
		for (int i = 0; i < 33; i++) {
			int n = (int)(Math.random() * array.size());
			newArray.add(array.get(n));
			array.remove(n);
		}
		for (int i = 0; i < 12; i++) {
			 mhos[i] = new Character(newArray.get(i)[0], newArray.get(i)[1]);
		}			
		for (int i = 0; i < 20; i++) {
			 fences[i] = new Character(newArray.get(i+12)[0], newArray.get(i+12)[1]);
		}
		 you.setXCoord(newArray.get(32)[0]); you.setYCoord(newArray.get(32)[1]);
	}
	
	/**
	 * Converts grid x-coordinates to screen coordinate equivalent
	 * @param gx x-coordinate on grid system (12x12)
	 * @return x-coordinate on screen system (screen width x screen height)
	 */
	 public int Gridx2Screenx (double gx) {
			int sX =(int)( (gx / GRIDW) * canvasW + canvasOx); //convert to canvas coordinates, add canvas offset
			return sX;
		}	 
	
	 /**
	  * Converts grid x-coordinates to screen coordinate equivalent 
	  * @param gy y-coordinate on grid system (12x12)
	  * @return y-coordinate on screen system (screen width x screen height)
	  */
	 public int Gridy2Screeny (double gy) {
			int sY =(int)( (gy / GRIDH) * canvasH + canvasOy); //convert to canvas coordinates, add canvas offset
			return sY;
		}	 
	 
	 /**
	  * Converts grid width to screen width
	  * @param gW grid width
	  * @return screen width
	  */
	 public int GridWidth2ScreenWidth (double gW){
		 int sW =(int)( (gW) * (canvasW / GRIDW)); //use ratio of canvas to grid to convert grid width to screen width
		 return sW;
	 }
	 
	 /**
	  * Converts grid height to screen height
	  * @param gH grid height
	  * @return screen height
	  */
	 public int GridHeight2ScreenHeight(double gH){
		 int sH =(int)( (gH) * (canvasH / GRIDH) + canvasOy);//use ratio of canvas to grid to convert grid height to screen height
		 return sH;
	 }
	 
	 /**
	  * Generate random x-coordinate between 1 and 11
	  * @return random x-coordinate
	  */
	 public int randomXCoord(){
		 int randomCoord = (int)(1 + Math.random() * 10);
		 return randomCoord;
	 }
	 
	 /**
	  * Generate random y-coordinate between 1 and 11
	  * @return random y-coordinate
	  */
	 public int randomYCoord(){
		 int randomCoord = (int)(1 + Math.random() * 10);
		 return randomCoord;
	 } 
	 
	/**
	 * gets ArrayList with 144 arrays starting from [0,0] to [11,11]
	 * @return the array
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
	 
	/**
	 * Move a character based on what key the user presses
	 * @param mho Character object, either mho or you
	 * @param moveX movement in x direction
	 * @param moveY movement in y direction
	 * @param justJumped boolean displaying status of jump 
	 */
	 public void move(Character mho, int moveX, int moveY){
		 if(k.action == "jump" && k.getJump() == false){
				int x = (int)(Math.random() * 9 + 1);
				int y = (int)(Math.random() * 9 + 1 );
				 mho.setXCoord(x);
				 mho.setYCoord(y);
				 
			 }
			
		 /*
		  * Conditional statements testing to see if mho is on an edge or a corner, 
		  * then updates coordinates based on key pressed by user
		  */
		 if(mho.getXCoord() >= 10 && ((k.action == "right") || (k.action == "up and right") || (k.action == "down and right"))) {
		 }
		 else if (mho.getXCoord() <= 1 && ((k.action == "left") || (k.action == "down and left") || (k.action == "up and left"))) {
		 }
		 else if (mho.getYCoord() >= 10 && ((k.action == "down") || (k.action == "down and left") || (k.action == "down and right"))) {
		 }
		 else if (mho.getYCoord() <= 1 && ((k.action == "up") || (k.action == "up and right") || (k.action == "up and left"))) {
		 }
		 else {
			 mho.setXCoord(mho.getXCoord() + moveX);
			 mho.setYCoord(mho.getYCoord() + moveY); 
		 }
		 
		
		 
	 }
	 
	 /**
	  * Update game state to current values
	  * @param k UserKeyPress that implements keyListener
	  */
	 public void UpdateGameState(UserKeyPress k){

		 if (k.action == "jump" && k.getJump() == false){
				int xMove = k.getMoveX();
				int yMove = k.getMoveY();
				move(you, (xMove), (yMove));
				k.resetX();
				k.resetY();
				repaint();
				k.setJump(true);
				

			 }
		 else{
			 int xMove = k.getMoveX();
			 int yMove = k.getMoveY();
			 move(you, (xMove), (yMove));
			 k.resetX();
			 k.resetY();
			 repaint();
		 }
		 
		
		 
	 }

	
}
