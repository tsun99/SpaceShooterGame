package spaceShooter;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage ss) {
		this.image = ss;
	}
	
	public BufferedImage grabImage(int col, int row, int width, int height) {
		
		BufferedImage img = image.getSubimage((col * 100) - 100, (row * 75) - 75, width, height);
		return img;
	}

}
