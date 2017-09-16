package com.vals.wait.command;

import com.vals.wait.Main;
import com.vals.wait.SelectGameScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class WaitCommand extends CommandBase {

	@Override
	public String getCommandName() {
		return "wait";
	}
	
	public int getRequiredPermissionLevel() {
        return 0;
    }

	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/wait";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] args) throws CommandException {
		SelectGameScreen.render = true;
	}
}
