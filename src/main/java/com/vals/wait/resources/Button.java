package com.vals.wait.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vals.wait.GameOver;
import com.vals.wait.Main;
import com.vals.wait.SelectGameScreen;
import com.vals.wait.snake.Snake;

import net.minecraft.client.gui.GuiIngame;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.model.obj.OBJModel.Group;

public class Button {
	public static Map<String, List<Button>> buttons = new HashMap<String, List<Button>>();
	private static int totalButtons = 0;
	private String text;
	private double buttonX;
	private double buttonY;
	private double height;
	private double width;
	private int color;
	private int backgroundColor;
	private boolean shadow;
	private int buttonId;
	private String group;

	public Button(String text, String group, double x, double y, double height, double width, int color, int backgroundColor, boolean shadow) {
		this.text = text;
		this.buttonX = x;
		this.buttonY = y;
		this.height = height;
		this.width = width;
		this.color = color;
		this.backgroundColor = backgroundColor;
		this.shadow = shadow;
		this.group = group;
	}

	public Button(String text, String group, double x, double y, double height, double width) {
		this(text, group, x, y, height, width, 0xFF000000, 0x50FFFFFF, true);
	}

	public double getX() {
		return buttonX;
	}

	public double getY() {
		return buttonY;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}
	
	public int getId() {
		return buttonId;
	}

	public static void registerButtons(Button... b) {
		totalButtons = 0;
		buttons.clear();
		for(Button button : b) {
			button.buttonId = totalButtons + 1;
			totalButtons++;
			List<Button> but;
			if(buttons.get(button.group) != null) {
				but = buttons.get(button.group);
			} else {
				but = new ArrayList<Button>();
			}
			but.add(button);
			buttons.put(button.group, but);
		}
	}

	public static void drawButtons(String group) {
		for(Button b : buttons.get(group)) {
			b.draw();
		}
	}

	private void draw() {
		double buttonMiddle = ((buttonY + height) - (height / 2)) - 5;
		Resources.drawRect(buttonX, buttonY, buttonX + width, buttonY + height, backgroundColor);
		Resources.renderTextAtCenter(text, (int)buttonX, (int)buttonX + (int)width, (int)buttonMiddle, color, shadow);
	}

	//System.out.println("Name: " + text + ", Group: " + group + ", ID: " + buttonId);

}
