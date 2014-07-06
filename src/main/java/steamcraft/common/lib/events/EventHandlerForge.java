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

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.entities.EntityPlayerExtended;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * @author warlordjones
 *
 */
public class EventHandlerForge
{
	@SubscribeEvent
	public void updatePlayer(LivingEvent.LivingUpdateEvent event)
	{
		/*
		 * if (event.entityLiving instanceof EntityPlayer) { EntityPlayer player
		 * = (EntityPlayer) event.entityLiving;
		 *
		 * ItemStack legsSlot = player.inventory.armorItemInSlot(1);
		 *
		 * if (legsSlot != null) { if (legsSlot.getItem() ==
		 * ConfigItems.itemLegBraces) { float distToFall = player.fallDistance;
		 *
		 * if (distToFall > 3.0F) { player.fallDistance = distToFall * 0.888F;
		 * legsSlot.damageItem(1, player); } } }
		 *
		 * ItemStack bootsSlot = player.inventory.armorItemInSlot(0);
		 *
		 * if (bootsSlot != null) { if (!player.isInWater() && player.onGround
		 * && bootsSlot.getItem() == ConfigItems.itemRollerSkates) {
		 * player.moveEntityWithHeading(player.moveStrafing, player.moveForward
		 * * 0.8F); player.jumpMovementFactor = 0.03F; player.stepHeight = 0.0F;
		 * } } else if (bootsSlot == null || bootsSlot.getItem() !=
		 * ConfigItems.itemRollerSkates) { player.stepHeight = 0.5F; } }
		 */
	}


	@SubscribeEvent
	public void entityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
			EntityPlayerExtended.register((EntityPlayer) event.entity);
	}

	@SubscribeEvent
	public void onItemDrop(ItemTossEvent event)
	{
		NBTTagCompound thrower = event.entityItem.getEntityData();
		thrower.setString("thrower", event.player.getCommandSenderName());
	}

	@SubscribeEvent
	public void livingUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack is = player.inventory.armorItemInSlot(3);

			if ((is != null) && (is.getItem() == ConfigItems.brassGoggles))
			{
				player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2, 0, true));
			}
		}
	}
}
