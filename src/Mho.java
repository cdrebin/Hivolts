import java.awt.*;

public class Mho extends Character{
	private final Color YELLOW = new Color(0xEE9605);

	Mho () {
		super.setXCoord((int)(1 + Math.random() * 10));
		super.setYCoord((int)(1 + Math.random() * 10));
	}
}