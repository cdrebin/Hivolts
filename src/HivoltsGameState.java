import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Displays and updates the game state of the Hivolts game 
 * @author Claire Drebin and Camille Bourbonnais
 *
 */

public class HivoltsGameState extends JFrame implements ActionListener{
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
	 
	 //grid boundaries
	 int leftBound = 0;
	 int rightBound = 11;
	 int upperBound = 0;
	 int lowerBound = 11;
	 
	 //used to signify when game is over
	 boolean gameOver = false;
	 public boolean displayedGameOver = false;

	 
	 //set you character with random coordinates
	 Character you = new Character();
	 
	 //initalize character and fence arrays
	 Character mho[] = new Character[12];
	 Character fences[] = new Character[20];

	 //initialize tiles
	 Tile tiles[][] = new Tile[12][12];

	 //initialize key listener
	 UserKeyPress keyPress = new UserKeyPress();



	 
	//Hivolts constructor
	public HivoltsGameState() {
		init();
	}
	
	/**Majority of init() created by Claire Drebin, initializeGame() created by Camille Bourbonnais
	 * Initializes window, grid, keyListener, fence and mho arrays
	 */
	public void init() {
		setSize(1200, 1200);
		setBackground(Color.WHITE);
		repaint();
		
		addKeyListener(keyPress);
		
		//set screen width and height to actual values of window
		 screenW = getWidth();
		 screenH = getHeight();
		//initializeArray(mho);
		//initializeArray(fences);
		initializeGame(mho, fences, you, tiles);
	}
	
	/* Created by Claire and Camille
	 * (non-Javadoc)
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g){
		
		UpdateGameState(keyPress);
		

		if (gameOver == false){
			drawGame(g);
		}
		
		else{
			displayGameOver(g);	
			displayedGameOver = true;
		}
		
		try {
		    Thread.sleep(100);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		
		repaint();
	}
	
	/** Created by Camille
	 * draws background, grid, mhos and fences
	 * @param g Graphics object
	 */
	public void drawGame(Graphics g){
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
		
		repaint();
	}
	
	
	/** Created by Claire
	 * display Game Over, clear screen
	 * @param g graphics object
	 */
	public void displayGameOver(Graphics g){
		//g.setColor(Color.BLACK);
		g.fillRect(0,0, screenW, screenH);
		//g.setColor(Color.WHITE);
		g.drawString("GAME OVER :(", screenW / 2, screenH / 2);
		g.setColor(Color.CYAN);
		g.fillRect(39,45,10,10);
		
//		JPanel buttonPanel = new JPanel();
//	
//		JButton quitButton = new JButton("quit");
//		
//		buttonPanel.add(quitButton);
//		
//		getContentPane().add(buttonPanel, BorderLayout.SOUTH);
//		
	}
	
	public void nextAction(){
		//int action = UserFeedback.choose();	 
		
		
		
		/**if(action == 1){
			dispose();
		}
		else{
			initializeGame(fences, fences, you, tiles);
		}
		*/
	}
	
	
	/**Created by Claire
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
	
	/** Created by Camille
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
	
	/** Created by Camille
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
	
	/** Created by Camille
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

	/** Created by Camille
	 * Initialize an array that is used to create all objects
	 * @param array type of character array
	 */
	 public void initializeArray(Character array[]) {
		 for (int i = 0; i < array.length; i++) {
		 }
	 }
	 
