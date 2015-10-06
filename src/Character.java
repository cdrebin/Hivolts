import java.awt.Color;

public class Character{
	private int size;
	private Color baseColor;
	private int xCoord;
	private int yCoord;
	
	Character(){
		size = 40;
		xCoord = 0;
		yCoord = 0;
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

}
