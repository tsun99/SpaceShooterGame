package spaceShooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Bullet {
	
	private double x;
	private double y;
	private double angle;
	
	
	private Textures tex;
	
	public Bullet(double x, double y, Textures tex, double angle) {
		this.x = x;
		this.y = y;
		
		this.tex = tex;
		this.angle = angle;
		
	}
	public void tick() {
		//y -= 10;
		
		x += (Math.cos(Math.toRadians(angle-90)))* (10);
		y += (Math.sin(Math.toRadians(angle-90)))* (10);
	}
	
	public void render(Graphics g) {
		//g.drawImage(tex.missile, (int) x, (int) y, null);
		
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.rotate(Math.toRadians(angle), x+50, y+37);
		g2d.drawImage(tex.missile, (int) x, (int) y, null);
		g2d.dispose();
	}
	public double getY() {
		return this.y;
	}
	public double getX() {
		return this.x;
	}
	public double getAngle() {
		return this.angle;
	}
	public void setAngle(double angle) {
		this.angle = angle;
	}
}
