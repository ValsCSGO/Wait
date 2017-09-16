package com.vals.wait.snake;

import java.util.Random;

public class Food {

	private int x;
	private int y;
	private int foodEaten;
	private boolean isDead;
	
	public Food(int x, int y) {
		this.x = x;
		this.y = y;
		isDead = false;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getFoodEaten() {
		return foodEaten;
	}
	
	public boolean isDead() {
		return isDead;
	}
	
	public void eat(Player p) {
		isDead = true;
		foodEaten += 1;
		p.currentLength += 1;
	}
	
	public static Food getRandomFood(int maxx, int maxy, Player p) {
		Random r = new Random();
		int ran = r.nextInt(maxx);
		int ran2 = r.nextInt(maxy);
		for(int i = 0; i < p.xLocs.size(); i++) {
			int x = p.xLocs.get(i);
			int y = p.yLocs.get(i);
			if(ran == x && ran2 == y) {
				getRandomFood(maxx, maxy, p);
			}
		}
		return new Food(ran, ran2);
	}
}
