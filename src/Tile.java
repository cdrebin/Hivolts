import java.awt.Color;

public class Tile {
	private Boolean empty;
	private String type;
	private int xCoord;
	private int yCoord;

	Tile(){
		empty = true;
		xCoord = 0;
		yCoord = 0;
	}
	
	Tile(Boolean empty, String type, int xCoord, int yCoord){
		this.empty = empty;
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
	
	public Boolean getEmptyValue(){
		return this.empty;
	}
	
	public String getType(){
		return this.type;
	}
	

	public void setEmptyValue(Boolean newEmptyValue){
		empty = newEmptyValue;
	}
	
	public void setType(String newType){
		type = newType;
	}
	
}
