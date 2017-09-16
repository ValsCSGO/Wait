package com.vals.wait.snake;

import java.util.ArrayList;
import java.util.List;

public class Player {

	public static List<Integer> xLocs = new ArrayList<Integer>();
	public static List<Integer> yLocs = new ArrayList<Integer>();
	private int headLocX;
	private int headLocY;
	private int currentDirection;
	public final int LOWEST_LENGTH = 5;
	public int currentLength;
	public int points;

	public Player(int headx, int heady, int currentdirection) {
		this.headLocX = headx;
		this.headLocY = heady;
		this.currentDirection = currentdirection;
	}

	public int getHeadLocX() {
		return headLocX;
	}

	public int getHeadLocY() {
		return headLocY;
	}

	public int getCurrentLength() {
		return currentLength;
	}

	public void setCurrentLength(int curr) {
		currentLength = curr;
	}

	public int getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(int curr) {
		currentDirection = curr;
	}

	public void move() {
		for(int i = 1; i < xLocs.size(); i++) {
			int x = xLocs.get(i);
			int y = yLocs.get(i);
			if(headLocX == x && headLocY == y) {
				SnakePlayScreen.gameOver();
			}
		}
		addLoc(headLocX, headLocY);
		switch(currentDirection) {
		case 0:
			headLocY -= 1;
			break;
		case 1:
			headLocX -= 1;
			break;
		case 2:
			headLocY += 1;
			break;
		case 3:
			headLocX += 1;
			break;
		}
		if(headLocX > SnakePlayScreen.boardSizeX) 
			headLocX = 0;
		if(headLocY > SnakePlayScreen.boardSizeY) 
			headLocY = 0;
		if(headLocX < 0) 
			headLocX = SnakePlayScreen.boardSizeX;
		if(headLocY < 0) 
			headLocY = SnakePlayScreen.boardSizeY;
	}

	public void addLoc(int x, int y) {
		xLocs.add(x);
		yLocs.add(y);
		if(xLocs.size() > currentLength) {
			xLocs.remove(0);
			yLocs.remove(0);
		}
	}
}
