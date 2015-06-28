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
package steamcraft.common.lib.events;

import net.minecraft.block.Block;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.machines.BlockIntake;
import steamcraft.common.blocks.machines.BlockLightningRod;
import steamcraft.common.blocks.machines.BlockSteamBoiler;
import steamcraft.common.blocks.machines.BlockTurbine;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.init.InitAchievements;
import steamcraft.common.init.InitItems;
import steamcraft.common.items.ItemIngot;
import steamcraft.common.items.ItemSheet;
import steamcraft.common.items.armor.ItemClockworkWings;
import steamcraft.common.items.armor.ItemSteamJetpack;
import steamcraft.common.items.electric.ItemRayGun;
import steamcraft.common.items.electric.ItemShrinkray;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class EventHandlerFML
{
	@SubscribeEvent
	public void onItemCrafted(PlayerEvent.ItemCraftedEvent event)
	{
		Block craftingBlock = Block.getBlockFromItem(event.crafting.getItem());

		if(event.crafting.getItem() instanceof ItemRayGun)
			event.player.triggerAchievement(InitAchievements.raygunAchieve);
		else if(event.crafting.getItem() instanceof ItemShrinkray)
			event.player.triggerAchievement(InitAchievements.shrinkrayAchieve);
		else if(craftingBlock instanceof BlockSteamBoiler)
			event.player.triggerAchievement(InitAchievements.boilerAchieve);
		else if(craftingBlock instanceof BlockIntake)
			event.player.triggerAchievement(InitAchievements.intakeAchieve);
		else if(craftingBlock instanceof BlockTurbine)
			event.player.triggerAchievement(InitAchievements.turbineAchieve);
		else if(craftingBlock instanceof BlockLightningRod)
			event.player.triggerAchievement(InitAchievements.rodAchieve);
		else if(event.crafting.getItem() instanceof ItemSheet)
			event.player.triggerAchievement(InitAchievements.sheetAchieve);
		else if(event.crafting.getItem() instanceof ItemClockworkWings)
			event.player.triggerAchievement(InitAchievements.wingsAchieve);
		else if(event.crafting.getItem() instanceof ItemSteamJetpack)
			event.player.triggerAchievement(InitAchievements.jetpackAchieve);
		else if(event.crafting.getItem().getUnlocalizedName().equals(InitItems.itemSteamWingpack.getUnlocalizedName()))
			event.player.triggerAchievement(InitAchievements.wingpackAchieve);
	}

	@SubscribeEvent
	public void onItemSmelted(PlayerEvent.ItemSmeltedEvent event)
	{
		if(event.smelting.getItem() instanceof ItemIngot)
			event.player.triggerAchievement(InitAchievements.ingotAchieve);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
	{
		if(event.player.getUniqueID().toString().equals("c2e83bd4-e8df-40d6-a639-58ba8b05401e"))
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
		else if(event.player.getUniqueID().toString().equals("94644c62-f190-4f18-a69a-ad36e7425280"))
		{
			ChatComponentText component1 = new ChatComponentText("Welcome, Lord of the Mole People!");
			component1.getChatStyle().setColor(EnumChatFormatting.GRAY);
			event.player.addChatComponentMessage(component1);
		}
		else if(event.player.getUniqueID().toString().equals("edb4e6c2-7d07-4438-a0bb-2f4aabbea24d"))
		{
			ChatComponentText component2 = new ChatComponentText("Your Clockwerk Empire awaits!");
			component2.getChatStyle().setBold(true);
			component2.getChatStyle().setColor(EnumChatFormatting.GOLD);
			event.player.addChatComponentMessage(component2);
			if(!ConfigGeneral.partyPooper)
			{
				ChatComponentText link = new ChatComponentText("Welcome ClockwerkKaiser to the server!");
				link.getChatStyle().setColor(EnumChatFormatting.YELLOW);
				link.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.twitch.tv/ClockwerkKaiser"));
				link.getChatStyle().setChatHoverEvent(
						new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click me to go to Clock's Twitch!")));
				MinecraftServer.getServer().getConfigurationManager().sendChatMsg(link);
			}
		}
		if((event.player.worldObj.getPlayerEntityByName("warlordjones") != null)
				|| (event.player.worldObj.getPlayerEntityByName("decebaldecebal") != null))
		{
			event.player.triggerAchievement(InitAchievements.creatorAchieve);
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs)
	{
		if(eventArgs.modID.equals(ModInfo.ID))
			Config.initialise(Steamcraft.configFolder);
	}
}
