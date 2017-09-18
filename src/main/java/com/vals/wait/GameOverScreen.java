package com.vals.wait;

import com.vals.wait.ballz.BallzPlayScreen;
import com.vals.wait.resources.Button;
import com.vals.wait.resources.Resources;
import com.vals.wait.snake.SnakePlayScreen;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GameOverScreen extends GuiScreen {
	public static boolean render = false;
	private static String score;
	
	public static void renderScreen() {
		if(render) {
			render = false;
			Main.mc.displayGuiScreen(new GameOverScreen());
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
		
		Resources.renderTextAtCenter("Game Over", (int)left, (int)right, (int)halfHeight - 60, 0xFF5555, true);
		Resources.renderTextAtCenter(score, (int)left, (int)right, (int)halfHeight - 30, 0xFFFFFF, true);
		Button.drawButtons("gameover");
		super.drawScreen(x, y, ticks);
	}
	
	public static void setScore(String newscore) {
		score = newscore;
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if(mouseButton == 0) {
			for(Button b : Button.buttons.get("gameover")) {
				if((b.getX() <= mouseX) && (b.getY() <= mouseY) && (b.getHeight() + b.getY() > mouseY) && (b.getWidth() + b.getX() > mouseX)) {
					switch(b.getId()) {
					case 1:
						switch(Main.getGame()) {
						case SNAKE:
							SnakePlayScreen.render = true;
							break;
						case MINESWEEPER:
							//TODO add minesweeper
							break;
						case BALLZ:
							BallzPlayScreen.render = true;
							break;
						}
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
