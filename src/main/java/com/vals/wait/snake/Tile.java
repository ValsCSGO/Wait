package com.vals.wait.snake;

import java.util.ArrayList;
import java.util.List;

import com.vals.wait.resources.Button;
import com.vals.wait.resources.Resources;

public class Tile {
	
	public static List<Tile> tiles = new ArrayList<Tile>();
	private double leftBorder;
	private double topBorder;
	private double rightBorder;
	private double bottomBorder;
	private int color;
	private Type type;
	
	public Tile(double x, double y, double size, Type type) {
		this.leftBorder = x * size + 2;
		this.topBorder = y * size + 2;
		this.rightBorder = ((x * size) + size) - 1;
		this.bottomBorder = ((y * size) + size) + 1;
		this.type = type;
		if(type == type.FOOD) {
			this.color = 0xFFFF0000;
		} else if (type == type.SNAKEHEAD){
			this.color = 0xEE00FF00;
		}else if(type == type.SNAKEBODY) {
			this.color = 0xFF00FF00;
		} else if (type == type.SPACE){
			this.color = 0x51111111;
		}
	}
	
	public static void drawTiles(int xOffset, int yOffset) {
		for(Tile t : tiles) {
			t.draw(xOffset, yOffset);
//			System.out.println(t.leftBorder + ", " +
//			t.topBorder + ", " +
//			t.rightBorder + ", " +
//			t.bottomBorder);
		}
	}
	
	private void draw(int xOffset, int yOffset) {
		Resources.drawRect(xOffset + leftBorder, yOffset + topBorder, xOffset + rightBorder, yOffset + bottomBorder, color);
	}
}
