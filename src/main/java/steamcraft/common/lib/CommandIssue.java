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
package steamcraft.common.lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import net.minecraftforge.common.ForgeVersion;

import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.service.IssueService;
import steamcraft.common.entities.EntityPlayerExtended;

/**
 * @author Surseance
 * 
 */
public class CommandIssue extends CommandBase
{
	private final List<String> aliases;// TODO use aliases

	public CommandIssue()
	{
		this.aliases = new ArrayList<String>();
		this.aliases.add("reportissue");
	}

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender)
	{
		return true;
	}

	@Override
	public String getCommandName()
	{
		return "issue";
	}

	@Override
	public String getCommandUsage(ICommandSender sender)
	{
		return "/issue <title> <body>";
	}

	@Override
	public void processCommand(ICommandSender sender, String[] parameters)
	{
		if(sender instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) sender;
			EntityPlayerExtended props = ((EntityPlayerExtended) player.getExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME));
			if(parameters.length == 2)
			{
				if(props.getCooldown() == 0)
				{
					Issue issue = new Issue();
					issue.setNumber(1);
					issue.setTitle(parameters[0].replace("_", " "));
					issue.setBody(parameters[1].replace("_", " ") + " Forge Version:" + ForgeVersion.getVersion() + "Mod Version:" + ModInfo.VERSION
							+ " Reporter: " + sender.getCommandSenderName());
					IssueService issueservice = new IssueService();
					// This token is read only, don't even bother trying to use it to hack :P
					issueservice.getClient().setOAuth2Token("df100cf80572205cad48cefa0cbfc5baf8d9c716");
					try
					{
						issueservice.createIssue("BrassGoggledCoders", "Steamcraft2", issue);
						props.setCooldown(1200);
					}
					catch(IOException e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					ChatComponentText invalid = new ChatComponentText(
							"[Steamcraft Issue Reporter] You must wait " + props.getCooldown() / 20
									+ " seconds before using that command again");
					invalid.getChatStyle().setColor(EnumChatFormatting.RED);
					sender.addChatMessage(invalid);
				}

			}
			else
			{
				ChatComponentText invalid = new ChatComponentText("[Steamcraft Issue Reporter] Invalid number of parameters. Usage is /issue <title> <body>");
				invalid.getChatStyle().setColor(EnumChatFormatting.GOLD);
				sender.addChatMessage(invalid);
			}
		}
	}
}
