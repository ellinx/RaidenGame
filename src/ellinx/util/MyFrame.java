package ellinx.util;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MyFrame extends Frame {
	
	
	//double buffering to improve animation
	private Image offScreenImage = null;
	public void update(Graphics g) {
		if (offScreenImage == null) 
			offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		
		Graphics gOff = offScreenImage.getGraphics();
		
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	/**
	 * loading window
	 */
	public void launchFrame() {
		//window size and location
		setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
		setLocation(100, 100);
		
		//default is invisible
		setVisible(true);
		
		new PaintThread().start();
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	
	class PaintThread extends Thread {
		
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
