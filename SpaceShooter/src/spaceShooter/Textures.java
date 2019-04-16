package spaceShooter;

import java.awt.image.BufferedImage;

public class Textures {
	
	public BufferedImage player, missile, enemy;
	
	private SpriteSheet ss;
	
	public Textures(Game game) {
		ss = new SpriteSheet(game.getSpriteSheet());
		
		getTextures();
	}
	
	private void getTextures() {
		player = ss.grabImage(1, 1, 100, 75);
		missile = ss.grabImage(2, 1, 100, 32);
		enemy = ss.grabImage(1, 2, 100, 100);
	}

}
