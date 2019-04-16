package spaceShooter;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player{

	
	static double x;
	static double y;
	
	private double angle;
	
	private double vel = 5;
	private double velo;
	private boolean forward;
	
	private BufferedImage player;
	
	private Textures tex;
	
	public Player(double x, double y, double angle, Textures tex) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		
		this.tex = tex;
		
		//SpriteSheet ss = new SpriteSheet(game.getSpriteSheet());
		
		//player= ss.grabImage(1, 1, 100, 75);
	}
	
	public void tick() {
		//x += velX;
		//y += velY;
		
		if(forward) {
			x += (Math.cos(Math.toRadians(angle-90)))* (vel*velo);
			y += (Math.sin(Math.toRadians(angle-90)))* (vel*velo);
		}
		if(!forward) {
			x -= Math.cos(Math.toRadians(angle-90))* (vel*velo);
			y -= Math.sin(Math.toRadians(angle-90))* (vel*velo);
		}
		
		//x += Math.cos(Math.toRadians(angle))* (vel);
		//y += Math.sin(Math.toRadians(angle))* (vel);
		
		if(x <= 0) {
			x = 0;
		}
		if(x >= 720) {
			x = 720;
		}
		if(y <= 0) {
			y = 0;
		}
		if(y >= 520) {
			y = 520;
		}
	}
	//double rotation = Math.toRadians(angle);
	//AffineTransform tx = AffineTransform.getRotateInstance(rotation, x, y);
	//AffineTransform op = new AffineTransform(tx, AffineTransformOp.TYPE_BILINEAR);
	
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D)g.create();
		g2d.rotate(Math.toRadians(angle), x+50, y+37);
		g2d.drawImage(tex.player, (int) x, (int) y, null);
		g2d.dispose();
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	
	public void setVelX(double velX) {
		velo = velX;
		this.forward = true;
	}
	public void setVelY(double velY) {
		velo = velY;
		this.forward = false;
	}
	public double getAngle() {
		return this.angle;
	}
	public void setAngle(double angle) {
		this.angle += angle;
		System.out.println("angle" + this.angle);
		
	}

}
