package spaceShooter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 400;
	public static final int HEIGHT = WIDTH / 12*9;
	public static final int SCALE = 2;
	public final String TITLE = "2D Space Shooter";
	
	private boolean running = false;
	private Thread thread;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null, background = null;
	
	private Player p;
	private Controller c;
	private Textures tex;
	private Enemy e;
	
	
	public void init() {
		
		
		requestFocus();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			
			spriteSheet = loader.loadImage("res/sprite_sheet.png");
			background = loader.loadImage("res/background.png");
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		addKeyListener(new KeyInput(this));
		
		tex = new Textures(this);
		
		p = new Player(350, 400, 0, tex);
		c = new Controller(this, tex);
		e = new Enemy (350, 0, tex);
		
	}
	
	private synchronized void start() {
		if(running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		if(!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(1);
	}
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
				
			}
			Rectangle r = new Rectangle((int) e.getX(),(int) e.getY(), 70, 90);
			Rectangle t = new Rectangle((int) p.getX(), (int) p.getY(), 100, 75);
			
			if(r.intersects(t)) {
				System.out.println("GAME OVER!");
				Graphics g = getGraphics();
				g.setColor(Color.RED);
				g.setFont(new Font("arial", Font.BOLD, 80));
				g.drawString("GAME OVER", WIDTH/2, HEIGHT/2+200);
				break;
			}
		}
		stop();
		
	}
	private void tick() {
		p.tick();
		c.tick();
		e.tick();
	}
	private void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		/////////////////////////////////
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		g.drawImage(background, 0, 0,800,800,this);
		
		p.render(g);
		c.render(g);
		e.render(g);
		
		
		/////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			p.setAngle(5);
		} else if(key == KeyEvent.VK_LEFT) {
			p.setAngle(-5);
		} else if(key == KeyEvent.VK_DOWN) {
			p.setVelY(1);
		} else if(key == KeyEvent.VK_UP) {
			p.setVelX(1);
		} else if(key == KeyEvent.VK_SPACE) {
			
			c.addBullet(new Bullet (p.getX() + 20, p.getY(), tex, p.getAngle()));
		}
			
	}
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_RIGHT) {
			p.setAngle(0);
		} else if(key == KeyEvent.VK_LEFT) {
			p.setAngle(0);
		} else if(key == KeyEvent.VK_DOWN) {
			p.setVelY(0);
		} else if(key == KeyEvent.VK_UP) {
			p.setVelX(0);
		}
	}
	
	
	public static void main(String args[]) {
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
		
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	public Enemy getEnemy() {
		return this.e;
	}
	
}
