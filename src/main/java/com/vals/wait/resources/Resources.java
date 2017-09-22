package com.vals.wait.resources;

import java.awt.Graphics;

import org.lwjgl.opengl.GL11;

import com.vals.wait.Main;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class Resources {

	public static void DrawCircleOutline(float x, float y, float r) { 
        double theta = 2 * Math.PI / 360; 
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        
        double xHolder;
        double unitCircleX = 1; 
        double unitCircleY = 0; 

        GL11.glBegin(GL11.GL_LINE_LOOP); 
        for(int i = 0; i < 360; i++) { 
            GL11.glVertex2d(unitCircleX * r + x, unitCircleY * r + y);
            xHolder = unitCircleX;
            unitCircleX = cos * unitCircleX - sin * unitCircleY;
            unitCircleY = sin * xHolder + cos * unitCircleY;
        } 
        GL11.glEnd(); 
    }
	
	public static void drawCircle(float x, float y, float radius, int color) {
		float alpha = (float)(color >> 24 & 255) / 255.0F;
		float red = (float)(color >> 16 & 255) / 255.0F;
		float green = (float)(color >> 8 & 255) / 255.0F;
		float blue = (float)(color & 255) / 255.0F;
		
		GlStateManager.enableBlend();
		GlStateManager.disableTexture2D();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		GL11.glColor4f(red, green, blue, alpha);
		GL11.glBegin(GL11.GL_TRIANGLE_FAN);
		GL11.glVertex2f(x, y);
		for (float i = 0.0f; i < 360.0f; i += 0.2) {
			float x2 = (float) (x + Math.sin(i) * radius);
			float y2 = (float) (y + Math.cos(i) * radius);
			GL11.glVertex2f(x2, y2);
		}
		GL11.glEnd();
		GlStateManager.enableTexture2D();
		GlStateManager.disableBlend();
	}

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
