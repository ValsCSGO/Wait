package com.vals.wait.ballz;

import java.util.ArrayList;
import java.util.List;

public class Ball {
	
	private List<Ball> balls = new ArrayList<Ball>();
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
	
	public void move() {
		
	}
}
