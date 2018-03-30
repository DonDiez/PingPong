package pongV2;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class tennis extends Applet implements Runnable,KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int WIDTH = 700, HEIGHT = 500;
	Thread thread;
	HumanPaddle p1;
	HumanPaddle p2;
	Ball b1;
	boolean gameStarted;
	
	public void init() {
		this.resize(WIDTH, HEIGHT);
		this.addKeyListener(this);
		p1 = new HumanPaddle(1);
		p2 = new HumanPaddle(2);
		b1 = new Ball();
		gameStarted = false;
		thread = new Thread(this);
		thread.start();
	}
	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.fillRect((WIDTH/2)-5, 0,10,HEIGHT);
		g.setColor(Color.BLACK);
		if (b1.getX()<-10 || b1.getX()>690) {
			g.setColor(Color.red);
			g.drawString("Game Over!", 350, 250);
			}else {
			p1.draw(g);
			p2.draw(g);
			b1.draw(g);
			}
	}
	public void update (Graphics g) {
		paint(g);
	}

	public void run() {
		for (;;) {
			if (gameStarted) {
				p1.move();
				p2.move();
				b1.move();
				b1.cheackPaddleCollision(p1, p2);
			}
			
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			p1.setUpAccel(true);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			p1.setDownAccel(true);
		}else if (e.getKeyCode()==KeyEvent.VK_ENTER) {
			gameStarted=true;
		}
		
		if (e.getKeyCode()==KeyEvent.VK_W) {
			p2.setUpAccel(true);
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			p2.setDownAccel(true);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode()==KeyEvent.VK_UP) {
			p1.setUpAccel(false);
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			p1.setDownAccel(false);
		}
		if (e.getKeyCode()==KeyEvent.VK_W) {
			p2.setUpAccel(false);
		}else if(e.getKeyCode()==KeyEvent.VK_S) {
			p2.setDownAccel(false);
		}
		
		
	}

}
