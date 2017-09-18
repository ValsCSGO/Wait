package com.vals.wait.ballz;

import com.vals.wait.Main;
import com.vals.wait.resources.Resources;
import com.vals.wait.resources.Tile;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

//THIS IS THE MAIN GAME SCREEN
//NEEDS PLAY BUTTON, STORE, DISPLAY COINS
//ALL THAT OTHER SHIT

public class BallzPlayScreen extends GuiScreen {
	public static boolean render = false;
	public static int boardSizeX = 1;
	public static int boardSizeY = 1;
	private static double availablePixels = 1;
	private static int tileSize = 1;
	private static boolean gameOver = true;

	public static void renderScreen() {
		if(render) {
			render = false;
			gameOver = false;
			boardSizeX = 64;
			boardSizeY = boardSizeX / 2;
			Main.mc.displayGuiScreen(new BallzPlayScreen());
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
}

