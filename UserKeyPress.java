import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class UserKeyPress implements KeyListener{
	String action;
	
	UserKeyPress(){
	}
	
	public String getAction(){
		return action;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		char button = e.getKeyChar();
		
		switch(button){
		case 'q': action = "up and left";
			break;
		case 'w': action = "up";
			break;
		case 'e': action = "up and right";
			break;
		case 'a': action = "left";
			break;
		case 's': action = "sit";
			break;
		case 'd': action = "right";
			break;
		case 'z': action = "down and left";
			break;
		case 'x': action = "down";
			break;
		case 'c': action = "down and right";
			break;
		case 'j': action = "jump";
			break;
		default: action = "nothing significant was pressed"	;
		}
		
		System.out.println(action);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}