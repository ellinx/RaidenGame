package ellinx.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import ellinx.util.Constant;
import ellinx.util.GameUtil;
import ellinx.util.MyFrame;

public class PlaneGameFrame extends MyFrame{
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane p = new Plane("images/teddybear0.png",50,50,15);
	
	ArrayList bulletList = new ArrayList();
	
	Explode explosion;
	
	Date startTime;
	Date endTime;
	
	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		p.draw(g);
		
		for (int i=0;i<bulletList.size();i++) {
			Bullet b = (Bullet) bulletList.get(i);
			b.draw(g);
			
			//detect conflict
			boolean peng = b.getRect().intersects(p.getRect());
			if (peng) {
				p.setAlive(false);
				
				if (explosion==null) {
					endTime = new Date();
					explosion = new Explode(p.x,p.y);					
				}
				explosion.draw(g);
				break;
			}
		}
		
		if (!p.isAlive()) {
			printInfo(g,"Game Over", 50, Constant.GAME_WIDTH/2-100, Constant.GAME_HEIGHT/2, Color.red);
			int period = (int)(endTime.getTime() - startTime.getTime())/1000;
			printInfo(g, "Survived " + period +" seconds", 20, 120, 260, Color.red);
		}
		
		
	}
	
	public void printInfo(Graphics g, String info, int fontSize, int x, int y, Color color) {
		Color c = g.getColor();
		Font f = g.getFont();
		g.setColor(color);
		g.setFont(new Font("TimesRoman",Font.BOLD,fontSize));
		g.drawString(info, x, y);
		
		g.setColor(c);
		g.setFont(f);
	}
	
	public void launchFrame() {
		super.launchFrame();
		addKeyListener(new KeyMonitor());
		
		//generate bullets
		for (int i=0;i<10;i++) {
			Bullet b = new Bullet();
			bulletList.add(b);
		}
		
		startTime = new Date();
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
