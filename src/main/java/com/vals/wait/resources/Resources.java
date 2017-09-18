package com.vals.wait.resources;

import com.vals.wait.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class Resources {

	public static void drawRect(double left, double top, double right, double bottom, int color) {
		if (left < right) {
			double i = left;
			left = right;
			right = i;
		}

		if (top < bottom) {
			double j = top;
			top = bottom;
			bottom = j;
		}

		float f3 = (float)(color >> 24 & 255) / 255.0F;
		float f = (float)(color >> 16 & 255) / 255.0F;
		float f1 = (float)(color >> 8 & 255) / 255.0F;
		float f2 = (float)(color & 255) / 255.0F;
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GlStateManager.color(f, f1, f2, f3);
		worldrenderer.begin(7, DefaultVertexFormats.POSITION);
		worldrenderer.pos(left+1, bottom-1, 0.0D).endVertex();
		worldrenderer.pos(right-1, bottom-1, 0.0D).endVertex();
		worldrenderer.pos(right-1, top-1, 0.0D).endVertex();
		worldrenderer.pos(left+1, top-1, 0.0D).endVertex();
		tessellator.draw();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

	public static void renderTextAtCenter(String text, int left, int right, int height, int color, boolean shadow) {
		int middle = (right - left) / 2;
		int textlength = Main.mc.fontRendererObj.getStringWidth(text);
		int textcenter = (int) Math.floor(textlength / 2);
		int truecenter = left + middle - textcenter;
		if(shadow) {
			Main.mc.fontRendererObj.drawStringWithShadow(text, truecenter, height, color);
		} else {
			Main.mc.fontRendererObj.drawString(text, truecenter, height, color);
		}
	}

	
}
