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

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

import steamcraft.common.config.ConfigBalance;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.container.InventoryVanity;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class EventHandlerForge
{
	@SubscribeEvent
	public void entityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
			EntityPlayerExtended.register((EntityPlayer) event.entity);
	}

	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack is = player.inventory.armorItemInSlot(3);

			if ((is != null) && (is.getItem() == InitItems.itemBrassGoggles))
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2, 0, true));
			if ((is != null) && (is.getItem() == InitItems.itemDivingHelmet) && player.isInWater())
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2, 0, true));

			// TODO Change tis!!
			ItemStack stack = player.inventory.armorItemInSlot(0);
			if (stack == null)
				player.stepHeight = 0.5F;
		}
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(ModInfo.ID) && event.configID.equalsIgnoreCase(ConfigBalance.configId))
			ConfigBalance.loadConfiguration();
	}

	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onRenderPlayer(RenderPlayerEvent event)
	{
		IExtendedEntityProperties props = event.entityPlayer.getExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME);
		InventoryVanity inventory = ((EntityPlayerExtended) props).getInventory();
		EntityPlayer player = event.entityPlayer;

		if (inventory.getSizeInventory() != 0)
		{
			player.rotationYaw++;
		}
	}

	public Random rand = new Random();

	@SubscribeEvent
	public void onMobDrops(LivingDropsEvent event)
	{
		if (event.entity instanceof EntityIronGolem && event.entity.isBurning() && ConfigGeneral.golemFireDrop)
		{
			event.drops.clear();
			ItemStack ingot = new ItemStack(InitItems.itemIngot, 3 + this.rand.nextInt(3), 7);
			ItemStack poppy = new ItemStack(Item.getItemFromBlock(Blocks.red_flower), this.rand.nextInt(3), 0);

			EntityItem ingotDrop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, ingot);
			EntityItem poppyDrop = new EntityItem(event.entity.worldObj, event.entity.posX, event.entity.posY, event.entity.posZ, poppy);

			event.drops.add(ingotDrop);
			event.drops.add(poppyDrop);
		}
	}
}
