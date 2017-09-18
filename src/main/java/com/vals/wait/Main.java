package com.vals.wait;

import com.vals.wait.ballz.Ballz;
import com.vals.wait.ballz.BallzPlayScreen;
import com.vals.wait.command.WaitCommand;
import com.vals.wait.snake.Snake;
import com.vals.wait.snake.SnakePlayScreen;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;

@Mod(name = Main.NAME, modid = Main.MODID, version = Main.VERSION)
public class Main {
	public static final String NAME = "Wait";
    public static final String MODID = "wait";
    public static final String VERSION = "1.0.1";
    
    public static Game lastPlayedGame;
    private int gameTicks = 0;
    
    public static Minecraft mc = Minecraft.getMinecraft();
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
		ClientCommandHandler.instance.registerCommand(new WaitCommand());
		MinecraftForge.EVENT_BUS.register(this);
    }
    
    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
    	SelectGameScreen.renderScreen();
    	GameOverScreen.renderScreen();
    	
    	Snake.renderScreen();
    	SnakePlayScreen.renderScreen();
    	
    	Ballz.renderScreen();
    	BallzPlayScreen.renderScreen();
    }
    
    @SubscribeEvent
    public void clientTick(ClientTickEvent event) {
    	if(!SnakePlayScreen.isGameOver()) {
    		if(gameTicks > 5) {
    			gameTicks = 0;
    			SnakePlayScreen.update();
    		}
    		gameTicks++;
    	}
    }
    
    public static Game getGame() {
    	return lastPlayedGame;
    }
    
    public static void setGame(Game g) {
    	lastPlayedGame = g;
    }
}
