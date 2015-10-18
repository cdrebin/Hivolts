import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UserFeedback{
	
	public static int choose(){
		String[] options = {"Play Again", "Quit"};
		JFrame j = new JFrame();
		JLabel l = new JLabel();
		j.add(l);
		
		
		int index = JOptionPane.showOptionDialog(null,l, "You lost. What next?",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
		
		return (index);
		}
	}