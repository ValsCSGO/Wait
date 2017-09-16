package com.vals.wait;

import com.vals.wait.resources.Button;
import com.vals.wait.resources.Resources;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GameOver extends GuiScreen {
	public static boolean render = false;
	
	public static void renderScreen() {
		if(render) {
			render = false;
			Main.mc.displayGuiScreen(new GameOver());
		}
	}
	
	@Override
	public void drawScreen(int x, int y, float ticks) {
		ScaledResolution sr = new ScaledResolution(Main.mc);
		double buttonWidth = sr.getScaledWidth() * 0.2;
		double buttonHeight = 20;
		double halfWidth = (sr.getScaledWidth() / 2) - (buttonWidth / 2);
		double halfHeight = ((sr.getScaledHeight() / 2) - (buttonHeight / 2)) + (sr.getScaledHeight() * 0.05);
		double halfButtonWidth = (buttonWidth / 2);
		double width = (sr.getScaledWidth() / 2);
		int backgroundColor = 0x30FFFFFF;

		Button.registerButtons(
				new Button("Try again", "gameover", halfWidth - halfButtonWidth - 5, halfHeight, buttonHeight, buttonWidth, 0xFFFFFF, backgroundColor, true),
				new Button("Quit", "gameover", halfWidth + halfButtonWidth + 5, halfHeight, buttonHeight, buttonWidth, 0xFF5555, backgroundColor, true));
		
		double left = width - buttonWidth - 20;
		double right = width + buttonWidth + 20;
		Resources.drawRect(left, halfHeight - 85, right, halfHeight + 35, 0xf0000000);
		
		Resources.renderTextAtCenter("Game Over", (int)left, (int)right, (int)halfHeight - 40, 0xFF5555, true);
		Button.drawButtons("gameover");
		super.drawScreen(x, y, ticks);
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(mouseButton == 0) {
			for(Button b : Button.buttons.get("gameover")) {
				if((b.getX() <= mouseX) && (b.getY() <= mouseY) && (b.getHeight() + b.getY() > mouseY) && (b.getWidth() + b.getX() > mouseX)) {
					switch(b.getId()) {
					case 1:
						SelectGameScreen.render = true;
						break;
					case 2:
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
