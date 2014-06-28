package steamcraft.common.lib.events;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigAchievements;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.items.ItemIngot;
import steamcraft.common.items.ItemRayGun;
import steamcraft.common.items.ItemSheet;
import steamcraft.common.items.ItemShrinkray;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

public class EventHandlerFML
{
	/**
	 * On item crafted.
	 *
	 * @param event
	 *            the event
	 */
	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event)
	{
		if (event.crafting.getItem() instanceof ItemRayGun)
			event.player.addStat(ConfigAchievements.raygunAchieve, 1);
		else if (event.crafting.getItem() instanceof ItemShrinkray)
			event.player.addStat(ConfigAchievements.shrinkrayAchieve, 1);
		else if (event.crafting == new ItemStack(ConfigBlocks.blockSteamBoiler))
			event.player.addStat(ConfigAchievements.boilerAchieve, 1);
		else if (event.crafting.getItem() instanceof ItemSheet)
			event.player.addStat(ConfigAchievements.sheetAchieve, 1);
	}

	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemSmeltedEvent event)
	{
		if (event.smelting.getItem() instanceof ItemIngot)
			event.player.addStat(ConfigAchievements.ingotAchieve, 1);
	}
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
	{
		if(event.player.getCommandSenderName().equals("warlordjones"))
		{
			ChatComponentText component = new ChatComponentText("Encrypted Connection to Stark Secure Server #1 Established");
			component.getChatStyle().setBold(true);
			component.getChatStyle().setColor(EnumChatFormatting.RED);
			event.player.addChatComponentMessage(component);
			ChatComponentText component1 = new ChatComponentText("Jarvis: At your service sir");
			component1.getChatStyle().setItalic(true);
			component1.getChatStyle().setColor(EnumChatFormatting.GREEN);
			event.player.addChatComponentMessage(component1);
		}
		else if(event.player.getCommandSenderName().equals("WGSXFrank"))
		{
			ChatComponentText component = new ChatComponentText("Your Clockwerk Empire awaits!");
			component.getChatStyle().setBold(true);
			component.getChatStyle().setColor(EnumChatFormatting.GOLD);
			event.player.addChatComponentMessage(component);
			if(!Config.partyPooper)
			{
			ChatComponentText link = new ChatComponentText("Welcome ClockwerkKaiser to the server!");
			link.getChatStyle().setColor(EnumChatFormatting.YELLOW);
			link.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "www.twitch.tv/ClockwerkKaiser"));
			link.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click me to go to Clock's Twitch!")));
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(link);
			}
		}
		else if(event.player.getCommandSenderName().equals("Rongmario"))
		{
			ChatComponentText component = new ChatComponentText("Gooby PLZ");
			component.getChatStyle().setBold(true).setUnderlined(true);
			component.getChatStyle().setColor(EnumChatFormatting.AQUA);
			event.player.addChatComponentMessage(component);
		}
		else if(event.player.getCommandSenderName().equals("Spack3rz_MC"))
		{
			ChatComponentText component = new ChatComponentText("BAKA!");
			component.getChatStyle().setBold(true).setUnderlined(true);
			component.getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE);
			event.player.addChatComponentMessage(component);
		}
		else if(event.player.getCommandSenderName().equals("lstarwars3"))
		{
			ChatComponentText component = new ChatComponentText("Pig in a tank!");
			component.getChatStyle().setColor(EnumChatFormatting.GREEN);
			event.player.addChatComponentMessage(component);
		}
	}
}
