import java.awt.Color;

/**
 * Tile class. Tiles are assigned to each position on grid, can be filled or empty
 * @author Camille Bourbonnais
 *
 */
public class Tile {
	private String type;
	private int xCoord;
	private int yCoord;

	Tile(){
		type = "empty";
		xCoord = 0;
		yCoord = 0;
	}
	
	Tile(String type, int xCoord, int yCoord){
		this.type = type;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
	}	
	
	public int getXCoord(){
		return this.xCoord;
	}
	
	public int getYCoord(){
		return this.yCoord;
	}
	
	public void setXCoord(int newXCoord){
		xCoord = newXCoord;
	}
	
	public void setYCoord(int newYCoord){
		yCoord = newYCoord;
	}
	
	public String getType(){
		return this.type;
	}

	public void setType(String newType){
		type = newType;
	}
	
}