	/**Created by Camille
	 * 
	 * @param mhos
	 * @param fences
	 * @param you
	 * @param tiles
	 */
	public void initializeGame(Character mhos[], Character fences[], Character you, Tile tiles[][]) {
	for (int i = 0; i < 12; i++) {
		for (int j = 0; j < 12; j++) {
			tiles[i][j] = new Tile();
		}
	}
	ArrayList<Integer[]> array = coords();
	ArrayList<Integer[]> newArray = new ArrayList<Integer[]>();
	for (int i = 0; i < 33; i++) {
		int n = (int)(Math.random() * array.size());
		newArray.add(array.get(n));
		array.remove(n);
	}
	for (int i = 0; i < 12; i++) {
		 mhos[i] = new Character(newArray.get(i)[0], newArray.get(i)[1]);
		 tiles[mhos[i].getXCoord()][mhos[i].getYCoord()].setXCoord(mhos[i].getXCoord());
		 tiles[mhos[i].getXCoord()][mhos[i].getYCoord()].setYCoord(mhos[i].getYCoord());
		 tiles[mhos[i].getXCoord()][mhos[i].getYCoord()].setType("mho");
	}			
	for (int i = 0; i < 20; i++) {
		fences[i] = new Character(newArray.get(i+12)[0], newArray.get(i+12)[1]);
		tiles[fences[i].getXCoord()][fences[i].getYCoord()].setXCoord(fences[i].getXCoord());
		tiles[fences[i].getXCoord()][fences[i].getYCoord()].setYCoord(fences[i].getYCoord());
		tiles[fences[i].getXCoord()][fences[i].getYCoord()].setType("fence");
	}
	 you.setXCoord(newArray.get(32)[0]); you.setYCoord(newArray.get(32)[1]);
	 tiles[you.getXCoord()][you.getYCoord()].setXCoord(you.getXCoord());
	 tiles[you.getXCoord()][you.getYCoord()].setYCoord(you.getYCoord());
	 tiles[you.getXCoord()][you.getYCoord()].setType("you");
}

	/**Created by Claire
	 * Converts grid x-coordinates to screen coordinate equivalent
	 * @param gx x-coordinate on grid system (12x12)
	 * @return x-coordinate on screen system (screen width x screen height)
	 */
	 public int Gridx2Screenx (double gx) {
			int sX =(int)( (gx / GRIDW) * canvasW + canvasOx); //convert to canvas coordinates, add canvas offset
			return sX;
		}	 
	
	 /**Created by Claire
	  * Converts grid x-coordinates to screen coordinate equivalent 
	  * @param gy y-coordinate on grid system (12x12)
	  * @return y-coordinate on screen system (screen width x screen height)
	  */
	 public int Gridy2Screeny (double gy) {
			int sY =(int)( (gy / GRIDH) * canvasH + canvasOy); //convert to canvas coordinates, add canvas offset
			return sY;
		}	 
	 
	 /**Created by Claire
	  * Converts grid width to screen width
	  * @param gW grid width
	  * @return screen width
	  */
	 public int GridWidth2ScreenWidth (double gW){
		 int sW =(int)( (gW) * (canvasW / GRIDW)); //use ratio of canvas to grid to convert grid width to screen width
		 return sW;
	 }
	 
	 /**Created by Claire
	  * Converts grid height to screen height
	  * @param gH grid height
	  * @return screen height
	  */
	 public int GridHeight2ScreenHeight(double gH){
		 int sH =(int)( (gH) * (canvasH / GRIDH) + canvasOy);//use ratio of canvas to grid to convert grid height to screen height
		 return sH;
	 }
	 
	 /**Created by Claire
	  * Generate random x-coordinate between 1 and 11
	  * @return random x-coordinate
	  */
	 public int randomXCoord(){
		 int randomCoord = (int)(1 + Math.random() * 10);
		 return randomCoord;
	 }
	 
	 /**Created by Claire
	  * Generate random y-coordinate between 1 and 11
	  * @return random y-coordinate
	  */
	 public int randomYCoord(){
		 int randomCoord = (int)(1 + Math.random() * 10);
		 return randomCoord;
	 } 
	 
