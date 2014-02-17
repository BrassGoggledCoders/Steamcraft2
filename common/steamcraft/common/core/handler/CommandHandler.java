/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 7, 2014, 4:13:42 PM]
 */
package common.steamcraft.common.core.handler;

import java.util.Arrays;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

/**
 * The SC2 command handler class.
 * 
 * @author MrArcane111
 *
 */
public class CommandHandler extends CommandBase {
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(sender);
	}
	
	@Override
	public String getCommandName() {
		return "sc2";
	}
	
	@Override
	public String getCommandUsage(ICommandSender sender) {
		return "/sc2 <parameters>";
	}
	
	@Override
	public List getCommandAliases() {
		return Arrays.asList(new String[] {"steamcraft2", "SC2"});
	}
	
	@Override
	public void processCommand(ICommandSender sender, String[] params) {
		if (params.length < 1) {
            sender.sendChatToPlayer(ChatMessageComponent.createFromText("Welcome to SC2!").setColor(EnumChatFormatting.GOLD));
            sender.sendChatToPlayer(ChatMessageComponent.createFromText("Type '/sc2 help' for a list of available commands.").setColor(EnumChatFormatting.BLUE));
        } else if (params.length == 1) {
	        if (params[0].equalsIgnoreCase("help")) {
	            sender.sendChatToPlayer(ChatMessageComponent.createFromText(" /sc2 help -- displays this guide.").setColor(EnumChatFormatting.DARK_AQUA));
	            sender.sendChatToPlayer(ChatMessageComponent.createFromText(" /sc2 latest -- checks for the newest available version of SC2.").setColor(EnumChatFormatting.AQUA));
	            sender.sendChatToPlayer(ChatMessageComponent.createFromText(" /sc2 debug -- toggles SC2's debug mode.").setColor(EnumChatFormatting.DARK_BLUE));
	        } else if (params[0].equalsIgnoreCase("latest")) {
	        	// Add update shit here...
	        } else if (params[0].equalsIgnoreCase("debug")) {
	        	//SC2.debug = !SC2.debug;
	        	//sender.sendChatToPlayer(ChatMessageComponent.createFromText(EnumColor.DARK_BLUE + "[SC2]" + EnumColor.GREY + " Debug mode set to " + EnumColor.DARK_GREY + SC2.debug));
	        } else {
	        	//sender.sendChatToPlayer(ChatMessageComponent.createFromText(EnumColor.DARK_BLUE + "[SC2]" + EnumColor.GREY + " Unknown command. Type '" + EnumColor.INDIGO + "/sc2 help" + EnumColor.GREY + "' for help."));
	        }
        }
	}
}
