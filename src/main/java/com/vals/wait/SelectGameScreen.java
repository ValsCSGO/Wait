package com.vals.wait;

import java.util.ArrayList;
import java.util.List;

import com.vals.wait.ballz.Ballz;
import com.vals.wait.resources.Button;
import com.vals.wait.resources.Resources;
import com.vals.wait.snake.Snake;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SelectGameScreen extends GuiScreen {
	public static boolean render = false;

	public static void renderScreen() {
		if(render) {
			render = false;
			Main.mc.displayGuiScreen(new SelectGameScreen());
		}
	}

	@Override
	public void drawScreen(int x, int y, float ticks) {
		ScaledResolution sr = new ScaledResolution(Main.mc);
		double buttonWidth = sr.getScaledWidth() * 0.4;
		double buttonHeight = 20;
		double halfWidth = (sr.getScaledWidth() / 2) - (buttonWidth / 2);
		double halfHeight = (sr.getScaledHeight() / 2) - (buttonHeight / 2);
		int textColor = 0xFFFF55FF;
		int backgroundColor = 0x30FFFFFF;

		Button.registerButtons(
				new Button("Ballz", "games", halfWidth, halfHeight - 45, buttonHeight, buttonWidth, textColor, backgroundColor, true), 
				new Button("Minesweeper", "games", halfWidth, halfHeight - 15, buttonHeight, buttonWidth, textColor, backgroundColor, true),
				new Button("Snake", "games", halfWidth, halfHeight + 15, buttonHeight, buttonWidth, textColor, backgroundColor, true),
				new Button("Quit", "games", halfWidth, halfHeight + 45, buttonHeight, buttonWidth, 0xFF5555, backgroundColor, true));

		Resources.drawRect(halfWidth - 10, halfHeight - 55, halfWidth + buttonWidth + 10, halfHeight + 75, 0xf0000000);
		Button.drawButtons("games");
		super.drawScreen(x, y, ticks);
	}

	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(mouseButton == 0) {
			for(Button b : Button.buttons.get("games")) {
				if((b.getX() <= mouseX) && (b.getY() <= mouseY) && (b.getHeight() + b.getY() > mouseY) && (b.getWidth() + b.getX() > mouseX)) {
					switch(b.getId()) {
					case 1:
						Ballz.render = true;
						break;
					case 2:
						GameOverScreen.render = true;
						break;
					case 3:
						Snake.render = true;
						break;
					case 4:
						Main.mc.displayGuiScreen(null);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public boolean doesGuiPauseGame() { return false; }
}