	/**Created by Camille
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
	 
	/**Created by Claire
	 * Changes the coordinates of a character based on what key the user presses
	 * @param mho Character object, either mho or you
	 * @param moveX movement in x direction
	 * @param moveY movement in y direction
	 * @param justJumped boolean displaying status of jump 
	 */
	 public void move(Character mho, int moveX, int moveY){
		 if(keyPress.action == "jump" && keyPress.getJump() == false) {
				int x = (int)(Math.random() * 9 + 1);
				int y = (int)(Math.random() * 9 + 1 );
				 mho.setXCoord(x);
				 mho.setYCoord(y);
			 }
		
		 
		 /*
		  * Conditional statements testing to see if mho is on an edge or a corner, 
		  * then updates coordinates based on key pressed by user
		  */
		 
		
		 
		 if(mho.getXCoord() >= rightBound  && ((keyPress.action == "right") || (keyPress.action == "up and right") || (keyPress.action == "down and right"))) {
			 gameOver = true;
		 }
		 else if (mho.getXCoord() <= leftBound  && ((keyPress.action == "left") || (keyPress.action == "down and left") || (keyPress.action == "up and left"))) {
			 gameOver = true;
		 }
		 else if (mho.getYCoord() >= lowerBound  && ((keyPress.action == "down") || (keyPress.action == "down and left") || (keyPress.action == "down and right"))) {
			 gameOver = true;
		 }
		 else if (mho.getYCoord() <= upperBound  && ((keyPress.action == "up") || (keyPress.action == "up and right") || (keyPress.action == "up and left"))) {
			 gameOver = true;
		 }
		 else {
			 mho.setXCoord(mho.getXCoord() + moveX);
			 mho.setYCoord(mho.getYCoord() + moveY); 
		 }
	 }
	 
	 /**Created by Claire
	  * Update game state to current values
	  * @param keyPress UserKeyPress that implements keyListener
	  */
	 public void UpdateGameState(UserKeyPress keyPress){
		
		 
		 if (keyPress.action == "jump" && keyPress.getJump() == false){
				
			 	//if tile you are jumping to contains a fence
				if(tiles[you.getXCoord()][you.getYCoord()].getType().equals("fence")){
					
					//keep jumping until you no longer on a fence
					while((tiles[you.getXCoord()][you.getYCoord()].getType()).equals("fence")){
						System.out.println("Sitting on a fence");

						int xMove = keyPress.getMoveX();
						int yMove = keyPress.getMoveY();
						move(you, (xMove), (yMove));
						keyPress.resetX();
						keyPress.resetY();
					}
					
				}
				//if not on a fence, move you
				else{
					System.out.println("NOT sitting on a fence");
					int xMove = keyPress.getMoveX();
					int yMove = keyPress.getMoveY();
					move(you, (xMove), (yMove));
					keyPress.resetX();
					keyPress.resetY();
				}
				
			
				keyPress.setJump(true);
				

			 }
		 else{
			 int xMove = keyPress.getMoveX();
			 int yMove = keyPress.getMoveY();
			 move(you, (xMove), (yMove));
			 keyPress.resetX();
			 keyPress.resetY();
		 }
		 
		 gameOver = testGameOver();
		 
		 if (displayedGameOver == true){
			 nextAction();
		 }
	 }
	 
	 /**Created by Claire
	  * Tests to see if location of "you" is already filled
	  * @return value of gameOver (false = game is NOT over, true = game IS over)
	  */
	 public boolean testGameOver(){
		 if(gameOver == true){
		 
		 }
		 
		 //not jumping, but hit a fence or a mho
		 else if(keyPress.getAction() != "jump" &&( (tiles[you.getXCoord()][you.getYCoord()].getType()).equals("fence") || 
				 ((tiles[you.getXCoord()][you.getYCoord()].getType()).equals("mho")))) {
			gameOver = true;
		 }
		 
		 //jumping on top of a mho
		 else if (keyPress.getAction() == "jump" && ((tiles[you.getXCoord()][you.getYCoord()].getType()).equals("mho"))){
			 gameOver = true;
		 }
			
		 else{
			gameOver = false; 
		 }
		 
		 return gameOver;
	 }
	 
	 public void resetGame(){
		 gameOver = false;
		 displayedGameOver = false;
		 
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}


