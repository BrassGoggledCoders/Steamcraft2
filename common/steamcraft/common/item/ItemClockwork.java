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
 * File created @ [Feb 17, 2014, 3:51:21 PM]
 */
package common.steamcraft.common.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author MrArcane111
 *
 */
public class ItemClockwork extends ItemMod {
	protected float efficiency;
	protected int damage;

	protected ItemClockwork(int id, float efficiency, int damage) {
		super(id);
		this.efficiency = efficiency;
		this.damage = damage;
		this.setNoRepair();
		this.setMaxStackSize(1);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase living1, EntityLivingBase living2) {
		if ((this.damage > 1) && (stack.getItemDamage() < getMaxDamage())) {
			stack.setItemDamage(stack.getItemDamage() + 1);
		}
		
		return this.damage > 1;
	}

	@Override
	public boolean onBlockDestroyed(ItemStack stack, World world, int blockID, int x, int y, int z, EntityLivingBase owner) {
		if ((this.efficiency > 1.0D) && (stack.getItemDamage() < getMaxDamage()) && (Block.blocksList[blockID].getBlockHardness(world, x, y, z) != 0.0D)) {
			stack.setItemDamage(stack.getItemDamage() + 1);
		}

		return true;
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block, int metadata) {
		if ((stack.getItemDamage() < getMaxDamage()) && (ForgeHooks.isToolEffective(stack, block, metadata))) {
			return this.efficiency;
		}

		return getStrVsBlock(stack, block);
	}

	public int getDamageVsEntity(Entity entity) {
		return this.damage;
	}
	
	@SuppressWarnings("all")
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag) {
		if (stack.getItemDamage() > 0) {
			list.add(String.format("%d/%d WE", new Object[] { Integer.valueOf(getMaxDamage() - stack.getItemDamage()), Integer.valueOf(getMaxDamage() - 1) }));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D() {
		return true;
	}
}
