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
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.machines.BlockIntake;
import steamcraft.common.blocks.machines.BlockLightningRod;
import steamcraft.common.blocks.machines.BlockSteamBoiler;
import steamcraft.common.blocks.machines.BlockTurbine;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigAchievements;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.config.ConfigKeyBindings;
import steamcraft.common.items.ItemIngot;
import steamcraft.common.items.ItemRayGun;
import steamcraft.common.items.ItemSheet;
import steamcraft.common.items.ItemShrinkray;
import steamcraft.common.items.armor.ItemClockworkWings;
import steamcraft.common.items.armor.ItemSteamJetpack;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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

		if (event.crafting.getItem() instanceof ItemRayGun)
			event.player.triggerAchievement(ConfigAchievements.raygunAchieve);
		else if (event.crafting.getItem() instanceof ItemShrinkray)
			event.player.triggerAchievement(ConfigAchievements.shrinkrayAchieve);
		else if (craftingBlock instanceof BlockSteamBoiler)
			event.player.triggerAchievement(ConfigAchievements.boilerAchieve);
		else if (craftingBlock instanceof BlockIntake)
			event.player.triggerAchievement(ConfigAchievements.intakeAchieve);
		else if (craftingBlock instanceof BlockTurbine)
			event.player.triggerAchievement(ConfigAchievements.turbineAchieve);
		else if (craftingBlock instanceof BlockLightningRod)
			event.player.triggerAchievement(ConfigAchievements.rodAchieve);
		else if (event.crafting.getItem() instanceof ItemSheet)
			event.player.triggerAchievement(ConfigAchievements.sheetAchieve);
		else if (event.crafting.getItem() instanceof ItemClockworkWings)
			event.player.triggerAchievement(ConfigAchievements.wingsAchieve);
		else if (event.crafting.getItem() instanceof ItemSteamJetpack)
			event.player.triggerAchievement(ConfigAchievements.jetpackAchieve);
		//TODO Dosn't work!
		else if (event.crafting.isItemEqual(new ItemStack(ConfigItems.itemSteamWingpack)))
			event.player.triggerAchievement(ConfigAchievements.wingpackAchieve);
	}

	@SubscribeEvent
	public void onItemSmelted(PlayerEvent.ItemSmeltedEvent event)
	{
		if (event.smelting.getItem() instanceof ItemIngot)
			event.player.triggerAchievement(ConfigAchievements.ingotAchieve);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
	{
		if (event.player.getCommandSenderName().equals("warlordjones"))
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
		else if (event.player.getCommandSenderName().equals("WGSXFrank"))
		{
			ChatComponentText component = new ChatComponentText("Your Clockwerk Empire awaits!");
			component.getChatStyle().setBold(true);
			component.getChatStyle().setColor(EnumChatFormatting.GOLD);
			event.player.addChatComponentMessage(component);
			if (!Config.partyPooper)
			{
				ChatComponentText link = new ChatComponentText("Welcome ClockwerkKaiser to the server!");
				link.getChatStyle().setColor(EnumChatFormatting.YELLOW);
				link.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "www.twitch.tv/ClockwerkKaiser"));
				link.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click me to go to Clock's Twitch!")));
				MinecraftServer.getServer().getConfigurationManager().sendChatMsg(link);
			}
		}
		else if (event.player.getCommandSenderName().equals("Rongmario"))
		{
			ChatComponentText component = new ChatComponentText("Gooby PLZ");
			component.getChatStyle().setBold(true).setUnderlined(true);
			component.getChatStyle().setColor(EnumChatFormatting.AQUA);
			event.player.addChatComponentMessage(component);
		}
		else if (event.player.getCommandSenderName().equals("Spack3rz_MC"))
		{
			ChatComponentText component = new ChatComponentText("BAKA!");
			component.getChatStyle().setBold(true).setUnderlined(true);
			component.getChatStyle().setColor(EnumChatFormatting.LIGHT_PURPLE);
			event.player.addChatComponentMessage(component);
		}
		else if (event.player.getCommandSenderName().equals("lstarwars3"))
		{
			ChatComponentText component = new ChatComponentText("Pig in a tank!");
			component.getChatStyle().setColor(EnumChatFormatting.GREEN);
			event.player.addChatComponentMessage(component);
		}
		else if (event.player.getCommandSenderName().equals("yuloveme537"))
		{
			ChatComponentText component = new ChatComponentText("Looking gentlemanly today James :-)");
			component.getChatStyle().setColor(EnumChatFormatting.DARK_RED);
			event.player.addChatComponentMessage(component);
		}
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void KeyInputEvent(KeyInputEvent event)
	{
		if (ConfigKeyBindings.vanity.isPressed())
		{
			if (Minecraft.getMinecraft().currentScreen == null)
			{
				EntityPlayer p = Minecraft.getMinecraft().thePlayer;
				p.openGui(Steamcraft.instance, GuiIDs.GUI_ID_VANITY, p.worldObj, (int) p.posX, (int) p.posY, (int) p.posZ);
			}
		}
	}
}
