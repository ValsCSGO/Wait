package com.vals.wait.resources;

import java.util.ArrayList;
import java.util.List;

public class Tile {
	
	public static List<Tile> tiles = new ArrayList<Tile>();
	private double leftBorder;
	private double topBorder;
	private double rightBorder;
	private double bottomBorder;
	private int color;
	private Type type;
	private int num;
	
	public Tile(double x, double y, double size, Type type, int number) {
		this.leftBorder = x * size + 2;
		this.topBorder = y * size + 2;
		this.rightBorder = ((x * size) + size) - 1;
		this.bottomBorder = ((y * size) + size) + 1;
		this.type = type;
		this.num = number;
		this.color = getFromType(type);
	}
	
	public int getFromType(Type t) {
		if(type == t.SNAKEFOOD) {
			return 0xFFFF0000;
		} else if (type == t.SNAKEHEAD){
			return 0xDD00FF00;
		}else if(type == t.SNAKEBODY) {
			return 0xFF00FF00;
		} else if (type == t.SPACE){
			return 0x51222222;
		} else {
			return 0;
		}
	}
	
	public static void drawTiles(double xOffset, double yOffset) {
		for(Tile t : tiles) {
			t.draw(xOffset, yOffset);
//			System.out.println(t.leftBorder + ", " +
//			t.topBorder + ", " +
//			t.rightBorder + ", " +
//			t.bottomBorder);
		}
	}
	
	private void draw(double xOffset, double yOffset) {
		Resources.drawRect(xOffset + leftBorder, yOffset + topBorder, xOffset + rightBorder, yOffset + bottomBorder, color);
		if(num != -1)
			Resources.renderTextAtCenter(num + "", (int)(xOffset + leftBorder), (int)(xOffset + rightBorder), (int)(((bottomBorder + topBorder) / 2) - 5 + yOffset), color, false);
	}
}
