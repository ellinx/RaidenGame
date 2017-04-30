package ellinx.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * tools (eg. loading image)
 * @author Ellinx
 *
 */
public class GameUtil {
	//utility class usually has private constructor and static methods
	private GameUtil() {
		
	}
	
	public static Image getImage(String path) {
		URL u = GameUtil.class.getClassLoader().getResource(path);
		BufferedImage img = null;
		try {
			img = ImageIO.read(u);
			//img = javax.imageio.ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
}
