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
 * File created @ [Mar 14, 2014, 9:16:25 AM]
 */
package steamcraft.common.lib.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.entities.EntityPlayerExtended;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EventHandlerEntity
{
	private int timer = 20;
	
	@ForgeSubscribe
	public void onItemDrop(ItemTossEvent event)
	{
		NBTTagCompound thrower = event.entityItem.getEntityData();
		thrower.setString("thrower", event.player.getCommandSenderName());
	}

	/*
	@ForgeSubscribe
	public void onPlayerLogIn(PlayerEvent. event)
	{
		if (event.player.getCommandSenderName().equals("Surseance"))
		{
			event.player.addExperience(10000);
		}
    }*/

	@ForgeSubscribe
	public void entityConstructing(EntityConstructing event)
	{
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayerExtended.register((EntityPlayer)event.entity);
		}
	}
	
	@ForgeSubscribe
	public void livingUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entityLiving;
			ItemStack is = player.inventory.armorItemInSlot(3);
			
			if ((is != null) && is.getItem() == ConfigItems.itemBrassGoggles)
			{
				
					player.addPotionEffect(new PotionEffect(Potion.nightVision.id, timer, 1));
					timer = 20;
			}
			else if ((is == null) || (is.getItem() != ConfigItems.itemBrassGoggles))
			{
				player.removePotionEffect(Potion.nightVision.id);
			}
		}
		else if (!(event.entityLiving instanceof EntityPlayer))
		{
			event.entityLiving.removePotionEffect(Potion.nightVision.id);
		}
	}
}
