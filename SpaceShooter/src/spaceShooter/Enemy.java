package spaceShooter;

import java.awt.Graphics;

public class Enemy {

	
	 double x, y;
	
	private int hp;
	
	private Textures tex;
	
	public Enemy (double x, double y, Textures tex) {
		this.x = x;
		this.y = y;
		
		this.tex = tex;
		
		hp = 50;
		
	}
	
	public void tick() {
		//y += 3;
		
		double diffX = (Player.x - x);
		double diffY = (Player.y - y);
		
		double angle = (double)Math.atan2(diffY, diffX);
		
		x += 1.2 * Math.cos(angle);
		y += 1.2 * Math.sin(angle);
		
		
		if(x <= 0) {
			x = 0;
		}
		if(x >= 720) {
			x = 720;
		}
		if(y <= 0) {
			y = 0;
		}
		if(y >= 510) {
			y = 510;
		}
		
	}
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int) x, (int) y, null);
	}
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public int getHp() {
		return this.hp;
	}
}
