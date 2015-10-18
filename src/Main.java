import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * main class containing HivoltsGameState object
 * @author Claire Drebin
 *
 */

public class Main{
	public static void main (String[] args){
		displayKey();
		HivoltsGameState hivoltsGame = new HivoltsGameState();
		hivoltsGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		hivoltsGame.setVisible(true);
	
	
	}
	

	public static void displayKey(){
		System.out.println("KEY");
		System.out.println("q: up and left");
		System.out.println("w: up");
		System.out.println("e: up and right");
		System.out.println("a: left");
		System.out.println("s: sit (stay on the same square for one turn)");
		System.out.println("d: right");
		System.out.println("z: down and left");
		System.out.println("x: down");
		System.out.println("c: down and right");
		System.out.println("j: jump to random square");
	}
	
}