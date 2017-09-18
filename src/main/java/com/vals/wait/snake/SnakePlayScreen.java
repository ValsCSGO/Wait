package com.vals.wait.snake;

import java.util.Random;

import org.lwjgl.input.Keyboard;

import com.vals.wait.Game;
import com.vals.wait.GameOverScreen;
import com.vals.wait.Main;
import com.vals.wait.SelectGameScreen;
import com.vals.wait.resources.Resources;
import com.vals.wait.resources.Tile;
import com.vals.wait.resources.Type;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.world.WorldSettings.GameType;

public class SnakePlayScreen extends GuiScreen {
	public static boolean render = false;
	public static int boardSizeX = 1;
	public static int boardSizeY = 1;
	private static double availablePixels = 1;
	private static int tileSize = 1;
	private static boolean gameOver = true;
	private static Food f;
	private static Player p;

	public static void renderScreen() {
		if(render) {
			render = false;
			gameOver = false;
			boardSizeX = 64;
			boardSizeY = boardSizeX / 2;
			p = new Player(boardSizeX / 2, boardSizeY / 2, -2);
			f = Food.getRandomFood(boardSizeX, boardSizeY, p);
			Main.mc.displayGuiScreen(new SnakePlayScreen());
		}
	}

	@Override
	public void drawScreen(int x, int y, float ticks) {
		ScaledResolution sr = new ScaledResolution(Main.mc);
		double width = tileSize * boardSizeX;
		double height = tileSize * boardSizeY;
		double midScreen = sr.getScaledWidth() / 2;
		double size = width / 2;
		double leftBorder = midScreen - size;
		Resources.drawRect(leftBorder, 40, leftBorder + width + 1, 40 + height + 3, 0x90000000);
		Tile.drawTiles(leftBorder, 40);
		super.drawScreen(x, y, ticks);
	}

	public static boolean isGameOver() {
		return gameOver;
	}
	
	public static void gameOver() {
		gameOver = true;
		Main.setGame(Game.SNAKE);
		GameOverScreen.setScore("Your snake had a length of " + (p.getCurrentLength() + 1) + " tiles."); //+1 for head, too lazy to add the right way
		Main.mc.displayGuiScreen(new GameOverScreen());
	}

	public static void update() {
		ScaledResolution sr = new ScaledResolution(Main.mc);
		availablePixels = sr.getScaledWidth() - 80;
		tileSize = (int)availablePixels / boardSizeX;
		Tile.tiles.clear();
		p.move();
		if(p.getHeadLocX() == f.getX() && p.getHeadLocY() == f.getY()) 
			f.eat(p);
		if(f.isDead()) 
			f = Food.getRandomFood(boardSizeX, boardSizeY, p);
		for(int i = 0; i < boardSizeX; i++) {
			for(int j = 0; j < boardSizeY; j++) {
				if(i == f.getX() && j == f.getY()) {
					Tile.tiles.add(new Tile(i, j, tileSize, Type.SNAKEFOOD, -1));
				} else if(i == p.getHeadLocX() && j == p.getHeadLocY()) {
					Tile.tiles.add(new Tile(i, j, tileSize, Type.SNAKEHEAD, -1));
				} else {
					Tile.tiles.add(new Tile(i, j, tileSize, Type.SPACE, -1));
				}
				for(int k = 0; k < p.xLocs.size(); k++) {
					int currx = p.xLocs.get(k);
					int curry = p.yLocs.get(k);
					if(i == currx && j == curry) {
						Tile.tiles.add(new Tile(i, j, tileSize, Type.SNAKEBODY, -1));
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(char typedChar, int keyCode) {
		p.setLastDirection(p.getCurrentDirection());
		switch (keyCode) {
		case Keyboard.KEY_W:
		case Keyboard.KEY_UP:
			p.setCurrentDirection(-2);
			break;
		case Keyboard.KEY_A:
		case Keyboard.KEY_LEFT:
			p.setCurrentDirection(-1);
			break;
		case Keyboard.KEY_S:
		case Keyboard.KEY_DOWN:
			p.setCurrentDirection(2);
			break;
		case Keyboard.KEY_D:
		case Keyboard.KEY_RIGHT:
			p.setCurrentDirection(1);
			break;
		case Keyboard.KEY_ESCAPE:
			Snake.render = true;
		}
	}

	@Override
	public boolean doesGuiPauseGame() { return false; }
}
