package ellinx.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ellinx.util.Constant;
import ellinx.util.GameUtil;
import ellinx.util.MyFrame;

public class PlaneGameFrame extends MyFrame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane p = new Plane("images/teddybear0.png",50,50);
	
	ArrayList bulletList = new ArrayList();
	
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		p.draw(g);
		
		for (int i=0;i<bulletList.size();i++) {
			Bullet b = (Bullet) bulletList.get(i);
			b.draw(g);
			
			//detect conflict
			boolean peng = b.getRect().intersects(p.getRect());
			if (peng) {
				System.out.println("###############peng!");
			}
		}
	}
	

	
	public void launchFrame() {
		super.launchFrame();
		addKeyListener(new KeyMonitor());
		
		//generate bullets
		for (int i=0;i<50;i++) {
			Bullet b = new Bullet();
			bulletList.add(b);
		}
	}
	
	//inner class can access class field
	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("press key: " + e.getKeyCode());
			p.addDirection(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("release key: " + e.getKeyCode());
			p.deleteDirection(e);
		}
		
	}
	
	public static void main(String[] args) {
		new PlaneGameFrame().launchFrame();
	}
}
