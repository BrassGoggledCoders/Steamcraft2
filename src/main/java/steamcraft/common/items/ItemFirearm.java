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
 * File created @ [Apr 8, 2014, 7:56:41 PM]
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import steamcraft.common.entities.projectile.EntityBullet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemFirearm extends Item
{
	private int damage; // damage in half-hearts
	private int ammoID;
	private int fireMax; 
	private String fireSound; 
	private String reloadSound;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		
	}

	public ItemFirearm(int id, int damage, int delay, int ammoID, String fireSound, String reloadSound)
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

		if (player.inventory.hasItem(this.ammoID))
		{
			World world = player.worldObj;

			if (count == 1)
				world.playSoundAtEntity(player, this.reloadSound, 0.8F, 1.0F);

			if (count == this.getMaxItemUseDuration(stack) / 2)
			{
				player.inventory.consumeInventoryItem(this.ammoID);

				if (!world.isRemote)
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
