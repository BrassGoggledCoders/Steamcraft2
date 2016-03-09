/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package xyz.brassgoggledcoders.steamcraft.common.lib;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.ClickEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

/**
 * @author Surseance
 *
 */
public class CommandSteamcraft extends CommandBase
{
	private final List<String> aliases;// TODO use aliases

	public CommandSteamcraft()
	{
		this.aliases = new ArrayList<String>();
		this.aliases.add("steamcraft");
		this.aliases.add("sc");
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	@Override
	public String getCommandName()
	{
		return "sc2";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/sc2 <parameters>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] parameters)
	{
		ChatComponentText prefix = new ChatComponentText(" [Steamcraft] ");
		prefix.getChatStyle().setColor(EnumChatFormatting.GOLD);

		if (parameters.length < 1)
			sender.addChatMessage(prefix.appendSibling(new ChatComponentText("Welcome to Steamcraft 2!")));
		else if (parameters.length == 1)
		{
			if (parameters[0].equalsIgnoreCase("help"))
			{
				sender.addChatMessage(new ChatComponentText("version -- returns current SC2 version"));
				sender.addChatMessage(new ChatComponentText("contact -- returns ways to contact us"));
				sender.addChatMessage(new ChatComponentText(
						"issue <name> <body> -- creates a new Github Issue on the Steamcraft Repo. Automatically appends your username to the end of the issue's body text. Use _ instead of spaces."));
			}
			else if (parameters[0].equalsIgnoreCase("version"))
			{
				ChatComponentText version = new ChatComponentText("Steamcraft 2 " + ModInfo.VERSION + " for MC v1.7.2");
				sender.addChatMessage(prefix.appendSibling(version));
			}
			else if (parameters[0].equalsIgnoreCase("contact"))
			{
				ChatComponentText contact = new ChatComponentText("Contact Information");
				sender.addChatMessage(prefix.appendSibling(contact));
				ChatComponentText twitter = new ChatComponentText("Twitter");
				twitter.getChatStyle().setColor(EnumChatFormatting.BLUE);
				twitter.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://twitter.com/brasscoders"));
				sender.addChatMessage(twitter);
				ChatComponentText email = new ChatComponentText("Email");
				email.getChatStyle().setColor(EnumChatFormatting.RED);
				email.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "mailto:brasscoders@gmail.com"));
				sender.addChatMessage(email);
				ChatComponentText git = new ChatComponentText("Github");
				git.getChatStyle().setColor(EnumChatFormatting.GRAY);
				git.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/BrassGoggledCoders"));
				sender.addChatMessage(git);
			}
			else if (parameters[0].equalsIgnoreCase("issue"))
			{
				sender.addChatMessage(prefix.appendText("Invalid Usage. Correct Syntax is /sc2 issue <title> <body text>"));
			}
			else
				sender.addChatMessage(prefix.appendText("Not a valid sub-command! Run /sc2 help for help!"));
		}
		else if (parameters.length == 2)
		{
			prefix.appendText("Invalid number of parameters");
		}
		else
		{
			prefix.appendText("Invalid number of parameters");
		}
	}
}
