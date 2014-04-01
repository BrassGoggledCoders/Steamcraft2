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
 * File created @ [Jan 30, 2014, 5:33:43 PM]
 */
package common.steamcraft.common.core.handler;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.FillBucketEvent;

import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.item.ModArmors;
import common.steamcraft.common.item.ModItems;

/**
 * @author MrArcane111 & general3214
 *
 */
public class ModEventHandler 
{
	@ForgeSubscribe
	public void updatePlayer(LivingEvent.LivingUpdateEvent event) {
		if (event.entityLiving instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			
			//I do not know if these can be moved since they change the step height
			ItemStack bootsSlot = player.inventory.armorItemInSlot(0); // le boots

			if (bootsSlot != null) {
				if (!player.isInWater() && player.onGround && bootsSlot.itemID == ModArmors.rollerSkates.itemID) {	
					player.moveEntityWithHeading(player.moveStrafing, player.moveForward * 0.8F);
					player.jumpMovementFactor = 0.03F;
					player.stepHeight = 0.0F;
				}
				if (!player.isInWater() && bootsSlot.itemID == ModArmors.pnematicBoots.itemID)
				{
					//player.jumpMovementFactor = 1F;
					player.stepHeight = 1F;
				}
			} 
			else if (bootsSlot == null || bootsSlot.itemID != ModArmors.rollerSkates.itemID) {
				player.stepHeight = 0.5F;
			}
		}
	}

    @ForgeSubscribe
    public void onEntityFall(LivingFallEvent event) {
        if (event.entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.entityLiving;

            ItemStack chestSlot = player.inventory.armorItemInSlot(2); // le chest

            if (chestSlot != null) {
            		
                /*
                 * I will start working on this afterwards
                 * 
                if(chestSlot.itemID == ModArmors.steamWings.itemID)
                {
                	ItemSteamWings wings = (ItemSteamWings)chestSlot.getItem();
                	event.distance = 0;
                }
                */
            }
        }
    }

    @ForgeSubscribe
    public void onBucketFill(FillBucketEvent event) {

            ItemStack result = fillCustomBucket(event.world, event.target);

            if (result == null)
                    return;

            event.result = result;
            event.setResult(Result.ALLOW);
    }

    private ItemStack fillCustomBucket(World world, MovingObjectPosition pos) 
    {
	 	int id = world.getBlockId(pos.blockX, pos.blockY, pos.blockZ);

        if (id == ModBlocks.steamBlock.blockID && world.getBlockMetadata(pos.blockX, pos.blockY, pos.blockZ) == 0) 
        {
                world.setBlockToAir(pos.blockX, pos.blockY, pos.blockZ);
                return new ItemStack(ModItems.steamBucket);
        } else
                return null;

    }
}