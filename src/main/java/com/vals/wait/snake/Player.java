package com.vals.wait.snake;

import java.util.ArrayList;
import java.util.List;

public class Player {

	public static List<Integer> xLocs = new ArrayList<Integer>();
	public static List<Integer> yLocs = new ArrayList<Integer>();
	private int headLocX;
	private int headLocY;
	private int currentDirection;
	public int currentLength;
	public int points;
	public int lastDirection;

	public Player(int headx, int heady, int currentdirection) {
		this.headLocX = headx;
		this.headLocY = heady;
		this.currentDirection = currentdirection;
		xLocs.clear();
		yLocs.clear();
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

	public int getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(int last) {
		lastDirection = last;
	}

	public void move() {
		addLoc(headLocX, headLocY);
		if(xLocs.size() != 0) {
			if(lastDirection == -currentDirection) {
				SnakePlayScreen.gameOver();
				return;
			}
		}
		switch(currentDirection) {
		case -2:
			headLocY -= 1;
			break;
		case -1:
			headLocX -= 1;
			break;
		case 2:
			headLocY += 1;
			break;
		case 1:
			headLocX += 1;
			break;
		}
		if(headLocX > SnakePlayScreen.boardSizeX - 1) {
			headLocX = 0;
		} else if(headLocY > SnakePlayScreen.boardSizeY - 1) {
			headLocY = 0;
		} else if(headLocX < 0) {
			headLocX = SnakePlayScreen.boardSizeX - 1;
		} else if(headLocY < 0) {
			headLocY = SnakePlayScreen.boardSizeY - 1;
		}
		for(int i = 0; i < xLocs.size(); i++) {
			int x = xLocs.get(i);
			int y = yLocs.get(i);
			if(headLocX == x && headLocY == y) {
				SnakePlayScreen.gameOver();
			}
		}
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
