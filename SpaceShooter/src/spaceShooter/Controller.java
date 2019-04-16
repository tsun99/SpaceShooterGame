package spaceShooter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Controller {
	
	private LinkedList<Bullet> b = new LinkedList<Bullet>();
	//private LinkedList<Enemy> e = new LinkedList<Enemy>();
	
	Bullet TempBullet;
	Enemy tempEnemy;
	
	int HP;
	
	
	Game game;
	
	public Controller(Game game, Textures tex) {
		this.game = game;
		HP = 100;

	}
	
	public void tick() {
		for(int i = 0; i < b.size(); i++){
			TempBullet = b.get(i);
			
			
			
			if(TempBullet.getY() == 0) {
				TempBullet.setAngle(TempBullet.getAngle()*-1);
			}
			
			tempEnemy = game.getEnemy();
			
			Rectangle l = new Rectangle((int) TempBullet.getX(), (int) TempBullet.getY(), 100, 75);
			Rectangle k = new Rectangle((int) tempEnemy.getX(),(int) tempEnemy.getY(), 100, 100 );
			
			if(l.intersects(k)) {
				HP--;
				removeBullet(TempBullet);
			}
			
			if(HP <= 0) {
				System.out.println("YOU WIN");
				Graphics g = game.getGraphics();
				g.setColor(Color.blue);
				g.setFont(new Font("arial", Font.BOLD, 80));
				g.drawString("YOU WIN", game.WIDTH/2, game.HEIGHT/2+200);
				game.stop();
				
			}
			
			if(TempBullet.getY() < 0) {
				removeBullet(TempBullet);
			}
			
			
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < b.size(); i++){
			TempBullet = b.get(i);
			
			TempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet block) {
		b.add(block);
	}
	public void removeBullet(Bullet block) {
		b.remove(block);
	}

}
