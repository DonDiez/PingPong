package pongV2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {
	double x,y,xVel,yVel;


	
	public Ball() {
		x=350;
		y=250;
		xVel=randomSpeed(10);
		System.out.println(xVel);
		yVel=2;
	}
	public int randomSpeed(int absolutt) {
		Random rn = new Random();
		int x=rn.nextInt(absolutt - (-absolutt) + 1) + (-absolutt);
		if (x%2==0) {
			return -1;
		}else {
			return 1;
		}
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval((int)x-10, (int)y-10, 20, 20);
	}
	
	public void move() {
		x+=xVel;
		y+=yVel;
		if (y<10) {
			yVel=-yVel;
		}else if(y>490) {
			yVel=-yVel;
		}
	}
	public void cheackPaddleCollision(Paddle p1,Paddle p2) {
		//System.out.println("ball: "+x+" "+y+" \nP1: "+p1.getY()+" "+(p1.getY()+80)+" \nP2: "+p2.getY()+" "+(p2.getY()+80));
		if(x<=50) {
			if (y >= p1.getY() && y<=p1.getY()+26) {
				xVel=-xVel+1;
			}
			else if (y > p1.getY()+26 && y<=p1.getY()+52) {
				xVel=-xVel;
			}
			else if (y > p1.getY()+52 && y<=p1.getY()+80) {
				xVel=-xVel-1;
			}
			
		}else if(x>=650){
			if (y>= p2.getY() && y<= p2.getY()+80) {
				xVel=-xVel-1;
			}else if (y > p2.getY()+26 && y<=p2.getY()+52) {
				xVel=-xVel;
			}
			else if (y > p2.getY()+52 && y<=p2.getY()+80) {
				xVel=-xVel+1;
		}}
	}
	
	public int getX(){
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
}
