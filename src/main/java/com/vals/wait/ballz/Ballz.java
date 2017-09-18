package com.vals.wait.ballz;

import com.vals.wait.Main;
import com.vals.wait.SelectGameScreen;
import com.vals.wait.resources.Button;
import com.vals.wait.resources.Resources;
import com.vals.wait.resources.Tile;
import com.vals.wait.resources.Type;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class Ballz extends GuiScreen {
	public static boolean render = false;
	public static boolean tilesMade = false;
	
	public static void renderScreen() {
		if(render) {
			render = false;
			tilesMade = false;
			Main.mc.displayGuiScreen(new Ballz());
		}
	}
	
	@Override
	public void drawScreen(int x, int y, float ticks) {
		ScaledResolution sr = new ScaledResolution(Main.mc);
		double screenwidth = sr.getScaledWidth() * 0.3;
		double height = sr.getScaledHeight() * 0.6;
		
		double centeredScreenWidth = sr.getScaledWidth() / 2;
		double buttonHeight = 20;
		double buttonWidth = screenwidth - 10;
		double topHeight = (sr.getScaledHeight() / 2) - (height / 2);
		double bottom = (sr.getScaledHeight() / 2) + (height / 2);
		int textColor = 0xFFFF55FF;
		int backgroundColor = 0x30FFFFFF;
		
		double tileWidth = (screenwidth / 2) / 8;
		double board = tileWidth * 8;
		double width = board / 2;
		double left = centeredScreenWidth - width;
		
		double between = ((topHeight + 15 + bottom - 55 - board) / 2) + 5;

		Button.registerButtons(
				new Button("Play", "ballz", centeredScreenWidth - (buttonWidth / 2), bottom - 50, buttonHeight, buttonWidth, textColor, backgroundColor, true), 
				new Button("< Back", "ballz", centeredScreenWidth - (buttonWidth / 2), bottom - 25, buttonHeight, buttonWidth, textColor, backgroundColor, true));

		Resources.drawRect(centeredScreenWidth - (screenwidth / 2), topHeight, centeredScreenWidth + (screenwidth / 2), bottom, 0xF0000000);
		makeTiles(tileWidth);
		Tile.drawTiles(left, between);
		Resources.renderTextAtCenter("Ballz", (int)centeredScreenWidth - 10, (int)centeredScreenWidth + 10, (int)topHeight + 15, 0x55FF55, true);
		Button.drawButtons("ballz");
		super.drawScreen(x, y, ticks);
	}
	
	public void makeTiles(double tileSize) {
		if(!tilesMade) {
			tilesMade = true;
			Tile.tiles.clear();
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					if(j == 1 && i == 2) {
						Tile.tiles.add(new Tile(i, j, tileSize, Type.SNAKEFOOD, -1));
					} else if ((j == 1 && (i == 4 || i == 5)) || (j == 2 && i == 5) || ((j >= 2 && j <= 5) && i == 6) || (j == 5 && i == 5) || (j == 6 && (i >= 1 && i <= 5)) || ((j >= 3 && j <= 5) && i == 1) || (j == 3 && (i == 2 || i == 3))) {
						Tile.tiles.add(new Tile(i, j, tileSize, Type.SNAKEBODY, -1));
					} else {
						Tile.tiles.add(new Tile(i, j, tileSize, Type.SPACE, -1));
					}
				}
			}
		}
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(mouseButton == 0) {
			for(Button b : Button.buttons.get("ballz")) {
				if((b.getX() <= mouseX) && (b.getY() <= mouseY) && (b.getHeight() + b.getY() > mouseY) && (b.getWidth() + b.getX() > mouseX)) {
					switch(b.getId()) {
					case 1:
						BallzPlayScreen.render = true;
						break;
					case 2:
						Main.mc.displayGuiScreen(new SelectGameScreen());
						break;
					}
				}
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() { return false; }
}
