package ellinx.plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import ellinx.util.GameUtil;

public class Plane {
	Image img;
	double x,y;
	int speed = 10;
	boolean left,right,up,down;
	
	int width,height;
	
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	
	public void draw(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
		move();
	}

	public void move() {
		if (left) {
			x -= speed;
		}
		if (right) {
			x += speed;
		}
		if (up) {
			y -= speed;
		}
		if (down) {
			y += speed;
		}
	}
	
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		default:
			break;
		}
	}
	
	public void deleteDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}
	}

	public Plane(String imgPath, double x, double y) {
		this.img = GameUtil.getImage(imgPath);;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.x = x;
		this.y = y;
	}
	
	public Plane() {
		
	}
}
