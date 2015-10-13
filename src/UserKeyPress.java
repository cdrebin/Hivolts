import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UserKeyPress implements KeyListener{
	String action;
	int moveX = 0;
	int moveY = 0;
	boolean justJumped = false;
	
	UserKeyPress(){
	}
	
	public String getAction(){
		return action;
	}
	
	public int getMoveX(){
		return moveX;
	}
	
	public int getMoveY(){
		return moveY;
	}
	
	public void resetX(){
		moveX = 0;
	}
	
	public void resetY(){
		moveY = 0;
	}
	
	public boolean getJump(){
		return justJumped;
	}
	
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
		case 'j': action = "jump";
			
			break;
			
		default: action = "nothing significant was pressed"	;
		}
		
		System.out.println(action);
		
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