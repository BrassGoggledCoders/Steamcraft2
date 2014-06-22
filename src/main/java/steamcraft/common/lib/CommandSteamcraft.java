/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Mar 14, 2014, 9:02:08 AM]
 */
package steamcraft.common.lib;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

// TODO: Auto-generated Javadoc
/**
 * The Class CommandSteamcraft.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class CommandSteamcraft extends CommandBase
{

	/** The aliases. */
	private List<String> aliases;

	/**
	 * Instantiates a new command steamcraft.
	 */
	public CommandSteamcraft()
	{
		this.aliases = new ArrayList<String>();
		this.aliases.add("steamcraft");
		this.aliases.add("sc");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.command.CommandBase#canCommandSenderUseCommand(net.minecraft
	 * .command.ICommandSender)
	 */
	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.command.ICommand#getCommandName()
	 */
	@Override
	public String getCommandName()
	{
		return "sc2";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.command.ICommand#getCommandUsage(net.minecraft.command.
	 * ICommandSender)
	 */
	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/sc2 <parameters>";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.command.ICommand#processCommand(net.minecraft.command.
	 * ICommandSender, java.lang.String[])
	 */
	@Override
	public void processCommand(ICommandSender sender, String[] parameters)
	{
		/*
		 * ChatMessageComponent chat = sender.(); String prefix =
		 * EnumChatFormatting.GOLD + " [Steamcraft] ";
		 * 
		 * if (parameters.length < 1) {
		 * sender.addChatMessage(chat.appendText(prefix +
		 * EnumChatFormatting.LIGHT_PURPLE + "Welcome to Steamcraft 2!")); }
		 * else if (parameters.length == 1) { if
		 * (parameters[0].equalsIgnoreCase("help")) {
		 * sender.addChatMessage(chat.appendText(EnumChatFormatting.RED +
		 * " =-=-=-" + prefix + EnumChatFormatting.RED + "-=-=-=")); /*
		 * EnumChatFormatting.GOLD + "\r/sc2" + EnumChatFormatting.LIGHT_PURPLE
		 * + "-- the main mod function" + EnumChatFormatting.GOLD + "/sc2 help"
		 * + EnumChatFormatting.LIGHT_PURPLE + "-- displays this list" +
		 * EnumChatFormatting.GOLD + "/sc2 version" +
		 * EnumChatFormatting.LIGHT_PURPLE + "-- the running mod version" +
		 * EnumChatFormatting.GOLD + "/sc2 update" +
		 * EnumChatFormatting.LIGHT_PURPLE + "-- checks for latest version"));
		 * 
		 * } else if (parameters[0].equalsIgnoreCase("version")) {
		 * sender.addChatMessage(chat.appendText(prefix +
		 * EnumChatFormatting.LIGHT_PURPLE + "Steamcraft 2 v" + LibInfo.VERSION
		 * + " for MC v1.7.2")); } else if
		 * (parameters[0].equalsIgnoreCase("debug") &&
		 * this.isDeveloper(sender.getCommandSenderName())) { boolean debug =
		 * LibInfo.DEBUG; sender.addChatMessage(chat.appendText(prefix +
		 * EnumChatFormatting.LIGHT_PURPLE + "Debug mode for is set to: " +
		 * debug)); } else if (parameters[0].equalsIgnoreCase("update")) { if
		 * (Utils.checkForUpdatedVersion(LibInfo.NAME, LibInfo.VERSION)) {
		 * sender.addChatMessage(chat.appendText(prefix +
		 * EnumChatFormatting.LIGHT_PURPLE + "Version " + Utils.newestVersion +
		 * " of SC2 is now available!")); } else {
		 * sender.addChatMessage(chat.appendText(prefix +
		 * EnumChatFormatting.LIGHT_PURPLE +
		 * "You are running the latest version")); } } else {
		 * sender.addChatMessage(chat.appendText(prefix + EnumChatFormatting.RED
		 * + "Not a valid command!")); } }
		 */
	}

	/**
	 * Checks if is s c2 developer.
	 * 
	 * @param username
	 *            the username
	 * @return true, if is s c2 developer
	 */
	@SuppressWarnings("unused")
	private boolean isSC2Developer(String username)
	{
		return (username.equals("Surseance") || (username.equals("decebaldecebal") || (username.equals("warlordjones"))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.command.CommandBase#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object arg0)
	{
		return 0;
	}
}
