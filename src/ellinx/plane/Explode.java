package ellinx.plane;

import java.awt.Graphics;
import java.awt.Image;

import ellinx.util.GameUtil;

public class Explode {
	double x,y;
	static Image[] imgs = new Image[9];
	static {
		for (int i=0;i<9;i++) {
			imgs[i] = GameUtil.getImage("images/explosion/explosion0"+(i+1)+".png");
			imgs[i].getWidth(null);//first time loading won't display image, use it to display
		}
	}
	int index;
	
	public void draw(Graphics g) {
		if (index<=8) {
			g.drawImage(imgs[index], (int)x, (int)y, null);
			index++;			
		}
	}
	
	public Explode(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
