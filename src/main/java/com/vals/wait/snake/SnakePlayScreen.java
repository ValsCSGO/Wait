package com.vals.wait.snake;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.vals.wait.GameOver;
import com.vals.wait.Main;
import com.vals.wait.SelectGameScreen;
import com.vals.wait.resources.Resources;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class SnakePlayScreen extends GuiScreen {
	public static boolean render = false;
	public static int boardSizeX;
	public static int boardSizeY;
	private static double availablePixels;
	private static int tileSize;
	private static boolean gameOver = true;
	private static Food f;
	private static Player p;

	public static void renderScreen() {
		if(render) {
			render = false;
			gameOver = false;
			boardSizeX = 64;
			boardSizeY = 32;
			p = new Player(boardSizeX / 2, boardSizeY / 2, 0);
			f = Food.getRandomFood(boardSizeX, boardSizeY, p);
			Main.mc.displayGuiScreen(new SnakePlayScreen());
		}
	}

	@Override
	public void drawScreen(int x, int y, float ticks) {
		ScaledResolution sr = new ScaledResolution(Main.mc);
		Resources.drawRect(40, 40, sr.getScaledWidth() - 40, sr.getScaledHeight() - 40, 0x50000000);
		Tile.drawTiles(40, 40);
		super.drawScreen(x, y, ticks);
	}

	public static boolean isGameOver() {
		return gameOver;
	}
	
	public static void gameOver() {
		gameOver = true;
		Main.mc.displayGuiScreen(new GameOver());
	}

	public static void update() {
		ScaledResolution sr = new ScaledResolution(Main.mc);
		availablePixels = sr.getScaledHeight() - 80;
		tileSize = (int)availablePixels / boardSizeY;
		Tile.tiles.clear();
		p.move();
		if(p.getHeadLocX() == f.getX() && p.getHeadLocY() == f.getY()) 
			f.eat(p);
		if(f.isDead()) 
			f = Food.getRandomFood(boardSizeX, boardSizeY, p);
		for(int i = 0; i < boardSizeX; i++) {
			for(int j = 0; j < boardSizeY; j++) {
				if(i == f.getX() && j == f.getY()) {
					Tile.tiles.add(new Tile(i, j, tileSize, Type.FOOD));
				} else if(i == p.getHeadLocX() && j == p.getHeadLocY()) {
					Tile.tiles.add(new Tile(i, j, tileSize, Type.SNAKEHEAD));
				} else {
					Tile.tiles.add(new Tile(i, j, tileSize, Type.SPACE));
				}
				for(int k = 0; k < p.xLocs.size(); k++) {
					int currx = p.xLocs.get(k);
					int curry = p.yLocs.get(k);
					if(i == currx && j == curry) {
						Tile.tiles.add(new Tile(i, j, tileSize, Type.SNAKEBODY));
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) {
		switch (keyCode) {
		case Keyboard.KEY_W:
		case Keyboard.KEY_UP:
			p.setCurrentDirection(0);
			break;
		case Keyboard.KEY_A:
		case Keyboard.KEY_LEFT:
			p.setCurrentDirection(1);
			break;
		case Keyboard.KEY_S:
		case Keyboard.KEY_DOWN:
			p.setCurrentDirection(2);
			break;
		case Keyboard.KEY_D:
		case Keyboard.KEY_RIGHT:
			p.setCurrentDirection(3);
			break;
		case Keyboard.KEY_ESCAPE:
			Snake.render = true;
		}
	}

	@Override
	public boolean doesGuiPauseGame() { return false; }
}
