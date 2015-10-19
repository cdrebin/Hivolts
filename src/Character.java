import java.awt.Color;

/**
 * Character class. Each character has a certain size, base color, and position
 * @author Camille Bourbonnais
 *
 */
public class Character{
	private int size;
	private Color baseColor;
	private int xCoord;
	private int yCoord;
	public int numWins;
	public int numLosses;
	public boolean alive;
	
	Character(){
		size = 40;
		xCoord = 0;
		yCoord = 0;
		numWins = 0;
		numLosses = 0;
		alive = true;
	}
	
	Character(int size, Color baseColor, int xCoord, int yCoord){
		this.size = size;
		this.baseColor = baseColor;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}
	
	Character(int xCoord, int yCoord){
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		alive = true;
	}
	
	public void addWin(){
		numWins++;
	}
	
	public void addLoss(){
		numLosses++;
	}
	public int getSize(){
		return this.size;
	}
	
	public Color getBaseColor(){
		return this.baseColor;
	}
	
	public int getXCoord(){
		return this.xCoord;
	}
	
	public int getYCoord(){
		return this.yCoord;
	}
	
	public void setSize(int newSize){
		this.size = newSize;
	}
	
	public void setBaseColor(Color newBaseColor){
		this.baseColor = newBaseColor;
	}
	public void setXCoord(int newXCoord){
		this.xCoord = newXCoord;
	}

	public void setYCoord(int newYCoord){
		this.yCoord = newYCoord;
	}

	public boolean getAlive(){
		return this.alive;
	}
	
	public void setAlive(boolean newAlive){
		this.alive = newAlive;
	}
}
