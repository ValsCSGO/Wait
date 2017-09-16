package com.vals.wait.ballz;

public class Block {
	
	private double xLoc;
	private double yLoc;
	private int color;
	private int life;
	private boolean isDead;
	
	public Block(double x, double y, int life, int color) {
		this.xLoc = x;
		this.yLoc = y;
		this.life = life;
		this.color = color;
		this.isDead = false;
	}
	
	private void blockCollide() {
		if(life > 0) {
			life -= 1;
		} else {
			isDead = true;
		}
	}
}