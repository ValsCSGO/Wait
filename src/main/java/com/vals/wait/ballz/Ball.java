package com.vals.wait.ballz;

import java.util.ArrayList;
import java.util.List;

public class Ball {
	
	private List<Ball> balls = new ArrayList<Ball>();
	private double startLocX;
	private double startLocY;
	private double xLoc;
	private double yLoc;
	private double xVel;
	private double yVel;
	private boolean isDead;
	private int totalBallCount;
	private int roundBallCount;
	
	public Ball(double x, double y, double xVel, double yVel) {
		this.xLoc = x;
		this.yLoc = y;
		this.xVel = xVel;
		this.yVel = yVel;
		isDead = false;
	}
	
	public double getStartX() {
		return startLocX;
	}
	
	public void setStartX(double newX) {
		startLocX = newX;
	}
	
	public double getStartY() {
		return startLocY;
	}
	
	public void setStartY(double newY) {
		startLocY = newY;
	}
	
	public void move(double xVel, double yVel, double left, double top, double right, double bottom) {
		for(Ball b : balls) {
			if(!b.isDead) {
				xLoc += xVel;
				yLoc += yVel;
				if(b.xLoc > right) {
					xLoc = -xLoc;
				}
				if(b.xLoc < left) {
					xLoc = -xLoc;
				}
				if(b.yLoc < top) {
					xLoc = -xLoc;
				}
				if(b.yLoc > bottom) {
					isDead = true;
				}
			}
		}
	}
	
	public void drawBalls() {
		for(Ball b : balls)
			drawBall(b.xLoc, b.yLoc);
	}
	
	public void drawBall(double x, double y) {
		
	}
}
