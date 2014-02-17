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
 * File created @ [Feb 4, 2014, 5:13:56 PM]
 */
package common.steamcraft.common.item;

import common.steamcraft.common.entity.EntityBullet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 
 * @author MrArcane111 & EntireCraft
 *
 */
public class ItemGun extends ItemMod
{
	private int damage; // damage in half-hearts
	private int ammoID;
	private int fireMax; 
	private String fireSound; 
	private String reloadSound;

	public ItemGun(int id, int damage, int delay, int ammoID, String fireSound, String reloadSound)
	{ 
		super(id); 
		this.damage = damage;
		this.fireMax = delay;
		this.ammoID = ammoID; 
		this.fireSound = fireSound; 
		this.reloadSound = reloadSound; 
		this.setMaxStackSize(1);  
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}

	@Override
    public void onUsingItemTick(ItemStack stack, EntityPlayer player, int count)
    {
		
		/*
		 * 
		 * Also implemented the reload.The weapon fires first then reloads, but if you do not hold the right mouse button for long enough, it won't
		 * play the reload sound, which is a small problem.To track if it is reloaded or not, it needs to be implemented through NBT and onUpdate()
		 * 
		 */

		if(player.inventory.hasItem(this.ammoID))
		{
			World world = player.worldObj;
			
			if(count == 1)
				world.playSoundAtEntity(player, this.reloadSound, 0.8F, 1.0F);
			
			if(count == this.getMaxItemUseDuration(stack) / 2)
			{
				player.inventory.consumeInventoryItem(this.ammoID);
	
				if(!world.isRemote)
				{
					world.spawnEntityInWorld(new EntityBullet(world, player, this.damage, 1));
				}
	
				world.playSoundAtEntity(player, this.fireSound, 0.6F, 1.0F);
			}
		}
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
		return stack;
	}

	@Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return this.fireMax;
    }
}