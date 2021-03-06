import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Implementation of key listener
 * @author Claire Drebin
 *
 */
public class UserKeyPress implements KeyListener{
	String action;
	int moveX = 0;
	int moveY = 0;
	boolean justJumped = false;
	
	UserKeyPress(){
	}
	
	/**
	 * accessor method for action
	 * @return which action should be taken
	 */
	public String getAction(){
		return action;
	}
	
	/**
	 * accessor method for moveX
	 * @return value of moveX describing movement in x direction
	 */
	public int getMoveX(){
		return moveX;
	}
	
	/**
	 * accessor method for moveY
	 * @return value of moveY describing movement in y direction
	 */
	public int getMoveY(){
		return moveY;
	}
	
	/**
	 * resets the value of moveX to zero
	 */
	public void resetX(){
		moveX = 0;
	}
	
	/**
	 * resets the value of moveY to zero
	 */
	public void resetY(){
		moveY = 0;
	}
	
	/**
	 * accessor method for justJumped
	 * @return justJumped
	 */
	public boolean getJump(){
		return justJumped;
	}
	
	/**
	 * mutator method for justJumped, sets it to true or false
	 * @param value state of jumping (true or false)
	 */
	public void setJump(boolean value){
		justJumped = value;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		char button = e.getKeyChar();
		
		switch(button){
		case 'q': action = "up and left";
			moveX = -1;
			moveY = -1;
			break;
		case 'w': action = "up";
			moveX = 0;
			moveY = -1;
			break;
		case 'e': action = "up and right";
			moveX = 1;
			moveY = -1;
			break;
		case 'a': action = "left";
			moveX = -1;
			moveY = 0;
			break;
		case 's': action = "sit";
			moveX = 0;
			moveY = 0;
			break;
		case 'd': action = "right";
			moveX = 1;
			moveY = 0;
			break;
		case 'z': action = "down and left";
			moveX = -1;
			moveY = 1;
			break;
		case 'x': action = "down";
			moveX = 0;
			moveY = 1;
			break;
		case 'c': action = "down and right";
			moveX = 1;
			moveY = 1;
			break;
		case 'y': action = "yes";
			break;
		case 'n': action = "no";
			break;
		case 'j': action = "jump";
			break;
			
		default: action = "nothing significant was pressed"	;
			moveX = 0;
			moveY = 0;
		}
				
	}

	@Override
	public void keyReleased(KeyEvent e) {
		char button = e.getKeyChar();
		if (button == 'j'){
			justJumped = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}