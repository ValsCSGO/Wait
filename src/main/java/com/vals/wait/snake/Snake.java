package com.vals.wait.snake;

import com.vals.wait.Main;
import com.vals.wait.SelectGameScreen;
import com.vals.wait.resources.Button;
import com.vals.wait.resources.Resources;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class Snake extends GuiScreen {
	public static boolean render = false;

	public static void renderScreen() {
		if(render) {
			render = false;
			Main.mc.displayGuiScreen(new Snake());
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

		Button.registerButtons(
				new Button("Play", "snake", centeredScreenWidth - (buttonWidth / 2), bottom - 50, buttonHeight, buttonWidth, textColor, backgroundColor, true), 
				new Button("< Back", "snake", centeredScreenWidth - (buttonWidth / 2), bottom - 25, buttonHeight, buttonWidth, textColor, backgroundColor, true));

		Resources.drawRect(centeredScreenWidth - (screenwidth / 2), topHeight, centeredScreenWidth + (screenwidth / 2), bottom, 0xF0000000);
		Resources.renderTextAtCenter("Snake", (int)centeredScreenWidth - 10, (int)centeredScreenWidth + 10, (int)height, 0x55FF55, true);
		Button.drawButtons("snake");
		super.drawScreen(x, y, ticks);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(mouseButton == 0) {
			for(Button b : Button.buttons.get("snake")) {
				if((b.getX() <= mouseX) && (b.getY() <= mouseY) && (b.getHeight() + b.getY() > mouseY) && (b.getWidth() + b.getX() > mouseX)) {
					switch(b.getId()) {
					case 1:
						SnakePlayScreen.render = true;
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
